package p2;

public class App {
    public static void main(String[] args) {
        //String 

        String text = "" ; 
        long time = System.currentTimeMillis();
        System.out.println("첫번쨰" + (System.currentTimeMillis() - time )+"ms"); //1970년 1월 1일 0시 0분 0초 000ms부터 지금까지 지난 ms 
        for(int i = 0 ; i < 100; i++) {
            text += "야호 ! ";
        // //     //이거 할 때마다 new String이나 다름없음 
        // //     //결과는 잘나오지만 최적화구림 불변객체는 안정성 측면에서 좋다.
        // //     //즉 한번 세팅되면 그 메모리는 값이 변하지 않음. 
        // //     //단, 값을 변경시키는 메서드 혹은 그에 준하는 연산자 수행 시, 새로운 메모리를 생성시킨다
        // //     //이러한 이유때문에 String + 연산은 많이 발생했을 시에 퍼포먼스에 영향을 준다 
        }
        
        System.out.println(text);
        
        //그럼뭘써야하나.. 
        //StringBuilder, StringBuffer _ String에 대한 이해 + 스레드에 대한 이해 + 실질적 개발자들은 StringBuffer씀 Builder는 스레드에 대한 이해가 있어야 사용할 수 있음
        // 스트링빌더는 동기화X 스레드에 안전하지 않음. 근데 왜 씀? 
        // 두 개의 차이는 뭘까..? 여유가 있는걸 버퍼라고 함(여유메모리를 가진 스트링..) 위에 야호는 버퍼가 없음 .
        //가변객체임 (내부의 메모리를 변경하고 새로운 메모리를 만들지는 않음 )

        StringBuilder stringBuilder = new StringBuilder(); //넓은 배열 만들어줌 버퍼를 좀 가지고 있음 
        
        for(int i = 0 ; i < 100; i++) {
            stringBuilder.append("야호 ! ");
        }

        System.out.println("두번쨰" + (System.currentTimeMillis() - time )+"ms"); //1970년 1월 1일 0시 0분 0초 000ms부터 지금까지 지난 ms 
        System.out.println(stringBuilder);
        String resultText = stringBuilder.toString(); 

        System.out.println(resultText);

        //실행속도체크를 위한 API

        // System.out.println(time);/
    }
    
}