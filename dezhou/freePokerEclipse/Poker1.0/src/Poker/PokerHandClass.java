package Poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Player.PlayerBase;

public class PokerHandClass{

	private List<PokerClassBase> listpc = new ArrayList<PokerClassBase>();//公共牌
	private List<Map<Integer, PlayerBase>> listplayers = new ArrayList<Map<Integer,PlayerBase>>();//玩家手中的牌
	private List<Map<PlayerBase, List<PokerClassBase>>> playerspokers = 
			new ArrayList<Map<PlayerBase,List<PokerClassBase>>>();//玩家的牌+公共牌
	
	/**
	 * 初始化比较牌
	 * @param lp 公共牌集合
	 * @param lmp  玩家集合
	 */
	public PokerHandClass(List<PokerClassBase> lp,List<Map<Integer, PlayerBase>> lmp) {
		this.listpc = lp;
		this.listplayers = lmp;
	}
	
	
	public List<PokerClassBase> getListpc() {
		return listpc;
	}
	

	public List<Map<PlayerBase, List<PokerClassBase>>> getPlayerspokers() {
		return playerspokers;
	}

	/**
	 * 合并玩家和公共牌
	 * @return
	 */
	public List<Map<PlayerBase, List<PokerClassBase>>> PlayerPublicPokers(){
		for (int i = 0; i < listplayers.size(); i++) {
			for (Iterator iterator=listplayers.get(i).entrySet().iterator(); iterator.hasNext();) {
				Map.Entry elementEntry = (Map.Entry) iterator.next();
				PlayerBase playinfo = (PlayerBase) elementEntry.getValue();
				List<PokerClassBase> pClassBases = new ArrayList<PokerClassBase>();
				pClassBases = playinfo.getPlayerPoker();
//				System.out.println("playinfo:"+playinfo.getPlayerName());
//				for (int j = 0; j < pClassBases.size(); j++) {
//					System.out.print("Pokers:"+pClassBases.get(j).getpoker()+","+pClassBases.get(j).getcolor());
//				}
//				System.out.println(" ");
				for (int j = 0; j < listpc.size(); j++) {
					pClassBases.add(listpc.get(j));
					//System.out.print(listpc.get(j).getpoker()+","+listpc.get(j).getcolor());
				}
				
				Map<PlayerBase, List<PokerClassBase>> map = new HashMap<PlayerBase, List<PokerClassBase>>();
				map.put(playinfo,pClassBases);
				
				playerspokers.add(map);
			}
		}	
		//ShowPlayerPublicPokers();
		return playerspokers;
	}
	
	public void ShowPlayerPublicPokers(){
		for (int i = 0; i < playerspokers.size(); i++) {
			for (Iterator iterator = playerspokers.get(i).entrySet().iterator(); iterator.hasNext();) {
				Map.Entry elementEntry = (Map.Entry) iterator.next();
				PlayerBase playinfo = (PlayerBase) elementEntry.getKey();
				List<PokerClassBase> lpc = (List<PokerClassBase>) elementEntry.getValue();
				System.out.println("Player:"+playinfo.getPlayerName());
				for (int j = 0; j < lpc.size(); j++) {
					System.out.print("Pokers:"+lpc.get(j).getpoker()+","+lpc.get(j).getcolor());
				}
			}
		}	
	}
	
	/**
	 * 比较玩家手中的牌，比较算法：
	 * 1 2   3 4   5 6  7
	 *  2     3     5   7
	 *     3     5      7
	 *        5         7
	 *              5
	 * 玩家5胜出 
	 */
	public void WinPlayer(){
		PokerHandWin();
	}
	
	public void PokerHandWin(){
		int size = this.playerspokers.size();
		List<Map<PlayerBase, Map<Integer, Float>>> playerpokerlevelList = 
				new ArrayList<Map<PlayerBase,Map<Integer, Float>>>();
		if(size>=2){
			for (int i = 0; i < playerspokers.size(); i++) {
				for (Iterator iterator = playerspokers.get(i).entrySet().iterator(); iterator.hasNext();) {
					Map.Entry elementEntry = (Map.Entry) iterator.next();
					PlayerBase playinfo = (PlayerBase) elementEntry.getKey();
					List<PokerClassBase> lpc = (List<PokerClassBase>) elementEntry.getValue();
					
					Map<PlayerBase, Map<Integer, Float>> map = new HashMap<PlayerBase, Map<Integer, Float>>();
					map.put(playinfo, PokerLevel(lpc));
					playerpokerlevelList.add(map);
				}
			}
		}
		//排序，按玩家手中的牌大小级别从大到小
		PlayerPokerLevelComparatorClass ppccClass = new PlayerPokerLevelComparatorClass();
		Collections.sort(playerpokerlevelList,ppccClass);
		
		for (int i = 0; i < playerpokerlevelList.size(); i++) {
			for (Iterator iterator = playerpokerlevelList.get(i).entrySet().iterator(); iterator.hasNext();) {
				Map.Entry elementEntry = (Map.Entry) iterator.next();
				PlayerBase player = (PlayerBase) elementEntry.getKey();
				System.out.println("player:"+player.getPlayerName());
				Map<Integer, Float> map = new HashMap<Integer, Float>();
				map = (Map) elementEntry.getValue();
				for (Iterator iterator2 = map.entrySet().iterator(); iterator2.hasNext();) {
					Map.Entry elementEntry2 = (Map.Entry) iterator2.next();
					System.out.println("level:"+elementEntry2.getKey()+", score:"+elementEntry2.getValue());
				}
				//Integer level = (Integer) elementEntry.getValue();
				System.out.println(" ");
			}
		}
	}
	
