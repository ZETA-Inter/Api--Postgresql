package com.example.Api_Postgresql.validation;

import com.example.Api_Postgresql.dto.request.ProgramRequestDTO;
import com.example.Api_Postgresql.exception.MultipleValidationException;
import com.example.Api_Postgresql.model.Program;
import com.example.Api_Postgresql.model.Segment;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ProgramPatchValidation {


    public Program validator(ProgramRequestDTO updates, Program program, Segment segment) {
        Map<String, String> errors = new HashMap<>();

        if (StringUtils.isNotEmpty(updates.getName())) {
            if (verifyName(updates.getName(), errors)) {
                program.setName(updates.getName());
            }
        }

        if (updates.getDescription() != null) {
            program.setDescription(updates.getDescription());
        }

        if (updates.getSegmentId() != null && updates.getSegmentId() > 0) {
            program.setSegment(segment);
        }

        if (!errors.isEmpty()) {
            throw new MultipleValidationException(errors);
        }

        return program;
    }

    public boolean verifyName(String name, Map<String, String> errors) {
        if (name.length() < 2){
            errors.put("name", "The name should have a minimun of 2 characters");
            return false;
        }
        return true;
    }

}
