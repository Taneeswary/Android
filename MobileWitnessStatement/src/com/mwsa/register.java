package com.mwsa;
import android.app.Activity;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class register extends Activity{
	EditText paswd1;
	EditText paswd2;
	String password;
	String password1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password_setting);
	}
	public void goSave(View v) {
		paswd1 = (EditText) findViewById(R.id.pwd1);
		
		paswd2 = (EditText) findViewById(R.id.pwd2);
		
		
		password = paswd1.getText().toString();
		password1 = paswd2.getText().toString();
		
		if (password.equals(password1) && !password.equals("") && !password1.equals("")) {
			
			
			SharedPreferences prefs = getSharedPreferences("com.mwsa", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("FIRSTRUN", false);
			editor.putString("PASSWORD", password);
			
			editor.commit();
			Toast.makeText(register.this,
					"Password have been registered. proceed...",
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(
					register.this,
					reg_details.class);
			startActivity(intent);
			finish();
		}
	
			
		 else {
			Toast.makeText(this, "Password must be same and cannot be blank.", Toast.LENGTH_SHORT).show();
		}
	}
	
}