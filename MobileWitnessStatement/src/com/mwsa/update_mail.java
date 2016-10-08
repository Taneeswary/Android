package com.mwsa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
public class update_mail extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();
		
		if(isConnected) {
			System.out.println("NETWORK AVAILABLE");
			SharedPreferences prefs = context.getSharedPreferences("com.mwsa", Context.MODE_PRIVATE);
			
			if(prefs.getBoolean("PENDING_EMAIL", false)) {
				System.out.println("SENDING PENDING EMAIL");
				
				String email = prefs.getString("EMAIL", "");
				String filename = prefs.getString("FILENAME", "");
				
				send_mail runner = new send_mail();
				runner.execute(email, filename);
				
				SharedPreferences.Editor editor = prefs.edit();
				editor.putBoolean("PENDING_EMAIL", false);
				editor.commit();
				
				Toast.makeText(context, "Pending email sent.", Toast.LENGTH_SHORT).show();
			}
		}
	}
}