package com.mwsa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class update_details extends Activity {
	public EditText name;
	public EditText ic;
	public EditText hpNum;
	public EditText email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_detail);
	}
	public void goSave(View v) {
		 name = (EditText) findViewById(R.id.name1);
		 ic = (EditText) findViewById(R.id.ic1);
		 hpNum = (EditText) findViewById(R.id.hpNum1);
		 email = (EditText) findViewById(R.id.email1);
		
		email.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		String name11 = name.getText().toString();
		String ic11 = ic.getText().toString();
		String hpNum11 = hpNum.getText().toString();
		
		
		if (!name11.equals("") && !ic11.equals("") && !hpNum11.equals("") && !email.equals("")) {
			SharedPreferences prefs = this.getSharedPreferences(
					"com.mwsa", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs
					.edit();
			
			editor.putString("NAME", name.getText().toString());
			editor.putString("IC", ic.getText().toString());
			editor.putString("HPNUM", hpNum.getText().toString());
			editor.putString("EMAIL", email.getText().toString());
			editor.commit();
			Toast.makeText(this, "Details updated. Proceed...", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
finish();
			
		}
		else {
			Toast.makeText(this, "details cannot be blank.", Toast.LENGTH_SHORT).show();
		}
	
}
	}

