import java.util.ArrayList;
import java.util.List;

public class Repo {
    List<Dto> dtoList = new ArrayList<>();

    public void save(Dto dto){
        dtoList.add(dto);
    }

    public List<Dto> findAll(){
        return dtoList;
    }
   

}
