package com.example.john.monitor.phonerecorder;

import java.util.List;

public class VoiceFile
{
  public static int selectedFilesCount = 0;
  private String fullName;
  private boolean selected;
  private String showName;
  private String showTime;
  private long size;
  
  public static void reverseAllCheckedState(List<VoiceFile> paramList)
  {
    int i = paramList.size();
    int j = 0;
    if (j >= i) {
      return;
    }
    VoiceFile localVoiceFile = (VoiceFile)paramList.get(j);
    if (((VoiceFile)paramList.get(j)).isSelected()) {}
    for (boolean bool = false;; bool = true)
    {
      localVoiceFile.setSelected(bool);
      j++;
      break;
    }
  }
  
  public static List<VoiceFile> search(String[] paramArrayOfString)
  {
    return null;
  }
  
  public static void setAllToCheckedState(List<VoiceFile> paramList)
  {
    int i = paramList.size();
    for (int j = 0;; j++)
    {
      if (j >= i) {
        return;
      }
      ((VoiceFile)paramList.get(j)).setSelected(true);
    }
  }
  
  public static void setAllToUnCheckedState(List<VoiceFile> paramList)
  {
    int i = paramList.size();
    for (int j = 0;; j++)
    {
      if (j >= i) {
        return;
      }
      ((VoiceFile)paramList.get(j)).setSelected(false);
    }
  }
  
  public static void sortByFileSizeAsc(List<VoiceFile> paramList) {}
  
  public static void sortByFileSizeDesc(List<VoiceFile> paramList) {}
  
  public static void sortByTimeAsc(List<VoiceFile> paramList) {}
  
  public static void sortByTimeDesc(List<VoiceFile> paramList) {}
  
  public String getFullName()
  {
    return this.fullName;
  }
  
  public String getShowName()
  {
    return this.showName;
  }
  
  public String getShowTime()
  {
    return this.showTime;
  }
  
  public long getSize()
  {
    return this.size;
  }
  
  public boolean isSelected()
  {
    return this.selected;
  }
  
  public void setFullName(String paramString)
  {
    this.fullName = paramString;
  }
  
  public void setSelected(boolean paramBoolean)
  {
    if ((paramBoolean ^ this.selected))
    {
      this.selected = paramBoolean;
      if (this.selected) {
        selectedFilesCount = 1 + selectedFilesCount;
      }
    }
    else
    {
      return;
    }
    selectedFilesCount = -1 + selectedFilesCount;
  }
  
  public void setShowName(String paramString)
  {
    this.showName = paramString;
  }
  
  public void setShowTime(String paramString)
  {
    this.showTime = paramString;
  }
  
  public void setSize(long paramLong)
  {
    this.size = paramLong;
  }
}


/* Location:              H:\baiduyundownload\android\反编译\record_dex2jar.jar!\com\sdvdxl\phonerecorder\VoiceFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */