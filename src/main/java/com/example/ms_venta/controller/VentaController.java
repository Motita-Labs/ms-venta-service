package com.example.ms_venta.controller;

import com.example.ms_venta.dto.VentaRequestDTO;
import com.example.ms_venta.dto.VentaResponseDTO;
import com.example.ms_venta.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/venta")
public class VentaController {
    @Autowired
    VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaResponseDTO> crearVenta(@Valid @RequestBody VentaRequestDTO request){
        return ResponseEntity.status(CREATED).body(ventaService.crearVenta(request));
    }

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> listarVentas(){
        return ResponseEntity.ok(ventaService.listarVentas());
    }
    @GetMapping ("/{id}")
    public ResponseEntity<VentaResponseDTO> listarVentaPorId(@PathVariable Long id){
        return ResponseEntity.ok(ventaService.listarVentaPorId(id));
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> eliminarVentaPorId(@PathVariable Long id){
        return ResponseEntity.noContent().build();
    }

}
