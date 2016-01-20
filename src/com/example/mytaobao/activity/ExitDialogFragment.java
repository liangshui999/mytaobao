package com.example.mytaobao.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
/*
 * 点击返回按钮时弹出的对话框
 * */
public class ExitDialogFragment extends DialogFragment {
	/*
	 * 定义了一个内部接口，要在和这个ExitDialogFragment相关联的activity中实现
	 * 这个内部接口主要是用来传递点击事件的
	 * */
	public interface ExitDialogListener {
		public void onDialogPositiveClick(DialogFragment dialog);
		public void onDialogNegativeClick(DialogFragment dialog);
	}
	ExitDialogListener mListener;
	
	/*
	 * 这是这个类自带的方法，通过这个方法和活动绑定
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
	 *在这个方法里面创建对话框 
	 * */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("警告").setMessage("您确定要退出吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						mListener.onDialogPositiveClick(ExitDialogFragment.this);

					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						mListener.onDialogNegativeClick(ExitDialogFragment.this);

					}
				});
		return builder.create();

	}

}
