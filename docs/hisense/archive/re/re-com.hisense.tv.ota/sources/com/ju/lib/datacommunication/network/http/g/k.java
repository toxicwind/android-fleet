package com.ju.lib.datacommunication.network.http.g;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes.dex */
public class k {
    public static String a(String str) {
        try {
            m mVar = new m();
            byte[] bytes = "D5B6D8584F94B432".getBytes("UTF-8");
            byte[] bytes2 = "205681D89D731A8F".getBytes("UTF-8");
            byte[] bArrA = d.a(str);
            mVar.b(bytes, bytes2);
            return new String(mVar.a(bArrA), "UTF-8");
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean b(String str) {
        return new File(str).exists();
    }

    /* JADX WARN: Code duplicated, block: B:69:0x00a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x009c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:? A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v7, types: [java.io.BufferedReader] */
    public static String c(String str) throws Throwable {
        ?? r10;
        BufferedReader bufferedReader;
        FileInputStream fileInputStream;
        com.ju.lib.datacommunication.network.http.i.b.b("DynamicAddressTools", "readFromFile()  enter");
        long jCurrentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        String string = null;
        string = null;
        string = null;
        string = null;
        string = null;
        FileInputStream fileInputStream2 = null;
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
                            } catch (FileNotFoundException e) {
                                e = e;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                com.ju.lib.datacommunication.network.http.i.b.b("DynamicAddressTools", "readFromFile()  duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                                return string;
                            } catch (IOException e3) {
                                e = e3;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                com.ju.lib.datacommunication.network.http.i.b.b("DynamicAddressTools", "readFromFile()  duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                                return string;
                            }
                        }
                        string = sb.toString();
                        try {
                            fileInputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                        bufferedReader.close();
                    } catch (FileNotFoundException e6) {
                        e = e6;
                        bufferedReader = null;
                    } catch (IOException e7) {
                        e = e7;
                        bufferedReader = null;
                    } catch (Throwable th) {
                        th = th;
                        str = null;
                        fileInputStream2 = fileInputStream;
                        r10 = str;
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e8) {
                                e8.printStackTrace();
                            }
                        }
                        if (r10 != 0) {
                            throw th;
                        }
                        try {
                            r10.close();
                            throw th;
                        } catch (IOException e9) {
                            e9.printStackTrace();
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (FileNotFoundException e10) {
                e = e10;
                bufferedReader = null;
                fileInputStream = null;
            } catch (IOException e11) {
                e = e11;
                bufferedReader = null;
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                r10 = 0;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (r10 != 0) {
                    throw th;
                }
                r10.close();
                throw th;
            }
        } catch (IOException e12) {
            e12.printStackTrace();
        }
        com.ju.lib.datacommunication.network.http.i.b.b("DynamicAddressTools", "readFromFile()  duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        return string;
    }

    public static void d(String str, String str2) throws Throwable {
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
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        com.ju.lib.datacommunication.network.http.i.b.g("DynamicAddressTools", "saveObjectToFile()  exception : ", e);
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        com.ju.lib.datacommunication.network.http.i.b.b("DynamicAddressTools", "saveObjectToFile()  duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            } catch (Exception e4) {
                e = e4;
            }
            com.ju.lib.datacommunication.network.http.i.b.b("DynamicAddressTools", "saveObjectToFile()  duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
