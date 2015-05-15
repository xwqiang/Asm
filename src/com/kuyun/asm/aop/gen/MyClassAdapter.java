package com.kuyun.asm.aop.gen;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class MyClassAdapter extends ClassAdapter {

	public MyClassAdapter(ClassVisitor cv) {
		super(cv);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		MethodVisitor methodVisitor = cv.visitMethod(access, name, desc,
				signature, exceptions);
		if (name.equals("<init>")) {
			return new InitMethodAdapter(methodVisitor);
		} else if (name.equals("setName")) {
			return new SetMethodAdapter(methodVisitor);
		} else if (name.equals("getName")) {
			return new GetMethodAdapter(methodVisitor);
		} else {
			return super.visitMethod(access, name, desc, signature, exceptions);
		}
	}

        //这个类生成具体的构造方法字节码
	class InitMethodAdapter extends MethodAdapter {
		public InitMethodAdapter(MethodVisitor mv) {
			super(mv);
		}

		@Override
		public void visitCode() {
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object",
					"<init>", "()V");//调用父类的构造方法
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitLdcInsn("zhangzhuo");//将常量池中的字符串常量加载刀栈顶
			mv.visitFieldInsn(Opcodes.PUTFIELD,
					"org/victorzhzh/core/classes/MyClass", "name",
					Type.getDescriptor(String.class));//对name属性赋值
			mv.visitInsn(Opcodes.RETURN);//设置返回值
			mv.visitMaxs(2, 1);//设置方法的栈和本地变量表的大小
		}
	};

class SetMethodAdapter extends MethodAdapter {
		public SetMethodAdapter(MethodVisitor mv) {
			super(mv);
		}

		@Override
		public void visitCode() {
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitFieldInsn(Opcodes.PUTFIELD,
					"org/victorzhzh/core/classes/MyClass", "name",
					Type.getDescriptor(String.class));
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(2, 2);
		}

	}

       
         //这个类生成具体的getName方法字节
      class GetMethodAdapter extends MethodAdapter {

		public GetMethodAdapter(MethodVisitor mv) {
			super(mv);
		}

		@Override
		public void visitCode() {
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitFieldInsn(Opcodes.GETFIELD,
					"org/victorzhzh/core/classes/MyClass", "name",
					Type.getDescriptor(String.class));//获取name属性的值
			mv.visitInsn(Opcodes.ARETURN);//返回一个引用，这里是String的引用即name
			mv.visitMaxs(1, 1);
		}
	}
}
