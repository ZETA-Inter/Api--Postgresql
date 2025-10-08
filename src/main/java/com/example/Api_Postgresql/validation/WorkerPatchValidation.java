package com.example.Api_Postgresql.validation;

import com.example.Api_Postgresql.dto.request.WorkerRequestDTO;
import com.example.Api_Postgresql.exception.MultipleValidationException;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Worker;
import com.example.Api_Postgresql.repository.CompanyRepository;
import com.example.Api_Postgresql.repository.PlanRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class WorkerPatchValidation {

    private final PlanRepository planRepository;

    private final CompanyRepository companyRepository;

    public Worker validator(WorkerRequestDTO updates, Worker worker) {
        Map<String, String> errors = new HashMap<>();

        if (StringUtils.isNotEmpty(updates.getName())) {
            if (verifyName(updates.getName(), errors)) {
                worker.setName(updates.getName());
            }
        }

        if (StringUtils.isNotEmpty(updates.getEmail())) {
            if (verifyEmail(updates.getEmail(), errors)) {
                worker.setEmail(updates.getEmail());
            }
        }

        if (updates.getBirthDate() != null) {
            if (verifyBirthDate(updates.getBirthDate(), errors)) {
                worker.setBirthDate(updates.getBirthDate());
            }
        }

        if (updates.getCompanyId() != null) {
            Company company = companyRepository.findById(updates.getCompanyId()).get(); // talvez poderia retornar uma exceção de company não encontrado
            worker.setCompany(company);
        }

        if (!errors.isEmpty()) {
            throw new MultipleValidationException(errors);
        }

        return worker;

    }

    public boolean verifyName(String name, Map<String, String> errors) {
        if (name.length() < 2){
            errors.put("name", "The name should have a minimun of 2 characters");
            return false;
        }
        return true;
    }

    public boolean verifyEmail(String email, Map<String, String> errors) {
        if (email.length() < 5){
            errors.put("email", "Email shouldn't have less than 5 characters!");
            return false;
        }
        return true;
    }

    public boolean verifyPassword(String password, Map<String, String> errors) {
        if (password.length() < 8){
            errors.put("password", "Password shouldn't have have less than 8 characters!");
            return false;
        }
        return true;
    }

    public boolean verifyBirthDate(LocalDate birthDate, Map<String, String> errors) {
        if (birthDate.isAfter(LocalDate.now())){
            errors.put("birthDate", "The birthDate should be before now!");
            return false;
        }
        return true;
    }

}
