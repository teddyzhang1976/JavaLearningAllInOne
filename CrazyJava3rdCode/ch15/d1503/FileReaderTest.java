package CrazyJava3rdCode.ch15.d1503;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
public class FileReaderTest
{
	public static void main(String[] args)
	{
		File fs=new File(".");
		
		//System.out.println(fs.getAbsolutePath());
		System.out.println(fs.getAbsoluteFile().getParent());
		System.out.println(FileReaderTest.class.getPackage().getName());
		try(
			// �����ַ�������
			
				
			FileReader fr = new FileReader(fs.getAbsoluteFile().getParent()+'/'+FileReaderTest.class.getPackage().getName().replaceAll("\\.", "/")+"/FileReaderTest.java"))
		{
			// ����һ������Ϊ32�ġ���Ͳ��
			char[] cbuf = new char[32];
			// ���ڱ���ʵ�ʶ�ȡ���ַ���
			int hasRead = 0;
			// ʹ��ѭ�����ظ���ȡˮ������
			while ((hasRead = fr.read(cbuf)) > 0 )
			{
				// ȡ������Ͳ����ˮ�Σ��ַ��������ַ�����ת�����ַ������룡
				System.out.print(new String(cbuf , 0 , hasRead));
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
