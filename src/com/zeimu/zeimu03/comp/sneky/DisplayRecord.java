package com.zeimu.zeimu03.comp.sneky;

import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;


//import android.content.Context;
import android.graphics.Color;


public class DisplayRecord extends Activity implements OnClickListener{
	private SharedPreferences pref;
	private int sectNo;
	private String sectNoXXX;
	private TextView tv;
	
	private int NUMOFRANKING = 5;//�����L���O��\������v�f��
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * 
	 * ranking���쐬���A�s���𗦃g�b�v�T���O���t�ŕ\��
	 */

	public void onCreate(Bundle savedInstanceState) {
    	System.out.println("display record constructor start!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_record);
        
        System.out.println("complete setcontent");
        
        //Record�N���X����n���ꂽ�͔ԍ����擾
        Intent intent = getIntent();
        sectNo = (Integer)intent.getSerializableExtra("SectNo");
        
        DecimalFormat df = new DecimalFormat("000");
        //sectNoXXX = new String("");
        sectNoXXX = df.format(sectNo);
        System.out.println("�͔ԍ���" + sectNo);
        //�n���ꂽ�͔ԍ������Ƃ�XML�t�@�C�����̊Y���f�[�^�����擾����
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        
        
        this.tv = (TextView)this.findViewById(R.id.disp_title);
        //sect004���i�[����Ă��Ȃ�
        if(this.pref.getString("sect" + sectNoXXX, "").equals("")){
        	System.out.println("�܂��S�␳���̋L�^�͂���܂���");
        	this.tv.setText("��" + sectNo  + "�͂̎�_����\n" +
        			"�܂��S�␳���̋L�^�͂���܂���B");
        }else{
        	System.out.println("�S�␳���̋L�^������܂�");
        	System.out.println(pref.getString("sect" + sectNoXXX, ""));
	        this.tv.setText("��" + sectNo + "�͂̎�_�����L���O\n(�ő��^�C��:" + 
	        		(new DecimalFormat("0.00").format(Long.parseLong(pref.getString("sect" + sectNoXXX, ""))/1000)) + "�b)");
        }
        /*
         * �헪�I�C���[�W
         * �듚��(�s����/(���𐔁{�s����))���薈�Ƀ����L���O���A
         * ��ʂT��̖�蕶�͂Ɖ𓚂�\��(�ʉ��)
         * a/////////////////////sectXXXtime���Ăяo���ƃG���[����
         */
        
        
    	this.onDraw();
        
        
        
    	
	}
	public void onClick(View v){
		if(((Button)v).getId() == R.id.disp_bt_return){
			System.out.println("���щ�ʂ���܂��B");
			finish();
			return;
		}
	}
	private CombValueRank[] getRanking(){
		System.out.println("start getRanking method");
		//XML�t�@�C���Ɋi�[����Ă�����̕s���𗦃����L���O���쐬����
		DecimalFormat df000 = new DecimalFormat("000");
		long[] array = new long[30];//�s�����ƂȂ����񐔂��i�[����z��
		int cnt = 0;
		/*
		 * �s�����ƂȂ����񐔂�array�z��Ɋi�[����
		 * ���i�[����Ă����萔�͕�����Ȃ�(R.id.sect001���擾����Ȃ�R�[�h�������Ȃ�)�B
		 */
		for(int i = 0;i<array.length;i++){
			System.out.println(i + "���");
			//System.out.println(pref.getString("sect004ques000right",""));
			try{
				if(pref.getString(
						"sect" + df000.format(sectNo) + "ques" + df000.format(i) + "wrong", "").equals("")){
					//��肪�Ȃ��A�������͕s�����̉񓚐����i�[����Ă��Ȃ���Ή������Ȃ�
					System.out.println(i + "��ڂɂ͊i�[����Ă��܂���B");
					
					//�z��̒�����ύX����(���쐬)
					
					
				}else{
					
					array[cnt] = Long.parseLong(pref.getString("sect" + this.sectNoXXX + "ques" + df000.format(i) + "wrong", 0 +""));
					
					System.out.println("array = " + array[cnt]);
					System.out.print("sect" + df000.format(sectNo) + "ques" + df000.format(i) + "right = ");
					System.out.println(pref.getString("sect" + df000.format(sectNo) + "ques" + df000.format(i) + "right", ""));
					System.out.print("sect" + df000.format(sectNo) + "ques" + df000.format(i) + "wrong = ");
					System.out.println(pref.getString("sect" + df000.format(sectNo) + "ques" + df000.format(i) + "wrong", ""));
					System.out.println(cnt + ":" + array[cnt]);
					
					cnt++;
				}
			}catch(Exception e){
				System.out.println(e);
				System.out.println(i + "��ڂ�null");
			}
		}
		
		
		System.out.println("�A���S���Y���\�[�g�J�n");
		/*
		 * �s�����̉񐔂𑽂����ɕ��ёւ�
		 * �A���S���Y���F�ő�l���擾���āA���̎��̍ő�l���擾���āA�E�E�E�J��Ԃ�
		 */
		CombValueRank[] cvr = new CombValueRank[this.NUMOFRANKING];
		for(int i = 0;i<this.NUMOFRANKING;i++){
			cvr[i] = new CombValueRank();
		}
		
		for(int i = 0;i<cvr.length;i++){
			cvr[i].setQNo(9999999);
			cvr[i].setNumOfWrong(0);//�������ɂ����܂ł̉񐔎��s���Ȃ����낤(�y�ϓI�l)
		}
		int maxOfNo = 0;//�ő�l�ƂȂ�z��v�f�ԍ�
		//long max = 0;//�ő�l
		long tempMax = 0;//�T�����ꎞ�I�ő�l
		for(int j = 0;j<cvr.length;j++){
			System.out.println("j = " + j);
			tempMax = 0;
			//����numOfWrong�ɑI������Ă��Ȃ����ōő�ltempMax�̗v�fmaxOfNo��T������
			for(int i = 0;i<array.length;i++){//��r�Ώ�i�Ԗڗv�f���擾
				if(array[i] >= tempMax){//array[i]�����i�K�ő�l�����傫�����
					//�ߋ��Ɋi�[����ĂȂ����m�F��
					int k = 0;//���̃��[�v�̊O�ł��C�e���[�V�������g�����ߊO����
					for(k = 0;k < cvr.length;k++){
						System.out.println(i + ", " + cvr[k].getQNo());
						if(i == cvr[k].getQNo())break;//��i+1�Ԗڍő�l����������̂ł͂Ȃ��A�i�[���ꂽ�C���f�b�N�X(�v�f�ԍ�)�Ō�������K�v
					}
					//�Ō�܂ŒT�����Ă��ߋ���numOfWrong�Ɋi�[����Ă��Ȃ��Ƃ�
					if(k == cvr.length){
						tempMax = array[i];//(���̎��_�ł�)j+1�Ԗڂɑ傫���ő�l
						maxOfNo = i;//(���̎��_�ł�)j+1�Ԗڂɑ傫���ő�l�̗v�f�ԍ�
						cvr[j].setQNo(i);
						cvr[j].setNumOfWrong(array[i]);
						System.out.println(j + "�Ԗڗv�f�T����:" + maxOfNo + "��" + tempMax + "���i�[����\��ł�");
					}
				}//if
			}//for i
			System.out.println("cvr��" + cvr[j].getQNo() + "��" + cvr[j].getNumOfWrong() + "���i�[���܂���");
		}//for j
		
		
		//test
		for(int i = 0;i < array.length;i++){
			System.out.println("array[" + i + "]=" + array[i]);
		}
		
		System.out.println("complete getRanking");
		return cvr;
	}
	private void onDraw(){
		
		/*
		//�e�L�X�g�̕\���ƃ^�C���A���т̕ϐ��i�[
        try{
        	if(pref.getString("sect" + sectNoXXX,"")==null){
	        	System.out.println("finish");
	        	finish();
	        }else{
		        //System.out.println("1." + pref.getString("sect" + this.sectNo,"")+"");
		    	//System.out.println("2." + pref.getString("sect" + this.sectNoXXX,"") + "");
		    	
		    	
		    	TextView tv = (TextView)this.findViewById(R.id.disp_record_title);
		    	System.out.println(tv.toString());
		    	tv.setText("��" + sectNo + "��𓚏�");
		    	
	        }
        }catch(NullPointerException e){
        	System.out.println("XML�t�@�C���Ɏw�肵���^�O�f�[�^���i�[����Ă��܂���B");
        }
		
		*/
		
		
		
		
		
		
		
		
		CombValueRank[] cvr;
		System.out.println("start getRanking");
		cvr = getRanking();
		System.out.println("ranking �i�[��");
		
		//�s����������[���Ȃ�`��͂����ɁA�_�C�A���O�̂ݕ\�����Ĕ�����
		boolean drawFlag = false;//���������܂ŕ`��֎~
		for(int i = 0;i<cvr.length;i++){
			System.out.println("���蒆" + cvr[i].getNumOfWrong());
			if(cvr[i].getNumOfWrong() != 0){
				drawFlag = true;//�`�������
				break;
			}
		}
		if(!drawFlag){//�`��֎~�̎w��������Ȃ�
			System.out.println("�s�������������܂���=>�`��֎~");
			
			//�_�C�A���O�\��
			
			new AlertDialog.Builder(this)
				.setMessage("�s�����̉𓚂���������܂���")
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
		}else{
			System.out.println("�`��J�n");
		}
		
        CategorySeries series = new CategorySeries(null);
        DefaultRenderer renderer = new DefaultRenderer();
        //int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA,
        //                           Color.YELLOW, Color.CYAN, Color.parseColor("#ffffff")};//, Color.RED };
        
        int[] colors = new int[]{ Color.parseColor("#ffff00"),//���F
        						  Color.parseColor("#00ff7f"),//����or 00ff00, 00fa9a
        						  Color.parseColor("#00ffff"),//��
        						  Color.parseColor("#8a2be2"),//�p�[�v��
        						  Color.parseColor("#ff1493")//�s���N
        						};
        /*
         * http://www.colordic.org/
         * Color.parseColor("#0000ff"),//�� or 00bfff
         * ���Fffff00
         */
        
        System.out.println("start �O���t�f�[�^�i�[");
        for(int i = 0;i < cvr.length; i++){
        	if(cvr[i].getNumOfWrong() == 0){
        		//�s���𐔂��Ȃ���΂����ŏI��(�傫�����ɂȂ��Ă���̂Œl���[���Ȃ炻���ŏI��)
        		break;
        	}else{
        		System.out.println("�O���t�v�f" + cvr[i].getQNo() + "��" + cvr[i].getNumOfWrong() + "��ǉ�");
        		//series.add(i);
        		//���ڃ��x���͖��ԍ�(�s����)
        		//���̏��Ԃ͍Ō�ɉ𓚂�����肪�D�悳���B
        		series.add((cvr[i].getQNo() + 1) + "���" + 
        				"(" + cvr[i].getNumOfWrong() + "��)", 
        				new Long(cvr[i].getNumOfWrong()));
        		//series.add("Cupcake", new Integer(40));
        	}
        }
        System.out.println("end �O���t�f�[�^�i�[");
        
        renderer.setLabelsTextSize(15); //���x���̕����T�C�Y
//      renderer.setShowLabels(false); //���x����\�����邩
        renderer.setLegendTextSize(24); //�}��̕����T�C�Y
//      renderer.setShowLegend(false);  //�}���\�����邩
        //for (int color : colors) {
        for(int i = 0;i < this.NUMOFRANKING;i++){
        	System.out.println(i + ", " + i % colors.length);
        	int color = colors[i % colors.length];
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
            System.out.println(i + ", " + i % colors.length);
        }

        GraphicalView pie_chart = ChartFactory.getPieChartView(this, series, renderer);
        LinearLayout chart_area = (LinearLayout) findViewById(R.id.chart_area);
        chart_area.addView(pie_chart);
        System.out.println("�~�O���t�\������");
	}

}

/**
 * ���ԍ��ƕs�����ƂȂ����񓚐����i�[����I�u�W�F�N�g
 * @author oqo52025257
 *
 */
class CombValueRank{
	
