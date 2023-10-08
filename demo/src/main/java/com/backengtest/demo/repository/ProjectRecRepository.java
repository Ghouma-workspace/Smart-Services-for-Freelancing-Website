package com.backengtest.demo.repository;

import com.backengtest.demo.model.ProjectRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin()
@Repository
public interface ProjectRecRepository extends JpaRepository<ProjectRec, Long> {
    List<ProjectRec> findByTitle(String title);
}
