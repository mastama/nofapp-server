package com.nofappserver.controller;

import com.alibaba.fastjson.JSON;
import com.nofappserver.dto.JwtResponseDto;
import com.nofappserver.dto.LoginDto;
import com.nofappserver.dto.RegisterDto;
import com.nofappserver.model.User;
import com.nofappserver.payload.response.UserResponse;
import com.nofappserver.repository.UserRepository;
import com.nofappserver.service.JpaUserDetailService;
import com.nofappserver.service.UserService;
import com.nofappserver.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JpaUserDetailService jpaUserDetailService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    //    @GetMapping("/home")
//    public String home(){
//        return "home page";
//    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginDto loginDto) throws Exception {
        // authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        UserDetails userDetails = jpaUserDetailService.loadUserByUsername(loginDto.getUsername());
        String jwtToken = jwtUtil.generateToken(userDetails);
        JwtResponseDto jwtResponse = new JwtResponseDto(jwtToken, 200, "success");

        return new ResponseEntity<JwtResponseDto>(jwtResponse, HttpStatus.ACCEPTED);
    }

    // create registration
    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody RegisterDto registerDto) throws Exception {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        Optional<User> userOptional = userRepository.findByUsername(registerDto.getUsername());
        if (userOptional.isPresent()) {
            result.put("message", "Email Sudah Terdaftar");
            result.put("status", 400);
            result.put("error", "Email Invalid");
            String json = JSON.toJSON(result).toString();
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
        } else {
            try {
                UserResponse user = userService.create(registerDto);
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(registerDto.getUsername(), registerDto.getPassword()));

                UserDetails userDetails = jpaUserDetailService.loadUserByUsername(registerDto.getUsername());
                String jwtToken = jwtUtil.generateToken(userDetails);

                result.put("token", jwtToken);
                result.put("message", "Register Berhasil");
                result.put("status", 200);
                result.put("data", user);
                String json = JSON.toJSON(result).toString();
                return new ResponseEntity<>(json, HttpStatus.OK);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                result.put("message", "Register Gagal");
                result.put("status", 500);
                result.put("error", e.getMessage());
                String json = JSON.toJSON(result).toString();
                return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }
}
