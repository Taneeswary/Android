package com.mwsa;

import java.util.Date;




import android.os.AsyncTask;
import android.util.Log;

public class send_mail extends AsyncTask<String, String, String>  {
private String resp;


	
	// NOTABLE EMAIL ACCOUNT DETAILS
	private String accountEmail = "MOBILEWITNESSSTATEMENT@gmail.com";
	private String accountPassword = "mobilewitnessstatement2016";
	@Override
	protected String doInBackground(String... params) {
		mail m = new mail(accountEmail, accountPassword);

		try {
			if(params[1].indexOf(".pdf") != -1) {
			String[] toArr = { params[0], accountEmail };
			m.setTo(toArr);
			m.setFrom(accountEmail);
			
				m.setSubject("Report! @ MWSA App");
				m.setBody("Report Generated! at " + new Date() + ".");
				m.addAttachment(params[1]);
			} else {
				String[] toArr = { params[0] };
				m.setTo(toArr);
				m.setFrom(accountEmail);
				m.setSubject("Forgot Password @ MWSA App");
				
				String password = params[1];
				
				m.setBody("Your password is : " + password + ". requested at " + new Date()+ ".");
			}

			if (m.send()) {
				System.out.println("Email was sent successfully.");
			} else {
				System.out.println("Email was not sent.");
			}
		} catch (Exception e) {
			Log.e("MailApp", "Could not send email", e);
		}
		return resp;
	}
	@Override
	protected void onPostExecute(String result) {
		// execution of result of Long time consuming operation
	}
	@Override
	protected void onPreExecute() {
		// Things to be done before execution of long running operation. For
		// example showing ProgessDialog
	}
	protected void onProgressUpdate(String... text) {
		// Things to be done while execution of long running operation is in
		// progress. For example updating ProgessDialog
	}
}