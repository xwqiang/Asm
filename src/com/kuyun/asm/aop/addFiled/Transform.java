package com.kuyun.asm.aop.addFiled;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class Transform extends ClassAdapter {

	public Transform(ClassVisitor cv) {
		super(cv);
	}

	@Override
	public void visitEnd() {
		cv.visitField(Opcodes.ACC_PUBLIC, "age", Type.getDescriptor(int.class),
				null, null);
	}

}