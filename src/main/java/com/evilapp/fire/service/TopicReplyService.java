package com.evilapp.fire.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evilapp.fire.model.TopicReply;
import com.evilapp.fire.repository.TopicReplyRepository;

@Service
public class TopicReplyService {

    private final TopicReplyRepository topicReplyRepository;

    @Autowired
    public TopicReplyService(TopicReplyRepository topicReplyRepository) {
        this.topicReplyRepository = topicReplyRepository;
    }

    public List<TopicReply> getAllReplies() {
        return topicReplyRepository.findAll();
    }

    public List<TopicReply> getAllRepliesForTopic(Integer topicId) {
        List<TopicReply> replies = topicReplyRepository.findByTopicId(topicId);
        return replies;
    }

}
