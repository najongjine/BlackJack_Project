package com.biz.blackjack;

import java.util.Scanner;

import com.biz.blackjack.service.BlackJackServiceImp;

public class BlackJackEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlackJackServiceImp bs=new BlackJackServiceImp();
		int maxPlayerNum=1;
		Scanner scan=new Scanner(System.in);
		
		System.out.println(bs.toStringPlayer());
		for(int i=0,turnOfWhichPerson=0;;turnOfWhichPerson=(++i)%(maxPlayerNum+1)) {//+1은 딜러 포함
			if(turnOfWhichPerson==1) {//dealer turn
				System.out.println();
				System.out.println("딜러의 턴 입니다.");
				bs.checkForceHit_Dealer();
				//bs.decideAI();
				System.out.println("딜러의 턴이 끝났습니다.");
				System.out.println();
			}
			else {//player turn
				System.out.println();
				System.out.printf("%s 의 턴입니다.",BlackJackServiceImp.getPlayerVO().getName());
				System.out.print("1.hit  2. stay  3.open  >>");
				int intMenu=Integer.valueOf(scan.nextInt());
				switch (intMenu) {
				case 1:
					bs.hit(BlackJackServiceImp.getPlayerVO());
					System.out.println(bs.toStringPlayer());
					break;
				case 2:
					bs.stay();
					break;
				case 3:
					System.out.println("카드를 오픈합니다.");
					bs.open();
					return;
				default:
					System.out.println("입력하신 값이 잘못되었습니다.");
					i--;
					break;
				}
			}
		}
	}

}
