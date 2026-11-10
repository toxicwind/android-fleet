package com.ju.lib.datacommunication.network.http.debug;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.os.SystemProperties;
import android.text.TextUtils;
import b.a.a.a.a;
import com.google.common.primitives.UnsignedLong;
import com.ju.lib.datacommunication.annotation.JuMethodAnnotation;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.error.NetworkErrorEnum;
import com.ju.lib.datacommunication.network.http.error.RequestFailedBean;
import com.ju.lib.datacommunication.network.http.error.RequestFailedManager;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import d.i;
import e.b.b;
import e.b.d;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes2.dex */
public class DebugHelper {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static String TAG = "DebugHelper";
    public static volatile DebugHelper instance;
    public static Boolean isDebugModel;
    public volatile DebugConfig debugConfig;
    public Map<DebugCheckType, DebugInfo> debugInfoMap = new ConcurrentHashMap();
    public String exDirPath;
    public Handler mWorkHandler;

    public DebugHelper() {
        HandlerThread handlerThread = new HandlerThread("NetworkDebug", 10);
        handlerThread.start();
        this.mWorkHandler = new Handler(handlerThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndSaveDebugTrace(DebugCheckType debugCheckType, DebugTrace debugTrace) throws Throwable {
        String debugTracePreInfo = this.debugConfig.getDebugTracePreInfo(debugCheckType, debugTrace);
        HttpLog.i(TAG, " checkAndSaveDebugTrace: ", debugCheckType, a.a(", preInfo: ", debugTracePreInfo), ", url:", debugTrace.url);
        if (debugTracePreInfo == null) {
            return;
        }
        if (!this.debugInfoMap.containsKey(debugCheckType)) {
            File file = new File(this.exDirPath + debugCheckType.filePath);
            if (!file.exists()) {
                try {
                    if (!file.createNewFile()) {
                        HttpLog.e(TAG, "cannot create file: " + this.exDirPath + debugCheckType.filePath);
                        return;
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            this.debugInfoMap.put(debugCheckType, new DebugInfo(debugCheckType, readFile(file)));
        }
        String unDuplicateData = this.debugInfoMap.get(debugCheckType).getUnDuplicateData(debugTrace);
        if (unDuplicateData != null) {
            writeFile(debugCheckType, a.a(debugTracePreInfo, unDuplicateData, "\n----------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n"));
        }
    }

    private <T> List<T> convertJsonArray(e.b.a aVar) {
        if (aVar == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < aVar.a(); i++) {
            try {
                if (!(aVar.get(i) instanceof String)) {
                    arrayList.add(aVar.get(i));
                } else if (!"".equals(((String) aVar.get(i)).trim())) {
                    arrayList.add(((String) aVar.get(i)).trim());
                }
            } catch (b e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    private Map<String, String> convertJsonObject(d dVar) {
        if (dVar == null) {
            return null;
        }
        HashMap map = new HashMap();
        Iterator<String> itA = dVar.a();
        while (itA.hasNext()) {
            String strTrim = itA.next().trim();
            map.put(strTrim, dVar.m(strTrim).trim());
        }
        return map;
    }

    public static DebugHelper getInstance() {
        if (instance == null) {
            synchronized (DebugHelper.class) {
                if (instance == null) {
                    instance = new DebugHelper();
                }
            }
        }
        return instance;
    }

    private String getStackTrace() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return "no stack...";
        }
        int iMin = Math.min(stackTrace.length, 25);
        String str = TAG;
        StringBuilder sbA = a.a("stackTrace.length: ");
        sbA.append(stackTrace.length);
        HttpLog.i(str, sbA.toString());
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 6; i < iMin; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if (stringBuffer.length() > 0) {
                stringBuffer.append(" <- ");
                stringBuffer.append(System.getProperty("line.separator"));
            }
            stringBuffer.append(MessageFormat.format("    {0}.{1}() {2}", stackTraceElement.getClassName(), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())));
        }
        return stringBuffer.toString();
    }

    @JuMethodAnnotation(description = "是否是调试模式,调试模式功能参考:http://wiki.hisensecloud.com/pages/viewpage.action?pageId=31088433")
    public static boolean isDebugModel() {
        if (isDebugModel == null) {
            isDebugModel = Boolean.valueOf("true".equals(SystemProperties.get("persist.his.ju_http_trace", "")));
        }
        return isDebugModel.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseConfig(File file) {
        try {
            d dVar = new d(readFile(file));
            this.debugConfig.respRules = convertJsonObject(dVar.f("respRules"));
            this.debugConfig.paramRules = convertJsonArray(dVar.e("paramRules"));
            this.debugConfig.urlRules = convertJsonArray(dVar.e("urlRules"));
            this.debugConfig.statusCodeRules = convertJsonArray(dVar.e("statusCodeRules"));
            HttpLog.i(TAG, "debugConfig: ", this.debugConfig);
        } catch (b e2) {
            HttpLog.e(TAG, " debugConfig parse error!!! pls check file: ", this.exDirPath, "httpTraceRule.json");
            e2.printStackTrace();
            isDebugModel = false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0046 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0043: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:28:0x0043 */
    private String readFile(File file) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                    }
                    String string = sb.toString();
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return string;
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                bufferedReader3 = bufferedReader2;
                if (bufferedReader3 != null) {
                    try {
                        bufferedReader3.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedReader3 != null) {
                bufferedReader3.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x005d -> B:30:0x0060). Please report as a decompilation issue!!! */
    private void writeFile(DebugCheckType debugCheckType, String str) throws Throwable {
        Throwable th;
        OutputStreamWriter outputStreamWriter;
        Exception e2;
        String str2 = this.exDirPath + debugCheckType.filePath;
        HttpLog.i(TAG, a.a(" writeFile path: ", str2), "append: ", str);
        OutputStreamWriter outputStreamWriter2 = null;
        try {
            try {
                try {
                    outputStreamWriter = new OutputStreamWriter(new FileOutputStream(new File(str2), true), "UTF-8");
                    try {
                        outputStreamWriter.write(str);
                        outputStreamWriter.flush();
                        outputStreamWriter.close();
                    } catch (Exception e3) {
                        e2 = e3;
                        outputStreamWriter2 = outputStreamWriter;
                        e2.printStackTrace();
                        if (outputStreamWriter2 != null) {
                            outputStreamWriter2.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (outputStreamWriter != null) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStreamWriter = outputStreamWriter2;
                }
            } catch (Exception e5) {
                e2 = e5;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
    }

    public DebugTrace buildDebugTrace(HiRequest hiRequest, Object obj) {
        final DebugTrace debugTrace = new DebugTrace();
        RequestFailedBean requestFailedBean = new RequestFailedBean(NetworkErrorEnum.UnknownError, hiRequest.getUrl());
        debugTrace.url = requestFailedBean.url();
        if (hiRequest.getMethod() == HiRequest.Method.POST) {
            requestFailedBean.requestParams(RequestFailedManager.getInstance().parseQuery(hiRequest));
        }
        debugTrace.requestParams = requestFailedBean.getRequestParams();
        debugTrace.stackTrace = getStackTrace();
        if (obj != null) {
            debugTrace.callBackCls = obj.getClass().getName();
        }
        this.mWorkHandler.post(new Runnable() { // from class: com.ju.lib.datacommunication.network.http.debug.DebugHelper.2
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                DebugHelper.this.checkAndSaveDebugTrace(DebugCheckType.Url, debugTrace);
                DebugHelper.this.checkAndSaveDebugTrace(DebugCheckType.Param, debugTrace);
            }
        });
        return debugTrace;
    }

    public void handleResponse(Response response, final DebugTrace debugTrace) {
        if (debugTrace != null) {
            debugTrace.respCode = response.code();
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody != null && responseBodyBody.contentLength() > 0 && responseBodyBody.contentLength() < 1000) {
                Charset charset = responseBodyBody.contentType() != null ? responseBodyBody.contentType().charset() : Charset.forName("UTF-8");
                i iVarSource = responseBodyBody.source();
                iVarSource.request(UnsignedLong.UNSIGNED_MASK);
                debugTrace.respBody = iVarSource.a().m8clone().a(charset);
            }
            this.mWorkHandler.post(new Runnable() { // from class: com.ju.lib.datacommunication.network.http.debug.DebugHelper.3
                @Override // java.lang.Runnable
                public void run() throws Throwable {
                    DebugHelper.getInstance().checkAndSaveDebugTrace(DebugCheckType.RespBody, debugTrace);
                    DebugHelper.getInstance().checkAndSaveDebugTrace(DebugCheckType.RespCode, debugTrace);
                }
            });
        }
    }

    public synchronized void init(String str) {
        HttpLog.i(TAG, "init pkgName: " + str);
        if (isDebugModel() && this.debugConfig == null) {
            this.exDirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/network/";
            HttpLog.i(TAG, " debug mode start, init exDirPath: " + this.exDirPath);
            final File file = new File(this.exDirPath + "httpTraceRule.json");
            if (file.exists() && file.canRead()) {
                if (TextUtils.isEmpty(str)) {
                    str = Process.myPid() + "";
                    HttpLog.e(TAG, "ohg, this process has not set pkgName: " + str);
                }
                this.exDirPath += str + "/";
                File file2 = new File(this.exDirPath);
                if (!file2.exists() && !file2.mkdir()) {
                    HttpLog.e(TAG, " mkdir failed, pls delete the dir and retry: " + this.exDirPath);
                    isDebugModel = false;
                    return;
                }
                this.debugConfig = new DebugConfig();
                this.mWorkHandler.post(new Runnable() { // from class: com.ju.lib.datacommunication.network.http.debug.DebugHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DebugHelper.this.parseConfig(file);
                    }
                });
            }
            HttpLog.e(TAG, "file" + this.exDirPath + "httpTraceRule.json not exists, pls make sure the file existsand has read permissions for every process!!!");
            isDebugModel = false;
        }
    }
}
