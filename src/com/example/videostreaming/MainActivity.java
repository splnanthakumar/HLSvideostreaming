package com.example.videostreaming;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.Files;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	VideoView videoView;
	MediaPlayer mediaPlayer;
	SurfaceHolder surfaceHolder;
	SurfaceView playerSurfaceView;
	// String videoSrc = "/sdcard/anbe.mp4";//to play from local folders
	String videoSrc = "http://daily3gp.com/vids/747.3gp";
	String pat;
	private String urlStream;
	Webservices web;
	List<String> parent;
	private URL url;
	File tempFile;

	FileOutputStream fos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		videoView = (VideoView) findViewById(R.id.videoView1);
		try {
			tempFile = File.createTempFile("mediaplayertemp", "dat");
			fos = new FileOutputStream(tempFile, true);
			tempFile.deleteOnExit();
			pat = tempFile.getAbsolutePath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MediaController mc = new MediaController(this);
		videoView.setMediaController(mc);
		// urlStream =
		// "http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8";
		// urlStream =
		// "http://commondatastorage.googleapis.com/gtv-videos-bucket/big_buck_bunny_1080p.mp4";
	//	urlStream = "http://192.168.1.4:1935/vod/mp4:sample.mp4/chunklist_w812000946.m3u8";
		
		urlStream="http://www.endlesspools.com/iphone/iphone_streams/EndlessPool/EndlessPool_128k.m3u8";
		new net().execute();

		/*
		 * runOnUiThread(new Runnable() {
		 * 
		 * @SuppressLint("NewApi")
		 * 
		 * @Override public void run() { try { Thread.sleep(2000);
		 * videoView.setVideoPath(tempFile.getAbsolutePath()); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * } });
		 */
	}

	class net extends AsyncTask<Void, Void, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub

			parent = new ArrayList<String>();
			web = new Webservices(urlStream);
			try {
				Log.v("async ", "" + web.GetResponse());
				parent.addAll(parseHLSMetadata(web.GetResponse()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

	}

	List<String> parseHLSMetadata(InputStream i) {
		List<String> local = new ArrayList<String>();
		int in = 0;
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(i,
					"UTF-8"));
			String line;

			while ((line = r.readLine()) != null) {
				Log.v("file is:", "" + line);
				if (line.contains(".ts")) {
					local = new ArrayList<String>();
					/*
					 * Matcher matcher = pattern.matcher(line); matcher.find();
					 * // find the first matching digit, which // represents the
					 * duration of the segment, // dont call .find() again that
					 * will throw // digit which may be contained in the //
					 * description.
					 */

					Log.v("ts file is:", "" + line);
					web = new Webservices(
							"http://www.endlesspools.com/iphone/iphone_streams/EndlessPool/"
									+ line.trim());

					try {
						getFile(web.GetResponse());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
			r.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return local;
	}

	@SuppressLint("NewApi")
	private void getFile(InputStream is) {
		// TODO Auto-generated method stub
		int read = 0;
		try {

			byte[] buf = new byte[1024];
			while ((read = is.read(buf)) != -1) {
				fos.write(buf, 0, read);
			}
			try {
				is.close();
			} catch (IOException ex) {
				Log.e("error ", "error: " + ex.getMessage(), ex);
			}
		} catch (IOException ex) {
			Log.e("error ", "error: " + ex.getMessage(), ex);
		}
		Log.v("video view length is ", "" + videoView.getDuration());
//		videoView.notify();
		if (!videoView.isPlaying()) {
			try {
				Log.v("started playing", "video is started to play");
				playVideo(tempFile.getAbsolutePath());
			} catch (IOException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void playVideo(String pathto) throws IOException {
		Log.v("pathis ", pathto);
		videoView.setVideoPath(pathto);
		videoView.start();
		videoView.requestFocus();

		// videoView.setMediaController(new MediaController(this));

		/*
		 * playerSurfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
		 * 
		 * surfaceHolder = playerSurfaceView.getHolder();
		 * surfaceHolder.addCallback(this);
		 */

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		tempFile.delete();
	}

	/*
	 * @Override public void surfaceChanged(SurfaceHolder arg0, int arg1, int
	 * arg2, int arg3) { // TODO Auto-generated method stub }
	 * 
	 * @Override public void surfaceCreated(SurfaceHolder arg0) {
	 * 
	 * try { mediaPlayer = new MediaPlayer();
	 * mediaPlayer.setDisplay(surfaceHolder);
	 * mediaPlayer.setDataSource(videoSrc); mediaPlayer.prepare();
	 * mediaPlayer.setOnPreparedListener(this);
	 * mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC); } catch
	 * (IllegalArgumentException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (SecurityException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } catch
	 * (IllegalStateException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } }
	 * 
	 * @Override public void surfaceDestroyed(SurfaceHolder arg0) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void onPrepared(MediaPlayer mp) { //mediaPlayer.start();
	 * }
	 */

}
