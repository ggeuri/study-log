package atcoder;

import java.util.Scanner;

public class Abc249 {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int a = scanner.nextInt();//사실 뜯어내는게 맞다 
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();
        final int d = scanner.nextInt();
        final int e = scanner.nextInt();
        final int f = scanner.nextInt();
        final int x = scanner.nextInt();

        final Runner takahashi = new Runner(b, a, c); 
        final Runner aoki = new Runner(e, d, f); 

        Stadium stadium = new Stadium(x, takahashi, aoki); 
        stadium.simulate();

        scanner.close();
    }
}

class Stadium {
    final private int totalRunningTime;
    private Runner takahashi; 
    private Runner aoki; 

    public Stadium(int totalRunningTime, Runner takahashi, Runner aoki){
        this.totalRunningTime = totalRunningTime;
        this.takahashi = takahashi; 
        this.aoki = aoki; 
    }
    
    public void simulate() {
        for(int i = 0; i <= totalRunningTime ; i ++) {
            takahashi.process();
            aoki.process();
        }
    if(takahashi.getDistance() == aoki.getDistance()){
        System.out.println("draw");
    } else if (takahashi.getDistance() > aoki.getDistance()){
        System.out.println("takahashi");
    } else {System.out.println("aoki");} // 출력도 뜯어내야함 

    }

}

class Runner {
    // private final String name; 사실 네임있어야..  
    private final int speed ; // 초속
    private final int walkTime ; // 걷고
    private final int restTime ; // 쉬고

    private int distance = 0 ; 
    private int currentRuntime = 0; 
    private int currentResttime = 0; 
    private boolean isRest = false; 

    public Runner(final int speed,final int walkTime, final int restTime){
        this.speed = speed; 
        this.walkTime = walkTime; 
        this.restTime = restTime; 
    }

    public int getDistance() {
        return distance; 
    }

    public void process(){
        if(isRest){
            rest();
        } else { 
            run(); 
        }
    }

    private void run() {
        distance += speed; 
        currentRuntime++;
        if(currentResttime == walkTime) {
            isRest = true; 
            currentRuntime = 0 ;
        }
    }
    private void rest() {
        currentResttime++;
        if(currentResttime == restTime){
            isRest = false;
            currentResttime = 0; 
        }
    }

    



}

