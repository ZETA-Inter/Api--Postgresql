package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.request.ResponsibleRequestDTO;
import com.example.Api_Postgresql.dto.response.ResponsibleResponseDTO;
import com.example.Api_Postgresql.exception.EntityAlreadyExists;
import com.example.Api_Postgresql.mapper.ResponsibleMapper;
import com.example.Api_Postgresql.model.Responsible;
import com.example.Api_Postgresql.repository.ResponsibleRepository;
import com.example.Api_Postgresql.validation.ResponsiblePatchValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ResponsibleService {

    private final ResponsibleRepository responsibleRepository;

    private final ResponsibleMapper responsibleMapper;

    private final ResponsiblePatchValidation validator;

    public List<ResponsibleResponseDTO> listAllResponsibles() {
        return responsibleRepository.findAll()
                .stream()
                .map(r -> responsibleMapper.toResponsibleResponseDTO(r))
                .toList();
    }

    public ResponsibleResponseDTO findResponsibleById(Integer id) {
        Responsible responsible = responsibleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Responsible not found"));
        return responsibleMapper.toResponsibleResponseDTO(responsible);
    }

    public ResponsibleResponseDTO findResponsibleByEmail(String email) {
        Responsible responsible = responsibleRepository.findByEmail(email);
        if (responsible == null) {
            throw new EntityNotFoundException("Responsible not found");
        }
        return responsibleMapper.toResponsibleResponseDTO(responsible);
    }

    public ResponsibleResponseDTO createResponsible(ResponsibleRequestDTO requestDTO) {
        if (responsibleRepository.findByEmail(requestDTO.getEmail()) != null) {
            throw new EntityAlreadyExists("Responsible already exists");
        }
        Responsible newResponsible = responsibleMapper.toResponsible(requestDTO);
        Responsible savedResponsible = responsibleRepository.save(newResponsible);
        return responsibleMapper.toResponsibleResponseDTO(savedResponsible);
    }

    public void deleteResponsible(Integer id) {
        if (!responsibleRepository.findById(id).isPresent()) {
            throw new EntityNotFoundException("Responsible not found");
        }
        responsibleRepository.deleteById(id);
    }

    public void updateResponsible(Integer id, ResponsibleRequestDTO requestDTO) {
        Responsible exists = responsibleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Responsible not found"));
        Responsible newResponsible = responsibleMapper.toResponsible(requestDTO);
        newResponsible.setId(exists.getId());
        responsibleRepository.save(newResponsible);
    }

    public void partiallyUpdateResponsible(Integer id, ResponsibleRequestDTO requestDTO) {
        Optional<Responsible> exists = responsibleRepository.findById(id);
        if (exists.isPresent()) {
            Responsible responsible = exists.get();

            Responsible newResponsible = validator.validator(requestDTO, responsible);

            responsibleRepository.save(newResponsible);
        } else {
            throw new EntityNotFoundException("Responsible not found");
        }
    }

}
