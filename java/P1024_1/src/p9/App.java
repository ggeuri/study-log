package p9;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class App {
    public static void main(String[] args) throws Exception {

        Class<AAA> clazz = AAA.class;

        // AAA aaa = clazz.getConstructors(AAA.class).newInstance(); //클래스에서 정보 끌고와서.... 

        
    }

}


class AAA{
    int a;
    int b;

    public AAA(){}
    
    @HelloWorld// 디폴트값있어서 가능 
    public void test1(){}
    @HelloWorld(qwer=5,name="멍") // 메타데이터 넣는거임. 근데 이걸로 뭐함
    public void test2(){}
}

//어노테이션 정의(문법). 정의부분은 그다지 신경 안써도 됨.
@Target(ElementType.METHOD) // 필드에 붙일지, 메서드에 붙일지, Constructor에 붙일지  
@Retention(RetentionPolicy.SOURCE)//소스일때는 소스에만 남고. Runtime이면 클래스까지남고 램에서 제거(제일 오래가는 케이스)
@interface HelloWorld { // 그냥 인터페이스를 이용해서 어노테이션한 것뿐 왜 하필 인터페이스인지는 모르게씀.인터페이스랑 엮지말 것. 다른문법임 걍 
    int value() default 0; // 메타데이터에서 value는 대표이기때문에 만약 디폴트 다 설적되어있을때 숫자 하나만 적으면 value거임 
    int qwer() default 0 ;
    String name() default "양양";
}