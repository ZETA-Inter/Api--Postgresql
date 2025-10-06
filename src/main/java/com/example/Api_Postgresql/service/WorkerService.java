package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.request.WorkerRequestDTO;
import com.example.Api_Postgresql.dto.response.WorkerProgressResponse;
import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.exception.BadCredentialsException;
import com.example.Api_Postgresql.exception.EntityAlreadyExists;
import com.example.Api_Postgresql.mapper.WorkerMapper;
import com.example.Api_Postgresql.model.Worker;
import com.example.Api_Postgresql.repository.WorkerRepository;
import com.example.Api_Postgresql.validation.WorkerPatchValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkerService {

    private final WorkerRepository workerRepository;

    private final WorkerMapper workerMapper;

    private final WorkerPatchValidation validation;

    private final ImageService imageService;

    public List<WorkerResponseDTO> list() {
        return workerRepository.findAll()
                .stream()
                .map(workerMapper::convertWorkerToWorkerResponse)
                .toList();
    }

    public List<WorkerResponseDTO> listWorkersByCompanyId(Integer companyId) {
        return workerRepository.findAllByCompany_Id(companyId)
                .stream()
                .map(workerMapper::convertWorkerToWorkerResponse)
                .toList();
    }

    public WorkerResponseDTO findById(Integer id) {
        Worker exists = workerRepository.findById(id).get();
        if (exists == null) {
            throw new EntityNotFoundException("Worker not found!");
        }
        return workerMapper.convertWorkerToWorkerResponse(exists);
    }

    public WorkerResponseDTO findByEmail(String email) {
        Worker exists = workerRepository.findByEmail(email);
        if (exists == null) {
            throw new EntityNotFoundException("Worker not found!");
        }
        return workerMapper.convertWorkerToWorkerResponse(exists);
    }

    public WorkerResponseDTO login(String email, String password) {
        Worker exists = workerRepository.findByEmail(email);
        if (exists == null) {
            throw new EntityNotFoundException("Email is incorrect!");
        }

        if (!password.equals(exists.getPassword())) {
            throw new BadCredentialsException("Password is incorrect!");
        }
        return workerMapper.convertWorkerToWorkerResponse(exists);
    }

    public WorkerResponseDTO createWorker(WorkerRequestDTO request) {
        if (workerRepository.findByEmail(request.getEmail()) != null) {
            throw new EntityAlreadyExists("Company already exist!");
        }
        Worker worker = workerMapper.convertWorkerRequestToWorker(request);
        workerRepository.save(worker);

        imageService.createImage("workers", request.getImageUrl(), worker.getId());

        return workerMapper.convertWorkerToWorkerResponse(worker);
    }

    public void deleteWorker(Integer id) {
        Worker worker = workerRepository.findById(id).get();
        if (worker == null) {
            throw new EntityNotFoundException("Worker with ID '"+id+"' don't exist!");
        }
        workerRepository.delete(worker);
    }

    public void updateWorker(Integer id, WorkerRequestDTO request) {
        Worker workerExists = workerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Worker with ID \"" + id + "\" not found"));
        Worker worker = workerMapper.convertWorkerRequestToWorker(request);
        worker.setId(id);
        workerRepository.save(worker);
    }

    public void partiallyUpdateWorker(Integer id, WorkerRequestDTO request) {
        Optional<Worker> workerExists = workerRepository.findById(id);
        if (workerExists.isPresent()) {
            Worker worker = workerExists.get();

            Worker workerFinal = validation.validator(request, worker);

            workerRepository.save(workerFinal);
        } else {
            throw new EntityNotFoundException("Worker with ID '"+id+"' not found!");
        }
    }

    public WorkerProgressResponse getMostRecentProgress(Integer workerId) {
        return workerRepository.getMostRecentProgress(workerId);
    }

    public WorkerProgressResponse getProgramProgress(Integer workerId, Integer programId) {
        return workerRepository.getProgramProgress(workerId, programId);
    }

}
