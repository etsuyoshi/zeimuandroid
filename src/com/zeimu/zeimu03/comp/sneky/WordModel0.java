package com.zeimu.zeimu03.comp.sneky;


import java.text.DecimalFormat;
import java.util.StringTokenizer;

import android.content.Context;


import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class WordModel0 extends Activity implements OnClickListener, OnCheckedChangeListener{
	/**
	 * �y���������z
	 * ��蕶��I�����A�d��\��\�����A���̔������`�B
	 * �߂�{�^���̔�����`
	 * ����{�^���̔�����`
	 * ���փ{�^���̔�����`
	 * 
	 * �y�����������z
	 * 
	 * �P���ԍ��\������
	 * �Q����@�\����s���(�ݕ��ɓ�ȏ�̍��ڂ�����ꍇ�ɔ��肪�o���Ȃ��������)�͉�����
	 * �R����{�^���������̏ڍג�`��������(selectstory.java���ŉ�����͂�ǂݍ���@strCSV�̏C������
	 * �S�{�^�����O���[�o���ɒ�`���AviewUpdate�œ��ꂳ�ꂽ(������₷��)���O�̔z��Ɋi�[�B-> ��
	 * �@viewUpdate���\�b�h���⑼���\�b�h���Ŕz��Ƃ��Ďg�p�ł���悤�ɂ���B���@�\�����͉�����
	 * �T�����d��\�̊i�[������Ă��遨������
	 * �U�{�^�������x�������Ɩ��������y����m�F�z��x�I�����ꂽ���ڂ�����������ɍX�ɍ��ڂ�I�����悤�Ƃ���Ɠ�x�������Ȃ��Ɗi�[����Ȃ��B���y������z�Č���������(or������)
	 * �V�d��\���ڃ{�^���̒l��""�ɂ��遨������
	 * �W�ŏI����̏�����intent��startactivity��SelectStory��ʂɖ߂�Ȃ��B�B�|>��
	 * �X���̖��Ƀ��X�i�[�����Ă��Ȃ���������
	 * 10�߂�{�^���������Ɂu���I���v�ɍs���̂ł͂Ȃ��A�O�̃y�[�W�ɍs����������
	 * 11������̕\���̓_�C�A���O�ł͂Ȃ��A���ʂ�Activity�y�[�W�ɂ���\��(���R�F�������ɑ΂��ă_�C�A���O��ʂ̑傫�����������A���A���s���ǂݎ��Ȃ����߁B->��
	 * 12(��)sharedPreferences�Ő��ѕ\��ۑ�(�ő����ԂƐ����������L���O)
	 * 13(���Ή�)�������͂����Ɏ��̖��ɍs�����Ƃ���ƒ��Ӄ_�C�A���O���\�����t�Ɏg�����肪�����̂ŁA�������̂ł���Ή𓚂���Ă���΃A���[�g���s����悤�ɂ���B
	 * 14(��)���̍ŏ��ɐ������͂�\�����A���̍ۂɃt���b�N�A�N�V�����ɂ��Ă̑�����@���\������->��ʉ����e�L�X�g�\��
	 * 15(���Ή�)�ۋ��^�v���O�����̍쐬->http://www.atmarkit.co.jp/fsmart/articles/android_billing02/01.html
	 * 16(��)���ь���xml�t�@�C���̕ۑ�->12�ɓ�����
	 * 17(��)�T���l�C���̍쐬
	 * 18(��)�C���[�W�{�^���쐬
	 * 19(��)�s�����ɂȂ��������͍ēx���Ȃ����y�ρz->�듚�ꗗ�̕\�������𕶏͂̕\�����듚��蒼���@�\�ǉ�
	 * �@�@�@�@�@->�s�����ɂȂ����񐔂̊i�[(xml�t�@�C���Ƃ��ĕۑ�)
	 * 		   ->�N���X��p�ӂ��Č듚�񐔃t�B�[���h���쐬
	 * 		�@�@->�s���������L���O�̏��ԂɎ��s����@�\
	 * 20(��)�I�����̕����������߂��ĕ����T�C�Y������������
	 * 21(���Ή�)��̖͂��ō��ڂ������͂��ĉ𓚃{�^���������Ƌ����I���B->�d��\�̕Б���null�̎���answerJudge�̃��W�b�N�m�F�[���Č����ēx�m�F
	 * 22(��)�ēx��蒼�����Ɏ��Ԃ����Z����Ă��܂��B
	 * 23(��)���э폜�����j���[��ʂɔz�u���A�듚�̂ݍēx���s�{�^������W�̎��ɕ\���B
	 * ->�듚�̂ݍēx���s�͂P�ŏI���Ŏ��փ{�^�����������ꂽ�Ƃ�(finalView)�A����i�[�z��(����)���g���Č��ԍ�����������񐔂����Z����shared...�Ɋi�[���遨�I�v�V�����{�^���ŕ\���A�폜����B
	 * ->�ʏ�𓚎��Ɂu�����v�u�듚�v�t���O(����)��\������(�D�揇�ʗ��)
	 * 24(�ρj���I����ʂ�finish�{�^�����G���[->Button��ImageButton���L���X�g�ł��Ȃ��B�I�u�W�F�N�g�͑������Ƒ�������e����A����
	 * 25(��)��蕶�͂����s����
	 * 26(���Ή�)�v�����Ԃ������x�傫���Ȃ�����v���I��(long passTime�̊i�[���e�ʎ���)
	 * 27(���Ή�)���I����ʂŃI�v�V�����ݒ�H�H
	 * 28(��)��蕶�̕������������ꍇ�͕����T�C�Y���k������
	 * 29(��)sectXXXtime��sectX��O�҂ɓ���
	 * 30(��)displayRecord�N���X�Ō듚����\��(�~�O���t)���\���������X�V����Ȃ����y�{���Ή��z
	 * 31(�H���Ή�)SharedPreferences���g�p����O�ɁA�K�����炩�̒l���i�[���邱�ƁI->nullPointerException�ƂȂ�
	 * 32(���Ή�)��蕶�́A�y�щ�����͂̑啶�����̃J���}�u�C�v���������J���}�u,�v�ɕϊ�
	 * 33(�ŗD�掖���I���Ή�)�ݕ��̐����̂ݔ�\���ɂ��ĉ𓚂���Ɨ�����B
	 * 34(��)�s�����Ď��s���[�h�ōŌ�܂ōs���Ă��I�����Ȃ�
	 * 35(��)�{�^���̕������؂�Ă���
	 * 36(��)�ŏI����next�{�^����A�����ĉ����Ȃ��l�ɂ���
	 */
	
	
	/*
	 * ���Ԍv���̓R���X�g���N�^�Ăяo�����ƍŏ������蒼���{�^���������̂݊J�n����(startTime������)
	 */
	private long startTime;
	private long endTime;
	private long passTime;
	
	//���I�����Ɍ듚�̂ݍĎ��s�{�^�����������ꂽ��true�ɂ���
	private boolean REPEAT_MODE;
	//�s������̂�true���i�[����->�S�␳���ɂȂ�����S��false�ɂȂ��Ė��I����ʂɖ߂�
	private boolean[] RepeatQuesNumberArray;
	
	private SharedPreferences pref;
	
	private int qNo;//�[������n�܂�
	private StoreData sData;
	private ResultData rData;
	
	private RadioGroup myRadioGroup;
	private boolean finalUpdateFlag;//finalUpdate���\�b�h�����s�ł�����
	
	private TextView txt_title;//���╶�� 
	
	private int selectedItemNo;
	private int selectedItem;
	
	private Context myContext;
	@Override
	public void onCreate(Bundle savedInstanceState){
		System.out.println("START WORD MODEL1!!");
		super.onCreate(savedInstanceState);
		System.out.println("onCreate complete!");
		setContentView(R.layout.word_model0);
		
		//���̃N���X�̃C���X�^���X���쐬(�듚��XML�t�@�C���ɋL��������ۂɊ��p��finalUpdate)
		myContext = this;
		
		System.out.println("���X�i�[�ǉ�");
		//RadioGroup�̏�����
		myRadioGroup = (RadioGroup)findViewById(R.id.myRadioGroup);
		myRadioGroup.setOnCheckedChangeListener(this);
		
		//�ŏ��̉�ʕ`�掞�ɑ�����@���_�C�A���O�ŕ\��
		/*
		 * �Ȃ����G���[�����B�B������͕K�v�Ȃ��̂ŕ\�����Ȃ�
		new AlertDialog.Builder(this)
			.setTitle("���߂�(������@)")
			.setMessage("�܂��ŏ��ɖ�蕶��ǂ�ł���A�K�؂Ȍ����쐬���ĉ������B\n" +
					"OK�{�^���������Ǝ��Ԃ��v������܂��B")
			.setPositiveButton("����",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					//do nothing...
				}
			}).show();
		*/
		Intent intent = getIntent();
		//���f�[�^�Ɛ����f�[�^�̎󂯎��p�I�u�W�F�N�g
		sData = (StoreData)intent.getSerializableExtra("StoreData");
		//System.out.println("the number of question sentence = " + sData.getQuestion().length);

		//sData���ɉ񓚕��͂��i�[����Ă���������ArData�ɂ����茋�ʂ��i�[����z���p�ӂ���
		System.out.println(sData.getQuestionNo());
		rData = new ResultData(sData.getQuestionNo());


		//���Ԍv���J�n
		startTime = System.currentTimeMillis();
		System.out.println("clock complete initiate");
		
		//��ʍX�V
		this.viewUpdate();
		
		
		
				
		
		//���ѕ\�i�[�p��xml�t�@�C���ۑ��p�C���X�^���X�̐ݒ�
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		
		System.out.println("complete executing constructor1");
		
		this.RepeatQuesNumberArray = new boolean[sData.getQuestionNo()];
		
		//finalUpdate�t���O��true�ɂ��ă��\�b�h���󂯕t�������Ԃɂ���
		finalUpdateFlag = true;
		System.out.println("complete executing constructor2");
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /*
     * radioButton�̃��X�i�[(non-Javadoc)
     * @see android.widget.RadioGroup.OnCheckedChangeListener#onCheckedChanged(android.widget.RadioGroup, int)
     */
    public void onCheckedChanged(RadioGroup rGroup, int checkedId){
		
    	RadioButton rb = (RadioButton)findViewById(checkedId);
    	if(rb.isChecked() == true){
    		System.out.println("���X�i�[����" + checkedId);
    		selectedItemNo = checkedId+1;
    	}
    	System.out.println("���X�i�[����2");
    	Toast.makeText(this,  "�I���������W�I�{�^��:" + rb.getText(),  Toast.LENGTH_SHORT).show();
    }
    
    public void onClick(View v){
    	/**
    	 * �ȉ��̏������͕ʃ��\�b�h�ɂ���(�����I��)
    	 */
	    switch(v.getId()){
	    		/**
	    		 * �I�����̃��A�N�V������`
	    		 */
	    		
		    	case R.id.st1_previous:
		    		if(this.REPEAT_MODE){
	    				//�s�������������܂Ŗ߂�
		    			//�ŏ��܂Ŗ߂��Ă��s�����肪�Ȃ���Ζ��I����ʂɖ߂�
		    			if(qNo == 0){
		    				finish();
		    			}else{
		    				System.out.println("qNo = " + qNo);
		    				do {
		    					qNo--;
		    					System.out.println(qNo);
		    					if(qNo < 0){
		    						System.out.println("finish");
		    						finish();
		    						break;//�����ꂪ�Ȃ��ƃG���[(IllegalStateException����)�ɂȂ�I�I
		    					}
		    					
		    				}while(!this.RepeatQuesNumberArray[qNo]);
		    				
		    				System.out.println("qNo = " + qNo);
		    				if(qNo >= 0){
		    					this.viewUpdate();
		    				}else{
		    					finish();
		    				}
		    				
		    				
		    			}
	    				break;
	    			}else{
			    		if(qNo==0){
		    				//�ŏ��̖��Ŗ߂�{�^���������ꂽ����I����ʂɖ߂�(����Activity�����)
		    				finish();
			    		}else{
			    			qNo --;
			    			this.viewUpdate();
			    			break;
			    		}
					}
		    		break;
		    	case R.id.st1_kaisetu:
		    		System.out.println("pressed kaisetu");
		    		Intent intent = new Intent(this, ExplainView.class);
		    		System.out.println("complete newing intent");
		    		intent.putExtra("explainView",sData.getExplain(qNo));
		    		System.out.println("complete putting extra");
		    		
		    		startActivity(intent);
		    		break;
		    	case R.id.st1_exit:
		    		System.out.println("pressed exit!");
		    		//�I���m�F�̃_�C�A���O�\����A�I��

		    		//��肪�Ō�܂ŒB�����̂Ő��т̌��ʂ��_�C�A���O�ŕ\�����܂��B
		    		 AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

	    	        // �_�C�A���O�̐ݒ�
	    	        alertDialog.setTitle("���m�F");			//�^�C�g��
	    	        alertDialog.setMessage("�I���{�^����������܂����B\n�����I�����Ă��X�����ł����H");
	    	        //alertDialog.setIcon(R.drawable.icon);	//�A�C�R���ݒ�

	    	        alertDialog.setPositiveButton("�I��", new DialogInterface.OnClickListener() {

	    				public void onClick(DialogInterface dialog, int which) {
	    					// TODO �����������ꂽ���\�b�h�E�X�^�u
	    					System.out.println("�I���{�^���������ꂽ�̂ŏI�����܂��B");
	    					finish();
	    				}
	    			});
	    	        alertDialog.setNegativeButton("�L�����Z��", new DialogInterface.OnClickListener(){
	    	        	public void onClick(DialogInterface dialog, int which){
	    	        		System.out.println("�I���{�^��������A�L�����Z������܂���");
	    	        	}
	    	        });
	    	        // �_�C�A���O�̍쐬�ƕ\��
	    	        alertDialog.create();
	    	        alertDialog.show();
	    	        break;
		    	    
		    	case R.id.st1_answering:
		    		System.out.println("pressed answering!");
		    		
		    		//RadioButton radioButton = (RadioButton)findViewById(myRadioGroup.getCheckedRadioButtonId());
		    		System.out.println("�F������Ă���{�^����" + myRadioGroup.getCheckedRadioButtonId());
		    		System.out.println("radio0 = " + R.id.radio0);
		    		System.out.println("radio1 = " + R.id.radio1);
		    		System.out.println("radio2 = " + R.id.radio2);
		    		System.out.println("radio3 = " + R.id.radio3);
		    		System.out.println("radio4 = " + R.id.radio4);
		    		switch(myRadioGroup.getCheckedRadioButtonId()){
		    			
		    			case R.id.radio0:
		    				System.out.println("1��������Ă��܂��B");
		    				selectedItemNo = 1;
		    				break;
		    			case R.id.radio1:
		    				System.out.println("2��������Ă��܂��B");
		    				selectedItemNo = 2;
		    				break;
		    			case R.id.radio2:
		    				System.out.println("3��������Ă��܂��B");
		    				selectedItemNo = 3;
		    				break;
		    			case R.id.radio3:
		    				System.out.println("4��������Ă��܂��B");
		    				selectedItemNo = 4;
		    				break;
		    			case R.id.radio4:
		    				System.out.println("5��������Ă��܂�");
		    				selectedItemNo = 5;
		    				break;
		    			default:
		    				System.out.println("�ǂ��������Ă��Ȃ��I");
		    				selectedItemNo = 0;
		    				break;
		    		}
		    		System.out.println("answerJudge�O�F�I�����ꂽ�ԍ���" + selectedItemNo);
		    		dialogEither(answerJudge());
		    		System.out.println("answerJudge��F�I�����ꂽ�ԍ���" + selectedItemNo);
	    			return;
	    		
		    	case R.id.st1_next:
		    		System.out.println("pressed next!");
		    		System.out.println("�J��Ԃ����[�h" + this.REPEAT_MODE);
		    		if(this.REPEAT_MODE){//�s��������s���[�hON�Ȃ��
						//�O��s������܂�qNo++;���J��Ԃ�
						for(qNo = qNo + 1;qNo<sData.getQuestionNo();qNo++){
							System.out.println("check" + qNo);
							if(this.RepeatQuesNumberArray[qNo]){
								System.out.println(qNo + "�͕s������Ȃ̂ŌJ��Ԃ��܂��B");
								break;//�s������Ȃ��for���𔲂���
							}else{
								//�s������ł͂Ȃ��̂ŁA�������Ȃ��Ŏ��̖���T������
							}
						}//for qNo
						//�J��Ԃ����s����T���������ʁA��蕶���Ō�܂ōs������I��
						if(!(qNo<sData.getQuestionNo())){
							//finalUpdate���\�b�h���Ăяo���āA���ѕ\�������ɂ����Ă��ǂ����A���̖��̔��肪�o���Ă��Ȃ��B
							System.out.println("�J��Ԃ����[�h�ōŏI�����I�����̂ŃV�X�e���I�����܂��B");
							finish();
							return;
						}
					}else{
						qNo++;
						System.out.println("���ԍ����Z->" + qNo);
					}
		    		System.out.println(qNo + "��\�����܂��B���i�[�ԍ�->" + sData.getQuestionNo());
	    			if(qNo < sData.getQuestionNo()){//���ԍ����i�[���ȉ��Ȃ�
	    				if(!(sData.getQuestion(qNo).equals("-"))){//���╶�Ƀn�C�t�������邱�Ƃ͂Ȃ����O�̂���
	    					//�ʏ�͈ȉ����������s����邾���ŁA��������͂���Ȃ��͂�(�n�C�t�������邱�Ƃ͖�������)
	    					System.out.println("call viewUpDate by next button!");
	    					//�\�Ȃ牽���I������Ă��Ȃ��ꍇ��alertDialog�𔭐�������->�d��\���쐬���Ă����̖��ɍs���Ă���߂�Ǝd��\�������͂̏�ԂɂȂ��Ă��܂��B
			    			this.viewUpdate();
	    				}else{//�n�C�t���ɂȂ邱�Ƃ͂Ȃ����A�O�̂��߃��X�N�w�b�W
			    			System.out.println("���炭�����͐���t���[�ɗ���Ă��Ȃ�");
			    			
			    		}
	    			}else{// if(qNo >= sData.getQuestion().length)
	    				/**
	    				 * ��肪�Ō�܂ŒB�����̂Ő��т̌��ʂ��_�C�A���O�ŕ\������
	    				 */
	    				//�s������Ď��s���[�h�ł͂����ɐ���t���[�͗���Ȃ�(��̃u���b�N�Œ�`��)
	    				if(finalUpdateFlag){
	    					finalUpdateFlag = false;//�A������finalUpdateflag���Ăяo���Ȃ��l�ɂ���
	    					this.finalUpdate();
	    				}
	    			}
	    			break;
		}
    }
    /**
     * �����̑S�Ă�boolean�z���false�ɂ���
     * �p�r�F��̃{�^���������ꂽ�Ƃ��A���̑S�Ẵ{�^�������(false)�ɂ���
     *  �����Y�{�^���̒l�͂��̐���t���[�̒���true�ɂ����B
     *      
     */
    private void ArrayAllFalse(boolean[] args){
    	for(int i = 0;i < args.length;i++){
    		args[i] = false;
    	}
    }
    
    /*
     * ��肪�Ō�܂ŕ\�����ꂽ���next�{�^�����������ꂽ�Ƃ��ɌĂяo�����
     */
    
    private void finalUpdate(){
    	//���댋�ʂ����ʗ����t�@�C��(XML)�ɏo�͂��邽�߂̃t�B�[���h�����C���X�^���X
    	//->�R���X�g���N�^���Ăяo�������Ŋi�[�����
    	AnswerContext ac = new AnswerContext(myContext);
    	ac.inputXML(rData, sData.getSectNo());
    	
    	/*
    	System.out.println(ac.outputXML(sData.getSectNo(), 0, true));
    	System.out.println(ac.outputXML(sData.getSectNo(), 1, true));
    	System.out.println(ac.outputXML(sData.getSectNo(), 2, true));
    	*/
    	
    	//�_�C�A���O�^�C�g��
    	String dialogTitle = new String("");
    	//�_�C�A���O�e�L�X�g
    	String dialogText = new String("");
    	
    	//���Ԃ̌v��
    	endTime = System.currentTimeMillis();
		passTime = endTime - startTime;
		
		//�u�S�␳���̏ꍇ�v�������́u���񎞁v�̂݁A
		//���񂩂��������Ԃ��O�����������Ώo�͂������o���B
		System.out.println("�S�⊮��");
		System.out.println("�񓚐�=" + qNo);
		System.out.println("�����A������=" + rData.getSumRightAnswer());
		
		
		
		DecimalFormat df000 = new DecimalFormat("000");
		/*
		 * �S�␳�����A���A���́y�P�z�܂��́y�Q�z�̏ꍇ�ɂ̂ݍ���̎��ԏ����i�[����
		 * �y�P�zSharedPreferences(XML�t�@�C��)��sectXXX�^�O�̃f�[�^���i�[����Ă��Ȃ�(���߂đ�XXX�͂��𓚂���)�ꍇ
		 * ��������
		 * �y�Q�z�O��𓚂��������𓚂����ꍇ
		 */
		int sectNo = sData.getSectNo();
		String sectNoXXX = df000.format(sectNo);
		if(rData.getSumRightAnswer() == sData.getQuestionNo() &&
				((pref.getString("sect" + sectNoXXX, "")).equals("") || 
				passTime < Long.parseLong(pref.getString("sect" + sectNoXXX, "")))){
			
			
			System.out.println("���т��i�[");
			SharedPreferences.Editor editor = pref.edit();
			System.out.println("����O�i�[�l" + pref.getString("sect" + sectNoXXX,""));
			
			
			dialogTitle = "�S�␳���I\n�^�C�����X�V���܂����I";
			dialogText = "���� : " + rData.getSumRightAnswer() + 
					"\n������ : " + 
					(new DecimalFormat("0.0%")).format(
							((float)rData.getSumRightAnswer() / (float)sData.getQuestionNo())) + 
					"\n����^�C����" + (new DecimalFormat("0.000")).format((float)passTime/1000) + "�b�ł�";
			
			if(pref.getString("sect" + sectNoXXX,"").equals("")){
				
				dialogText += "\n���O��̋L�^�͂���܂���B";
				
			}else{
				dialogText += "\n�O��^�C����" +
						(new DecimalFormat("0.000")).format((float)Long.parseLong(pref.getString("sect" + sectNoXXX, ""))/1000) + "�b�ł����B";
			}
			
			//���т̊i�[
			//editor.putString("sect" + (new DecimalFormat("000")).format(sData.getSectNo()) + "time", passTime + "");
			editor.putString("sect" + sectNoXXX ,passTime + "");
			editor.commit();
			System.out.println("���ъi�[����->" + pref.getString("sect" + sectNoXXX, ""));

		}else{
			System.out.println("���т��i�[���܂���B���̗��R��...");
			if(rData.getSumRightAnswer() != sData.getQuestionNo()){
				System.out.println("�S�␳���łȂ����߂ł�");
				dialogTitle="�y�c�O�I�z�ꕔ�𓚂Ɍ�肪����܂�������";
				dialogText = "���� : " + rData.getSumRightAnswer() + 
						"\n������ : " + 
						(new DecimalFormat("0.0%")).format(
								((float)rData.getSumRightAnswer() / (float)sData.getQuestionNo()) 
						) + 
						"\n����^�C����" + (new DecimalFormat("0.000")).format((float)passTime/1000) + "�b�ł�";
				
			}else if(passTime >= Long.parseLong(pref.getString("sect" + sectNoXXX,""))){
				System.out.println("�O��^�C��������Ȃ��������߂ł�");
				System.out.println("���Ȃ݂ɑO��^�C��" + pref.getString("sect" + sectNoXXX,"") + 
						"����^�C���F" + (new DecimalFormat("0.000")).format(passTime/1000));
				
				dialogTitle="�y�������I�z�S�␳���ł����A�^�C���X�V�Ȃ炸...";
//						Long.parseLong(pref.getString("sect" + sData.getSectNo(), ""))/1000 + "�b)������܂���ł�������";
				dialogText = "���� : " + rData.getSumRightAnswer() + 
						"\n������ : " + 
						(new DecimalFormat("0.0%")).format(
								((float)rData.getSumRightAnswer() / (float)sData.getQuestion().length) 
						) + 
						"\n����^�C����" + (new DecimalFormat("0.000")).format((float)passTime/1000) + "�b�ł�" + 
						"\n�O��^�C����" + (new DecimalFormat("0.000")).format((float)Long.parseLong(pref.getString("sect" + sectNoXXX, ""))/1000) + "�b�ł����B";
			}
			
			System.out.println("�o�ߎ��� = " + passTime);
			System.out.println("���i�[����F" + (pref.getString("sect" + sectNoXXX, "")).equals(""));
			System.out.println("�ŏI�i�[�l = " + pref.getString("sect" + sectNoXXX,""));
			
			
		}
		
		//��肪�Ō�܂ŒB�����̂Ő��т̌��ʂ��_�C�A���O�ŕ\�����܂��B
		new AlertDialog.Builder(this)
			.setTitle(dialogTitle)
			.setMessage(dialogText)
			.setPositiveButton("�I��",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					/* �������̏��� */
					finalUpdateFlag = true;//����finalUpdateflag���Ăяo�����Ԃɂ���
	    			System.out.println("���ԍ����Ō�ɒB���A�I����I�������̂ŏ͑I����ʂɖ߂�܂�");
	    			finish();
				}
			})
			.setNegativeButton("������x\n�ŏ�����", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int whichButton){
					/* �������̏��� */
					finalUpdateFlag = true;//����finalUpdateflag���Ăяo�����Ԃɂ���
					System.out.println("���ԍ����Ō�ɒB���A�ŏ�����I�������܂����B");
					REPEAT_MODE = false;//�s��������s���[�hOFF
					qNo = 0;
					//���Ԍv���͖��N����(�R���X�g���N�^�N����)�ƍĎ��s��(��)�I
					startTime = System.currentTimeMillis();
					viewUpdate();
				}
			})
			
			.setNeutralButton("�듚�̂ݍĎ��s", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int whichButton){
					finalUpdateFlag = true;//����finalUpdateflag���Ăяo�����Ԃɂ���
					System.out.println("�듚�̂ݍēx���s");
					//////////////////
					//System.out.println(new AnswerContext(myContext, rData, sData.getSectNo()).getString());
					//�s������̊i�[
					for(int i = 0;i<RepeatQuesNumberArray.length;i++){
						if(rData.getJudgeArray(i)){
							//����A�����������ꍇ
							System.out.println("���" + i + "�͐���");
							RepeatQuesNumberArray[i] = false;
						}else{
							//����A�s�����������ꍇ
							System.out.println("���" + i + "�͕s�����Ȃ̂ŌJ��Ԃ����ɒǉ����܂��B");
							RepeatQuesNumberArray[i] = true;
						}
					}
					//�s��������s���[�hON
					REPEAT_MODE = true;
					for(qNo = 0;qNo<sData.getQuestionNo();qNo++){
						//�ŏ��ɊԈႦ�����Ń��[�v�𔲂���
						if(RepeatQuesNumberArray[qNo]){
							break;
						}
					}
					if(qNo < sData.getQuestionNo()){
						startTime=System.currentTimeMillis();
						viewUpdate();
					}else{
						System.out.println("�S�␳���Ȃ̂ŌJ��Ԃ������͎��s�����I�����܂��B");
						finish();
					}
				}
			})
		.show();
		//http://techbooster.jpn.org/andriod/ui/1140/

    }
    
    private void viewUpdate(){
    	/**
    	 * �`�惂�W���[��
    	 */
    	System.out.println("start view update!");
    	int questionNo = qNo;
    	
    	/*
		System.out.println("qNo = " + qNo);
		
		System.out.println("��蕶�� ; " + sData.getQuestion(qNo));
		
		System.out.println("sData.getQuestion().length = " + sData.getQuestion().length);
		System.out.println("sData.getQuestion(" + questionNo + ") = " + sData.getQuestion(qNo));
		*/
    	System.out.println("���ԍ�" + questionNo);
    	System.out.println("���i�[��" + sData.getQuestion().length);
    	System.out.println("���݂̎�����e" + sData.getQuestion(questionNo));
    	if(questionNo < sData.getQuestion().length &&
    			sData.getQuestion(questionNo) != null){
    		
    		/*
    		 * ���W�I�{�^������������Ă��I�����ꂽ�ԍ����F������Ȃ��I�I�I�I���[�G���[�m�F(���X�i�[���L�q�K�v�H)
    		 */
    		System.out.println("�����N���A�[����̓I�`��J�n");
    		//�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I�I
    		//�I�I�I�I�I�I�I�I�I�I���������I�I�I�I�I�I�I�I�I�I�I�I�I�I�I�폜�ɂ��e���m�F�v�I�I
    		//this.setContentView(R.layout.word_model0);
    		
    		//�͔ԍ��̐ݒ�
    		txt_title = (TextView)findViewById(R.id.txt_sectName);
    		txt_title.setText("�y��" + sData.getSectNo() + "�́z" + sData.getCategory());
    		
    		//���╶�e�L�X�g�r���[�̏�����(�I�u�W�F�N�g�̃C���X�^���X��)
    		TextView question = (TextView)this.findViewById(R.id.txt_questSntnc);
    		//�e�L�X�g�r���[�ɉ��s���ꂽ�e�L�X�g��ݒ�
    		
    		question.setText("(��" + (qNo + 1) + "��) " +  
    				insertParagraph(sData.getQuestion(questionNo)));
    		/*
    		 * ��ʍĒ����@�\(��蕶�̒����ɑI������z�u)�ɂ��]���̕����񒷂ɂ��T�C�Y�����͕s�v�ɂȂ���
    		if(sData.getQuestion(questionNo).length() > 100){
    			question.setTextSize(12.0f);
    		}else if(sData.getQuestion(questionNo).length() > 70){
    			question.setTextSize(13.0f);
    		}else{
    			question.setTextSize(15.0f);
    		}
    		*/
    		
    		//�I�����̒�`
    		System.out.println("���W�I�{�^���I�����̌��F" + sData.getSelectionCnt(questionNo));
    		
    		System.out.println("radiogroup");
    		//�`�F�b�N����Ă��郉�W�I�{�^����ID���擾
    		//myRadioGroup.check(R.id.radio0);
    		
    		/*
    		 * <�I������sData�Ɋi�[���ꂽ�I�������������>
    		 * [����̉��ǌ���]�v�f���ɉ����Ď����I�Ɏ擾
    		 */
    		
    		//5�̑I�������W�I�{�^�����쐬
    		RadioButton rb[] = new RadioButton[5];
    		rb[0] = (RadioButton)findViewById(R.id.radio0);
    		rb[1] = (RadioButton)findViewById(R.id.radio1);
    		rb[2] = (RadioButton)findViewById(R.id.radio2);
    		rb[3] = (RadioButton)findViewById(R.id.radio3);
    		rb[4] = (RadioButton)findViewById(R.id.radio4);
    		for(int i = 0;i<rb.length;i++){
    			rb[i].setText(sData.getSelection(qNo,i));
    		}
    		
    		System.out.println("�`�抮��");
    		
    	}else{
    		/**
    		 * ���ԍ���sData�̖��z��̒������傫���Ȃ邱�Ƃ��Ȃ����A
    		 * ��蕶�͂�null�l�ɂȂ邱�Ƃ��Ȃ��̂ŁA�����ɐ���t���[������邱�Ƃ͂Ȃ��B
    		 */
    		System.out.println("��O�����@���@updateView");
    		//this.setContentView(R.layout.story_model1);
    		//�Ō�̖����I�����ꍇ�A���I���̎|�̃_�C�A���O��\��������A���ѕ\���(���쐬)�Ɉڍs
    		//Intent intent=new Intent(this, SelectStory.class);
			////intent.putExtra("StoreData", rData);//sData��setQSA���Ŋi�[����Ă���
			////intent.setAction(Intent.ACTION_VIEW);
			//startActivity(intent);
			
    		finish();
    	}
    	
    }
    
    
    
    private boolean answerJudge(){
    	/**
    	 * �񓚃{�^���������ꂽ���ɌĂяo����A
    	 * �_�C�A���O�{�b�N�X�Ŏd��\�̐����^�s�����𔻒�
    	 */
    	System.out.println("pressed Judgement!");
    	
    	
    	if(sData!=null){
    		System.out.println("qNo = " + qNo);
    		System.out.println("���� = " + sData.getAnswerNo());
    		System.out.println("��蕶�� = " + sData.getQuestion(qNo));
			System.out.println("�I�� = " + selectedItemNo);
			
    		if(sData.getAnswerNo(qNo) == selectedItemNo){//�����̏ꍇ
    			
    			System.out.println("�����I");
    			return true;
    		}else{//�s�����̏ꍇ
    			System.out.println("�s�����I");
    			System.out.println(qNo + "��ڂ̐�����" + sData.getAnswerNo(qNo));
    			System.out.println("����I����" + selectedItemNo);
    			return false;
    		}
    		
    	}else{
    		System.out.println("sData is null!");
    		//sData��null�̏ꍇ�͕s��v�t���O��Ԃ�
			return false;
		}
    	
    }
    
    //��ʏ�̉𓚃{�^�����������ꂽ�Ƃ���answerJudgement�֐��̕Ԃ�l(���딻�f)��\������
    private void dialogEither(boolean judgement){
    	
    	String strJudgement = null;
    	System.out.println("called dialog!!");
    	if(judgement){
    		strJudgement = "�����I:" + selectedItemNo;
    		rData.setJudgement(qNo, true);
    		System.out.println("�����I�F���܂ł̐���" + rData.getSumRightAnswer());
    	}else{
    		strJudgement = "�s����!:" + selectedItemNo;
    		rData.setJudgement(qNo, false);
    		System.out.println("�s�����I�F���܂ł̐���" + rData.getSumRightAnswer());
    	}
		new AlertDialog.Builder(WordModel0.this)
			.setMessage(strJudgement)
			.setCancelable(false)
			.setPositiveButton("���̖���", 
					new  DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							//���̖���
							if(REPEAT_MODE){//�s��������s���[�hON�Ȃ��
								//�O��s������܂�qNo++;���J��Ԃ�
								for(qNo = qNo + 1;qNo<sData.getQuestionNo();qNo++){
									System.out.println("Repeat = " + RepeatQuesNumberArray[qNo]);
									if(RepeatQuesNumberArray[qNo]){
										break;//�s������Ȃ��for���𔲂���
									}
								}
							}else{
								qNo++;
							}
							if(qNo < sData.getQuestion().length){//���ԍ����i�[���ȉ��Ȃ�
								//����ԍ����C���v�������g(�{�P)������ŉ�ʍX�V
								System.out.println("finalUpdate execute! @ " + qNo);
			    				viewUpdate();
							}else{
								//����ȏ��萔���Ȃ��ꍇ
								System.out.println("finalUpdate execute! @ " + qNo);
								finalUpdate();
							}
						}
			})
			.setNegativeButton("����\��",
					new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							System.out.println("pressed kaisetu");
				    		Intent intent = new Intent(WordModel0.this, ExplainView.class);
				    		System.out.println("complete newing intent");
				    		intent.putExtra("explainView",sData.getExplain(qNo));
				    		System.out.println("complete putting extra");
				    		
				    		startActivity(intent);
				}
			}).show();
    }
    
    /**
     * #�����s����
     * @param arg
     * @return
     */
    private String insertParagraph(String arg){
    	System.out.println("complete reading givenData");
    	StringTokenizer st = null;
    	String line2[] = null;
        try{
        	//�X�g�����O�g�[�J�i�C�U�[���g����#�ŉ��s����
        	st = new StringTokenizer(arg, "#");
	        
	        String line[] = new String[10];//�ő�10�s���x�Ƃ���
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
        
        String out = "";
        for(int i = 0;i<line2.length;i++){
        	out += "\n" + line2[i];
        }
    	
    	return out;
    }
    
    

}
