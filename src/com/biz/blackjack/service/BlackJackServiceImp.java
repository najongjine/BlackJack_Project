package com.biz.blackjack.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.biz.blackjack.domain.CardVO;
import com.biz.blackjack.domain.DealerVO;
import com.biz.blackjack.domain.FakeDeepLearningVO;
import com.biz.blackjack.domain.PlayerVO;

public class BlackJackServiceImp {
	private static DealerVO dealerVO=null;
	private static PlayerVO playerVO=null;
	private static List<CardVO> cardLists=null;
	private static Map<Integer, FakeDeepLearningVO> fdlVO;
	private int lastCardIndex=0;
	
	public BlackJackServiceImp() {
		super();
		// TODO Auto-generated constructor stub
		cardLists=new ArrayList<CardVO>();
		fdlVO=new TreeMap<Integer, FakeDeepLearningVO>();
		playerVO=new PlayerVO();
		dealerVO=new DealerVO();
		init();
		FakeDeepLearningService.initFDL();
	}
	public void init() {
		playerVO.getCardList1().clear();
		dealerVO.getCardList1().clear();
		cardLists.clear();
		playerVO.setbBust(false);
		playerVO.setCardSetValue(0);
		playerVO.setbLose(false);
		dealerVO.setbBust(false);
		dealerVO.setbShouldHit(true);
		dealerVO.setCardSetValue(0);
		
		CardVO vo;
		vo=new CardVO("Spade2", 2);
		cardLists.add(vo);
		vo=new CardVO("Spade3", 3);
		cardLists.add(vo);
		vo=new CardVO("Spade4", 4);
		cardLists.add(vo);
		vo=new CardVO("Spade5", 5);
		cardLists.add(vo);
		vo=new CardVO("Spade6", 6);
		cardLists.add(vo);
		vo=new CardVO("Spade7", 7);
		cardLists.add(vo);
		vo=new CardVO("Spade8", 8);
		cardLists.add(vo);
		vo=new CardVO("Spade9", 9);
		cardLists.add(vo);
		vo=new CardVO("Spade10", 10);
		cardLists.add(vo);
		vo=new CardVO("SpadeJack",10);
		cardLists.add(vo);
		vo=new CardVO("SpadeQueen", 10);
		cardLists.add(vo);
		vo=new CardVO("SpadeKing", 10);
		cardLists.add(vo);
		vo=new CardVO("SpadeAce", 1);
		cardLists.add(vo);
		
		vo=new CardVO("Clover2", 2);
		cardLists.add(vo);
		vo=new CardVO("Clover3", 3);
		cardLists.add(vo);
		vo=new CardVO("Clover4", 4);
		cardLists.add(vo);
		vo=new CardVO("Clover5", 5);
		cardLists.add(vo);
		vo=new CardVO("Clover6", 6);
		cardLists.add(vo);
		vo=new CardVO("Clover7", 7);
		cardLists.add(vo);
		vo=new CardVO("Clover8", 8);
		cardLists.add(vo);
		vo=new CardVO("Clover9", 9);
		cardLists.add(vo);
		vo=new CardVO("Clover10", 10);
		cardLists.add(vo);
		vo=new CardVO("CloverJack", 10);
		cardLists.add(vo);
		vo=new CardVO("CloverQueen", 10);
		cardLists.add(vo);
		vo=new CardVO("CloverKing", 10);
		cardLists.add(vo);
		vo=new CardVO("CloverAce", 1);
		cardLists.add(vo);
		
		vo=new CardVO("Heart2", 2);
		cardLists.add(vo);
		vo=new CardVO("Heart3", 3);
		cardLists.add(vo);
		vo=new CardVO("Heart4", 4);
		cardLists.add(vo);
		vo=new CardVO("Heart5", 5);
		cardLists.add(vo);
		vo=new CardVO("Heart6", 6);
		cardLists.add(vo);
		vo=new CardVO("Heart7", 7);
		cardLists.add(vo);
		vo=new CardVO("Heart8", 8);
		cardLists.add(vo);
		vo=new CardVO("Heart9", 9);
		cardLists.add(vo);
		vo=new CardVO("Heart10", 10);
		cardLists.add(vo);
		vo=new CardVO("HeartJack", 10);
		cardLists.add(vo);
		vo=new CardVO("HeartQueen", 10);
		cardLists.add(vo);
		vo=new CardVO("HeartKing", 10);
		cardLists.add(vo);
		vo=new CardVO("HeartAce", 1);
		cardLists.add(vo);
		
		vo=new CardVO("Dia2", 2);
		cardLists.add(vo);
		vo=new CardVO("Dia3", 3);
		cardLists.add(vo);
		vo=new CardVO("Dia4", 4);
		cardLists.add(vo);
		vo=new CardVO("Dia5", 5);
		cardLists.add(vo);
		vo=new CardVO("Dia6", 6);
		cardLists.add(vo);
		vo=new CardVO("Dia7", 7);
		cardLists.add(vo);
		vo=new CardVO("Dia8", 8);
		cardLists.add(vo);
		vo=new CardVO("Dia9", 9);
		cardLists.add(vo);
		vo=new CardVO("Dia10", 10);
		cardLists.add(vo);
		vo=new CardVO("DiaJack", 10);
		cardLists.add(vo);
		vo=new CardVO("DiaQueen", 10);
		cardLists.add(vo);
		vo=new CardVO("DiaKing", 10);
		cardLists.add(vo);
		vo=new CardVO("DiaAce", 1);
		cardLists.add(vo);
		lastCardIndex=cardLists.size()-1;
		Collections.shuffle(cardLists);
		
		playerVO.setName("Me");
		playerVO.getCardList1().add(cardLists.get(lastCardIndex--));
		playerVO.getCardList1().add(cardLists.get(lastCardIndex--));
		calculate(playerVO);
		
		dealerVO.setName("Dealer");
		dealerVO.getCardList1().add(cardLists.get(lastCardIndex--));
		dealerVO.getCardList1().add(cardLists.get(lastCardIndex--));
		calculate(dealerVO);
		checkForceHit_Dealer();
	}
	public String toStringPlayer() {
		String playerInfo=playerVO.getName()+"의 카드[";
		for(CardVO cardVO:playerVO.getCardList1()) {
			playerInfo+=String.format("%s, ", cardVO.getName());
		}
		playerInfo+="]";
		return playerInfo;
	}
	public String toStringDealer() {
		String playerInfo=dealerVO.getName()+"의 카드[";
		for(CardVO cardVO:dealerVO.getCardList1()) {
			playerInfo+=String.format("%s, ", cardVO.getName());
		}
		playerInfo+="]";
		return playerInfo;
	}
	
