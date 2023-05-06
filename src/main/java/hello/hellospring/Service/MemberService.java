package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {  // command + shift + t => create test code

    private final MemberRepository memberRepository;


    //생성자
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }




    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 거르기
        // Optional로 받는이유 : findByName으로 result라는 객체에 값을 받아옴. 만약 null이라면 트라이 캐치를 사용해야 되는데 일단 객체로 받기 위함
        //Optional<Member> result = memberRepository.findByName(member.getName());
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    // 메서드로 빼는 방법 ctrl+t -> extract method -> name method
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
     *  전체 회원 조회
     * */
    public List<Member> findMember(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
