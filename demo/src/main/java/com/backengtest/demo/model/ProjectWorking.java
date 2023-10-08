package com.backengtest.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name="project_working")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectWorking extends Project {
    @Column(name = "acceptation_date")
    @UpdateTimestamp
    private Instant accdate;
    @Column(name = "progress")
    private int progress;
    @Column(name = "finished")
    private Boolean finished;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    @JsonBackReference
    private User user;
}
