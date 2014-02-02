package com.zeimu.zeimu03.comp.sneky;



import android.app.Activity;
import android.content.Intent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.AlertDialog;

import android.content.SharedPreferences;
import java.text.DecimalFormat;

public class SelectStoryPractice extends Activity 
	implements OnClickListener
	/**,
				DialogInterface.OnClickListener,
				DialogInterface.OnCancelListener **/
	{

	//�𓚐��сA�𓚗����擾�p�̔�(�_�C�A���O�{�b�N�X���ł����g�p���Ă��Ȃ����A�����ňȉ���final�t�B�[���h���`�ł��Ȃ��O���[�o���ɂ���)
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		System.out.println("SELECT_STORY_OR_PRACTICE!!");
		super.onCreate(savedInstanceState);
		System.out.println("onCreate complete!");
		setContentView(R.layout.select_story_practice);
		
		this.pref = PreferenceManager.getDefaultSharedPreferences(this);
		this.editor = pref.edit();
		
	}
	public void onClick(View v){
		//ToDo Auto-generated method stub
		//����ʂ̃A�N�e�B�r�e�B�I��
		if (((Button)v).getId()==R.id.bt_story){
			//���쐬
			Intent intent = new Intent(SelectStoryPractice.this, SelectStory.class);
			startActivity(intent);
			
		/*
		}else if(((Button)v).getId()==R.id.bt_practice){
			System.out.println("pressed bt_practice!");

			Intent intent = new Intent(SelectStoryPractice.this, SelectPractice.class);
    		//����ʂ̃A�N�e�B�r�e�B�N��
    		startActivity(intent);
    		*/
		}else if(((Button)v).getId()==R.id.bt_record){
			//���ёI����ʂɈڍs
			Intent intent = new Intent(SelectStoryPractice.this, SelectRecord.class);
			startActivity(intent);
			
		}else if(((Button)v).getId()==R.id.bt_exit){
			System.out.println("pressed bt_exit!");
			finish();
			/**
			AlertDialog.show(SelectStoryPractice.this,
                    "Alert Test", 
                    "Hello, This is Alert Dialog.", 
                    "yes",
					SelectStoryPractice.this,
                    "no",
					SelectStoryPractice.this,
                    Boolean.TRUE,
					SelectStoryPractice.this);
			**/
			/*
			new AlertDialog.Builder(SelectStoryPractice.this)
				.setMessage("Are you sure to exit?")
				.setCancelable(false)
				.setPositiveButton("Yes", 
						new  DialogInterface.OnClickListener(){
							public void onClick(DialogInterface dialog, int id){
								SelectStoryPractice.this.finish();
							}
				})
				.setNegativeButton("No",
						new DialogInterface.OnClickListener(){
							public void onClick(DialogInterface dialog, int id){
								dialog.cancel();
					}
				})
			.show();
			*/
		}
	}	
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    

    
}
