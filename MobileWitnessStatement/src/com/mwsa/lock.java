package com.mwsa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class lock extends Activity{
	private TextView tv1;
	private EditText pswd;
	private String password;
	
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.lock_scr);
	
	SharedPreferences prefs = this.getSharedPreferences(
			"com.mwsa", Context.MODE_PRIVATE);
	if (prefs.getBoolean("FIRSTRUN", true)) {
		Intent i = new Intent(this, register.class);
		startActivity(i);

		finish();
	}	
	
tv1 = (TextView) findViewById(R.id.tv1);
tv1.setOnClickListener(new OnClickListener() {

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		SharedPreferences prefs = getSharedPreferences(
				"com.mwsa", Context.MODE_PRIVATE);
		String email = prefs.getString("EMAIL", " @ ");
		password = prefs.getString("PASSWORD", null);
		send_mail runner = new send_mail();
		runner.execute(email, password.replace(",", ""));

		Toast.makeText(
				lock.this,
				"Your password has been sent to your email (" + email
						+ ").", Toast.LENGTH_SHORT).show();
	}
});
}
	public void doLogin(View v) {
		SharedPreferences prefs = this.getSharedPreferences(
				"com.mwsa", Context.MODE_PRIVATE);
		password = prefs.getString("PASSWORD", null);
	
	 pswd = (EditText) findViewById(R.id.editText1);
	String password1 = pswd.getText().toString();
	if (password.equals(password1)) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);

		finish();
	}else {
		Toast.makeText(this, "Invalid password. Please try again.",
				Toast.LENGTH_SHORT).show();
	}
}
	

	
	
	

}
