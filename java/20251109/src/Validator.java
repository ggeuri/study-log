public class Validator {
    public static final String range1To100 = "^(?:[1-9]\\d?|100)$";
    
    public String checkName(String inputName){
        
        while(!inputName.matches("^.*\\S.*$")){
                IoManager.print("잘못된 이름을 입력하셨습니다. ");
                IoManager.print("다시 입력해주세요. ");
                inputName = IoManager.input("재입력 > ");
            }

        return inputName;
    }
    
    public String checkNumber(String inputValue){
        while(!inputValue.matches(range1To100)){
                IoManager.print("잘못된 숫자를 입력하셨습니다. ");
                IoManager.print("다시 입력해주세요. ");
                inputValue = IoManager.input("재입력 > ");
            }

        return inputValue; 
    }
}
