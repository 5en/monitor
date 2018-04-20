package com.example.john.monitor.phonerecorder;

import android.telephony.PhoneStateListener;
import android.util.Log;

public class CallInListener
  extends PhoneStateListener
{
  static final String TAG = CallInListener.class.getName();
  private MyRecorder recorder;
  
  public CallInListener()
  {
    this.recorder = new MyRecorder();
  }
  
  public CallInListener(MyRecorder paramMyRecorder)
  {
    this.recorder = paramMyRecorder;
  }
  
  public void onCallStateChanged(int paramInt, String paramString)
  {
    super.onCallStateChanged(paramInt, paramString);
    Log.d(TAG, "CallIn");
    switch (paramInt)
    {
    default: 
    case 0: 
    case 2: 
      do
      {

        Log.d(TAG, "IDEL");
        this.recorder.stop();
//        return;

      } while (!this.recorder.isCommingNumber());
      this.recorder.start();
//      return;
    }
//    Log.d(TAG, "响铃");
    this.recorder.setPhoneNumber(paramString);
    Log.d(TAG, "准备录音， 设置打入号码为：" + paramString);
    this.recorder.setIsCommingNumber(true);
  }
  
  public void setPhoneNumber(String paramString)
  {
    this.recorder.setPhoneNumber(paramString);
  }
}


/* Location:              H:\baiduyundownload\android\反编译\record_dex2jar.jar!\com\sdvdxl\phonerecorder\CallInListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */