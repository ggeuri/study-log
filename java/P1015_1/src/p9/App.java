package p9;

public class App {
    public static void main(String[] args) {
        Account a1 = new Account("철수"); 
        
        // a1.balance += 30000; 제한자로 private된 순간부터 이런식으로 코드 못짬. 
        a1.deposit(3000);
        a1.deposit(3000);
        a1.withdraw(50000);

        // System.out.println(a1.owner + "의 잔액: " + a1.balance ); ; // 제한자로 private된 순간부터 이런식으로 코드 못짬.  
        System.out.println(a1.getOwner() + "의 잔액: " + a1.getBalance() );
    }
}

class Account {
    private String owner; // 실무상 속성을 public으로 푸는 일은 없다........private! 
    private int balance;  // 쉽게 남에 의해서 변경돼서는 안된다 . 

    public Account(String owner) { // 생성자를 private vs public ... private면 생성불가 클래스 
        this.owner = owner;

    }
    public void deposit(int value) {
        balance += value ; 
    }

    public void withdraw(int value) {
        if(canNotWithdraw(value)) {
            System.out.println("잔액이 부족합니다.");
            return; 
        }
        balance -= value;

    }
    //get read-only (메서드로 권한 풀어주기)
    public String getOwner(){
        return owner;
    }
    public int getBalance(){
        return balance;
    }

    private boolean canNotWithdraw(int value) {
        return !(balance < value);  
    }
}