//import java.util.Arrays;

public class test_dontopen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {1, 2, 3};
		
		int[] test2 = new int[10];
		
		System.arraycopy(test, 0, test2, 0, 2);
		
		System.out.println(test.length);
		System.out.println(test2.length);
		
		for(int i = 0; i < test.length; i++){
			System.out.print(test[i]);
		}
		System.out.println();
		for(int i = 0; i < test.length; i++){
			System.out.print(test[i]);
		}
	}

}
