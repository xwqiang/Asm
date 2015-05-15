package com.kuyun.asm.aop.gen;

import java.lang.reflect.Method;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class GenerateClass {
	public void generateClass() {

                //方法的栈长度和本地变量表长度用户自己计算
				ClassWriter classWriter = new ClassWriter(true);	
	
                //Opcodes.V1_6指定类的版本
                //Opcodes.ACC_PUBLIC表示这个类是public，
                //“org/victorzhzh/core/classes/MyClass”类的全限定名称
                //第一个null位置变量定义的是泛型签名，
                //“java/lang/Object”这个类的父类
                //第二个null位子的变量定义的是这个类实现的接口
				classWriter.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC,
				"org/victorzhzh/core/classes/MyClass", null,
				"java/lang/Object", null);
               
		ClassAdapter classAdapter = new MyClassAdapter(classWriter);

		classAdapter.visitField(Opcodes.ACC_PRIVATE, "name",
				Type.getDescriptor(String.class), null, null);//定义name属性

		classAdapter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null,
				null).visitCode();//定义构造方法

		String setMethodDesc = "(" + Type.getDescriptor(String.class) + ")V";
		classAdapter.visitMethod(Opcodes.ACC_PUBLIC, "setName", setMethodDesc,
				null, null).visitCode();//定义setName方法

		String getMethodDesc = "()" + Type.getDescriptor(String.class);
		classAdapter.visitMethod(Opcodes.ACC_PUBLIC, "getName", getMethodDesc,
				null, null).visitCode();//定义getName方法

		byte[] classFile = classWriter.toByteArray();//生成字节码

		MyClassLoader classLoader = new MyClassLoader();//定义一个类加载器
		Class clazz = classLoader.defineClassFromClassFile(
				"org.victorzhzh.core.classes.MyClass", classFile);
		try {//利用反射方式，访问getName
			Object obj = clazz.newInstance();
			Method method = clazz.getMethod("getName");
			System.out.println(method.invoke(obj, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class MyClassLoader extends ClassLoader {
		public Class defineClassFromClassFile(String className, byte[] classFile)
				throws ClassFormatError {
			return defineClass(className, classFile, 0, classFile.length);
		}
	}

	public static void main(String[] args) {
		GenerateClass generateClass = new GenerateClass();
		generateClass.generateClass();
	}
}
