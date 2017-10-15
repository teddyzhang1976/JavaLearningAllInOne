package CrazyJava3rdCode.ch06.d9.interfacedir;



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
public enum Gender implements GenderDesc
{
	// �˴���ö��ֵ������ö�Ӧ������������
	MALE("��")
	// �����Ų���ʵ������һ�����岿��
	{
		public void info()
		{
			System.out.println("���ö��ֵ��������");
		}
	},
	FEMALE("Ů")
	{
		public void info()
		{
			System.out.println("���ö��ֵ����Ů��");
		}
	};
	// ����������codes\06\6.9\best\Gender.java�е�Gender����ȫ��ͬ
	private final String name;
	// ö����Ĺ�����ֻ��ʹ��private����
	private Gender(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	// ���������info()������ʵ��GenderDesc�ӿڱ���ʵ�ֵķ���
	public void info()
	{
		System.out.println(
			"����һ���������ڶ����Ա��ö����");
	}
}