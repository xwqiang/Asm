package com.kuyun.asm.aop.mod;

import java.util.concurrent.TimeUnit;

public class Person {
	public String name;

	public void sayName() {
		System.out.println(name);
	}
	public void sleep() {
	    try {
			System.out.println("我要睡一会...");
			TimeUnit.SECONDS.sleep(2);
	    } catch (InterruptedException e) {
	    	e.printStackTrace();
	    }
	}
}

