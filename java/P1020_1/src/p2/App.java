package p2;

public class App {
    //재귀가 중요한건 데이터베이스.. 자료가 재귀형태이면 재귀를 써야한다
    public static void main(String[] args) {
        
    }

}

class ShopDto {
    String name; 
    //기타 추가정보

    ProductDto[] productList = new ProductDto[5]; // 1:N의 관계
}

class ProductDto {
    String name; 
    //기타 추가정보
    OptionDto[] optionList = new OptionDto[5]; // 1:N의 관계 
}

class OptionDto {
    String name ;
    //기타 추가정보
}
