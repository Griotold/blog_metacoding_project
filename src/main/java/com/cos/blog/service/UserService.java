package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void join(User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        User saved = userRepository.save(user);
    }
    @Transactional
    public User updateServe(User user) {
        User findedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> {
                    return new IllegalArgumentException("수정할 회원이 없습니다.");
                });

        // 서버 사이드 Validation!
        if (findedUser.getOauth() == null || findedUser.getOauth().equals("")) {
            String rawPassword = user.getPassword();
            String encPassword = bCryptPasswordEncoder.encode(rawPassword);
            findedUser.setPassword(encPassword);
            findedUser.setEmail(user.getEmail());
        }
        return findedUser;
    }
    @Transactional
    public boolean check(String username) {
        return userRepository.findByUsername(username).isEmpty();

    }
}
