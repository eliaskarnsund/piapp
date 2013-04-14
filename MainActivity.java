package com.example.piapp;

import java.io.IOException;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;

import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	String ipAddress = "http://192.168.1.31:5000";
	TextView addressinfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addressinfo = (TextView) findViewById(R.id.textViewAddress);
		
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// Called when the user clicks channel1on-button
	public void buttonSwitch(View view) throws IOException {

		String request = null;
		String channel = null;

		switch (Integer.parseInt((String) view.getTag())) {
		case 11:
			channel = "/lampa1/";
			request = "POST";
			break;
		case 10:
			channel = "/lampa1/";
			request = "GET";
			break;
		case 21:
			channel = "/lampa2/";
			request = "POST";
			break;
		case 20:
			channel = "/lampa2/";
			request = "GET";
			break;
		case 31:
			channel = "/lampa3/";
			request = "POST";
			break;
		case 30:
			channel = "/lampa3/";
			request = "GET";
			break;
		case 41:
			channel = "/lampa4/";
			request = "POST";
			break;
		case 40:
			channel = "/lampa4/";
			request = "GET";
			break;
		default:
			break;
		}

		try {
			new HttpRequest(getApplicationContext()).execute(ipAddress + channel, request);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/** Called when the user clicks submitAddress-button */
	public void submitAddress(View view) {
		EditText text = (EditText) findViewById(R.id.editText1);
		String newAddress = text.getText().toString();
		if (newAddress.length() > 7
				&& newAddress.substring(0, 7).equals("http://")) {
			ipAddress = newAddress;
		} else {
			ipAddress = "http://" + newAddress;
		}
		// hides keyboard
		InputMethodManager imm = (InputMethodManager)getSystemService(
			      Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(text.getWindowToken(), 0);
		addressinfo.setText("Skickar till: " + ipAddress);
		
		
	}
}
