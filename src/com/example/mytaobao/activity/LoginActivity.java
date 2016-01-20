package com.example.mytaobao.activity;

import com.example.mytaobao.R;
import com.example.mytaobao.R.id;
import com.example.mytaobao.R.layout;
import com.example.mytaobao.R.menu;
import com.example.mytaobao.biz.Init;
import com.example.mytaobao.biz.UserManager;
import com.example.mytaobao.model.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends FragmentActivity implements android.view.View.OnClickListener, ExitDialogFragment.ExitDialogListener{	
	private Button loginButton;
	private Button registerButton;
	private EditText userIdEditText;
	private EditText passwordEditText;
	private UserManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        loginButton=(Button)findViewById(R.id.login_button);        
        registerButton=(Button)findViewById(R.id.register_button);
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        userIdEditText=(EditText)findViewById(R.id.edit_userId);
        passwordEditText=(EditText)findViewById(R.id.edit_password);
        
    }
    
    @Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		showExitDialog();
	}

	//用于显示ExitDialog
	private void showExitDialog() {
		ExitDialogFragment exitDialogFragment=new ExitDialogFragment();
        exitDialogFragment.show(getFragmentManager(), "ExitDialogFragment");
	}
   
    //确定按钮点击之后会关闭当前活动
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		finish();
	}
	//当你点击了dialog之后，系统会自动帮你关闭，所以取消按钮可以不用写方法
	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}
	//活动中的按钮点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.login_button:
			userManager=Init.getUserManager();
			//MyLog.d("MainActivity", "haha"+userManager.login(userIdEditText.toString(), passwordEditText.toString()).getUserId());
			User user= userManager.login(userIdEditText.getText().toString(), passwordEditText.getText().toString());
			if(user!=null){
				Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
				Intent intent=new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
			}else{
				Toast.makeText(this, "用户不存在或者密码不正确", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.register_button:
			Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
			break;
		}
	}

	
	
	
    
    


    
}
