package com.zeimu.zeimu03.comp.sneky;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

/**
 * Activity���p�����Ă��闝�R�F
 * android�t�@�C���V�X�e��/res/raw/�����̃t�@�C����
 * �ǂݍ���ł��邽�߁B
 * @author oqo52025257
 *
 */
public class QuestSelect01 extends Activity{
	private String[] question;//��蕶
	private String[][] selection;//�I����
	private String[][] answer;//����

    public void onCreate(Bundle savedInstanceState) {
    	System.out.println("Start1!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("start2");
        System.out.println(setQuestAns());
        //setQuestAns();
    }
	
	//https://ssl.aiosl-tec.co.jp/java-start/chap06.html
	private boolean setQuestAns(){
		BufferedReader br = null;
		StringTokenizer st = null;
		Resources res = this.getResources();
		//�t�@�C���ǂݍ��ݎ��Ɉ�s���ǂݍ��ލۂ̈ꎞ���͗p�z��
		String inputLine[] = new String[50];
		System.out.println("a");
		try{
//			InputStream is = res.openRawResource(R.raw.zeimu201203_001);
			InputStream is = res.openRawResource(R.raw.zeimu01);
			br = new BufferedReader(new InputStreamReader(is));
			
			System.out.println("b");
			
			int i = 0;
			//�t�@�C���̌��������[�v���Ĕz��Ɋi�[���܂��B
			while (br.ready()){
				//��s�ǂݍ���
				String line = br.readLine();
				inputLine[i] = line;//��s���i�[
				i++;
			}
			System.out.println("c");
			//http://eprostation.jpn.org/java/jacsv.html
			for(int j = 0;j < inputLine.length;j++){
				System.out.println("d" + j);
				
				System.out.println(inputLine[j] + " = ");
				st = new StringTokenizer(inputLine[j], ",");
				while(st.hasMoreTokens());{
					System.out.print(st.nextToken() + "->");
				}
			}

			//���ڂ̓Z�N�V����(�͔ԍ�)
			
			//���ڂ͖��ԍ�
			
		}catch(FileNotFoundException e){
			System.out.println("�t�@�C����������܂���!");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("���o�̓G���[");
			e.printStackTrace();
		}catch(Exception e){
			System.out.println("Other Error Occured!");
			e.printStackTrace();
		}finally{
			try{
				br.close();
			}catch(Exception e){
				System.out.println("���o�̓G���[");
				e.printStackTrace();
			}
		}
		return false;//����Ɏ擾�ł����true��Ԃ�
	}
	
}