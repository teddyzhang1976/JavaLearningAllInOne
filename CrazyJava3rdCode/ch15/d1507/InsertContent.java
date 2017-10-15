package CrazyJava3rdCode.ch15.d1507;


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
public class InsertContent
{
	public static void insert(String fileName , long pos
		, String insertContent) throws IOException
	{
		File tmp = File.createTempFile("tmp" , null);
		tmp.deleteOnExit();
		try(
			RandomAccessFile raf = new RandomAccessFile(fileName , "rw");
			// ʹ����ʱ�ļ�����������������
			RandomAccessFile tmpInOut = new RandomAccessFile(tmp,"rw");)
		//	FileOutputStream tmpOut = new FileOutputStream(tmp);
		//	FileInputStream tmpIn = new FileInputStream(tmp))
		{
			raf.seek(pos);
			// ------������뽫����������ݶ�����ʱ�ļ��б���------
			byte[] bbuf = new byte[64];
			// ���ڱ���ʵ�ʶ�ȡ���ֽ���
			int hasRead = 0;
			// ʹ��ѭ����ʽ��ȡ�����������
			while ((hasRead = raf.read(bbuf)) > 0 )
			{
				// ����ȡ������д����ʱ�ļ�
				tmpInOut.write(bbuf , 0 , hasRead);
			}
			// ----------��������������----------
			// ���ļ���¼ָ�����¶�λ��posλ��
			raf.seek(pos);
			// ׷����Ҫ���������
			raf.write(insertContent.getBytes());
			// ׷����ʱ�ļ��е�����
			while ((hasRead = tmpInOut.read(bbuf)) > 0 )
			{
				raf.write(bbuf , 0 , hasRead);
			}
		}
	}
	public static void main(String[] args)
		throws IOException
	{
		insert("d:/InsertContent.java" , 15 , "���������\r\n");
	}
}

