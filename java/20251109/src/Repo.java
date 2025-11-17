
import java.util.List;
import java.util.Map;

public interface Repo {
  public static final String k_Name = "이름";
  public static final String k_Age = "나이";
  public static final String k_Score = "점수";



    public abstract void save(Map<String, Object> student);
    public abstract List<Map<String, Object>> listStudent();
}
