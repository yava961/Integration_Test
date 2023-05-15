package com.example.MongoHW.service.Impl;

import com.example.MongoHW.documents.UserDocument;
import com.example.MongoHW.exception.NotFoundException;
import com.example.MongoHW.repository.UserRepo;
import com.example.MongoHW.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserDocument createUser(UserDocument userDocument) {
        return userRepo.save(userDocument);
    }

    @Override
    public List<UserDocument> getUsersByFirstName(String firstName) {
        return userRepo.findUsersByFirstName(firstName).stream().collect(Collectors.toList());
    }

    @Override
    public List<UserDocument> getUsersByLastName(String lastName) {
        return userRepo.findUsersByLastName(lastName).stream().collect(Collectors.toList());

    }

    @Override
    public UserDocument getUserByEmail(String email) {
        return userRepo.findByEmail(email);

    }

    @Override
    public List<UserDocument> getUsersByAgeGreaterThan(Integer age) {
        return userRepo.findUsersByAgeGreaterThan(age).stream().collect(Collectors.toList());
    }

    @Override
    public List<UserDocument> getUsersByIsMarried(Boolean isMarried) {
        return userRepo.findUsersByIsMarried(isMarried).stream().collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        } else {
            throw new NotFoundException("User with id : " + id + " not found");
        }
    }
}