	public static Map<Integer, FakeDeepLearningVO> getFdlVO() {
		return fdlVO;
	}
	public static void setFdlVO(Map<Integer, FakeDeepLearningVO> fdlVO) {
		BlackJackServiceImp.fdlVO = fdlVO;
	}
	public static DealerVO getDealerVO() {
		return dealerVO;
	}
	public static void setDealerVO(DealerVO dealerVO) {
		BlackJackServiceImp.dealerVO = dealerVO;
	}
	public static PlayerVO getPlayerVO() {
		return playerVO;
	}
	public static void setPlayerVO(PlayerVO playerVO) {
		BlackJackServiceImp.playerVO = playerVO;
	}
	public static List<CardVO> getCardLists() {
		return cardLists;
	}
	public static void setCardLists(List<CardVO> cardLists) {
		BlackJackServiceImp.cardLists = cardLists;
	}
	
	public void calculate(PlayerVO PlayerVO) {
		int _value1=0;
		
		if(PlayerVO.getCardList1().size()<=0) {
			System.out.printf("플레이어 %s는 아직 아무 카드도 가지고 있지 않습니다\n",PlayerVO.getName());
			return;
		}
		
		for(int i=0;i<PlayerVO.getCardList1().size();i++) {
			_value1+=PlayerVO.getCardList1().get(i).getValue();
		}
		PlayerVO.setCardSetValue(_value1);
		checkIsBust();
	}
	public void calculate(DealerVO dealerVO) {
		int _value1=0;
		
		if(dealerVO.getCardList1().size()<=0) {
			System.out.printf("플레이어 %s는 아직 아무 카드도 가지고 있지 않습니다\n",dealerVO.getName());
			return;
		}
		
		for(int i=0;i<dealerVO.getCardList1().size();i++) {
			_value1+=dealerVO.getCardList1().get(i).getValue();
		}
		dealerVO.setCardSetValue(_value1);
		checkIsBust();
		
	}
	public void hit(PlayerVO playerVO) {
		if(lastCardIndex<=0) return;
		
		playerVO.getCardList1().add(cardLists.get(lastCardIndex--));
		//cardLists.remove(lastCardIndex--);
		calculate(playerVO);
			
	}//end hit
	public void hit(DealerVO dealerVO) {
		if(checkIsCardListsEmpty()) return;
		
		dealerVO.getCardList1().add(cardLists.get(lastCardIndex--));
		//cardLists.remove(lastCardIndex--);
		calculate(dealerVO);
			
	}//end hit
	public boolean checkIsCardListsEmpty() {
		if(cardLists.size()<=0) {
			System.out.println("더이상 뽑을수 있는 카드가 없습니다.");
			return true;
		}
		return false;
	}//end check card empty
	public boolean checkIsBust() {
		if(playerVO==null) return false;
		if(playerVO.getCardSetValue()>21) {
			playerVO.setbBust(true);
			//System.out.printf("%s 는 총 카드 값이 21을 넘어서 지셨습니다.\n",playerVO.getName());
			//System.out.printf("%s 의 승리입니다.\n",dealerVO.getName());
			return true;
		}
		if(dealerVO==null) return false;
		if(dealerVO.getCardSetValue()>21) {
			dealerVO.setbBust(true);
			//System.out.printf("%s 는 총 카드 값이 21을 넘어서 지셨습니다.\n",dealerVO.getName());
			//System.out.printf("%s 의 승리입니다.\n",playerVO.getName());
			return true;
		}
		return false;
		
	}//end checkbust
	public void stay() {
		
	}//end stay
	public void checkForceHit_Dealer() {
		if(dealerVO.getCardSetValue()>16) {
			return;
		}
		hit(dealerVO);
		calculate(dealerVO);
		System.out.println("딜러가 카드 한장을 뽑았습니다.");
	}//end forcehit
	public void checkWinner() {
		if(playerVO.isbBust()==true && dealerVO.isbBust()==true) {
			System.out.println("무승부 입니다.");
			playerVO.setbLose(false);
			return;
		}
		else if(playerVO.isbBust()==true && dealerVO.isbBust()==false) {
			System.out.println("딜러의 승 입니다.");
			playerVO.setbLose(true);
			return;
		}
		else if(playerVO.isbBust()==false && dealerVO.isbBust()==true) {
			System.out.printf("%s 의 승 입니다.",playerVO.getName());
			playerVO.setbLose(false);
			return;
		}
		int playerValDiff=21-playerVO.getCardSetValue();
		int dealderVallDiff=21-dealerVO.getCardSetValue();
		if(playerValDiff<dealderVallDiff) {
			System.out.printf("%s 의 승 입니다.",playerVO.getName());
			playerVO.setbLose(false);
			return;
		}
		else if(playerValDiff>dealderVallDiff) {
			System.out.println("딜러의 승 입니다.");
			playerVO.setbLose(true);
			return;
		}
		else {
			System.out.println("무승부 입니다.");
		}
	}//end check winner
	public boolean open() {
		toStringDealer();
		toStringPlayer();
		checkWinner();
		return true;
		
	}//end open
	public boolean decideAI_V1() {
		if(dealerVO.getCardSetValue()>16) {
			dealerVO.setbShouldHit(false);
			return false;
		}
		hit(dealerVO);
		return true;
	}
}
