public class Validator {

    public int readIntInRange1To100(String inputValue){
        while (!inputValue.matches("^([1-9]\\d?|100)$")) {
            IoManager.print("잘못된 입력값입니다. 다시 입력해주세요.");
            inputValue = IoManager.input("입력창 > ");
        }
        int value = Integer.parseInt(inputValue); 
        return value;
    }

}
