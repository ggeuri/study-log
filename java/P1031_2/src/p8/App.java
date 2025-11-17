package p8;

public class App {
    public static void main(String[] args) {

        Box<String> boxString = new Box("냠냠");
        Box<Integer> boxInt = new Box(30);


        System.out.println(boxString.getItem());
        System.out.println(boxInt.getItem());

        
    }

}

class Box<T> {
    private T item; 

    public Box(T item){
        this.item = item ;
    }

    public void setItem(T item){
         this.item = item ;
    }

    public T getItem(){
        return item;
    }



}
