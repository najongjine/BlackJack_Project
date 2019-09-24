package com.biz.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class PlayerVO {
	private String name="";
	private int cardSetValue=0;
	private boolean bBust=false;
	private boolean bLose=false;
	private List<CardVO> cardList1=null;
	
	public PlayerVO() {
		super();
		// TODO Auto-generated constructor stub
		cardList1=new ArrayList<CardVO>();
	}
	public PlayerVO(String name, List<CardVO> cardList1) {
		super();
		this.name = name;
		this.cardList1 = cardList1;

	}
	
	@Override
	public String toString() {
		return "PlayerVO [name=" + name + ", cardSetValue=" + cardSetValue + ", cardList1=" + cardList1 + "]";
	}
	
	public boolean isbLose() {
		return bLose;
	}
	public void setbLose(boolean bLose) {
		this.bLose = bLose;
	}
	public boolean isbBust() {
		return bBust;
	}
	public void setbBust(boolean bBust) {
		this.bBust = bBust;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCardSetValue() {
		return cardSetValue;
	}
	public void setCardSetValue(int cardSetValue) {
		this.cardSetValue = cardSetValue;
	}
	public List<CardVO> getCardList1() {
		return cardList1;
	}
	public void setCardList1(List<CardVO> cardList1) {
		this.cardList1 = cardList1;
	}

}
