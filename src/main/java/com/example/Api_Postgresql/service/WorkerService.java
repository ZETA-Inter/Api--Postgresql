package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.request.PaymentRequest;
import com.example.Api_Postgresql.dto.request.WorkerRequestDTO;
import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
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

    private final PaymentService paymentService;

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

    public WorkerResponseDTO createWorker(WorkerRequestDTO request) {
        if (workerRepository.findByEmail(request.getEmail()) != null) {
            throw new EntityAlreadyExists("Company already exist!");
        }

        Worker worker = workerMapper.convertWorkerRequestToWorker(request);
        workerRepository.save(worker);

        if (request.getImageUrl() != null) {
            imageService.createImage("workers", request.getImageUrl(), worker.getId());
        }

        paymentService.createPayment(new PaymentRequest("worker", worker.getId(), request.getPlanInfo()));

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

}