	/**
	 * 判断牌的组合
	 * Royal Flush 		皇家同花顺10
	 * Straight Flush	同花顺9
	 * Four-of-a-Kind	四条8
	 * Full House		葫芦7
	 * Flush			同花色6
	 * Straight			顺子5
	 * Three-of-a-Kind	三条4
	 * Two Pair			二对3
	 * One Pair			一对2
	 * No Pair			没对子 1
	 * @param playerpokers
	 */
	public Map<Integer, Float> PokerLevel(List<PokerClassBase> playerpokers){
		List<Integer> pokerlist = new ArrayList<Integer>();
		List<Integer> colorlist = new ArrayList<Integer>();
		int[] pair = new int[15];
		int[] straight = new int[5];
		float score = 0;
		for (int i = 0; i < playerpokers.size(); i++) {
			PokerClassBase poker = playerpokers.get(i);
			pokerlist.add(Integer.parseInt(poker.getpoker()));
			colorlist.add(Integer.parseInt(poker.getcolor()));
			//统计相同大小的牌的个数
			int getpairno = Integer.parseInt(poker.getpoker());
			pair[getpairno]++;
			//统计相同花牌的个数
			int getstraightno = Integer.parseInt(poker.getcolor());
			straight[getstraightno]++;
		}
		PokerComparatorClass pcc = new PokerComparatorClass();
		Collections.sort(playerpokers,pcc);
		
		Map<Integer, Float> map = new HashMap<Integer, Float>();
		
		if((score = RoyalFlush(playerpokers))>0){
			map.put(10, score);
			return map;
		}else if((score = StraightFlush(playerpokers))>0){
			map.put(9, score);
			return map;
		}else if((score = FourofaKind(pair))>0){
			map.put(8, score);
			return map;
		}else if((score = FullHouse(pair))>0){
			map.put(7, score);
			return map;
		}else if((score = Flush(straight))>0){
			map.put(6, score);
			return map;
		}else if((score = Straight(pokerlist))>0){
			map.put(5, score);
			return map;
		}else if((score = ThreeofaKind(pair))>0){
			map.put(4, score);
			return map;
		}else if((score = TwoPair(pair))>0){
			map.put(3, score);
			return map;
		}else if((score = OnePair(pair))>0){
			map.put(2, score);
			return map;
		}else{
			try {
				PokerClassBase poker0 = playerpokers.get(0);
				score = Float.parseFloat(poker0.getpoker())+(Float.parseFloat(poker0.getcolor())/10);
			} catch (Exception e) {
				// TODO: handle exception
			}
			map.put(1, score);
			return map;
		}
	}
	
