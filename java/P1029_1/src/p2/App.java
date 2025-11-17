package p2;

public class App {
    public static void main(String[] args) {
        
    }
}

// 쇼핑몰 Dto들 : 1:다:다 ... Seller하나에 여러 Product , Product마다 여러 Option 
// 보통 상세페이지는 List아님 . List는 목록임 순서 .. 
// 하나에 대한 상세는 Map 
// 

class Seller {
    String name; 
    //등등.. 
}

class Product {
    String name;
    // 등등.. 

}

class Option {
    String name; 
    //등등.. 
}

class Review {
    String name; 
    //등등.. 
}