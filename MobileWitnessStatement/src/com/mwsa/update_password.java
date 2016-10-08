package com.mwsa;

import android.app.Activity;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class update_password extends Activity{
	EditText paswd11;
	EditText paswd22;
	String password1;
	String password22;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_password);
	}
	public void goSave(View v) {
		paswd11 = (EditText) findViewById(R.id.pwd11);
		
		paswd22 = (EditText) findViewById(R.id.pwd22);
		
		
		password1 = paswd11.getText().toString();
		password22 = paswd22.getText().toString();
		
		if (password1.equals(password22) && !password1.equals("") && !password22.equals("")) {
			
			
			SharedPreferences prefs = getSharedPreferences("com.mwsa", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			
			editor.putString("PASSWORD", password1);
			
			editor.commit();
			Toast.makeText(update_password.this,
					"Password have been updated. proceed...",
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(
					update_password.this,
					lock.class);
			startActivity(intent);
			finish();
		}
	
			
		 else {
			Toast.makeText(this, "Password must be same and cannot be blank.", Toast.LENGTH_SHORT).show();
		}
	}
	
}


