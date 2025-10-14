package com.example.Api_Postgresql.validation;

import com.example.Api_Postgresql.dto.request.ResponsibleRequestDTO;
import com.example.Api_Postgresql.exception.MultipleValidationException;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Responsible;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ResponsiblePatchValidation {

    public Responsible validator(ResponsibleRequestDTO updates, Responsible responsible, Company company) {
        Map<String, String> errors = new HashMap<>();

        if (StringUtils.isNotEmpty(updates.getFirstName())) {
            if (verifyName(updates.getFirstName(), errors)) {
                responsible.setFirstName(updates.getFirstName());
            }
        }

        if (StringUtils.isNotEmpty(updates.getLastName())) {
            if (verifyName(updates.getLastName(), errors)) {
                responsible.setLastName(updates.getLastName());
            }
        }

        if (StringUtils.isNotEmpty(updates.getEmail())) {
            if (verifyEmail(updates.getEmail(), errors)) {
                responsible.setEmail(updates.getEmail());
            }
        }

        if (updates.getCompanyId() != null) {
            responsible.setCompany(company);
        }

        if (!errors.isEmpty()) {
            throw new MultipleValidationException(errors);
        }

        return responsible;
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
            errors.put("email", "Email shouldn't have less than 5 characters!");return false;
        }
        return true;
    }

}
