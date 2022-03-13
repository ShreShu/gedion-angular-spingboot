package com.project.youtube.youtubeclone.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.youtube.youtubeclone.model.Vedio;

@Repository
public interface VideoRepository extends MongoRepository<Vedio, String> {

}
