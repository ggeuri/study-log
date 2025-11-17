package p1;

public class App {
    public static void main(String[] args) {
        Computer computer = new Computer();
        int sum = computer.sum2(1,2,3,4,5,6);
        System.out.println(sum);

        int sum2 = computer.sum1(new int[] {1,2,3,4,5,6});//귀찮음 
        System.out.println(sum2);
    
    }

}

class Computer {

    int sum2(int ... values){
        int sum = 0; 
        for(int i = 0 ; i < values.length; i++){
            sum += values[i];
        }
    
    return sum;
    }
    int sum1(int[] values){ /////////귀찮음
        int sum = 0; 
        for(int i = 0 ; i < values.length; i++){
            sum += values[i];
        }
    
    return sum;
    }
}