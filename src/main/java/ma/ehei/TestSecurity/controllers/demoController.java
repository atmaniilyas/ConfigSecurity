package ma.ehei.TestSecurity.controllers;

import lombok.AllArgsConstructor;
import ma.ehei.TestSecurity.domain.User;
import ma.ehei.TestSecurity.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/demo/users")
public class demoController {
    private final UserRepository userRepository;
    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
