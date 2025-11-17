import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileRepo implements PersistableRepo{
    private static File file = new File("/Users/rimu/Temp/stmprac.dat");
    List<Map<String, Object>> list = new ArrayList<>();

    @Override
    public void save(Map<String, Object> student){ 
        list.add(student);
    }

    @Override
    public List<Map<String, Object>> listStudent(){
        
        return list;
    }
@Override
public void saveToFile(){

        try (FileOutputStream fos = new FileOutputStream(file); // 클로즈하려고 finally쪽에 넣어줌 
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            DataOutputStream dos = new DataOutputStream(bos)
        ){

            dos.writeByte('S');//시그니처. 로드할 때를 위해서
            dos.writeByte('T');//시그니처
            dos.writeByte('M');//시그니처

            dos.writeByte(1); // 버전
            
            dos.writeInt(list.size()); // 파일에 몇명 학생 존재하는지
       
            for (Map<String,Object> map : list) {
                dos.writeUTF((String)map.get(k_Name));
                dos.writeInt((Integer)map.get(k_Age));
                dos.writeInt((Integer)map.get(k_Score));
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadFromFile(){

        if(!file.exists()){// 파일이 존재하지 않을 경우도 있으니 예외처리 
            return;
        }

        try (FileInputStream fis = new FileInputStream(file); // 클로즈하려고 finally쪽에 넣어줌 
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream dis = new DataInputStream(bis)
        ){
            if(dis.readByte()!='S' || dis.readByte()!='T' || dis.readByte()!='M') {
                IoManager.print("지원되지 않는 파일이거나, 파일이 깨졌습니다.");
                return;                 
            }

            int version = dis.readByte();

            if (version != 1) {
            IoManager.print("지원하지 않는 파일 버전입니다. (ver=" + version + ")");
            return;
            }

            int count = dis.readInt();
       
            for(int i = 0; i < count ; i++){
                String name = dis.readUTF();
                int age =dis.readInt();
                int score =dis.readInt();

                Map<String, Object> student = new HashMap<>();
                student.put(Repo.k_Name, name);
                student.put(Repo.k_Age, age);
                student.put(Repo.k_Score, score);

                list.add(student);
            }
            
            IoManager.print("파일이 정상적으로 로드되었습니다.");
                
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
