import java.util.Scanner;
import java.io.*;
/**
 * 
 * @author Tin Wong, Justin Koutros, Eddie Lorenzana, Aaron Maynard
 * @title Data Structures ADT Lab 2 (Application)
 * @DueDate 10/16/16
 *
 */
public class calculator {
	private static Integer result = 0;
	/**
	 * Main method
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File file = new File("src/Infix Calculator Expressions - valid -- 2016-10-04 01.txt");

		readFile(file);
	}
	/**
	 * reads the file line by line
	 * @param file
	 * @throws FileNotFoundException
	 */
	private static void readFile(File file) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(file);
		String line = "";

		while (fileScanner.hasNextLine()) {
			line = fileScanner.nextLine();
			System.out.println("Line of .txt file - " + line);
			addToStack(line);
		}

		fileScanner.close();
	}
	/**
	 * A method to addToStack, calculates and pops accordingly to orderOfOperations (precedence) method
	 * @param eq
	 */
	private static void addToStack(String eq) {
		//Two Stacks (VectorStack), one to store numbers, one to store operations
		VectorStack<Integer> numbers = new VectorStack<>();
		VectorStack<Character> operations = new VectorStack<>();
		
		try {
			for (int i = 0; i < eq.length(); i++) {
				char index = eq.charAt(i);

				// before changes: if(index >= 0 && index <= 9)
				// after changes...

				if (index >= 48) {
					Integer intIndex = Character.getNumericValue(index);
					numbers.push(intIndex);
				} else if (index == '(') {
					operations.push(index);
				} else if (index == '+' || index == '-' || index == '*' || index == '/') {
					if (operations.isEmpty()) {
						operations.push(index);
					} else if (orderOfOperations(index) > orderOfOperations(operations.peek())) {
						operations.push(index);
					} else {
						while (!operations.isEmpty() && orderOfOperations(index) <= orderOfOperations(operations.peek())) {
							result = calculate(numbers.pop(), numbers.pop(), operations.pop());
							numbers.push(result);
						}
						operations.push(index);
					}

				} else if (index == ')') {
					// line 42 in .txt has (9/6)/(2/3)
					while (!operations.isEmpty() && operations.peek() != '(') {
						result = calculate(numbers.pop(), numbers.pop(), operations.pop());
						numbers.push(result);
					}
					//in case there was only one operation, which you would come across an EmptyStackException
					if (!operations.isEmpty()) {
						operations.pop();
					}
				}
			}
			
			while (!operations.isEmpty()) {
				result = calculate(numbers.pop(), numbers.pop(), operations.pop());
				numbers.push(result);
			}

			result = Integer.valueOf(numbers.peek());

			System.out.println("Result: " + result);

		} catch (ArithmeticException e) {
			System.out.println("DIVISION BY ZERO ERROR");
		}
		
	}

	/**
	 * Precedence method to return an integer for orderOfOperations
	 * 
	 * @param index
	 * @return
	 */
	private static int orderOfOperations(Character index) {
		switch (index) {
		case '(':
		case ')':
			return 0;
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		default:
			return 3;
		}
	}

	/**
	 * Calculate numOne, numTwo, and takes in operator - and does the operation accordingly
	 * 
	 * @param numOne
	 * @param numTwo
	 * @param operator
	 * @return
	 */
	private static Integer calculate(Integer numOne, Integer numTwo, Character operator) {
		switch (operator) {
		case '+':
			return (numOne + numTwo);
		case '-':
			return (numTwo - numOne);
		case '*':
			return (numOne * numTwo);
		case '/':
			return (numTwo / numOne);
		//(p.s it should never go to default, but it had to be implemented as it needed to return an Integer)
		default:
			return 0;
		}
	}
}
