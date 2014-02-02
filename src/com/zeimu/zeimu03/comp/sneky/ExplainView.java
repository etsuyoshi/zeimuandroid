package com.zeimu.zeimu03.comp.sneky;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.StringTokenizer;




public class ExplainView extends Activity implements OnClickListener {
	private StringTokenizer st;
	private String line2[];
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	System.out.println("explain constructor start!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explain_view3);
        
        System.out.println("complete setcontent");
        Intent intent = getIntent();
        String givenData = intent.getStringExtra("explainView");
        //givenData��#�Ńg�[�N����������K�v������
        
        System.out.println("complete reading givenData");
        try{
        	st = new StringTokenizer(givenData, "#");
	        
	        String line[] = new String[100];
	        //#�g�[�N���ŕ�������
	        int cnt = 0;
	        while(st.hasMoreTokens()){
	        	line[cnt]=st.nextToken();
	        	cnt++;
	        }
	        //�������ꂽ�g�[�N���̐������̗v�f����������z����쐬���A�������ꂽ�g�[�N�����i�[
	        line2 = new String[cnt];
	        System.arraycopy(line, 0, line2, 0, cnt);
	        
        }catch(Exception e){
        	System.out.println("������͕\���y�[�W�Ńg�[�N�������G���[���������܂����B");
        	System.out.println(e);
        }
        
        System.out.println("complete reconstructing givenData");
        //�Ƃ肠�����͉�������ꊇ�\��
        TextView tv = (TextView)findViewById(R.id.explain_text);
        String out = new String();
        out = "";
        for(int i = 0;i<line2.length;i++){
        	out += "\n" + line2[i];
        }
        tv.setText(out);
        //tv.setText(line2[0] + "\n" + line2[1]);
        //tv.setText(givenData);
        System.out.println("complete constructor!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    public void onClick(View v){
    	if (((Button)v).getId()==R.id.explain_exit){
    		System.out.println("������\���y�[�W�̖߂�{�^����������܂���");
    		finish();
    		return;
    	}
    	/*
    	if(((Button)v).getId()==R.id.explain_next){
    		System.out.println("���փ{�^����������܂����B");
    		//storymodel01�����̖���qNo�Ƃ��ĕ\��������͓̂���H
    	}
    	*/
    }
}
