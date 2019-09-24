package com.biz.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class DealerVO {
	private String name="";
	private int cardSetValue=0;
	private boolean bBust=false;
	private boolean bShouldHit=true;
	private List<CardVO> cardList1=null;
	public DealerVO() {
		super();
		// TODO Auto-generated constructor stub
		cardList1=new ArrayList<CardVO>();
	}
	public DealerVO(String name, int cardSetValue, List<CardVO> cardList1) {
		super();
		this.name = name;
		this.cardSetValue = cardSetValue;
		this.cardList1 = cardList1;
	}
	@Override
	public String toString() {
		return "DealerVO [name=" + name + ", cardSetValue=" + cardSetValue + ", cardList1=" + cardList1 + "]";
	}
	
	public boolean isbShouldHit() {
		return bShouldHit;
	}
	public void setbShouldHit(boolean bShouldHit) {
		this.bShouldHit = bShouldHit;
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
