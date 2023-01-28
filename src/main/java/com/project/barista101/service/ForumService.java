package com.project.barista101.service;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.model.forum.Forums;
import com.project.barista101.payload.request.ForumRequest;
import com.project.barista101.repository.AccountRepository;
import com.project.barista101.repository.ForumRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ForumService {

    @Autowired
    private final ForumRepository forumRepository;
    @Autowired
    private final AccountRepository accountRepository;

    @Transactional
    public List<Forums> getAllForums(){
        return forumRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public Forums getForum(UUID forumId){
        return forumRepository.findById(forumId)
            .orElseThrow(() -> new IllegalStateException("Forum with current id cannot be found"));
    }
    
    @Transactional
    public List<Forums> getAllForumsForAccount(UUID accountId){
        Accounts account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalStateException("Account with current id cannot be found"));

        return forumRepository.findAllByAuthorOrderByCreatedAtDesc(account);
    }

    @Transactional
    public Forums addForum(MultipartFile file, ForumRequest forumRequest){
        Accounts account = accountRepository.findById(forumRequest.getAccountId())
            .orElseThrow(() -> new IllegalStateException("Account with current id cannot be found"));

        Forums forum = new Forums();
        forum.setAuthor(account);
        forum.setTitle(forumRequest.getTitle());
        forum.setBody(forumRequest.getBody());
        forum.setCreatedAt(OffsetDateTime.now());

        if(file != null){
            forum.setThumbnail(addImage(file));
        }

        return forumRepository.save(forum);
    }

    @Transactional
    public void deleteForum(UUID forumId){
        Forums forum = forumRepository.findById(forumId)
            .orElseThrow(() -> new IllegalStateException("Forum with current id cannot be found"));

        forumRepository.delete(forum);
    }

    public String addImage(MultipartFile file){
        try{
            byte[] image = new byte[0];
            
            image = file.getBytes();

            String encodedString = Base64.getEncoder().encodeToString(image);

            return encodedString;
            
        } catch (IOException exception){
            throw new IllegalStateException("Could not add the current file", exception);
        }
    }

    public byte[] getThumbnail(UUID forumId){

        Forums forum = getForum(forumId);

        byte[] decodedBytes = Base64.getDecoder().decode(forum.getThumbnail());

        return decodedBytes;
    }
}
