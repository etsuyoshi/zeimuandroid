package com.zeimu.zeimu03.comp.sneky;

import java.io.Serializable;

public class ResultData implements Serializable{
	//����
	private int sumRightAnswer;
	
	//���듚�z��
	private boolean judgeArray[];
	
	public ResultData(int qNoSum){
		sumRightAnswer = 0;
		judgeArray = new boolean[qNoSum];
	}
	
	public void setJudgement(int qNo, boolean judgement){
		judgeArray[qNo] = judgement;
		sumRightAnswer = this.calcSumRightAnswer();
	}
	
	public int  getSumRightAnswer(){
		return this.sumRightAnswer;
	}
	public boolean[] getJudgeArray(){
		return this.judgeArray;
	}
	public boolean getJudgeArray(int qNo){
		return this.judgeArray[qNo];
	}
	
	private int calcSumRightAnswer(){
		//����judgeArray�z��̒���true�̌����J�E���g
		int tempCount = 0;
		for(int i = 0;i<this.judgeArray.length;i++){
			if(this.judgeArray[i]){
				tempCount ++;
			}
		}
		return tempCount;
	}
	
	
}
