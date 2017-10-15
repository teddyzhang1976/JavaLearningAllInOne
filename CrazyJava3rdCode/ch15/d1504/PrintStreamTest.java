package CrazyJava3rdCode.ch15.d1504;


import java.io.*;
/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class PrintStreamTest
{
	public static void main(String[] args)
	{
		try(
			FileOutputStream fos = new FileOutputStream("test.txt");
			PrintStream ps = new PrintStream(fos))
		{
			// ʹ��PrintStreamִ�����
			ps.println("��ͨ�ַ���");
			// ֱ��ʹ��PrintStream�������
			ps.println(new PrintStreamTest());
			System.out.println(new PrintStreamTest());
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
