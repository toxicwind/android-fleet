package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@WorkerThread
public final class zzeq implements Runnable {
    public final /* synthetic */ zzer zza;
    public final URL zzb;
    public final byte[] zzc;
    public final zzen zzd;
    public final String zze;
    public final Map<String, String> zzf;

    public zzeq(zzer zzerVar, String str, URL url, byte[] bArr, Map<String, String> map, zzen zzenVar) {
        this.zza = zzerVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzenVar);
        this.zzb = url;
        this.zzc = bArr;
        this.zzd = zzenVar;
        this.zze = str;
        this.zzf = map;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00c9 A[Catch: all -> 0x00ef, LOOP:1: B:26:0x00c3->B:28:0x00c9, LOOP_END, TryCatch #4 {all -> 0x00ef, blocks: (B:25:0x00c1, B:26:0x00c3, B:28:0x00c9, B:29:0x00cd), top: B:77:0x00c1 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x0144  */
    /* JADX WARN: Code duplicated, block: B:75:0x0184  */
    /* JADX WARN: Code duplicated, block: B:79:0x0168 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:81:0x0128 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x00cd A[EDGE_INSN: B:93:0x00cd->B:29:0x00cd BREAK  A[LOOP:1: B:26:0x00c3->B:28:0x00c9], SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x00fc: MOVE (r12 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:42:0x00fa */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0102: MOVE (r9 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:44:0x00ff */
    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        Throwable th;
        int i;
        HttpURLConnection httpURLConnection;
        Map map;
        IOException e2;
        Map map2;
        zzep zzepVar;
        zzfs zzfsVarZzaz;
        IOException iOException;
        int responseCode;
        Map map3;
        Map map4;
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        int i2;
        this.zza.zzax();
        int i3 = 0;
        OutputStream outputStream = null;
        try {
            zzer zzerVar = this.zza;
            URLConnection uRLConnectionOpenConnection = this.zzb.openConnection();
            if (!(uRLConnectionOpenConnection instanceof HttpURLConnection)) {
                throw new IOException("Failed to obtain HTTP connection");
            }
            httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
            httpURLConnection.setDefaultUseCaches(false);
            zzerVar.zzs.zzf();
            httpURLConnection.setConnectTimeout(60000);
            zzerVar.zzs.zzf();
            httpURLConnection.setReadTimeout(61000);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setDoInput(true);
            try {
                Map<String, String> map5 = this.zzf;
                if (map5 != null) {
                    for (Map.Entry<String, String> entry : map5.entrySet()) {
                        httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                if (this.zzc != null) {
                    byte[] bArrZzz = this.zza.zzf.zzu().zzz(this.zzc);
                    zzej zzejVarZzj = this.zza.zzs.zzay().zzj();
                    int length = bArrZzz.length;
                    zzejVarZzj.zzb("Uploading data. size", Integer.valueOf(length));
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.addRequestProperty("Content-Encoding", FirebaseInstallationServiceClient.GZIP_CONTENT_ENCODING);
                    httpURLConnection.setFixedLengthStreamingMode(length);
                    httpURLConnection.connect();
                    OutputStream outputStream2 = httpURLConnection.getOutputStream();
                    try {
                        outputStream2.write(bArrZzz);
                        outputStream2.close();
                        responseCode = httpURLConnection.getResponseCode();
                        try {
                            try {
                                Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
                                try {
                                    byteArrayOutputStream = new ByteArrayOutputStream();
                                    inputStream = httpURLConnection.getInputStream();
                                    try {
                                        bArr = new byte[1024];
                                        while (true) {
                                            i2 = inputStream.read(bArr);
                                            if (i2 > 0) {
                                                break;
                                            } else {
                                                byteArrayOutputStream.write(bArr, 0, i2);
                                            }
                                        }
                                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                                        inputStream.close();
                                        httpURLConnection.disconnect();
                                        zzfsVarZzaz = this.zza.zzs.zzaz();
                                        zzepVar = new zzep(this.zze, this.zzd, responseCode, null, byteArray, headerFields, null);
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    inputStream = null;
                                }
                            } catch (IOException e3) {
                                map2 = null;
                                iOException = e3;
                                i3 = responseCode;
                                if (outputStream != null) {
                                    try {
                                        outputStream.close();
                                    } catch (IOException e4) {
                                        this.zza.zzs.zzay().zzd().zzc("Error closing HTTP compressed POST connection output stream. appId", zzel.zzn(this.zze), e4);
                                    }
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                zzfsVarZzaz = this.zza.zzs.zzaz();
                                zzepVar = new zzep(this.zze, this.zzd, i3, iOException, null, map2, null);
                            } catch (Throwable th4) {
                                th = th4;
                                map = null;
                                i = responseCode;
                                if (outputStream != null) {
                                    try {
                                        outputStream.close();
                                    } catch (IOException e5) {
                                        this.zza.zzs.zzay().zzd().zzc("Error closing HTTP compressed POST connection output stream. appId", zzel.zzn(this.zze), e5);
                                    }
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                this.zza.zzs.zzaz().zzp(new zzep(this.zze, this.zzd, i, null, null, map, null));
                                throw th;
                            }
                        } catch (IOException e6) {
                            iOException = e6;
                            i3 = responseCode;
                            map2 = map4;
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            zzfsVarZzaz = this.zza.zzs.zzaz();
                            zzepVar = new zzep(this.zze, this.zzd, i3, iOException, null, map2, null);
                        } catch (Throwable th5) {
                            th = th5;
                            i = responseCode;
                            map = map3;
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            this.zza.zzs.zzaz().zzp(new zzep(this.zze, this.zzd, i, null, null, map, null));
                            throw th;
                        }
                    } catch (IOException e7) {
                        e2 = e7;
                        map2 = null;
                        outputStream = outputStream2;
                        iOException = e2;
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        zzfsVarZzaz = this.zza.zzs.zzaz();
                        zzepVar = new zzep(this.zze, this.zzd, i3, iOException, null, map2, null);
                        zzfsVarZzaz.zzp(zzepVar);
                    } catch (Throwable th6) {
                        th = th6;
                        i = 0;
                        map = null;
                        outputStream = outputStream2;
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        this.zza.zzs.zzaz().zzp(new zzep(this.zze, this.zzd, i, null, null, map, null));
                        throw th;
                    }
                } else {
                    responseCode = httpURLConnection.getResponseCode();
                    Map<String, List<String>> headerFields2 = httpURLConnection.getHeaderFields();
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    inputStream = httpURLConnection.getInputStream();
                    bArr = new byte[1024];
                    while (true) {
                        i2 = inputStream.read(bArr);
                        if (i2 > 0) {
                            break;
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i2);
                    }
                    byte[] byteArray2 = byteArrayOutputStream.toByteArray();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    zzfsVarZzaz = this.zza.zzs.zzaz();
                    zzepVar = new zzep(this.zze, this.zzd, responseCode, null, byteArray2, headerFields2, null);
                }
            } catch (IOException e8) {
                e2 = e8;
                map2 = null;
            } catch (Throwable th7) {
                th = th7;
                i = 0;
                map = null;
            }
            zzfsVarZzaz.zzp(zzepVar);
        } catch (IOException e9) {
            e2 = e9;
            httpURLConnection = null;
            map2 = null;
        } catch (Throwable th8) {
            th = th8;
            i = 0;
            httpURLConnection = null;
            map = null;
        }
    }
}
