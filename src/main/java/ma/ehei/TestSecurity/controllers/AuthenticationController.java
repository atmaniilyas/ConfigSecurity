package ma.ehei.TestSecurity.controllers;

import lombok.RequiredArgsConstructor;
import ma.ehei.TestSecurity.services.AuthenticationService;
import ma.ehei.TestSecurity.utils.AuthenticationRequest;
import ma.ehei.TestSecurity.utils.AuthenticationResponse;
import ma.ehei.TestSecurity.utils.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
    return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
