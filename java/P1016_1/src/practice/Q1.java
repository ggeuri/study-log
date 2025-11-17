package practice;

public class Q1 {
    public static void main(String[] args) {
        
    }

    
}

class BadAccount {
    private final String owner; // owner안바뀜
    private int balance; // 바뀜 

    void deposit(int v) { balance += v; }
    void withdraw(int v) { balance -= v; }
}