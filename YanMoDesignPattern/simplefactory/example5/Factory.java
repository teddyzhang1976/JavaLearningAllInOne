package YanMoDesignPattern.simplefactory.example5;
import java.util.*;
import java.io.*;
/**
 * �����࣬��������Api����
 */
public class Factory {
	/**
	 * ����Ĵ���Api�ķ��������������ļ��Ĳ����������ӿ�
	 * @return ����õ�Api����
	 * Now, even Factory don't need to know the detail about Impl.
	 * In future, if you have a new impl, you only need to put it's
	 * name in the properties file.
	 * This way's shortage is that you can only fit one impl a time.
	 * and a little low effective.
	 * If you want to fit more impl, you had better use back the example4.
	 */
	public static Api createApi(){
		//ֱ�Ӷ�ȡ�����ļ�����ȡ��Ҫ����ʵ������
		
		//������ζ�ȡProperties������η�������Ͳ�������
		Properties p = new Properties(); 
		InputStream in = null;
		try {
			in = Factory.class.getResourceAsStream("FactoryTest.properties");
			p.load(in);
		} catch (IOException e) {
			System.out.println("װ�ع��������ļ������ˣ�����Ķ�ջ��Ϣ���£�");
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//�÷���ȥ��������Щ���⴦������ƵĹ�������Ͳ�����
		Api api = null;
		try {
			api = (Api)Class.forName(p.getProperty("ImplClass")).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return api;
	}
}