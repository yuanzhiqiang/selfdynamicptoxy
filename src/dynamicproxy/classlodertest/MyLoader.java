package dynamicproxy.classlodertest;

import java.io.*;

public class MyLoader extends ClassLoader {

	@Override
	public Class findClass(String name) throws ClassNotFoundException {
		String className = name;
		name = name.replace('.', '\\') + ".class";
		File file = new File("D:\\test\\ab", name);
		int length = (int) file.length();
		byte[] bf = new byte[length];
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			int start = 0;
			int len = length;
			while (true) {
				int i = is.read(bf, start, length);
				length -= i;
				start += i;
				if (0 == i | -1 == i) {
					break;
				}
			}
		} catch (Exception e) {
			throw new ClassNotFoundException(e.getMessage());
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				System.exit(1);
			}
		}
		return defineClass(className, bf, 0, bf.length);
	}
}