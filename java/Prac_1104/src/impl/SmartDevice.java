package impl;

abstract public class SmartDevice {
    protected String name; 
    protected boolean power; 


    protected SmartDevice(String name){
        this.name = name; 
    }
    
    public void turnOn(){
        power = true;
        System.out.println( name + " 전원을 켭니다.");}

    public void turnOff(){
        power = false;
        System.out.println( name + "전원을 끕니다.");}

    public abstract void showStatus();

    protected void printPowerStatus(){
        System.out.println(getName() + "전원상태: " + (power ? "ON":"OFF"));
    }

    protected String getName(){
        return name;
        
    }
}

