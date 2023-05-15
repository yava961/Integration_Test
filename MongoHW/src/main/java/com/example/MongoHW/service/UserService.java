package com.example.MongoHW.service;

import com.example.MongoHW.documents.UserDocument;

import java.util.List;

public interface UserService {

    UserDocument createUser(UserDocument userDocument);
    List<UserDocument> getUsersByFirstName(String firstName);
    List<UserDocument> getUsersByLastName(String lastName);
    UserDocument getUserByEmail(String email);
    List<UserDocument> getUsersByAgeGreaterThan(Integer age);
    List<UserDocument> getUsersByIsMarried(Boolean isMarried);
    void deleteUserById(Long id);
}
