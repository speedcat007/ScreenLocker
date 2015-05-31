package me.nowhug.fookey.screenlocker;

import android.app.KeyguardManager;  
import android.app.Service;  
import android.app.KeyguardManager.KeyguardLock;  
import android.content.BroadcastReceiver;  
import android.content.Context;  
import android.content.Intent;  
import android.content.IntentFilter;  
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LockerService extends Service
{

    private static final String TAG="LockService";

    private LocalBrocastReceiver receiver;
    
    @Override
    public IBinder onBind(Intent arg0)
    {   
    	return null;  
    }  
    
    @Override
    public void onCreate()  
    {  
    	super.onCreate();

        receiver=new LocalBrocastReceiver();

    }  
    
    @Override
    public void onStart(Intent intent, int startId)
    {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("KeyguardLock");
        keyguardLock.disableKeyguard();

        Log.d(TAG, "onStart");

        IntentFilter filter=new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        registerReceiver(receiver, filter);

        Toast.makeText(getApplicationContext(), "成功启动锁屏", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
        Toast.makeText(getApplicationContext(), "成功关闭锁屏", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onDestroy");
    }
}
