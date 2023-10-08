package com.backengtest.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "age")
    private int age;
    @Column(name = "gender")
    private String gender;
    @Column(name = "avgVote")
    private double avgVote = 0;
    @Enumerated(EnumType.STRING)
    @Column(name = "userRole")
    private UserRole userRole;
    @Column(name = "created")
    private Instant created;
    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany(mappedBy = "users")
    @JsonManagedReference
    private List<ProjectApplication> projectsAppliedTo = new ArrayList<>();
    @OneToMany(mappedBy = "user",orphanRemoval = true)
    @JsonManagedReference
    private List<ProjectWorking> projectsWorkingOn = new ArrayList<>();
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @JsonManagedReference
    private List<ProjectWorking> projectFinished = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();
    public boolean isEnabled() {
        return enabled;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