	private int combQNo;
	private long combNumOfWrong;
	public CombValueRank(){
		this.combQNo = 0;
		this.combNumOfWrong = 0;
	}
	
	public void setQNo(int value){
		this.combQNo = value;
	}
	public void setNumOfWrong(long value){
		this.combNumOfWrong = value;
	}
	public int getQNo(){
		return combQNo;
	}
	public long getNumOfWrong(){
		return this.combNumOfWrong;
	}
}


/*
String quesNoXXX = new String("");
quesNoXXX = df.format(1);
((TextView)findViewById(R.id.disp_record001)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(2);
((TextView)findViewById(R.id.disp_record002)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(3);
((TextView)findViewById(R.id.disp_record003)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(4);
((TextView)findViewById(R.id.disp_record004)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(5);
((TextView)findViewById(R.id.disp_record005)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(6);
((TextView)findViewById(R.id.disp_record006)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(7);
((TextView)findViewById(R.id.disp_record007)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(8);
((TextView)findViewById(R.id.disp_record008)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(9);
((TextView)findViewById(R.id.disp_record009)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
quesNoXXX = df.format(10);
((TextView)findViewById(R.id.disp_record010)).setText(pref.getString("sect" + quesNoXXX + "ques" + quesNoXXX + "right","no data"));
*/
