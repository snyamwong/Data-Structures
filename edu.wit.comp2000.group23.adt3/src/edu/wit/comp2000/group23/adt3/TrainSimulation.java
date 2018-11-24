package edu.wit.comp2000.group23.adt3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainSimulation {
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("src/Station.txt");
		Scanner scanner = new Scanner(file);
		ArrayList<TrainRoute> routes = new ArrayList<>();
		
		
		while(scanner.hasNext()){
			TrainRoute tr = new TrainRoute();
			tr.addToTrainRoute(routes, scanner);
			System.out.println(tr);
		}
	}
}
