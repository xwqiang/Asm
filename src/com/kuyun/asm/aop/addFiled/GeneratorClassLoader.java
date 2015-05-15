package com.kuyun.asm.aop.addFiled;

public class GeneratorClassLoader extends ClassLoader {

	public Class defineClassFromClassFile(String className, byte[] classFile)
			throws ClassFormatError {
		return defineClass(className, classFile, 0, classFile.length);
	}
}