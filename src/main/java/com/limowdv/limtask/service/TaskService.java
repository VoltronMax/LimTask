package com.limowdv.limtask.service;

import com.limowdv.limtask.model.dto.CreateTaskRequest;
import com.limowdv.limtask.model.dto.TaskResponse;
import com.limowdv.limtask.model.dto.UpdateTaskRequest;

import java.util.List;

public interface TaskService {

    TaskResponse crearTarea(CreateTaskRequest request);
    List<TaskResponse> consultarTareas();
    TaskResponse consultarTareaPorId(Long id);
    TaskResponse modificarTarea(Long id, UpdateTaskRequest request);
    void eliminarTarea(Long id);



}
