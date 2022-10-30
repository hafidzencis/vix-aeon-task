package com.miniproject.aeon.service;


import com.miniproject.aeon.domain.dao.Users;
import com.miniproject.aeon.domain.dto.TokenDTO;
import com.miniproject.aeon.domain.dto.UsersDTO;
import com.miniproject.aeon.repository.UserRepository;
import com.miniproject.aeon.security.JwtTokenProvider;
import com.miniproject.aeon.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<Object> register(UsersDTO req) {

        if (userRepository.existsByUsername(req.getUsername())) {
            log.error("User already exist");
            return ResponseUtil.build("SUCCESS", null, HttpStatus.BAD_REQUEST);
        }

        if (req.getRole() == null || !req.getRole().equals("ADMIN")) {
            log.info(" User : {}", req.getRole());
            Users users = Users.builder()
                    .username(req.getUsername())
                    .password(passwordEncoder.encode(req.getPassword()))
                    .role("USER")
                    .build();
            userRepository.save(users);
            log.info("User is Saved");

            return ResponseUtil.build("SUCCESS", null, HttpStatus.OK);
        }

        if (req.getRole().equals("ADMIN") ) {
            log.info(" admin , {}", req.getRole());
            Users users = Users.builder()
                    .username(req.getUsername())
                    .password(passwordEncoder.encode(req.getPassword()))
                    .role("ADMIN")
                    .build();
            userRepository.save(users);
            log.info("Admin is Saved");
            return ResponseUtil.build("SUCCESS", null, HttpStatus.OK);

        }
        return null;
    }


    public ResponseEntity<?> authenticateAndGenerateToken(UsersDTO req) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getUsername(),
                            req.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generationToken(authentication);
            return ResponseUtil.build("SUCCESS", TokenDTO.builder().token(jwt).build(),
                    HttpStatus.OK);
        } catch (BadCredentialsException e){
            log.error("Bad Credential", e.getMessage());
            return ResponseUtil.build("FAILED",null, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseUtil.build("FAILED",null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
