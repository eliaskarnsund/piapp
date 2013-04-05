package com.example.piapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class HttpRequest extends AsyncTask<String, String, Void> {
	Context mainContext;


	@Override
	protected Void doInBackground(String... request) {

		//  response = null;

		if (request[1].equals("POST")) {
			// response = sendPost(request[0]);
			sendPost(request[0]);
		} else {
			// response = sendGet(request[0]);
			sendGet(request[0]);
		}

		String resp = null;
		try {
			//NULL ?
			//resp = inputStreamToString(response.getEntity().getContent());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if (resp!=null) {
			publishProgress(resp);
		}
		

		return null;
	}

	protected void onProgressUpdate(String text) {
	Toast toast = Toast.makeText(mainContext, text, Toast.LENGTH_SHORT);
	toast.show();
	}

	private HttpResponse sendPost(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpPost httppost = new HttpPost(url);

		HttpResponse response = null;
		// Execute the request
		try {
			response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private HttpResponse sendGet(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpGet httpget = new HttpGet(url);

		HttpResponse response = null;
		// Execute the request
		try {
			response = httpclient.execute(httpget);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	// reads http response and returns a string
	private String inputStreamToString(InputStream is) throws IOException {
		String line = "";
		StringBuilder total = new StringBuilder();

		// Wrap a BufferedReader around the InputStream
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));

		// Read response until the end
		while ((line = rd.readLine()) != null) {
			total.append(line);
		}

		// Return full string
		return total.toString();
	}

}
