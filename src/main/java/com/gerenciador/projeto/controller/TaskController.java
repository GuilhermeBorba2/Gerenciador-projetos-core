package com.gerenciador.projeto.controller;

import com.gerenciador.projeto.model.Task;
import com.gerenciador.projeto.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask (@PathVariable Long id, @RequestBody Task taskDetails){
        Task updateTask =taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask (@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
