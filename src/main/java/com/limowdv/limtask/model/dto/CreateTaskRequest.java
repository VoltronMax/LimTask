package com.limowdv.limtask.model.dto;

import com.limowdv.limtask.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateTaskRequest(
        @NotBlank
        String titulo,

        @NotBlank
        String descripcion,

        @NotNull
        Priority prioridad,

        @NotNull
        LocalDateTime fechaLimite
        ) {
}
