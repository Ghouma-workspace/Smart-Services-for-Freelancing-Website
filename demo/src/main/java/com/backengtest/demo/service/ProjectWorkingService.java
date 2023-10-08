package com.backengtest.demo.service;
import com.backengtest.demo.dto.ProjectApplicationDto;
import com.backengtest.demo.dto.ProjectWorkingDto;
import com.backengtest.demo.exception.ProjectNotFoundException;
import com.backengtest.demo.mapper.ProjectWorkingMapper;
import com.backengtest.demo.model.*;
import com.backengtest.demo.repository.ProjectDomainRepo;
import com.backengtest.demo.repository.ProjectWorkingRepository;
import com.backengtest.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectWorkingService {
    private ProjectWorkingRepository projectWorkingRepository;
    private ProjectWorkingMapper projectWorkingMapper;
    private ProjectDomainRepo projectDomainRepo;
    private UserRepository userRepository;
    /*
    @Transactional
    //houni blaset el project dakhel projectApplication
    public ProjectWorkingDto createProject(Project project, User user) {
        ProjectWorking projectWorking = (ProjectWorking) project;
        projectWorking.setUser(user);
        projectWorking.setFinished(false);
        projectWorking.setProgress(0);
        projectWorking.setAccdate(Instant.now());
        ProjectWorking save = projectWorkingRepository.save(projectWorking);
        return projectWorkingMapper.mapProjectToDto(save);
    }

     */
    //Houni matensech bech tsala7a !!!!!!!!
    @Transactional
    public ProjectWorkingDto updateProject(Long id, ProjectWorkingDto projectDto) {
        ProjectDomain projectDomain = projectDomainRepo.findById(projectDto.getProjectDomainId())
                .orElseThrow(() -> new IllegalArgumentException("Project domain not found"));
        User user = userRepository.findByUsername(projectDto.getUserName())
                .orElseThrow(() -> new ProjectNotFoundException("user with id " + projectDto.getUserName()+ " not found"));
        ProjectWorking project = projectWorkingMapper.mapProjectDtoToEntity(projectDto, user, projectDomain);
        ProjectWorking existingProject = projectWorkingRepository.findById(project.getId())
                .orElseThrow(() -> new ProjectNotFoundException("Project with id " + project.getId() + " not found"));

        return projectWorkingMapper.mapProjectToDto(projectWorkingRepository.save(project));
    }

    @Transactional
    public void deleteProject(Long id) {
        projectWorkingRepository.deleteById(id);
    }

    @Transactional
    public ProjectWorkingDto getProjectById(Long id) {
        ProjectWorking project = projectWorkingRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id " + id + " not found"));
        return projectWorkingMapper.mapProjectToDto(project);
    }

    @Transactional
    public List<ProjectWorkingDto> getAllProjects() {
        return projectWorkingRepository.findAll()
                .stream()
                .map(projectWorkingMapper::mapProjectToDto)
                .collect(toList());
    }

    @Transactional
    public List<ProjectWorkingDto> getAllProjectsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return   projectWorkingRepository.findProjectWorkingsByUserId(user.getId())
                .stream()
                .map(projectWorkingMapper::mapProjectToDto)
                .collect(toList());
    }
}
