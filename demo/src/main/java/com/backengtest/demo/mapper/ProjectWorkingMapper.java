package com.backengtest.demo.mapper;
import com.backengtest.demo.dto.ProjectWorkingDto;
import com.backengtest.demo.model.Project;
import com.backengtest.demo.model.ProjectDomain;
import com.backengtest.demo.model.ProjectWorking;
import com.backengtest.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectWorkingMapper {

    @Mapping(target = "userName", source = "project.user.username")
    @Mapping(target = "projectDomainId", source = "project.projectDomain.id")
    ProjectWorkingDto mapProjectToDto(ProjectWorking project);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "projectDomain", source = "projectDomain")
    ProjectWorking mapProjectDtoToEntity(ProjectWorkingDto projectDto, User user, ProjectDomain projectDomain);
}
