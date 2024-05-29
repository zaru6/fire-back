package com.evilapp.fire.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/topics/{id}")
    public ResponseEntity<Topic> getTopicbyId(@PathVariable Long id) {
        Optional<Topic> topic = topicService.findTopicById(id);
        return topic.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/topics")
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        Topic savedTopic = topicService.saveTopic(topic);
        return new ResponseEntity<>(savedTopic, HttpStatus.CREATED);
    }

    @GetMapping("/replies")
    public ResponseEntity<List<TopicReply>> getAllReplies() {
        List<TopicReply> replies = topicReplyService.getAllReplies();
        return ResponseEntity.ok(replies);
    }

    @GetMapping("/topic-replies/{id}")
    public ResponseEntity<List<TopicReply>> getRepliesFromTopicId(@PathVariable Long id) {
        List<TopicReply> replies = topicReplyService.getAllRepliesForTopic(id.intValue());
        return ResponseEntity.ok(replies);
    }

    @PostMapping("/replies")
    public ResponseEntity<TopicReply> createTopicReply(@RequestBody TopicReply topicReply) {
        Integer maxReplyOrder = topicReplyService.getMaxReplyOrderForTopic(topicReply.getTopicId());
        if (maxReplyOrder == null) {
            topicReply.setReplyOrder(1);
        } else {
            topicReply.setReplyOrder(maxReplyOrder + 1);
        }
        TopicReply savedTopicReply = topicReplyService.saveTopicReply(topicReply);
        return new ResponseEntity<>(savedTopicReply, HttpStatus.CREATED);
    }

}
