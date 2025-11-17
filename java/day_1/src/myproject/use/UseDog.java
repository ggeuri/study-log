/* 외부 클래스 사용 */
package myproject.use; 
import myproject.animal.Dog;

class UseDog{
	public static void main(String[] args){

	Dog d = new Dog();
	System.out.println("The age of Dog is " + d.age);

	}
}