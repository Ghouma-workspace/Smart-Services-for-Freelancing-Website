package com.backengtest.demo.mapper;
import com.backengtest.demo.dto.ProjectDto;
import com.backengtest.demo.model.Project;
import com.backengtest.demo.model.ProjectDomain;
import com.backengtest.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(target = "projectDomainId", source = "project.projectDomain.id")
    ProjectDto mapProjectToDto(Project project);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "projectDomain", source = "projectDomain")
    Project mapProjectDtoToEntity(ProjectDto projectDto, ProjectDomain projectDomain);
}
