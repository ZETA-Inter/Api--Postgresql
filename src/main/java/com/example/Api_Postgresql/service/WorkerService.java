package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.request.PaymentRequestDTO;
import com.example.Api_Postgresql.dto.request.WorkerRequestDTO;
import com.example.Api_Postgresql.dto.response.*;
import com.example.Api_Postgresql.exception.EntityAlreadyExists;
import com.example.Api_Postgresql.mapper.ImageMapper;
import com.example.Api_Postgresql.mapper.SegmentMapper;
import com.example.Api_Postgresql.mapper.WorkerMapper;
import com.example.Api_Postgresql.model.*;
import com.example.Api_Postgresql.repository.CompanyRepository;
import com.example.Api_Postgresql.repository.ProgramRepository;
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

    private final ImageMapper imageMapper;

    private final PaymentService paymentService;

    private final PlanService planService;

    private final WorkerProgramService workerProgramService;

    private final ProgramRepository programRepository;

    private final CompanyRepository companyRepository;

    private final SegmentMapper segmentMapper;

    private final WorkerGoalService workerGoalService;

    public List<WorkerResponseDTO> list() {
        return workerRepository.findAll()
                .stream()
                .map(w -> {
                    ImageResponseDTO image = imageService.getImageById("workers", w.getId());
                    String planName = planService.getPlanNameByWorkerId(w.getId());
                    return workerMapper.convertWorkerToWorkerResponse(w, image, planName);
                }).toList();
    }

    public List<WorkerResponseDTO> listWorkersByCompanyId(Integer companyId) {
        return workerRepository.findAllByCompany_Id(companyId)
                .stream()
                .map(w -> {
                    ImageResponseDTO image = imageService.getImageById("workers", w.getId());
                    String planName = planService.getPlanNameByWorkerId(w.getId());
                    return workerMapper.convertWorkerToWorkerResponse(w, image, planName);
                }).toList();
    }

    public List<WorkerResponseDTO> listActiveWorkersByCompanyId(Integer companyId) {
        return workerRepository.findAllByCompany_Id(companyId)
                .stream()
                .filter(Worker::isActive)
                .map(w -> {
                    ImageResponseDTO image = imageService.getImageById("workers", w.getId());
                    String planName = planService.getPlanNameByWorkerId(w.getId());
                    return workerMapper.convertWorkerToWorkerResponse(w, image, planName);
                }).toList();
    }

    public WorkerResponseDTO findById(Integer id) {
        Worker exists = workerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Worker not found!"));

        ImageResponseDTO image = imageService.getImageById("workers", exists.getId());
        String planName = planService.getPlanNameByWorkerId(id);
        return workerMapper.convertWorkerToWorkerResponse(exists, image, planName);
    }

    public WorkerResponseDTO findByEmail(String email) {
        Worker exists = workerRepository.findByEmail(email);
        if (exists == null) {
            throw new EntityNotFoundException("Worker not found!");
        }

        ImageResponseDTO image = imageService.getImageById("workers", exists.getId());

        String planName = planService.getPlanNameByWorkerId(exists.getId());

        return workerMapper.convertWorkerToWorkerResponse(exists, image, planName);
    }

    public List<ProgramWorkerResponseDTO> listActualProgramsById(Integer workerId) {
        List<WorkerProgramResponseDTO> wps = workerProgramService.listWorkerPrograms(workerId);
        return wps.stream()
                .filter(wp -> {
                    Progress latest = wp.getProgresses().getFirst();
                    return latest != null && latest.getProgressPercentage() < 100;
                })
                .map(wp -> {
                    Progress latest = wp.getProgresses().getFirst();
                    ProgramWorkerResponseDTO dto = new ProgramWorkerResponseDTO();
                    dto.setId(wp.getProgram().getId());
                    dto.setName(wp.getProgram().getName());
                    dto.setDescription(wp.getProgram().getDescription());
                    dto.setSegment(segmentMapper.toSegmentResponseDTO(wp.getProgram().getSegment()));
                    dto.setProgressPercentage(latest.getProgressPercentage());
                    return dto;
                })
                .toList();
    }

    public WorkerResponseDTO createWorker(WorkerRequestDTO request) {
        if (workerRepository.findByEmail(request.getEmail()) != null) {
            throw new EntityAlreadyExists("Worker already exist!");
        }

        Company company = null;
        if (request.getCompanyId() != null) {
            company = companyRepository.findById(request.getCompanyId())
                    .orElseThrow(() -> new EntityNotFoundException("Company with ID '"+request.getCompanyId()+"' don't exist!"));
        }

        Worker worker = workerMapper.convertWorkerRequestToWorker(request, company);
        workerRepository.save(worker);

        ImageResponseDTO imageResponse = null;
        if (request.getImageUrl() != null) {
            Image image = imageService.createImage("workers", request.getImageUrl(), worker.getId());
            imageResponse = imageMapper.convertToImageResponse(image);
        }

        String planName = null;
        if (request.getCompanyId() == null) {
            paymentService.createPayment(new PaymentRequestDTO("worker", worker.getId(), request.getPlanInfo()));

            planName = planService.getPlanNameByWorkerId(worker.getId());
        }

        return workerMapper.convertWorkerToWorkerResponse(worker, imageResponse, planName);
    }

    public void inactiveWorker(Integer id) {
        Optional<Worker> workerExists = workerRepository.findById(id);
        if (workerExists.isEmpty()) {
            throw new EntityNotFoundException("Worker with ID '"+id+"' don't exist!");
        }
        Worker worker = workerExists.get();
        worker.setActive(false);
        workerRepository.save(worker);
    }

    public void updateWorker(Integer id, WorkerRequestDTO request) {
        Worker workerExists = workerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Worker with ID \"" + id + "\" not found"));

        Company company = workerExists.getCompany();
        if (request.getCompanyId() != null) {
            company = companyRepository.findById(request.getCompanyId())
                    .orElseThrow(() -> new EntityNotFoundException("Company with ID '"+request.getCompanyId()+"' don't exist!"));
        }

        Worker worker = workerMapper.convertWorkerRequestToWorker(request, company);
        worker.setId(id);
        workerRepository.save(worker);
    }

    public void partiallyUpdateWorker(Integer id, WorkerRequestDTO request) {
        Optional<Worker> workerExists = workerRepository.findById(id);
        if (workerExists.isPresent()) {
            Worker worker = workerExists.get();

            Company company = worker.getCompany();
            if (request.getCompanyId() != null) {
                company = companyRepository.findById(request.getCompanyId())
                        .orElseThrow(() -> new EntityNotFoundException("Company with ID '"+request.getCompanyId()+"' don't exist!"));
            }

            Worker workerFinal = validation.validator(request, worker, company);

            workerRepository.save(workerFinal);
        } else {
            throw new EntityNotFoundException("Worker with ID '"+id+"' not found!");
        }
    }

    public WorkerProgressResponse getMostRecentProgress(Integer workerId) {
        return workerRepository.getMostRecentProgress(workerId);
    }

    public int getOverallProgress(Integer workerId) {
        List<WorkerGoal> workerGoals = workerGoalService.listWorkerGoalsByWorkerId(workerId);

        int length = workerGoals.size();

        int completedGoals = countCompletedGoals(workerGoals);

        return Math.round((float) completedGoals / length);
    }

    public WorkerProgressResponse getProgramProgress(Integer workerId, Integer programId) {
        return workerRepository.getProgramProgress(workerId, programId);
    }

    public void assignProgramToWorker(Integer workerId, Integer programId) {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new EntityNotFoundException("Worker with ID '" + workerId + "' not found"));

        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new EntityNotFoundException("Program with ID '" + programId + "' not found"));

        workerProgramService.assignProgramToWorker(worker, program);
    }

    public int countCompletedGoals(List<WorkerGoal> workerGoals) {
        return (int) workerGoals.stream()
                .filter(WorkerGoal::isCompleted)
                .count();
    }

}
