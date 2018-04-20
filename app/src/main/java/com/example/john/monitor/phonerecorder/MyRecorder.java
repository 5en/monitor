package com.example.john.monitor.phonerecorder;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

public class MyRecorder
{
  private String TAG = "Recorder";
  private boolean isCommingNumber = false;
  private MediaRecorder mMediaRecorder;
  private long offHookTime;
  private String phoneNumber;
  private boolean started = false;
  
  public MyRecorder() {}
  
  public MyRecorder(String paramString)
  {
    setPhoneNumber(paramString);
  }
  
  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }
  
  public boolean isCommingNumber()
  {
    return this.isCommingNumber;
  }
  
  public boolean isStarted()
  {
    return this.started;
  }
  
  public void pause() {}
  
  public void setIsCommingNumber(boolean paramBoolean)
  {
    this.isCommingNumber = paramBoolean;
  }
  
  public void setPhoneNumber(String paramString)
  {
    this.phoneNumber = paramString;
  }
  
  public void setStarted(boolean paramBoolean)
  {
    this.started = paramBoolean;
  }
  
  /* Error */

  public void startRecord() {
    // 开始录音
        /* ①Initial：实例化MediaRecorder对象 */
    if (mMediaRecorder == null)
      mMediaRecorder = new MediaRecorder();
    try {
            /* ②setAudioSource/setVedioSource */
      mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置麦克风
            /* ②设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default 声音的（波形）的采样 */
      mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            /*
             * ②设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default THREE_GPP(3gp格式
             * ，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
             */
      mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

      filePath = FolderPath + getAudioFile() ;
            /* ③准备 */
      mMediaRecorder.setOutputFile(filePath);
      mMediaRecorder.setMaxDuration(MAX_LENGTH);
      try {
        mMediaRecorder.prepare();
      } catch (IOException e) {
        e.printStackTrace();
      }
            /* ④开始 */
      mMediaRecorder.start();
      // AudioRecord audioRecord.
            /* 获取开始时间* */
      startTime = System.currentTimeMillis();
      updateMicStatus();
      Log.e("fan", "startTime" + startTime);
    } catch (IllegalStateException e) {
      Log.i(TAG, "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
    }
  }


  public void stop()
  {
    try
    {
      if (this.isCommingNumber)
      {
        int j = (int)(System.currentTimeMillis() - this.offHookTime);
        Log.d(this.TAG, "间隔" + j);
        if ((this.mMediaRecorder != null) && (this.started) && (j > 500))
        {
          this.mMediaRecorder.stop();
          this.started = false;
          this.mMediaRecorder.release();
          this.mMediaRecorder = null;
          Log.d(this.TAG, "录音结束");
        }
      }
      else
      {
        int i = (int)(System.currentTimeMillis() - this.offHookTime);
        Log.d(this.TAG, "间隔" + i);
        if ((this.mMediaRecorder != null) && (this.started) && (i > 500))
        {
          this.mMediaRecorder.stop();
          this.started = false;
          this.mMediaRecorder.release();
          this.mMediaRecorder = null;
          Log.d(this.TAG, "录音结束");
          return;
        }
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      localIllegalStateException.printStackTrace();
    }
  }
}


/* Location:              H:\baiduyundownload\android\反编译\record_dex2jar.jar!\com\sdvdxl\phonerecorder\MyRecorder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */