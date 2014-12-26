import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassloader extends ClassLoader {

	public MyClassloader() {
		super();
	}

	@Override
	protected Class<?> findClass(String name){
		File file = new File("/Users/yuanzq/git/selfdynamicptoxy/Test.class");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[200];
			int len;
			while (-1 != (len = fis.read(buffer))) {
				baos.write(buffer, 0, len);
			}
			MyClassloader loader = new MyClassloader();
			Class classs = loader.loadByte(baos.toByteArray());
			return classs;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Class loadByte(byte[] classByte) {
		return defineClass(classByte, 0, classByte.length);
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	public static void main(String[] args) throws IOException,
			NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			InstantiationException, InterruptedException,
			ClassNotFoundException {
		File file = new File("/Users/yuanzq/git/selfdynamicptoxy/Test.class");
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[200];
		int len;
		while (-1 != (len = fis.read(buffer))) {
			baos.write(buffer, 0, len);
		}
		MyClassloader myClassloader1 = new MyClassloader();
		
		Class classs1 = myClassloader1.loadByte(baos.toByteArray());
		System.out.println(classs1.getClassLoader().getClass().getName());
		Method method = classs1.getMethod("hello", null);
		method.invoke(classs1.newInstance(), null);
		
		MyClassloader myClassloader2 = new MyClassloader();
		Class classs2 = myClassloader2.loadByte(baos.toByteArray());
		System.out.println(classs2.getClassLoader().getClass().getName());
		Method method3 = classs2.getMethod("hello", null);
		method3.invoke(classs2.newInstance(), null);
		
		
		MyClassloader myClassloader3 = new MyClassloader();
		Class classs3 = Class.forName("Test", false, myClassloader3);
		System.out.println(classs2 == classs1);
		System.out.println(classs2 == classs3);
		System.out.println(classs1 == classs3);
//		Thread thread = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(5000);
//					Class classs = Class.forName("Test");
//					Method method = classs.getMethod("hello", null);
//					method.invoke(classs.newInstance(), null);
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				} catch (NoSuchMethodException e) {
//					e.printStackTrace();
//				} catch (SecurityException e) {
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					e.printStackTrace();
//				} catch (IllegalArgumentException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InstantiationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//		thread.start();
//		thread.join();
//		Class classs = Class.forName("Test");
//		Method method = classs.getMethod("hello", null);
	}
}
