package edu.wit.comp2000.group23.adt3;
import edu.wit.comp2000.group23.adt3.tools.ArrayQueue;
/**
 * 
 * @author wongt1
 *
 */
public class Station implements Comparable<Station>{
	
	private Platform inbound;
	private Platform outbound;
	private ArrayQueue<Passenger> enteringPassengers;
	private ArrayQueue<Passenger> leavingPassengers;
	private int route;
	private String name;
	
	public Station(String name, int route){
		this.name = name;
		this.route = route;
	}
	
	/**
	 * When passengers are entering the station
	 * @param p
	 */
    public void addArrivingPassenger(Passenger p){
        this.enteringPassengers.enqueue(p);
    }
    
    /**
     * When passengers are off the train
     */
    public void removeLeavingPassenger(){
    	this.leavingPassengers.dequeue();
    }
    
    /**
     * 
     * @return
     */
    public int getRoute(){
    	return this.route;
    }
    
    /**
     * 
     */
    public String getStationName(){
    	return this.name;
    }
    
	@Override
	public int compareTo(Station o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString(){
		return getStationName();
	}
}
