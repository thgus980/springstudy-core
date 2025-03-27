package hello.core.singleton;

public class StatefulService {

    private int price; // 상태를 유지하는 필드 , 공유되는 필드. 해결법: 이 필드 없애고 order2 메소드 이용하자

    public void order(String name, int price) {
        System.out.println("name = "+name+" price = "+price);
        this.price=price; //여기가 문제!!
    }

    public int getPrice() {
        return price;
    }

 //   public int order2(String name, int price) {
 //       System.out.println("name = "+name+" price = "+price);
//        return price;
//    }

}
