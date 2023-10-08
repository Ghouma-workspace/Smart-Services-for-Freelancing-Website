package com.backengtest.demo.service;

import com.backengtest.demo.dto.ProjectApplicationDto;
import com.backengtest.demo.dto.UserDto;
import com.backengtest.demo.exception.ProjectNotFoundException;
import com.backengtest.demo.exception.SpringFreelanciniException;
import com.backengtest.demo.mapper.ProjectApplicationMapper;
import com.backengtest.demo.model.Project;
import com.backengtest.demo.model.ProjectApplication;
import com.backengtest.demo.model.ProjectDomain;
import com.backengtest.demo.model.User;
import com.backengtest.demo.repository.ProjectApplicationRepository;
import com.backengtest.demo.repository.ProjectDomainRepo;
import com.backengtest.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectApplicationService {
    private final ProjectApplicationRepository projectRepo;
    private final ProjectDomainRepo projectDomainRepo;
    private final UserRepository userRepository;
    private final ProjectApplicationMapper projectApplicationMapper;

    @Transactional
    public ProjectApplicationDto createProject(Project project) {
        ProjectApplication projectApplication = (ProjectApplication) project;
        projectApplication.setHiring(true);
        ProjectApplication save = projectRepo.save(projectApplication);
        return projectApplicationMapper.mapProjectToDto(save);
    }

    public ProjectApplicationDto updateProject(Long id,ProjectApplicationDto projectDto) {
        ProjectDomain projectDomain = projectDomainRepo.findById(projectDto.getProjectDomainId())
                .orElseThrow(() -> new IllegalArgumentException("Project domain not found"));
        ProjectApplication project = projectRepo.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id "+ id + " not found"));
        project.setProjectDomain(projectDomain);
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        project.setPayment(projectDto.getPayment());
        project.setActive(projectDto.getActive());
        project.setDate(projectDto.getDate());
        project.setHiring(projectDto.getHiring());
        return projectApplicationMapper.mapProjectToDto(projectRepo.save(project));
    }

    @Transactional
    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }

    @Transactional
    public ProjectApplicationDto getProjectById(Long id) {
        ProjectApplication project = projectRepo.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with id " + id + " not found"));
        return projectApplicationMapper.mapProjectToDto(project);
    }

    @Transactional
    public List<ProjectApplicationDto> getAllProjects() {
        return   projectRepo.findAll()
                .stream()
                .map(projectApplicationMapper::mapProjectToDto)
                .collect(toList());
    }

    @Transactional
    public ProjectApplicationDto postulate(ProjectApplicationDto projectDto, String username){
        ProjectApplication project = projectRepo.findById(projectDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringFreelanciniException("User Not Found"));
        List<ProjectApplication> pa = user.getProjectsAppliedTo();
        List<User> ur = project.getUsers();
        if (pa == null){
            pa = new ArrayList<>();
        }
        if(ur == null){
            ur = new ArrayList<>();
        }
        else if (!ur.contains(user)) {
            pa.add(project);
            ur.add(user);
        }
        else
            System.out.println("-----------------MAWJOUUUUUUUUUUUUUUUUUUUUD-----------------");
        project.setUsers(ur);
        user.setProjectsAppliedTo(pa);
        userRepository.save(user);
        return projectApplicationMapper.mapProjectToDto(projectRepo.save(project));
    }

    @Transactional
    public ProjectApplicationDto cancelPostulation(ProjectApplicationDto projectDto, String username){
        ProjectApplication project = projectRepo.findById(projectDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringFreelanciniException("User Not Found"));
        List<ProjectApplication> pa = user.getProjectsAppliedTo();
        List<User> ur = project.getUsers();
        if (ur.contains(user)) {
            pa.remove(project);
            ur.remove(user);
        }
        else
            System.out.println("-----------------MOUUUUUUUCH MAWJOUUUUUUUUUUUUUUUUUUUUD-----------------");
        project.setUsers(ur);
        user.setProjectsAppliedTo(pa);
        userRepository.save(user);
        return projectApplicationMapper.mapProjectToDto(projectRepo.save(project));
    }

    @Transactional
    public List<ProjectApplicationDto> getAllProjectsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return   projectRepo.findProjectsByUserId(user.getId())
                .stream()
                .map(projectApplicationMapper::mapProjectToDto)
                .collect(toList());
    }

    public Boolean isPostulated(Long id, String username) {
        ProjectApplication project = projectRepo.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project Not found"));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user.getProjectsAppliedTo().contains(project)){
            return true;
        }
        else
            return false;
    }
}
