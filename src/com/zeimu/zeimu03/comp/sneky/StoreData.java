package com.zeimu.zeimu03.comp.sneky;

import java.io.Serializable;




public class StoreData implements Serializable{
	/**
	 * �����Resources���i�[����getResources()�œf���o�������̋@�\
	 * �����I�ɂ́A���̃N���X���œ��͂��ꂽResources�C���X�^���X�����H����
	 * �͖��̖��ɕ�������StoryModel�uX�v�N���X�Ŗ�薈�Ɏ��o���d�g�݂ɂ���
	 */
	private int sectNo;
	private String category;
	private String[] question;
	private String[] answer;
	private int[] answerNo;
	private JournalModel01 journalModel01[];
	private String[][] selection;
	private String[] explain;
	//private Resources res;
	//private BufferedReader br;
	protected StoreData(){
		this.sectNo = 0;
		this.category = null;
		this.question = null;
		this.selection = null;
		this.journalModel01 = null;
		this.explain = null;
	}
	//���ԍ�
	public int getSectNo(){
		return this.sectNo;
	}
	public void setSectNo(int val){
		this.sectNo = val;
	}
	//���J�e�S��
	public String getCategory(){
		return this.category;
	}
	public void setCategory(String cate){
		this.category = cate;
	}
	//��蕶
	public String[] getQuestion(){
		return this.question;
	}
	public String getQuestion(int row){
		return this.question[row];
	}
	public void setQuestion(String[] ques){
		this.question = ques;
	}
	//�I����
	public void setSelection(String[][] selection){
		this.selection = selection;
	}
	public String[][] getSelection(){
		return this.selection;
	}
	public String getSelection(int row, int col){
		return this.selection[row][col];
	}
	//row�Ԗڂ̎���̑I�����̌�
	public int getSelectionCnt(int row){
		return this.selection[row].length;
	}
	public int getQuestionNo(){
		return this.question.length;
	}
	
	public void setAnswer(String answer, int qNo){
		this.answer[qNo] = answer;
	}
	public void setAnswer(String[] answer){
		this.answer = answer;
	}
	public String[] getAnswer(){
		return this.answer;
	}
	
	public String getAnswer(int qNo){
		return this.answer[qNo];
	}
	
	public void setAnswerNo(int[] answerNo){
		this.answerNo = answerNo;
	}
	
	public int[] getAnswerNo(){
		return this.answerNo;
	}
	public int getAnswerNo(int qNo){
		return this.answerNo[qNo];
	}
	
	
	public void setExplain(String[] arExplain){
		this.explain = arExplain;
	}
	public void setExplain(int qNo, String inExplain){
		this.explain[qNo] = inExplain;
	}
	public String getExplain(int qNo){
		return this.explain[qNo];
	}
	public String[] getExplain(){
		return this.explain;
	}
}
