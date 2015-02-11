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
		
		btnClosed.setOnClickListener(new OnClickListener() {// �رհ�ť���

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
							// class);// ���ص�����ֻ�ı���Ŀ
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
		if (null == notFinishIndex || 0 == notFinishIndex.size()) {// ����
			lytQuesIndex.setVisibility(View.GONE);
			lytCommitSure.setVisibility(View.VISIBLE);
			lytCommitNotAnswer.setVisibility(View.GONE);
		} else {// û����
			lytQuesIndex.setVisibility(View.VISIBLE);
			lytCommitSure.setVisibility(View.GONE);
			lytCommitNotAnswer.setVisibility(View.VISIBLE);
		}
		if (isBack) {// �˳�����
			txtCommitNotAnswerSure
					.setText(getString(R.string.hint_submit_exit));
			// okButton = getString(R.string.exit);
			txtCommitOk.setText(R.string.exit);
		} else {// �ύ����
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
				// �ύ��
				// intent.putExtra("finished", finished);
				// intent.putExtra("isBack", isBack);
				// intent.putExtra("list_not_finished", list_not_finished);
				// intent.putExtra("type",
				// AnswerExamPaperService.service.mCurrentTypeId);//
				// �������ͣ����͡��½ڡ�����
				// intent.setAction(Constants.RECEIVER_EXAM_PAPER_INFO);//
				// action���������ͬ
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
		dialog.setTitle("��ʾ");
		dialog.setCancelable(false);
		/* 
         * ��ȡʥ����Ĵ��ڶ��󼰲����������޸ĶԻ���Ĳ�������,
         * ����ֱ�ӵ���getWindow(),��ʾ������Activity��Window
         * ����,�����������ͬ���ķ�ʽ�ı����Activity������.
         */
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);

        /*
         * lp.x��lp.y��ʾ�����ԭʼλ�õ�ƫ��.
         * ������ֵ����Gravity.LEFTʱ,�Ի�����������,����lp.x�ͱ�ʾ�����ߵ�ƫ��,��ֵ����.
         * ������ֵ����Gravity.RIGHTʱ,�Ի���������ұ�,����lp.x�ͱ�ʾ����ұߵ�ƫ��,��ֵ����.
         * ������ֵ����Gravity.TOPʱ,�Ի���������ϱ�,����lp.y�ͱ�ʾ����ϱߵ�ƫ��,��ֵ����.
         * ������ֵ����Gravity.BOTTOMʱ,�Ի���������±�,����lp.y�ͱ�ʾ����±ߵ�ƫ��,��ֵ����.
         * ������ֵ����Gravity.CENTER_HORIZONTALʱ
         * ,�Ի���ˮƽ����,����lp.x�ͱ�ʾ��ˮƽ���е�λ���ƶ�lp.x����,��ֵ�����ƶ�,��ֵ�����ƶ�.
         * ������ֵ����Gravity.CENTER_VERTICALʱ
         * ,�Ի���ֱ����,����lp.y�ͱ�ʾ�ڴ�ֱ���е�λ���ƶ�lp.y����,��ֵ�����ƶ�,��ֵ�����ƶ�.
         * gravity��Ĭ��ֵΪGravity.CENTER,��Gravity.CENTER_HORIZONTAL |
         * Gravity.CENTER_VERTICAL.
         * 
         * ����setGravity�Ĳ���ֵΪGravity.LEFT | Gravity.TOPʱ�Ի���Ӧ�����ڳ�������Ͻ�,����
         * ���ֻ��ϲ���ʱ���־�������ϱ߶���һС�ξ���,���Ҵ�ֱ����ѳ��������Ҳ����������,
         * Gravity.LEFT, Gravity.TOP, Gravity.BOTTOM��Gravity.RIGHT�������,�ݱ߽���һС�ξ���
         */
//        lp.x = 100; // ��λ��X����
//        lp.y = 100; // ��λ��Y����
        lp.width = 600; // ���
        lp.height = 400; // �߶�
        lp.alpha = 0.7f; // ͸����

        // ��Window��Attributes�ı�ʱϵͳ����ô˺���,����ֱ�ӵ�����Ӧ������Դ��ڲ����ĸ���,Ҳ������setAttributes
        // dialog.onWindowAttributesChanged(lp);
        dialogWindow.setAttributes(lp);
		
		// return dialog;
	}
}
