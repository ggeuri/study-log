package impl;

public class Speaker extends SmartDevice  {
    public Speaker(String name){
        super(name);
    }
    
    @Override
    public void turnOn(){
        super.turnOn();
        System.out.println("음향 체크");
    }
    @Override
    public void turnOff(){
        super.turnOff();
    }

    @Override
    public void showStatus(){
        printPowerStatus();}

}
