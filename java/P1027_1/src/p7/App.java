package p7;

public class App {
    public static void main(String[] args) {
        // throw
        int result = 0; //try-catch도 블록이라..메모리..ㅠ 
        try {
            result = new SumCalculator().sum(10, 9);
        } catch (Exception e) {
            e.getStackTrace();
        }
        System.out.println(result);
        System.out.println("프로그램종료");
    }

}

class SumCalculator {
    public int sum(int a, int b){

        if( b < a ){
            // return -1;// 이건 의미가 안맞음 그러니까 throw
            throw new RuntimeException("b는 a보다 클 수 없음");// throw만나면 그즉시 프로그램종료 
        }
        int sum = 0; 
        for(int i = a; i <= b ; i ++){
            sum+=i;
        }
        return sum;
    }
}