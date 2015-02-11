package org.sogrey.mydialog;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {
	Button btn;
	private Dialog dialog;
	boolean isBack = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mian);
		initDatas();
		initViews();
	}

	private void initViews() {
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.show();
			}
		});

	}

	private void initDatas() {

		ArrayList<Integer> notFinishIndex = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
//			if (i % 3 == 0) {
				notFinishIndex.add(i + 1);
//			}
		}
		View view = getLayoutInflater().inflate(R.layout.view_dialog_commit,
				null);
		Button btnClosed = (Button) view
				.findViewById(R.id.view_dialog_commit_closed);
		LinearLayout lytQuesIndex = (LinearLayout) view
				.findViewById(R.id.lyt_dialog_commit_not_answer_index);
		LinearLayout lytCommitSure = (LinearLayout) view
				.findViewById(R.id.lyt_dialog_commit_sure);
		LinearLayout lytCommitNotAnswer = (LinearLayout) view
				.findViewById(R.id.lyt_dialog_commit_not_answer_count);
		TextView txtCommitNotAnswerCount = (TextView) view
				.findViewById(R.id.txt_submit_not_answer_count);
		TextView txtCommitNotAnswerSure = (TextView) view
				.findViewById(R.id.txt_submit_not_answer_sure);
		TextView txtCommitCancle = (TextView) view
				.findViewById(R.id.txt_dialog_commit_cancle);
		TextView txtCommitOk = (TextView) view
				.findViewById(R.id.txt_dialog_commit_ok);
		
		btnClosed.setOnClickListener(new OnClickListener() {// 关闭按钮点击

					@Override
					public void onClick(View v) {
						 dialog.dismiss();
					}
				});

		if (null != notFinishIndex && 0 != notFinishIndex.size()){
			ArrayList<ArrayList<Integer> >  indexs = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> indexItem = null;
			for (Integer index : notFinishIndex) {
				if (null==indexItem||5==indexItem.size()) {
					indexItem = new ArrayList<Integer>();
				}
					indexItem.add(index);
					if (5==indexItem.size()||indexItem.size()==notFinishIndex.size()) {
						indexs.add(indexItem);
					}
			}
			lytQuesIndex.setGravity(Gravity.CENTER);
			LinearLayout lytQuesIndexItem;
			for (ArrayList<Integer> index1 : indexs) {
				lytQuesIndexItem = new LinearLayout(this);
				lytQuesIndexItem.setOrientation(LinearLayout.HORIZONTAL);
				lytQuesIndexItem.setGravity(Gravity.CENTER);
				for (Integer index2 : index1) {
					final Button btn = new Button(this);
					btn.setBackgroundResource(R.drawable.btn_exam_head_question_index_unanswered);
					btn.setText(String.valueOf(index2));
					btn.setGravity(Gravity.CENTER);
					btn.setTextSize(getResources().getDimension(
							R.dimen.frontsize_normal));
					btn.setTextColor(getResources().getColor(R.color.black));
					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
							60, 60);
					lp.setMargins(5, 5, 5, 5);
					btn.setLayoutParams(lp);
					btn.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// AnswerExamPaperService.service.mQusetIndex = index;
							// AnswerExamPaperService.service.mQusetIndexBeforeChange
							// =index;
							// Intent intent = new Intent(this,
							// class);// 跳回到自身，只改变题目
							// this.startActivity(intent);
							// this.finish();
							Toast.makeText(MainActivity.this, btn.getText().toString(), Toast.LENGTH_SHORT).show();
						}
					});
					lytQuesIndexItem.addView(btn);
				}
//				if (5==lytQuesIndexItem.getChildCount()) {
					lytQuesIndex.addView(lytQuesIndexItem);
//				}
			}
