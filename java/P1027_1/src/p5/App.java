package p5;

public class App {
    public static void main(String[] args) {
        try{        
            String str = null;
            str = str.substring(3); // null // NullPointerException
    
            int a = 3/0; // by zero // ArithmeticException
    
            int[] arr = new int[5];
            arr[6] = 30; // 배열 5까지임 out of bound // ArrayIndexOutOfBoundsException

        }catch(NullPointerException e){ // Exception 혹은 Exception 상속받은 클래스만 사용 가능 Exception이 최고 부모. Exception을 상속한 클래스인 경우만 쓸 수있음
            System.out.println("NullPointerException이 발생한 경우의 예외를 처리하는 코드");
            e.printStackTrace(); //아 printStackTrace();는 어디서 에러났는지 알려주는구나 
            System.out.println(e.getMessage());//얘도 뭔지알려주는거긴한데  printStackTrace(); 이거씀 
        }catch(ArithmeticException e){
            System.out.println("ArithmeticException이 발생한 경우의 예외를 처리하는 코드");
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("ArrayIndexOutOfBoundsException이 발생한 경우의 예외를 처리하는 코드");
        }catch(Exception e ){
            System.out.println("걍 else같은 존재인가 Exception계의 Object 사실상 예측불가능");
            e.getStackTrace(); //여기서 로그찍어
        }
        System.out.println("프로그램 종료");

    }

}


