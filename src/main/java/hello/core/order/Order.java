package hello.core.order;

public class Order {
    //필드 선언
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    // 생성자, getter, setter 단축키 (alt+fn+insert)
    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }


    public int calculatePrice(){ // 최종 계산된 금액
        return itemPrice-discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() { // 객체를 출력하면 toString 결과가 나온다 (편하게 데이터를 볼 수 있게) (ex 객체 order을 println에 바로 사용할 수 있다->order 출력 시 order.toString()으로 자동 호출)
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
