
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {
    Repo repo = new Repo();
    Validator validator = new Validator();

    public void addStudent(){
        IoManager.print("#### 학생 등록 ####");
        String name = IoManager.input("이름 입력 > ");

        String inputAge = IoManager.input("나이 입력 > ");
        int age = validator.readIntInRange1To100(inputAge);

        String inputScore = IoManager.input("점수 입력 > ");
        int score = validator.readIntInRange1To100(inputScore);

        Map<String, Object> student = new HashMap<>();

        student.put("이름", name);
        student.put("나이", age);
        student.put("점수", score);

        repo.save(student);

        IoManager.print("[" + name + "] 학생이 등록되었습니다.");

    }

    public void listStudent(){
        IoManager.print("#### 학생 목록 ####");
        List<Map<String,Object>> list = repo.findAll();

        if(list.isEmpty()){
            IoManager.print("등록된 학생이 없습니다.");
            return; }

        list.stream()
            .map(a -> String.format("이름 : %s , 나이: %d , 점수: %d",
            a.get("이름"),a.get("나이"),a.get("점수")))
            .forEach(System.out::println);

    }

    public void searchStudent(){
        IoManager.print("#### 학생 검색 ####");
        String searchKeyword = IoManager.input("검색할 학생의 이름 > ");

        List<Map<String,Object>> list = repo.findAll();

        if(list.isEmpty()){
            IoManager.print("등록된 학생이 없습니다.");
            return; 
        } else {
            list.stream()
                .filter(a -> ((String)a.get("이름")).contains(searchKeyword))
                .map(a -> String.format("이름 : %s , 나이: %d , 점수: %d", 
                a.get("이름"),a.get("나이"),a.get("점수")))
                .findFirst()
                .ifPresent(System.out::println);
            }
    }

    public void deletedStudent() {
        IoManager.print("#### 학생 삭제 ####");
        String deleteName = IoManager.input("삭제할 학생의 이름 > ");

        List<Map<String,Object>> list = repo.findAll();

        if(list.isEmpty()){
            IoManager.print("등록된 학생이 없습니다.");
            return;}

        boolean removed = list.removeIf(a -> a.get("이름").equals(deleteName));
        
        if(removed){
            IoManager.print("[" + deleteName + "] 학생이 삭제되었습니다.");
        } else {
            IoManager.print("검색된 학생이 존재하지 않습니다.");
        }
    }
    
    public void updateStudent(){
        IoManager.print("#### 학생 검색 ####");
        String targetName = IoManager.input("검색할 학생의 이름 > ");
        String updateName = IoManager.input("변경할 학생의 이름 > ");

        List<Map<String,Object>> list = repo.findAll();

        if(list.isEmpty()){
            IoManager.print("등록된 학생이 없습니다.");
            return;}
            
            boolean check = false;
            
            for (Map<String,Object> map : list) {
                if(((String)map.get("이름")).equals(targetName)){
                    map.replace("이름",targetName,updateName);
                    check = true;
                }
            }
            
            if(check){
                IoManager.print(targetName + "학생의 이름이 " + updateName + "으로 변경되었습니다.");
            } else {IoManager.print("검색된 학생이 존재하지 않습니다.");}
        }
        
    public void calculateScore(){
        IoManager.print("#### 학생 통계 ####");
        List<Map<String,Object>> list = repo.findAll();
        
        if(list.isEmpty()){
            IoManager.print("등록된 학생이 없습니다.");
            return;}
            
        double average = list.stream()
            .mapToDouble(a -> (Integer)a.get("점수"))
            .average()
            .orElse(0);

        int max = list.stream()
            .mapToInt(a -> (Integer)a.get("점수"))
            .max()
            .orElse(0);

        int min = list.stream()
            .mapToInt(a -> (Integer)a.get("점수"))
            .min()
            .orElse(0);

        System.out.println("학생 총 인원: " + list.size() + ", 점수 평균 : " + average + ", 최고 점수 : " + max + ", 최저 점수 : " + min);
    }

}




