package com.limowdv.limtask.model.dto;

import com.limowdv.limtask.model.Priority;
import com.limowdv.limtask.model.Status;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record UpdateTaskRequest(
        @Size(min = 3, max = 100)
        String titulo,

        @Size(max = 500)
        String descripcion,

        Priority prioridad,

        Status estado,

        @FutureOrPresent
        LocalDateTime fechaLimite
) {
}
