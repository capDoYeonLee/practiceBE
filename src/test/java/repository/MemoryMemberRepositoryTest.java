package repository;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach  // 공용 데이터 제거
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Tony");

        repository.save(member);

        // 여기 result의 타입을 왜 Member type으로? #TODO
        Member result = repository.findById(member.getId()).get();
        System.out.println(repository.findById(member.getId()).get());
        //result의 값은 :hello.hellospring.domain.Member@29a0cdb -> HashMap이라서 Hash값으로 매핑이 된건가??
//        System.out.println("result의 값은 :" + result);
//        System.out.println("result =" + (result == member));
        assertEquals(member, result); //기대하는 값, 현재 값
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Tony");
        repository.save(member1);

        Member member2 = new Member(); // shift + f6
        member2.setName("Doyeon");
        repository.save(member2);

        Member result = repository.findByName("Tony").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Tony");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Doyeon");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);


    }




}