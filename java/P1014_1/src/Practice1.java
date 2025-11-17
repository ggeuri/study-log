public class Practice1 {
    public static void main(String[] args) {
        Account ex = new Account("홍길동",10000);

        ex.printInfo();
    }

}

class Account{
    String owner ; 
    int balance; 

    Account(){
        owner = "이름없음"; 
        balance = 0;
    }

    Account(String owner, int balance) {
        this.owner = owner; 
        this.balance = balance; 

    }

    void printInfo() {
        System.out.println("예금주: " + owner + "잔액: " + balance + "원");
    }
}