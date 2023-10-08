package com.backengtest.demo.repository;

import com.backengtest.demo.model.ProjectWorking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectWorkingRepository extends JpaRepository<ProjectWorking, Long> {
    List<ProjectWorking> findProjectWorkingsByUserId(Long userId);
}
