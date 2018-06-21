package com.spring.first.demo.Services;

import com.spring.first.demo.models.Comment;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);
}
