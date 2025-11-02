package com.example.Api_Postgresql.swagger;

import com.example.Api_Postgresql.dto.request.GradeRequestDTO;
import com.example.Api_Postgresql.dto.request.ProgressRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Worker Program",
        description = "Gerencia as associações entre trabalhadores e programas, incluindo atualização de notas (grade) e adição de progresso."
)
public interface WorkerProgramControllerDocs {

    @Operation(
            summary = "Atualizar nota (grade) de um trabalhador em um programa",
            description = """
                      Atualiza a nota (grade) de um trabalhador para um programa específico.
                      É necessário informar o `workerId`, o `programId` e a nova nota (`grade`).
                      """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Nota atualizada com sucesso.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "Grade updated to 85 for worker_id=1 and program_id=5")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Associação entre trabalhador e programa não encontrada.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Requisição inválida (campos ausentes ou dados incorretos).",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PatchMapping("/grade")
    ResponseEntity<String> updateGrade(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Dados necessários para atualizar a nota de um trabalhador em um programa.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GradeRequestDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de requisição",
                                    value = """
                            {
                              "workerId": 1,
                              "programId": 5,
                              "grade": 85
                            }
                            """
                            )
                    )
            )
            @RequestBody GradeRequestDTO request
    );

    @Operation(
            summary = "Adicionar progresso a um trabalhador em um programa",
            description = """
                      Cria um novo registro de progresso para um trabalhador em um programa específico.
                      É necessário informar o `workerId`, o `programId` e a porcentagem de progresso (`percentage`).
                      """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Progresso adicionado com sucesso.",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "Progress added successfully for worker_id=1 and program_id=5")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Associação entre trabalhador e programa não encontrada.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Requisição inválida (campos ausentes ou dados incorretos).",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PostMapping("/add-progress")
    ResponseEntity<String> addProgress(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Dados necessários para adicionar progresso de um trabalhador em um programa.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProgressRequestDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de requisição",
                                    value = """
                            {
                              "workerId": 1,
                              "programId": 5,
                              "percentage": 50
                            }
                            """
                            )
                    )
            )
            @RequestBody ProgressRequestDTO request
    );
}