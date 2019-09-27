package com.biz.blackjack;

import java.util.Scanner;

import com.biz.blackjack.domain.BlackJackVars;
import com.biz.blackjack.service.BlackJackServiceImp;

public class BlackJackEx01 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BlackJackServiceImp bs=new BlackJackServiceImp();
		int maxPlayerNum=1;
		BlackJackVars.maxPlayerNum=maxPlayerNum;
		Scanner scan=new Scanner(System.in);
		
		int turnOfWhichPerson=0;
		for(int i=0;;i++) {
			if(BlackJackVars.intStay>=BlackJackVars.maxPlayerNum+1) {//open
				bs.open();
				BlackJackVars.intStay=0;
				//System.out.println("----------------------------");
				i=0;
				continue;
			}
			turnOfWhichPerson=i%(BlackJackVars.maxPlayerNum+1);//+1은 딜러 포함
			if(turnOfWhichPerson==BlackJackVars.DEALER) {//dealer turn
				System.out.println();
				System.out.println("딜러의 턴 입니다.");
				bs.decideAI_V3(BlackJackServiceImp.getDealerVO());
				System.out.println("딜러의 턴이 끝났습니다.");
				System.out.println();
			}
			else {//player turn
				System.out.println();
				if(BlackJackVars.intStay>=BlackJackVars.maxPlayerNum+1) {//open
					bs.open();
					BlackJackVars.intStay=0;
					//System.out.println("----------------------------");
					i=0;
					continue;
				}
				System.out.println("딜러가 카진 카드의 갯수: "+BlackJackServiceImp.getDealerVO().getCardList1().size());
				System.out.println(bs.toStringPlayer());
				System.out.printf("%s 의 턴입니다.",BlackJackServiceImp.getPlayerVO().getName());
				System.out.print("1.hit  2. stay (0=종료, stay 두번 선택하면 open) >>");
				int intMenu=Integer.valueOf(scan.nextInt());
				switch (intMenu) {
				case 0:
					System.out.println("================게임 종료==================");
					return;
				case 1:
					bs.hit(BlackJackServiceImp.getPlayerVO());
					System.out.println(bs.toStringPlayer());
					break;
				case 2:
					bs.stay();
					break;
				default:
					System.out.println("입력하신 값이 잘못되었습니다.");
						i--;
					break;
				}
			}
		}
	}

}
