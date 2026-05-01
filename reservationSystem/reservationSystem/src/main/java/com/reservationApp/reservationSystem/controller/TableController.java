package com.reservationApp.reservationSystem.controller;

import com.reservationApp.reservationSystem.entity.Table;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tables")
public class TableController {

    private final Map<String, Table> tableData = new HashMap<>();

    public TableController() {
        // Hardcoded data
        tableData.put("T001", new Table("T001", 4, "Window", true));
        tableData.put("T002", new Table("T002", 2, "Corner", false));
        tableData.put("T003", new Table("T003", 6, "Center", true));
        tableData.put("T004", new Table("T004", 8, "Balcony", false));
    }

    @GetMapping("/{tableId}")
    public ResponseEntity<?> getTable(@PathVariable String tableId) {
        Table table = tableData.get(tableId);
        if (table == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Table not found"));
        }
        return ResponseEntity.ok(table);
    }

    @PutMapping("/{tableId}")
    public ResponseEntity<?> updateAvailability(@PathVariable String tableId, @RequestBody Map<String, Boolean> body) {
        Table table = tableData.get(tableId);
        if (table == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Table not found"));
        }
        table.setAvailable(body.getOrDefault("available", false));
        return ResponseEntity.ok(table);
    }
}
