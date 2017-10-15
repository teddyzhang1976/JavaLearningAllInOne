package CrazyJava3rdCode.ch06.d9;



/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class GenderTest
{
	public static void main(String[] args)
	{
		// 通过Enum的valueOf()方法来获取指定枚举类的枚举值
		//Gender g = Enum.valueOf(Gender.class , "FEMALE");
		Gender g = Gender.FEMALE;
		// 直接为枚举值的name实例变量赋值
		g.name = "女";
		// 直接访问枚举值的name实例变量
		System.out.println(g + "代表:" + g.name);
	}
}
