package p4;

import java.util.Stack;

public class App {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.push("햄버거");
        stack.push("치킨");
        stack.push("피자");
        System.out.println(stack.pop());//리턴하고 삭제 
        System.out.println(stack.pop());//리턴하고 삭제 
        System.out.println(stack.pop());//리턴하고 삭제 
        
    }

}
