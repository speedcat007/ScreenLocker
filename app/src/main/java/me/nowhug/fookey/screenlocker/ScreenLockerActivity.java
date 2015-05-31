package me.nowhug.fookey.screenlocker;

import android.app.Activity;  
import android.content.Intent;  
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ScreenLockerActivity extends Activity
{
    private Button _button1=null;
    private Button _button2=null;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        _button1= (Button) findViewById(R.id.button1);
        _button2= (Button) findViewById(R.id.button2);

        _button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent mService = new Intent("me.nowhug.fookey.LockService");

                startService(mService);

            }
        });
        _button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent mService = new Intent("me.nowhug.fookey.LockService");
                stopService(mService);

            }
        });

    }
}