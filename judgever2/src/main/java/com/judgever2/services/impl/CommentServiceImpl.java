package com.judgever2.services.impl;

import com.judgever2.models.entities.Comment;
import com.judgever2.models.serviceModels.CommentServiceModel;
import com.judgever2.repositories.CommentRepository;
import com.judgever2.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentServiceModel addComment(CommentServiceModel commentServiceModel) {
        Comment comment = this.modelMapper.map(commentServiceModel, Comment.class);
        return this.modelMapper.map(this.commentRepository.save(comment), CommentServiceModel.class);
    }
}
