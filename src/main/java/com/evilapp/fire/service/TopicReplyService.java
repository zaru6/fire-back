package com.evilapp.fire.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evilapp.fire.model.Product;
import com.evilapp.fire.model.TopicReply;
import com.evilapp.fire.repository.TopicReplyRepository;

@Service
public class TopicReplyService {

    private final TopicReplyRepository topicReplyRepository;
    private final UserService userService;

    @Autowired
    public TopicReplyService(TopicReplyRepository topicReplyRepository, UserService userService) {
        this.topicReplyRepository = topicReplyRepository;
        this.userService = userService;
    }

    public List<TopicReply> getAllReplies() {
        return topicReplyRepository.findAll();
    }

    public List<TopicReply> getAllRepliesForTopic(Integer topicId) {
        List<TopicReply> replies = topicReplyRepository.findByTopicId(topicId);
        return replies;
    }

    public TopicReply saveTopicReply(TopicReply topicReply) {
        topicReply.setCreatedBy(userService.getAuthenticatedUser().getId());
        return topicReplyRepository.save(topicReply);
    }

}
