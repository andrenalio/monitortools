package com.monitortools.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitortools.api.dto.EquipmentDTO;
import com.monitortools.api.service.EquipmentsService;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentsController {

	 private final EquipmentsService service;

	    public EquipmentsController(EquipmentsService service) {
	        this.service = service;
	    }

	    @PostMapping
	    public ResponseEntity<Object> cadastrar(@RequestHeader("X-Client-Id") String tenantId,@RequestBody EquipmentDTO dto) {
	        return ResponseEntity.ok(service.cadastrar(tenantId, dto));
	    }

	    @GetMapping
	    public ResponseEntity<Object> listar(@RequestHeader("X-Client-Id") String tenantId) {
	        return ResponseEntity.ok(service.listar(tenantId));
	    }
}
