package hello.core.singleton;

public class SingletonService {

    //이 코드에서 static 변수 instance는 싱글톤 패턴의 유일한 인스턴스를 저장하고, static 메소드 getInstance()는 이 인스턴스에 접근할 수 있도록 합니다.

    private static final SingletonService instance = new SingletonService(); // 자바가 실행되면서 static 영역에 있는 것들을 초기화 함

    public static SingletonService getInstance() { // 인스턴스의 참조를 꺼낼 수 있는 유일한 메서드
        return  instance;
    }

    private SingletonService() { //private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 방지

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
