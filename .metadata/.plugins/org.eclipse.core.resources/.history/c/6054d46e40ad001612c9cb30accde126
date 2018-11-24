package edu.wit.comp2000.group23.lists.Utils;

import java.util.ArrayList;
import java.util.Collections;

public class Pile{
	
	private ArrayList<Card> cards;
	
	/**
	 * Constructor for Pile
	 */
	public Pile(){
		this.cards = new ArrayList<Card>();
	}
	
	/**
	 * shuffle uses Collections.sort
	 */
	public void shuffle(){
		Collections.shuffle(cards);
	}
	
	@Override
	/**
	 * toString() for Pile
	 */
	public String toString(){
		String cardPile = "";
		for(Card c : this.cards){
			cardPile += c + " ";
		}
		return cardPile;
	}
	
	/**
	 * add card to pile's ArrayList
	 * @param c
	 */
	public void add(Card c){
		this.cards.add(c);
	}
	
	/**
	 * remove card from the list from Pile
	 * @param position
	 * @return
	 */
	public Card remove(int position){
		return this.cards.remove(position);
	}
	
	/**
	 * get length for Pile
	 * @return
	 */
	public int getLength(){
		return this.cards.size();
	}
	
	/**
	 * isEmpty method for private data field
	 * @return
	 */
	public boolean isEmpty(){
		if(this.cards.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * clear method for Pile's list
	 */
	public void clear(){
		this.cards.clear();
	}
	
	/**
	 * add pile to pile method
	 * 
	 * LOOK AS LOOK FROM PILE
	 * @param p
	 */
	public void addPileToPile(Pile p){
		for(int i = this.getLength() - 1; i > -1; i--){
			p.add(this.remove(i));
		}
	}
}
