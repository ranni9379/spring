package com.springStudy1.DTO;

public class TestA {
	private TestB tb;
	private TestC tc;
	
	public void makeObject2(TestC tc) {
		//약한 결합
		this.tc = tc;
	}
	
	public void makeObject1() {
		// 강한결합
		this.tb= new TestB(); 
		//TestB라는 클래스가 필요함, 기본생성자가 있어야함.
	}
	public void makeObject1(int num, String name) {
		this.tb= new TestB(num, name);
	}
	public void makeObject(int num) {
		this.tb = new TestB(num);
	}

}
