package me.nowhug.fookey.screenlocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by fookey on 15-5-25.
 */
public class LocalBrocastReceiver extends BroadcastReceiver {

    private static final String TAG="LocalBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();

        Log.d(TAG,"The Intent Action is "+action);

        if(TextUtils.equals(action,Intent.ACTION_SCREEN_ON)){

        }else if(TextUtils.equals(action,Intent.ACTION_SCREEN_OFF)) {
            Intent mIntent =new Intent(context,LockerActivity.class);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(mIntent);
        }

    }
}
