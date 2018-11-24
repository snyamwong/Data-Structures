package edu.wit.comp2000.TinWong.adt5;

public class primeNumberTest {

	/**
	 * ABORT MISSION THIS ISN'T WORK
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int polynomialCounter = 0;
//		int numBuckets = 0;
//		int counter = 0;
//
//		while (counter != 50) {
//			boolean prime = false;
//			polynomialCounter = numBuckets;
//
//			if (polynomialCounter < 2) {
//				polynomialCounter = 2;
//			} else if (polynomialCounter < 3) {
//				polynomialCounter = 3;
//			} else if (polynomialCounter < 5) {
//				polynomialCounter = 5;
//			} else if (polynomialCounter < 7) {
//				polynomialCounter = 7;
//			} else {
//				// gross, disgusting, atrocious, appalling
//				while (!prime) {
//					polynomialCounter++;
//					if (polynomialCounter % 2 != 0 && polynomialCounter % 3 != 0
//							&& polynomialCounter % 5 != 0 && polynomialCounter % 7 != 0) {
//						prime = true;
//					}
//				}
//			}
//			System.out.println(polynomialCounter);
//			numBuckets = polynomialCounter;
//			counter++;
//		}
		
		System.out.println(isPrime(13));
	}
	
	private static boolean isPrime(int n) {
		if (n <= 1) {
			return false;
		} else if (n <= 3) {
			return true;
		} else if (n % 2 == 0 && n % 3 == 0) {
			return false;
		}
		int i = 2;
		while (i < n / 2) {
			if(n % i == 0){
				return false;
			}
			i++;
		}
		return true;
	}
}