	/**
	 * Royal Flush 皇家同花顺
	 * 原理：统计这一次的数和下次一数的对比，如果是大于1且花色相同就算一次，如果连续出现4次以上，
	 * 且最小的数字为10 则为皇家同花顺
	 * 理解：为什么是4次不是5次？原因是我用了i和i+1的比较,虽为4次,但是已经比较了5个牌
	 * 
	 * @param playerpokers
	 * @return
	 */
	public float RoyalFlush(List<PokerClassBase> playerpokers){
		int times = 0;
		boolean royal = false;
		int score = 1;
		for (int i = 0; i < playerpokers.size(); i++) {
			if(i<(playerpokers.size()-1)){
				try {
					Integer poker = Integer.parseInt(playerpokers.get(i).getpoker());
					Integer poker2 = Integer.parseInt(playerpokers.get(i+1).getpoker());
					
					String colorString = playerpokers.get(i).getcolor();
					String colorString2 = playerpokers.get(i+1).getcolor();
					if((poker.equals(poker2-1)) && (colorString.equals(colorString2))){
						times ++;
						score = Integer.parseInt(colorString);
						if(times==1){
							if(poker.equals(10))
							royal = true;
						}
						//System.out.println("i:"+poker+",i+1:"+poker2+",color:"+colorString+",color2:"+colorString2);
					}else{
						if(times<4){
							times=0;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.toString());
				}
			}
		}
		if(times>=4 && royal){
			return score;
		}
		return 0;
	}
	
	/**
	 * Straight Flush 同花顺
	 * 原理：统计这一次的数和下次一数的对比，如果是大于1且花色相同就算一次，如果连续出现4次以上，则为同花顺
	 * @param playerpokers
	 * @return
	 */
	public float StraightFlush(List<PokerClassBase> playerpokers){
		int times = 0;
		int score = 0;
		boolean colorscore = true;
		for (int i = 0; i < playerpokers.size(); i++) {
			if(i<(playerpokers.size()-1)){
				try {
					Integer poker = Integer.parseInt(playerpokers.get(i).getpoker());
					Integer poker2 = Integer.parseInt(playerpokers.get(i+1).getpoker());
					
					String colorString = playerpokers.get(i).getcolor();
					String colorString2 = playerpokers.get(i+1).getcolor();
					if((poker.equals(poker2-1)) && (colorString.equals(colorString2))){
						times ++;
						score +=poker;
						if(colorscore){
							score += Integer.parseInt(colorString);
							colorscore = false;
						}
						//System.out.println("i:"+poker+",i+1:"+poker2+",color:"+colorString+",color2:"+colorString2+",times:"+times);
					}else{
						if(times<4){
							times=0;
							score=0;
							colorscore = true;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.toString());
				}
			}
		}
		if(times>=4){
			return score;
		}
		return 0;
	}
	

	/**
	 * Four-of-a-Kind 四条
	 * 原理：统计同一个数字出现4次或以上则为4条
	 * @param pair
	 * @return 
	 */
	public float FourofaKind(int[] pair){
		int sorce = 0;
		for (int i = 0; i < pair.length; i++) {
			if(pair[i]>=4){
				sorce = i;
				return sorce;
			}
			if(pair[i]>4){
				System.out.println("( ？_ ？) ** 有人作弊！.....");
			}
		}
		return 0;
	}

	/**
	 * Full House 葫芦
	 * 原理:统计一手牌中3条的出现1次，2条的至少出现1次，则为葫芦
	 * @param pair
	 * @return
	 */
	public float FullHouse(int[] pair){
		int times = 0;
		int times2 = 0;
		int score = 0;
		for (int i = 0; i < pair.length; i++) {
			if (pair[i]==3) {
				times++;
				score = i;
			}
			if(pair[i]==2){
				times2++;
			}
		}
		if (times2>=1 && times==1) {
			return score;
		}
		return 0;
	}
	
	/**
	 * Flush 同花色
	 * 原理：统计一手牌同一个花色出现的次数，如果大于等于5，则为同花色
	 * @param straight
	 * @return
	 */
	public float Flush(int[] straight){
		int score = 0;
		for (int i = 0; i < straight.length; i++) {
			if(straight[i]>=5){
				score = i;
				return score;
			}
		}
		return 0;
	}
	
	
	/**
	 * Straight 顺子
	 * 原理：统计这一次的数和下次一数的对比，如果是大于1就算一次，如果连续出现4次以上，则为顺子
	 * @param pokerlist
	 * @return
	 */
	public float Straight(List<Integer> pokerlist){
		int times = 0;
		int score = 0;
		for (int i = 0; i < pokerlist.size(); i++) {
			if(i<(pokerlist.size()-1)){
				try {
					if((pokerlist.get(i)).equals(((pokerlist.get(i+1))-1))){
						times ++;
						score += pokerlist.get(i);
						//System.out.println("i:"+pokerlist.get(i)+",i+1:"+pokerlist.get(i+1)+",(i+1)-1:"+((pokerlist.get(i+1))-1)+",times:"+times);
					}else{
						if(times<4){
							times=0;
							score=0;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.toString());
				}
			}
		}
		if(times>=4){
			return score;
		}
		return 0;
	}
	
	
	/**
	 * Three-of-a-Kind 三条
	 * 原理：统计同一个数字出现三次则为3条,因为整体判断是从皇家同花开始，三条比葫芦小，所以不用判断是否为葫芦
	 * @param pair
	 * @return
	 */
	public float ThreeofaKind(int[] pair){
		int score = 0;
		for (int i = 0; i < pair.length; i++) {
			if(pair[i]==3) {
				score = i;
				return score;
			}
		}
		return 0;
	}
	
	/**
	 * Two Pair 二对
	 * 原理：统计一手牌中有2种数字以上的牌重复出现2次
	 * @param pair
	 * @return
	 */
	public float TwoPair(int[] pair){
		int times = 0;
		int max = 0;
		List<Integer> scoreList = new ArrayList<Integer>();
		for (int i = 0; i < pair.length; i++) {
			if (pair[i]==2) {
				times++;
				scoreList.add(i);
			}
		}
		try {
			max = scoreList.get(0);
			for (int i = 1; i < scoreList.size(); i++) {
				int temp = scoreList.get(i);
				if(temp>max){
					max = temp;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		if(times>=2) return max;
		return 0;
	}

	
	/**
	 * One Pair 一对
	 * 原理：统计一手牌中一个数字的牌重复出现2次
	 * @param pair
	 * @return
	 */
	public float OnePair(int[] pair){
		int score = 0;
		for (int i = 0; i < pair.length; i++) {
			if (pair[i]==2) {
				score = i;
				return score;
			}
		}
		return 0;
	}
}