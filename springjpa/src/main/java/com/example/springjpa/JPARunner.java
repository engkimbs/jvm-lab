package com.example.springjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JPARunner implements ApplicationRunner {

    @Autowired
    private MemberRepository repo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Member> memberList = repo.findAll();
        System.out.println(memberList);
    }
}
