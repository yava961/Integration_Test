package com.example.MongoHW.repository;

import com.example.MongoHW.documents.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepo extends MongoRepository<UserDocument, Long> {

    List<UserDocument> findUsersByFirstName(String firstName);
    List<UserDocument> findUsersByLastName(String lastName);
    UserDocument findByEmail(String email);
    List<UserDocument> findUsersByAgeGreaterThan(Integer age);
    List<UserDocument> findUsersByIsMarried(Boolean isMarried);
}
