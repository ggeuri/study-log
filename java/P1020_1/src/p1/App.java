package p1;

public class App {
    public static void main(String[] args)throws Exception{
        new Test().qqqq();
        
    }
}


class Test {
    //재귀 메서드 - 자기 자신을 호출하는경우 //무한히 자기자신을 호출 
    // 탈출하려면 리턴잘달아줘야
    // 

    private int count = 0 ; 
    public void qqqq() throws Exception{
        Thread.sleep(100);
        count++;
        if(count > 10) return; //탈출만 잘해주면 빠져나오는디 
        int currentCount = count;
        System.out.println("qqqq 시작 : " + currentCount);
        qqqq();
        System.out.println("qqqq 끝 : "+ currentCount); // 끝은 역순으로 빠져나오는데... 흠
    }

    public void yyyy() {

    }
}
