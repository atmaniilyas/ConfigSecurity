package ma.ehei.TestSecurity.services;

import lombok.RequiredArgsConstructor;
import ma.ehei.TestSecurity.domain.User;
import ma.ehei.TestSecurity.enumiration.ERole;
import ma.ehei.TestSecurity.repositories.UserRepository;
import ma.ehei.TestSecurity.utils.AuthenticationRequest;
import ma.ehei.TestSecurity.utils.AuthenticationResponse;
import ma.ehei.TestSecurity.utils.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .ERole(ERole.USER)
                //TODO MANUPILATION

                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        //var refreshToken = jwtService.genrateRefreshToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
