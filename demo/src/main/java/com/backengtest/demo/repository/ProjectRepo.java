package com.backengtest.demo.repository;

import com.backengtest.demo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin()
@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    Optional<Project> findByTitle(String title);
    List<Project> findAllByTitleContaining(String title);
}
