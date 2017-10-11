package Poker;

import java.util.ArrayList;
import java.util.List;

import Player.PlayerBase;
import Player.PlayerClass;

//=============================chenrun 英文学习=========================
//shuffle洗牌，cut切牌，deal发牌，sort 理牌，draw摸牌，play打出，discard弃牌
//翻牌前（pre-flop）,翻牌后（flop）,转机（turn ）,河底（river）,摊牌（the showdown）
//庄家（dealer）,小盲注SB (small blind)和大盲注BB (big blind)
//观察(check),投注（bet）,弃牌（fold）,跟注（call）,加注（raise）
//spade 黑桃 heart 红心 diamond 方块 club 梅花
//
//Poker Hand规则
//Royal Flush 		皇家同花顺
//Straight Flush	同花顺
//Four-of-a-Kind	四条
//Full House		葫芦
//Flush				同花色
//Straight			顺子
//Three-of-a-Kind	三条
//Two Pair			二对
//One Pair			一对
//No Pair			没对子
//=======================================================================
public class PokerClass{ 
	PokerClassBase[] pokers=new PokerClassBase[52]; 
	//private String[] colors={"♦","♣","♥","♠"}; //打印不支持这些特殊符号 
	private String[] colors={"1","2","3","4"}; 
	//private String[] values={"2","3","4","5","6","7","8","9","10","J","Q","K","A"}; 
	private String[] values={"2","3","4","5","6","7","8","9","10","11","12","13","14"}; 
	private List<PokerClassBase> listpokerpublicBases = new ArrayList<PokerClassBase>();
	public PokerClass() { 
		
		for (int i = 0; i < colors.length; i++) { 
			for (int j = 0; j < values.length; j++) { 
				pokers[i * 13 + j] = new PokerClassBase(colors[i], values[j]); 
			} 
		} 
	} 
	public PokerClassBase[] getCard() { 
		return pokers; 
	} 
	
	public void Show() { 
		String showpoker = "";
		for (int i = 0; i < pokers.length; i++) {
			showpoker += pokers[i].getpoker()+pokers[i].getcolor()+',';
		}
		System.out.println("pokers:"+showpoker);
	} 
	/**
	 * 发牌
	 * 根据 Texas Hold'em Poker 规则，发牌应该从庄家dealer的第3个玩家开始发（即大盲注的下一位玩家，顺时针）
	 * dealer从0算起
	 * @param newplayers
	 * @param dealer
	 */
	public void DealPoker(PlayerClass newplayers,int dealer){
		
		//newplayers.clearPlayerPoker();
		PlayerBase[] pBase =  newplayers.getPlays();
		int len = pBase.length;

		PlayerBase[] playerstemp = new PlayerBase[len];
		int first = dealer+3;
		//编排顺序：先从dealer第3位开始发
		int last = 0;
		for (int i = first; i < len ; i++) {
			playerstemp[i-first] = pBase[i];
			last++;
			//System.out.println("playerstemp1["+(i-first)+"]:"+playerstemp[i-first].getPlayerNo());
		}
		//编排顺序：最后再发庄家和大小盲注
		for (int i = 0; i < first; i++) {
			playerstemp[last] = pBase[i];
			//System.out.println("playerstemp2["+(last)+"]:"+playerstemp[last].getPlayerNo());
			last++;
		}	
		
		//开始发,每人发两张，分2次发
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < len; i++) {
				try {
					PokerClassBase tempPokerClassBase = GetDealPoker();
					//System.out.println("GetDealPoker()"+tempPokerClassBase.getpoker()+tempPokerClassBase.getcolor());
					playerstemp[i].addPlayerPoker(tempPokerClassBase);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}
		//Show();	
	}
	
	/**
	 * 发牌动作
	 * 从一幅牌的第一张开始取
	 * 从pokers对象中取出第一个值，取出后并删除其索引
	 * @return getPokerClassBase
	 */
	public PokerClassBase GetDealPoker(){
		PokerClassBase getPokerClassBase = new PokerClassBase(pokers[0].getcolor(),pokers[0].getpoker());
		PokerClassBase[] pokerstemp=new PokerClassBase[pokers.length-1]; 
		for (int i = 1; i < pokers.length; i++) {
			pokerstemp[i-1]=pokers[i];
		}	
		pokers = pokerstemp;
		return getPokerClassBase;
	}
	
	/**
	 * 添加公共牌,从GM牌中取出
	 * @return 公共牌列表
	 */
	public List<PokerClassBase> AddPokerPublic(){
		PokerClassBase getpoker = GetDealPoker();
		//System.out.println("发公共牌"+getpoker.getpoker()+getpoker.getcolor());
		listpokerpublicBases.add(getpoker);
		return listpokerpublicBases;
	}
	
	public List<PokerClassBase> getListpokerpublicBases() {
		return listpokerpublicBases;
	}
	//洗牌运算
	public int[] random(int length) { 
		int[] a = new int[length]; 
		for (int i = 0; i < length; i++) { 
			a[i] = (int) (Math.random() * length);
			for (int j = 0; j < i; j++) { 
				if (a[j] == a[i]) {//比较是否相等，如果相等则重新产生 
					i--; 
					break; 
				} 
			} 
		} 
		return a; 
	} 
	
	//洗牌
	public void Shuffle() {
		//Show();
		int[] b = new int[52]; 
		b = random(52);//调用random方法产生一个长度为52的不重复的随机数组 
		
		pokers = getCard(); 
		PokerClassBase[] temp=new PokerClassBase[52];
		for(int i = 0; i < b.length; i++){ //开始遍历数组实现重新排列 
			int k = b[i]; 
			temp[i] = pokers[k]; 
		} 
		pokers = temp;
		//Show();
	} 
}