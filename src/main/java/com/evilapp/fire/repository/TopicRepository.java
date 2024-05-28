package com.evilapp.fire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evilapp.fire.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{

}
