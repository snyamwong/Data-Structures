package edu.wit.comp2000.group23.adt3;

import edu.wit.comp2000.group23.adt3.tools.ArrayQueue;

public class Platform {
	private Train train;
	private Station station;
	private String direction;
	private int platformID;
	private boolean trainReady = false;
	private ArrayQueue<Passenger> boardingPassengers;
	private ArrayQueue<Passenger> arrivingPassengers;

	public Platform(String direction, int platformID, Train train) {
		this.platformID = platformID;
		this.direction = direction;
		this.train = train;
		this.boardingPassengers = new ArrayQueue<Passenger>();
		this.arrivingPassengers = new ArrayQueue<Passenger>();
	}

	/**
	 * Used only when passengers are boarding the train (leaving the platform)
	 * 
	 * @return
	 */
	public Passenger dequeuePassenger() {
		if (this.canDequeuePassenger()) {
			return this.boardingPassengers.dequeue();
		} else {
			return null;
		}
	}

	/**
	 * Used only when passengers are arriving at the platform
	 * 
	 * @param passenger
	 */
	public void enqueuePassengers(Passenger passenger) {
		this.arrivingPassengers.enqueue(passenger);
	}

	public void setDirection(String direction) {
		this.direction.equals(direction);
	}

	public int getPlatformID() {
		return this.platformID;
	}

	public Station getStation() {
		return this.station;
	}

	public boolean canDequeuePassenger() {
		return this.boardingPassengers.size() > 0;
	}

	public boolean isTrainReadyToLeave() {
		return this.trainReady;
	}
	
	public void setTrainReadyToLeave(boolean ready) {
        this.trainReady = ready;
    }
}
