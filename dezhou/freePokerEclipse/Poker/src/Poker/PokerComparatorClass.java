package Poker;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Player.PlayerBase;
/**
 * 排序规则类
 * @author pgm3
 *
 */
public class PokerComparatorClass implements Comparator<PokerClassBase>{

	public int compare(PokerClassBase a, PokerClassBase b) {
		// TODO Auto-generated method stub
		int inta = Integer.parseInt(a.getpoker());
		int intb = Integer.parseInt(b.getpoker());

		if(inta>intb){
			return 1;
		}else{
			return -1;
		}
	}
}

/**
 * 所有玩家手中牌的级别排序规则
 * 先排级别再排分数
 * @author pgm3
 *
 */
class PlayerPokerLevelComparatorClass implements Comparator<Map<PlayerBase, Map<Integer, Float>>>{

	@Override
	public int compare(Map<PlayerBase, Map<Integer, Float>> object1, 
			Map<PlayerBase, Map<Integer, Float>> object2) {
		// TODO Auto-generated method stub
		Integer level1 = 0;
		Integer level2 = 0;
		float score1 = 0;
		float score2 = 0;
		for (Iterator iterator = object1.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry elementEntry = (Map.Entry) iterator.next();
			Map<Integer, Float> map = new HashMap<Integer, Float>();
			map = (Map) elementEntry.getValue();
			for (Iterator iterator2 = map.entrySet().iterator(); iterator2.hasNext();) {
				Map.Entry elementEntry2 = (Map.Entry) iterator2.next();
				level1 = (Integer) elementEntry2.getKey();
				score1 = (Float) elementEntry2.getValue();
			}
		}
		for (Iterator iterator = object2.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry elementEntry = (Map.Entry) iterator.next();
			Map<Integer, Float> map = new HashMap<Integer, Float>();
			map = (Map) elementEntry.getValue();
			for (Iterator iterator2 = map.entrySet().iterator(); iterator2.hasNext();) {
				Map.Entry elementEntry2 = (Map.Entry) iterator2.next();
				level2 = (Integer) elementEntry2.getKey();
				score2 = (Float) elementEntry2.getValue();
			}
		}
		if(level1>level2){
			return -1;
		}else if(level1==level2){
			if(score1>score2){
				return -1;
			}
		}
		return 1;
	}
	
}