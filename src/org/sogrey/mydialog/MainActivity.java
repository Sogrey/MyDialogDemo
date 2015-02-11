package org.sogrey.mydialog;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
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
		final TextView txtCommitCancle = (TextView) view
				.findViewById(R.id.txt_dialog_commit_cancle);
		final TextView txtCommitOk = (TextView) view
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
					if (5==indexItem.size()||indexItem.size()==notFinishIndex.size()||index==notFinishIndex.get(notFinishIndex.size()-1)) {
						indexs.add(indexItem);
					}
			}
			lytQuesIndex.setOrientation(LinearLayout.VERTICAL);
			lytQuesIndex.setGravity(Gravity.CENTER);
			LinearLayout lytQuesIndexItem;
			for (ArrayList<Integer> index1 : indexs) {
				lytQuesIndexItem = new LinearLayout(this);
				LinearLayout.LayoutParams lpItem = new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT,
						0, 1);
				lytQuesIndexItem.setLayoutParams(lpItem);
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
							Toast.makeText(MainActivity.this, btn.getText().toString(), Toast.LENGTH_SHORT).show();
						}
					});
					lytQuesIndexItem.addView(btn);
				}
					lytQuesIndex.addView(lytQuesIndexItem);
			}
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
			txtCommitOk.setText(R.string.exit);
		} else {// �ύ����
			txtCommitNotAnswerSure
					.setText(getString(R.string.hint_submit_sure));
			txtCommitOk.setText(R.string.tit_submit_dialog);
		}
		txtCommitCancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				txtCommitCancle.setBackgroundColor(getResources().getColor(R.color.head_backcolor));
				txtCommitOk.setBackgroundColor(getResources().getColor(R.color.tit_prasing_title_backgroud));
				 dialog.dismiss();
			}
		});
		txtCommitOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				txtCommitCancle.setBackgroundColor(getResources().getColor(R.color.tit_prasing_title_backgroud));
				txtCommitOk.setBackgroundColor(getResources().getColor(R.color.head_backcolor));
				 dialog.dismiss();
			}
		});
		LinearLayout.LayoutParams lp0 = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
		view.setLayoutParams( lp0 );
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
        
//      ����Ļ�ߴ簴�������ô�С

//      WindowManager m = getWindowManager();
//      Display d = m.getDefaultDisplay(); // ��ȡ��Ļ������
//      WindowManager.LayoutParams p = dialogWindow.getAttributes(); // ��ȡ�Ի���ǰ�Ĳ���ֵ
//      p.height = (int) (d.getHeight() * 0.6); // �߶�����Ϊ��Ļ��0.6
//      p.width = (int) (d.getWidth() * 0.65); // �������Ϊ��Ļ��0.65
//      dialogWindow.setAttributes(p);

	}
}
