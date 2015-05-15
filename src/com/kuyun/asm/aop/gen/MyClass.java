package com.kuyun.asm.aop.gen;


public class MyClass {
	private String name;
	
	public MyClass(){
		this.name = "zhangzhuo";
	}

	public String getName() throws SecurityException, NoSuchMethodException {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}