
public class RecursiveQuestion3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		countUp(3);
	}
	
	public static void countUp(int n){
		if(n >= 1){
			countUp(n - 1);
			System.out.println(n);
		}
	}
	
	public static void countDown(int n){
		if(n >= 1){
			System.out.println(n);
			countDown(n - 1);
		}
	}
}
