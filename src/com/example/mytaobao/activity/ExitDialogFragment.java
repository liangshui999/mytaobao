package com.example.mytaobao.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
/*
 * ������ذ�ťʱ�����ĶԻ���
 * */
public class ExitDialogFragment extends DialogFragment {
	/*
	 * ������һ���ڲ��ӿڣ�Ҫ�ں����ExitDialogFragment�������activity��ʵ��
	 * ����ڲ��ӿ���Ҫ���������ݵ���¼���
	 * */
	public interface ExitDialogListener {
		public void onDialogPositiveClick(DialogFragment dialog);
		public void onDialogNegativeClick(DialogFragment dialog);
	}
	ExitDialogListener mListener;
	
	/*
	 * ����������Դ��ķ�����ͨ����������ͻ��
	 * */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (ExitDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }

	}

	/*
	 *������������洴���Ի��� 
	 * */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("����").setMessage("��ȷ��Ҫ�˳���")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						mListener.onDialogPositiveClick(ExitDialogFragment.this);

					}
				})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						mListener.onDialogNegativeClick(ExitDialogFragment.this);

					}
				});
		return builder.create();

	}

}
