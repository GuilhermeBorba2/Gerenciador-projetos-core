package com.gerenciador.projeto.model;

import com.gerenciador.projeto.model.enums.Status;
import jakarta.persistence.*;
import jakarta.servlet.http.PushBuilder;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

   @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate dueDate;

    private LocalDate creationDate;
    private LocalDate completionDate;


    @ManyToOne
    @JoinColumn(name = "user.id")
    private User user;

    public LocalDate getCreationDate() {
        return creationDate;
    }



    public boolean isOverdue(){
        return dueDate != null && LocalDate.now().isAfter(dueDate) && status !=Status.CONCLUIDA;
    }

    public void updateStatusBasedOnDates(){
        if(status ==Status.CONCLUIDA){
            return;
        }
        if(isOverdue()){
            setStatus(Status.PENDENTE);
        }
        else{
            setStatus(Status.EM_ANDAMENTO);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public Task(){

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

}
