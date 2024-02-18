package com.gerenciador.projeto.controller;

import com.gerenciador.projeto.model.DTO.TaskDto;
import com.gerenciador.projeto.model.Task;
import com.gerenciador.projeto.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:9000")
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.findAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskByID(@PathVariable Long id){
        Task task = taskService.findTaskById(id)
                .orElseThrow(()-> new RuntimeException("Task not found for this id :: " + id));
        return ResponseEntity.ok().body(task);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?>  createTask(@Valid @RequestBody TaskDto taskDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Task task = taskService.saveTask(taskDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(location).body(task);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask (@PathVariable Long id, @RequestBody TaskDto taskDto){
        Task updateTask = taskService.updateTask(id, taskDto);
        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask (@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
