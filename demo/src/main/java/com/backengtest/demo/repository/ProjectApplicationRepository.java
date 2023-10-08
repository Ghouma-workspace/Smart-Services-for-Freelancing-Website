package com.backengtest.demo.repository;

import com.backengtest.demo.model.ProjectApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectApplicationRepository extends JpaRepository<ProjectApplication, Long> {
    @Query("SELECT p FROM ProjectApplication p JOIN p.users u WHERE u.id = :userId")
    List<ProjectApplication> findProjectsByUserId(@Param("userId") Long userId);
}
