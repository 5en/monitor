package com.example.john.monitor.common;

import android.os.Environment;
import java.io.File;

public class CommonHelper
{
  public static String getSDCardPath()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
  
  public static boolean isSDCardExist()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean isSDCardWritable()
  {
    return (isSDCardExist()) && (!Environment.getExternalStorageState().equals("mounted_ro"));
  }
  
  public File getSDCard()
  {
    File localFile = Environment.getExternalStorageDirectory();
    if (localFile == null) {
      localFile = null;
    }
    return localFile;
  }
}


/* Location:              H:\baiduyundownload\android\反编译\record_dex2jar.jar!\com\sdvdxl\common\CommonHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */