import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        // 주제 : 학생 정보 관리 프로그램 
        // 기능 : 학생 정보 등록, 목록, 검색, 삭제, 수정 
        // 한 명의 학생 데이터: 이름, 나이, 성적 

        Scanner scanner = new Scanner(System.in); 
        // 앞에 타입 있고 뒤에 = 있으니 scanner는 변수다. 변수 구분 잘하자. 참조타입이다. 참조타입이란? 어딘가를 참조하는 것 .은 접근연산자다. 
        int studentNum = 0 ; 
        String[] nameList = new String[5];
        int[] ageList = new int[5];
        int[] scoreList = new int[5];

        // 환영인사
        System.out.println("=======================");
        System.out.println(" 학생 관리 프로그램 v1 ");
        System.out.println(" 개발자 : ?  ");
        System.out.println("=======================");

        while (true) {

            System.out.println("**메뉴**");
            System.out.println("1. 학생 정보 등록");
            System.out.println("2. 학생 정보 목록");
            System.out.println("3. 학생 정보 검색");
            System.out.println("4. 학생 정보 삭제");
            System.out.println("5. 학생 정보 수정");
            System.out.println("6. 학생 정보 통계");
            System.out.println("0. 프로그램 종료");

            System.out.print("명령어 입력 > ");
            // int command = Integer.parseInt(scanner.nextLine());
            String command = scanner.nextLine();


            if(command.equals("0")){
                break;
            } 
            

            if(command.equals("1")) {
                System.out.println("##### 학생 등록 #####");

                if(studentNum == nameList.length) {  // 스택 오버플로우 해결 
                    String[] newNameList = new String[nameList.length * 2];
                    int[] newAgeList = new int[nameList.length * 2];
                    int[] newScoreList = new int[nameList.length * 2];

                    for(int i = 0; i < nameList.length ; i++) {
                        newNameList[i] = nameList[i];
                        newAgeList[i] = ageList[i];
                        newScoreList[i] = scoreList[i];
                    }

                    nameList = newNameList ; // 메모리 소멸의 핵심 
                    ageList = newAgeList ; 
                    scoreList = newScoreList ;

                }
                
                System.out.print("이름 입력 > ");
                String name = scanner.nextLine(); // nameList[i] = 이렇게 짜지 않는 이유 ? 유효성 검사를 해야하니까 + 가독성 

                //char로 바꿔보기

                System.out.print("나이 입력 > ");
                int age = Integer.parseInt(scanner.nextLine()); 

                while(true){
                    if(age <= 0 || age >= 100){
                        System.out.println("올바른 나이가 아닙니다. 다시 확인해주세요");
                        System.out.print("나이 입력 > ");
                        age = Integer.parseInt(scanner.nextLine()); 
                    } else {break;}
                }

                System.out.print("점수 입력 > ");
                int score = Integer.parseInt(scanner.nextLine()); 

                while(true){
                    if(score <= 0 || score >= 100){
                        System.out.println("올바른 점수가 아닙니다. 다시 확인해주세요");
                        System.out.print("점수 입력 > ");
                        score = Integer.parseInt(scanner.nextLine()); 
                    } else {break;}
                }

                System.out.println("학생 이름: " + name + ", 학생 나이: " + age + ", 학생 점수: " + score + "점으로 입력되었습니다.");
                System.out.print("위 정보가 확실합니까? (Y/N) > ");
                String yesNo = scanner.nextLine();

                if(yesNo.equals("Y")){
                    nameList[studentNum] = name ;
                    ageList[studentNum] = age ; 
                    scoreList[studentNum] = score ;    
                    
                    studentNum++; 

                    System.out.println("학생 정보가 등록되었습니다.");
                    System.out.println("#####################");    
                } else {
                    System.out.println("다시 입력해주세요");
                }
                

                // 문제 구간 ------------------------------------------------

                // String stringAge = scanner.nextLine();

                // if(stringAge >= 0 && stringAge <= 120){
                //  int age = Integer.parseInt(stringAge);
                // } else { 
                //  System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                // } // char로 쪼개서 하나씩 숫자인지 확인해라 ? 
                // 문제 ------------------------------------------------

                //문제 구간 ---------------------------------------------
                // 다음과 같이 입력되었습니다(정보 출력). 위 정보가 확실합니까 ?(Y/N) - 완료 
                //문제: 이름(2글자 이상), 나이, 점수 값 유효성 검사 - 숫자가 아닌 경우 

                

            } else if(command.equals("2")) {
                System.out.println("학생 정보 목록 로직 수행");

                if(studentNum == 0) {
                    System.out.println("등록된 학생의 정보가 존재하지 않습니다.");
                    System.out.println("계속하시려면 enter를 눌러주세요.");
                    scanner.nextLine(); 
                    continue;
                }

                for(int i = 0; i < studentNum; i++) { 
                    // System.out.println("학생 이름: " + nameList[i] + ", 학생 나이: " + ageList[i] + ", 학생 점수: " + scoreList[i]);
                    String text = "";
                    text += "이름: " + nameList[i];
                    text += ", 나이: " + ageList[i];
                    text += ", 점수: " + scoreList[i];

                    System.out.println(text);
                }

                // 문제(쉬움) 만약 검색된 학생 정보가 없다면, "등록된 학생이 없습니다."를 출력하세요. -완
                System.out.println("총 " + studentNum + "명의 학생 정보가 존재합니다.");
                
            } else if(command.equals("3")) {
                System.out.println("#######학생 검색 #########");
                System.out.print("검색할 학생의 이름을 입력하세요 > ");
                String searchName = scanner.nextLine(); 

                int searchCount = 0; 

                for(int i = 0 ; i < studentNum ; i ++) {
                    if(nameList[i].contains(searchName)) {
                        String text = "";
                        text += "이름: " + nameList[i];
                        text += ", 나이: " + ageList[i];
                        text += ", 점수: " + scoreList[i];

                        System.out.println(text);
                        searchCount++;

                    }
                }

                System.out.println("총 " + searchCount + "명이 검색되었습니다.");

                
            } else if(command.equals("4")) {
                System.out.println("학생 정보 삭제 로직 수행");
                System.out.print("삭제할 학생의 이름을 입력하세요 > ");
                String name = scanner.nextLine(); 

                int deleteNum = 0; 

                for(int x = 0; x < studentNum ; x++ ) {
                    if(name.equals(nameList[x])) { 
                        for(int y = x; y < studentNum - 1 ; y++){
                            nameList[y] = nameList[y+1];
                            ageList[y] = ageList[y+1];
                            scoreList[y] = scoreList[y+1];
                        } 
                        deleteNum ++;
                        studentNum --;  
                        x--;  // 얘를 넣어야 밀어넣은 애부터 다시 확인해서 중복을 확인할 수 있다 . 
                    }
                }
                
                System.out.println("총" + deleteNum + "명의 학생이 삭제되었습니다. ");
                System.out.println("#####################################");
            } else if(command.equals("5")) {
                System.out.println("학생 정보 수정 로직 수행"); // 기획해보십쇼 학생검색(이름) -> 대상은 누구다 -> 나이변경 1, 점수변경2 - 완
                System.out.print("수정할 학생의 이름을 입력하세요 > ");
                String targetName = scanner.nextLine();

                for(int i = 0; i < studentNum; i++) {
                    if(nameList[i].equals(targetName)){
                        System.out.println("수정하실 학생의 이름은" + nameList[i] + "입니다.");
                        System.out.println("1. 이름변경");
                        System.out.println("2. 나이변경");
                        System.out.println("3. 성적변경");
                        System.out.print("입력 > ");
                        int option = Integer.parseInt(scanner.nextLine());

                        if(option == 1) {
                            System.out.print("변경하실 이름을 입력하세요 > ");
                            String reName = scanner.nextLine(); 

                            nameList[i] = reName; 
                        } else if(option == 2) {
                            System.out.print("변경하실 나이를 입력하세요 > ");
                            int reAge = Integer.parseInt(scanner.nextLine());
                            
                            ageList[i] = reAge;
                        } else if(option == 3) {
                            System.out.print("변경하실 점수를 입력하세요 > ");
                            int reScore = Integer.parseInt(scanner.nextLine());
                            
                            scoreList[i] = reScore;
                        } else {System.out.println("잘못된 입력입니다.");}
                    }
                }

                
                
            } else if(command.equals("6")) {
                System.out.println("학생 정보 통계 로직 수행"); // 학생평균점수 - 완, 가장낮은점수가진학생, 가장높은점수를 가진 학생 ! 

                int sumScore = 0; 
                double averageScore = 0.0; 

                for(int i = 0; i < studentNum ; i++) {
                    sumScore += scoreList[i]; 
                }

                int maxScoreNum = 0; 
                int minScoreNum = 0; 

                for(int i = 0; i < studentNum; i++) {
                    if(scoreList[maxScoreNum] <= scoreList[i]) {
                        maxScoreNum = i; //비교할 값을 계속 바꿔줘야. 이전 최대값
                    } 
                    
                    if(scoreList[minScoreNum] >= scoreList[i]) {
                        minScoreNum = i;
                    } 
                }

                averageScore = sumScore / (double)studentNum; 

                System.out.println("학생의 평균 점수는" + averageScore + "점입니다.");
                System.out.println("최고 점수를 가진 학생은 " + nameList[maxScoreNum] + "이고, 점수는 " + scoreList[maxScoreNum]+ "입니다.");
                System.out.println("최저 점수를 가진 학생은 " + nameList[minScoreNum] + "이고, 점수는 " + scoreList[minScoreNum]+ "입니다.");

                
            } else {
                System.out.println("번호를 잘못 입력하셨습니다.");
                System.out.println("다시 입력 바랍니다.");
                
            }

            System.out.println("계속하시려면 enter를 눌러주세요.");
            scanner.nextLine();


        }

        System.out.println("프로그램을 종료합니다.");
        System.out.println("이용해주셔서 감사합니다."); // 시프트 +탭은 땡겨옴 탭이랑 반대 

        scanner.close(); // 마지막에 종료처리해주는게 일반적으로 좋음 

    }
}
