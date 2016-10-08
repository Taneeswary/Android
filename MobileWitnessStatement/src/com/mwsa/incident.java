
package com.mwsa;

import java.io.FileOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import android.widget.Button;
import android.content.Context;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.telephony.TelephonyManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;


@TargetApi(Build.VERSION_CODES.KITKAT)
public class incident extends Activity  {
	public TextView date;
	public TextView time;
	public TextView location;
	public EditText victim;
	public EditText suspect;
	public EditText vehicle;
	public EditText plateNo;
	public EditText descrip;
	public TextView IMEI;
	public String date1, time1, location1, victim1, suspect1,vehicle1, plateNo1,descrip1,IMEI1;
	final Context context = this;
	TelephonyManager tel;
	public Button address;
	public Button pic;
	public Button save;
	
	public ImageButton imgbtn;
	public ImageView iv;
	Location currentLocation;
	double   currentLatitude;
	double   currentLongitude;	
	public TextView lat1;
	public TextView lng2;
	private static int RESULT_LOAD_IMAGE = 1;
	public Spinner mySpinner;
	public String name;
	public String ic;
	public String hp;
	public String email;
	public String picturePath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description_layout);
		Toast.makeText(incident.this, "It is a Must to add An image", Toast.LENGTH_LONG)
        .show(); 
		SharedPreferences prefs = this.getSharedPreferences("com.mwsa", Context.MODE_PRIVATE);
		name = prefs.getString("NAME", null);
		ic = prefs.getString("IC", null);
		hp = prefs.getString("HPNUM", null);
		email = prefs.getString("EMAIL", null);
		victim = (EditText) findViewById(R.id.editText4);
		
		suspect = (EditText) findViewById(R.id.editText5);
		
		vehicle = (EditText) findViewById(R.id.editText6);
		
		plateNo = (EditText) findViewById(R.id.editText7);
		
		descrip = (EditText) findViewById(R.id.editText8);
		mySpinner=(Spinner) findViewById(R.id.spinner1);
		iv=(ImageView)findViewById(R.id.ivImage);
		save = (Button)findViewById(R.id.savebtn);
		save.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View v) {
				 
					 createPdf();
					
				 }
				
					 
				 
			 
		});
		
		
			imgbtn = (ImageButton)findViewById(R.id.imageButton1);
			imgbtn.setOnClickListener(new View.OnClickListener() {
	             
				  @Override
		            public void onClick(View arg0) {
		                 
		                Intent i = new Intent(
		                        Intent.ACTION_PICK,
		                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		                 
		                startActivityForResult(i, RESULT_LOAD_IMAGE);
		            }
		        });
				
		//AUTO SETTING DATE AND TIME
		date=(TextView)findViewById(R.id.d8);
        time=(TextView)findViewById(R.id.tym);
		
        SimpleDateFormat dateF = new SimpleDateFormat("yyyy/MM/dd" , Locale.getDefault());
        String Date = dateF.format(Calendar.getInstance().getTime());
		date.setText(Date);
		SimpleDateFormat timeF = new SimpleDateFormat("HH:mm:ss" , Locale.getDefault());
        String Time = timeF.format(Calendar.getInstance().getTime());
		time.setText(Time);
		
		
		
		//AUTO SETTING IMEI NUM
		tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		IMEI=(TextView)findViewById(R.id.imei);
		IMEI.setText(tel.getDeviceId().toString());
		
		//onclick setting address
		lat1=(TextView)findViewById(R.id.lat);
		lng2=(TextView)findViewById(R.id.lng);

		location=(TextView)findViewById(R.id.add);
		address = (Button)findViewById(R.id.addressButton);
		LocationManager locationManager = 
	            (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		
	        
	        LocationListener locationListener = new LocationListener() {
	            public void onLocationChanged(Location location) {
	                updateLocation(location);
	            }
	            public void onStatusChanged(
	                    String provider, int status, Bundle extras) {}
	            public void onProviderEnabled(String provider) {}
	            public void onProviderDisabled(String provider) {}
	        };

	        locationManager.requestLocationUpdates(
	                LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

	        this.address.setOnClickListener(new OnClickListener() {
	            public void onClick(View v){
	                getAddress();
	            }
	        });
		   }
		 // address function
	    void getAddress(){
	        try{
	            Geocoder gcd = new Geocoder(this, Locale.getDefault());  //default function from android
	            List<Address> addresses = 
	                gcd.getFromLocation(currentLatitude, currentLongitude,1);// radius of address
	            if (addresses.size() > 0) {
	                StringBuilder result = new StringBuilder();
	                for(int i = 0; i < addresses.size(); i++){
	                    Address address =  addresses.get(i);
	                    int maxIndex = address.getMaxAddressLineIndex();
	                    for (int x = 0; x <= maxIndex; x++ ){     // display of address
	                        result.append(address.getAddressLine(x));
	                        result.append(",");
	                    }               
	                    result.append(address.getLocality());
	                    result.append(",");
	                    result.append(address.getPostalCode());
	                    result.append("\n\n");
	                }
	                location.setText(result);
	                String stringdouble= Double.toString(currentLatitude);
	                lat1.setText(stringdouble);
	                String stringdouble1= Double.toString(currentLongitude);
	                lng2.setText(stringdouble1);
	            }
	        }
	        catch(IOException ex){
	        	location=(TextView)findViewById(R.id.add);
	            location.setText(ex.getMessage().toString());
	        }
	    }
	    void updateLocation(Location location){
	        currentLocation = location;
	        currentLatitude = currentLocation.getLatitude();
	        currentLongitude = currentLocation.getLongitude();
	        //textContent.setText(currentLatitude + "<--Latitude || Longitude-->" + currentLongitude);
	    }
	   
	    //picture
	   
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        try{
	        if (requestCode == RESULT_LOAD_IMAGE ) {
	            Uri selectedImage = data.getData();
	            String[] filePathColumn = { MediaStore.Images.Media.DATA };
	 
	            Cursor cursor = getContentResolver().query(selectedImage,
	                    filePathColumn, null, null, null);
	            cursor.moveToFirst();
	 
	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            picturePath = cursor.getString(columnIndex);
	            cursor.close();
	            
	            
	            iv.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	    }
	        
        
    } catch (Exception e) {
        Toast.makeText(this, "Something went embrassing", Toast.LENGTH_LONG)
                .show();
    }
	        
	   

}
	       
	//saving inputs
	    String filename;
		public void createPdf(){
			
			Document document = new Document();
			
			try{
				
			        
			   filename = Environment.getExternalStorageDirectory().getPath()
						+ "/MWSA_" + System.currentTimeMillis() + ".pdf";
			    FileOutputStream output = new FileOutputStream(filename);
				
        

	         //Step 2
	         PdfWriter.getInstance(document, output);

	         //Step 3
	         document.open();

	         //Step 4 Add content
	         document.add(new Paragraph("This witness report is created by Mobile Witness Statement for Android" ));
	         document.add(new Paragraph("" ));
	         document.add(new Paragraph("" ));
	         document.add(new Paragraph("Witness Name:" +" "+ name));
	         document.add(new Paragraph("Witness IC:" +" "+ ic));
	         document.add(new Paragraph("Witness HP:" +" "+ hp));
	         document.add(new Paragraph("Witness Email:" +" "+ email));
	         document.add(new Paragraph("Witness mobile IMEI number:" +" "+ IMEI.getText().toString()));
	         document.add(new Paragraph("Report created time and date:" +" "+ (time.getText().toString()) + "," + (date.getText().toString())));
	         document.add(new Paragraph("Latitude and Longitude:" +" "+ (lat1.getText().toString())+ "," + (lng2.getText().toString())));
	         document.add(new Paragraph("Incident Address:" +" "+ (location.getText().toString())));
	         document.add(new Paragraph("Type of crime:" +" "+ (mySpinner.getSelectedItem().toString())));
	         document.add(new Paragraph("Number of victims:" +"  "+ (victim.getText().toString())));
	         document.add(new Paragraph("Number of suspects:" +""+ (suspect.getText().toString())));
	         document.add(new Paragraph("Type of vehicle:" +"  "+ (vehicle.getText().toString())));
	         document.add(new Paragraph("Plate number:" +""+ (plateNo.getText().toString())));
	         document.add(new Paragraph("Description of the incident:" +""+ (descrip.getText().toString())));
	         Image image = Image.getInstance(picturePath);
	         image.scaleToFit(400f, 250f);
	            document.add(image);
	        
        
			} catch (DocumentException de) {
				   Log.e("PDFCreator", "DocumentException:" + de);
				  } catch (IOException e) {
				   Log.e("PDFCreator", "ioException:" + e);
				  } 
			finally
			{
				document.close();
				SharedPreferences prefs = this.getSharedPreferences("com.mwsa", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString("FILENAME", filename);
				editor.commit();
			}
			
			Intent intent = new Intent(this,att.class);
			
			startActivity(intent);
			finish();
         }
		
		

		

	}

	   






