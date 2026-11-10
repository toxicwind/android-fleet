package com.ju.lib.datacommunication.network.http.address;

import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicAddressTools {
    public static final String TAG = "DynamicAddressTools";

    public static String AESDecrypt(String str) {
        try {
            EncryptAES encryptAES = new EncryptAES();
            byte[] bytes = "D5B6D8584F94B432".getBytes("UTF-8");
            byte[] bytes2 = "205681D89D731A8F".getBytes("UTF-8");
            byte[] bArrString2ByteArray = ByteUtils.string2ByteArray(str);
            encryptAES.init(bytes, bytes2);
            return new String(encryptAES.decrypt(bArrString2ByteArray), "UTF-8");
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isExists(String str) {
        return new File(str).exists();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.BufferedReader] */
    public static String readFile(String str) throws Throwable {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        HttpLog.d(TAG, "readFromFile()  enter");
        long jCurrentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        String string = null;
        string = null;
        string = null;
        string = null;
        string = null;
         = 0;
        ?? r6 = 0;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(str);
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
                        while (true) {
                            try {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                sb.append(line);
                            } catch (FileNotFoundException e2) {
                                e = e2;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                HttpLog.d(TAG, "readFromFile()  duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                                return string;
                            } catch (IOException e4) {
                                e = e4;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                    }
                                }
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                HttpLog.d(TAG, "readFromFile()  duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                                return string;
                            }
                        }
                        string = sb.toString();
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                        bufferedReader.close();
                    } catch (FileNotFoundException e7) {
                        e = e7;
                        bufferedReader = null;
                    } catch (IOException e8) {
                        e = e8;
                        bufferedReader = null;
                    } catch (Throwable th) {
                        th = th;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e9) {
                                e9.printStackTrace();
                            }
                        }
                        if (r6 == 0) {
                            throw th;
                        }
                        try {
                            r6.close();
                            throw th;
                        } catch (IOException e10) {
                            e10.printStackTrace();
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    r6 = str;
                }
            } catch (FileNotFoundException e11) {
                e = e11;
                bufferedReader = null;
                fileInputStream = null;
            } catch (IOException e12) {
                e = e12;
                bufferedReader = null;
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
            }
        } catch (IOException e13) {
            e13.printStackTrace();
        }
        HttpLog.d(TAG, "readFromFile()  duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        return string;
    }

    public static void saveFile(String str, String str2) throws Throwable {
        long jCurrentTimeMillis = System.currentTimeMillis();
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    File file = new File(str2);
                    if (!file.exists()) {
                        new File(file.getParent()).mkdirs();
                        file.createNewFile();
                    }
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        fileOutputStream2.write(str.getBytes("UTF-8"));
                        fileOutputStream2.close();
                        fileOutputStream2.close();
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        HttpLog.w(TAG, "saveObjectToFile()  exception : ", e);
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        HttpLog.d(TAG, "saveObjectToFile()  duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            } catch (Exception e5) {
                e = e5;
            }
            HttpLog.d(TAG, "saveObjectToFile()  duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
