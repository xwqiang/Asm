package com.kuyun.asm.aop.time;

import java.lang.reflect.Method;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.kuyun.asm.aop.mod.Person;
import com.kuyun.shadowNet.matics.MatricsUtil;

public class MyModifyMethod extends MethodAdapter {

	public MyModifyMethod(MethodVisitor mv, int access, String name, String desc) {
		super(mv);
	}

	@Override
	public void visitCode() {
		mv.visitFieldInsn(Opcodes.GETSTATIC,
				Type.getInternalName(Person.class), "timer", "J");
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
				"currentTimeMillis", "()J");
		mv.visitInsn(Opcodes.LSUB);
		mv.visitFieldInsn(Opcodes.PUTSTATIC,
				Type.getInternalName(Person.class), "timer", "J");
	}

	@Override
	public void visitInsn(int opcode) {
		if (opcode == Opcodes.RETURN) {
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
					"Ljava/io/PrintStream;");
			mv.visitFieldInsn(Opcodes.GETSTATIC,
					Type.getInternalName(Person.class), "timer", "J");
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
					"currentTimeMillis", "()J");
			mv.visitInsn(Opcodes.LADD);
			mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
					"println", "(J)V");
//			mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Type.getInternalName(MatricsUtil.class),
//					"regist", Type.getMethodDescriptor(m));
		}
		mv.visitInsn(opcode);
		
		
//		Method m = null;
//		try {
//			m = MatricsUtil.class.getMethod("regist", String.class,String.class);
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		}  
//		mv.visitFieldInsn(Opcodes.GETSTATIC, Type.getInternalName(MatricsUtil.class), "regist",
//				Type.getMethodDescriptor(m));
	}
}