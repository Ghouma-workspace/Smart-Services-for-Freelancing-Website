package com.backengtest.demo.controller;

import com.backengtest.demo.dto.CommentsDto;
import com.backengtest.demo.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentsController {
    private final CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) {
        commentService.save(commentsDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping(params = "userName")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@RequestParam String userName){
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForUser(userName));
    }

    @GetMapping
    public ResponseEntity<List<CommentsDto>> getAllComments() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getAllComments());
    }


}
