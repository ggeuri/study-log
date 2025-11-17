package impl;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Tv samsungTv = new Tv("삼성 Tv");
        AirConditioner lgAirConditioner = new AirConditioner("LG 에어컨");
        Speaker boseSpeaker = new Speaker("BOSE 스피커");

        List<SmartDevice> deviceList = new ArrayList<>();
        
        deviceList.add(samsungTv);
        deviceList.add(lgAirConditioner);
        deviceList.add(boseSpeaker);


        for (SmartDevice smartDevice : deviceList) {
            smartDevice.turnOn();
            if(smartDevice instanceof Connectable){
                Connectable connectable = (Connectable) smartDevice;
                connectable.connectWifi();
            }
            smartDevice.showStatus();
            
        }

        lgAirConditioner.temperature = 26;
        
        for (SmartDevice smartDevice : deviceList) {
            smartDevice.showStatus();
            if(smartDevice instanceof Connectable){
                Connectable connectable = (Connectable) smartDevice;
                connectable.disconnectWifi();
            }
            smartDevice.turnOff();
        }
        

        
    }

}
