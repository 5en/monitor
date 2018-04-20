package com.example.john.monitor.phonerecorder;

import android.widget.SeekBar;

class BarUpdate
  extends Thread
{
  SeekBar mbar;
  MyMediaPlayer mplayer;
  
  public BarUpdate(MyMediaPlayer paramMyMediaPlayer, SeekBar paramSeekBar)
  {
    this.mplayer = paramMyMediaPlayer;
    this.mbar = paramSeekBar;
  }
  
  public void run()
  {
    for (;;)
    {
      if (this.mplayer.getPlayState() != 1) {
        return;
      }
      this.mbar.setProgress(this.mplayer.getCurrentPosition());
      try
      {
        Thread.sleep(500L);
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }
}


/* Location:              H:\baiduyundownload\android\反编译\record_dex2jar.jar!\com\sdvdxl\phonerecorder\BarUpdate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */