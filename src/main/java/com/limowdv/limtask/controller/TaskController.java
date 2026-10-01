package com.limowdv.limtask.controller;

import com.limowdv.limtask.model.dto.CreateTaskRequest;
import com.limowdv.limtask.model.dto.TaskResponse;
import com.limowdv.limtask.model.dto.UpdateTaskRequest;
import com.limowdv.limtask.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<TaskResponse> obtenerTareas(){
        return service.consultarTareas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse crearTarea(@RequestBody @Valid
                                       CreateTaskRequest request){
        return service.crearTarea(request);
    }

    @PatchMapping("/{id}")
    public TaskResponse actualizarTarea(@RequestBody @Valid
                                            UpdateTaskRequest request,
                                        @Positive @PathVariable Long id){
        return service.modificarTarea(id, request);
    }

    @GetMapping("/{id}")
    public TaskResponse obtenerTareaPorId(@Positive @PathVariable
                                              Long id){
        return service.consultarTareaPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTarea(@Positive @PathVariable
                                  Long id){
        service.eliminarTarea(id);
    }


}
