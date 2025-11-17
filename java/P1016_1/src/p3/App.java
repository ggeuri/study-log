package p3;

public class App {
    public static void main(String[] args) {

        SumCalculator sumCalculator = new SumCalculator(); 
        sumCalculator.showResult(); 
        // 클래스 설계 원칙
        // 캡슐화 (정보의 은닉), 응집도 

        // main에는 추상화레벨 제일 높은거 주면 됨. 필요한것만. println생각해봐도 그냥 결과만 관심있으니 그것만 준 것. 
        // 설명볼거면 option + 클릭

        //응집도를 높이는 법 SOLID 원칙 클린코드 
        //SRP - Single Responsibility Principle 단일책임원칙
        //하나의 클래스는 하나의 역할만 해야하고 하나의 메서드는 하나의 기능만 해야한다.
        // 하나의 메서드는 3줄 이상이면 의심해야한다 

    }

}

class SumCalculator{

    public void showResult(){
        int result = calculate();
        printResult(result);
    }

    private void printResult(int result) {
        System.out.println("결과: "+ result);
    }

    private int calculate(){
        int sum = 0 ; 
        for(int i = 1; i <= 10; i++){
            if(!isMultipleofThree(i)){
                sum = accumulate(sum,i);
            }
         }
         return sum;
    }

    private int accumulate(int sum, int i){
        return sum += i;
    }


    private boolean isMultipleofThree(int number) {
         return number%3==0;
    }
}


//SRP원칙에 따라서 개선할 점을 gpt에게.. 