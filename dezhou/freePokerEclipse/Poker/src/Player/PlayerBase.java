package Player;

import java.util.ArrayList;
import java.util.List;

import Poker.PokerClassBase;

public class PlayerBase{
	private int playerNo;
	private String playerName;
	private String playerSex;
	private int playerWealth;
	private int playerLevel;
	private String playerAvatar;
	private List<PokerClassBase> playerPoker = new ArrayList<PokerClassBase>();
	
	public PlayerBase(){
		//Show();
	}
	public void Show(){
		System.out.println("No:"+getPlayerNo());
		System.out.println("Name:"+getPlayerName());
		System.out.println("Sex:"+getPlayerSex());
		System.out.println("Wealth:"+getPlayerWealth());
		System.out.println("Level:"+getPlayerLevel());
		System.out.println("Avatar:"+getPlayerAvatar());
		System.out.println("Poker:"+getPlayerPoker());
	}
	
	public int getPlayerNo() {
		return playerNo;
	}
	public List<PokerClassBase> getPlayerPoker() {
		return playerPoker;
	}
	public void setPlayerPoker(List<PokerClassBase> playerpoker) {
		this.playerPoker = playerpoker;
	}
	public void addPlayerPoker(PokerClassBase pcb){
		this.playerPoker.add(pcb);
	}
	public void clearPlayerPoker(){
		this.playerPoker = new ArrayList<PokerClassBase>();
	}
	public void setPlayerNo(int playerNo) {
		this.playerNo = playerNo;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerSex() {
		return playerSex;
	}
	public void setPlayerSex(String playerSex) {
		this.playerSex = playerSex;
	}
	public int getPlayerWealth() {
		return playerWealth;
	}
	public void setPlayerWealth(int playerWealth) {
		this.playerWealth = playerWealth;
	}
	public int getPlayerLevel() {
		return playerLevel;
	}
	public void setPlayerLevel(int playerLevel) {
		this.playerLevel = playerLevel;
	}
	public String getPlayerAvatar() {
		return playerAvatar;
	}
	public void setPlayerAvatar(String playerAvatar) {
		this.playerAvatar = playerAvatar;
	}
	
}