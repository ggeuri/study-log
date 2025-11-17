import java.util.List;
import java.util.Scanner;

public class Service {
    Repo repo = new Repo();
    Scanner scanner = new Scanner(System.in);

    public void addStudent(){
        System.out.println("#### 학생 등록 ####");
        System.out.print("이름 입력 > ");
        String name = scanner.nextLine().trim();
        System.out.print("나이 입력 > ");
        String inputAge = scanner.nextLine().trim();

        while (!inputAge.matches("^([1-9]\\d?|100)$")) {
            System.out.println("다시 입력해주세요");
            System.out.print("나이 입력 > ");
            inputAge = scanner.nextLine().trim();
        }

        int age = Integer.parseInt(inputAge);
        System.out.print("점수 입력 > ");
        String inputScore = scanner.nextLine().trim();

        while (!inputScore.matches("^([1-9]\\d?|100)$")) {
            System.out.println("다시 입력해주세요");
            System.out.print("점수 입력 > ");
            inputScore = scanner.nextLine().trim();
        }
        int score = Integer.parseInt(inputScore);

        repo.save(new Dto(name, age, score));
       
        }

    public void listStudent(){
        System.out.println("#### 학생 목록 ####");
        List<Dto> list = repo.findAll();

        if(list.isEmpty()){
            System.out.println("등록된 학생이 없습니다.");
        } else {
            list.stream()
                .map(a -> "학생 이름: " + a.getName() + ", 학생 나이: " + a.getAge() + ", 학생 점수: " + a.getScore() )
                .forEach(System.out::println);
        }

    }

    public void searchStudent(){
        System.out.println("#### 학생 검색 ####");
        System.out.print("검색할 학생의 이름 > ");
        String searchKeyword = scanner.nextLine();

        List<Dto> list = repo.findAll();

        if(list.isEmpty()){
            System.out.println("등록된 학생이 없습니다.");
        } else {
            list.stream()
                .filter(a -> a.getName().contains(searchKeyword))
                .map(a -> "학생 이름: " + a.getName() + ", 학생 나이: " + a.getAge() + ", 학생 점수: " + a.getScore())
                .forEach(System.out::println);
        }
    }

    public void deletedStudent() {
        System.out.println("#### 학생 검색 ####");
        System.out.print("삭제할 학생의 이름 > ");
        String deleteName = scanner.nextLine();
        
        List<Dto> list = repo.findAll();
        
        boolean removed = list.removeIf(a -> a.getName().equals(deleteName));
        
        if(removed){
            System.out.println("[" + deleteName + "] 학생이 삭제되었습니다.");
        } else {
            System.out.println("검색된 학생이 존재하지 않습니다.");
        }
    }
    
    public void updateStudent(){
        System.out.println("#### 학생 검색 ####");
        System.out.print("수정할 학생의 이름 > ");
        String targetName = scanner.nextLine();
        System.out.print("변경할 학생의 이름 > ");
        String updateName = scanner.nextLine();

        List<Dto> list = repo.findAll();

        for (Dto dto : list) {
            if(dto.getName().equals(targetName)){
                dto.setName(updateName);
            }
        }

        System.out.println(targetName + "학생의 이름이 " + updateName + "으로 변경되었습니다.");
    }

    public void calculateScore(){
        System.out.println("#### 학생 통계 ####");
        List<Dto> list = repo.findAll();

        double average = list.stream()
            .mapToDouble(a -> a.getScore())
            .average()
            .orElse(0);

        int max = list.stream()
            .mapToInt(a -> a.getScore())
            .max()
            .getAsInt();

        System.out.println("학생 총 인원: " + list.size() + ", 학생 점수 평균 : " + average + ", 학생 최고 점수 : " + max);
    }
}




