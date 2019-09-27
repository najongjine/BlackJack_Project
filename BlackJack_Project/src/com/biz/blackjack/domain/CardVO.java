package com.biz.blackjack.domain;

public class CardVO {
	private String name="";
	private int value=0;
	
	public CardVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CardVO(String name, int value) {
		super();
		this.name = name;
		this.value = value;
	}
	@Override
	public String toString() {
		return "CardsVO [name=" + name + ", value=" + value + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

}
