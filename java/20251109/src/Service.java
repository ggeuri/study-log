import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {

    private final Repo repo;
    private final Validator validator = new Validator();

    public Service(Repo repo){
        this.repo = repo;
    }


    public void addStudent(){
        IoManager.print("[학생 등록]");
        String inputName = IoManager.input("학생 이름 > ");
        String name = validator.checkName(inputName);

        String inputAge = IoManager.input("학생 나이 > ");
        int age = Integer.parseInt(validator.checkNumber(inputAge));

        String inputScore = IoManager.input("학생 점수 > ");
        int score = Integer.parseInt(validator.checkNumber(inputScore));

        Map<String, Object> student = new HashMap<>();
        student.put(Repo.k_Name, name);
        student.put(Repo.k_Age, age);
        student.put(Repo.k_Score, score);

        repo.save(student);

    }

    public void listStudent(){
        IoManager.print("[학생 목록]");
        List<Map<String, Object>> list = repo.listStudent();

        if(list.isEmpty()){
            IoManager.print("등록된 학생이 존재하지 않습니다.");
            return;
        }

        list.stream()
        .sorted(Comparator.<Map<String, Object>,Integer> comparing(a -> (Integer) a.get(Repo.k_Score))
        .reversed().thenComparing( a -> (String)a.get(Repo.k_Name)))
        .map(a -> Formatter.formatStudent(a))
        .forEach(System.out::println);
    }

    public void searchStudent(){
        IoManager.print("[학생 검색]");
        String searchName = IoManager.input("검색할 학생의 이름 > ");
        List<Map<String, Object>> list = repo.listStudent();

        if(list.isEmpty()){
            IoManager.print("등록된 학생이 존재하지 않습니다.");
            return;
        } else {
            list.stream()
                .filter(a -> ((String)a.get(Repo.k_Name)).contains(searchName))
                .sorted(Comparator.<Map<String, Object>,Integer> comparing(a -> (Integer) a.get(Repo.k_Score))
                .reversed().thenComparing( a -> (String)a.get(Repo.k_Name)))
                .findFirst()
                .ifPresentOrElse(
                a -> System.out.println(Formatter.formatStudent(a)),
                () -> System.out.println("검색된 학생이 없습니다."));
        }




    }

    public void deleteStudent(){
        IoManager.print("[학생 삭제]");
        String deleteName = IoManager.input("삭제할 학생의 이름 > ");
        List<Map<String, Object>> list = repo.listStudent();

        int deleteCount = 0 ;
        for (Map<String,Object> map : list) {
            if(map.get(Repo.k_Name).equals(deleteName)){
                deleteCount++;
            }
            
        }

        boolean remove = list.
        removeIf(a -> ((String)a.get(Repo.k_Name)).equals(deleteName));

        if (remove) {
            IoManager.print("[" + deleteName +"] 학생이 " + deleteCount + "명 삭제되었습니다.");            
        } else {IoManager.print("[" + deleteName+"] 학생이 존재하지 않습니다.");}

    }

    public void updateStudent(){
        List<Map<String, Object>> list = repo.listStudent();
        IoManager.print("[학생 변경]");
        
        if(list.isEmpty()){
            IoManager.print("등록된 학생이 존재하지 않습니다.");
            return;
        }

        String targetName = IoManager.input("변경 대상 학생의 이름 > ");
        String updateName = IoManager.input("변경될 이름 > ");

        boolean updateCheck = false ; 

        for (Map<String,Object> map : list) {
            if(map.get(Repo.k_Name).equals(targetName)){
                IoManager.print("[" + targetName +"] 학생이 존재합니다.");
                map.replace(Repo.k_Name, targetName, updateName);
                updateCheck = true;
            }
        }

        if(updateCheck){
            IoManager.print("[" + targetName +"] -> [" + updateName + "] 으로 변경 완료 되었습니다.");
        } else {IoManager.print("변경된 학생이 없습니다.");}
    }

    public void calculateScore(){
        IoManager.print("[학생 통계]");
        List<Map<String, Object>> list = repo.listStudent();

        if(list.isEmpty()){
            IoManager.print("등록된 학생이 존재하지 않습니다.");
            return;
        }
        
        double average = list.stream()
                            .mapToDouble(a -> (int)a.get(Repo.k_Score))
                            .average()
                            .orElse(0);

        int max = list.stream()
                            .mapToInt(a -> (int)a.get(Repo.k_Score))
                            .max()
                            .orElse(0);

        int min = list.stream()
                            .mapToInt(a -> (int)a.get(Repo.k_Score))
                            .min()
                            .orElse(0);

        IoManager.print("학생 총 인원: " + list.size() + ", 총 평균점수: " + average + ", 최대 점수: " + max + ", 최저 점수: " + min);
    
    }


}
