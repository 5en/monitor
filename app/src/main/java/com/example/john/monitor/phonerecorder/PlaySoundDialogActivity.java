package com.example.john.monitor.phonerecorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.john.monitor.R;

public class PlaySoundDialogActivity
  extends Activity
{
  private SeekBar playProgressControlBar;
  private MyMediaPlayer player;
  private ImageButton playsoundCancel;
  private ImageButton playsoundStart;
  private ImageButton playsoundStop;
  String selectedFile;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(R.layout.playsound_dialog_activity);
    this.selectedFile = getIntent().getExtras().getString("com.sdvdxl.phonerecorder.VoiceFilesActivity.selectedFile");
    Log.d("Recorder", "传递过来的文件名：" + this.selectedFile);
    this.player = new MyMediaPlayer(this, this.selectedFile);
    this.playProgressControlBar = ((SeekBar)findViewById(R.id.));
    this.playsoundStart = ((ImageButton)findViewById(R.id.btnStart));
    this.playsoundStop = ((ImageButton)findViewById(2131165193));
    this.playsoundCancel = ((ImageButton)findViewById(2131165194));
    this.playsoundStop.setEnabled(true);
    this.playProgressControlBar.setMax(this.player.getDuration());
    Log.d("文件长度", String.valueOf(this.player.getDuration()));
    this.playsoundStart.setOnClickListener(new OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        switch (PlaySoundDialogActivity.this.player.getPlayState())
        {
        default: 
          return;
        case 2: 
          PlaySoundDialogActivity.this.player.start();
          Log.d("playsoundButton", "启动");
          return;
        case 1: 
          PlaySoundDialogActivity.this.player.pause();
          Log.d("playsoundButton", "暂停");
          return;
        }
        PlaySoundDialogActivity.this.player.resume();
        Log.d("playsoundButton", "恢复");
      }
    });
    this.playsoundStop.setOnClickListener(new OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PlaySoundDialogActivity.this.player.stop();
        Log.d("playsoundButton", "停止");
      }
    });
    this.playsoundCancel.setOnClickListener(new OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PlaySoundDialogActivity.this.finish();
      }
    });
    this.playProgressControlBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean) {}
      
      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
        PlaySoundDialogActivity.this.player.pause();
        Log.d("SeekBar", "SeekBar触摸开始 位置" + paramAnonymousSeekBar.getProgress());
      }
      
      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
        PlaySoundDialogActivity.this.player.seekTo(paramAnonymousSeekBar.getProgress());
        Log.d("SeekBar", "SeekBar触摸结束 位置" + paramAnonymousSeekBar.getProgress());
      }
    });
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.player.stop();
    this.player.release();
    Log.d("Recorder", this.player.toString());
  }
  
  public void onPause()
  {
    super.onPause();
    this.player.pause();
  }
}


/* Location:              H:\baiduyundownload\android\反编译\record_dex2jar.jar!\com\sdvdxl\phonerecorder\PlaySoundDialogActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */