package com.biz.blackjack.service;

import java.util.Map;
import java.util.Set;

import com.biz.blackjack.domain.FakeDeepLearningVO;
import com.biz.blackjack.domain.PlayerVO;

public class FakeDeepLearningService {
	public static void initFDL() { //카드값 12~15를 키 로 간주함. 딥러닝 하게 초기화 하는 함수
		//12~15까지 셋팅
		for(int i=12;i<16;i++) {
			FakeDeepLearningVO fdpVO=new FakeDeepLearningVO(i);
			BlackJackServiceImp.getFdlVO().put(i, fdpVO);
		}
	}//end
	public  static void processFDPL(int key) {// 얘가 bust 안날때까지 카드 뽑을때, 안전했다 || 버스트났다 횟수 기록 하는얘임
		if (key>=16) return; //과제 명세서에 16 이상은 안된다함.
		if(key>11) {
			FakeDeepLearningVO fdpVO=BlackJackServiceImp.getFdlVO().get(key);
			if(BlackJackServiceImp.getPlayerVO().isbBust()) {
				int intLose=fdpVO.getLose();
				fdpVO.setLose(intLose+1); //버스트 났으면 intLose 올림
				return;
			}
			int intSafe=fdpVO.getSafe();
			fdpVO.setSafe(intSafe+1); //버스트 안났으면 intSafe 올림
		}
	}//end
}
