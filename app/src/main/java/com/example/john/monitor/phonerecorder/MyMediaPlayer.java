package com.example.john.monitor.phonerecorder;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.example.john.monitor.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyMediaPlayer
{

  private static int playState;
  private MediaPlayer mplayer;
  private SeekBar playProgressControlBar;
  private ImageButton playsoundStart;
  private ImageButton playsoundStop;
  private int position;
  private String voiceFileName;
  
  public MyMediaPlayer(Activity paramActivity, String paramString)
  {
    this.voiceFileName = paramString;
    playState = MediaState.STOPPED;
    this.mplayer = new MediaPlayer();
    this.playsoundStart = ((ImageButton)paramActivity.findViewById(R.id.playsoundStart));
    this.playsoundStop = ((ImageButton)paramActivity.findViewById(R.id.playsoundStop));
    this.playProgressControlBar = ((SeekBar)paramActivity.findViewById(R.id.playProgressControlBar));
    this.mplayer.setOnCompletionListener(new OnCompletionListener()
    {
      public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
      {
        MyMediaPlayer.this.changePlayerButtonState(MediaState.STOPPED);
        Log.d("Player", "playing finished");
      }
    });
    prepare();
  }

  class MediaState{
    public static final int PAUSED = 0;
    public static final int PLAYING = 1;
    public static final int STOPPED = 2;
  }
  private void changePlayerButtonState(int paramInt)
  {
    switch (paramInt)
    {

    case MediaState.PLAYING:
      playState = 1;
      this.playsoundStart.setImageResource(R.drawable.btn_pause);
      this.playsoundStop.setImageResource(R.drawable.btn_pause);
      new BarUpdate(this, this.playProgressControlBar).start();
      Log.d("changePlayerButtonState", "playing");
      return;
    case MediaState.PAUSED:
      playState = 0;
      this.playsoundStart.setImageResource(R.drawable.btn_pause);
      this.playsoundStop.setImageResource(R.drawable.btn_pause);
      Log.d("changePlayerButtonState", "paused");
      return;
      case MediaState.STOPPED:
        playState = 2;
        this.playsoundStart.setImageResource(R.drawable.btn_pause);
        this.playsoundStop.setImageResource(R.drawable.btn_pause);
        this.playProgressControlBar.setProgress(0);
        break;
      default:
        return;
    }

    Log.d("changePlayerButtonState", "stopped");
  }
  
  private void prepare() {
    this.mplayer.reset();
    try {
      FileInputStream localFileInputStream1 = new FileInputStream(this.voiceFileName);

      this.mplayer.setDataSource(localFileInputStream1.getFD());
      this.mplayer.prepare();

    } catch (FileNotFoundException e) {
      e.printStackTrace();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public int getCurrentPosition()
  {
    return this.mplayer.getCurrentPosition();
  }
  
  public int getDuration()
  {
    return this.mplayer.getDuration();
  }
  
  public int getPlayState()
  {
    return playState;
  }
  
  public void pause()
  {
    if (this.mplayer.isPlaying())
    {
      this.position = this.mplayer.getCurrentPosition();
      this.mplayer.pause();
      changePlayerButtonState(0);
      Log.d("Player", "paused");
    }
  }
  
  public void release()
  {
    if (this.mplayer != null)
    {
      this.mplayer.release();
      this.mplayer = null;
      Log.d("Player", "release");
    }
  }
  
  public void resume()
  {
    this.mplayer.start();
    this.mplayer.seekTo(this.position);
    changePlayerButtonState(1);
    Log.d("Player", "resume");
  }
  
  public void seekTo(int paramInt)
  {
    this.mplayer.start();
    this.mplayer.seekTo(paramInt);
    changePlayerButtonState(1);
  }
  
  public void setVoiceFile(String paramString)
  {
    this.voiceFileName = paramString;
  }
  
  public void start()
  {
    if (this.mplayer != null) {}
    try
    {
      prepare();
      this.mplayer.start();
      changePlayerButtonState(1);
      Log.d("Recorder", "播放器已启动");
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      localIllegalStateException.printStackTrace();
    }
  }
  
  public void stop()
  {
    if (playState != 2)
    {
      this.mplayer.stop();
      changePlayerButtonState(2);
      Log.d("Player", "stopped");
    }
  }
}


/* Location:              H:\baiduyundownload\android\反编译\record_dex2jar.jar!\com\sdvdxl\phonerecorder\MyMediaPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */