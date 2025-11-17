package p3;

import java.util.LinkedList;
import java.util.Queue;

public class App {
    public static void main(String[] args) {
        Queue<OrderDto> queue = new LinkedList<>(); // 실상은 링크드리스트 

        queue.offer(new OrderDto("햄버거"));
        queue.offer(new OrderDto("피자"));
        queue.offer(new OrderDto("치킨"));

        System.out.println(queue.poll().name); // 리턴해주고 삭제  
        System.out.println(queue.poll().name); // 리턴해주고 삭제  
        System.out.println(queue.poll().name); // 리턴해주고 삭제  
        System.out.println(queue.peek().name); // 걍 첫번째거 리턴
        System.out.println(queue.peek().name); // 걍 첫번째거 리턴
        System.out.println(queue.peek().name); // 걍 첫번째거 리턴


    }

}


class OrderDto {
    String name;
    OrderDto(String name){
        this.name = name;
    }

}