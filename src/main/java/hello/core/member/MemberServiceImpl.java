package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{ //클라이언트 코드

    //private final MemberRepository memberRepository=new MemoryMemberRepository(); //문제점: MemberServiceImpl 은 추상화에도 의존하고 구체화에도 의존한다 -> DIP 위반
    private final MemberRepository memberRepository;

    @Autowired //자동 의존 관계 주입. 마치 ac.getBean(MemberRepository.class)와 같이 작동.
    public MemberServiceImpl(MemberRepository memberRepository) { //생성자. 다른 곳에서 호출할 때 매개변수에 MemoryMemberRepository 를 넣음.
        //생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부 AppConfig 에서 결정된다 (의존관계에 대한 고민은 외부에 맡기고 실행에 집중)
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
