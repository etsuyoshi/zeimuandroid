package com.zeimu.zeimu03.comp.sneky;

import java.io.Serializable;

public class JournalModel01 implements Serializable{
	/**
	 * �d��\�̐������ݕ�(credit)�Ǝؕ�(debit)�̔z�u
	 */
	private String[] creditName;//�ݕ����ږ���
	private String[] debitName;//�ؕ����ږ���
	private long[] creditAmount;//�ݕ����ڎc��
	private long[] debitAmount;//�ؕ����ڎc��
	
	public JournalModel01(){
		//�f�t�H���g�̔z�񒷂͂S�Ƃ���(�ő�S)
		int itemLength = 4;
		this.creditName = new String[itemLength];
		this.debitName = new String[itemLength];
		this.creditAmount = new long[itemLength];
		this.debitAmount = new long[itemLength];
	}
	
	public void setCreditName(String[] creditName){
		this.creditName = creditName;
	}
	public void setCreditName(int no, String creditName){
		System.out.println("setCreditName");
		this.creditName[no] = creditName;
	}
	
	public String[] getCreditName(){
		return this.creditName;
	}
	public String getCreditName(int no){
		return this.creditName[no];
	}
	
	public void setDebitName(String[] debitName){
		this.debitName = debitName;
	}
	public void setDebitName(int no, String debitName){
		this.debitName[no] = debitName;
	}
	public String[] getDebitName(){
		return this.debitName;
	}
	public String getDebitName(int no){
		return this.debitName[no];
	}
	
	public void setCreditAmount(long[] creditAmount){
		this.creditAmount = creditAmount;
	}
	public void setCreditAmount(int no, long creditAmount){
		this.creditAmount[no] = creditAmount;
	}
	public long[] getCreditAmount(){
		return this.creditAmount;
	}
	public long getCreditAmount(int no){
		return this.creditAmount[no];
	}
	
	public void setDebitAmount(long[] debitAmount){
		this.debitAmount = debitAmount;
	}
	public void setDebitAmount(int no, long debitAmount){
		this.debitAmount[no] = debitAmount;
	}
	public long[] getdebitAmount(){
		return this.debitAmount;
	}
	public long getDebitAmount(int no){
		return this.debitAmount[no];
	}

}
