package edu.wit.comp2000.group23.adt3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author wongt1
 *
 */
public class TrainRoute{
    private Station station;
	private Train train;
    private boolean TrainOccupy = false;
    private ArrayList<TrainRoute> routes = new ArrayList<>();
    
	/**
	 * Constructor for TrainRoute
	 */
	public TrainRoute(Station station){
		this.station = station;
	}
	
	public TrainRoute(){
		this.station = null;
		this.train = null;
	}
	
	public boolean hasTrainOccupy(){
		if(this.train != null){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void TrainOccupy(Train t){
		this.train.equals(t);
	}
	
	public void setTrainOccupy(boolean occupy){
		this.TrainOccupy = occupy;
	}
	
	public void addToTrainRoute(ArrayList<TrainRoute> routes, Scanner scanner){
		while(scanner.hasNext()){
			String stationName = scanner.next();
			int stationID = scanner.nextInt();
			Station station = new Station(stationName, stationID);
			routes.add(new TrainRoute(station));
		}
	}
	
	/**
	 * The method returns the Passenger of which Platform to take, either
	 * Inbound or Outbound
	 * @param start
	 * @param destination
	 * @return
	 */
	public String getDirection(Station start, Station destination){
		return "";
	}
	
	@Override
	public String toString(){
		return null;
	}
}
