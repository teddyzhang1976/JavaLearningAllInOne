package Poker;

public class PokerClassBase{ 
	private String color; 
	private String poker; 
	public PokerClassBase(){ 
	} 
	public PokerClassBase(String color,String poker){ 
		this.color = color; 
		this.poker = poker; 
	} 
	public String getcolor() { 
		return color; 
	} 
	public String getpoker() { 
		return poker; 
	} 
} 