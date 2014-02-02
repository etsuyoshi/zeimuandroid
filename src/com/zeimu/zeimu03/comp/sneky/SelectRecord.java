package com.zeimu.zeimu03.comp.sneky;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.ImageButton;

public class SelectRecord extends Activity implements OnClickListener{
	//�S���R�[�h�폜�p
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private Intent intent;
	public void onCreate(Bundle savedInstanceState){

		System.out.println("SELECT_STORY_OR_PRACTICE!!");
		super.onCreate(savedInstanceState);
		System.out.println("onCreate complete!");
		setContentView(R.layout.select_record);
		
		this.pref = PreferenceManager.getDefaultSharedPreferences(this);
		this.editor = pref.edit();
		
		
	}
	
	public void onClick(View v){
		//1.���т̍폜
		//2.�듚�����̍폜
		/*
		 * ��ʂ𐶐����ĉ𓚕\���{�^���Ɛ��э폜�{�^�����쐬
		 */
		
//		if((v.toString()).substring(0,21).equals("android.widget.Button")){
//		}else if((v.toString()).substring(0,26).equals("android.widget.ImageButton")){
		if((v.toString()).substring(0,21).equals("android.widget.Button")){
			if(((Button)v).getId() == R.id.layout_st_bt_return){
				System.out.println("return");
				finish();
			}else if(((Button)v).getId() == R.id.layout_st_remove){
				new AlertDialog.Builder(this)
					.setTitle("�y���ӁI�z�𓚎��ԋy�щ𓚗������폜���܂��I")
					.setMessage("�u�͂��v��I������ƁA�ߋ��̐��ыy�ї����͏���������܂��B��낵���ł����H")
					.setPositiveButton("�͂�(�폜)",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							/* �������̏��� */
							//do nothing...
							String sectNoXXX = null;
							String quesNoXXX = null;
							DecimalFormat df = new DecimalFormat("000");
							//xml�̍��ڂ����݂��Ă����Ȃ��Ă��폜�w�������Ă��G���[�͏o�Ȃ��B
							//editor.clear().commit();//������ł����v
							editor.clear();//�S���N���A
							editor.commit();
							/*
							for(int sectNo = 0;sectNo < 99;sectNo++){
								sectNoXXX = df.format(sectNo);
								//���𐔁A�s���𐔂̗����폜
								for(int quesNo = 0;quesNo < 99;quesNo++){
									quesNoXXX = df.format(quesNo);
									editor
										.remove("sect" + sectNoXXX + "ques" + quesNoXXX + "right")
										.commit();
								}
								//�𓚃^�C���̍폜
								editor.remove("sect" + sectNoXXX + "time").commit();
								//�ߋ��̍쐬�������ԋL�^�f�[�^�̍폜
								editor.remove("sect" + sectNo).commit();
							}
							*/
							
						}
					})
					.setNegativeButton("������", new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int whichButton){
							System.out.println("�L�����Z�������B�𓚗��������т͍폜���܂���B");
						}
					})
				.show();
			}else{
		//}else if((v.toString()).substring(0,26).equals("android.widget.ImageButton")){
			//�\�����ׂ����͔̏ԍ�
				int sectNo = 0;
				switch(((Button)v).getId()){
					case R.id.layout_st_bt_Q1:
						sectNo = 1;
						break;
					case R.id.layout_st_bt_Q2:
						sectNo = 2;
						break;
	
					case R.id.layout_st_bt_Q3:
						sectNo = 3;
						break;
					case R.id.layout_st_bt_Q4:
						sectNo = 4;
						break;
	
					case R.id.layout_st_bt_Q5:
						sectNo = 5;
						break;
				}//catch
				
				this.intent = new Intent(SelectRecord.this, DisplayRecord.class);
				intent.putExtra("SectNo", sectNo);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
			}
		}
	}

}
