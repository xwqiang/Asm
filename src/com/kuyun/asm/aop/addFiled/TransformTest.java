package com.kuyun.asm.aop.addFiled;

import java.lang.reflect.Field;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;

import com.kuyun.asm.aop.mod.Person;

public class TransformTest {
	public void addAge() throws Exception {
		ClassReader classReader = new ClassReader(Type.getInternalName(Person.class));
		ClassWriter classWriter = new ClassWriter(true);
		ClassAdapter classAdapter = new Transform(classWriter);

		classReader.accept(classAdapter, true);

		byte[] classFile = classWriter.toByteArray();

		GeneratorClassLoader classLoader = new GeneratorClassLoader();
		System.out.println(Person.class.getName());
		Class clazz = classLoader.defineClassFromClassFile(
				Person.class.getName(), classFile);
		Object obj = clazz.newInstance();
		
//		Field f = clazz.getDeclaredField("age");
//		f.setAccessible(true);
//		System.out.println(f.get(obj));//----(1)
		System.out.println(clazz.getDeclaredField("age").get(obj));//----(2)
	}
	public static void main(String[] a) throws Exception{
		TransformTest t = new TransformTest();
		t.addAge();
	}
}
