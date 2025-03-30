package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter //Getter Setter 를 Annotation 을 통해 편리하게 자동으로 만들어주는 롬복
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok= new HelloLombok();
        helloLombok.setName("헬로 롬복");

        String name= helloLombok.getName();
        System.out.println("name = "+name);

        System.out.println("helloLombok = "+helloLombok); //@ToString 덕분에 -> helloLombok = HelloLombok(name=헬로 롬복, age=0)
    }
}
