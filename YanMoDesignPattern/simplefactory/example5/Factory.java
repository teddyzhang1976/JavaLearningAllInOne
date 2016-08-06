package YanMoDesignPattern.simplefactory.example5;
import java.util.*;
import java.io.*;
/**
 * 工厂类，用来创造Api对象
 */
public class Factory {
	/**
	 * 具体的创造Api的方法，根据配置文件的参数来创建接口
	 * @return 创造好的Api对象
	 * Now, even Factory don't need to know the detail about Impl.
	 * In future, if you have a new impl, you only need to put it's
	 * name in the properties file.
	 * This way's shortage is that you can only fit one impl a time.
	 * and a little low effective.
	 * If you want to fit more impl, you had better use back the example4.
	 */
	public static Api createApi(){
		//直接读取配置文件来获取需要创建实例的类
		
		//至于如何读取Properties还有如何反射这里就不解释了
		Properties p = new Properties(); 
		InputStream in = null;
		try {
			in = Factory.class.getResourceAsStream("FactoryTest.properties");
			p.load(in);
		} catch (IOException e) {
			System.out.println("装载工厂配置文件出错了，具体的堆栈信息如下：");
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//用反射去创建，那些例外处理等完善的工作这里就不做了
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