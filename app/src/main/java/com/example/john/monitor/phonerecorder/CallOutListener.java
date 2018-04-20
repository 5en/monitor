package com.example.john.monitor.phonerecorder;

import android.telephony.PhoneStateListener;
import android.util.Log;

public class CallOutListener
  extends PhoneStateListener
{
  static final String TAG = CallOutListener.class.getName();
  private MyRecorder recorder;
  
  public CallOutListener()
  {
    this.recorder = new MyRecorder();
  }
  
  public CallOutListener(MyRecorder paramMyRecorder)
  {
    this.recorder = paramMyRecorder;
  }
  
  public void onCallStateChanged(int paramInt, String paramString)
  {
    super.onCallStateChanged(paramInt, paramString);
    Log.d(TAG, "CallOut");
    switch (paramInt)
    {
    }
    do
    {
//      return;
//      Log.d(TAG, "IDLE");
      this.recorder.stop();
//      return;
//      Log.d(TAG, "OFFHOOK");
    } while (this.recorder.isCommingNumber());
    this.recorder.start();
  }
  
  public void setPhoneNumber(String paramString)
  {
    this.recorder.setPhoneNumber(paramString);
  }
}


/* Location:              H:\baiduyundownload\android\反编译\record_dex2jar.jar!\com\sdvdxl\phonerecorder\CallOutListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */