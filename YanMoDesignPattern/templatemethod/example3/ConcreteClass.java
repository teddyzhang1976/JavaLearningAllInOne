package YanMoDesignPattern.templatemethod.example3;

import org.junit.Test;

/**
 * 具体实现类，实现原语操作
 */
public class ConcreteClass extends AbstractClass {
	public void doPrimitiveOperation1() { 
		//具体的实现
	}
	@Test
	public void doPrimitiveOperation2() { 
		//具体的实现
		System.out.println("do it");
	}
}

