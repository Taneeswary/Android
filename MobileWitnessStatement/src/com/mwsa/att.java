package com.mwsa;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class att extends Activity{
	private static String filename;
	public Button send;
	public Button bck;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		
		send = (Button)findViewById(R.id.sndbtn);
		send.setOnClickListener(new View.OnClickListener() {
             
			  @Override
	            public void onClick(View arg0) {
				  if(isNetworkAvailable()) {
						SharedPreferences prefs = getSharedPreferences("com.mwsa", Context.MODE_PRIVATE);
						String email = prefs.getString("EMAIL", null);
						filename=prefs.getString("FILENAME", null);
						send_mail runner = new send_mail();
						runner.execute(email, filename);
						Toast.makeText(att.this, "Email sent.", Toast.LENGTH_SHORT).show();
					} else {
						SharedPreferences preferences = getSharedPreferences("com.mwsa", Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = preferences.edit();
						editor.putBoolean("PENDING_EMAIL", true);
						editor.putString("FILENAME", filename);
						editor.commit();
						
						Toast.makeText(att.this, "Email pending. Waiting for network connection.", Toast.LENGTH_SHORT).show();
					}
					
	            }
	        });
		bck = (Button)findViewById(R.id.hme);
		bck.setOnClickListener(new View.OnClickListener() {
             
			  @Override
	            public void onClick(View arg0) {
				 
				  Intent intent = new Intent(att.this, MainActivity.class);
					startActivity(intent);

					finish();
	            }
	        });
	}
	
	
    private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
			}
	

