package com.zeimu.zeimu03.comp.sneky;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import java.text.DecimalFormat;

import android.content.Context;
/**
 * �S��I����ɐ��듚�̗������i�[
 * ->�S��I���ゾ����rData�ɐ��뗚�����i�[����Ă��邽�߁A�����XML�ɕۑ�����
 * �i�[����f�[�^�F�͔ԍ��A���ԍ��A���𐔁A�듚��
 * @author oqo52025257
 *
 */
public class AnswerContext {
	/**
	 * XML�t�@�C����p���ăf�[�^��ۑ��A�擾����
	 */
	private SharedPreferences pref;
	private ResultData rData;
	private String aaa;
	private String sectNoXXX;
	private String quesNoXXX;
	
	
	public AnswerContext(Context context){
		this.pref = PreferenceManager.getDefaultSharedPreferences(context);
		this.rData = rData;
	}
	public void inputXML(ResultData rData, int sectNo){
		DecimalFormat df = new DecimalFormat("000");
		sectNoXXX = df.format(sectNo);
		
		//SharedPreferences�ɏ͔ԍ��A���ԍ��̐��𐔁A�듚���A���i�[
		SharedPreferences.Editor editor = pref.edit();
		
		//���X�i�[����Ă�������
		long originRightAnswerNum = 0;
		//�V�����i�[����鐳��
		long rightAnswerNum = 0;
		
		//���X�i�[����Ă����듚��
		long originWrongAnswerNum = 0;
		//�V�����i�[�����듚��
		long wrongAnswerNum = 0;
		
		//���I�������S���ɂ��āA���������̂ƌ�������̂ɕ����āAXML�ɉ񐔂�ǉ�
		for(int qNo = 0; qNo < (rData.getJudgeArray()).length;qNo++){
			quesNoXXX = df.format(qNo);
			
			//System.out.print("answerContext : " + qNo);
			if(rData.getJudgeArray(qNo)){
				//System.out.println(" => " + true);
				//System.out.println(pref.getString("sect" + sData.getSectNo(),""));
				originRightAnswerNum = Long.parseLong(pref.getString("sect" + sectNoXXX + "ques" + quesNoXXX + "right", 0 + ""));
				rightAnswerNum = originRightAnswerNum + 1;
				editor.putString("sect" + sectNoXXX + "ques" + quesNoXXX + "right", rightAnswerNum + "");
				
				System.out.println("xml;" + "sect" + sectNoXXX + "ques" + quesNoXXX + "right" + " = " + 
							 pref.getString("sect" + sectNoXXX + "ques" + quesNoXXX + "right", 0 + ""));
			}else{
				System.out.println(" => " + false);
				//�듚�����i�[
				originWrongAnswerNum = Long.parseLong(pref.getString("sect" + sectNoXXX + "ques" + quesNoXXX + "wrong", 0 + ""));
				wrongAnswerNum = originWrongAnswerNum + 1;
				editor.putString("sect" + sectNoXXX + "ques" + quesNoXXX + "wrong", wrongAnswerNum + "");
			}
		}
		editor.commit();
		
		//test
		System.out.println("xml;" + "sect" + sectNoXXX + "ques" + quesNoXXX + "right" + " = " + 
					 pref.getString("sect" + sectNoXXX + "ques" + quesNoXXX + "right", 0 + ""));
	}
	
	
	public String getString(){
		return this.aaa + this.rData.getSumRightAnswer();
	}
	
	public long outputXML(int sectNo, int quesNo, boolean RightWrong){
		DecimalFormat df = new DecimalFormat("000");
		String sectNoXXX = df.format(sectNo);
		String quesNoXXX = df.format(quesNo); 
		if(RightWrong){//���𐔂����߂�ꍇ
			return Long.parseLong(pref.getString("sect" + sectNoXXX + "ques" + quesNoXXX + "right", 0 + ""));
		}else{//�듚�������߂�ꍇ
			return Long.parseLong(pref.getString("sect" + sectNoXXX + "ques" + quesNoXXX + "wrong", 0 + ""));
		}
	}
	
}
