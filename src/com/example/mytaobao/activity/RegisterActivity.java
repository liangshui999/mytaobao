package com.example.mytaobao.activity;

import com.example.mytaobao.R;
import com.example.mytaobao.biz.Init;
import com.example.mytaobao.biz.UserManager;
import com.example.mytaobao.model.User;
import com.example.mytaobao.util.MyLog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener{
	private EditText userId;
	private EditText password;
	private EditText password2;
	private EditText checkCodeEditText;//输入的验证码
	private TextView checkUserId;
	private TextView checkPassword;
	private TextView checkPassword2;
	private TextView checkCheckCode;
	private TextView checkCodeTextView;//自动生成的验证码
	private Button registerButton;
	private UserManager userManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		init();
		registerButton.setOnClickListener(this);
		checkCodeTextView.setOnClickListener(this);
	}
	//实例化控件以及其他的一些实例化
	
	private void init() {
		userId=(EditText) findViewById(R.id.edit_register_userId);
		password=(EditText) findViewById(R.id.edit_register_password);
		password2=(EditText) findViewById(R.id.edit_register_password_2);
		checkCodeEditText=(EditText) findViewById(R.id.edittext_checkcode);
		checkUserId=(TextView) findViewById(R.id.check_register_userId);
		checkPassword=(TextView) findViewById(R.id.check_register_password);
		checkPassword2=(TextView) findViewById(R.id.check_register_password_2);
		checkCheckCode=(TextView) findViewById(R.id.check_edittext_checkcode);
		checkCodeTextView=(TextView) findViewById(R.id.textview_checkcode);
		registerButton=(Button) findViewById(R.id.register_register_button);
		userManager=Init.getUserManager();
	}
	//给验证码设置初始化的值
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		checkCodeTextView.setText(userManager.getCheckCode());
		
	}
	//设置注册按钮和验证码textview点击时的事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		//注册按钮的点击事件，先验证信息是否正确，正确就跳转到登录界面
		case R.id.register_register_button:
			check();
			if(checkUserId.getText().toString().equals("√")&&checkPassword.getText().toString().equals("√")
					&&checkPassword2.getText().toString().equals("√")&&checkCheckCode.getText().toString().equals("√")){
				userManager.register(new User(userId.getText().toString(),password.getText().toString()));
				MyLog.d("RegisterActivity", userId.getText().toString()+password.getText().toString());
				Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
				//Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
				//startActivity(intent);
			}else{
				Toast.makeText(this, "注册失败", Toast.LENGTH_LONG).show();
			}
			break;
		//切换验证码的点击事件，点击时随机生成验证码
		case R.id.textview_checkcode:
			checkCodeTextView.setText(userManager.getCheckCode());
			break;
		default:
			break;
		}
		
	}
	//检查账号是否已经注册，密码验证码是否输入正确
	//注意这里面的条件设置时，要用userId.getText().toString().equals("")，而不能用userId.getText().toString()==null来判断
	@SuppressWarnings("unused")
	public void check(){
		//先检查账号是否重复
		MyLog.d("RegisterActivity", userId.getText().toString().equals("")+"hhahah");
		if(userId.getText().toString().equals("")){
			checkUserId.setText("×");
		}else {			
			if(userManager.isExist(userId.getText().toString())){
				//Toast.makeText(this, "用户名已经存在了", Toast.LENGTH_LONG).show();
				checkUserId.setText("×");
			}else{
				checkUserId.setText("√");
			}			
		}
		//检查密码长度是否大于6位，是否包含数字和字母
		String passwordContent=password.getText().toString();
		String number="[0-9]";
		String letter="[a-zA-Z]";
		String regex1="[a-zA-Z0-9]{6,}";		
		if(passwordContent==null){
			checkPassword.setText("×");
		}else{
			if(passwordContent.matches(regex1)){
				checkPassword.setText("√");
			}else{
				checkPassword.setText("×");
			}
		}
		//检查2次密码输入是否一样
		String passwordContent2=password2.getText().toString();
		MyLog.d("RegisterActivity", passwordContent2.equals("")+"hehehehhe");
		if(passwordContent2.equals("")){
			checkPassword2.setText("×");			
		}else{
			if(passwordContent2.equals(passwordContent)){
				checkPassword2.setText("√");
			}else{
				checkPassword2.setText("×");
			}			
		}
		//检查验证码是否输入正确
		String checkCodeContent=checkCodeEditText.getText().toString();
		if(checkCodeContent==null){
			checkCheckCode.setText("×");
		}
		else{
			if(checkCodeContent.equals(checkCodeTextView.getText().toString())){
				checkCheckCode.setText("√");
			}else{
				checkCheckCode.setText("×");
			}
		}
	}

}
