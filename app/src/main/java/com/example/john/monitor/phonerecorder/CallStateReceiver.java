package com.example.john.monitor.phonerecorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import static android.telephony.PhoneStateListener.LISTEN_CALL_STATE;

class CallStateReceiver
  extends BroadcastReceiver
{
  public static final String TAG = CallStateReceiver.class.getName();
  private CallInListener callIn = new CallInListener(this.recorder);
  private CallOutListener callOut = new CallOutListener(this.recorder);
  private MyRecorder recorder = new MyRecorder();
  TelephonyManager telmgr;
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {

    this.telmgr = ((TelephonyManager)paramContext.getSystemService(Context.TELEPHONY_SERVICE));
    String str1 = paramIntent.getAction();
    if (this.recorder == null) {
      this.recorder = new MyRecorder();
    }
    if (str1.equals("android.intent.action.NEW_OUTGOING_CALL"))
    {
      String str2 = paramIntent.getStringExtra("android.intent.extra.PHONE_NUMBER");
      this.telmgr.listen(this.callOut, LISTEN_CALL_STATE);
      this.recorder.setPhoneNumber(str2);
      this.recorder.setIsCommingNumber(false);
      Log.d(TAG, "设置为去电状态");
      Log.d(TAG, "去电状态 呼叫：" + str2);
      return;
    }
    this.telmgr.listen(this.callIn, LISTEN_CALL_STATE);
  }
}


/* Location:              H:\baiduyundownload\android\反编译\record_dex2jar.jar!\com\sdvdxl\phonerecorder\CallStateReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */