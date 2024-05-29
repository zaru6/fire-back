package com.evilapp.fire.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.evilapp.fire.model.Topic;
import com.evilapp.fire.model.TopicReply;
import com.evilapp.fire.service.TopicReplyService;
import com.evilapp.fire.service.TopicService;

@RestController
@RequestMapping("/api/discussion")
public class DiscussionController {

    private final TopicService topicService;
    private final TopicReplyService topicReplyService;

    public DiscussionController(TopicService topicService, TopicReplyService topicReplyService) {
        this.topicService = topicService;
        this.topicReplyService = topicReplyService;
    }

    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/replies")
    public ResponseEntity<List<TopicReply>> getAllreplies() {
        List<TopicReply> replies = topicReplyService.getAllReplies();
        return ResponseEntity.ok(replies);
    }

}