package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DummyControllerTest {

    private final UserRepository userRepository;
    @PostMapping("/dummy/join")
    public String dummyJoin(User user) {
        user.setRole(RoleType.USER);
        User saved = userRepository.save(user);
        return "회원 가입 굿";
    }
}
