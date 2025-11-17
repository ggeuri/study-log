package impl;

public class AirConditioner extends SmartDevice implements Connectable {
    int temperature = 24 ; 

    public AirConditioner(String name){
        super(name);
    }
    
    @Override
    public void turnOn(){
        super.turnOn();
        System.out.println("냉방 모드 준비");
    }
    @Override
    public void turnOff(){
        super.turnOff();
    }
    @Override
    public void connectWifi() {
        System.out.println("AirConditioner wifi 연결");
    }
    @Override
    public void disconnectWifi() {
        System.out.println("AirConditioner wifi 연결해지");
    }
    @Override
    public void showStatus(){
        System.out.println("현재온도: " + temperature + "도");
        printPowerStatus();}
    }
