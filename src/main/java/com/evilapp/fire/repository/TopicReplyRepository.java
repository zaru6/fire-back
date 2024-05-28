package com.evilapp.fire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evilapp.fire.model.TopicReply;

@Repository
public interface TopicReplyRepository extends JpaRepository<TopicReply, Long>{

}