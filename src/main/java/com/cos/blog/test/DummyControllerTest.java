package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DummyControllerTest {

    private final UserRepository userRepository;


    // 한 페이지당 2건에 데이터를 리턴 받기
    @GetMapping("dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> userList = pagingUser.getContent();
        return userList;
    }
    @GetMapping("/dummy/users")
    public List<User> userList() {
        return userRepository.findAll();
    }

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
