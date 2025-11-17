package p2;

public class App {
    public static void main(String[] args) {
        Test t1 = new Test();

        t1.a = -1; 
        t1.b = 10; 

        int r1 = t1.plus(23,34) ;

        t1.a = 4;

        int r2 = t1.plus(2,37) ;
        int r3 = t1.plus(3,34) ;
        
        t1.a = -1; 

        int r4 = t1.plus(23,4) ;

        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);
    
    }

}

class Test {
    int a ; 
    int b ; 

    Test(){

    }

    int plus(int c, int d){ 
        if(a < 0) {return -1;}

        int result = a + b + c + d; 

        return result; 
    }
}
