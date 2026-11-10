package com.mediatek;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Parcel;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class MtkMediaPlayer extends MediaPlayer {
    private static String strOfOutputPath = "X-tv-output-path";
    private static String strOfRMPriority = "X-tv-resource-manager-client-priority";
    private static String strOfSWAudioDecoder = "X-tv-assign-software-audio-decoder";
    private static String strOfSWVideoDecoder = "X-tv-assign-software-video-decoder";
    private static String strOfWithAudio = "X-tv-with-audio";
    private List<String> mCAStore;
    private boolean mUseCA;
    private int mPlayerType = -1;
    private MediaPlayer.OnInfoListener mLis = null;
    private MediaPlayer.OnInfoListener mInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.mediatek.MtkMediaPlayer.1
        @Override // android.media.MediaPlayer.OnInfoListener
        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            Log.d("MtkMediaPlayer", "onInfo what = " + i + " rep = -5006");
            if (i == -5006) {
                try {
                    Class<?> cls = Class.forName("android.media.MediaPlayer");
                    Field declaredField = cls.getDeclaredField("mIndexTrackPairs");
                    Field declaredField2 = cls.getDeclaredField("mInbandTrackIndices");
                    declaredField.setAccessible(true);
                    declaredField2.setAccessible(true);
                    synchronized (declaredField) {
                        declaredField.set(mediaPlayer, declaredField.getType().newInstance());
                        declaredField2.set(mediaPlayer, declaredField2.getType().newInstance());
                    }
                    Log.d("MtkMediaPlayer", "mIndexTrackPairs  = " + declaredField.get(mediaPlayer) + " mInbandTrackIndices = " + declaredField2.get(mediaPlayer));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (i == 3) {
                Log.i("MtkMediaPlayer", "EventHandler::handleMessage(): receive MEDIA_INFO_VIDEO_RENDERING_START");
            }
            if (MtkMediaPlayer.this.mLis != null) {
                return MtkMediaPlayer.this.mLis.onInfo(mediaPlayer, i, i2);
            }
            return false;
        }
    };

    @Deprecated
    public interface WebInterface {
        String getCookie(String str);

        String getUserAgent();
    }

    public MtkMediaPlayer() {
        this.mCAStore = new ArrayList();
        this.mUseCA = false;
        this.mCAStore = new ArrayList();
        this.mUseCA = false;
        Log.d("MtkMediaPlayer", "new MtkMediaPlayer");
    }

    private int assignSWVideoDecoder(boolean z) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                parcelObtain.writeInterfaceToken("android.media.IMediaPlayer");
                parcelObtain.writeInt(4096);
                parcelObtain.writeInt(z ? 1 : 0);
                invoke(parcelObtain, parcelObtain2);
            } catch (Exception unused) {
                Log.w("MtkMediaPlayer", "assignSWVideoDecoder Exception ");
            }
            return 0;
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    private int assignSWAudioDecoder(boolean z) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                parcelObtain.writeInterfaceToken("android.media.IMediaPlayer");
                parcelObtain.writeInt(4097);
                parcelObtain.writeInt(z ? 1 : 0);
                invoke(parcelObtain, parcelObtain2);
            } catch (Exception unused) {
                Log.w("MtkMediaPlayer", "assignSWAudioDecoder Exception ");
            }
            return 0;
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    private int setSurfaceType(int i) {
        Log.d("MtkMediaPlayer", "setSurfaceType Enter ");
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                parcelObtain.writeInterfaceToken("android.media.IMediaPlayer");
                parcelObtain.writeInt(4098);
                parcelObtain.writeInt(i);
                invoke(parcelObtain, parcelObtain2);
            } catch (Exception unused) {
                Log.w("MtkMediaPlayer", "setSurfaceType Exception ");
            }
            return 0;
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
            Log.d("MtkMediaPlayer", "setSurfaceType Leave ");
        }
    }

    private int setRMPriority(int i) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                parcelObtain.writeInterfaceToken("android.media.IMediaPlayer");
                parcelObtain.writeInt(4099);
                parcelObtain.writeInt(i);
                invoke(parcelObtain, parcelObtain2);
            } catch (Exception unused) {
                Log.w("MtkMediaPlayer", "setRMPriority Exception ");
            }
            return 0;
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    private int setWithAudio(boolean z) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                parcelObtain.writeInterfaceToken("android.media.IMediaPlayer");
                parcelObtain.writeInt(4100);
                parcelObtain.writeInt(z ? 1 : 0);
                invoke(parcelObtain, parcelObtain2);
            } catch (Exception unused) {
                Log.w("MtkMediaPlayer", "setWithAudio Exception ");
            }
            return 0;
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0058  */
    private void parseHeaders(Map<String, String> map) {
        int i;
        Log.d("MtkMediaPlayer", "parseHeaders Enter");
        if (map != null) {
            String str = map.get(strOfOutputPath);
            int withAudio = 0;
            if (str != null) {
                Log.d("MtkMediaPlayer", "valueOfOutputPath: " + str);
                boolean zEqualsIgnoreCase = str.equalsIgnoreCase("OUTPUT_VIDEO_MAIN");
                boolean zEqualsIgnoreCase2 = str.equalsIgnoreCase("OUTPUT_VIDEO_SUB");
                boolean zEqualsIgnoreCase3 = str.equalsIgnoreCase("OUTPUT_GRAPHIC");
                if (zEqualsIgnoreCase) {
                    i = 256;
                    Log.d("MtkMediaPlayer", "OUTPUT_VIDEO_MAIN case");
                } else if (zEqualsIgnoreCase2) {
                    i = 512;
                    Log.d("MtkMediaPlayer", "OUTPUT_VIDEO_SUB case");
                } else if (zEqualsIgnoreCase3) {
                    i = 1024;
                    Log.d("MtkMediaPlayer", "OUTPUT_GRAPHIC case");
                } else {
                    i = 0;
                }
            } else {
                i = 0;
            }
            Log.d("MtkMediaPlayer", "setSurfaceType before");
            try {
                withAudio = setSurfaceType(i);
            } catch (Exception unused) {
                Log.d("MtkMediaPlayer", "setSurfaceType Exception ");
            }
            Log.d("MtkMediaPlayer", "setSurfaceType ret" + withAudio);
            String str2 = map.get(strOfSWVideoDecoder);
            if (str2 != null) {
                Log.d("MtkMediaPlayer", "AssignSWVideoDecoder: " + str2);
                try {
                    assignSWVideoDecoder(str2.equalsIgnoreCase("true"));
                } catch (Exception unused2) {
                    Log.w("MtkMediaPlayer", "assignSWVideoDecoder Exception ");
                }
            }
            String str3 = map.get(strOfSWAudioDecoder);
            if (str3 != null) {
                Log.d("MtkMediaPlayer", "value_AssignSWAudioDecoder: " + str3);
                try {
                    assignSWAudioDecoder(str3.equalsIgnoreCase("true"));
                } catch (Exception unused3) {
                    Log.w("MtkMediaPlayer", "assignSWAudioDecoder Exception ");
                }
            }
            String str4 = map.get(strOfRMPriority);
            if (str4 != null) {
                Log.d("MtkMediaPlayer", "valueOfRMPriority: " + str4);
                try {
                    withAudio = setRMPriority(Integer.parseInt(str4));
                } catch (Exception unused4) {
                    Log.w("MtkMediaPlayer", "setRMPriority Exception ");
                }
                Log.d("MtkMediaPlayer", "setRMPriority ret" + withAudio);
            }
            String str5 = map.get(strOfWithAudio);
            if (str5 != null) {
                Log.d("MtkMediaPlayer", "valueOfWithAudio: " + str5);
                try {
                    withAudio = setWithAudio(str5.equalsIgnoreCase("true"));
                } catch (Exception unused5) {
                    Log.w("MtkMediaPlayer", "setRMPriority Exception ");
                }
                Log.d("MtkMediaPlayer", "setRMPriority ret" + withAudio);
            }
        }
        Log.d("MtkMediaPlayer", "parseHeaders Leave!!");
    }

    @Override // android.media.MediaPlayer
    public void setDataSource(Context context, Uri uri) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        Log.d("MtkMediaPlayer", "setDataSource(Context context, Uri uri) API:1");
        setDataSource(context, uri, (Map<String, String>) null);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0029  */
    /* JADX WARN: Code duplicated, block: B:36:0x0092  */
    @Override // android.media.MediaPlayer
    @Deprecated
    public void setDataSource(Context context, Uri uri, Map<String, String> map) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        Parcel parcelObtain;
        Parcel parcelObtain2;
        Log.d("MtkMediaPlayer", "setDataSource(Context context, Uri uri, Map<String, String> headers) API:2");
        String scheme = uri.getScheme();
        int i = 0;
        if (scheme == null || scheme.equals("file")) {
            setDataSource(uri.getPath());
        } else {
            AssetFileDescriptor assetFileDescriptor = null;
            try {
                AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
                if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                    String path = uri.getPath();
                    if (path != null && this.mPlayerType != -1) {
                        if (6 == this.mPlayerType) {
                            path = "https://mtk://player_type=6" + path;
                        } else if (7 == this.mPlayerType) {
                            path = "https://mtk://player_type=7" + path;
                        }
                    }
                    Log.d("MtkMediaPlayer", "setDataSource(String path): NewPath " + path);
                    if (6 == this.mPlayerType || 7 == this.mPlayerType) {
                        super.setDataSource(path, map);
                    }
                    if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                        assetFileDescriptorOpenAssetFileDescriptor.close();
                    }
                } else if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                    assetFileDescriptorOpenAssetFileDescriptor.close();
                }
            } catch (IOException unused) {
                if (0 != 0) {
                    assetFileDescriptor.close();
                }
                Log.d("MtkMediaPlayer", "Couldn't open file on client side, trying server side");
                setDataSource(uri.toString(), map);
                if (this.mUseCA && this.mCAStore.size() != 0) {
                    parcelObtain = Parcel.obtain();
                    parcelObtain2 = Parcel.obtain();
                    try {
                        try {
                            parcelObtain.writeInterfaceToken("android.media.IMediaPlayer");
                            parcelObtain.writeInt(8223);
                            parcelObtain.writeString(this.mCAStore.get(0));
                            invoke(parcelObtain, parcelObtain2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        parcelObtain.recycle();
                        parcelObtain2.recycle();
                    } catch (Throwable th) {
                        parcelObtain.recycle();
                        parcelObtain2.recycle();
                        throw th;
                    }
                }
            } catch (SecurityException unused2) {
                if (0 != 0) {
                    assetFileDescriptor.close();
                }
                Log.d("MtkMediaPlayer", "Couldn't open file on client side, trying server side");
                setDataSource(uri.toString(), map);
                if (this.mUseCA) {
                    parcelObtain = Parcel.obtain();
                    parcelObtain2 = Parcel.obtain();
                    parcelObtain.writeInterfaceToken("android.media.IMediaPlayer");
                    parcelObtain.writeInt(8223);
                    parcelObtain.writeString(this.mCAStore.get(0));
                    invoke(parcelObtain, parcelObtain2);
                    parcelObtain.recycle();
                    parcelObtain2.recycle();
                }
            } catch (Throwable th2) {
                if (0 != 0) {
                    assetFileDescriptor.close();
                }
                throw th2;
            }
        }
        int i2 = this.mPlayerType;
        if (6 == i2 || 7 == i2) {
            String lowerCase = uri.getPath().toLowerCase();
            if (lowerCase.startsWith("/") && lowerCase.endsWith("ssif")) {
                i = 1;
            }
            configPlayer(i);
        }
        try {
            parseHeaders(map);
        } catch (Exception unused3) {
            Log.w("MtkMediaPlayer", "setDataSource Exception ");
        }
    }

    /* JADX WARN: Code duplicated, block: B:12:0x003e  */
    @Override // android.media.MediaPlayer
    public void setDataSource(String str) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        String str2;
        boolean z;
        Log.d("MtkMediaPlayer", "setDataSource(String path) API:3");
        if (str == null) {
            Log.e("MtkMediaPlayer", "path is null!");
            return;
        }
        int i = this.mPlayerType;
        if (i == -1) {
            str2 = str;
        } else if (6 == i) {
            str2 = "https://mtk://player_type=6" + str;
        } else if (7 == i) {
            str2 = "https://mtk://player_type=7" + str;
        } else {
            str2 = str;
        }
        Log.d("MtkMediaPlayer", "setDataSource(String path): NewPath " + str2);
        String lowerCase = str.toLowerCase();
        boolean z2 = lowerCase.endsWith(".pvr") || lowerCase.endsWith(".tshift");
        if (lowerCase.startsWith("http://") || lowerCase.startsWith("https://")) {
            Log.d("MtkMediaPlayer", "setDataSource(String path): http= true ");
            z = true;
        } else {
            Log.d("MtkMediaPlayer", "setDataSource(String path): http= false ");
            z = false;
        }
        if (z) {
            int i2 = this.mPlayerType;
            if (6 == i2 || 7 == i2) {
                super.setDataSource(str2);
                if (this.mUseCA && this.mCAStore.size() != 0) {
                    Parcel parcelObtain = Parcel.obtain();
                    Parcel parcelObtain2 = Parcel.obtain();
                    try {
                        try {
                            parcelObtain.writeInterfaceToken("android.media.IMediaPlayer");
                            parcelObtain.writeInt(8223);
                            parcelObtain.writeString(this.mCAStore.get(0));
                            invoke(parcelObtain, parcelObtain2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        parcelObtain.recycle();
                        parcelObtain2.recycle();
                    } catch (Throwable th) {
                        parcelObtain.recycle();
                        parcelObtain2.recycle();
                        throw th;
                    }
                }
            } else {
                super.setDataSource(str);
            }
        } else if (z2) {
            super.setDataSource(str2);
        } else if (!z2 && !z) {
            int i3 = this.mPlayerType;
            if (6 == i3 || 7 == i3) {
                super.setDataSource(str2);
            } else {
                super.setDataSource(str);
            }
        }
        int i4 = this.mPlayerType;
        if (6 == i4 || 7 == i4) {
            String lowerCase2 = str.toLowerCase();
            configPlayer((lowerCase2.startsWith("/") && lowerCase2.endsWith("ssif")) ? 1 : 0);
        }
    }

    /* JADX WARN: Code duplicated, block: B:12:0x003e  */
    public void setDataSource(String str, Map<String, String> map) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        String str2;
        boolean z;
        Log.d("MtkMediaPlayer", "setDataSource(String path, Map<String, String> headers) API:4");
        if (str == null) {
            Log.e("MtkMediaPlayer", "path is null!");
            return;
        }
        int i = this.mPlayerType;
        if (i == -1) {
            str2 = str;
        } else if (6 == i) {
            str2 = "https://mtk://player_type=6" + str;
        } else if (7 == i) {
            str2 = "https://mtk://player_type=7" + str;
        } else {
            str2 = str;
        }
        Log.d("MtkMediaPlayer", "setDataSource(String path): NewPath " + str2);
        String lowerCase = str.toLowerCase();
        int i2 = 0;
        boolean z2 = lowerCase.endsWith(".pvr") || lowerCase.endsWith(".tshift");
        if (lowerCase.startsWith("http://") || lowerCase.startsWith("https://")) {
            Log.d("MtkMediaPlayer", "setDataSource(): http= true ");
            z = true;
        } else {
            Log.d("MtkMediaPlayer", "setDataSource(): http= false ");
            z = false;
        }
        if (z) {
            int i3 = this.mPlayerType;
            if (6 == i3 || 7 == i3) {
                super.setDataSource(str2, map);
            } else {
                super.setDataSource(str, map);
            }
        } else if (z2) {
            super.setDataSource(str2, map);
        } else if (!z2 && !z) {
            int i4 = this.mPlayerType;
            if (6 == i4 || 7 == i4) {
                super.setDataSource(str2, map);
            } else {
                super.setDataSource(str, map);
            }
        }
        int i5 = this.mPlayerType;
        if (6 == i5 || 7 == i5) {
            String lowerCase2 = str.toLowerCase();
            if (lowerCase2.startsWith("/") && lowerCase2.endsWith("ssif")) {
                i2 = 1;
            }
            configPlayer(i2);
        }
        try {
            parseHeaders(map);
        } catch (Exception unused) {
            Log.w("MtkMediaPlayer", "setDataSource Exception ");
        }
    }

    public boolean setSubtitleDataSource(String str) {
        int i;
        Log.d("MtkMediaPlayer", "setSubtitleDataSource called. uri=" + str);
        int i2 = this.mPlayerType;
        if (6 != i2 && 7 != i2) {
            return true;
        }
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                parcelObtain.writeInterfaceToken("android.media.IMediaPlayer");
                parcelObtain.writeInt(8216);
                parcelObtain.writeString(str);
                invoke(parcelObtain, parcelObtain2);
                i = parcelObtain2.readInt();
                parcelObtain.recycle();
                parcelObtain2.recycle();
            } catch (Exception e) {
                e.printStackTrace();
                parcelObtain.recycle();
                parcelObtain2.recycle();
                i = 0;
            }
            return i >= 1;
        } catch (Throwable th) {
            parcelObtain.recycle();
            parcelObtain2.recycle();
            throw th;
        }
    }

    @Override // android.media.MediaPlayer
    public void addTimedTextSource(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        Log.d("MtkMediaPlayer", "addTimedTextSource API1 called. path=" + str + "; mimeType=" + str2);
        int i = this.mPlayerType;
        if (6 == i || 7 == i) {
            setSubtitleDataSource(str);
        } else {
            super.addTimedTextSource(str, str2);
        }
    }

    @Override // android.media.MediaPlayer
    public void addTimedTextSource(Context context, Uri uri, String str) throws IllegalStateException, IOException, IllegalArgumentException {
        Log.d("MtkMediaPlayer", "addTimedTextSource API2 called. uri=" + uri.toString() + "; mimeType=" + str);
        int i = this.mPlayerType;
        if (6 == i || 7 == i) {
            setSubtitleDataSource(uri.getPath());
        } else {
            super.addTimedTextSource(context, uri, str);
        }
    }

    private int configPlayer(int i) throws IllegalStateException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                parcelObtain.writeInterfaceToken("android.media.IMediaPlayer");
                parcelObtain.writeInt(8227);
                parcelObtain.writeInt(i);
                invoke(parcelObtain, parcelObtain2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    @Override // android.media.MediaPlayer
    public void pause() throws IllegalStateException {
        Log.d("MtkMediaPlayer", "pause");
        super.pause();
    }

    @Override // android.media.MediaPlayer
    public void setOnInfoListener(MediaPlayer.OnInfoListener onInfoListener) {
        this.mLis = onInfoListener;
        super.setOnInfoListener(this.mInfoListener);
    }
}
