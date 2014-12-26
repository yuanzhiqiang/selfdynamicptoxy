package dynamicproxy.classlodertest;

public class ClassA {
	private static ClassB classB = new ClassB();

	public void setClassB(Object obj) {
		classB = (ClassB) obj;
	}

	public Object getClassB() {
		return classB;
	}
}
