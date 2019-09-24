package com.biz.blackjack;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;

import com.biz.blackjack.service.BlackJackServiceImp;
import com.biz.blackjack.service.FakeDeepLearningService;


public class BlackJackEx02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file="src/com/biz/blackjack/fdpl.txt";
		FileWriter fileWriter;
		PrintWriter printWriter;
		
		BlackJackServiceImp bs=new BlackJackServiceImp();
		FakeDeepLearningService fdlS=new FakeDeepLearningService();
		int maxPlayerNum=1;
		Scanner scan=new Scanner(System.in);
		
		int max_loop=Integer.MAX_VALUE/2;
		System.out.println(bs.toStringPlayer());
		for(int i=0,turnOfWhichPerson=0;i<max_loop;turnOfWhichPerson=(++i)%(maxPlayerNum+1)) {//+1은 딜러 포함
			bs.init();
			if(turnOfWhichPerson==1) {//dealer turn
				System.out.println();
				System.out.println("딜러의 턴 입니다.");
				bs.checkForceHit_Dealer();
				while(bs.decideAI_V1()) {
					continue;
				}
				System.out.println("딜러의 턴이 끝났습니다.");
				System.out.println();
			}
			else {//player turn
				System.out.println();
				System.out.printf("%s 의 턴입니다.",BlackJackServiceImp.getPlayerVO().getName());
				while(true) {
					if(BlackJackServiceImp.getPlayerVO().getCardSetValue()>11) break;
					bs.hit(BlackJackServiceImp.getPlayerVO());
				}
				bs.hit(BlackJackServiceImp.getPlayerVO());
				bs.open();
				int lastIndex=BlackJackServiceImp.getPlayerVO().getCardList1().size()-1;
				fdlS.processFDPL(BlackJackServiceImp.getPlayerVO().getCardSetValue()-BlackJackServiceImp.getPlayerVO().getCardList1().get(lastIndex).getValue());
			}
		}
		Set<Integer> keys =BlackJackServiceImp.getFdlVO().keySet();
		try {
			fileWriter=new FileWriter(file);
			printWriter=new PrintWriter(fileWriter);
			for(int key:keys) {
				System.out.printf("%d: safe:%d lose:%d\n",BlackJackServiceImp.getFdlVO().get(key).getValue(),BlackJackServiceImp.getFdlVO().get(key).getSafe(),BlackJackServiceImp.getFdlVO().get(key).getLose());
				String _str=String.format("%d: safe:%d lose:%d",BlackJackServiceImp.getFdlVO().get(key).getValue(),BlackJackServiceImp.getFdlVO().get(key).getSafe(),BlackJackServiceImp.getFdlVO().get(key).getLose());
				printWriter.println(_str);
				printWriter.flush();
			}
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
