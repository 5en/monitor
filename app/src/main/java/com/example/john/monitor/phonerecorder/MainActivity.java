package com.example.john.monitor.phonerecorder;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;

import com.example.john.monitor.R;
import com.example.john.monitor.common.CommonHelper;

import java.util.Iterator;
import java.util.List;

public class MainActivity
  extends Activity
{
  private static final int SDCARD_NOTBEWRITABLE = 4;
  public static final String TAG = "Recorder";
  private final int AUTHOR_INFO = 2;
  private final int FEEDBACK_INFO = 3;
  private final int FUNCTION_INFO = 0;
  private final int UPDATE_INFO = 1;
  private Button btnBrowseVoiceFile;
  private Button btnStart;
  private Button btnStop;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.main);
    Log.d("sdcard", CommonHelper.getSDCardPath());
    Log.d("sdcard", String.valueOf(CommonHelper.isSDCardExist()));
    Log.d("sdcard", String.valueOf(CommonHelper.isSDCardWritable()));
    this.btnStart = ((Button)findViewById(R.id.btnStart));
    this.btnStop = ((Button)findViewById(R.id.btnStop));
    this.btnBrowseVoiceFile = ((Button)findViewById(R.id.btnBrowseVoiceFile));
    this.btnStart.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (CommonHelper.isSDCardWritable())
        {
          MainActivity.this.startService(new Intent(MainActivity.this.getBaseContext(), PhoneCallStateService.class));
          MainActivity.this.btnStart.setEnabled(false);
          MainActivity.this.btnStop.setEnabled(true);
          return;
        }
        MainActivity.this.showDialog(4);
      }
    });
    this.btnStop.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.this.btnStart.setEnabled(true);
        MainActivity.this.btnStop.setEnabled(false);
        MainActivity.this.stopService(new Intent(MainActivity.this.getBaseContext(), PhoneCallStateService.class));
      }
    });
    this.btnBrowseVoiceFile.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.this.startActivity(new Intent("com.sdvdxl.phonerecorder.VOICEFILESACTIVITY"));
      }
    });
  }
  
//  protected Dialog onCreateDialog(int paramInt)
//  {
//    switch (paramInt)
//    {
//    default:
//      return null;
//    case 0:
//      new AlertDialog.Builder(this).setTitle("功能介绍").setMessage(2130968580).setPositiveButton("关闭", new DialogInterface.OnClickListener()
//      {
//        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
//        {
//          MainActivity.this.dismissDialog(0);
//        }
//      }).create();
//    case 1:
//      new AlertDialog.Builder(this).setTitle("更新说明").setMessage(2130968581).setPositiveButton("关闭", new DialogInterface.OnClickListener()
//      {
//        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
//        {
//          MainActivity.this.dismissDialog(1);
//        }
//      }).create();
//    case 2:
//      new AlertDialog.Builder(this).setTitle("作者简介").setMessage(2130968582).setPositiveButton("关闭", new DialogInterface.OnClickListener()
//      {
//        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
//        {
//          MainActivity.this.dismissDialog(2);
//        }
//      }).create();
//    case 3:
//      new AlertDialog.Builder(this).setTitle("反馈方式").setMessage(2130968583).setPositiveButton("关闭", new DialogInterface.OnClickListener()
//      {
//        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
//        {
//          MainActivity.this.dismissDialog(3);
//        }
//      }).create();
//    }
//    new AlertDialog.Builder(this).setTitle("提示").setMessage(2130968584).setPositiveButton("关闭", new DialogInterface.OnClickListener()
//    {
//      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
//      {
//        MainActivity.this.dismissDialog(4);
//      }
//    }).create();
//  }
//
//  public boolean onCreateOptionsMenu(Menu paramMenu)
//  {
//    getMenuInflater().inflate(2131099648, paramMenu);
//    return true;
//  }
  
//  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
//  {
//    switch (paramMenuItem.getItemId())
//    {
//    default:
//      return true;
//    case 2131165198:
//      showDialog(0);
//      Log.d("MainMenu", String.valueOf(paramMenuItem.getTitle()));
//      return true;
//    case 2131165199:
//      showDialog(1);
//      Log.d("MainMenu", String.valueOf(paramMenuItem.getTitle()));
//      return true;
//    case 2131165200:
//      showDialog(2);
//      Log.d("MainMenu", String.valueOf(paramMenuItem.getTitle()));
//      return true;
//    }
//    showDialog(3);
//    Log.d("MainMenu", String.valueOf(paramMenuItem.getTitle()));
//    return true;
//  }
  
  protected void onResume()
  {
    super.onResume();
    this.btnStop.setEnabled(false);
    this.btnStart.setEnabled(true);
    Iterator localIterator = ((ActivityManager)getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(50).iterator();
    RunningServiceInfo localRunningServiceInfo;
    do
    {
      if (!localIterator.hasNext()) {
        return;
      }
      localRunningServiceInfo = (RunningServiceInfo)localIterator.next();
      Log.d("Recorder", localRunningServiceInfo.service.getClassName());
    } while (!localRunningServiceInfo.service.getClassName().equals("com.sdvdxl.phonerecorder.PhoneCallStateService"));
    this.btnStop.setEnabled(true);
    this.btnStart.setEnabled(false);
  }
}


/* Location:              H:\baiduyundownload\android\反编译\record_dex2jar.jar!\com\sdvdxl\phonerecorder\MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */