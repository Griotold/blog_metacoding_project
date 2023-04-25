package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

    @GetMapping("http/lombok")
    public String lombokTest() {
        Member member = Member.builder().email("ssar@email.com")
                .username("ssar").password("1234").build();
        System.out.println("member.getUsername() = " + member.getUsername());
        member.setUsername("griotold");
        System.out.println("member.getUsername() = " + member.getUsername());
        return "lombok test successd";
    }

    @GetMapping("/http/get")
    public String getTest(Member m) { //?id=1&username=ssar&email=gtojd@email.com

        return "get요청: " + m.getId() + ", " + m.getUsername()
                + ", " + m.getEmail();
    }
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){
        return "get요청: " + m.getId() + ", " + m.getUsername()
                + ", " + m.getEmail() + ", " + m.getPassword();
    }
    @PutMapping("/http/put")
    public String putTest() {
        return "put요청";
    }
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete요청";
    }
}
