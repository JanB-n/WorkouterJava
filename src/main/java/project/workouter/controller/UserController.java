package project.workouter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.workouter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.workouter.repository.UserRepository;
import project.workouter.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

/**
 * Klasa odpowiedzialna za dodawanie użytkowników
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin
class UserController {

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/user")
    List<User> all() {
        return userRepository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/user")
    User newPerson(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // Single item


    @PutMapping("/user/{id}")
    User replacePerson(@RequestBody User newUser, @PathVariable Long id) {

        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("/user/{id}")
    void deletePerson(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    /**
     * Dodaje użytkownika do bazy
     */
    @PostMapping("/register")
    ResponseEntity register(@RequestBody User newUser){
        userService.addUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
