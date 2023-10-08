package com.backengtest.demo.mapper;

import com.backengtest.demo.dto.CommentsDto;
import com.backengtest.demo.model.Comment;
import com.backengtest.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    Comment map(CommentsDto commentsDto, User user);

    @Mapping(target = "username", source = "comment.user.username")
    CommentsDto mapToDto(Comment comment);
}
