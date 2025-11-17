package p6;

public class App {
    public static void main(String[] args) {
        Playable p = new MusicPlayer();
        p.play();
    }

}

interface Playable {
    
    public void play();
}

class MusicPlayer implements Playable{
     public void play(){
        System.out.println("음악 재생");
        }
    }
class VideoPlayer implements Playable{
     public void play(){
        System.out.println("영상 재생");
        }

}