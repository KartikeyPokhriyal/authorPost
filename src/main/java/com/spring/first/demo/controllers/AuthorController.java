package com.spring.first.demo.controllers;

import com.spring.first.demo.Services.AuthorRepository;
import com.spring.first.demo.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collection.*;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    public List<Author> retrieveAuthors() {
        List<Author> authors = new ArrayList<>();
        authorRepository.findAll().forEach(authors::add);
        return authors;
    }

    @GetMapping("/posts")
    public List<String> getAllPostsByAnAuthor(String name) {
        List<String> posts = new ArrayList<>();

        for (Author post : authorRepository.findByName(name)) {
            posts.add(post.getPost());
        }
        return posts;
    }

    @GetMapping("/votes")
    public List<Author> sortPostsByVotes(@RequestParam int limit) {
        List<Author> posts = new ArrayList<>();
        for (Author post:authorRepository.findAll()
                ) {
            posts.add(post);
        }

        posts.sort(Comparator.comparingDouble(Author::getVote));
        Collections.reverse(posts);
        return posts.stream().limit(limit).collect(Collectors.toList());
    }

    @GetMapping("/topAuthorPosts")
    public List<Author> retrieveTopPostsByAuthor(@RequestParam String name, int limit) {
        List<Author> posts = new ArrayList<>(authorRepository.findByName(name));
        posts.sort(Comparator.comparingDouble(Author::getVote));
        Collections.reverse(posts);

        return posts.stream().limit(limit).collect(Collectors.toList());
    }

}