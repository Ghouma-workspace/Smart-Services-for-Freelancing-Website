package com.backengtest.demo.repository;

import com.backengtest.demo.model.Project;
import com.backengtest.demo.model.Comment;
import com.backengtest.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUser(User user);
}
