package com.example.Api_Postgresql.validation;

import com.example.Api_Postgresql.dto.request.GoalRequestDTO;
import com.example.Api_Postgresql.exception.MultipleValidationException;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Goal;
import com.example.Api_Postgresql.model.Program;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GoalPatchValidation {

    public Goal validator(GoalRequestDTO updates, Goal goal, Company company, Program program) {
        Map<String, String> errors = new HashMap<>();

        if (StringUtils.isNotEmpty(updates.getName())) {
            if (verifyName(updates.getName(), errors)) {
                goal.setName(updates.getName());
            }
        }

        if (StringUtils.isNotEmpty(updates.getDescription())) {
            goal.setDescription(updates.getDescription());
        }

        if (updates.getCompanyId() != null) {
            goal.setCompany(company);
        }

        if (updates.getProgramId() != null) {
            goal.setProgram(program);
        }

        if (!errors.isEmpty()) {
            throw new MultipleValidationException(errors);
        }

        return goal;

    }

    public boolean verifyName(String name, Map<String, String> errors) {
        if (name.length() < 2){
            errors.put("name", "The name should have a minimun of 2 characters");
            return false;
        }
        return true;
    }

}
