package com.project.barista101.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.barista101.model.forum.ForumComments;
import com.project.barista101.model.forum.Forums;

@Repository
public interface ForumCommentRepository extends JpaRepository<ForumComments,UUID>{
    List<ForumComments> findAllByForumOrderByCreatedAtDesc(Forums forum);
}
