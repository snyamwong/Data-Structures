package edu.wit.comp2000.TinWong.adt5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author wongt1
 *
 */
public class WordCount {
	/**
	 * main method 
	 * 
	 * p.s american-english-JL.txt is not set to run automatically due to taking up an absurd amount of space
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File file = new File("src/wit-attendance-policy.txt");
		File file2 = new File("src/the-lancashire-cotton-famine.txt");
		File file3 = new File("src/attendance-output.txt");
		File file4 = new File("src/lancashire-output.txt");
		File file5 = new File("src/american-english-JL.txt");
		File file6 = new File("src/american-output.txt");

		HashTable fileTable = new HashTable();
		HashTable fileTable2 = new HashTable();
		HashTable fileTable3 = new HashTable();

		PrintWriter writer = new PrintWriter(file3);
		PrintWriter writer2 = new PrintWriter(file4);
		PrintWriter writer3 = new PrintWriter(file6);
		
		Scanner reader = new Scanner(file);
		readFile(reader, fileTable);

		Scanner reader2 = new Scanner(file2);
		readFile(reader2, fileTable2);

//		Scanner reader3 = new Scanner(file5);
//		readFile(reader3, fileTable3);

		writer.println("File: wit-attendance-policy.txt");
		writer.println(fileTable);

		writer2.println("File: the-lancashire-cotton-famine.txt");
		writer2.println(fileTable2);
		
//		writer3.println("File: american-english-JL.txt");
//		writer3.println(fileTable3);
		
		writer.close();
		writer2.close();
		writer3.close();
	}

	/**
	 * method that cleans up the String and gets rid of symbols, etc
	 * @param s
	 * @return
	 */
	public static String cleanUpString(String s) {
		s = s.toLowerCase();
		s = s.replaceAll("[,.-]", "");
		s = s.replaceAll("’s", "");
		s = s.replaceAll("'s", "");
		s = s.replaceAll("[0123456789]", "");
		s = s.replaceAll("\\s+", "");
		return s;
	}

	/**
	 * read file, then adds each String into HashTable
	 * @param s
	 * @param ht
	 */
	public static void readFile(Scanner s, HashTable ht) {
		while (s.hasNext()) {
			String string = s.next();
			string = cleanUpString(string);
			ht.add(string);
		}
	}
}
