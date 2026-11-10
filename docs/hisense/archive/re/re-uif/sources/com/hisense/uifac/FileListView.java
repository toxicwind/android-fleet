package com.hisense.uifac;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hisense.tvcm.FacPropertyDemo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FileListView implements DialogInterface.OnClickListener, DialogInterface.OnKeyListener {
    private FileListAdapter adapter = new FileListAdapter();
    private List<File> fileList;
    private AlertDialog mAlertDialog;
    private String[] mAudioTypeList;
    private Context mContext;
    private String[] mPicTypeList;
    private String[] mVideoTypeList;
    private static List<String> videoEnableList = new ArrayList();
    private static List<String> pictureEnableList = new ArrayList();
    private static List<String> musicEnableList = new ArrayList();

    static {
        videoEnableList.add(".mp4");
        videoEnableList.add(".avi");
        videoEnableList.add(".3gp");
        videoEnableList.add(".rmvb");
        videoEnableList.add(".rm");
        videoEnableList.add(".gif");
        videoEnableList.add(".wmv");
        videoEnableList.add(".mkv");
        videoEnableList.add(".mpg");
        videoEnableList.add(".vob");
        videoEnableList.add(".mov");
        videoEnableList.add(".flv");
        videoEnableList.add(".swf");
        pictureEnableList.add(".jpg");
        pictureEnableList.add(".jpeg");
        pictureEnableList.add(".bmp");
        pictureEnableList.add(".png");
        pictureEnableList.add(".gif");
        musicEnableList.add(".mp3");
    }

    public FileListView(Activity activity) {
        this.mVideoTypeList = null;
        this.mAudioTypeList = null;
        this.mPicTypeList = null;
        this.mContext = activity;
        if (activity != null) {
            this.mVideoTypeList = activity.getResources().getStringArray(R.array.facmenu_array_for_video_type);
            this.mAudioTypeList = this.mContext.getResources().getStringArray(R.array.facmenu_array_for_audio_type);
            this.mPicTypeList = this.mContext.getResources().getStringArray(R.array.facmenu_array_for_pic_type);
        }
    }

    public void show(List<File> list) {
        this.fileList = list;
        if (this.mContext != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            builder.setAdapter(this.adapter, this);
            builder.setTitle("USB: " + getUsbMountedNumber());
            AlertDialog alertDialogCreate = builder.create();
            this.mAlertDialog = alertDialogCreate;
            alertDialogCreate.setOnKeyListener(this);
            this.mAlertDialog.show();
        }
    }

    class FileListAdapter extends BaseAdapter {
        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        FileListAdapter() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (FileListView.this.fileList != null) {
                return FileListView.this.fileList.size();
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            if (FileListView.this.fileList == null || i < 0 || i >= FileListView.this.fileList.size()) {
                return null;
            }
            return ((File) FileListView.this.fileList.get(i)).getName();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (FileListView.this.fileList == null || i < 0 || i >= FileListView.this.fileList.size()) {
                return null;
            }
            if (view == null) {
                view = ((LayoutInflater) FileListView.this.mContext.getSystemService("layout_inflater")).inflate(android.R.layout.two_line_list_item, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view;
            TextView textView = (TextView) linearLayout.getChildAt(0);
            TextView textView2 = (TextView) linearLayout.getChildAt(1);
            textView.setText(((File) FileListView.this.fileList.get(i)).getName());
            textView2.setText(((File) FileListView.this.fileList.get(i)).getParent());
            return view;
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        List<File> list = this.fileList;
        if (list == null || i < 0 || i >= list.size()) {
            return;
        }
        openFile(this.fileList.get(i));
    }

    private void openFile(File file) {
        String parent = file.getParent();
        String name = file.getName();
        Log.d("Fac_FileListView", "openFile fileName = " + name);
        FacPropertyDemo.KindOfFile kindOfFile = kindOfFile(file);
        FacRootMenu facRootMenu = (FacRootMenu) this.mContext;
        if (kindOfFile == FacPropertyDemo.KindOfFile.VIDEO) {
            facRootMenu.startInnerVideoPlayer(parent, name);
        } else if (kindOfFile == FacPropertyDemo.KindOfFile.PICTURE) {
            facRootMenu.startPlayPicture(parent, name);
        } else if (kindOfFile == FacPropertyDemo.KindOfFile.MUSIC) {
            facRootMenu.startPlayMusic(parent, name);
        }
    }

    private FacPropertyDemo.KindOfFile kindOfFile(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        String name = file.getName();
        Log.d("Fac_FileListView", "kindOfFile file name" + name);
        int iLastIndexOf = name.lastIndexOf(".");
        if (iLastIndexOf < 0) {
            return null;
        }
        String lowerCase = name.substring(iLastIndexOf).toLowerCase();
        if (videoEnableList.contains(lowerCase) || isVideo(lowerCase)) {
            return FacPropertyDemo.KindOfFile.VIDEO;
        }
        if (pictureEnableList.contains(lowerCase) || isPic(lowerCase)) {
            return FacPropertyDemo.KindOfFile.PICTURE;
        }
        if (musicEnableList.contains(lowerCase) || isAudio(lowerCase)) {
            return FacPropertyDemo.KindOfFile.MUSIC;
        }
        return null;
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (i != 230 && i != 4293 && i != 209 && i != 4357) {
            return false;
        }
        this.mAlertDialog.dismiss();
        return true;
    }

    private int getUsbMountedNumber() {
        int i = 0;
        for (UsbDevice usbDevice : ((UsbManager) this.mContext.getSystemService("usb")).getDeviceList().values()) {
            if (usbDevice.getDeviceClass() == 0 && usbDevice.getInterface(0).getInterfaceClass() == 8) {
                i++;
            }
        }
        Log.i("Fac_FileListView", "getUsbCount num = " + i);
        return i;
    }

    private boolean isAudio(String str) {
        Log.d("Fac_FileListView", "isAudio file type  " + str);
        String strSubstring = str.substring(1);
        String[] strArr = this.mAudioTypeList;
        if (strArr != null && strArr.length > 0) {
            for (String str2 : strArr) {
                if (strSubstring.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isVideo(String str) {
        Log.d("Fac_FileListView", "isVideo file type  " + str);
        String strSubstring = str.substring(1);
        String[] strArr = this.mVideoTypeList;
        if (strArr != null && strArr.length > 0) {
            for (String str2 : strArr) {
                Log.d("Fac_FileListView", "isVideo file typeData  " + str2);
                if (strSubstring.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isPic(String str) {
        Log.d("Fac_FileListView", "isPic file type  " + str);
        String strSubstring = str.substring(1);
        String[] strArr = this.mPicTypeList;
        if (strArr != null && strArr.length > 0) {
            for (String str2 : strArr) {
                if (strSubstring.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
