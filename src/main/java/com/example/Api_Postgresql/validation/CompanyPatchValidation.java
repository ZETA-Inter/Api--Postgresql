package com.example.Api_Postgresql.validation;

import com.example.Api_Postgresql.dto.CompanyRequestDTO;
import com.example.Api_Postgresql.exception.MultipleValidationException;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Plan;
import com.example.Api_Postgresql.model.Responsible;
import com.example.Api_Postgresql.repository.PlanRepository;
import com.example.Api_Postgresql.repository.ResponsibleRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class CompanyPatchValidation {

    private final PlanRepository planRepository;

    private final ResponsibleRepository responsibleRepository;

    public Company validator(CompanyRequestDTO updates, Company company) {
        Map<String, String> errors = new HashMap<>();

        if (StringUtils.isNotEmpty(updates.getName())) {
            if (verifyName(updates.getName(), errors)) {
                company.setName(updates.getName());
            }
        }

        if (StringUtils.isNotEmpty(updates.getEmail())) {
            if (verifyEmail(updates.getEmail(), errors)) {
                company.setEmail(updates.getEmail());
            }
        }

        if (StringUtils.isNotEmpty(updates.getPassword())) {
            if (verifyPassword(updates.getPassword(), errors)) {
                company.setPassword(updates.getPassword());
            }
        }

        if (updates.getPlanId() != null) {
            Plan plan = planRepository.findById(updates.getPlanId()).get(); // talvez poderia retornar uma exceção de plano não encontrado
            company.setPlan(plan);
        }

        if (!errors.isEmpty()) {
            throw new MultipleValidationException(errors);
        }

        return company;

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

}
