package com.kuyun.asm.aop.time;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ModifyMethodClassAdapter extends ClassAdapter {

	public ModifyMethodClassAdapter(ClassVisitor cv) {
		super(cv);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		if (name.equals("sleep")) {
			return new ModifyMethod(super.visitMethod(access, name, desc,
					signature, exceptions), access, name, desc);
		}
		return super.visitMethod(access, name, desc, signature, exceptions);
	}

	@Override
	public void visitEnd() {
		cv.visitField(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, "timer", "J",
				null, null);
	}

}