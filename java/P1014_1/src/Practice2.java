public class Practice2 {

}
// Q2.
// Product 클래스를 만들고,
// 	•	필드: name, price, stock
// 	•	3개의 생성자
// 	•	기본 생성자 (모두 기본값)
// 	•	name만 받는 생성자
// 	•	name, price, stock 받는 생성자
// 	•	중복 코드 없이 this()로 오버로딩 구성하기.

class Product {
    String name; 
    int price; 
    int stock; 

    Product(){
        this(null,0,0);

    }
    Product(String name){

    }
    Product(String name, int price, int stock){

    }

    
}
