package com.ju.lib.datacommunication.network.http.dns;

import com.ju.lib.datacommunication.network.http.core.v;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
final class e extends g {
    private final File d;
    private v e;

    public e(File file) {
        if (file == null) {
            throw new NullPointerException("Directory file is null! ");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Directory file is not a folder! ");
        }
        this.d = file;
    }

    private String i(long j) {
        return "GSLB" + j;
    }

    private long j(String str) {
        try {
            if (str.startsWith("GSLB")) {
                return Long.parseLong(str.substring(4));
            }
            return 0L;
        } catch (Exception unused) {
            return 0L;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.BufferedReader] */
    private v k() throws Throwable {
        File file;
        BufferedReader bufferedReader;
        File[] fileArrListFiles = this.d.listFiles();
        ?? r1 = 0;
        if (fileArrListFiles != null) {
            long j = -1;
            file = null;
            for (File file2 : fileArrListFiles) {
                long j2 = j(file2.getName());
                if (j2 > j) {
                    file = file2;
                    j = j2;
                }
            }
        } else {
            file = null;
        }
        try {
            try {
                if (file != null) {
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
                            v vVarE = p.e("lbgs.hismarttv.com", sb.toString());
                            try {
                                bufferedReader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return vVarE;
                        } catch (IOException e2) {
                            e = e2;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return null;
                        } catch (JSONException e3) {
                            e = e3;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return null;
                        }
                    } catch (IOException e4) {
                        e = e4;
                        bufferedReader = null;
                    } catch (JSONException e5) {
                        e = e5;
                        bufferedReader = null;
                    } catch (Throwable th) {
                        th = th;
                        if (r1 != 0) {
                            try {
                                r1.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e7) {
                e7.printStackTrace();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            r1 = this;
        }
    }

    /* JADX WARN: Code duplicated, block: B:36:0x006f  */
    /* JADX WARN: Code duplicated, block: B:38:0x0072  */
    /* JADX WARN: Code duplicated, block: B:41:0x007b  */
    /* JADX WARN: Code duplicated, block: B:43:0x008d  */
    /* JADX WARN: Code duplicated, block: B:45:0x0093 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:66:0x007a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x0090 A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:36:0x006f, please report this as an issue */
    private void l(v vVar) throws Throwable {
        File[] fileArrListFiles = this.d.listFiles();
        long j = 0;
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                long j2 = j(file.getName());
                if (j2 > j) {
                    j = j2;
                }
            }
        }
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                try {
                    OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(new File(this.d, i(j + 1))), "UTF-8");
                    try {
                        outputStreamWriter2.write(p.i(vVar));
                        outputStreamWriter2.flush();
                        outputStreamWriter2.close();
                    } catch (IOException e) {
                        e = e;
                        outputStreamWriter = outputStreamWriter2;
                        e.printStackTrace();
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                        if (fileArrListFiles != null) {
                            for (File file2 : fileArrListFiles) {
                                if (!file2.isFile()) {
                                    return;
                                }
                                if (g.b.matcher(file2.getName()).find()) {
                                    file2.delete();
                                }
                            }
                        }
                    } catch (JSONException e2) {
                        e = e2;
                        outputStreamWriter = outputStreamWriter2;
                        e.printStackTrace();
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                        if (fileArrListFiles != null) {
                            while (i < r9) {
                                if (!file2.isFile()) {
                                    return;
                                }
                                if (g.b.matcher(file2.getName()).find()) {
                                    file2.delete();
                                }
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        outputStreamWriter = outputStreamWriter2;
                        if (outputStreamWriter != null) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            } catch (IOException e5) {
                e = e5;
            } catch (JSONException e6) {
                e = e6;
            }
            if (fileArrListFiles != null) {
                while (i < r9) {
                    if (!file2.isFile()) {
                        return;
                    }
                    if (g.b.matcher(file2.getName()).find()) {
                        file2.delete();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.dns.g
    v c() {
        v vVar = this.e;
        return vVar != null ? vVar : k();
    }

    @Override // com.ju.lib.datacommunication.network.http.dns.g
    void h(v vVar) throws Throwable {
        this.e = vVar;
        l(vVar);
    }
}
