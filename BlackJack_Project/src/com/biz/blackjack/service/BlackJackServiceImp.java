package com.biz.blackjack.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.biz.blackjack.domain.BlackJackVars;
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
	public static String fdlFile="src/com/biz/blackjack/fdpl.txt";
	
	public BlackJackServiceImp() throws Exception {
		super();
		// TODO Auto-generated constructor stub
		cardLists=new ArrayList<CardVO>();
		fdlVO=new TreeMap<Integer, FakeDeepLearningVO>();
		playerVO=new PlayerVO();
		dealerVO=new DealerVO();
		
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
		init();
		FakeDeepLearningService.initFDL();
		readFDL_Data();
	}
	public void init() {
		playerVO.getCardList1().clear();
		dealerVO.getCardList1().clear();
		//cardLists.clear();
		playerVO.setbBust(false);
		playerVO.setCardSetValue(0);
		playerVO.setbLose(false);
		dealerVO.setbBust(false);
		dealerVO.setbShouldHit(true);
		dealerVO.setCardSetValue(0);
		if(dealerVO.getIntLostBCZNumWasLow()<0) dealerVO.setIntLostBCZNumWasLow(0);
		if(dealerVO.getIntNumOfBust()<0) dealerVO.setIntNumOfBust(0);
		if(dealerVO.getIntNumOfLost()<0) dealerVO.setIntNumOfLost(0);
		
		lastCardIndex=cardLists.size()-1;
		Collections.shuffle(cardLists);
		
		
		System.out.println("\n=================블랙잭 게임을 시작합니다==================");
		playerVO.setName("Me");
		playerVO.getCardList1().add(cardLists.get(lastCardIndex--));
		playerVO.getCardList1().add(cardLists.get(lastCardIndex--));
		calculate(playerVO);
		checkIsBust();
		
		dealerVO.setName("Dealer");
		dealerVO.getCardList1().add(cardLists.get(lastCardIndex--));
		dealerVO.getCardList1().add(cardLists.get(lastCardIndex--));
		calculate(dealerVO);
		checkIsBust();
		//checkForceHit_Dealer();
	}
	public String toStringPlayer() {
		String playerInfo=playerVO.getName()+"의 카드[";
		for(CardVO cardVO:playerVO.getCardList1()) {
			playerInfo+=String.format("%s, ", cardVO.getName());
		}
		playerInfo+="]";
		playerInfo+=" "+this.playerVO.getCardSetValue();
		return playerInfo;
	}
	public String toStringDealer() {
		String playerInfo=dealerVO.getName()+"의 카드[";
		for(CardVO cardVO:dealerVO.getCardList1()) {
			playerInfo+=String.format("%s, ", cardVO.getName());
		}
		playerInfo+="]";
		playerInfo+=" "+this.dealerVO.getCardSetValue();
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
	
	public void calculate(PlayerVO playerVO) {
		int _value1=0;
		
		if(this.playerVO.getCardList1().size()<=0) {
			System.out.printf("플레이어 %s는 아직 아무 카드도 가지고 있지 않습니다\n",this.playerVO.getName());
			return;
		}
		
		for(int i=0;i<this.playerVO.getCardList1().size();i++) {
			_value1+=this.playerVO.getCardList1().get(i).getValue();
		}
		this.playerVO.setCardSetValue(_value1);
		checkIsBust();
	}
	public void calculate(DealerVO dealerVO) {
		int _value1=0;
		
		if(this.dealerVO.getCardList1().size()<=0) {
			System.out.printf("플레이어 %s는 아직 아무 카드도 가지고 있지 않습니다\n",dealerVO.getName());
			return;
		}
		
		for(int i=0;i<this.dealerVO.getCardList1().size();i++) {
			_value1+=this.dealerVO.getCardList1().get(i).getValue();
		}
		this.dealerVO.setCardSetValue(_value1);
		
		checkIsBust();
		
	}
	public void hit(PlayerVO playerVO) {
		if(lastCardIndex<0) {
			System.out.println("더이상 뽑을수 있는 카드가 없습니다!!");
			return;
		}
		
		this.playerVO.getCardList1().add(cardLists.get(lastCardIndex--));
		//cardLists.remove(lastCardIndex--);
		calculate(this.playerVO);
		checkIsBust();
		//System.out.println(toStringPlayer());
			
	}//end hit
	public void hit(PlayerVO playerVO, boolean bVar) {
		if(lastCardIndex<0) {
			System.out.println("더이상 뽑을수 있는 카드가 없습니다!!");
			return;
		}
		
		this.playerVO.getCardList1().add(cardLists.get(lastCardIndex--));
		//cardLists.remove(lastCardIndex--);
		calculate(this.playerVO);
		checkIsBust();
		int lastIndex=this.playerVO.getCardList1().size()-1;
		FakeDeepLearningService.processFDPL(this.playerVO.getCardSetValue()-this.playerVO.getCardList1().get(lastIndex).getValue());
		System.out.println(toStringPlayer());
			
	}//end hit
	public void hit(DealerVO dealerVO) {
		if(lastCardIndex<0) {
			System.out.println("더이상 뽑을수 있는 카드가 없습니다!!");
			return;
		}
		
		this.dealerVO.getCardList1().add(cardLists.get(lastCardIndex--));
		calculate(this.dealerVO);
		checkIsBust();
		
		int _currentValue=dealerVO.getCardSetValue();
		int currentCardSize=dealerVO.getCardList1().size();
		int _previousValue=_currentValue-dealerVO.getCardList1().get(currentCardSize-1).getValue();
		if(_previousValue>=15 && dealerVO.isbBust()==true) {
			dealerVO.setIntNumOfBust(dealerVO.getIntNumOfBust()+1);
		}
		else if(_previousValue>=15 && dealerVO.isbBust()==false) {
			dealerVO.setIntNumOfBust(dealerVO.getIntNumOfBust()-1);
			if(dealerVO.getIntNumOfBust()<0) dealerVO.setIntNumOfBust(0);
		}
			
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
		BlackJackVars.intStay++;
	}//end stay
	public void checkForceHit_Dealer() {
		calculate(dealerVO);
		calculate(playerVO);
		if(dealerVO.getCardSetValue()>16) {
			return;
		}
		hit(dealerVO);
		calculate(dealerVO);
		System.out.println("딜러가 카드 한장을 뽑았습니다.");
	}//end forcehit
	public void checkWinner() {
		if(playerVO.getCardSetValue()>21 && dealerVO.getCardSetValue()>21) {
			System.out.println("무승부 입니다.");
			playerVO.setbLose(false);
			return;
		}
		else if(playerVO.getCardSetValue()>21 && dealerVO.getCardSetValue()<22) {
			System.out.println("딜러의 승 입니다.");
			playerVO.setbLose(true);
			if(dealerVO.getIntNumOfLost()<0) return;
			dealerVO.setIntNumOfLost(dealerVO.getIntNumOfLost()-1);
			return;
		}
		else if(playerVO.getCardSetValue()<22 && dealerVO.getCardSetValue()>21) {
			System.out.printf("%s 의 승 입니다.\n",playerVO.getName());
			playerVO.setbLose(false);
			dealerVO.setIntNumOfLost(dealerVO.getIntNumOfLost()+1);
			return;
		}
		int playerValDiff=21-playerVO.getCardSetValue();
		int dealderVallDiff=21-dealerVO.getCardSetValue();
		if(playerValDiff<dealderVallDiff) {
			System.out.printf("%s 의 승 입니다.\n",playerVO.getName());
			playerVO.setbLose(false);
			dealerVO.setIntNumOfLost(dealerVO.getIntNumOfLost()+1);
			if(checkIsValueWasLow(dealerVO)) dealerVO.setIntLostBCZNumWasLow(dealerVO.getIntLostBCZNumWasLow()+1);
			return;
		}
		else if(playerValDiff>dealderVallDiff) {
			System.out.println("딜러의 승 입니다.");
			playerVO.setbLose(true);
			if(dealerVO.getIntNumOfLost()<0) return;
			dealerVO.setIntNumOfLost(dealerVO.getIntNumOfLost()-1);
			if(checkIsValueWasLow(dealerVO)) {
				dealerVO.setIntLostBCZNumWasLow(dealerVO.getIntLostBCZNumWasLow()-1);
				if(dealerVO.getIntLostBCZNumWasLow()<0) dealerVO.setIntLostBCZNumWasLow(0);
			}
			return;
		}
		else {
			System.out.println("무승부 입니다.");
		}
	}//end check winner
	public boolean open() {
		System.out.println("-----------------------------------------------");
		System.out.println(toStringDealer());
		System.out.println(toStringPlayer());
		checkWinner();
		System.out.println("--------------------------------------------------");
		init();
		return true;
		
	}//end open
	public boolean decideAI_V1(DealerVO dealerVO) {
		calculate(dealerVO);
		calculate(playerVO);
		if(lastCardIndex<0) {
			System.out.println("더이상의 남아있는 카드가 없습니다!!");
			return false;
		}
		if(this.dealerVO.getCardSetValue()>=16) {
			this.dealerVO.setbShouldHit(false);
			return false;
		}
		if(this.dealerVO.getCardSetValue()<=11) {
			hit(this.dealerVO);
			return true;
		}
		int key=this.dealerVO.getCardSetValue();
		if(key>12 && key <16) {
			int _lose=fdlVO.get(key).getLose();
			int _safe=fdlVO.get(key).getSafe();
			if(_safe>=_lose) {
				hit(this.dealerVO);
				return true;
			}
		}
		return false;
	}
	public boolean decideAI_V2(DealerVO dealerVO) {
		calculate(dealerVO);
		calculate(playerVO);
		if(lastCardIndex<0) {
			System.out.println("더이상의 남아있는 카드가 없습니다!!");
			return false;
		}
		if(this.dealerVO.getCardSetValue()>=16) {
			this.dealerVO.setbShouldHit(false);
			return false;
		}
		if(this.dealerVO.getCardSetValue()<=11) {
			hit(this.dealerVO);
			return true;
		}
		else if(this.dealerVO.getCardSetValue()<playerVO.getCardSetValue() && dealerVO.getCardSetValue()<21){
			hit(this.dealerVO);
			return true;
		}
		int key=this.dealerVO.getCardSetValue();
		if(key>12 && key <16) {
			int _lose=fdlVO.get(key).getLose();
			int _safe=fdlVO.get(key).getSafe();
			if(_safe>=_lose||(this.dealerVO.getCardSetValue()<playerVO.getCardSetValue() && dealerVO.getCardSetValue()<21)) {
				hit(this.dealerVO);
				return true;
			}
		}
		return false;
	}//end
	public boolean decideAI_V3(DealerVO dealerVO) {
		calculate(dealerVO);
		calculate(playerVO);
		int intLeftOverCard=lastCardIndex+1;
		int totalCardSize=cardLists.size();
		int maxPlayer=BlackJackVars.maxPlayerNum + 1;
		int intNumOfCardsAtStart=totalCardSize- (maxPlayer*2);
		
		//요건 패배횟수로 패널티값 부여 + 총 플레이어 인원수 + 남아있는 카드값 + 내가 가진 총 카드값 패널티
		double dBonus=(double)(121.0-BlackJackVars.maxPlayerNum-dealerVO.getIntNumOfLost()+(dealerVO.getIntLostBCZNumWasLow()*3)//값이 15였을때 stay시 시면 보너스 점수를 올리는데 부스트를 주기위해 *3 함
				-(15.0-dealerVO.getCardSetValue())-(intNumOfCardsAtStart-intLeftOverCard))/100.0;
		
		//모순발견!! 버스트패널티 내리는 조건을 못찾음!! 요건 bust횟수로 패널티값 부여 + 총 플레이어 인원수 + 남아있는 카드값 + 내가 가진 총 카드값 패널티
		//double dBonus=(double)(121-BlackJackVars.maxPlayerNum-dealerVO.getIntNumOfBust()
			//	-(15-dealerVO.getCardSetValue())-(intNumOfCardsAtStart-intLeftOverCard))/100.0;
		if(lastCardIndex<0) {
			System.out.println("더이상의 남아있는 카드가 없습니다!!");
			return false;
		}
		if(this.dealerVO.getCardSetValue()>=16) {
			this.dealerVO.setbShouldHit(false);
			return false;
		}
		if(this.dealerVO.getCardSetValue()<=11) {
			this.dealerVO.setbShouldHit(true);
			hit(this.dealerVO);
			return true;
		}
		int key=this.dealerVO.getCardSetValue();
		if(key<12 || key >15) {
			return false;
		}
		int _lose=fdlVO.get(key).getLose();
		int _safe=fdlVO.get(key).getSafe();
		
		boolean _bCardVlaueGRT6=false;//딜러의 카드중 7보다 큰 숫자 있냐
		for(CardVO vo:dealerVO.getCardList1()) {//딜러의 카드 목록중 6보다 큰 숫자 가졌다 확인
			if(vo.getValue()>6) {
				_bCardVlaueGRT6=true;
				break;
			}
		}//end GRT6
		if(key>=15  && _bCardVlaueGRT6==true) {
			//딜러의 전체 카드값이 15이상이고, 딜러의 카드중 6보다 큰 값이 있을경우
			if(checkIsBust()) {
				if(this.dealerVO.getIntNumOfBust()<=0) {
					this.dealerVO.setIntNumOfBust(0);
				}
				else{
					this.dealerVO.setIntNumOfBust(this.dealerVO.getIntNumOfBust()+1);
				}
			}
			_safe=(int)(_safe*dBonus);
		}
		if(_safe>=_lose) {//hit 할지말지 결정!!
			this.dealerVO.setbShouldHit(true);
			hit(this.dealerVO);
			return true;
		}
		return false;
	}
	public boolean decideAI_V1(PlayerVO playerVO) {
		calculate(dealerVO);
		calculate(this.playerVO);
		if(this.playerVO.getCardSetValue()>16) {
			this.playerVO.setbShouldHit(false);
			return false;
		}
		if(this.playerVO.getCardSetValue()<=11) {
			this.playerVO.setbShouldHit(true);
			hit(this.playerVO,true);
			return true;
		}
		//카드값 확인후 버스트 아니면 또 힛
		if(this.playerVO.isbBust()==false) {
			hit(this.playerVO,true);
			return true;
		}
		return false;
	}
	public boolean cheatAI(DealerVO dealerVO) {
		if(this.dealerVO.getCardSetValue()>=21) return false;
		if(this.dealerVO.getCardSetValue()<21) {
			if(this.dealerVO.getCardSetValue()+cardLists.get(lastCardIndex).getValue()<=21) {
				hit(this.dealerVO);
				return true;
			}
		}
		return false;
	}
	public void readFDL_Data() throws Exception {
		Set<Integer> keys=fdlVO.keySet();
		FileReader fr=new FileReader(fdlFile);
		BufferedReader buffer=new BufferedReader(fr);
		while(true) {
			String reader=buffer.readLine();
			if(reader==null) break;
			String[] _str=reader.split(":");
			int _value=Integer.valueOf(_str[0]);
			int _safe=Integer.valueOf(_str[1]);
			int _lose=Integer.valueOf(_str[2]);
			fdlVO.get(_value).setLose(_lose);
			fdlVO.get(_value).setSafe(_safe);
		}
		buffer.close();
		fr.close();
		//System.out.println(fdlVO.toString());
	}
	public boolean checkIsValueWasLow(DealerVO dealerVO) {
		calculate(dealerVO);
		checkIsBust();
		boolean valueWaslow=dealerVO.getCardSetValue()<16;
				//&& dealerVO.getIntNumOfLost()>0;
		//dealerVO.setIntLostBCZNumWasLow(dealerVO.getIntLostBCZNumWasLow()+1);
		return valueWaslow;
	}
}
