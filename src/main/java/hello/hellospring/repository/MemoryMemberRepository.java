package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //0뒤에 대문자 "L"을 붙인 이유 -> 0으로 초기화 했다면 Int로 인식, long 타입 값을 나타내기 위해서는 숫자 뒤에 반드시 L을 붙여야한다.
    private static long sequence = 0L;

    public void clearStore(){
        store.clear();
    }

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        // 자바 문법에서 <>가 잘 이해가 안간다. 괄호안에 타입으로
        return Optional.ofNullable(store.get(id));
        // 결과가 없으면 null로 나오겠지??
        // null 발생 가능성 때문에 Optional로 감싼다. 그럼 결과가 어떻게 나오려나 확인해보자! -> 테스트 코드 작성해보기
    }
    @Override
    public Optional<Member> findByName(String name) {

        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // getName이 name과 동일한지 확인
                .findAny();  //자바 8 문법 String
    }
    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values()); // Map<Long, Member> 여기서 store.valuse()는 Member를 의미?
    }
}
