package Casino;

public class DeskSeatClass{
	
	private String seatName;
	private int seatNo;
	//private int seatPostionX;
	//private int seatPostionY;
	
	public DeskSeatClass(String name,int seatno){
		this.seatName = name;
		this.seatNo = seatno;
		//this.seatPostionX = x;
		//this.seatPostionY = y;
	}
	
//	public int getSeatPostionX() {
//		return seatPostionX;
//	}
//
//	public void setSeatPostionX(int seatPostionX) {
//		this.seatPostionX = seatPostionX;
//	}
//
//	public int getSeatPostionY() {
//		return seatPostionY;
//	}
//
//	public void setSeatPostionY(int seatPostionY) {
//		this.seatPostionY = seatPostionY;
//	}

	public String GetSeatName(){
		return this.seatName;
	}
	
	public int GetSeatNo(){
		return this.seatNo;
	}
}