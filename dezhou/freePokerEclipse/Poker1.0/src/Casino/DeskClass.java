package Casino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Player.PlayerBase;
import Player.PlayerClass;

public class DeskClass{
	DeskSeatClass[] desks;
	
	//座位的位置坐标，固定值 ,单位dip
	//6点钟方向为第一个位置，顺时针方向排列
	//private int[] DeskSeatPostionX = {250,155,60,60,155,345,445,445,345};
	//private int[] DeskSeatPostionY = {210,210,170,70,25,25,70,170,210};
	
	//在座的玩家
	private List<Map<Integer, PlayerBase>> deskseatplayer = new ArrayList<Map<Integer, PlayerBase>>();
	private int deskseatsum;
	//所有座位的编号
	private Integer[] seats;
	
	
	public DeskClass(){
		//
	}
	//座位初始化
	public DeskClass(int desksum,String[] deskname,Integer[] deskno){
		this.deskseatsum = desksum;
		this.seats = deskno;
		desks = new DeskSeatClass[desksum];
		for (int i = 0; i < desksum; i++) {
			desks[i] = new DeskSeatClass(deskname[i], deskno[i]);
		}
	}
	
	public DeskSeatClass[] CheckDeskSeat(){
		return this.desks;
	}
	
	public void Show(){
		for (int i = 0; i < desks.length; i++) {
			System.out.println("No.:"+desks[i].GetSeatNo()+",Name:"+desks[i].GetSeatName());
		}
	}
	//存储座位编号
	public void SeatsNo(){

	}
	
	//获取座位数量
	public int getDeskseatsum() {
		return deskseatsum;
	}
	//设置座位数量
	public void setDeskseatsum(int deskseatsum) {
		this.deskseatsum = deskseatsum;
	}

	//玩家找座位坐下
	public void SitDown(PlayerClass player){
		PlayerBase[] pn = player.getPlays();
		Map<Integer, PlayerBase> map = new HashMap<Integer, PlayerBase>();
		for (int i = 0; i < seats.length; i++) {
			try {
				map = new HashMap<Integer, PlayerBase>();
				//System.out.println("seats:"+seats[i]+",players:"+pn[i]);
				map.put(seats[i], pn[i]);
				deskseatplayer.add(map);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		
	}
	
	/**
	 * 清桌子
	 */
	public void clearDeskPoker(){
		for (int i = 0; i < this.GetDeskPlayer().size(); i++) {
			for (Iterator iterator=this.GetDeskPlayer().get(i).entrySet().iterator(); iterator.hasNext();) {
				Map.Entry elementEntry = (Map.Entry) iterator.next();
				PlayerBase playinfo = (PlayerBase) elementEntry.getValue();
				playinfo.clearPlayerPoker();
			}
		}	
	}
	
	public List<Map<Integer, PlayerBase>> GetDeskPlayer(){
		return this.deskseatplayer;
	}

	public List<Map<Integer, PlayerBase>> getDeskseatplayer() {
		return deskseatplayer;
	}

	public void setDeskseatplayer(List<Map<Integer, PlayerBase>> deskseatplayer) {
		this.deskseatplayer = deskseatplayer;
	}

}