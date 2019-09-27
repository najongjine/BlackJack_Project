package com.biz.blackjack.domain;

public class FakeDeepLearningVO {
	private int value=0;
	private int safe=0;
	private int lose=0;
	public FakeDeepLearningVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FakeDeepLearningVO(int value, int safe, int lose) {
		super();
		this.value = value;
		this.safe = safe;
		this.lose = lose;
	}
	public FakeDeepLearningVO(int value) {
		super();
		this.value = value;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Integer getSafe() {
		return safe;
	}
	public void setSafe(int safe) {
		this.safe = safe;
	}
	public Integer getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	@Override
	public String toString() {
		return "FakeDeepLearningVO [value=" + value + ", safe=" + safe + ", lose=" + lose + "]";
	}
	
}
