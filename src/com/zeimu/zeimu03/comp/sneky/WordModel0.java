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
	 * 【完了事項】
	 * 問題文や選択肢、仕訳表を表示し、その反応を定義。
	 * 戻るボタンの反応定義
	 * 解説ボタンの反応定義
	 * 次へボタンの反応定義
	 * 
	 * 【未完了事項】
	 * 
	 * １問題番号表示→済
	 * ２判定機構→一行問題(貸方に二つ以上の項目がある場合に判定が出来なかった問題)は解決済
	 * ３解説ボタン押下時の詳細定義→未解決(selectstory.java内で解説文章を読み込む@strCSVの修正→済
	 * ４ボタンをグローバルに定義し、viewUpdateで統一された(分かりやすい)名前の配列に格納。-> 済
	 * 　viewUpdateメソッド内や他メソッド内で配列として使用できるようにする。→機能だけは解決済
	 * ５正解仕訳表の格納が誤っている→解決済
	 * ６ボタンを何度か押すと無反応→【現状確認】一度選択された項目を取り消した後に更に項目を選択しようとすると二度押ししないと格納されない。→【解決策】再現性検討中(or解決済)
	 * ７仕訳表項目ボタンの値を""にする→解決済
	 * ８最終問題後の処理→intentでstartactivityでSelectStory画面に戻らない。。－>済
	 * ９次の問題にリスナーがついていない→解決済
	 * 10戻るボタン押下時に「問題選択」に行くのではなく、前のページに行く→解決済
	 * 11解説文の表示はダイアログではなく、普通のActivityページにする予定(理由：文字数に対してダイアログ画面の大きさが小さい、かつ、改行が読み取れないため。->済
	 * 12(済)sharedPreferencesで成績表を保存(最速時間と正答率ランキング)
	 * 13(未対応)何も入力せずに次の問題に行こうとすると注意ダイアログが表示→逆に使い勝手が悪いので、もしやるのであれば解答されていればアラート実行するようにする。
	 * 14(済)問題の最初に説明文章を表示し、その際にフリックアクションについての操作方法も表示する->画面下部テキスト表示
	 * 15(未対応)課金型プログラムの作成->http://www.atmarkit.co.jp/fsmart/articles/android_billing02/01.html
	 * 16(済)成績結果xmlファイルの保存->12に等しい
	 * 17(済)サムネイルの作成
	 * 18(済)イメージボタン作成
	 * 19(済)不正解になった部分は再度やりなおし【済】->誤答一覧の表示＆正解文章の表示＆誤答やり直し機能追加
	 * 　　　　　->不正解になった回数の格納(xmlファイルとして保存)
	 * 		   ->クラスを用意して誤答回数フィールドを作成
	 * 		　　->不正解ランキングの順番に実行する機能
	 * 20(済)選択肢の文字数が多過ぎて文字サイズを小さくする
	 * 21(未対応)二章の問題で項目だけ入力して解答ボタンを押すと強制終了。->仕訳表の片側がnullの時のanswerJudgeのロジック確認ー＞再現性再度確認
	 * 22(済)再度やり直し時に時間が加算されてしまう。
	 * 23(済)成績削除をメニュー画面に配置し、誤答のみ再度実行ボタンを問題集領事に表示。
	 * ->誤答のみ再度実行は１最終問題で次へボタンを押下されたとき(finalView)、正誤格納配列(既存)を使って誤り番号だけ誤った回数を加算してshared...に格納する→オプションボタンで表示、削除する。
	 * ->通常解答時に「正解」「誤答」フラグ(既存)を表示する(優先順位劣位)
	 * 24(済）問題選択画面でfinishボタンがエラー->ButtonとImageButtonがキャストできない。オブジェクトは代入すると代入元も影響を植える
	 * 25(済)問題文章を改行する
	 * 26(未対応)計測時間が一定程度大きくなったら計測終了(long passTimeの格納許容量次第)
	 * 27(未対応)問題選択画面でオプション設定？？
	 * 28(済)問題文の文字数が多い場合は文字サイズを縮小する
	 * 29(済)sectXXXtimeとsectXを前者に統一
	 * 30(済)displayRecordクラスで誤答率を表示(円グラフ)→表示したが更新されない→【本日対応】
	 * 31(？未対応)SharedPreferencesを使用する前に、必ず何らかの値を格納すること！->nullPointerExceptionとなる
	 * 32(未対応)問題文章、及び解説文章の大文字このカンマ「，」を小文字カンマ「,」に変換
	 * 33(最優先事項！未対応)貸方の数字のみ非表示にして解答すると落ちる。
	 * 34(済)不正解再実行モードで最後まで行っても終了しない
	 * 35(済)ボタンの文字が切れている
	 * 36(済)最終問題でnextボタンを連続して押せない様にする
	 */
	
	
	/*
	 * 時間計測はコンストラクタ呼び出し時と最初からやり直しボタン押下時のみ開始する(startTime初期化)
	 */
	private long startTime;
	private long endTime;
	private long passTime;
	
	//問題終了時に誤答のみ再実行ボタンを押下されたらtrueにする
	private boolean REPEAT_MODE;
	//不正解問題のみtrueを格納する->全問正解になったら全部falseになって問題選択画面に戻る
	private boolean[] RepeatQuesNumberArray;
	
	private SharedPreferences pref;
	
	private int qNo;//ゼロから始まる
	private StoreData sData;
	private ResultData rData;
	
	private RadioGroup myRadioGroup;
	private boolean finalUpdateFlag;//finalUpdateメソッドを実行できる状態
	
	private TextView txt_title;//質問文章 
	
	private int selectedItemNo;
	private int selectedItem;
	
	private Context myContext;
	@Override
	public void onCreate(Bundle savedInstanceState){
		System.out.println("START WORD MODEL1!!");
		super.onCreate(savedInstanceState);
		System.out.println("onCreate complete!");
		setContentView(R.layout.word_model0);
		
		//このクラスのインスタンスを作成(誤答をXMLファイルに記憶させる際に活用＠finalUpdate)
		myContext = this;
		
		System.out.println("リスナー追加");
		//RadioGroupの初期化
		myRadioGroup = (RadioGroup)findViewById(R.id.myRadioGroup);
		myRadioGroup.setOnCheckedChangeListener(this);
		
		//最初の画面描画時に操作方法をダイアログで表示
		/*
		 * なぜかエラー発生。。→今回は必要ないので表示しない
		new AlertDialog.Builder(this)
			.setTitle("初めに(操作方法)")
			.setMessage("まず最初に問題文を読んでから、適切な語句を作成して下さい。\n" +
					"OKボタンを押すと時間が計測されます。")
			.setPositiveButton("閉じる",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					//do nothing...
				}
			}).show();
		*/
		Intent intent = getIntent();
		//問題データと正解データの受け取り用オブジェクト
		sData = (StoreData)intent.getSerializableExtra("StoreData");
		//System.out.println("the number of question sentence = " + sData.getQuestion().length);

		//sData内に回答文章が格納されている個数だけ、rDataにも判定結果を格納する配列を用意する
		System.out.println(sData.getQuestionNo());
		rData = new ResultData(sData.getQuestionNo());


		//時間計測開始
		startTime = System.currentTimeMillis();
		System.out.println("clock complete initiate");
		
		//画面更新
		this.viewUpdate();
		
		
		
				
		
		//成績表格納用のxmlファイル保存用インスタンスの設定
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		
		System.out.println("complete executing constructor1");
		
		this.RepeatQuesNumberArray = new boolean[sData.getQuestionNo()];
		
		//finalUpdateフラグをtrueにしてメソッドを受け付けられる状態にする
		finalUpdateFlag = true;
		System.out.println("complete executing constructor2");
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /*
     * radioButtonのリスナー(non-Javadoc)
     * @see android.widget.RadioGroup.OnCheckedChangeListener#onCheckedChanged(android.widget.RadioGroup, int)
     */
    public void onCheckedChanged(RadioGroup rGroup, int checkedId){
		
    	RadioButton rb = (RadioButton)findViewById(checkedId);
    	if(rb.isChecked() == true){
    		System.out.println("リスナー発動" + checkedId);
    		selectedItemNo = checkedId+1;
    	}
    	System.out.println("リスナー発動2");
    	Toast.makeText(this,  "選択したラジオボタン:" + rb.getText(),  Toast.LENGTH_SHORT).show();
    }
    
    public void onClick(View v){
    	/**
    	 * 以下の初期化は別メソッドにする(将来的に)
    	 */
	    switch(v.getId()){
	    		/**
	    		 * 選択肢のリアクション定義
	    		 */
	    		
		    	case R.id.st1_previous:
		    		if(this.REPEAT_MODE){
	    				//不正解だった問題まで戻る
		    			//最初まで戻っても不正解問題がなければ問題選択画面に戻る
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
		    						break;//←これがないとエラー(IllegalStateException発動)になる！！
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
		    				//最初の問題で戻るボタンが押されたら問題選択画面に戻る(このActivityを閉じる)
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
		    		//終了確認のダイアログ表示後、終了

		    		//問題が最後まで達したので成績の結果をダイアログで表示します。
		    		 AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

	    	        // ダイアログの設定
	    	        alertDialog.setTitle("ご確認");			//タイトル
	    	        alertDialog.setMessage("終了ボタンが押されました。\n問題を終了しても宜しいですか？");
	    	        //alertDialog.setIcon(R.drawable.icon);	//アイコン設定

	    	        alertDialog.setPositiveButton("終了", new DialogInterface.OnClickListener() {

	    				public void onClick(DialogInterface dialog, int which) {
	    					// TODO 自動生成されたメソッド・スタブ
	    					System.out.println("終了ボタンが押されたので終了します。");
	    					finish();
	    				}
	    			});
	    	        alertDialog.setNegativeButton("キャンセル", new DialogInterface.OnClickListener(){
	    	        	public void onClick(DialogInterface dialog, int which){
	    	        		System.out.println("終了ボタン押下後、キャンセルされました");
	    	        	}
	    	        });
	    	        // ダイアログの作成と表示
	    	        alertDialog.create();
	    	        alertDialog.show();
	    	        break;
		    	    
		    	case R.id.st1_answering:
		    		System.out.println("pressed answering!");
		    		
		    		//RadioButton radioButton = (RadioButton)findViewById(myRadioGroup.getCheckedRadioButtonId());
		    		System.out.println("認識されているボタンは" + myRadioGroup.getCheckedRadioButtonId());
		    		System.out.println("radio0 = " + R.id.radio0);
		    		System.out.println("radio1 = " + R.id.radio1);
		    		System.out.println("radio2 = " + R.id.radio2);
		    		System.out.println("radio3 = " + R.id.radio3);
		    		System.out.println("radio4 = " + R.id.radio4);
		    		switch(myRadioGroup.getCheckedRadioButtonId()){
		    			
		    			case R.id.radio0:
		    				System.out.println("1が押されています。");
		    				selectedItemNo = 1;
		    				break;
		    			case R.id.radio1:
		    				System.out.println("2が押されています。");
		    				selectedItemNo = 2;
		    				break;
		    			case R.id.radio2:
		    				System.out.println("3が押されています。");
		    				selectedItemNo = 3;
		    				break;
		    			case R.id.radio3:
		    				System.out.println("4が押されています。");
		    				selectedItemNo = 4;
		    				break;
		    			case R.id.radio4:
		    				System.out.println("5が押されています");
		    				selectedItemNo = 5;
		    				break;
		    			default:
		    				System.out.println("どれも押されていない！");
		    				selectedItemNo = 0;
		    				break;
		    		}
		    		System.out.println("answerJudge前：選択された番号は" + selectedItemNo);
		    		dialogEither(answerJudge());
		    		System.out.println("answerJudge後：選択された番号は" + selectedItemNo);
	    			return;
	    		
		    	case R.id.st1_next:
		    		System.out.println("pressed next!");
		    		System.out.println("繰り返しモード" + this.REPEAT_MODE);
		    		if(this.REPEAT_MODE){//不正解問題実行モードONならば
						//前回不正解問題までqNo++;を繰り返す
						for(qNo = qNo + 1;qNo<sData.getQuestionNo();qNo++){
							System.out.println("check" + qNo);
							if(this.RepeatQuesNumberArray[qNo]){
								System.out.println(qNo + "は不正解問題なので繰り返します。");
								break;//不正解問題ならばfor文を抜ける
							}else{
								//不正解問題ではないので、何もしないで次の問題を探索する
							}
						}//for qNo
						//繰り返し実行問題を探索した結果、問題文が最後まで行ったら終了
						if(!(qNo<sData.getQuestionNo())){
							//finalUpdateメソッドを呼び出して、成績表を書きにいっても良いが、他の問題の判定が出来ていない。
							System.out.println("繰り返しモードで最終問題を終えたのでシステム終了します。");
							finish();
							return;
						}
					}else{
						qNo++;
						System.out.println("問題番号加算->" + qNo);
					}
		    		System.out.println(qNo + "を表示します。問題格納番号->" + sData.getQuestionNo());
	    			if(qNo < sData.getQuestionNo()){//問題番号が格納数以下なら
	    				if(!(sData.getQuestion(qNo).equals("-"))){//質問文にハイフンがあることはないが念のため
	    					//通常は以下処理が実行されるだけで、条件分岐はされないはず(ハイフンが入ることは無いため)
	    					System.out.println("call viewUpDate by next button!");
	    					//可能なら何も選択されていない場合はalertDialogを発生させる->仕訳表を作成しても次の問題に行ってから戻ると仕訳表が未入力の状態になってしまう。
			    			this.viewUpdate();
	    				}else{//ハイフンになることはないが、念のためリスクヘッジ
			    			System.out.println("恐らくここは制御フローに流れてこない");
			    			
			    		}
	    			}else{// if(qNo >= sData.getQuestion().length)
	    				/**
	    				 * 問題が最後まで達したので成績の結果をダイアログで表示する
	    				 */
	    				//不正解問題再実行モードではここに制御フローは流れない(上のブロックで定義済)
	    				if(finalUpdateFlag){
	    					finalUpdateFlag = false;//連続してfinalUpdateflagを呼び出せない様にする
	    					this.finalUpdate();
	    				}
	    			}
	    			break;
		}
    }
    /**
     * 引数の全てのboolean配列をfalseにする
     * 用途：一つのボタンが押されたとき、他の全てのボタンを解放(false)にする
     *  ※当該ボタンの値はその制御フローの中でtrueにされる。
     *      
     */
    private void ArrayAllFalse(boolean[] args){
    	for(int i = 0;i < args.length;i++){
    		args[i] = false;
    	}
    }
    
    /*
     * 問題が最後まで表示された後にnextボタンを押下されたときに呼び出される
     */
    
    private void finalUpdate(){
    	//正誤結果を結果履歴ファイル(XML)に出力するためのフィールドを持つインスタンス
    	//->コンストラクタを呼び出すだけで格納される
    	AnswerContext ac = new AnswerContext(myContext);
    	ac.inputXML(rData, sData.getSectNo());
    	
    	/*
    	System.out.println(ac.outputXML(sData.getSectNo(), 0, true));
    	System.out.println(ac.outputXML(sData.getSectNo(), 1, true));
    	System.out.println(ac.outputXML(sData.getSectNo(), 2, true));
    	*/
    	
    	//ダイアログタイトル
    	String dialogTitle = new String("");
    	//ダイアログテキスト
    	String dialogText = new String("");
    	
    	//時間の計測
    	endTime = System.currentTimeMillis();
		passTime = endTime - startTime;
		
		//「全問正解の場合」もしくは「初回時」のみ、
		//今回かかった時間が前回よりも早ければ出力を書き出す。
		System.out.println("全問完了");
		System.out.println("回答数=" + qNo);
		System.out.println("うち、正答数=" + rData.getSumRightAnswer());
		
		
		
		DecimalFormat df000 = new DecimalFormat("000");
		/*
		 * 全問正解し、かつ、次の【１】または【２】の場合にのみ今回の時間情報を格納する
		 * 【１】SharedPreferences(XMLファイル)にsectXXXタグのデータが格納されていない(初めて第XXX章を解答した)場合
		 * もしくは
		 * 【２】前回解答よりも早く解答した場合
		 */
		int sectNo = sData.getSectNo();
		String sectNoXXX = df000.format(sectNo);
		if(rData.getSumRightAnswer() == sData.getQuestionNo() &&
				((pref.getString("sect" + sectNoXXX, "")).equals("") || 
				passTime < Long.parseLong(pref.getString("sect" + sectNoXXX, "")))){
			
			
			System.out.println("成績を格納");
			SharedPreferences.Editor editor = pref.edit();
			System.out.println("代入前格納値" + pref.getString("sect" + sectNoXXX,""));
			
			
			dialogTitle = "全問正解！\nタイムも更新しました！";
			dialogText = "正解数 : " + rData.getSumRightAnswer() + 
					"\n正答率 : " + 
					(new DecimalFormat("0.0%")).format(
							((float)rData.getSumRightAnswer() / (float)sData.getQuestionNo())) + 
					"\n今回タイムは" + (new DecimalFormat("0.000")).format((float)passTime/1000) + "秒です";
			
			if(pref.getString("sect" + sectNoXXX,"").equals("")){
				
				dialogText += "\n※前回の記録はありません。";
				
			}else{
				dialogText += "\n前回タイムは" +
						(new DecimalFormat("0.000")).format((float)Long.parseLong(pref.getString("sect" + sectNoXXX, ""))/1000) + "秒でした。";
			}
			
			//成績の格納
			//editor.putString("sect" + (new DecimalFormat("000")).format(sData.getSectNo()) + "time", passTime + "");
			editor.putString("sect" + sectNoXXX ,passTime + "");
			editor.commit();
			System.out.println("成績格納完了->" + pref.getString("sect" + sectNoXXX, ""));

		}else{
			System.out.println("成績を格納しません。その理由は...");
			if(rData.getSumRightAnswer() != sData.getQuestionNo()){
				System.out.println("全問正解でないためです");
				dialogTitle="【残念！】一部解答に誤りがありました＞＜";
				dialogText = "正解数 : " + rData.getSumRightAnswer() + 
						"\n正答率 : " + 
						(new DecimalFormat("0.0%")).format(
								((float)rData.getSumRightAnswer() / (float)sData.getQuestionNo()) 
						) + 
						"\n今回タイムは" + (new DecimalFormat("0.000")).format((float)passTime/1000) + "秒です";
				
			}else if(passTime >= Long.parseLong(pref.getString("sect" + sectNoXXX,""))){
				System.out.println("前回タイムを上回らなかったためです");
				System.out.println("ちなみに前回タイム" + pref.getString("sect" + sectNoXXX,"") + 
						"今回タイム：" + (new DecimalFormat("0.000")).format(passTime/1000));
				
				dialogTitle="【おしい！】全問正解ですが、タイム更新ならず...";
//						Long.parseLong(pref.getString("sect" + sData.getSectNo(), ""))/1000 + "秒)を上回りませんでした＞＜";
				dialogText = "正解数 : " + rData.getSumRightAnswer() + 
						"\n正答率 : " + 
						(new DecimalFormat("0.0%")).format(
								((float)rData.getSumRightAnswer() / (float)sData.getQuestion().length) 
						) + 
						"\n今回タイムは" + (new DecimalFormat("0.000")).format((float)passTime/1000) + "秒です" + 
						"\n前回タイムは" + (new DecimalFormat("0.000")).format((float)Long.parseLong(pref.getString("sect" + sectNoXXX, ""))/1000) + "秒でした。";
			}
			
			System.out.println("経過時間 = " + passTime);
			System.out.println("未格納判定：" + (pref.getString("sect" + sectNoXXX, "")).equals(""));
			System.out.println("最終格納値 = " + pref.getString("sect" + sectNoXXX,""));
			
			
		}
		
		//問題が最後まで達したので成績の結果をダイアログで表示します。
		new AlertDialog.Builder(this)
			.setTitle(dialogTitle)
			.setMessage(dialogText)
			.setPositiveButton("終了",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					/* 押下時の処理 */
					finalUpdateFlag = true;//次にfinalUpdateflagを呼び出せる状態にする
	    			System.out.println("問題番号が最後に達し、終了を選択したので章選択画面に戻ります");
	    			finish();
				}
			})
			.setNegativeButton("もう一度\n最初から", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int whichButton){
					/* 押下時の処理 */
					finalUpdateFlag = true;//次にfinalUpdateflagを呼び出せる状態にする
					System.out.println("問題番号が最後に達し、最初から選択をしました。");
					REPEAT_MODE = false;//不正解問題実行モードOFF
					qNo = 0;
					//時間計測は問題起動時(コンストラクタ起動時)と再実行時(今)！
					startTime = System.currentTimeMillis();
					viewUpdate();
				}
			})
			
			.setNeutralButton("誤答のみ再実行", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int whichButton){
					finalUpdateFlag = true;//次にfinalUpdateflagを呼び出せる状態にする
					System.out.println("誤答のみ再度実行");
					//////////////////
					//System.out.println(new AnswerContext(myContext, rData, sData.getSectNo()).getString());
					//不正解問題の格納
					for(int i = 0;i<RepeatQuesNumberArray.length;i++){
						if(rData.getJudgeArray(i)){
							//今回、正解だった場合
							System.out.println("問題" + i + "は正解");
							RepeatQuesNumberArray[i] = false;
						}else{
							//今回、不正解だった場合
							System.out.println("問題" + i + "は不正解なので繰り返し問題に追加します。");
							RepeatQuesNumberArray[i] = true;
						}
					}
					//不正解問題実行モードON
					REPEAT_MODE = true;
					for(qNo = 0;qNo<sData.getQuestionNo();qNo++){
						//最初に間違えた問題でループを抜ける
						if(RepeatQuesNumberArray[qNo]){
							break;
						}
					}
					if(qNo < sData.getQuestionNo()){
						startTime=System.currentTimeMillis();
						viewUpdate();
					}else{
						System.out.println("全問正解なので繰り返し処理は実行せず終了します。");
						finish();
					}
				}
			})
		.show();
		//http://techbooster.jpn.org/andriod/ui/1140/

    }
    
    private void viewUpdate(){
    	/**
    	 * 描画モジュール
    	 */
    	System.out.println("start view update!");
    	int questionNo = qNo;
    	
    	/*
		System.out.println("qNo = " + qNo);
		
		System.out.println("問題文章 ; " + sData.getQuestion(qNo));
		
		System.out.println("sData.getQuestion().length = " + sData.getQuestion().length);
		System.out.println("sData.getQuestion(" + questionNo + ") = " + sData.getQuestion(qNo));
		*/
    	System.out.println("問題番号" + questionNo);
    	System.out.println("問題格納数" + sData.getQuestion().length);
    	System.out.println("現在の質問内容" + sData.getQuestion(questionNo));
    	if(questionNo < sData.getQuestion().length &&
    			sData.getQuestion(questionNo) != null){
    		
    		/*
    		 * ラジオボタンが押下されても選択された番号が認識されない！！！！＜ーエラー確認(リスナーを記述必要？)
    		 */
    		System.out.println("条件クリアー＞具体的描画開始");
    		//！！！！！！！！！！！！！！！！！！！！
    		//！！！！！！！！！！↓が原因！！！！！！！！！！！！！！！削除による影響確認要！！
    		//this.setContentView(R.layout.word_model0);
    		
    		//章番号の設定
    		txt_title = (TextView)findViewById(R.id.txt_sectName);
    		txt_title.setText("【第" + sData.getSectNo() + "章】" + sData.getCategory());
    		
    		//質問文テキストビューの初期化(オブジェクトのインスタンス化)
    		TextView question = (TextView)this.findViewById(R.id.txt_questSntnc);
    		//テキストビューに改行されたテキストを設定
    		
    		question.setText("(第" + (qNo + 1) + "問) " +  
    				insertParagraph(sData.getQuestion(questionNo)));
    		/*
    		 * 画面再調整機能(問題文の直下に選択肢を配置)により従来の文字列長によるサイズ調整は不要になった
    		if(sData.getQuestion(questionNo).length() > 100){
    			question.setTextSize(12.0f);
    		}else if(sData.getQuestion(questionNo).length() > 70){
    			question.setTextSize(13.0f);
    		}else{
    			question.setTextSize(15.0f);
    		}
    		*/
    		
    		//選択肢の定義
    		System.out.println("ラジオボタン選択肢の個数：" + sData.getSelectionCnt(questionNo));
    		
    		System.out.println("radiogroup");
    		//チェックされているラジオボタンのIDを取得
    		//myRadioGroup.check(R.id.radio0);
    		
    		/*
    		 * <選択肢にsDataに格納された選択肢文字列を代入>
    		 * [今後の改良検討]要素数に応じて自動的に取得
    		 */
    		
    		//5つの選択肢ラジオボタンを作成
    		RadioButton rb[] = new RadioButton[5];
    		rb[0] = (RadioButton)findViewById(R.id.radio0);
    		rb[1] = (RadioButton)findViewById(R.id.radio1);
    		rb[2] = (RadioButton)findViewById(R.id.radio2);
    		rb[3] = (RadioButton)findViewById(R.id.radio3);
    		rb[4] = (RadioButton)findViewById(R.id.radio4);
    		for(int i = 0;i<rb.length;i++){
    			rb[i].setText(sData.getSelection(qNo,i));
    		}
    		
    		System.out.println("描画完了");
    		
    	}else{
    		/**
    		 * 問題番号がsDataの問題配列の長さより大きくなることもないし、
    		 * 問題文章がnull値になることもないので、ここに制御フローが流れることはない。
    		 */
    		System.out.println("例外発生　＠　updateView");
    		//this.setContentView(R.layout.story_model1);
    		//最後の問題を終えた場合、問題終了の旨のダイアログを表示した後、成績表画面(未作成)に移行
    		//Intent intent=new Intent(this, SelectStory.class);
			////intent.putExtra("StoreData", rData);//sDataはsetQSA内で格納されている
			////intent.setAction(Intent.ACTION_VIEW);
			//startActivity(intent);
			
    		finish();
    	}
    	
    }
    
    
    
    private boolean answerJudge(){
    	/**
    	 * 回答ボタンが押された時に呼び出され、
    	 * ダイアログボックスで仕訳表の正解／不正解を判定
    	 */
    	System.out.println("pressed Judgement!");
    	
    	
    	if(sData!=null){
    		System.out.println("qNo = " + qNo);
    		System.out.println("正解 = " + sData.getAnswerNo());
    		System.out.println("問題文章 = " + sData.getQuestion(qNo));
			System.out.println("選択 = " + selectedItemNo);
			
    		if(sData.getAnswerNo(qNo) == selectedItemNo){//正解の場合
    			
    			System.out.println("正解！");
    			return true;
    		}else{//不正解の場合
    			System.out.println("不正解！");
    			System.out.println(qNo + "問目の正解は" + sData.getAnswerNo(qNo));
    			System.out.println("今回選択は" + selectedItemNo);
    			return false;
    		}
    		
    	}else{
    		System.out.println("sData is null!");
    		//sDataがnullの場合は不一致フラグを返す
			return false;
		}
    	
    }
    
    //画面上の解答ボタンが押下されたときにanswerJudgement関数の返り値(正誤判断)を表示する
    private void dialogEither(boolean judgement){
    	
    	String strJudgement = null;
    	System.out.println("called dialog!!");
    	if(judgement){
    		strJudgement = "正解！:" + selectedItemNo;
    		rData.setJudgement(qNo, true);
    		System.out.println("正解！：今までの正解数" + rData.getSumRightAnswer());
    	}else{
    		strJudgement = "不正解!:" + selectedItemNo;
    		rData.setJudgement(qNo, false);
    		System.out.println("不正解！：今までの正解数" + rData.getSumRightAnswer());
    	}
		new AlertDialog.Builder(WordModel0.this)
			.setMessage(strJudgement)
			.setCancelable(false)
			.setPositiveButton("次の問題へ", 
					new  DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int id){
							//次の問題へ
							if(REPEAT_MODE){//不正解問題実行モードONならば
								//前回不正解問題までqNo++;を繰り返す
								for(qNo = qNo + 1;qNo<sData.getQuestionNo();qNo++){
									System.out.println("Repeat = " + RepeatQuesNumberArray[qNo]);
									if(RepeatQuesNumberArray[qNo]){
										break;//不正解問題ならばfor文を抜ける
									}
								}
							}else{
								qNo++;
							}
							if(qNo < sData.getQuestion().length){//問題番号が格納数以下なら
								//質問番号をインプルメント(＋１)した上で画面更新
								System.out.println("finalUpdate execute! @ " + qNo);
			    				viewUpdate();
							}else{
								//それ以上問題数がない場合
								System.out.println("finalUpdate execute! @ " + qNo);
								finalUpdate();
							}
						}
			})
			.setNegativeButton("解説表示",
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
     * #を改行する
     * @param arg
     * @return
     */
    private String insertParagraph(String arg){
    	System.out.println("complete reading givenData");
    	StringTokenizer st = null;
    	String line2[] = null;
        try{
        	//ストリングトーカナイザーを使って#で改行する
        	st = new StringTokenizer(arg, "#");
	        
	        String line[] = new String[10];//最大10行程度とする
	        //#トークンで分解する
	        int cnt = 0;
	        while(st.hasMoreTokens()){
	        	line[cnt]=st.nextToken();
	        	cnt++;
	        }
	        //分解されたトークンの数だけの要素を持つ文字列配列を作成し、分解されたトークンを格納
	        line2 = new String[cnt];
	        System.arraycopy(line, 0, line2, 0, cnt);
	        
        }catch(Exception e){
        	System.out.println("解説文章表示ページでトークン分解エラーが発生しました。");
        	System.out.println(e);
        }
        
        System.out.println("complete reconstructing givenData");
        //とりあえずは解説文を一括表示
        
        String out = "";
        for(int i = 0;i<line2.length;i++){
        	out += "\n" + line2[i];
        }
    	
    	return out;
    }
    
    

}
