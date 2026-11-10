package com.hmct.cloud.sdk.utils;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.protobuf.MessageSchema;
import com.hmct.cloud.sdk.bean.global.StartupInfo;
import com.ju.lib.datacommunication.network.http.Version;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class StartAppUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f2084a = "暂时无法跳转应用";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f2085b = "返回异常";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Toast f2086c;

    /* JADX WARN: Code duplicated, block: B:100:0x020b  */
    /* JADX WARN: Code duplicated, block: B:103:0x0217  */
    /* JADX WARN: Code duplicated, block: B:105:0x021d  */
    /* JADX WARN: Code duplicated, block: B:111:0x024d A[Catch: Exception -> 0x0267, TryCatch #1 {Exception -> 0x0267, blocks: (B:109:0x022d, B:111:0x024d, B:113:0x0253, B:115:0x0259), top: B:129:0x022d }] */
    /* JADX WARN: Code duplicated, block: B:121:0x0277  */
    /* JADX WARN: Code duplicated, block: B:122:0x029a  */
    /* JADX WARN: Code duplicated, block: B:36:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:69:0x0189  */
    /* JADX WARN: Code duplicated, block: B:71:0x0191  */
    /* JADX WARN: Code duplicated, block: B:72:0x0198  */
    /* JADX WARN: Code duplicated, block: B:74:0x019e  */
    /* JADX WARN: Code duplicated, block: B:76:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:80:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:81:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:83:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:85:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:87:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:88:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:89:0x01d9  */
    /* JADX WARN: Code duplicated, block: B:90:0x01df  */
    /* JADX WARN: Code duplicated, block: B:92:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:99:0x0205 A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Instruction removed from duplicated block: B:121:0x0277, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:122:0x029a, please report this as an issue */
    public static void a(Context context, Intent intent, String str, String str2, boolean z, boolean z2) {
        Intent intent2;
        String str3;
        String string;
        Cursor cursorQuery;
        Intent intent3 = intent;
        if (intent3 != null) {
            String str4 = "com.hisense.store.tv";
            if (!"SuperApp".equals(str2) && !"SuperApp_v4".equals(str2)) {
                if (!checkPackageInfo(context, str, z, z2)) {
                    intent3 = new Intent("android.intent.action.VIEW");
                    intent3.addFlags(67108864);
                    intent3.setData(Uri.parse("himarket://details?id=" + str + "&isAutoDownload=1"));
                }
                intent2 = intent3;
                intent2.addFlags(67108864);
                if (a.a()) {
                }
                Log.d("StartAppUtil", "tv is vidaa 123");
                Log.d("StartAppUtil", "packageName is " + str4);
                if (TextUtils.isEmpty(str4)) {
                    str3 = new String("AppCenter");
                } else if (str4.equalsIgnoreCase("com.jamdeo.tv.vod")) {
                    str3 = new String("OnDemand");
                } else if (str4.equalsIgnoreCase("com.hisense.tv.gamecenter")) {
                    if (a.a()) {
                        str3 = new String("AppCenter");
                    } else {
                        str3 = new String("AppCenter");
                    }
                } else if (str4.equalsIgnoreCase("com.hisense.hicloud.edca")) {
                    if (!a.d()) {
                        str3 = new String("AppCenter");
                    } else if (a.e()) {
                        str3 = new String("OnDemand");
                    } else {
                        str3 = new String("Education");
                    }
                } else if (str4.equalsIgnoreCase("com.hisense.hitv.shopping")) {
                    str3 = new String("AppCenter");
                } else {
                    str3 = new String("AppCenter");
                }
                string = str3;
                if (a.c()) {
                    cursorQuery = context.getContentResolver().query(Uri.parse("content://com.jamdeo.tv.provider.hostedapps/hosted_apps"), new String[]{"silo"}, "package=?", new String[]{str4}, null);
                    if (cursorQuery != null) {
                        string = cursorQuery.getString(cursorQuery.getColumnIndex("silo"));
                        cursorQuery.close();
                    }
                } else {
                    cursorQuery = context.getContentResolver().query(Uri.parse("content://com.jamdeo.tv.provider.hostedapps/hosted_apps"), new String[]{"silo"}, "package=?", new String[]{str4}, null);
                    if (cursorQuery != null) {
                        string = cursorQuery.getString(cursorQuery.getColumnIndex("silo"));
                        cursorQuery.close();
                    }
                }
                if (!TextUtils.isEmpty(intent2.getStringExtra("startupType"))) {
                    Log.d("StartAppUtil", "silo is " + string);
                    Intent intent4 = new Intent();
                    intent4.setAction("com.jamdeo.tv.policy.SILO_SWITCH_EVENT");
                    intent4.putExtra("jamdeo_silo_name", string);
                    intent4.putExtra("silo_name", string);
                    context.sendBroadcast(intent4);
                    return;
                }
                Log.d("StartAppUtil", "silo is " + string);
                Intent intent5 = new Intent();
                intent5.setAction("com.jamdeo.tv.policy.SILO_SWITCH_EVENT");
                PendingIntent activity = PendingIntent.getActivity(context, 0, intent2, MessageSchema.REQUIRED_MASK);
                intent5.putExtra("jamdeo_silo_name", string);
                intent5.putExtra("silo_name", string);
                intent5.putExtra("com.jamdeo.tv.ui.internal.SiloConstants.PendingIntent", activity);
                intent5.putExtra("com.jamdeo.tv.ui.internal.KeyCodeIntentHandler.PendingIntent", activity);
                intent5.putExtra("com.jamdeo.tv.common.SiloConstants.PendingIntent", activity);
                context.sendBroadcast(intent5);
                return;
            }
            if (!str.equalsIgnoreCase("com.hisense.hitv.shopping") && !str.equalsIgnoreCase("com.hisense.hicloud.edca") && !str.equalsIgnoreCase("com.hisense.tv.gamecenter") && !str.equalsIgnoreCase("com.hisense.store.tv") && !str.equalsIgnoreCase("com.jamdeo.tv.vod") && !str.equalsIgnoreCase("com.hisense.hitv.hicloud.account")) {
                if (!checkPackageInfo(context, str, z, z2)) {
                    int intExtra = intent3.getIntExtra("downType", 0);
                    intent3.addFlags(67108864);
                    if (intExtra == 1) {
                        intent3.setClassName("com.jamdeo.tv.vod", "com.hisense.base.activity.AppLoadingActivity");
                    } else {
                        intent3.setClassName("com.jamdeo.tv.vod", "com.ju.old.library.ui.thirdpartner.ThirdPartnerActivity");
                    }
                    intent3.putExtra(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME, str);
                    intent3.setData(null);
                    intent3.removeExtra("startupType");
                }
            }
            intent2 = intent3;
            str4 = "com.jamdeo.tv.vod";
            intent2.addFlags(67108864);
            if (!a.a() || a.b() || a.c() || a.g()) {
                Log.d("StartAppUtil", "tv is vidaa 123");
                Log.d("StartAppUtil", "packageName is " + str4);
                if (TextUtils.isEmpty(str4)) {
                    str3 = new String("AppCenter");
                } else if (str4.equalsIgnoreCase("com.jamdeo.tv.vod")) {
                    str3 = new String("OnDemand");
                } else if (str4.equalsIgnoreCase("com.hisense.tv.gamecenter")) {
                    if (a.a() || a.b()) {
                        str3 = new String("AppCenter");
                    } else {
                        str3 = new String("GameCenter");
                    }
                } else if (str4.equalsIgnoreCase("com.hisense.hicloud.edca")) {
                    if (!a.d()) {
                        str3 = new String("AppCenter");
                    } else if (a.e()) {
                        str3 = new String("OnDemand");
                    } else {
                        str3 = new String("Education");
                    }
                } else if (str4.equalsIgnoreCase("com.hisense.hitv.shopping") || !a.d()) {
                    str3 = new String("AppCenter");
                } else {
                    str3 = a.e() ? new String("OnDemand") : new String("Shopping");
                }
                string = str3;
                if ((a.c() || a.g()) && "com.hisense.tv.gamecenter".equals(context.getPackageName()) && !TextUtils.isEmpty(str4)) {
                    try {
                        cursorQuery = context.getContentResolver().query(Uri.parse("content://com.jamdeo.tv.provider.hostedapps/hosted_apps"), new String[]{"silo"}, "package=?", new String[]{str4}, null);
                        if (cursorQuery != null && !cursorQuery.isClosed() && cursorQuery.moveToFirst()) {
                            string = cursorQuery.getString(cursorQuery.getColumnIndex("silo"));
                            cursorQuery.close();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (!TextUtils.isEmpty(intent2.getStringExtra("startupType"))) {
                    Log.d("StartAppUtil", "silo is " + string);
                    Intent intent6 = new Intent();
                    intent6.setAction("com.jamdeo.tv.policy.SILO_SWITCH_EVENT");
                    intent6.putExtra("jamdeo_silo_name", string);
                    intent6.putExtra("silo_name", string);
                    context.sendBroadcast(intent6);
                    return;
                }
                Log.d("StartAppUtil", "silo is " + string);
                Intent intent7 = new Intent();
                intent7.setAction("com.jamdeo.tv.policy.SILO_SWITCH_EVENT");
                PendingIntent activity2 = PendingIntent.getActivity(context, 0, intent2, MessageSchema.REQUIRED_MASK);
                intent7.putExtra("jamdeo_silo_name", string);
                intent7.putExtra("silo_name", string);
                intent7.putExtra("com.jamdeo.tv.ui.internal.SiloConstants.PendingIntent", activity2);
                intent7.putExtra("com.jamdeo.tv.ui.internal.KeyCodeIntentHandler.PendingIntent", activity2);
                intent7.putExtra("com.jamdeo.tv.common.SiloConstants.PendingIntent", activity2);
                context.sendBroadcast(intent7);
                return;
            }
            if (a.i()) {
                if (!a.h()) {
                    Log.d("StartAppUtil", "tv is afterVidda4");
                    Intent intent8 = new Intent();
                    intent8.setAction("com.jamdeo.tv.policy.SILO_SWITCH_EVENT");
                    intent8.putExtra("silo_name", str4);
                    intent8.putExtra("com.jamdeo.tv.common.SiloConstants.PendingIntent", PendingIntent.getActivity(context, 0, intent2, MessageSchema.REQUIRED_MASK));
                    context.sendBroadcast(intent8);
                    return;
                }
                Log.d("StartAppUtil", "tv is isVidda6 or isDoublehome");
                Intent intent9 = new Intent();
                intent9.setAction("com.jamdeo.tv.policy.SILO_SWITCH_EVENT");
                intent9.putExtra("silo_name", str4);
                intent9.putExtra("app_switch_intent", intent2);
                if (intent2.getIntExtra("startupType", 0) == 2) {
                    Log.d("StartAppUtil", "is broadcast");
                    intent9.putExtra("action_type", "action_type_broadcast");
                }
                String stringExtra = intent2.getStringExtra("superAppParam");
                String stringExtra2 = intent2.getStringExtra("thirdPackageName");
                if (!TextUtils.isEmpty(stringExtra)) {
                    try {
                        if ("1".equals(new e.b.d(stringExtra).a("vertical", ""))) {
                            intent9.putExtra("rotateDirection", MtkTvRatingConvert2Goo.SUB_RATING_STR_V);
                        }
                    } catch (e.b.b e3) {
                        e3.printStackTrace();
                    }
                }
                if (stringExtra2 != null) {
                    intent9.putExtra("thirdPackageName", stringExtra2);
                }
                context.sendBroadcast(intent9);
                return;
            }
            Log.d("StartAppUtil", "tv is vision");
            try {
                context.startActivity(intent2);
                return;
            } catch (Exception unused) {
            }
            str4 = str;
            intent2 = intent3;
            intent2.addFlags(67108864);
            if (a.a()) {
            }
            Log.d("StartAppUtil", "tv is vidaa 123");
            Log.d("StartAppUtil", "packageName is " + str4);
            if (TextUtils.isEmpty(str4)) {
                str3 = new String("AppCenter");
            } else if (str4.equalsIgnoreCase("com.jamdeo.tv.vod")) {
                str3 = new String("OnDemand");
            } else if (str4.equalsIgnoreCase("com.hisense.tv.gamecenter")) {
                if (a.a()) {
                    str3 = new String("AppCenter");
                } else {
                    str3 = new String("AppCenter");
                }
            } else if (str4.equalsIgnoreCase("com.hisense.hicloud.edca")) {
                if (!a.d()) {
                    str3 = new String("AppCenter");
                } else if (a.e()) {
                    str3 = new String("OnDemand");
                } else {
                    str3 = new String("Education");
                }
            } else if (str4.equalsIgnoreCase("com.hisense.hitv.shopping")) {
                str3 = new String("AppCenter");
            } else {
                str3 = new String("AppCenter");
            }
            string = str3;
            if (a.c()) {
                cursorQuery = context.getContentResolver().query(Uri.parse("content://com.jamdeo.tv.provider.hostedapps/hosted_apps"), new String[]{"silo"}, "package=?", new String[]{str4}, null);
                if (cursorQuery != null) {
                    string = cursorQuery.getString(cursorQuery.getColumnIndex("silo"));
                    cursorQuery.close();
                }
            } else {
                cursorQuery = context.getContentResolver().query(Uri.parse("content://com.jamdeo.tv.provider.hostedapps/hosted_apps"), new String[]{"silo"}, "package=?", new String[]{str4}, null);
                if (cursorQuery != null) {
                    string = cursorQuery.getString(cursorQuery.getColumnIndex("silo"));
                    cursorQuery.close();
                }
            }
            if (!TextUtils.isEmpty(intent2.getStringExtra("startupType"))) {
                Log.d("StartAppUtil", "silo is " + string);
                Intent intent10 = new Intent();
                intent10.setAction("com.jamdeo.tv.policy.SILO_SWITCH_EVENT");
                intent10.putExtra("jamdeo_silo_name", string);
                intent10.putExtra("silo_name", string);
                context.sendBroadcast(intent10);
                return;
            }
            Log.d("StartAppUtil", "silo is " + string);
            Intent intent11 = new Intent();
            intent11.setAction("com.jamdeo.tv.policy.SILO_SWITCH_EVENT");
            PendingIntent activity3 = PendingIntent.getActivity(context, 0, intent2, MessageSchema.REQUIRED_MASK);
            intent11.putExtra("jamdeo_silo_name", string);
            intent11.putExtra("silo_name", string);
            intent11.putExtra("com.jamdeo.tv.ui.internal.SiloConstants.PendingIntent", activity3);
            intent11.putExtra("com.jamdeo.tv.ui.internal.KeyCodeIntentHandler.PendingIntent", activity3);
            intent11.putExtra("com.jamdeo.tv.common.SiloConstants.PendingIntent", activity3);
            context.sendBroadcast(intent11);
            return;
        }
        showToast(context, f2084a);
    }

    public static void backSilo(Context context) {
        Log.d("StartAppUtil", "backSilo:");
        if (a.d()) {
            if (a.f()) {
                Log.d("StartAppUtil", "isPx530OTA:" + context.getPackageName());
                Intent intent = new Intent("com.jamdeo.tv.policy.SILO_EVENT");
                intent.putExtra("silo_prog_key", 88);
                intent.putExtra("jamdeo_previous_silo_back_app_name", "OnDemand");
                context.sendBroadcast(intent);
                return;
            }
            Log.d("StartAppUtil", "isVidda3OTA:" + context.getPackageName());
            Intent intent2 = new Intent("com.jamdeo.tv.policy.SILO_EVENT");
            intent2.putExtra("silo_prog_key", 88);
            intent2.putExtra("silo_back_app_name", context.getPackageName());
            context.sendBroadcast(intent2);
        }
    }

    public static boolean checkPackageInfo(Context context, String str, boolean z, boolean z2) {
        if (!z) {
            return z2;
        }
        if (str != null && !"".equals(str)) {
            try {
                if (context.getPackageManager().getPackageInfo(str, 0) != null) {
                    return true;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return false;
    }

    public static String getPackageVersion(Context context, String str) {
        if (str != null && !"".equals(str)) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 8192);
                if (packageInfo != null) {
                    return packageInfo.versionName;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return "";
    }

    public static void showToast(Context context, String str) {
        Toast toast = f2086c;
        if (toast != null) {
            toast.cancel();
        }
        Toast toastMakeText = Toast.makeText(context.getApplicationContext(), str, 0);
        f2086c = toastMakeText;
        toastMakeText.setGravity(48, 0, 30);
        f2086c.show();
    }

    public static void startApp(Context context, String str) {
        startApp(context, str, true);
    }

    public static Intent startAppIntent(Context context, String str) {
        return startAppIntent(context, str, true, false);
    }

    public static void startApp(Context context, String str, boolean z) {
        startApp(context, str, z, true, true, false, "H");
    }

    /* JADX WARN: Code duplicated, block: B:136:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:138:0x02ca  */
    /* JADX WARN: Code duplicated, block: B:141:0x02cf  */
    /* JADX WARN: Code duplicated, block: B:144:0x02db  */
    /* JADX WARN: Code duplicated, block: B:146:0x02e5  */
    /* JADX WARN: Code duplicated, block: B:149:0x02ec A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:163:0x0318  */
    public static Intent startAppIntent(Context context, String str, boolean z, boolean z2) {
        e.b.a aVar;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String strA;
        String string;
        ApplicationInfo applicationInfo;
        int i;
        Intent launchIntentForPackage;
        String[] strArrSplit;
        e.b.d dVarA;
        e.b.a aVar2;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        SDKUtil.LogD("StartAppUtil", "param=" + str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            e.b.d dVar = new e.b.d(str);
            String str13 = "startupType";
            int iA = dVar.a("startupType", 0);
            String str14 = "";
            String strA2 = dVar.a("startupUrl", "");
            String str15 = RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME;
            String strA3 = dVar.a(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME, "");
            if (iA == 1 || iA == 2 || iA == 3) {
                dVar.a("startupCommand", "");
                dVar.a("startupUrl", "");
                dVar.a(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME, "");
                return null;
            }
            if (iA != 4) {
                Log.e("StartAppUtil", "param=" + str);
                return null;
            }
            Intent intent = new Intent();
            intent.putExtra("thirdPackageName", context.getPackageName());
            if (TextUtils.isEmpty(strA2)) {
                aVar = null;
            } else {
                try {
                    aVar = new e.b.a(strA2);
                } catch (e.b.b e2) {
                    e2.printStackTrace();
                    aVar = null;
                }
            }
            String str16 = "com.hisense.hicloud.edca";
            if (aVar == null || aVar.a() <= 0) {
                str2 = "com.hisense.hicloud.edca";
                str3 = "com.hisense.hitv.hicloud.account";
                str4 = "com.hisense.store.tv";
                str5 = "com.hisense.tv.gamecenter";
                str6 = RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME;
                str7 = "com.ju.old.library.ui.thirdpartner.ThirdPartnerActivity";
                if (TextUtils.isEmpty(strA3)) {
                    strA = strA3;
                } else {
                    strA = strA3;
                    Intent launchIntentForPackage2 = context.getPackageManager().getLaunchIntentForPackage(strA);
                    if (launchIntentForPackage2 != null) {
                        intent = launchIntentForPackage2;
                    }
                    if (a.d() && launchIntentForPackage2 != null && (strA.equalsIgnoreCase("com.hisense.hitv.shopping") || strA.equalsIgnoreCase(str2) || strA.equalsIgnoreCase(str5) || strA.equalsIgnoreCase(str4) || strA.equalsIgnoreCase("com.jamdeo.tv.vod") || strA.equalsIgnoreCase(str3))) {
                        intent.putExtra("startupType", 0);
                    }
                }
                string = null;
            } else {
                intent.addFlags(MessageSchema.REQUIRED_MASK);
                Bundle bundle = new Bundle();
                strA = strA3;
                int i2 = 0;
                String strA4 = null;
                int iA2 = 0;
                while (i2 < aVar.a()) {
                    try {
                        dVarA = aVar.a(i2);
                    } catch (e.b.b e3) {
                        e3.printStackTrace();
                        dVarA = null;
                    }
                    if (dVarA != null) {
                        aVar2 = aVar;
                        String strA5 = dVarA.a("key", str14);
                        str8 = str13;
                        if (strA5.equals(str13)) {
                            str9 = str16;
                            iA2 = dVarA.a("value", 0);
                        } else {
                            str9 = str16;
                            if (strA5.equals(str15)) {
                                strA = dVarA.a("value", str14);
                            } else if (strA5.equals(Params.ACTION)) {
                                intent.setAction(dVarA.a("value", str14));
                            } else if (strA5.equals("className")) {
                                intent.setClassName(strA, dVarA.a("value", str14));
                                strA4 = dVarA.a("value", str14);
                            } else if (strA5.equals("intentType")) {
                                intent.setType(dVarA.a("value", str14));
                            } else if (strA5.equals("category")) {
                                intent.addCategory(dVarA.a("value", str14));
                            } else if (strA5.equals("intentFlags")) {
                                intent.addFlags(dVarA.a("value", 0));
                            } else {
                                if (strA5.equals("uri")) {
                                    intent.setData(Uri.parse(dVarA.a("value", str14)));
                                } else {
                                    String strA6 = dVarA.a("type", str14);
                                    str11 = str15;
                                    if (strA6.equals("String")) {
                                        bundle.putString(strA5, dVarA.a("value", str14));
                                    } else if (strA6.equals("int")) {
                                        bundle.putInt(strA5, dVarA.a("value", 0));
                                    } else if (strA6.equals("long")) {
                                        str12 = str14;
                                        str10 = strA4;
                                        bundle.putLong(strA5, dVarA.a("value", 0L));
                                    } else {
                                        str12 = str14;
                                        str10 = strA4;
                                        if (strA6.equals("boolean")) {
                                            bundle.putBoolean(strA5, dVarA.a("value", false));
                                        } else if (strA6.equals("double")) {
                                            bundle.putDouble(strA5, dVarA.a("value", Double.NaN));
                                        }
                                    }
                                    str12 = str14;
                                    str10 = strA4;
                                }
                                strA4 = str10;
                                i2++;
                                str14 = str12;
                                aVar = aVar2;
                                str16 = str9;
                                str13 = str8;
                                str15 = str11;
                            }
                        }
                        str11 = str15;
                        str12 = str14;
                        i2++;
                        str14 = str12;
                        aVar = aVar2;
                        str16 = str9;
                        str13 = str8;
                        str15 = str11;
                    } else {
                        aVar2 = aVar;
                        str8 = str13;
                        str9 = str16;
                    }
                    str10 = strA4;
                    str11 = str15;
                    str12 = str14;
                    strA4 = str10;
                    i2++;
                    str14 = str12;
                    aVar = aVar2;
                    str16 = str9;
                    str13 = str8;
                    str15 = str11;
                }
                String str17 = str13;
                String str18 = str16;
                String str19 = strA4;
                str6 = str15;
                if (TextUtils.isEmpty(DeviceConfig.getProp("ro.product.linktv"))) {
                    try {
                        applicationInfo = context.getPackageManager().getApplicationInfo("com.jamdeo.tv.vod", 128);
                    } catch (PackageManager.NameNotFoundException e4) {
                        e4.printStackTrace();
                        applicationInfo = null;
                    }
                    string = (applicationInfo == null || applicationInfo.metaData == null) ? null : applicationInfo.metaData.getString("package_version");
                } else {
                    string = "SuperApp";
                }
                if ("SuperApp".equals(string) || "SuperApp_v4".equals(string)) {
                    if (strA.equalsIgnoreCase("com.hisense.hitv.shopping")) {
                        str3 = "com.hisense.hitv.hicloud.account";
                        str4 = "com.hisense.store.tv";
                        str5 = "com.hisense.tv.gamecenter";
                        str2 = str18;
                    } else {
                        str2 = str18;
                        if (strA.equalsIgnoreCase(str2)) {
                            str3 = "com.hisense.hitv.hicloud.account";
                            str4 = "com.hisense.store.tv";
                            str5 = "com.hisense.tv.gamecenter";
                        } else {
                            str5 = "com.hisense.tv.gamecenter";
                            if (strA.equalsIgnoreCase(str5)) {
                                str3 = "com.hisense.hitv.hicloud.account";
                                str4 = "com.hisense.store.tv";
                            } else {
                                str4 = "com.hisense.store.tv";
                                if (strA.equalsIgnoreCase(str4) || strA.equalsIgnoreCase("com.jamdeo.tv.vod")) {
                                    str3 = "com.hisense.hitv.hicloud.account";
                                } else {
                                    str3 = "com.hisense.hitv.hicloud.account";
                                    if (strA.equalsIgnoreCase(str3)) {
                                    }
                                    if (i != 1) {
                                        if (i == 2) {
                                            intent.putExtras(bundle);
                                            context.sendBroadcast(intent);
                                        } else if (i == 3) {
                                        }
                                        if (!TextUtils.isEmpty(strA)) {
                                            launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(strA);
                                            if (launchIntentForPackage != null) {
                                                intent = launchIntentForPackage;
                                            }
                                            if (a.d() && launchIntentForPackage != null && (strA.equalsIgnoreCase("com.hisense.hitv.shopping") || strA.equalsIgnoreCase(str2) || strA.equalsIgnoreCase(str5) || strA.equalsIgnoreCase(str4) || strA.equalsIgnoreCase("com.jamdeo.tv.vod") || strA.equalsIgnoreCase(str3))) {
                                                intent.putExtra(str17, i);
                                            }
                                        }
                                    } else {
                                        intent.putExtras(bundle);
                                    }
                                }
                            }
                        }
                    }
                    str7 = "com.ju.old.library.ui.thirdpartner.ThirdPartnerActivity";
                    intent.setClassName("com.jamdeo.tv.vod", str7);
                    intent.setData(null);
                    i = 1;
                    if (i != 1) {
                        if (i == 2) {
                            intent.putExtras(bundle);
                            context.sendBroadcast(intent);
                        } else if (i == 3) {
                        }
                        if (!TextUtils.isEmpty(strA)) {
                            launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(strA);
                            if (launchIntentForPackage != null) {
                                intent = launchIntentForPackage;
                            }
                            if (a.d()) {
                                intent.putExtra(str17, i);
                            }
                        }
                    } else {
                        intent.putExtras(bundle);
                    }
                } else {
                    if ("com.jamdeo.tv.vod".equals(strA)) {
                        String packageVersion = getPackageVersion(context, strA);
                        if (!TextUtils.isEmpty(packageVersion) && (strArrSplit = packageVersion.split(".")) != null && strArrSplit.length > 0) {
                            try {
                                if (Integer.parseInt(strArrSplit[0]) >= 2017 && !"com.jamdeo.tv.vod.detail.ContentDetailActivity".equals(str19)) {
                                    intent.setClassName(strA, strA + ".ThirdPartnerEntryActivity");
                                }
                            } catch (NumberFormatException e5) {
                                e5.printStackTrace();
                            }
                        }
                    }
                    str3 = "com.hisense.hitv.hicloud.account";
                    str4 = "com.hisense.store.tv";
                    str5 = "com.hisense.tv.gamecenter";
                    str2 = str18;
                }
                str7 = "com.ju.old.library.ui.thirdpartner.ThirdPartnerActivity";
                i = iA2;
                if (i != 1) {
                    if (i == 2) {
                        intent.putExtras(bundle);
                        context.sendBroadcast(intent);
                    } else if (i == 3) {
                    }
                    if (!TextUtils.isEmpty(strA)) {
                        launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(strA);
                        if (launchIntentForPackage != null) {
                            intent = launchIntentForPackage;
                        }
                        if (a.d()) {
                            intent.putExtra(str17, i);
                        }
                    }
                } else {
                    intent.putExtras(bundle);
                }
            }
            if (!"SuperApp".equals(string) && !"SuperApp_v4".equals(string)) {
                if (checkPackageInfo(context, strA, z, z2)) {
                    return intent;
                }
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.addFlags(MessageSchema.REQUIRED_MASK);
                intent2.addFlags(67108864);
                intent2.setData(Uri.parse("himarket://details?id=" + strA + "&isAutoDownload=1"));
                return intent2;
            }
            if (strA.equalsIgnoreCase("com.hisense.hitv.shopping") || strA.equalsIgnoreCase(str2) || strA.equalsIgnoreCase(str5) || strA.equalsIgnoreCase(str4) || strA.equalsIgnoreCase("com.jamdeo.tv.vod") || strA.equalsIgnoreCase(str3) || checkPackageInfo(context, strA, z, z2)) {
                return intent;
            }
            intent.addFlags(67108864);
            String str20 = str6;
            intent.putExtra(str20, "com.jamdeo.tv.vod");
            intent.setClassName("com.jamdeo.tv.vod", str7);
            intent.putExtra(str20, strA);
            intent.setData(null);
            return intent;
        } catch (e.b.b e6) {
            e6.printStackTrace();
            return null;
        }
    }

    public static void startApp(Context context, String str, boolean z, String str2) {
        startApp(context, str, z, true, true, false, str2);
    }

    public static void startApp(Context context, String str, boolean z, boolean z2) {
        startApp(context, str, true, true, z, z2, "H");
    }

    /* JADX WARN: Code duplicated, block: B:151:0x030e  */
    /* JADX WARN: Code duplicated, block: B:153:0x0311  */
    /* JADX WARN: Code duplicated, block: B:156:0x0315  */
    /* JADX WARN: Code duplicated, block: B:160:0x0325  */
    /* JADX WARN: Code duplicated, block: B:163:0x032c A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:177:0x0358  */
    /* JADX WARN: Code duplicated, block: B:179:0x035d  */
    /* JADX WARN: Code duplicated, block: B:181:0x0361  */
    public static void startApp(Context context, String str, boolean z, boolean z2, boolean z3, boolean z4, String str2) {
        e.b.d dVar;
        int iA;
        String strA;
        String strA2;
        e.b.a aVar;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        ApplicationInfo applicationInfo;
        String string;
        int i;
        Intent launchIntentForPackage;
        String[] strArrSplit;
        e.b.d dVarA;
        e.b.a aVar2;
        String str12;
        String str13;
        String str14;
        String str15;
        Log.d("StartAppUtil", "param=" + str + "|| isStartSilo=" + z + "|| newTask=" + z2 + "|| needCheckAppInstall=" + z3 + "|| isInstall=" + z4);
        if (TextUtils.isEmpty(str)) {
            showToast(context, f2084a);
            return;
        }
        try {
            dVar = new e.b.d(str);
        } catch (e.b.b e2) {
            e2.printStackTrace();
            dVar = null;
        }
        String str16 = RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME;
        String str17 = "startupType";
        if (dVar != null) {
            iA = dVar.a("startupType", 0);
            strA = dVar.a("startupUrl", "");
            strA2 = dVar.a(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME, "");
        } else {
            iA = 0;
            strA = null;
            strA2 = null;
        }
        if (iA != 1 && iA != 2 && iA != 3) {
            if (iA == 4) {
                Intent intent = new Intent();
                intent.putExtra("thirdPackageName", context.getPackageName());
                intent.putExtra("video_jump_param", str);
                if (TextUtils.isEmpty(strA)) {
                    aVar = null;
                } else {
                    try {
                        aVar = new e.b.a(strA);
                    } catch (e.b.b e3) {
                        e3.printStackTrace();
                        aVar = null;
                    }
                }
                String str18 = "com.hisense.hicloud.edca";
                String str19 = "com.hisense.hitv.shopping";
                String str20 = strA2;
                if (aVar != null && aVar.a() > 0) {
                    if (z2) {
                        intent.addFlags(MessageSchema.REQUIRED_MASK);
                    }
                    Bundle bundle = new Bundle();
                    String strA3 = str20;
                    String strA4 = null;
                    int i2 = 0;
                    int i3 = 0;
                    while (i2 < aVar.a()) {
                        try {
                            dVarA = aVar.a(i2);
                        } catch (e.b.b e4) {
                            e4.printStackTrace();
                            dVarA = null;
                        }
                        if (dVarA != null) {
                            aVar2 = aVar;
                            String strA5 = dVarA.a("key", "");
                            str12 = str18;
                            if (strA5.equals(str17)) {
                                str13 = str19;
                                int iA2 = dVarA.a("value", 0);
                                intent.putExtra(str17, iA2);
                                i3 = iA2;
                            } else {
                                str13 = str19;
                                if (strA5.equals(str16)) {
                                    strA3 = dVarA.a("value", "");
                                } else if (strA5.equals(Params.ACTION)) {
                                    intent.setAction(dVarA.a("value", ""));
                                } else if (strA5.equals("className")) {
                                    intent.setClassName(strA3, dVarA.a("value", ""));
                                    strA4 = dVarA.a("value", "");
                                } else if (strA5.equals("intentType")) {
                                    intent.setType(dVarA.a("value", ""));
                                } else if (strA5.equals("category")) {
                                    intent.addCategory(dVarA.a("value", ""));
                                } else if (strA5.equals("intentFlags")) {
                                    intent.addFlags(dVarA.a("value", 0));
                                } else {
                                    if (strA5.equals("uri")) {
                                        intent.setData(Uri.parse(dVarA.a("value", "")));
                                    } else {
                                        String strA6 = dVarA.a("type", "");
                                        str15 = str16;
                                        if (strA6.equals("String")) {
                                            bundle.putString(strA5, dVarA.a("value", ""));
                                        } else if (strA6.equals("int")) {
                                            bundle.putInt(strA5, dVarA.a("value", 0));
                                        } else if (strA6.equals("long")) {
                                            str14 = str17;
                                            bundle.putLong(strA5, dVarA.a("value", 0L));
                                        } else {
                                            str14 = str17;
                                            if (strA6.equals("boolean")) {
                                                bundle.putBoolean(strA5, dVarA.a("value", false));
                                            } else if (strA6.equals("double")) {
                                                bundle.putDouble(strA5, dVarA.a("value", Double.NaN));
                                            }
                                        }
                                        str14 = str17;
                                    }
                                    i2++;
                                    aVar = aVar2;
                                    str19 = str13;
                                    str18 = str12;
                                    str16 = str15;
                                    str17 = str14;
                                }
                            }
                        } else {
                            aVar2 = aVar;
                            str12 = str18;
                            str13 = str19;
                        }
                        str15 = str16;
                        str14 = str17;
                        i2++;
                        aVar = aVar2;
                        str19 = str13;
                        str18 = str12;
                        str16 = str15;
                        str17 = str14;
                    }
                    String str21 = str18;
                    String str22 = str19;
                    str5 = str16;
                    String str23 = str17;
                    boolean z5 = Version.VersionName.equals(DeviceConfig.getProp("ro.product.linktv")) || "1.2".equals(DeviceConfig.getProp("ro.product.linktv"));
                    if (z5) {
                        string = "SuperApp";
                    } else {
                        try {
                            applicationInfo = context.getPackageManager().getApplicationInfo("com.jamdeo.tv.vod", 128);
                        } catch (PackageManager.NameNotFoundException e5) {
                            e5.printStackTrace();
                            applicationInfo = null;
                        }
                        string = (applicationInfo == null || applicationInfo.metaData == null) ? null : applicationInfo.metaData.getString("package_version");
                    }
                    if (!"SuperApp".equals(string) && !"SuperApp_v4".equals(string)) {
                        if ("com.jamdeo.tv.vod".equals(strA3)) {
                            String packageVersion = getPackageVersion(context, strA3);
                            if (!TextUtils.isEmpty(packageVersion) && (strArrSplit = packageVersion.split(".")) != null && strArrSplit.length > 0) {
                                try {
                                    if (Integer.parseInt(strArrSplit[0]) >= 2017 && !"com.jamdeo.tv.vod.detail.ContentDetailActivity".equals(strA4)) {
                                        intent.setClassName(strA3, strA3 + ".ThirdPartnerEntryActivity");
                                    }
                                } catch (NumberFormatException e6) {
                                    e6.printStackTrace();
                                }
                            }
                        }
                        str7 = "com.hisense.hitv.hicloud.account";
                        str4 = "com.hisense.store.tv";
                        str6 = "com.hisense.tv.gamecenter";
                        str19 = str22;
                        str8 = str21;
                    } else {
                        str19 = str22;
                        if (strA3.equalsIgnoreCase(str19)) {
                            str7 = "com.hisense.hitv.hicloud.account";
                            str4 = "com.hisense.store.tv";
                            str6 = "com.hisense.tv.gamecenter";
                            str8 = str21;
                        } else {
                            str8 = str21;
                            if (strA3.equalsIgnoreCase(str8)) {
                                str7 = "com.hisense.hitv.hicloud.account";
                                str4 = "com.hisense.store.tv";
                                str6 = "com.hisense.tv.gamecenter";
                            } else {
                                str6 = "com.hisense.tv.gamecenter";
                                if (strA3.equalsIgnoreCase(str6)) {
                                    str7 = "com.hisense.hitv.hicloud.account";
                                    str4 = "com.hisense.store.tv";
                                } else {
                                    str4 = "com.hisense.store.tv";
                                    if (strA3.equalsIgnoreCase(str4) || strA3.equalsIgnoreCase("com.jamdeo.tv.vod") || strA3.equalsIgnoreCase("com.ju.music")) {
                                        str7 = "com.hisense.hitv.hicloud.account";
                                    } else {
                                        str7 = "com.hisense.hitv.hicloud.account";
                                        if (strA3.equalsIgnoreCase(str7)) {
                                        }
                                        if (i == 1) {
                                            intent.putExtras(bundle);
                                        } else if (i != 2) {
                                            if (i == 3 && !TextUtils.isEmpty(strA3)) {
                                                launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(strA3);
                                                if (launchIntentForPackage != null) {
                                                    intent = launchIntentForPackage;
                                                }
                                                if (a.d() && launchIntentForPackage != null && (strA3.equalsIgnoreCase(str19) || strA3.equalsIgnoreCase(str8) || strA3.equalsIgnoreCase(str6) || strA3.equalsIgnoreCase(str4) || strA3.equalsIgnoreCase("com.jamdeo.tv.vod") || strA3.equalsIgnoreCase(str7))) {
                                                    intent.putExtra(str23, i);
                                                }
                                            }
                                        } else {
                                            intent.putExtras(bundle);
                                            if (!z5) {
                                                context.sendBroadcast(intent);
                                                return;
                                            }
                                        }
                                        str10 = strA3;
                                        str11 = string;
                                    }
                                }
                            }
                        }
                        str3 = "com.ju.old.library.ui.thirdpartner.ThirdPartnerActivity";
                        intent.setClassName("com.jamdeo.tv.vod", str3);
                        intent.setData(null);
                        i = 1;
                        if (i == 1) {
                            intent.putExtras(bundle);
                        } else if (i != 2) {
                            intent.putExtras(bundle);
                            if (!z5) {
                                context.sendBroadcast(intent);
                                return;
                            }
                        } else if (i == 3) {
                            launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(strA3);
                            if (launchIntentForPackage != null) {
                                intent = launchIntentForPackage;
                            }
                            if (a.d()) {
                                intent.putExtra(str23, i);
                            }
                        }
                        str10 = strA3;
                        str11 = string;
                    }
                    str3 = "com.ju.old.library.ui.thirdpartner.ThirdPartnerActivity";
                    i = i3;
                    if (i == 1) {
                        intent.putExtras(bundle);
                    } else if (i != 2) {
                        intent.putExtras(bundle);
                        if (!z5) {
                            context.sendBroadcast(intent);
                            return;
                        }
                    } else if (i == 3) {
                        launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(strA3);
                        if (launchIntentForPackage != null) {
                            intent = launchIntentForPackage;
                        }
                        if (a.d()) {
                            intent.putExtra(str23, i);
                        }
                    }
                    str10 = strA3;
                    str11 = string;
                } else {
                    str3 = "com.ju.old.library.ui.thirdpartner.ThirdPartnerActivity";
                    str4 = "com.hisense.store.tv";
                    str5 = RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME;
                    str6 = "com.hisense.tv.gamecenter";
                    str7 = "com.hisense.hitv.hicloud.account";
                    str8 = "com.hisense.hicloud.edca";
                    if (TextUtils.isEmpty(str20)) {
                        str9 = str20;
                    } else {
                        str9 = str20;
                        Intent launchIntentForPackage2 = context.getPackageManager().getLaunchIntentForPackage(str9);
                        if (launchIntentForPackage2 != null) {
                            intent = launchIntentForPackage2;
                        }
                        if (a.d() && launchIntentForPackage2 != null && (str9.equalsIgnoreCase("com.hisense.hitv.shopping") || str9.equalsIgnoreCase(str8) || str9.equalsIgnoreCase(str6) || str9.equalsIgnoreCase(str4) || str9.equalsIgnoreCase("com.jamdeo.tv.vod") || str9.equalsIgnoreCase(str7))) {
                            intent.putExtra("startupType", 0);
                        }
                    }
                    str10 = str9;
                    str11 = null;
                }
                if (z) {
                    a(context, intent, str10, str11, z3, z4);
                    return;
                }
                intent.addFlags(67108864);
                if (!"SuperApp".equals(str11) && !"SuperApp_v4".equals(str11)) {
                    if (!checkPackageInfo(context, str10, z3, z4)) {
                        intent = new Intent("android.intent.action.VIEW");
                        intent.addFlags(MessageSchema.REQUIRED_MASK);
                        intent.addFlags(67108864);
                        intent.setData(Uri.parse("himarket://details?id=" + str10 + "&isAutoDownload=1"));
                    }
                } else {
                    String str24 = str8;
                    if (!str10.equalsIgnoreCase(str19) && !str10.equalsIgnoreCase(str24) && !str10.equalsIgnoreCase(str6) && !str10.equalsIgnoreCase(str4) && !str10.equalsIgnoreCase(r2) && !str10.equalsIgnoreCase(str7)) {
                        if (!checkPackageInfo(context, str10, z3, z4)) {
                            int intExtra = intent.getIntExtra("downType", 0);
                            intent.addFlags(67108864);
                            if (intExtra == 1) {
                                intent.setClassName("com.jamdeo.tv.vod", "com.hisense.base.activity.AppLoadingActivity");
                            } else {
                                intent.setClassName(r2, str3);
                            }
                            intent.putExtra(str5, str10);
                            intent.setData(null);
                        } else if (MtkTvRatingConvert2Goo.SUB_RATING_STR_V.equalsIgnoreCase(str2) && "1".equals(DeviceConfig.getProp("ro.his.rotation_support"))) {
                            a(context, intent, str10, str11, z3, z4);
                        }
                    }
                }
                try {
                    context.startActivity(intent);
                    return;
                } catch (Exception unused) {
                }
            }
            showToast(context, f2084a);
            return;
        }
        StartupInfo startupInfo = new StartupInfo();
        startupInfo.setStartupType(iA);
        startupInfo.setStartupCommand(dVar.a("startupCommand", ""));
        startupInfo.setStartupUrl(dVar.a("startupUrl", ""));
        startupInfo.setPackageName(dVar.a(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME, ""));
        startApp(context, startupInfo, z3, z4);
    }

    public static void backSilo(Context context, String str) {
        String str2;
        if (!a.a() && !a.b() && !a.c() && !a.g()) {
            if (a.i()) {
                Log.d("StartAppUtil", "afterVidda4");
                Intent intent = new Intent();
                intent.setAction("com.jamdeo.tv.policy.SILO_SWITCH_EVENT");
                intent.putExtra("silo_name", str);
                context.sendBroadcast(intent);
                return;
            }
            showToast(context, f2085b);
            return;
        }
        Log.d("StartAppUtil", "tv is vidaa 123");
        Log.d("StartAppUtil", "packageName is " + str);
        if (!TextUtils.isEmpty(str)) {
            if (str.equalsIgnoreCase("com.jamdeo.tv.vod")) {
                str2 = new String("OnDemand");
            } else if (str.equalsIgnoreCase("com.hisense.tv.gamecenter")) {
                if (!a.a() && !a.b()) {
                    str2 = new String("GameCenter");
                } else {
                    str2 = new String("AppCenter");
                }
            } else if (str.equalsIgnoreCase("com.hisense.hicloud.edca")) {
                if (a.d()) {
                    str2 = new String("Education");
                } else {
                    str2 = new String("AppCenter");
                }
            } else if (str.equalsIgnoreCase("com.hisense.hitv.shopping") && a.d()) {
                str2 = new String("Shopping");
            } else {
                str2 = new String("AppCenter");
            }
        } else {
            str2 = new String("AppCenter");
        }
        if ((a.c() || a.g()) && "com.hisense.tv.gamecenter".equals(context.getPackageName()) && !TextUtils.isEmpty(str)) {
            try {
                Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://com.jamdeo.tv.provider.hostedapps/hosted_apps"), new String[]{"silo"}, "package=?", new String[]{str}, null);
                if (cursorQuery != null && !cursorQuery.isClosed() && cursorQuery.moveToFirst()) {
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex("silo"));
                    try {
                        cursorQuery.close();
                        str2 = string;
                    } catch (Exception e2) {
                        e = e2;
                        str2 = string;
                        e.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
        Log.d("StartAppUtil", "silo is " + str2);
        Intent intent2 = new Intent();
        intent2.setAction("com.jamdeo.tv.policy.SILO_SWITCH_EVENT");
        intent2.putExtra("jamdeo_silo_name", str2);
        intent2.putExtra("silo_name", str2);
        context.sendBroadcast(intent2);
    }

    public static void startApp(Context context, StartupInfo startupInfo, boolean z, boolean z2) {
        if (startupInfo != null) {
            Intent intent = new Intent();
            try {
                e.b.d dVar = new e.b.d(startupInfo.getStartupUrl());
                int startupType = startupInfo.getStartupType();
                if (startupType == 1) {
                    try {
                        if (!TextUtils.isEmpty(startupInfo.getPackageName())) {
                            if (!TextUtils.isEmpty(startupInfo.getStartupCommand())) {
                                intent.setComponent(new ComponentName(startupInfo.getPackageName(), startupInfo.getStartupCommand()));
                            } else {
                                intent = context.getPackageManager().getLaunchIntentForPackage(startupInfo.getPackageName());
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else if (startupType == 2) {
                    intent = null;
                    Intent intent2 = new Intent();
                    try {
                        intent2.setAction(startupInfo.getStartupCommand());
                        Bundle bundle = new Bundle();
                        Iterator<String> itA = dVar.a();
                        while (itA != null && itA.hasNext()) {
                            String next = itA.next();
                            bundle.putString(next, dVar.h(next));
                        }
                        intent2.putExtras(bundle);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    context.sendBroadcast(intent2);
                } else if (startupType == 3) {
                    intent.setAction(startupInfo.getStartupCommand());
                    if (!TextUtils.isEmpty(startupInfo.getStartupUrl())) {
                        intent.setData(Uri.parse(startupInfo.getStartupUrl()));
                    }
                }
                a(context, intent, startupInfo.getPackageName(), null, z, z2);
                return;
            } catch (Exception e4) {
                e4.printStackTrace();
                showToast(context, f2084a);
                return;
            }
        }
        showToast(context, f2084a);
    }
}
