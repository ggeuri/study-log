package p2;

public class App {
    public static void main(String[] args) {
        new 식사().식사하다();
    }
}






class 식사 {
    
    private 재료준비하는사람 a;
    private 요리하는사람 b;
    private 식사하는사람 c; 
    
    public void 식사하다(){ //제일중요한 메서드를 제일 위에 
        재료를준비하다();
        요리를한다();
        음식을먹는다();
        
    }
    
    private void 재료를준비하다(){
        마트갈준비하다();
        마트에가다();
        재료를사다();
        마트에서돌아온다();
    }
    
    private void 마트갈준비하다(){}
    private void 마트에가다(){}
    private void 재료를사다(){}
    private void 마트에서돌아온다(){}
    
    
    private void 요리를한다(){
        
    }
    
    private void 음식을먹는다(){
        
    }
}

class 재료준비하는사람{

}

class 요리하는사람{
}

class 식사하는사람{
}
