package sleek.poker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Casino.DeskClass;
import Player.PlayerBase;
import Player.PlayerClass;
import Poker.PokerClass;
import Poker.PokerClassBase;
import Poker.PokerHandClass;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PokerActivity extends Activity {
    /** Called when the activity is first created. */
	//定义座位的名称
	private String[] deskname = {"A1","B1","C1","D1","E1","F1","J1","K1","H1"};
	//定义座位的编号
	//范围：总共有9个位置
	private Integer[] deskno = {1,2,3,4,5,6,7,8,9};
	//定义一张桌子的座位数量
	private int desksum = 9;
	//定义玩家数量
	private int playsum = 9;
	//定义玩家资料
	private int[] playno = {3,1,2,4,5,6,7,8,9};
	private String[] playname = {"Dog","Cat","Woft","Sheep","Lion","Sky","Mouse","Misu","Cr"};
	private String[] playsex = {"Mr","Ms","Ms","Mr","Mr","Mr","Mr","Mr","Mr"};
	private int[] playwealth = {10000,20000,5000,3000,2500,50000,3000,2500,50000};
	private int[] playlevel = {5,8,1,6,4,1,7,10,2};
	private String playavatar =""; 
	
	private List<PokerClassBase>[] playpoker = null;
	private List<PokerClassBase> publicpokers;
	private List<Map<Integer, PlayerBase>> deskplays = new ArrayList<Map<Integer,PlayerBase>>();
	//实例化
	DeskClass newDesk;
	PokerClass newpoker; 
	PlayerClass newplayers;
	PokerHandClass pokerhand;
	
	//main xml 元素定义
	private RelativeLayout desk;
	private LinearLayout publicpoker;
	private Button newgame;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //初始化
        Initialize();
        //游戏准备
        NewGame();
		//开始游戏
		StartGame();
    }
    public void NewGame(){
		//新建一张赌桌
		newDesk = new DeskClass(desksum,deskname,deskno);
        
		//新建玩家
		newplayers = new PlayerClass(playsum,playno,playname, playsex, 
				playwealth, playlevel, playavatar,playpoker);
		//玩家就座
		newDesk.SitDown(newplayers);
		//玩家座位 视图
		ShowPlayerSeat(newDesk);
    }
    
    public void StartGame(){
    	//清理桌子
    	newDesk.clearDeskPoker();
        //新建一副牌
        newpoker = new PokerClass(); 
		//洗牌
		newpoker.Shuffle();
		//发牌
		newpoker.DealPoker(newplayers, 0);
		//发牌视图化
		ShowPlayerPoker(newDesk);	
		//发公共牌
		ShowPublicPokers(5);
		
		//新建游戏规则
		pokerhand = new PokerHandClass(newpoker.getListpokerpublicBases(), newDesk.GetDeskPlayer());
		//算牌
		pokerhand.PlayerPublicPokers();
		
		pokerhand.WinPlayer();
    }
    
    public void ShowPlayerSeat(DeskClass deskclass){
    	deskplays = deskclass.GetDeskPlayer();

    	LinearLayout.LayoutParams avatarParams =  new LinearLayout.LayoutParams(40,40); 
		
		for (int i = 0; i < deskplays.size(); i++) {
			for (Iterator iterator=deskplays.get(i).entrySet().iterator(); iterator.hasNext();) {
				Map.Entry elementEntry = (Map.Entry) iterator.next();
				//System.out.println(elementEntry.getKey()+","+elementEntry.getValue());
				try {
					LinearLayout deskRL = (LinearLayout) findViewById(GetSeatByNo(elementEntry.getKey()));
					PlayerBase playinfo = (PlayerBase) elementEntry.getValue();
					//名称
					TextView playerName = new TextView(this);
					playerName.setText(playinfo.getPlayerName());
					playerName.setTextSize(12);
					playerName.setGravity(1);
					playerName.setTextColor(Color.WHITE);
					deskRL.addView(playerName);
					//头像
					ImageView playerAvatar = new ImageButton(this);
					playerAvatar.setImageResource(R.drawable.icon);
					playerAvatar.setId((Integer) elementEntry.getKey());
					playerAvatar.setLayoutParams(avatarParams);
					//playerAvatar.setBackgroundColor(Color.GRAY);
					deskRL.addView(playerAvatar);
					//财富
					TextView playerWealth = new TextView(this);
					playerWealth.setText("$"+playinfo.getPlayerWealth()+"");
					playerWealth.setTextSize(10);
					playerWealth.setGravity(1);
					playerWealth.setTextColor(Color.YELLOW);
					deskRL.addView(playerWealth);

				
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.toString());
				}
			}
		}
    }
    
    public void ShowPlayerPoker(DeskClass deskclass){
		deskplays = deskclass.GetDeskPlayer();
		LinearLayout.LayoutParams avatarParams =  new LinearLayout.LayoutParams(45,60); 
		for (int i = 0; i < deskplays.size(); i++) {
			for (Iterator iterator=deskplays.get(i).entrySet().iterator(); iterator.hasNext();) {
				try {
					Map.Entry elementEntry = (Map.Entry) iterator.next();
					//System.out.println(elementEntry.getKey()+","+elementEntry.getValue());
					PlayerBase playinfo = (PlayerBase) elementEntry.getValue();
					List<PokerClassBase> pClassBases = playinfo.getPlayerPoker();
					LinearLayout pokerLL = (LinearLayout) findViewById(GetPokerByNo(elementEntry.getKey()));
					pokerLL.removeAllViews();
					String pokerString = "";
					for (int j = 0; j < pClassBases.size(); j++) {
						//System.out.println(pClassBases.get(j).getpoker()+pClassBases.get(j).getcolor()+",");
						//pokerString += pClassBases.get(j).getpoker()+pClassBases.get(j).getcolor();
						String pokerimageString = pClassBases.get(j).getpoker();
						String colorimageString = pClassBases.get(j).getcolor();

						ImageView pokerImageView = new ImageView(this);
						Field field=R.drawable.class.getField(ChangeColor(colorimageString)+pokerimageString);    
						int imagedrawid= field.getInt(new R.drawable()); 
						pokerImageView.setImageResource(imagedrawid);
						pokerImageView.setLayoutParams(avatarParams);
						pokerImageView.setPadding(0, 0, 0, 1);
						pokerLL.addView(pokerImageView);
					}
					//牌
//					TextView playerName = new TextView(this);
//					playerName.setText(pokerString);
//					playerName.setTextSize(10);
//					playerName.setGravity(1);
//					playerName.setTextColor(Color.WHITE);
//					pokerLL.addView(playerName);
				}
				catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.toString());
				}
			}
		}		
    }
    
    /**
     * 发并显示公共牌，num最大为5
     * @param num
     */
    public void ShowPublicPokers(int num){
    	publicpokers = new ArrayList<PokerClassBase>();
    	if(num<=5){
	    	if(num>1){
	    		for (int i = 0; i < num; i++) {
	    			publicpokers = newpoker.AddPokerPublic();
				}
	    	}else{
	    		publicpokers = newpoker.AddPokerPublic();
	    	}
	    	LinearLayout.LayoutParams ppParams =  new LinearLayout.LayoutParams(45,60); 
	    	publicpoker.removeAllViews();
	    	for (int i = 0; i < publicpokers.size(); i++) {
	    		PokerClassBase addpp = publicpokers.get(i);
	    		
//				TextView publicpokerTextView = new TextView(this);
//				publicpokerTextView.setText(addpp.getpoker()+addpp.getcolor());
//				publicpokerTextView.setTextSize(10);
//				publicpokerTextView.setGravity(1);
//				publicpokerTextView.setLayoutParams(ppParams);
//				publicpokerTextView.setTextColor(Color.WHITE);
//				publicpoker.addView(publicpokerTextView);
				
				String pokerimageString = addpp.getpoker();
				String colorimageString = addpp.getcolor();
				//System.out.println("poker:"+pokerimageString+",  color:"+colorimageString);
				try {
					ImageView pokerImageView = new ImageView(this);
					Field field=R.drawable.class.getField(ChangeColor(colorimageString)+pokerimageString);    
					int imagedrawid= field.getInt(new R.drawable()); 
					pokerImageView.setImageResource(imagedrawid);
					pokerImageView.setLayoutParams(ppParams);
					pokerImageView.setPadding(0, 0, 0, 1);
					publicpoker.addView(pokerImageView);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
    	}
    }
    
    public int GetSeatByNo(Object key){
    	int seat = (Integer) key;
    	switch (seat) {
		case 1:
			return R.id.seat1;
		case 2:
			return R.id.seat2;
		case 3:
			return R.id.seat3;
		case 4:
			return R.id.seat4;
		case 5:
			return R.id.seat5;
		case 6:
			return R.id.seat6;
		case 7:
			return R.id.seat7;
		case 8:
			return R.id.seat8;
		case 9:
			return R.id.seat9;
		default:
			return R.id.seat1;
		}
    }
    
    public int GetPokerByNo(Object key){
    	int seat = (Integer) key;
    	switch (seat) {
		case 1:
			return R.id.poker1;
		case 2:
			return R.id.poker2;
		case 3:
			return R.id.poker3;
		case 4:
			return R.id.poker4;
		case 5:
			return R.id.poker5;
		case 6:
			return R.id.poker6;
		case 7:
			return R.id.poker7;
		case 8:
			return R.id.poker8;
		case 9:
			return R.id.poker9;
		default:
			return R.id.poker1;
		}
    }
    
    public void Initialize(){
    	desk = (RelativeLayout) findViewById(R.id.desk);
    	publicpoker = (LinearLayout) findViewById(R.id.publicpoker);
    	newgame = (Button) findViewById(R.id.newgame);
    	
    	newgame.setOnClickListener(new newgameOnClickListener());
    }
    
    public class newgameOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			StartGame();
		}
    	
    }
    
    public String ChangeColor(String color){
    	String colorimageString ="";
		if(color=="1"){
			colorimageString="a";
		}else if(color=="2"){
			colorimageString="b";
		}else if(color=="3"){
			colorimageString="c";
		}else {
			colorimageString="d";
		}
    	return colorimageString;
    }
}