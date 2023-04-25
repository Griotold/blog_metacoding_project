package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequiredArgsConstructor
public class DummyControllerTest {

    private final UserRepository userRepository;

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable Long id) {

        // 2. 예외를 던져 버리기
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    return new EntityNotFoundException("해당 사용자는 없어용 id :" + id);
                });

        return user;
    }
    @PostMapping("/dummy/join")
    public String dummyJoin(User user) {
        user.setRole(RoleType.USER);
        User saved = userRepository.save(user);
        return "회원 가입 굿";
    }
}
