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
	private EditText checkCodeEditText;//�������֤��
	private TextView checkUserId;
	private TextView checkPassword;
	private TextView checkPassword2;
	private TextView checkCheckCode;
	private TextView checkCodeTextView;//�Զ����ɵ���֤��
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
	//ʵ�����ؼ��Լ�������һЩʵ����
	
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
	//����֤�����ó�ʼ����ֵ
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		checkCodeTextView.setText(userManager.getCheckCode());
		
	}
	//����ע�ᰴť����֤��textview���ʱ���¼�
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		//ע�ᰴť�ĵ���¼�������֤��Ϣ�Ƿ���ȷ����ȷ����ת����¼����
		case R.id.register_register_button:
			check();
			if(checkUserId.getText().toString().equals("��")&&checkPassword.getText().toString().equals("��")
					&&checkPassword2.getText().toString().equals("��")&&checkCheckCode.getText().toString().equals("��")){
				userManager.register(new User(userId.getText().toString(),password.getText().toString()));
				MyLog.d("RegisterActivity", userId.getText().toString()+password.getText().toString());
				Toast.makeText(this, "ע��ɹ�", Toast.LENGTH_LONG).show();
				//Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
				//startActivity(intent);
			}else{
				Toast.makeText(this, "ע��ʧ��", Toast.LENGTH_LONG).show();
			}
			break;
		//�л���֤��ĵ���¼������ʱ���������֤��
		case R.id.textview_checkcode:
			checkCodeTextView.setText(userManager.getCheckCode());
			break;
		default:
			break;
		}
		
	}
	//����˺��Ƿ��Ѿ�ע�ᣬ������֤���Ƿ�������ȷ
	//ע�����������������ʱ��Ҫ��userId.getText().toString().equals("")����������userId.getText().toString()==null���ж�
	@SuppressWarnings("unused")
	public void check(){
		//�ȼ���˺��Ƿ��ظ�
		MyLog.d("RegisterActivity", userId.getText().toString().equals("")+"hhahah");
		if(userId.getText().toString().equals("")){
			checkUserId.setText("��");
		}else {			
			if(userManager.isExist(userId.getText().toString())){
				//Toast.makeText(this, "�û����Ѿ�������", Toast.LENGTH_LONG).show();
				checkUserId.setText("��");
			}else{
				checkUserId.setText("��");
			}			
		}
		//������볤���Ƿ����6λ���Ƿ�������ֺ���ĸ
		String passwordContent=password.getText().toString();
		String number="[0-9]";
		String letter="[a-zA-Z]";
		String regex1="[a-zA-Z0-9]{6,}";		
		if(passwordContent==null){
			checkPassword.setText("��");
		}else{
			if(passwordContent.matches(regex1)){
				checkPassword.setText("��");
			}else{
				checkPassword.setText("��");
			}
		}
		//���2�����������Ƿ�һ��
		String passwordContent2=password2.getText().toString();
		MyLog.d("RegisterActivity", passwordContent2.equals("")+"hehehehhe");
		if(passwordContent2.equals("")){
			checkPassword2.setText("��");			
		}else{
			if(passwordContent2.equals(passwordContent)){
				checkPassword2.setText("��");
			}else{
				checkPassword2.setText("��");
			}			
		}
		//�����֤���Ƿ�������ȷ
		String checkCodeContent=checkCodeEditText.getText().toString();
		if(checkCodeContent==null){
			checkCheckCode.setText("��");
		}
		else{
			if(checkCodeContent.equals(checkCodeTextView.getText().toString())){
				checkCheckCode.setText("��");
			}else{
				checkCheckCode.setText("��");
			}
		}
	}

}
