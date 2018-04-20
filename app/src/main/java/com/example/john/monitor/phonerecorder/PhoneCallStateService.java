package com.example.john.monitor.phonerecorder;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class PhoneCallStateService
  extends Service
{
  public static final String TAG = CallStateReceiver.class.getName();
  private CallStateReceiver callReciver;
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    this.callReciver = new CallStateReceiver();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PHONE_STATE");
    localIntentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
    registerReceiver(this.callReciver, localIntentFilter);
    Log.d("Recorder", "正在监听中...");
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.callReciver);
    Toast.makeText(this, "已关闭电话监听服务", Toast.LENGTH_LONG).show();
    Log.d("Recorder", "已关闭电话监听服务");
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    Toast.makeText(this, "电话监听服务已启动",Toast.LENGTH_LONG ).show();
    return Service.START_STICKY;
  }
}


/* Location:              H:\baiduyundownload\android\反编译\record_dex2jar.jar!\com\sdvdxl\phonerecorder\PhoneCallStateService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */