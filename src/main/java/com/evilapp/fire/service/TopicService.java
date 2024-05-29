package com.evilapp.fire.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evilapp.fire.model.Topic;
import com.evilapp.fire.model.TopicReply;
import com.evilapp.fire.repository.TopicRepository;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserService userService;

    @Autowired
    public TopicService(TopicRepository topicRepository, UserService userService) {
        this.topicRepository = topicRepository;
        this.userService = userService;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Optional<Topic> findTopicById(Long id) {
        return topicRepository.findById(id);
    }

    public Topic saveTopic(Topic topic) {
        topic.setCreatedBy(userService.getAuthenticatedUser().getId());
        return topicRepository.save(topic);
    }

}
