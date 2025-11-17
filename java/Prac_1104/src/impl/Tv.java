package impl;

public class Tv extends SmartDevice implements Connectable {
    public Tv(String name){
        super(name);
    }
    
    @Override
    public void turnOn(){
        super.turnOn();
    }
    @Override
    public void turnOff(){
        super.turnOff();
    }
    @Override
    public void connectWifi() {
        System.out.println("Tv - wifi 연결");
    }
    @Override
    public void disconnectWifi() {
        System.out.println("Tv - wifi 연결해지");
    }
    @Override
    public void showStatus(){printPowerStatus();}
    

}
