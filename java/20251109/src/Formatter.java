import java.util.Map;

public class Formatter {
    
    public static String formatStudent(Map<String, Object> student){
        return String.format("이름 : [%s], 나이: %d세, 점수: %d점",
        student.get(Repo.k_Name),student.get(Repo.k_Age),student.get(Repo.k_Score));
    }

}
