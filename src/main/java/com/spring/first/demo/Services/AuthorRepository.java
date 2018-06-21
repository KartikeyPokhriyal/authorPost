package com.spring.first.demo.Services;

import com.spring.first.demo.models.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, String> {

    List<Author> findByName(String name);
}
