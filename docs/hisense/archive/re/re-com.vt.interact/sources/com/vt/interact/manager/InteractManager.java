package com.vt.interact.manager;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.vt.interact.ACIFSignalingMsg;
import com.vt.interact.ICFileDetail;
import com.vt.interact.ICFilesMsg;
import com.vt.interact.ICHTMLEntryPkg;
import com.vt.interact.ICSignalingMsg;
import com.vt.interact.IHisTvInteractService;
import com.vt.interact.IHisTvInteractServiceListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class InteractManager {
    public static int INTERACT_CLIENT_AUDIO_VOLUME = 3;
    public static int INTERACT_CLIENT_NOTIFY_DOWNLOAD_STATE_CHANGE = 13;
    public static int INTERACT_CLIENT_NOTIFY_OPRATOR_BROWSER = 13;
    private static int NOTIFY_NETWORK_STATE_CHANGE = 30;
    private static final String TAG = "InteractManager";
    private static IHisTvInteractService iHisTvInteractService;
    private static IHisTvInteractServiceListener listener;
    private static int m_networkState;
    private AudioManager mAudioManager;
    private Context mContext;

    public native void initNativeInteract();

    public native int opratorInteract(int i, String str, String str2, String str3, String str4);

    public native void sentHELD(ICSignalingMsg iCSignalingMsg, ICHTMLEntryPkg[] iCHTMLEntryPkgArr);

    public native void sentNRTFile(ICFilesMsg iCFilesMsg, ICFileDetail[] iCFileDetailArr);

    public int testfun(int i) {
        return 0;
    }

    public int onInteractState(int i, String str, int i2) {
        String str2 = TAG;
        Log.d(str2, "[onInteractState] type: " + i);
        Log.d(str2, "[onInteractState] app_url: " + str);
        Log.d(str2, "[onInteractState] http_code: " + i2);
        IHisTvInteractServiceListener iHisTvInteractServiceListener = listener;
        if (iHisTvInteractServiceListener != null) {
            try {
                return iHisTvInteractServiceListener.onInteractState(i, str, i2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int setTvserverOperator(int i, String str, String str2, String str3, String str4) {
        String str5 = TAG;
        Log.d(str5, "[setTvserverOperator] type: " + i + ",first: " + str + ",second: " + str2);
        int i2 = 1;
        int tvserverOperator = 0;
        if (i == INTERACT_CLIENT_AUDIO_VOLUME) {
            if (Integer.parseInt(str) == 0) {
                setVolume(Integer.parseInt(str2));
                return 0;
            }
            if (Integer.parseInt(str) == 1) {
                return getVolume();
            }
            Log.e(str5, "[setTvserverOperator] first value is error!");
            return -1;
        }
        if (i == INTERACT_CLIENT_NOTIFY_OPRATOR_BROWSER && str.startsWith("download")) {
            String strSubstring = str.substring(str.indexOf(":") + 1);
            Log.d(str5, "notifyOpratorBrowser is called, the download_url = " + strSubstring);
            int iIndexOf = strSubstring.indexOf(" ");
            int iLastIndexOf = strSubstring.lastIndexOf(" ");
            Log.d(str5, "notifyOpratorBrowser is called, the first_index = " + iIndexOf + ",last_index=" + iLastIndexOf);
            String strSubstring2 = strSubstring.substring(0, iIndexOf);
            String strSubstring3 = strSubstring.substring(iIndexOf + 1, iLastIndexOf);
            String strSubstring4 = strSubstring.substring(iLastIndexOf + 1);
            Log.d(str5, "notifyOpratorBrowser is called, type_s = " + strSubstring2 + ",source_url=" + strSubstring3 + ",target_url=" + strSubstring4);
            if (strSubstring2.compareTo("cache") != 0) {
                if (strSubstring2.compareTo("mpd") == 0) {
                    i2 = 2;
                } else {
                    i2 = strSubstring2.compareTo("dash") == 0 ? 3 : 0;
                }
            }
            InteractiveDownLoad(i2, strSubstring3, strSubstring4);
            return 0;
        }
        IHisTvInteractServiceListener iHisTvInteractServiceListener = listener;
        if (iHisTvInteractServiceListener != null) {
            try {
                tvserverOperator = iHisTvInteractServiceListener.setTvserverOperator(i, str, str2, str3, str4);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "[setTvserverOperator] ret: " + tvserverOperator);
        return tvserverOperator;
    }

    public int getTvserverState(int i, String str, String str2) {
        int tvserverState;
        Log.d(TAG, "[getTvserverState] type: " + i + "first: " + str + "second: " + str2);
        IHisTvInteractServiceListener iHisTvInteractServiceListener = listener;
        if (iHisTvInteractServiceListener != null) {
            try {
                tvserverState = iHisTvInteractServiceListener.getTvserverState(i, str, str2);
            } catch (RemoteException e) {
                e.printStackTrace();
                tvserverState = 0;
            }
        } else {
            tvserverState = 0;
        }
        Log.d(TAG, "[getTvserverState] ret: " + tvserverState);
        return tvserverState;
    }

    public String getTvserverStateForString(int i, String str, String str2) {
        String tvserverStateForString;
        Log.d(TAG, "[getTvserverForString] type: " + i + "first: " + str + "second: " + str2);
        IHisTvInteractServiceListener iHisTvInteractServiceListener = listener;
        if (iHisTvInteractServiceListener != null) {
            try {
                tvserverStateForString = iHisTvInteractServiceListener.getTvserverStateForString(i, str, str2);
            } catch (RemoteException e) {
                e.printStackTrace();
                tvserverStateForString = "";
            }
        } else {
            tvserverStateForString = "";
        }
        Log.d(TAG, "[getTvserverForString] msg: " + tvserverStateForString);
        return tvserverStateForString;
    }

    public String interactCallTvplayer(String str, String str2, String str3) {
        String strInteractCallTvplayer;
        Log.d(TAG, "[interactCallTvplayer] category: " + str + "subType: " + str2 + "jsonString: " + str3);
        IHisTvInteractServiceListener iHisTvInteractServiceListener = listener;
        if (iHisTvInteractServiceListener != null) {
            try {
                strInteractCallTvplayer = iHisTvInteractServiceListener.interactCallTvplayer(str, str2, str3);
            } catch (RemoteException e) {
                e.printStackTrace();
                strInteractCallTvplayer = "";
            }
        } else {
            strInteractCallTvplayer = "";
        }
        Log.d(TAG, "[interactCallTvplayer] msg: " + strInteractCallTvplayer);
        return strInteractCallTvplayer;
    }

    public ACIFSignalingMsg getSignaling(String str) {
        Log.d(TAG, "[getSignaling] signalType: " + str);
        ACIFSignalingMsg aCIFSignalingMsg = new ACIFSignalingMsg();
        IHisTvInteractServiceListener iHisTvInteractServiceListener = listener;
        if (iHisTvInteractServiceListener == null) {
            return aCIFSignalingMsg;
        }
        try {
            return iHisTvInteractServiceListener.getSignaling(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return aCIFSignalingMsg;
        }
    }

    public void notifyRequestAppKey(int[] iArr) {
        IHisTvInteractServiceListener iHisTvInteractServiceListener = listener;
        if (iHisTvInteractServiceListener != null) {
            try {
                iHisTvInteractServiceListener.notifyRequestAppKey(iArr);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyNetworkState(int i) {
        Log.d(TAG, " notifyNetworkState call! state: " + i);
        opratorInteract(NOTIFY_NETWORK_STATE_CHANGE, Integer.toString(i), "", "", "");
    }

    private void setVolume(int i) {
        String str = TAG;
        Log.d(str, " setVolume call! index: " + i);
        AudioManager audioManager = (AudioManager) this.mContext.getSystemService("audio");
        this.mAudioManager = audioManager;
        audioManager.setStreamVolume(3, i, 1);
        Log.d(str, " end setVolume call! index: " + i);
    }

    private int getVolume() {
        String str = TAG;
        Log.d(str, "getVolume call ");
        AudioManager audioManager = (AudioManager) this.mContext.getSystemService("audio");
        this.mAudioManager = audioManager;
        int streamVolume = audioManager.getStreamVolume(3);
        Log.d(str, "getVolume ret =  " + streamVolume);
        return streamVolume;
    }

    private InteractManager() {
        this.mAudioManager = null;
        this.mContext = null;
        Log.d(TAG, "InteractManager,before loadLibrary");
        System.loadLibrary("interact_jni");
    }

    public static InteractManager getInstance() {
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final InteractManager sInstance = new InteractManager();

        private SingletonHolder() {
        }
    }

    public void init(Context context) {
        Log.d(TAG, "init,before initNativeInteract");
        this.mContext = context;
        initNativeInteract();
    }

    public static int createAtscDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            Log.d(TAG, "Dir " + str + " has already exists!");
            return 0;
        }
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        if (file.mkdir()) {
            Log.d(TAG, "mkdir " + str + " sucess!");
            return 0;
        }
        Log.e(TAG, "mkdir " + str + " failed!");
        return -1;
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "[AntPlayer] onBind(). return new BnInteractServer()");
        return new BnInteractServer();
    }

    class BnInteractServer extends IHisTvInteractService.Stub {
        BnInteractServer() {
        }

        @Override // com.vt.interact.IHisTvInteractService
        public int notifyOpratorInteract(int i, String str, String str2, String str3, String str4) throws RemoteException {
            Log.d(InteractManager.TAG, "[AntPlayer] basicTypes()");
            return InteractManager.this.opratorInteract(i, str, str2, str3, str4);
        }

        @Override // com.vt.interact.IHisTvInteractService
        public void OnReceivedNRTFile(ICFilesMsg iCFilesMsg, ICFileDetail[] iCFileDetailArr) throws RemoteException {
            Log.d(InteractManager.TAG, "OnReceivedNRTFile call");
            InteractManager.this.sentNRTFile(iCFilesMsg, iCFileDetailArr);
        }

        @Override // com.vt.interact.IHisTvInteractService
        public void OnReceivedHELD(ICSignalingMsg iCSignalingMsg, ICHTMLEntryPkg[] iCHTMLEntryPkgArr) throws RemoteException {
            Log.d(InteractManager.TAG, "OnReceivedHELD call");
            InteractManager.this.sentHELD(iCSignalingMsg, iCHTMLEntryPkgArr);
        }

        @Override // com.vt.interact.IHisTvInteractService
        public void registerListener(IHisTvInteractServiceListener iHisTvInteractServiceListener) throws RemoteException {
            Log.d(InteractManager.TAG, "[AntPlayer] livetv_registerListener()");
            if (iHisTvInteractServiceListener != null) {
                IHisTvInteractServiceListener unused = InteractManager.listener = iHisTvInteractServiceListener;
            }
        }

        @Override // com.vt.interact.IHisTvInteractService
        public void unregisterListener(IHisTvInteractServiceListener iHisTvInteractServiceListener) throws RemoteException {
            Log.d(InteractManager.TAG, "[AntPlayer] livetv_unRegisterListener()");
            IHisTvInteractServiceListener unused = InteractManager.listener = null;
        }
    }

    public void InteractiveDownLoad(int i, String str, String str2) {
        Log.e(TAG, "InteractiveDownLoad type=" + i + ",url=" + str + ",savePath=" + str2);
        new Thread(new DownLoadThread(i, str, str2)).start();
    }

    public class DownLoadThread implements Runnable {
        private String dlSavePath;
        private int dlType;
        private String dlUrl;

        public DownLoadThread(int i, String str, String str2) {
            this.dlType = i;
            this.dlUrl = str;
            this.dlSavePath = str2;
        }

        /* JADX WARN: Code duplicated, block: B:43:0x014c  */
        /* JADX WARN: Code duplicated, block: B:44:0x0154  */
        /* JADX WARN: Code duplicated, block: B:68:0x0183 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:74:0x018d A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:86:? A[SYNTHETIC] */
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            FileOutputStream fileOutputStream;
            InputStream inputStream;
            Exception e;
            int i;
            Log.d(InteractManager.TAG, "start download");
            InputStream inputStream2 = null;
            try {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.dlUrl).openConnection();
                    Log.d(InteractManager.TAG, "setDoOutput");
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        Log.d(InteractManager.TAG, "getInputStream");
                        String str = "/data/data/com.vt.interact/" + this.dlSavePath;
                        String strSubstring = this.dlUrl.substring(this.dlUrl.lastIndexOf("/") + 1);
                        Log.d(InteractManager.TAG, "fileName:" + strSubstring);
                        String str2 = str + strSubstring;
                        Log.d(InteractManager.TAG, "dirPath:" + str);
                        new File(str).mkdirs();
                        Log.d(InteractManager.TAG, "filePath:" + str2);
                        File file = new File(str2);
                        file.createNewFile();
                        Log.d(InteractManager.TAG, "createNewFile:" + str2);
                        Log.d(InteractManager.TAG, "getInputStream");
                        fileOutputStream = new FileOutputStream(file);
                        try {
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int i2 = inputStream.read(bArr);
                                    if (i2 == -1) {
                                        break;
                                    } else {
                                        fileOutputStream.write(bArr, 0, i2);
                                    }
                                }
                                Log.d(InteractManager.TAG, "finally");
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                fileOutputStream.close();
                            } catch (Exception e3) {
                                e = e3;
                                e.printStackTrace();
                                Log.d(InteractManager.TAG, "finally");
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                Log.d(InteractManager.TAG, "download end");
                                i = this.dlType;
                                if (i == 2) {
                                    readMPDContent(this.dlUrl, this.dlSavePath);
                                } else {
                                    if (i == 1) {
                                    }
                                    InteractManager.this.opratorInteract(InteractManager.INTERACT_CLIENT_NOTIFY_DOWNLOAD_STATE_CHANGE, String.valueOf(this.dlType), "1", "", this.dlUrl);
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            inputStream2 = inputStream;
                            Log.d(InteractManager.TAG, "finally");
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                    throw th;
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                    throw th;
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e7) {
                        e = e7;
                        fileOutputStream = null;
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = null;
                        inputStream2 = inputStream;
                        Log.d(InteractManager.TAG, "finally");
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                            throw th;
                        }
                        throw th;
                    }
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
            } catch (Exception e9) {
                inputStream = null;
                e = e9;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                Log.d(InteractManager.TAG, "finally");
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    throw th;
                }
                throw th;
            }
            Log.d(InteractManager.TAG, "download end");
            i = this.dlType;
            if (i == 2) {
                readMPDContent(this.dlUrl, this.dlSavePath);
            } else if (i == 1 && i != 3) {
                Log.d(InteractManager.TAG, "the type is error!!");
            } else {
                InteractManager.this.opratorInteract(InteractManager.INTERACT_CLIENT_NOTIFY_DOWNLOAD_STATE_CHANGE, String.valueOf(this.dlType), "1", "", this.dlUrl);
            }
        }

        private void readMPDContent(String str, String str2) {
            Log.d(InteractManager.TAG, "readMPDContent");
            String str3 = "";
            String strSubstring = str.substring(str.lastIndexOf("/") + 1);
            Log.d(InteractManager.TAG, "fileName:" + strSubstring);
            String str4 = ("/data/data/com.vt.interact/" + str2) + strSubstring;
            Log.d(InteractManager.TAG, "filePath:" + str4);
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(str4));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    } else {
                        str3 = str3 + line + "\n";
                    }
                }
                fileInputStream.close();
            } catch (FileNotFoundException unused) {
                Log.e("TestFile", "The File doesn't not exist.");
            } catch (IOException e) {
                Log.e("TestFile", e.getMessage());
            }
            String str5 = str3;
            Log.d(InteractManager.TAG, "mpd:" + str5);
            InteractManager.this.opratorInteract(InteractManager.INTERACT_CLIENT_NOTIFY_DOWNLOAD_STATE_CHANGE, String.valueOf(this.dlType), "1", str5, str);
        }
    }
}
