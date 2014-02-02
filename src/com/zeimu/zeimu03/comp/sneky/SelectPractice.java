package com.zeimu.zeimu03.comp.sneky;






import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;

public class SelectPractice extends Activity implements OnClickListener{
		
	@Override
	public void onCreate(Bundle savedInstanceState){
		System.out.println("SELECT_STORY_OR_PRACTICE!!");
		super.onCreate(savedInstanceState);
		System.out.println("onCreate complete!");
		setContentView(R.layout.select_practice);
		

		//�܂��J�����Ȃ̂ŁA�_�C�A���O��\�����ďI������
		new AlertDialog.Builder(this)
			.setMessage("�\���󂲂����܂���B���C�g�łł͂��g�p�ɂȂ�܂���B")
			.setCancelable(true)
			.setPositiveButton("����", 
					new  DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							finish();
							return;
						}
			})
		.show();
		return;
	}
	public void onClick(View v){
		//ToDo Auto-generated method stub
		//����ʂ̃A�N�e�B�r�e�B�I��
		if (((Button)v).getId()==R.id.s_p_bt_Q1){
			System.out.println("pressed practice1");
			Intent intent=new Intent(SelectPractice.this, PracticeModel0.class);
			startActivity(intent);
			
		}else if(((Button)v).getId()==R.id.s_p_bt_Q2){
			System.out.println("pressed practice2!->finish!");
			//���쐬
			finish();
		}else{
			System.out.println("maintainance now ...");
			
			//�H�����B�B
			new AlertDialog.Builder(SelectPractice.this)
				.setTitle("���N�A�֍��N�A���݂܂���I")
				.setMessage("maintainance now ....")
				.show();
		}
	}	
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }    
    
}
