package atcoder.domain;

public class Runner {
    private int speed = 0; 
    private int walkTime = 0; 
    private int restTime = 0; 

    private int distance = 0;
    private int currentRuntime = 0;
    private int currentResttime = 0;
    private boolean isRest = false;

    Runner(int speed, int walkTime, int restTime){
        this.speed = speed;
        this.walkTime = walkTime;
        this.restTime = restTime;
    }

    
    private void run(){
        distance += speed; 
        currentRuntime++;
        if(currentResttime == walkTime) {
            isRest = true; 
            currentRuntime = 0 ;
        }
    }   
    private void rest(){
        currentResttime++;
        if(currentResttime == restTime){
            isRest = false;
            currentResttime = 0; 
        }
    }
    public int getDistance() {
        return distance; 
    }
}   





