import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Repo {
    List<Map<String,Object>> mapList = new ArrayList<>();

    public void save(Map<String,Object> student){
        mapList.add(student);
    }

    public List<Map<String,Object>> findAll(){
        return mapList;
    }
   

}
