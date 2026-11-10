package com.hisense.tv.smartsense.module.firebase.service;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: loaded from: classes2.dex */
public class FirebaseStorageService extends IntentService {
    public FirebaseStorageService() {
        super("FirebaseStorageService");
    }

    @Override // android.app.IntentService
    public void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("PARMS_PATH");
        String stringExtra2 = intent.getStringExtra("PARMS_CHILD");
        final String stringExtra3 = intent.getStringExtra("PARMS_FILENAME");
        String stringExtra4 = intent.getStringExtra("PARMS_FILEPATH");
        final boolean booleanExtra = intent.getBooleanExtra("PARMS_DELETE", false);
        Log.d("FirebaseStorageService", "filepath=" + stringExtra4);
        if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2) || TextUtils.isEmpty(stringExtra3) || TextUtils.isEmpty(stringExtra4)) {
            return;
        }
        final File file = new File(stringExtra4);
        if (!file.exists()) {
            Log.i("FirebaseStorageService", "File not exist!");
            return;
        }
        try {
            FirebaseStorage.getInstance().getReference().child(stringExtra).child(stringExtra2 + "/" + stringExtra3).putStream(new FileInputStream(file)).addOnSuccessListener(new OnSuccessListener() { // from class: b.f.b.b.b.b.e.b
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    e.a(stringExtra3, booleanExtra, file, (UploadTask.TaskSnapshot) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: b.f.b.b.b.b.e.c
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    b.a.a.a.a.c("upload failed,filename =", stringExtra3, "FirebaseStorageUtils");
                }
            });
        } catch (Exception e2) {
            Log.e("FirebaseStorageService", e2.toString());
        }
    }
}
