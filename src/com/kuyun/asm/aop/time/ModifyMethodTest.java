package com.kuyun.asm.aop.time;

import java.io.File;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;

import com.kuyun.asm.aop.addFiled.GeneratorClassLoader;
import com.kuyun.asm.aop.mod.Person;

public class ModifyMethodTest {
	public void modiySleepMethod() throws Exception {
		ClassReader classReader = new ClassReader(Person.class.getName());
		ClassWriter classWriter = new ClassWriter(true);
		ClassAdapter classAdapter = new ModifyMethodClassAdapter(classWriter);
		classReader.accept(classAdapter, true);

		byte[] classFile = classWriter.toByteArray();

		GeneratorClassLoader classLoader = new GeneratorClassLoader();
		@SuppressWarnings("rawtypes")
		Class clazz = classLoader.defineClassFromClassFile(
				Person.class.getName(), classFile);
		Object obj = clazz.newInstance();
		System.out.println(clazz.getDeclaredField("name").get(obj));
		clazz.getDeclaredMethod("sleep").invoke(obj);
		

		// 将这个类输出到原先的类文件目录下，这是原先的类文件已经被修改
		File file = new File("D:/a.class");
		FileOutputStream stream = new FileOutputStream(file);
		stream.write(classFile);
		stream.close();
		
	}
	public static void main(String[] a) throws Exception{
		ModifyMethodTest t = new ModifyMethodTest();
		t.modiySleepMethod();
	}
}
