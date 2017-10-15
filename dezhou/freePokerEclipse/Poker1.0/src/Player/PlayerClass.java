package Player;

import java.util.List;

import Poker.PokerClassBase;

public class PlayerClass{
	PlayerBase[] plays;
	private int[] playerNo;
	private String[] playerName;
	private String[] playerSex;
	private int[] playerWealth;
	private int[] playerLevel;
	private String playerAvatar;
	private List<PokerClassBase>[] playerPoker;
	
	public PlayerClass(){
		
	}
	public PlayerClass(int playssum,int[] no,String[] name,String[] sex,int[] wealth,
			int[] level,String avatar,List<PokerClassBase>[] pp){
		plays = new PlayerBase[playssum];
		for (int i = 0; i < playssum; i++) {
			plays[i] = new PlayerBase();
			plays[i].setPlayerNo(no[i]);
			plays[i].setPlayerName(name[i]);
			plays[i].setPlayerSex(sex[i]);
			plays[i].setPlayerWealth(wealth[i]);
			plays[i].setPlayerLevel(level[i]);
			plays[i].setPlayerAvatar(avatar);
			//plays[i].setPlayerPoker(pp[i]);
			//plays[i].Show();
		}
		this.playerNo = no;
		this.playerName = name;
		this.playerSex = sex;
		this.playerWealth = wealth;
		this.playerLevel = level;
		this.playerAvatar = avatar;
		this.playerPoker = pp;
	}

	public PlayerBase[] getPlays() {
		return plays;
	}

	public void setPlays(PlayerBase[] plays) {
		this.plays = plays;
	}

	public List<PokerClassBase>[] getPlayerPoker() {
		return playerPoker;
	}

	public void setPlayerPoker(List<PokerClassBase>[] playerPoker) {
		this.playerPoker = playerPoker;
	}
	
	public void clearPlayerPoker(){
		this.playerPoker = null;
	}

	public int[] getPlayerNo() {
		return playerNo;
	}

	public void setPlayerNo(int[] playerNo) {
		this.playerNo = playerNo;
	}

	public String[] getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String[] playerName) {
		this.playerName = playerName;
	}

	public String[] getPlayerSex() {
		return playerSex;
	}

	public void setPlayerSex(String[] playerSex) {
		this.playerSex = playerSex;
	}

	public int[] getPlayerWealth() {
		return playerWealth;
	}

	public void setPlayerWealth(int[] playerWealth) {
		this.playerWealth = playerWealth;
	}

	public int[] getPlayerLevel() {
		return playerLevel;
	}

	public void setPlayerLevel(int[] playerLevel) {
		this.playerLevel = playerLevel;
	}

	public String getPlayerAvatar() {
		return playerAvatar;
	}

	public void setPlayerAvatar(String playerAvatar) {
		this.playerAvatar = playerAvatar;
	}
	
	
}