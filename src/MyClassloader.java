import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MyClassloader extends ClassLoader {

	
	public MyClassloader(){
		super();
	}
	
	public Class loadByte(byte[] classByte){
		return defineClass(classByte, 0, classByte.length);
	}
	
	public static void main(String[] args) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		File file = new File("F:/Test.class");
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[200];
		int len;
		while(-1 != (len = fis.read(buffer))){
			baos.write(buffer,0,len);
		}
		MyClassloader loader = new MyClassloader();
		Class classs = loader.loadByte(baos.toByteArray());
		System.out.println(classs.getClassLoader().getClass().getName());
		System.out.println(MyClassloader.class.getClassLoader().getClass().getName());
		Method method = classs.getMethod("hello", null);
		method.invoke(classs.newInstance(), null);
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					Class classs = Class.forName("Test");
					Method method = classs.getMethod("hello", null);
					method.invoke(classs.newInstance(), null);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
}
