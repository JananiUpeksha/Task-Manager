package com.example.taskmanager.service.impl;
import com.example.taskmanager.dao.UserDAO;
import com.example.taskmanager.dto.UserDTO;
import com.example.taskmanager.entity.impl.UserEntity;
import com.example.taskmanager.secure.JWTAuthResponse;
import com.example.taskmanager.secure.SignIn;
import com.example.taskmanager.service.AuthService;
import com.example.taskmanager.service.JWTService;
import com.example.taskmanager.utill.AppUtill;
import com.example.taskmanager.utill.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceIMPL implements AuthService {

    private final UserDAO userDAO;
    private final Mapping mapping;
    private final AppUtill appUtill;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    // user log in
    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword())
        );
        var user = userDAO.findByUsername(signIn.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var generatedToken = jwtService.generateToken((UserDetails) user);

        return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
        // Generate a Long ID and set it as the user ID
        userDTO.setId(Long.parseLong(appUtill.generateId("user"))); // Convert the ID to Long
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserEntity savedUser = userDAO.save(mapping.toUserEntity(userDTO));
        var token = jwtService.generateToken((UserDetails) savedUser); // No need to cast to UserDetails

        return JWTAuthResponse.builder().token(token).build();
    }



    // refresh token
    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        var username = jwtService.extractUserName(accessToken);
        var findUser = userDAO.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        var refreshedToken = jwtService.refreshToken((UserDetails) findUser);

        return JWTAuthResponse.builder().token(refreshedToken).build();
    }
}
