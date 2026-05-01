package com.example.reservationRequest.Controller;

import com.example.reservationRequest.Entity.ReservationRequest;
import com.example.reservationRequest.Entity.ReservationResponse;
import com.example.reservationRequest.Entity.Table;
import com.example.reservationRequest.Entity.WaitlistEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final Map<String, List<WaitlistEntry>> waitlist = new HashMap<>();
    private final Map<String, ReservationResponse> reservations = new HashMap<>();
    private final RestTemplate restTemplate = new RestTemplate();
    private final AtomicInteger reservationCounter = new AtomicInteger(1);
    private final String tableServiceUrl = "http://localhost:8081/tables/";

    @PostMapping
    public ResponseEntity<?> handleReservation(@RequestBody ReservationRequest request) {
        if (!"reserve".equalsIgnoreCase(request.getReservationType()) &&
                !"cancel".equalsIgnoreCase(request.getReservationType())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid reservation type"));
        }

        String tableId = request.getTableId();
        String tableUrl = tableServiceUrl + tableId;

        ResponseEntity<Table> response;
        try {
            response = restTemplate.getForEntity(tableUrl, Table.class);
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Table not found"));
        }

        Table table = response.getBody();

        if ("reserve".equalsIgnoreCase(request.getReservationType())) {
            if (table.isAvailable()) {
                // Confirm reservation
                updateTableAvailability(tableId, false);
                String reservationId = "R" + reservationCounter.getAndIncrement();
                ReservationResponse res = new ReservationResponse(reservationId, "success", tableId, null);
                reservations.put(reservationId, res);
                return ResponseEntity.ok(res);
            } else {
                // Add to waitlist
                waitlist.computeIfAbsent(tableId, k -> new ArrayList<>())
                        .add(new WaitlistEntry(request.getCustomerId(), request.getPreferences()));
                int position = waitlist.get(tableId).size();
                ReservationResponse res = new ReservationResponse(null, "waitlisted", null,
                        "You are #" + position + " in the queue.");
                return ResponseEntity.ok(res);
            }
        } else {
            // cancel
            Optional<Map.Entry<String, ReservationResponse>> resToCancel = reservations.entrySet().stream()
                    .filter(e -> e.getValue().getTableId().equals(tableId) &&
                            e.getValue().getStatus().equals("success") &&
                            request.getCustomerId().equals(e.getValue().getReservationId()))
                    .findFirst();

            resToCancel.ifPresent(e -> reservations.remove(e.getKey()));

            List<WaitlistEntry> queue = waitlist.getOrDefault(tableId, new ArrayList<>());
            if (!queue.isEmpty()) {
                WaitlistEntry next = queue.remove(0);
                updateTableAvailability(tableId, false);
                String reservationId = "R" + reservationCounter.getAndIncrement();
                ReservationResponse res = new ReservationResponse(reservationId, "success", tableId, null);
                reservations.put(reservationId, res);
                return ResponseEntity.ok(res);
            } else {
                updateTableAvailability(tableId, true);
                return ResponseEntity.ok(Map.of("status", "cancellation successful", "tableId", tableId));
            }
        }
    }

    private void updateTableAvailability(String tableId, boolean available) {
        restTemplate.put(tableServiceUrl + tableId, Map.of("available", available));
    }
}
