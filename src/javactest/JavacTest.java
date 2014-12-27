package javactest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class JavacTest {

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class javac = Class.forName("com.sun.tools.javac.Main");
		Method method = javac.getMethod("main", new Class[]{String[].class});
		System.out.println(method.getName());
		System.out.println(method.getParameterTypes()[0]);
		String[] arg = new String[]{"C:\\Users\\jjjsyjiao\\Desktop\\Test.java"};
		method.invoke(javac.newInstance(), new Object[]{new String[]{"C:\\Users\\jjjsyjiao\\Desktop\\Test.java"}});
	}
}
