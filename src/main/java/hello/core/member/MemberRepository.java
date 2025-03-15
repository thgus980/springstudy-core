package hello.core.member;

public interface MemberRepository {
    void save (Member member); // 회원 정보 저장
    Member findById(Long memberId); // ID를 통해 회원을 찾는 기능
}
