package com.backengtest.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="project_application")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectApplication extends Project {
    @Column(name = "hiring")
    private Boolean hiring;
    @ManyToMany
    @JoinTable(
            name = "project_user",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
