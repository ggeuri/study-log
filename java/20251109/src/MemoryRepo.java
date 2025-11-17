
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MemoryRepo implements Repo{
  List<Map<String, Object>> list = new ArrayList<>();


    @Override
    public void save(Map<String, Object> student){
        list.add(student);
    }
    
    @Override
    public List<Map<String, Object>> listStudent(){
        return Collections.unmodifiableList(list);
    }
}