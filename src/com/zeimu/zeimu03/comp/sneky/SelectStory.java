package com.zeimu.zeimu03.comp.sneky;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.app.AlertDialog;
import android.widget.Button;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Random;


public class SelectStory extends Activity implements OnClickListener{
	private StoreData sData;
	private Resources res01;
	private BufferedReader br;
	private StringTokenizer st;
	private String sectNo[];
	private String qNo[];
	private String category;
	private String question[];
	private String answer[];
	private int answerNo[];
	private String strCSV[][];//csv�t�@�C���̓��e��S���񎟌��̕�����s��ɕϊ�
	private String selection[][];
	private String explain[];
	
	private final int readingRowCount = 100;
	private final int readingColCount = 21; 

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		System.out.println("SELECT_STORY!!");
		super.onCreate(savedInstanceState);
		System.out.println("onCreate complete!");
		setContentView(R.layout.select_story);
		
		
		sectNo = new String[readingRowCount];
		qNo = new String[readingRowCount];
		category = new String();
		question = new String[qNo.length];
		answer = new String[qNo.length];
		answerNo = new int[qNo.length];
		
		selection = new String[qNo.length][5];//�I�����̌���5��
		explain = new String[qNo.length];
		
		res01 = this.getResources();
		System.out.println("selectStory���i�[����");
		
	}
	public void onClick(View v){
		/* ImageButton���g�����ꍇ�A
		 * view����xButton�ɃL���X�g���Ă��܂���ImageButton�ɒ����Ȃ��̂�
		 * �@�@�@�@����ImageButton�̔���Ɏg���Ȃ��I�I
		 */
		
		System.out.println("original view = " + v.toString());
		
		/*
		View viewImageButton = v;
		View viewButton = v;
		//view��Button�I�u�W�F�N�g�ł���ꍇ�A�ȉ��̊i�[������ۂɃG���[������
		viewImageButton = (ImageButton)viewImageButton;
		viewButton = (Button)viewButton;
		System.out.println("�Poriginal view = " + v.toString());
		System.out.println("�Pimage button = " + viewImageButton.toString());
		System.out.println("�Pbutton = " + viewButton.toString());
	 	*/
		
		
		//�ȉ�����(try-error�ő����Ă��ǂ��j
		//�Q�l�Fsubstring(start, end)�͕�������O����J�E���g����[start]�Ԗڂ���[end-1]�Ԗڂ܂ł𒊏o
		System.out.println(((v.toString())).substring(0,21));
		System.out.println("button pressed");
		if(((Button)v).getId() == R.id.s_s_bt_return){

			System.out.println("pressed finish button!");
			finish();
			return;
		}
		
		InputStream is = res01.openRawResource(R.raw.test0001);
		if(((Button)v).getId()==R.id.s_st_bt_Q1){
			System.out.println("pressed story1");
//			InputStream is = res01.openRawResource(R.raw.zeimu201203_001);
//			InputStream is = res01.openRawResource(R.raw.zeimu01);
			if (setQSA2(is, 1)){//�t�@�C���̓ǂݍ��݂ɐ���������StoreData�I�u�W�F�N�g�̃t�B�[���h��csv��񂪊i�[�����B
				System.out.println("reading csv file succeed!!");
				Intent intent=new Intent(SelectStory.this, WordModel0.class);
				System.out.println("complete initiating intent");
				intent.putExtra("StoreData", sData);//sData��setQSA���Ŋi�[����Ă���
				System.out.println("complete initiating putExtra");
				intent.setAction(Intent.ACTION_VIEW);
				System.out.println("complete initiating setAction");
				startActivity(intent);
				System.out.println("complete initiating startActivity");
			}else{
				System.out.println("reading csv file failed!!");
				//�_�C�A���O�ŕ���
				
			}
			return;
			
		}else if(((Button)v).getId()==R.id.s_st_bt_Q2){
			System.out.println("pressed story2");
//			InputStream is = res01.openRawResource(R.raw.zeimu201203_002);
//			InputStream is = res01.openRawResource(R.raw.zeimu02);
			if (setQSA2(is, 2)){//�t�@�C���̓ǂݍ��݂ɐ���������StoreData�I�u�W�F�N�g�̃t�B�[���h��csv��񂪊i�[�����B
				System.out.println("reading csv file succeed!!");
				Intent intent=new Intent(SelectStory.this, WordModel0.class);
				System.out.println("complete initiating intent");
				intent.putExtra("StoreData", sData);//sData��setQSA���Ŋi�[����Ă���
				System.out.println("complete initiating putExtra");
				intent.setAction(Intent.ACTION_VIEW);
				System.out.println("complete initiating setAction");
				startActivity(intent);
				System.out.println("complete initiating startActivity");
			}else{
				System.out.println("reading csv file failed!!");
				//�_�C�A���O�ŕ���
				
			}
			return;
			
		}else if(((Button)v).getId()==R.id.s_st_bt_Q3){
			System.out.println("pressed story3");
//			InputStream is = res01.openRawResource(R.raw.zeimu201203_003);
//			InputStream is = res01.openRawResource(R.raw.zeimu03);
			if (setQSA2(is, 3)){//�t�@�C���̓ǂݍ��݂ɐ���������StoreData�I�u�W�F�N�g�̃t�B�[���h��csv��񂪊i�[�����B
				System.out.println("reading csv file succeed!!");
				Intent intent=new Intent(SelectStory.this, WordModel0.class);
				System.out.println("complete initiating intent");
				intent.putExtra("StoreData", sData);//sData��setQSA���Ŋi�[����Ă���
				System.out.println("complete initiating putExtra");
				intent.setAction(Intent.ACTION_VIEW);
				System.out.println("complete initiating setAction");
				startActivity(intent);
				System.out.println("complete initiating startActivity");
			}else{
				System.out.println("reading csv file failed!!");
				//�_�C�A���O�ŕ���
				
			}
			return;
			
		}else if(((Button)v).getId()==R.id.s_st_bt_Q4){
			System.out.println("pressed story4");
//			InputStream is = res01.openRawResource(R.raw.zeimu04);
			if (setQSA2(is, 4)){//�t�@�C���̓ǂݍ��݂ɐ���������StoreData�I�u�W�F�N�g�̃t�B�[���h��csv��񂪊i�[�����B
				System.out.println("reading csv file succeed!!");
				Intent intent=new Intent(SelectStory.this, WordModel0.class);
				System.out.println("complete initiating intent");
				intent.putExtra("StoreData", sData);//sData��setQSA���Ŋi�[����Ă���
				System.out.println("complete initiating putExtra");
				intent.setAction(Intent.ACTION_VIEW);
				System.out.println("complete initiating setAction");
				startActivity(intent);
				System.out.println("complete initiating startActivity");
			}else{
				System.out.println("reading csv file failed!!");
				//�_�C�A���O�ŕ���
				
			}
			return;
			
		}else if(((Button)v).getId()==R.id.s_st_bt_Q5){
			System.out.println("pressed story5");
//			InputStream is = res01.openRawResource(R.raw.zeimu05);
			if (setQSA2(is, 5)){//�t�@�C���̓ǂݍ��݂ɐ���������StoreData�I�u�W�F�N�g�̃t�B�[���h��csv��񂪊i�[�����B
				System.out.println("reading csv file succeed!!");
				Intent intent=new Intent(SelectStory.this, WordModel0.class);
				System.out.println("complete initiating intent");
				intent.putExtra("StoreData", sData);//sData��setQSA���Ŋi�[����Ă���
				System.out.println("complete initiating putExtra");
				intent.setAction(Intent.ACTION_VIEW);
				System.out.println("complete initiating setAction");
				startActivity(intent);
				System.out.println("complete initiating startActivity");
			}else{
				System.out.println("reading csv file failed!!");
				//�_�C�A���O�ŕ���
			}
			return;
			
		}else if(((Button)v).getId()==R.id.s_st_bt_Q6){
			System.out.println("pressed story6");
//			InputStream is = res01.openRawResource(R.raw.zeimu06);
			if (setQSA2(is, 6)){//�t�@�C���̓ǂݍ��݂ɐ���������StoreData�I�u�W�F�N�g�̃t�B�[���h��csv��񂪊i�[�����B
				System.out.println("reading csv file succeed!!");
				Intent intent=new Intent(SelectStory.this, WordModel0.class);
				System.out.println("complete initiating intent");
				intent.putExtra("StoreData", sData);//sData��setQSA���Ŋi�[����Ă���
				System.out.println("complete initiating putExtra");
				intent.setAction(Intent.ACTION_VIEW);
				System.out.println("complete initiating setAction");
				startActivity(intent);
				System.out.println("complete initiating startActivity");
			}else{
				System.out.println("reading csv file failed!!");
				//�_�C�A���O�ŕ���
			}
			return;
			
		}
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }    
    
    
	public boolean setQSA2(InputStream is, int noAllowedSect){
		/**
		 * �ȉ��̎O���ڂ�StoreData(�O���[�o��)�C���X�^���X�Ɋi�[
		 * Question
		 * Selection
		 * Answer
		 */
		//�o�͗p��蕶�i�[�z��
		String[] outputQuestion = null;
		
		//InputStream is = res01.openRawResource(R.raw.eigo001);
		br = new BufferedReader(new InputStreamReader(is));
		
		//strCSV�z��(�񎟌�)�Ɋi�[
		strCSV = new String[readingRowCount][readingColCount];//500x21�����}�g���N�X
		
		try{
			for(int row = 0;row<strCSV.length;row++){
				//��s�ǂݍ���Ńg�[�N���ɕ���
				StringTokenizer st = new StringTokenizer(br.readLine(), ",");
				for(int col =0;col < strCSV[row].length;col++){
					//strCsv�̊e�v�f�Ɋi�[
					strCSV[row][col] = st.nextToken();
					System.out.println("strCSV[" + row + "][" + col + "] = " + strCSV[row][col]);
					if(!st.hasMoreTokens()){


						for(int i = 4;i < 10;i++){
							System.out.println("strcsv["+row+"]["+i+"]="+strCSV[row][i]);
						}
						
						
						//�I��������בւ���
						
						//�܂��͊i�[
						String strSelection[] = new String[5];//�I�����i�[�p�z��
						int strTmp[] = new int[strSelection.length];//�i�[�σC���f�b�N�X�ۑ��p�z��
						for(int i = 0 ;i < strSelection.length;i++){
							strSelection[i] = strCSV[row][i+4];
							strTmp[i] = -1;//���ꂩ��ԍ����i�[����
						}
						//�����_���ɔz�u
						Random rnd = new Random();
						int insertNo;
						for(int j = 0; j < strSelection.length;j++){
							for(;;){
								insertNo = rnd.nextInt(5);
								
								//���Ɋi�[����Ă��Ȃ����`�F�b�N
								int i;
								for(i = 0; i < strSelection.length;i++){
									if(strTmp[i] == insertNo){
										break;//for-i
									}
								}
								if(i == strSelection.length){//�Ō�܂Ń`�F�b�N����insertNo���i�[����Ă��Ȃ������Ȃ�
									//�܂��i�[����Ă��Ȃ��I�����Ȃ̂Ŋi�[����
									strCSV[row][j+4]=strSelection[insertNo];//0-4
									//�i�[�ς̃C���f�b�N�X��ۑ�
									strTmp[j]=insertNo;
									//����ԍ����X�V����
									if(insertNo==0){//�ŏ��̔ԍ����𓚂Ȃ̂ł���������_���ɔz�u�F���M�Ȃ�
										strCSV[row][9]=(j+1)+"";//1�`5�܂ł̐���
										//�m�F
										System.out.println("insertNo=" + insertNo + ":" + strCSV[row][9]);
										System.out.println("given answer="+strCSV[row][10] + ", randomed ans="+
										strCSV[row][insertNo+4]);
									}
									
									break;//for-j
								}
							}//for(;;)
						}//for-j
						
						//���בւ���m�F
						for(int i = 4;i < 10;i++){
							System.out.println("after : strcsv["+row+"]["+i+"]="+strCSV[row][i]);
						}
						
						break;//for-col-break;
					}//if(!st.hasMoreTokens()){
				}//for-col
				if(strCSV[row][0].equals("[EOF]")){
					//�ŏI�s�ɒB������csv�ǂݍ��ݏI��
					break;//for row
				}
			}
			System.out.println("all CSV-word is completed reading!!");
			/*
			for(int row = 0;row < strCSV.length;row ++){
				for(int col = 0; col<strCSV[row].length;col ++){
					System.out.print(strCSV[row][col] + ",");
				}
				System.out.println("\n");
			}
			*/
			
		}catch(Exception e){
			System.out.println("CSV�ǂݍ��ݎ��ɃG���[�����I�I");
			System.out.println("�G���[��" + e);
		}
		
		int rowCSV = 1;//�Ǎ��p�J�E���^�[(0�s�ڂ̓��x��)
		int noArray = 0;//�i�[�p�J�E���^�[
		category = strCSV[rowCSV][2];//�J�e�S���[
		
		do{//�J�E���^�[rowCSV�Ń��[�v
			System.out.println("rowCSV =  " + rowCSV);
			if(strCSV[rowCSV][1] == null ||
					strCSV[rowCSV][0].equals("[EOF]")){
				/*
				 * strCSV�z��̍Ō�܂œǂݍ��񂾂Ƃ� 
				 */
				//strCSV���i�[���鎞��[EOF]�̍s�̓[����ڂ�[EOF]�łP��ڈȍ~��null
				System.out.println(rowCSV + "�s�ڂ�2��ڂ�Null��������1��ڂ�[EOF]�ł��̂ōŌ�܂Ŕz��Ɋi�[���I���܂����B");
				break;
			}else{
				//�f�o�b�O�̕K�v
				System.out.println("strCSV = " + strCSV[rowCSV][1]);
			}
			
			String strNoSect = strCSV[rowCSV][0].substring(4,7);//SECTXXX0��XXX�����o��
			int intNoSect = Integer.parseInt(strNoSect);
//			System.out.println(strNoSect + " : sect no = " + intNoSect);
			//���݂̍s�̖�蕶���n�C�t���ł���Ȃ�Ύ��̍s��ǂݍ���
//			if((!strCSV[rowCSV][3].equals("-"))){//����t�@�C����-�͑��݂��Ȃ�
			if(intNoSect == noAllowedSect){//�w�肳�ꂽ�͔ԍ��̎��̂݊e�z��(sectNo��)�Ɋi�[
				/*
				 * �͔ԍ��̊i�[
				 */
				sectNo[noArray] = strCSV[rowCSV][0];
				/*
				 *���ԍ��̊i�[ 
				 */
				qNo[noArray] = strCSV[rowCSV][1];
				//category = strCSV[rowCSV][2];�����[�v���Ŋi�[����K�v���Ȃ��̂ŊO�o����
				/*
				 * ���╶�̊i�[(D��)
				 */
				question[noArray] = strCSV[rowCSV][3];
				System.out.println("noArray = " + noArray);
				System.out.println("question = " + question[noArray]);
				
				
				//�I�������̐ݒ�(�i�[�ꏊ��E�񂩂�H��)
				for(int slcNo = 0;slcNo < selection[noArray].length;slcNo++){
					int colCSV = slcNo + 4;//�I�����̊i�[�ꏊ�͂S��ڂ���n�܂�
					selection[noArray][slcNo] = strCSV[rowCSV][colCSV];
					System.out.println("selection:" + slcNo + "=" + selection[noArray][slcNo]);
				}
				
				//�𓚔ԍ��i�[
				answerNo[noArray] = Integer.parseInt(strCSV[rowCSV][9]);
				System.out.println("answer["+noArray+"]=" + answerNo[noArray]);
				
				//�𓚊i�[
				answer[noArray] = strCSV[rowCSV][10];
				
				/*
				 * ������̎�荞��
				 */
				explain[noArray] = strCSV[rowCSV][11];
				System.out.println("explain complete initiating");
				
				noArray++;
				
			}else if(strCSV[rowCSV][0].equals("[EOF]")){
				//��Ɏ��̍s�̈��ڂ�[EOF]�����邩�m�F���Ă��邽�ߕs�K�v�ȏ����H
				System.out.println("�����ɐ���t���[������邱�Ƃ͂Ȃ�");
				
				break;
			}
			rowCSV ++;//Sect001, Sect002, ...�Ƃ����悤�ȏ��Ԃɂ͂Ȃ��Ă��Ȃ��̂őS�Č���K�v������
		}while(true);
		//�I����selection��5��ڂ���9���(col=4-8)��ǂݍ���Ŋi�[
		
		
		//���̍Ō�ɒB������A���i�[�z��question�̗v�f���𓖊Y���ԍ�row�܂�outputQuestion�ɂ���
//		outputQuestion = new String[rowCSV-1];//�ŏ��̓t�@�C���̊i�[����outputQuestion.length����������ǂ��������A���͈قȂ�(��̃t�@�C��test0001�ɂ͑��̃Z�N�^�[�������Ă���)
		outputQuestion = new String[noArray-2];
//		System.out.println("error at " + outputQuestion.length);
		//outputQuestion�̍ŏ�����Ō�܂ł̔z����Aquestion(:��(�ŏ�����)�\�t����
		System.arraycopy(question, 0, outputQuestion, 0, outputQuestion.length);
		
		
		for(int no = 0;no<outputQuestion.length;no++){
			System.out.println(no + ","  + outputQuestion[no]);
		}
		
		System.out.println("complete initiating Array!!");
		
		sData = new StoreData();
		System.out.println("complete initiating StoreData!!");
		//���͔̏ԍ���SECT�����������l�����̂ݓn��
		System.out.println(sectNo[0].replaceAll("SECT", ""));
		sData.setSectNo(Integer.valueOf(sectNo[0].replaceAll("SECT","")).intValue());
		System.out.println(Integer.valueOf(sectNo[0].replaceAll("SECT", "")).intValue());
		System.out.println("complete setSectNo");
		sData.setCategory(category);
		System.out.println("complete setCategory");
		sData.setQuestion(outputQuestion);
		System.out.println("complete setQuestion");
		sData.setSelection(selection);
		System.out.println("complete setSelection");
		sData.setAnswer(answer);
		System.out.println("complete answer");
		sData.setAnswerNo(answerNo);
		System.out.println("complete answerNo");
		sData.setExplain(explain);
		System.out.println("complete setExplain");
		
	
		return true;
	}
	public void dispAlert(){
		//�܂����C�g�ł̓_�C�A���O��\�����ďI������
		new AlertDialog.Builder(this)
			.setMessage("���ɐ\���󂲂����܂��񂪁A���S�ł݂̂̂����p�ƂȂ�܂��B")
			.setCancelable(true)
			.setPositiveButton("����", 
					new  DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							return;
						}
			})
		.show();
	}

}
