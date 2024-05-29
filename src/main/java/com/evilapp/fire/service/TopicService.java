package com.evilapp.fire.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evilapp.fire.model.Product;
import com.evilapp.fire.model.Topic;
import com.evilapp.fire.repository.TopicRepository;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Optional<Topic> findTopicById(Long id) {
        return topicRepository.findById(id);
    }

}
