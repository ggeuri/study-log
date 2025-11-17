package p1;

public class App {
    public static void main(String[] args) {
        new Solution().printGugudan();
    }

}


class Solution {
    public void printGugudan(){
        for(int i = 1; i <= 9 ; i++){
            for(int y = 1; y <= 9; y++){
                int gugu = i*y; 
                System.out.println(gugu);
            }
        }
    }
}