
public class RecursiveQuestion4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(product(5));
	}
	
	public static int product(int n){
		if(n > 1){
			return product(n-1) * n;
		}
		return n;
	}
}
