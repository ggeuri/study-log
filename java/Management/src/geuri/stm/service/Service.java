package geuri.stm.service;

import geuri.stm.dto.StudentDto;
import geuri.stm.repository.Repository;
import geuri.stm.util.IoManager;

public class Service {
    private Repository repository = new Repository(); 

    public void addStudent(){

        IoManager.println("학생 정보 등록");
        String name = IoManager.input("이름 > ");
        int age = Integer.parseInt(IoManager.input("나이 > "));
        int score = Integer.parseInt(IoManager.input("점수 > "));

        StudentDto studentDto = new StudentDto(name,age,score);//studentDto 클래스 퍼블릭이야 데려옴 

        repository.save(studentDto);

        IoManager.println("등록되었습니다.");


    }
    
    public void listStudent(){
        IoManager.println("학생 정보 목록");

        StudentDto[] list = repository.find(); //이거 참조값 주는거잖아 배열로 받고 

        for(StudentDto studentDto : list){
            IoManager.println("이름: " + studentDto.getName() + ", 나이: " + studentDto.getAge()+ ", 성적: " + studentDto.getScore());
        }

        IoManager.println("총 " + list.length + "명이 출력되었습니다.");
        
    }

    public void searchStudent(){
        IoManager.println("학생 검색");
        String searchWord = IoManager.input("검색할 학생의 이름 > ");
        StudentDto[] list = repository.findByName(searchWord);

        for(StudentDto studentDto : list){
            IoManager.println("이름: " + studentDto.getName() + ", 나이: " + studentDto.getAge()+ ", 성적: " + studentDto.getScore());
        }

        IoManager.println("총 " + list.length + "명이 출력되었습니다.");

    }
    
    public void deleteStudent(){
        IoManager.println("학생 삭제");
        int deleteCount = repository.deleteStudent(IoManager.input("삭제할 학생의 이름 > "));

        IoManager.println("총 " + deleteCount + "명이 삭제되었습니다.");

    }

    public void updateStudent(){
        IoManager.println("학생 수정");
        
        while (true) {
            StudentDto[] list ;
            IoManager.println("수정할 항목을 선택해주세요.");
            IoManager.println("1. 이름");
            IoManager.println("2. 나이");
            IoManager.println("3. 성적");
            IoManager.println("4. 수정항목 나가기");
            String selectCommand =  IoManager.input("입력 값 > ");

            if(selectCommand.equals("1")){ 
                String targetName = IoManager.input("이름 수정할 학생 이름 > ");
                String updateName = IoManager.input("변경할 학생 이름 > ");
                list = repository.updateStudent(targetName,updateName);
                for(StudentDto studentDto : list){
                    IoManager.println("변경된 이름: " + studentDto.getName());
                }
            }else if (selectCommand.equals("2")){
                String targetName = IoManager.input("나이 수정할 학생 이름 > ");
                int updateAge = Integer.parseInt(IoManager.input("변경할 나이 > "));
                list = repository.updateStudentAge(targetName,updateAge);
                for(StudentDto studentDto : list){
                    IoManager.println("변경된 나이: " + studentDto.getAge());
                }
            }else if (selectCommand.equals("3")){
                String targetName = IoManager.input("성적 수정할 학생 이름 > ");
                int updateScore = Integer.parseInt(IoManager.input("변경할 성적 > "));
                list = repository.updateStudentScore(targetName,updateScore);
                for(StudentDto studentDto : list){
                    IoManager.println("변경된 성적: " + studentDto.getScore());
                }

            }else if (selectCommand.equals("4")){
                IoManager.println("수정을 종료합니다.");
                break;
            }else{
                IoManager.println("잘못 입력하셨습니다.");
                IoManager.println("수정을 종료합니다.");
                break;}
     
            }
        }

        public void calculateStudent(){
            IoManager.println("학생 점수 통계");

            int sumScore = repository.calculateScore();

            double average = sumScore / (double)repository.find().length;
            
            IoManager.println("평균 점수는 " + average + "입니다. ");
        }

        

    } 

 

