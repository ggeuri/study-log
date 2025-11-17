public class P2 {
    public static void main(String[] args) {

        StudentDto s1 = new StudentDto();  // 속성만 생성된다.
        s1.AdditionalInfo = new AdditionalInfo(); //이게 꼭 필요해 연쇄적으로 인스턴스 자동생성안되니까 우리가 수동생성

        s1.name = "한조" ; 
        s1.AdditionalInfo.birth = "남";

        System.out.println(s1.AdditionalInfo.birth); // NullPointerException 
        // 그럼 얘를 확인하려면 인스턴스 생성해줘야겠네 -> s1.AdditionalInfo = new AdditionalInfo();

    }
}

class StudentDto {
    String name ; 
    int age; 
    int score; 
    AdditionalInfo AdditionalInfo; // String 도 참조변수니까 들어가잖아. 다른클래스도 참조변수니까 들어감 얘는 참조에 참조..활용들어가면 또 참조지 뭐 
}

class AdditionalInfo {
    String gender;
    String birth;

}