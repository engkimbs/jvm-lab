package springjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memberService")
public class MemberListService implements MemberListServiceImpl {

    @Autowired
    private MemberRepository repo;

    @Override
    public List<Member> getMemberList() throws Exception {
        return repo.findAll();
    }
}
