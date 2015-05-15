package com.kuyun.asm.aop.mod;

import java.io.File;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;

public class GenerateNewPerson {
	public static void main(String[] args) throws Exception {
		// 使用全限定名，创建一个ClassReader对象
		ClassReader classReader = new ClassReader(Type.getInternalName(Person.class));
		// 构建一个ClassWriter对象，并设置让系统自动计算栈和本地变量大小
		ClassWriter classWriter = new ClassWriter(true);

		ClassAdapter classAdapter = new GeneralClassAdapter(classWriter);

		classReader.accept(classAdapter, true);

		byte[] classFile = classWriter.toByteArray();

		// 将这个类输出到原先的类文件目录下，这是原先的类文件已经被修改
		File file = new File("E:/myeclipse/workspace/Asm/bin/com/kuyun/asm/aop/mod/Person.class");
		FileOutputStream stream = new FileOutputStream(file);
		stream.write(classFile);
		stream.close();
	}
}

