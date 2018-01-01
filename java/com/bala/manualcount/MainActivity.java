package com.bala.manualcount;

import android.app.Activity;
import android.app.ActionBar;
import android.content.Context;
import android.view.View;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.Random;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		ActionBar actionBar = getActionBar();
		actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView)findViewById(R.id.my_text);
		text.setText("0");
		File root = new File(Environment.getExternalStorageDirectory(), "ManualCounts");
		if (!root.exists()) { root.mkdirs(); }
		Context context = getApplicationContext();
		Toast.makeText(context, "Start by touching anywhere on screen...", Toast.LENGTH_SHORT).show();
		LinearLayout layOut = (LinearLayout)findViewById(R.id.lay);
		layOut.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				try {
					Date date = Calendar.getInstance().getTime();
					DateFormat preciseTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
					DateFormat justDate = new SimpleDateFormat("yyyy-MM-dd");
					File root = new File(Environment.getExternalStorageDirectory(), "ManualCounts");
					File csvfile = new File(root,justDate.format(date)+".csv");
					FileWriter fwriter = new FileWriter(csvfile,true);
					BufferedWriter writer = new BufferedWriter(fwriter);
					String strDate = preciseTime.format(date);
					writer.append(strDate);
					writer.newLine();
					writer.close();
        			TextView text = (TextView)findViewById(R.id.my_text);
					int count = Integer.parseInt(text.getText().toString());
					text.setText(Integer.toString(count+1));
				} catch (IOException e) {
					e.printStackTrace();
					Context context = getApplicationContext();
					Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
				}
			}
		});
    }
}
