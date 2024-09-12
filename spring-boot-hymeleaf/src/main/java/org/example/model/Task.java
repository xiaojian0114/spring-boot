package org.example.model;

public class Task {
    private Long id;
    private String name;

    private Boolean completed;

    public Task(Long id,String name){
        this.id = id;
        this.name = name;
        this.completed = false;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
