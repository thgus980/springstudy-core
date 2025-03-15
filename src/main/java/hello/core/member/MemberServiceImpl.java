package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository=new MemoryMemberRepository(); //문제점: MemberServiceImpl은 추상화에도 의존하고 구체화에도 의존한다 -> DIP 위반

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
