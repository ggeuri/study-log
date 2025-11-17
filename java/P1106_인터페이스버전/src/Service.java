
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {
    Validator validator = new Validator();
    private final Repo repo;
    public Service(Repo repo){
        this.repo = repo;
    }

    public void addStudent(){
        IoManager.print("#### 학생 등록 ####");
        String name = IoManager.input("이름 입력 > ");

        String inputAge = IoManager.input("나이 입력 > ");
        int age = validator.readIntInRange1To100(inputAge);

        String inputScore = IoManager.input("점수 입력 > ");
        int score = validator.readIntInRange1To100(inputScore);

        Map<String, Object> student = new HashMap<>();

        student.put(Repo.keyName, name);
        student.put(Repo.keyAge, age);
        student.put(Repo.keyScore, score);

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
            .sorted(Comparator.comparing((Map<String,Object> a) -> ((Integer)a.get(Repo.keyScore))).reversed()
            .thenComparing((Map<String,Object> a) -> ((String)a.get(Repo.keyName))))
            .map(a -> String.format("이름 : %s , 나이: %d , 점수: %d",
            a.get(Repo.keyName),a.get(Repo.keyAge),a.get(Repo.keyScore)))
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
                .filter(a -> ((String)a.get(Repo.keyName)).contains(searchKeyword))
                .sorted(Comparator.comparing((Map<String,Object> a) -> ((Integer)a.get(Repo.keyScore))).reversed()
                .thenComparing((Map<String,Object> a) -> ((String)a.get(Repo.keyName))))
                .map(a -> (String.format("이름 : %s , 나이: %d , 점수: %d", 
                a.get(Repo.keyName),a.get(Repo.keyAge),a.get(Repo.keyScore))))
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

        boolean removed = list.removeIf(a -> a.get(Repo.keyName).equals(deleteName));
        
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
                if(((String)map.get(Repo.keyName)).equals(targetName)){
                    map.replace(Repo.keyName,targetName,updateName);
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
            .mapToDouble(a -> (Integer)a.get(Repo.keyScore))
            .average()
            .orElse(0);

        int max = list.stream()
            .mapToInt(a -> (Integer)a.get(Repo.keyScore))
            .max()
            .orElse(0);

        int min = list.stream()
            .mapToInt(a -> (Integer)a.get(Repo.keyScore))
            .min()
            .orElse(0);

        System.out.println("학생 총 인원: " + list.size() + ", 점수 평균 : " + average + ", 최고 점수 : " + max + ", 최저 점수 : " + min);
    }

}




