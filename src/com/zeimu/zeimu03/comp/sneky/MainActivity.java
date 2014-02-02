package com.zeimu.zeimu03.comp.sneky;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        TextView tvTitle = (TextView)findViewById(R.id.tx_title);
//        tvTitle.setText("��s�Ɩ�����Ŗ��R��\n");
//        
//        TextView tvSub = (TextView)findViewById(R.id.tx_sub_title);
//        tvSub.setText("presented by ���Z�o�ϋ��猤����");
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    public void onClick(View v){
    	if (((Button)v).getId()==R.id.bt_start){
    		System.out.println("pressed start!!");
    		//execute!!

    		Intent intent = new Intent(MainActivity.this, SelectStoryPractice.class);
    		//����ʂ̃A�N�e�B�r�e�B�N��
    		startActivity(intent);

    		
    	}else if(((Button)v).getId()==R.id.bt_exit){
    		System.out.println("exit");
    		
    		new AlertDialog.Builder(this)
			.setTitle("�����ꂳ�܂ł����I�I")
			.setMessage("�I�����܂����H")
			.setPositiveButton("�I��",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					/* �������̏��� */
	    			System.out.println("���ԍ����Ō�ɒB���A�I����I�������̂ŏ͑I����ʂɖ߂�܂�");
	    			//exit!
	    			finish();
				}
			})
			.setNegativeButton("������", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int whichButton){
					/* �������̏��� */
					System.out.println("�������������������܂����I");
					//do nothing!
				}
			})
			.show();
    	}
    }

    
}
