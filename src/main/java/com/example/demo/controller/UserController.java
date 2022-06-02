package com.example.demo.controller;

import com.example.demo.exeption.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    // Получить все записи
    @GetMapping("/user")
    public List getAllNotes() {
        return userRepository.findAll();
    }

    // Создать запись
    @PostMapping("/user")
    public User createNote(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

   /* @PostMapping("/user/{Login,Password}")
    public User getUserByLoginAndPassword(@PathVariable(value = "Login") String Login,
                                          @PathVariable(value = "Password") String Password) throws UserNotFoundException {

        return userRepository.findByLoginAndPassword(Login,Password);


    }
    // Получить запись по id
    @GetMapping("/user/{id}")
    public User getNoteById(@PathVariable(value = "id") Long userId) throws UserNotFoundException {

        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
*/
    // Обновить запись
/*    @PutMapping("/user/{id}")
    public User updateNote(@PathVariable(value = "id") Long userId,
                           @Valid @RequestBody User userDetails) throws UserNotFoundException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.setLogin(userDetails.getLogin());
        user.setPassword(userDetails.getPassword());
        user.setVerity(userDetails.getVerity());
        user.setHash1(userDetails.getHash1());
        user.setHash2(userDetails.getHash2());

        User updatedUser = userRepository.save(user);
        return updatedUser;
    }*/

    // Удалить запись по id
/*    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteBook(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }*/
}
