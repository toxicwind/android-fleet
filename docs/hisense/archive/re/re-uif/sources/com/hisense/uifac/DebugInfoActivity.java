package com.hisense.uifac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import java.io.FileReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class DebugInfoActivity extends Activity {
    private String mFileName = null;
    private TextView mTextView;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_debug_info);
        getWindow().addFlags(128);
        Intent intent = getIntent();
        if (intent != null) {
            this.mFileName = intent.getStringExtra("fileName");
        }
        TextView textView = (TextView) findViewById(R.id.debug_info);
        this.mTextView = textView;
        String str = this.mFileName;
        if (str != null) {
            textView.setText(getFileContent(str));
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 || i == 82) {
            finish();
        }
        finish();
        return true;
    }

    public String getFileContent(String str) {
        String str2 = null;
        try {
            FileReader fileReader = new FileReader(getFilesDir().getPath() + "/" + str);
            char[] cArr = new char[1024];
            while (true) {
                int i = fileReader.read(cArr);
                if (i == -1) {
                    break;
                }
                str2 = str2 + new String(cArr, 0, i);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Fac_DebugInfoActivity", "read file wrong");
        }
        return str2;
    }
}