//			for (final Integer index : notFinishIndex) {}
		}
		
		txtCommitNotAnswerCount.setText(notFinishIndex.size() + "");
		if (null == notFinishIndex || 0 == notFinishIndex.size()) {// 答完
			lytQuesIndex.setVisibility(View.GONE);
			lytCommitSure.setVisibility(View.VISIBLE);
			lytCommitNotAnswer.setVisibility(View.GONE);
		} else {// 没答完
			lytQuesIndex.setVisibility(View.VISIBLE);
			lytCommitSure.setVisibility(View.GONE);
			lytCommitNotAnswer.setVisibility(View.VISIBLE);
		}
		if (isBack) {// 退出答题
			txtCommitNotAnswerSure
					.setText(getString(R.string.hint_submit_exit));
			// okButton = getString(R.string.exit);
			txtCommitOk.setText(R.string.exit);
		} else {// 提交答题
			txtCommitNotAnswerSure
					.setText(getString(R.string.hint_submit_sure));
			// okButton = getString(R.string.ok);
			txtCommitOk.setText(R.string.tit_submit_dialog);
		}
		txtCommitCancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				 dialog.dismiss();
				// if (mDialogCommit != null) {
				// mDialogCommit.cancel();
				// }
			}
		});
		txtCommitOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent();
				// intent.putExtra("dis", Constants.RECEIVER_SUBMIT_ANSWER);//
				// 提交答案
				// intent.putExtra("finished", finished);
				// intent.putExtra("isBack", isBack);
				// intent.putExtra("list_not_finished", list_not_finished);
				// intent.putExtra("type",
				// AnswerExamPaperService.service.mCurrentTypeId);//
				// 测试类型：题型。章节。测试
				// intent.setAction(Constants.RECEIVER_EXAM_PAPER_INFO);//
				// action与接收器相同
				// sendBroadcast(intent);
				 dialog.dismiss();
			}
		});
//		builder.setView(view);
//		dialog.setContentView(R.layout.view_dialog_commit);
//		dialog.addContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		LinearLayout.LayoutParams lp0 = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
		view.setLayoutParams( lp0 );
//		builder.setView(view);
//		dialog = builder.create();
		dialog = new Dialog(this);
		 dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(view);
		dialog.setTitle("提示");
		dialog.setCancelable(false);
		/* 
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置,
         * 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);

        /*
         * lp.x与lp.y表示相对于原始位置的偏移.
         * 当参数值包含Gravity.LEFT时,对话框出现在左边,所以lp.x就表示相对左边的偏移,负值忽略.
         * 当参数值包含Gravity.RIGHT时,对话框出现在右边,所以lp.x就表示相对右边的偏移,负值忽略.
         * 当参数值包含Gravity.TOP时,对话框出现在上边,所以lp.y就表示相对上边的偏移,负值忽略.
         * 当参数值包含Gravity.BOTTOM时,对话框出现在下边,所以lp.y就表示相对下边的偏移,负值忽略.
         * 当参数值包含Gravity.CENTER_HORIZONTAL时
         * ,对话框水平居中,所以lp.x就表示在水平居中的位置移动lp.x像素,正值向右移动,负值向左移动.
         * 当参数值包含Gravity.CENTER_VERTICAL时
         * ,对话框垂直居中,所以lp.y就表示在垂直居中的位置移动lp.y像素,正值向右移动,负值向左移动.
         * gravity的默认值为Gravity.CENTER,即Gravity.CENTER_HORIZONTAL |
         * Gravity.CENTER_VERTICAL.
         * 
         * 本来setGravity的参数值为Gravity.LEFT | Gravity.TOP时对话框应出现在程序的左上角,但在
         * 我手机上测试时发现距左边与上边都有一小段距离,而且垂直坐标把程序标题栏也计算在内了,
         * Gravity.LEFT, Gravity.TOP, Gravity.BOTTOM与Gravity.RIGHT都是如此,据边界有一小段距离
         */
//        lp.x = 100; // 新位置X坐标
//        lp.y = 100; // 新位置Y坐标
        lp.width = 600; // 宽度
        lp.height = 400; // 高度
        lp.alpha = 0.7f; // 透明度

        // 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
        // dialog.onWindowAttributesChanged(lp);
        dialogWindow.setAttributes(lp);
		
		// return dialog;
	}
}
