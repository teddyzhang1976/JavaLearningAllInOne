package YanMoDesignPattern.simplefactory.example4;

/**
 * �����࣬��������Api��
 */
public class Factory {
	/**
	 * ����Ĵ���Api�ķ��������ݿͻ��˵Ĳ����������ӿ�
	 * @param type �ͻ��˴����ѡ����ӿڵ�����
	 * @return ����õ�Api����
	 * in this way, client may need to know a little detail about 
	 * Impl. But the advantage to do this is that in future, one day
	 * if you get impl3, all you need is change code here and notice
	 * client that you have a new impl he/she can use it by input 3 for 
	 * factory method.
	 */
	public static Api createApi(int type){
		//�����typeҲ���Բ����ⲿ���룬����ֱ�Ӷ�ȡ�����ļ�����ȡ
		//Ϊ�˰�ע��������ģʽ�����ϣ�����Ͳ�ȥд��ȡ�����ļ��Ĵ�����
		
		//����type������ѡ�񣬵�Ȼ�����1��2Ӧ�����ɳ���
		
		Api api = null;
		if(type==1){
			api = new Impl();
		}else if(type==2){
			api = new Impl2();
		}
		return api;
	}
}