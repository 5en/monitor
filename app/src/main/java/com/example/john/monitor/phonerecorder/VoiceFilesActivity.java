package com.example.john.monitor.phonerecorder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.john.monitor.R;
import com.example.john.monitor.common.CommonHelper;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VoiceFilesActivity
  extends Activity {
  private static final int DIALOG_DELETE_FILE = 1;
  private static final int DIALOG_SDCARD_NOT_EXIST = 2;
  private static final int EDITABLE_MODE = 1;
  private static final int NORMAL_MODE = 0;
  public static final String SELECTED_FILES = "com.sdvdxl.phonerecorder.VoiceFilesActivity.selectedFile";
  private static int showMode;
  private EditableListAdapter editableAdapter;
  private NormalListAdapter normalAdapter;
  private List<VoiceFile> voiceFiles;
  private ListView voiceFilesList;

  private void changeShowMode(int mode) {
    switch (mode) {

      case NORMAL_MODE:
        showMode = mode;
        VoiceFile.setAllToUnCheckedState(this.voiceFiles);
        this.voiceFilesList.setAdapter(this.normalAdapter);
        this.voiceFilesList.setOnItemClickListener(normalModeClickListener);
        this.voiceFilesList.setOnItemLongClickListener(onItemLongClickListener);
      break;
      case EDITABLE_MODE:

        showMode = 1;
        VoiceFile.setAllToUnCheckedState(this.voiceFiles);
        this.voiceFilesList.setAdapter(this.editableAdapter);
        this.voiceFilesList.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
            VoiceFile localVoiceFile = (VoiceFile) VoiceFilesActivity.this.voiceFiles.get(paramAnonymousInt);
            if (((VoiceFile) VoiceFilesActivity.this.voiceFiles.get(paramAnonymousInt)).isSelected()) {
            }
            for (boolean bool = false; ; bool = true) {
              localVoiceFile.setSelected(bool);
//              Log.d("编辑模式 单击", aramAnonymousInt + ((VoiceFile) VoiceFilesActivity.this.voiceFiles.get(paramAnonymousInt)).isSelected());
              VoiceFilesActivity.this.editableAdapter.notifyDataSetChanged();
              return;
            }
          }
        });
    }
  }


  private void checkAllFiles(Boolean paramBoolean) {
    if (paramBoolean == null) {
      VoiceFile.reverseAllCheckedState(this.voiceFiles);
    }
    for (; ; ) {
      this.editableAdapter.notifyDataSetChanged();
      return;
      if (paramBoolean.booleanValue()) {
        VoiceFile.setAllToCheckedState(this.voiceFiles);
      } else {
        VoiceFile.setAllToUnCheckedState(this.voiceFiles);
      }
    }
  }

  private void confirmDeleteFilesInf() {
    if (VoiceFile.selectedFilesCount == 0) {
      Toast.makeText(this, "未选中任何文件", 0).show();
      return;
    }
    showDialog(1);
  }

  private void deleteFiles() {
    int i = this.voiceFiles.size();
    int j = 0;
    new VoiceFile();
    int k = 0;
    if (k >= i) {
      Toast.makeText(this, "已删除" + j + "个录音文件", 0).show();
      Toast.makeText(this, i - j + "个录音文件删除失败", 0).show();
      if (showMode != 0) {
        break label243;
      }
      refreshListView(this.normalAdapter);
    }
    for (; ; ) {
      VoiceFile.setAllToUnCheckedState(this.voiceFiles);
      return;
      VoiceFile localVoiceFile = (VoiceFile) this.voiceFiles.get(k);
      if (localVoiceFile.isSelected()) {
        if (!new File(localVoiceFile.getFullName()).delete()) {
          break label186;
        }
        j++;
        Log.d("Recorder", "已删除录音文件" + localVoiceFile.getFullName());
      }
      for (; ; ) {
        k++;
        break;
        label186:
        Toast.makeText(this, "删除文件失败" + localVoiceFile.getFullName(), 0).show();
        Log.d("Recorder", "删除录音文件失败" + localVoiceFile.getFullName());
      }
      label243:
      refreshListView(this.editableAdapter);
    }
  }

  private void doEditableOptions(MenuItem paramMenuItem) {
    switch (paramMenuItem.getItemId()) {
      default:
        return;
      case 2131165207:
        changeShowMode(0);
        Log.d("Recorder", "取消编辑");
        return;
      case 2131165203:
        checkAllFiles(Boolean.valueOf(true));
        Log.d("Recorder", "全选");
        return;
      case 2131165205:
        checkAllFiles(null);
        Log.d("Recorder", "反选");
        return;
      case 2131165204:
        checkAllFiles(Boolean.valueOf(false));
        Log.d("Recorder", "取消全选");
        return;
    }
    confirmDeleteFilesInf();
    Log.d("Recorder", "排序");
  }

  private void doNormalOptions(MenuItem paramMenuItem) {
    switch (paramMenuItem.getItemId()) {
      default:
        return;
    }
    changeShowMode(1);
    Log.d("Recorder", "编辑");
  }

  private void initData() {
    this.voiceFiles = new ArrayList();
    this.voiceFilesList = ((ListView) findViewById(2131165197));
    listVoiceFiles();
    Log.d("voiceFilesList", this.voiceFilesList.toString());
    this.normalAdapter = new NormalListAdapter(this);
    this.editableAdapter = new EditableListAdapter(this);
    changeShowMode(0);
    refreshListView(this.normalAdapter);
  }

  private void listVoiceFiles() {
    if (!CommonHelper.isSDCardExist()) {
      showDialog(2);
    }
    for (; ; ) {
      return;
      this.voiceFiles.clear();
      File localFile = new File(Environment.getExternalStorageDirectory() + "/My Record");
      if (!localFile.exists()) {
        localFile.mkdir();
      }
      File[] arrayOfFile = localFile.listFiles();
      int i = arrayOfFile.length;
      int j = 0;
      while (j < i) {
        try {
          String str1 = arrayOfFile[j].getName();
          String str2 = str1.split("#")[0];
          String str3 = str1.substring(1 + str1.indexOf('#'), str1.indexOf('.'));
          try {
            String str5 = new SimpleDateFormat("yyy/MM/dd HH:mm:ss").format(new SimpleDateFormat("yyy-MM-dd_HH-mm-ss").parse(str3));
            str4 = str5;
          } catch (ParseException localParseException) {
            for (; ; ) {
              VoiceFile localVoiceFile;
              localParseException.printStackTrace();
              String str4 = null;
            }
          }
          localVoiceFile = new VoiceFile();
          localVoiceFile.setFullName(arrayOfFile[j].getAbsolutePath());
          localVoiceFile.setShowName(str2);
          localVoiceFile.setShowTime(str4);
          this.voiceFiles.add(localVoiceFile);
        } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
          localStringIndexOutOfBoundsException.printStackTrace();
          j++;
        }
      }
    }
  }

  private void refreshListView(BaseAdapter paramBaseAdapter) {
    listVoiceFiles();
    setTitle("一共" + this.voiceFiles.size() + "个文件");
    Log.d("debug", String.valueOf(2131165195));
    paramBaseAdapter.notifyDataSetChanged();
  }

  public void onBackPressed() {
    if (showMode == 1) {
      changeShowMode(0);
      return;
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2130903044);
    initData();
  }

  protected Dialog onCreateDialog(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 1:
        new AlertDialog.Builder(this).setTitle("删除选中录音文件").setPositiveButton("确定", new OnClickListener() {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
            VoiceFilesActivity.this.deleteFiles();
          }
        }).setNegativeButton("取消", new OnClickListener() {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
            Log.d("Recorder", "取消删除录音文件");
          }
        }).create();
    }
    new AlertDialog.Builder(this).setTitle("提示").setPositiveButton("关闭", new OnClickListener() {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
        VoiceFilesActivity.this.dismissDialog(2);
      }
    }).setMessage("SD Card 不存在").create();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu) {
    getMenuInflater().inflate(2131099649, paramMenu);
    paramMenu.findItem(2131165202);
    Log.d("Menu", "createOptionsMenu");
    return true;
  }

  public void onResume() {
    super.onResume();
    if (showMode == 0) {
      refreshListView(this.normalAdapter);
      return;
    }
    refreshListView(this.editableAdapter);
  }

  class EditableListAdapter
          extends BaseAdapter {
    LayoutInflater inflater;

    public EditableListAdapter(Context paramContext) {
      this.inflater = LayoutInflater.from(paramContext);
    }

    public int getCount() {
      return VoiceFilesActivity.this.voiceFiles.size();
    }

    public Object getItem(int paramInt) {
      return null;
    }

    public long getItemId(int paramInt) {
      return 0L;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
      EditableListItem localEditableListItem;
      if (paramView == null) {
        localEditableListItem = new EditableListItem();
        paramView = this.inflater.inflate(2130903040, null);
        localEditableListItem.voiceFileCheck = ((CheckBox) paramView.findViewById(2131165187));
        localEditableListItem.voiceFileName = ((TextView) paramView.findViewById(2131165185));
        localEditableListItem.voiceFileTime = ((TextView) paramView.findViewById(2131165186));
        paramView.setTag(localEditableListItem);
      }
      for (; ; ) {
        localEditableListItem.voiceFileName.setText(((VoiceFile) VoiceFilesActivity.this.voiceFiles.get(paramInt)).getShowName());
        localEditableListItem.voiceFileTime.setText(((VoiceFile) VoiceFilesActivity.this.voiceFiles.get(paramInt)).getShowTime());
        localEditableListItem.voiceFileCheck.setChecked(((VoiceFile) VoiceFilesActivity.this.voiceFiles.get(paramInt)).isSelected());
        localEditableListItem.voiceFileCheck.setOnCheckedChangeListener(new CheckBoxStateChanged(paramInt));
        Log.d("item", paramInt + localEditableListItem.voiceFileCheck.isChecked());
        return paramView;
        localEditableListItem = (EditableListItem) paramView.getTag();
      }
    }

    class CheckBoxStateChanged
            implements OnCheckedChangeListener {
      int position;

      public CheckBoxStateChanged(int paramInt) {
        this.position = paramInt;
      }

      public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean) {
        ((VoiceFile) VoiceFilesActivity.this.voiceFiles.get(this.position)).setSelected(paramBoolean);
      }
    }

    class EditableListItem {
      CheckBox voiceFileCheck;
      TextView voiceFileName;
      TextView voiceFileTime;

      EditableListItem() {
      }
    }
  }

  class NormalListAdapter
          extends BaseAdapter {
    LayoutInflater inflater;

    public NormalListAdapter(Context paramContext) {
      this.inflater = LayoutInflater.from(paramContext);
    }

    public int getCount() {
      return VoiceFilesActivity.this.voiceFiles.size();
    }

    public Object getItem(int paramInt) {
      return null;
    }

    public long getItemId(int paramInt) {
      return 0L;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
      EditableListItem localEditableListItem = null;
      if (paramView == null) {
        localEditableListItem = new EditableListItem();
        paramView = this.inflater.inflate(R.layout.voicefiles_items, null);
        localEditableListItem.voiceFileName = ((TextView) paramView.findViewById(R.id.voiceFileName));
        localEditableListItem.voiceFileTime = ((TextView) paramView.findViewById(R.id.voicFileTime));
        paramView.setTag(localEditableListItem);
      } else {
        localEditableListItem = (EditableListItem) paramView.getTag();
      }

      localEditableListItem.voiceFileName.setText(((VoiceFile) VoiceFilesActivity.this.voiceFiles.get(paramInt)).getShowName());
      localEditableListItem.voiceFileTime.setText(((VoiceFile) VoiceFilesActivity.this.voiceFiles.get(paramInt)).getShowTime());


      return paramView;
    }
  }

  class EditableListItem {
    CheckBox voiceFileCheck;
    TextView voiceFileName;
    TextView voiceFileTime;

  }


  private OnItemClickListener normalModeClickListener = new OnItemClickListener() {
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      ((VoiceFile) VoiceFilesActivity.this.voiceFiles.get(position)).setSelected(true);
      Log.d("Recorder", "点击了录音文件:" + ((VoiceFile) VoiceFilesActivity.this.voiceFiles.get(position)).getShowName());
      Intent localIntent = new Intent(VoiceFilesActivity.this, PlaySoundDialogActivity.class);
      Bundle localBundle = new Bundle();
      localBundle.putString("com.sdvdxl.phonerecorder.VoiceFilesActivity.selectedFile", ((VoiceFile) VoiceFilesActivity.this.voiceFiles.get(position)).getFullName());
      localIntent.putExtras(localBundle);
      VoiceFilesActivity.this.startActivity(localIntent);
    }
  };

  private  OnItemLongClickListener onItemLongClickListener =  new OnItemLongClickListener()
  {
    public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      Log.d("Recorder", "文件列表长按菜单");
      ((VoiceFile)VoiceFilesActivity.this.voiceFiles.get(paramAnonymousInt)).setSelected(true);
      VoiceFilesActivity.this.showDialog(1);
      return false;
    }
  };

}
/* Location:              H:\baiduyundownload\android\反编译\record_dex2jar.jar!\com\sdvdxl\phonerecorder\VoiceFilesActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */