package com.example.MongoHW.web;

import com.example.MongoHW.documents.UserDocument;
import com.example.MongoHW.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createUserDoc(@RequestBody UserDocument userDocument) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDocument));
    }

    @GetMapping("/firstName/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getUsersDocByFirstName(@PathVariable String firstName) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersByFirstName(firstName));
    }

    @GetMapping("/lastName/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getUsersDocByLastName(@PathVariable String lastName) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersByLastName(lastName));
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getUsersDocByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));
    }

    @GetMapping("/age/{age}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getUsersDocByAgeGreaterThan(@PathVariable Integer age) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersByAgeGreaterThan(age));
    }

    @GetMapping("/married/{isMarried}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDocument>> getUsersDocByMarried(@PathVariable Boolean isMarried) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersByIsMarried(isMarried));
    }

    @DeleteMapping("/delete/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteUserDoc(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
