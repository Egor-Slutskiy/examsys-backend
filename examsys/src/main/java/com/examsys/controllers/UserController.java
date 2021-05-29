package com.examsys.controllers;

import com.examsys.entity.Response;
import com.examsys.entity.User;
import com.examsys.errors.UserNotFoundException;
import com.examsys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    User findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        final Response response = new Response();
        User db_user = repository.findByLogin(user.getLogin());
        if (db_user == null){
            repository.save(user);
            response.message = "SUCCESS";
        }else response.error = "USERNAME ALREADY TAKEN";
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authorize(@RequestBody User user){
        final Response response = new Response();
        User db_user = repository.findByLogin(user.getLogin());
        if (db_user != null) {
            if (db_user.getPassword().equals(user.getPassword())) {
                response.message = db_user.getId();
                response.message += ";" + db_user.getRole();
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }else {
                response.error = "WRONG DATA";
                return new ResponseEntity<>(response, HttpStatus.FOUND);
            }
        }
        else {
            response.error = "WRONG DATA";
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }
    }


    @PutMapping("/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id){
        return repository.findById(id)
                .map(user -> {
                    user.setLogin(newUser.getLogin());
                    user.setPassword(newUser.getPassword());
                    user.setName(newUser.getName());
                    user.setRole(newUser.getRole());
                    return repository.save(user);
                })
                .orElseGet(() -> repository.save(newUser));
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
