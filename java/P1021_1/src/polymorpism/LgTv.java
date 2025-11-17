package polymorpism;

public class LgTv implements Tv{
    private Speaker speaker; // 인스턴스변수에 의존하지않는다. 인터페이스에 의존한다.

    public LgTv(Speaker speaker){
        this.speaker = speaker;
    }


    public void powerOn(){
        System.out.println("Lg 티비 전원 켠다");
    }
    public void powerOff(){
        System.out.println("Lg 티비 전원 끈다");
    }
    public void volumeUp(){
        speaker.soundUp();
    }
    public void volumeDown(){
        speaker.soundDown();
    }

}
