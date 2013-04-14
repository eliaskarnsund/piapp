package com.example.piapp;


import java.io.IOException;
import java.net.HttpURLConnection;

import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class HttpRequest extends AsyncTask<String, CharSequence, String> {
	Context mainContext;

	public HttpRequest(Context main) {

		super();
		mainContext = main;
	}

	@Override
	protected String doInBackground(String... request) {

		String response = "";
		try {
			response = Integer.toString(sendGet(request[0]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	private int sendGet(String address) throws IOException {
		int response;
		URL url = new URL(address);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		try {
			// InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			
			response = urlConnection.getResponseCode();

		} finally {
			urlConnection.disconnect();
		}
		return response;
	}

	protected void onPostExecute(String responseCode) {
		String message = translateResponseCode(responseCode);
		Toast toast = Toast.makeText(mainContext, message, Toast.LENGTH_SHORT);
		toast.show();
	}


	private String translateResponseCode(String responseCode) {
		
		switch (Integer.parseInt(responseCode)) {
		case 200:
			return "OK";
		case 404:
			return "Not found";
		default:
			return "Could not resolve address";
		}
		
	}


}
