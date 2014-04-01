package com.example.videostreaming;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Webservices {
	// HttpParams httpParams=new BasicHttpParams();
	HttpClient httpclient;
	HttpRequest request;
	HttpPost httppost;
	HttpResponse httpresp;
	String url, result, key, val;
	Context cont;
	List<NameValuePair> parameters = new ArrayList<NameValuePair>();
	ByteArrayOutputStream bytes;
	ConnectivityManager connmanager;
	NetworkInfo networkInfo;

	public boolean networkConnection(Context context) {
		this.cont = context;
		connmanager = (ConnectivityManager) cont
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		networkInfo = connmanager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isAvailable()
				&& networkInfo.isConnected()) {
			return true;
		} else {
			return false;
		}

	}

	public Webservices() {
		super();
	}

	public Webservices(String url) {

		this.url = url;
		this.httppost = new HttpPost(this.url);

	}

	public Webservices(String url, String s1) {

		this.url = url;
		this.key = s1;
		this.httppost = new HttpPost(this.url + key);
	}

	public void addparameter(String s1, String s2) {
		this.key = s1;
		this.val = s2;
		this.parameters.add(new BasicNameValuePair(key, val));
	}

	/*
	 * public void addparameterDecimal(String s1, int s2) { this.key = s1;
	 * 
	 * this.parameters.add(new BasicNameValuePair(key,new Integer(s2))); }
	 */

	public void addheader(String s1, String s2) {
		this.key = s1;
		this.val = s2;
		this.httppost.addHeader(key, val);
	}

	public String PostResponse() throws Exception {
		try {
			/*
			 * // Set the // timeout in // milliseconds // until a // connection
			 * is // established. // // The // default value // is zero, that //
			 * means the // timeout is // not used. int timeoutConnection =
			 * 10000; HttpConnectionParams.setConnectionTimeout(parameters,
			 * timeoutConnection); // Set the default socket timeout
			 * (SO_TIMEOUT) // in milliseconds which is the timeout for waiting
			 * for data. int timeoutSocket = 10000;
			 * HttpConnectionParams.setSoTimeout(parameters, timeoutSocket);
			 * httpclient = new DefaultHttpClient(parameters);
			 */
			Log.v("url is :", "" + this.url);
			httppost.setEntity(new UrlEncodedFormEntity(parameters));
			httpclient = returnClient();
			httpresp = httpclient.execute(httppost);
			result = C.ConvertStreamintoString(httpresp.getEntity()
					.getContent());
			Log.v("Response(Webservice) is: ", result);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new Exception(
					"Unsupported Encoding please try different file");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			throw new Exception("Client Protocol Error");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Exception("You are connected slow network");
		}
		return result;

	}

	public InputStream GetResponse() throws Exception {
		try {
			httpclient = returnClient();
			httpresp = httpclient.execute(httppost);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new Exception(
					"Unsupported Encoding please try different file");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			throw new Exception("Client Protocol Error");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Exception("You are connected slow network");
		}
		return httpresp.getEntity().getContent();

	}

	public String GetResponse_String() throws Exception {
		try {
			httpclient = returnClient();
			httpresp = httpclient.execute(httppost);
			result = C.ConvertStreamintoString(httpresp.getEntity()
					.getContent());
			Log.v("Response (Webservice)is: ", result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new Exception(
					"Unsupported Encoding please try different file");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			throw new Exception("Client Protocol Error");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Exception("You are connected slow network");
		}
		return result;

	}

	/*
	 * public String PostResponseMultipartentity(MultipartEntity params) throws
	 * Exception { try { httppost.setEntity(params); // to view content of
	 * params bytes = new ByteArrayOutputStream(); params.writeTo(bytes); String
	 * content = bytes.toString(); Log.v("Content of MultipartEntity :",
	 * content); httpclient = returnClient(); httpresp =
	 * httpclient.execute(httppost); result =
	 * C.ConvertStreamintoString(httpresp.getEntity() .getContent());
	 * Log.v("Response(Webservice) is: ", result);
	 * 
	 * } catch (UnsupportedEncodingException e) { // TODO Auto-generated catch
	 * block throw new Exception(
	 * "Unsupported Encoding please try different file"); } catch
	 * (ClientProtocolException ec) { // TODO Auto-generated catch block throw
	 * new Exception("Client Protocol Error"); } catch (IOException io) { //
	 * TODO Auto-generated catch block throw new
	 * Exception("You are connected slow network"); }
	 * 
	 * return result;
	 * 
	 * }
	 */
	private HttpClient returnClient() {
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 20000);
		HttpConnectionParams.setSoTimeout(httpParams, 20000);
		return new DefaultHttpClient(httpParams);

	}

}
