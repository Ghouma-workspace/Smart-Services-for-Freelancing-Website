package com.backengtest.demo.mapper;
import com.backengtest.demo.dto.ProjectApplicationDto;
import com.backengtest.demo.model.ProjectApplication;
import com.backengtest.demo.model.ProjectDomain;
import com.backengtest.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectApplicationMapper {
    @Mapping(target = "projectDomainId", source = "projectapp.projectDomain.id")
    ProjectApplicationDto mapProjectToDto(ProjectApplication projectapp);

    @Mapping(target = "id", source = "projectDomain.id")
    @Mapping(target = "projectDomain", source = "projectDomain")
    @Mapping(target = "users", ignore = true)
    ProjectApplication mapProjectDtoToEntity(ProjectApplicationDto projectappDto, ProjectDomain projectDomain);
}
