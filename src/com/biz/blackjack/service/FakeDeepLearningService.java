package com.biz.blackjack.service;

import java.util.Map;
import java.util.Set;

import com.biz.blackjack.domain.FakeDeepLearningVO;
import com.biz.blackjack.domain.PlayerVO;

public class FakeDeepLearningService {
	public static void initFDL() {
		//12~16까지 셋팅
		for(int i=12;i<16;i++) {
			FakeDeepLearningVO fdpVO=new FakeDeepLearningVO(i);
			BlackJackServiceImp.getFdlVO().put(i, fdpVO);
		}
	}//end
	public  static void processFDPL(int key) {
		if (key>=16) return;
		if(key>11) {
			FakeDeepLearningVO fdpVO=BlackJackServiceImp.getFdlVO().get(key);
			if(BlackJackServiceImp.getPlayerVO().isbBust()) {
				int intLose=fdpVO.getLose();
				fdpVO.setLose(intLose+1);
				return;
			}
			int intSafe=fdpVO.getSafe();
			fdpVO.setSafe(intSafe+1);
		}
	}//end
}
