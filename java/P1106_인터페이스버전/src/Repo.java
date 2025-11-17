import java.util.List;
import java.util.Map;

public interface Repo {
   String keyName = "이름";
   String keyAge = "나이";
   String keyScore = "점수";
   void save(Map<String,Object> student);
   List<Map<String,Object>> findAll();

}





