package com.limowdv.limtask.model.dto;

import com.limowdv.limtask.model.Priority;
import com.limowdv.limtask.model.Status;

import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String titulo,
        String descripcion,
        Priority prioridad,
        Status estado,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaLimite
) {
}
