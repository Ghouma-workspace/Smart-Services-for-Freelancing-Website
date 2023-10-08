package com.backengtest.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name="project")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "domain_id", nullable = true)
    @JsonBackReference
    private ProjectDomain projectDomain;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "payment")
    private double payment;
    @Column(name = "active")
    private boolean active;
    @Column(name = "date")
    @UpdateTimestamp
    private Date date;


    public boolean getActive() {
        return this.active;
    }

    public String getTitle() {
        return title;
    }

    public void setActive(boolean active){
        this.active = active;
    }
}
