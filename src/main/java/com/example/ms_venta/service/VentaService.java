package com.example.ms_venta.service;

import com.example.ms_venta.dto.VentaRequestDTO;
import com.example.ms_venta.dto.VentaResponseDTO;
import com.example.ms_venta.model.Venta;
import com.example.ms_venta.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService {
    @Autowired
    VentaRepository ventaRepository;

    private Venta getProductOrTrow(Long id){
        return ventaRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus
                        .NOT_FOUND,("Venta no encontrada")));
    }

    public VentaResponseDTO crearVenta(VentaRequestDTO request){
        Venta varVenta = Venta.builder()
                .fechaArriendo(request.getFechaArriendo())
                .build();
        Venta varVenta2 = ventaRepository.save(varVenta);
        return VentaResponseDTO.builder()
                .id(varVenta2.getId())
                .fechaArriendo(varVenta2.getFechaArriendo())
                .build();
    }

    public List<VentaResponseDTO> listarVentas() {
        List<Venta> listaVentas = ventaRepository.findAll();
        List<VentaResponseDTO> listaResponse = new ArrayList<>();

        for (Venta varVenta : listaVentas) {
            VentaResponseDTO responseDTO = VentaResponseDTO.builder()
                    .id(varVenta.getId())
                    .fechaArriendo(varVenta.getFechaArriendo())
                    .build();
            listaResponse.add(responseDTO);

        }
        return listaResponse;
    }

    public VentaResponseDTO listarVentaPorId(Long id){
        Venta varVenta = getProductOrTrow(id);
        return VentaResponseDTO.builder()
                .id(varVenta.getId())
                .fechaArriendo(varVenta.getFechaArriendo())
                .build();
    }

    public void eliminarVenta(Long id){
        Venta varVenta = getProductOrTrow(id);
    }



}
