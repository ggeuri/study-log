package p1;

public class App {
    public static void main(String[] args) {
        MyLinkedList<StudentDto> list = new MyLinkedList<>(); // 하면 firstNode가 생김 
        list.add(new StudentDto("철수"));
        list.add(new StudentDto("영희"));
        list.add(new StudentDto("길동"));
        list.add(new StudentDto("한조"));
//철수메르시길동한조
        list.add(new StudentDto("메르시"),2);
        list.remove(0);

        System.out.println(list.get(0).name);
        System.out.println(list.get(1).name);
        System.out.println(list.get(2).name);
        System.out.println(list.get(3).name);


    }

}

class StudentDto {
    StudentDto(String name){
        this.name = name;
    }
    String name; 
    int age;
    int score; 
}

// 학생정보를 담을 수 있는 클래스 정의 
// 알고리즘. 링크드리스트 
// 구현목표: 구현 방식을 통해 알고리즘 동작의 장단점을 이해한다
// 이미 남들이 구현해놨기떄문에 사실상 구현할 필요는 X 
// 자료구조 알고리즘별 장단점을 구분할줄알아야함. 완벽한 것은 없음. 특히 속도 면에서 장단점..
// 이걸 구분하려면 동작구조를 알아야함 


class Node<E> { //핵심코어부분 
    E data;
    Node<E> next; // 이부분에 다음애 참조값을넣네 
}


class MyLinkedList<E> {
    private Node<E> firstNode;
    
    // 추가 (맨 뒤)
    public void add(E data) {
        Node<E> newNode = new Node<>();
        newNode.data = data; //1번째 그림. newNode가 소멸된다. 학생메모리도 소멸된다 -> first 필요 ! 
        
        if(firstNode == null){
            firstNode = newNode;
            return;
        }
        
        // 꼬리 찾기 
        Node<E> tempNode = firstNode;
        
        while (tempNode.next != null) {
            tempNode = tempNode.next; //firstNode거 next에는 첫번째 노드 번지수있으니까 타고 타고 타고 null만날때까지 타고 타고 타고
        }
        
        //꼬리찾음
        tempNode.next = newNode;
        
    }
    
    ///////////////////////삽입 
    public void add(E data, int index) {
        Node<E> newNode = new Node<>();
        newNode.data = data;

        if(index == 0) {
            newNode.next = firstNode;
            firstNode = newNode;
            return;
        }

        Node<E> tempNode = firstNode ; 
        for(int i = 0 ; i <index-1 ; i++){
            tempNode = tempNode.next;
        }

        newNode.next = tempNode.next;
        tempNode.next = newNode;
    
    }


    //삭제(최상) 앞에 있는애를 찾아서 next 값을 삭제할 참조번지가 아니라 그 다음 링크 참조번지로 교체해주면 GC
    public void remove(int index){
        if(index == 0) {
            firstNode = firstNode.next;
            return;
        }

        Node<E> tempNode = firstNode; 
        for(int i = 0; i < index - 1; i++){
            tempNode = tempNode.next;
        }

        tempNode.next = tempNode.next.next; // 근데 이러면 원본이 아니라 tempNode에 저장되는거라 원본에 영향없는거아닌지?

    }

    //접근
    public E get(int index) {//최악 
        if(index == 0) {
            return firstNode.data;
        }
        
        Node<E> tempNode = firstNode;
        for(int i = 0; i < index ; i++ ){
            tempNode = tempNode.next;
        }

        return tempNode.data;
    }



}