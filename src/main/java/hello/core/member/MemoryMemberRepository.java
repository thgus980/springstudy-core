package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component //수동으로 bean 등록 안해도 됨
public class MemoryMemberRepository implements MemberRepository{

    //클래스에 생성자가 명시적으로 정의되어 있지 않다면, Java 는 기본 생성자를 자동으로 제공합니다.
    //기본 생성자는 인자가 없는 생성자로, 아무런 초기화 작업도 수행하지 않습니다. 따라서 객체를 생성할 때 아무런 초기화 코드도 실행되지 않습니다.

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
