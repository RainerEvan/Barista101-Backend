package com.project.barista101.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.model.forum.ForumComments;
import com.project.barista101.model.forum.Forums;
import com.project.barista101.payload.request.ForumCommentRequest;
import com.project.barista101.repository.AccountRepository;
import com.project.barista101.repository.ForumCommentRepository;
import com.project.barista101.repository.ForumRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ForumCommentService {
    
    @Autowired
    private final ForumCommentRepository forumCommentRepository;
    @Autowired
    private final ForumRepository forumRepository;
    @Autowired
    private final AccountRepository accountRepository;

    @Transactional
    public List<ForumComments> getAllCommentsForForum(UUID forumId){
        Forums forum = forumRepository.findById(forumId)
            .orElseThrow(() -> new IllegalStateException("Forum with current id cannot be found"));

        return forumCommentRepository.findAllByForumOrderByCreatedAtDesc(forum);
    }

    @Transactional
    public ForumComments addForumComment(ForumCommentRequest forumCommentRequest){
        Forums forum = forumRepository.findById(forumCommentRequest.getForumId())
            .orElseThrow(() -> new IllegalStateException("Forum with current id cannot be found"));

        Accounts account = accountRepository.findById(forumCommentRequest.getAccountId())
            .orElseThrow(() -> new IllegalStateException("Account with current id cannot be found"));

        ForumComments forumComment = new ForumComments();
        forumComment.setForum(forum);
        forumComment.setAuthor(account);
        forumComment.setBody(forumCommentRequest.getBody());
        forumComment.setCreatedAt(OffsetDateTime.now());

        return forumCommentRepository.save(forumComment);
    }

    @Transactional
    public void deleteForumComment(UUID forumCommentId){
        ForumComments forumComment = forumCommentRepository.findById(forumCommentId)
            .orElseThrow(() -> new IllegalStateException("Forum comment with current id cannot be found"));

        forumCommentRepository.delete(forumComment);
    }
}
