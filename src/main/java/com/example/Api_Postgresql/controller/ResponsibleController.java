package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.ResponsibleRequestDTO;
import com.example.Api_Postgresql.dto.ResponsibleResponseDTO;
import com.example.Api_Postgresql.service.ResponsibleService;
import com.example.Api_Postgresql.validation.OnCreate;
import com.example.Api_Postgresql.validation.OnPatch;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/responsibles")
public class ResponsibleController {

    private final ResponsibleService responsibleService;

    @GetMapping("/list")
    public ResponseEntity<List<ResponsibleResponseDTO>> listAllResponsibles() {
        return ResponseEntity.ok().body(responsibleService.listAllResponsibles());
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<ResponsibleResponseDTO> findResponsibleById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(responsibleService.findResponsibleById(id));
    }

    @GetMapping("/find-email/{email}")
    public ResponseEntity<ResponsibleResponseDTO> findResponsibleByEmail(String email) {
        return ResponseEntity.ok().body(responsibleService.findResponsibleByEmail(email));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponsibleResponseDTO> createResponsible(@Validated({OnCreate.class, Default.class}) @RequestBody ResponsibleRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(responsibleService.createResponsible(requestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteResponsible(@PathVariable Integer id) {
        responsibleService.deleteResponsible(id);
        return ResponseEntity.ok().body("Responsible with ID "+id+" updated sucessfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateResponsible(@PathVariable Integer id, @Validated({OnCreate.class, Default.class}) @RequestBody ResponsibleRequestDTO requestDTO) {
        responsibleService.updateResponsible(id, requestDTO);
        return ResponseEntity.ok().body("Responsible with ID "+id+" updated successfully!");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> partiallyUpdateResponsible(@PathVariable Integer id, @Validated({OnPatch.class, Default.class}) @RequestBody ResponsibleRequestDTO requestDTO) {
        responsibleService.partiallyUpdateResponsible(id, requestDTO);
        return ResponseEntity.ok().body("Responsible with ID "+id+" partially updated successfully!");
    }

}
