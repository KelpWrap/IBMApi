package com.whiteboard.whiteboard.services;

import com.whiteboard.whiteboard.dbAdapter.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    
}
