package com.evilapp.fire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evilapp.fire.model.TopicReply;

@Repository
public interface TopicReplyRepository extends JpaRepository<TopicReply, Long> {
        @Query("SELECT MAX(tr.replyOrder) FROM TopicReply tr WHERE tr.topicId = :topicId")
        Integer findMaxReplyOrderByTopicId(@Param("topicId") Integer topicId);

        List<TopicReply> findByTopicId(Integer topicId);
}