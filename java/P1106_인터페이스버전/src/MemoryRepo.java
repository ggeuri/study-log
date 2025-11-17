import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemoryRepo implements Repo {
    List<Map<String,Object>> mapList = new ArrayList<>();

    @Override
    public void save(Map<String,Object> student){
        mapList.add(student);
    }
    @Override
    public List<Map<String,Object>> findAll(){
        return mapList;
    }

}