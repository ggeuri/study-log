package persistence;

import model.Student;
import model.StudentManager;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentData 클래스
 * 
 * 학생 정보를 파일에 저장하고 파일에서 로드하는 기능을 담당하는 클래스입니다.
 * CSV 형식으로 데이터를 저장하고 읽어들입니다.
 * 
 * 파일 형식: 이름,나이,점수
 * 예시:
 *   홍길동,25,95.5
 *   김철수,23,87.0
 *   이영희,24,92.3
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class StudentData {
    
    // ==================== 상수 ====================
    
    /** 학생 데이터를 저장할 파일의 기본 경로 및 이름 */
    private static final String DATA_FILE = "students.txt";
    
    /** CSV 형식의 데이터에서 각 필드를 구분하는 구분자 */
    private static final String DELIMITER = ",";

    
    // ==================== 파일 저장 메서드 ====================
    
    /**
     * 학생 목록을 파일에 저장합니다.
     * 
     * @param manager 학생 정보를 관리하는 StudentManager 객체
     * @return 저장 성공 시 true, 실패 시 false
     */
    public static boolean saveStudents(StudentManager manager) {
        try {
            FileWriter fw = new FileWriter(DATA_FILE, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Student student : manager.getAllStudents()) {
                String line = student.getName() + DELIMITER + 
                              student.getAge() + DELIMITER + 
                              student.getScore();
                
                bw.write(line);
                bw.newLine();
            }

            bw.close();
            fw.close();

            System.out.println("✓ 학생 정보가 파일에 저장되었습니다.");
            return true;

        } catch (IOException e) {
            System.out.println("✗ 파일 저장 중 오류가 발생했습니다: " + e.getMessage());
            return false;
        }
    }

    
    // ==================== 파일 로드 메서드 ====================
    
    /**
     * 파일에서 학생 정보를 읽어 로드합니다.
     * 
     * @return 파일에서 로드한 학생 리스트
     */
    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();

        try {
            File file = new File(DATA_FILE);
            if (!file.exists()) {
                System.out.println("저장된 학생 정보가 없습니다.");
                return students;
            }

            FileReader fr = new FileReader(DATA_FILE);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    String[] data = line.split(DELIMITER);

                    if (data.length == 3) {
                        String name = data[0].trim();
                        int age = Integer.parseInt(data[1].trim());
                        double score = Double.parseDouble(data[2].trim());

                        Student student = new Student(name, age, score);
                        students.add(student);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("⚠ 손상된 데이터 라인을 건너뛰었습니다: " + line);
                    continue;
                }
            }

            br.close();
            fr.close();

            if (students.size() > 0) {
                System.out.println("✓ 총 " + students.size() + "명의 학생 정보를 로드했습니다.");
            }

            return students;

        } catch (IOException e) {
            System.out.println("✗ 파일 로드 중 오류가 발생했습니다: " + e.getMessage());
            return students;
        }
    }

    
    // ==================== 파일 존재 여부 확인 메서드 ====================
    
    /**
     * 학생 데이터 파일이 존재하는지 확인합니다.
     * 
     * @return 파일이 존재하면 true, 없으면 false
     */
    public static boolean fileExists() {
        File file = new File(DATA_FILE);
        return file.exists();
    }
}
