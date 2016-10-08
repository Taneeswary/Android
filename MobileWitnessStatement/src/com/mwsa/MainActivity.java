package com.mwsa;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
	}

	public void doUpdateDetail(View v) {
		Intent intent = new Intent(this, update_details.class);
		startActivity(intent);
		
	}
	public void doUpdatePassword(View v) {
		Intent intent = new Intent(this, update_password.class);
		startActivity(intent);
		
	}
	public void doCreateIncident(View v) {
		Intent intent = new Intent(this, incident.class);
		startActivity(intent);
		finish();
	}
	public void doExitApplication(View v) {
			
				finish();
	}
}