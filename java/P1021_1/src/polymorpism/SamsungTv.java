package polymorpism;

public class SamsungTv implements Tv {
    private Speaker speaker;

    public SamsungTv(Speaker speaker){
        this.speaker = speaker;
    }

    public void powerOn(){
        System.out.println("삼성 티비 전원 켠다");
    }
    public void powerOff(){
        System.out.println("삼성 티비 전원 끈다");
    }
    public void volumeUp(){
        speaker.soundUp();
    }
    public void volumeDown(){
        speaker.soundDown();
    }

}
