package p6;

public class App {
    public static void main(String[] args) {
        QQQ calcultateSqrt = Math::sqrt;   
        QQQ calcultateLog = Math::log;
        
        double sqrtResult = calcultateSqrt.calcultate(5);
        double logResult = calcultateLog.calcultate(5);

        System.out.println(sqrtResult);
        System.out.println(logResult);
        
    }


interface QQQ {
    public double calcultate(int a);

    }
}