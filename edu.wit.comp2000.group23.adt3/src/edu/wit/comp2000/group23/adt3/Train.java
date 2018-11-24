package edu.wit.comp2000.group23.adt3;

import java.util.ArrayList;

public class Train {

	private String direction;

	private int maxPassengers;
	private int currentPassengers = 0;

	private boolean doorsOpen = true;

	private int id;

	private Station currentStation;
	private Platform currentPlatform;

	private ArrayList<Passenger> passengers;

	/**
	 * @param d
	 *            - enum direction (inbound or outbound)
	 * @param max
	 *            - max capacity
	 * @param id
	 *            - train id
	 * @param l
	 *            - logger
	 */
	public Train(int max, int id) {

		this.maxPassengers = max;

		this.id = id;

		this.passengers = new ArrayList<>();
	}

	public String getDirection() {
		return this.direction;
	}

	public int getMaxPassengers() {
		return this.maxPassengers;
	}

	public int getCurrentPassengers() {
		return this.currentPassengers;
	}

	public int getId() {
		return this.id;
	}

	public ArrayList<Passenger> getPassengers() {
		return this.passengers;
	}

	public void openDoors() {
		this.doorsOpen = true;
		this.currentPlatform.setTrainReadyToLeave(false);
	}

	public void closeDoors() {
		this.doorsOpen = false;
		this.currentPlatform.setTrainReadyToLeave(true);
	}

	public Station getCurrentStation() {
		return this.currentStation;
	}

	public void setCurrentStation(Station s) {
		this.currentStation = s;
	}

	public boolean embarkPassenger(Passenger p) {
		if (!this.doorsOpen) {
			return false;
		}

		this.passengers.add(p);
		p.setTrain(this);
		currentPassengers = passengers.size();

		if (this.passengers.size() == this.maxPassengers) {
			this.closeDoors();
			return false;
		}

		return true;
	}

	public void disembarkPassenger(Passenger p) {
		this.passengers.remove(p);
		p.setTrain(null);
		this.currentPassengers = passengers.size();
	}

	public void setDirection(String d) {
		this.direction = d;
	}
}