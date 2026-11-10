package com.hisense.uifac.menu.commonview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.uifac.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class UserProgramTypeDialog extends Dialog {
    private EditText[] mArrType;
    private Context mContext;
    private Button mReturnButton;
    private Button mSaveButton;
    private int[] tempTypeValue;
    String userProgramTypeValue;

    public UserProgramTypeDialog(Context context) {
        super(context);
        this.mArrType = new EditText[12];
        this.tempTypeValue = new int[13];
        this.userProgramTypeValue = "0,0,0,0,0,0,0,0,0,0,0,0";
        this.mContext = context;
    }

    public boolean loadDataForUpdateUI() {
        String[] strArrSplit = this.userProgramTypeValue.split(",");
        ArrayList arrayList = new ArrayList();
        for (String str : strArrSplit) {
            try {
                arrayList.add(Integer.valueOf(Integer.parseInt(str)));
            } catch (Exception unused) {
                System.out.println("zjl , Parser UserProgramTypeValue Para error!!!");
            }
        }
        for (short s = 0; s < 12; s = (short) (s + 1)) {
            try {
                this.mArrType[s].setText(Integer.toHexString(((Integer) arrayList.get(s)).intValue()));
            } catch (Exception e) {
                this.mArrType[s].setText("0");
                System.out.println("zjl , Parse Dec to Hex failed!");
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 219) {
            for (EditText editText : this.mArrType) {
                if (editText.isFocused()) {
                    deleteChar(editText);
                }
            }
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.userprogramtype);
        Window window = getWindow();
        Display defaultDisplay = window.getWindowManager().getDefaultDisplay();
        window.setLayout((int) (((double) defaultDisplay.getWidth()) * 0.4d), (int) (((double) defaultDisplay.getHeight()) * 0.4d));
        window.setGravity(17);
        window.setAttributes(window.getAttributes());
        findViews();
        registerListeners();
        loadDataForUpdateUI();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean collectUIDataForUpdateDataSet() {
        int[] iArr = new int[13];
        this.tempTypeValue = iArr;
        iArr[0] = 2;
        for (short s = 0; s < 12; s = (short) (s + 1)) {
            String string = this.mArrType[s].getText().toString();
            if (string.equals(null) || string.equals(" ")) {
                this.tempTypeValue[s + 1] = 0;
            } else {
                try {
                    this.tempTypeValue[s + 1] = Integer.valueOf(string, 16).shortValue();
                } catch (Exception unused) {
                    return false;
                }
            }
        }
        return true;
    }

    private String delete(int i, String str) {
        if (i == 0) {
            return str.substring(0);
        }
        if (i == str.length()) {
            return str.substring(0, i - 1);
        }
        if (i <= 0 || i >= str.length()) {
            return str;
        }
        return str.substring(0, i - 1) + str.substring(i);
    }

    private void deleteChar(EditText editText) {
        int selectionStart = editText.getSelectionStart();
        editText.setText(delete(selectionStart, editText.getText().toString()));
        int i = selectionStart - 1;
        if (i <= 0) {
            i = 0;
        }
        editText.setSelection(i);
    }

    private void findViews() {
        this.mArrType[0] = (EditText) findViewById(R.id.type1);
        this.mArrType[1] = (EditText) findViewById(R.id.type2);
        this.mArrType[2] = (EditText) findViewById(R.id.type3);
        this.mArrType[3] = (EditText) findViewById(R.id.type4);
        this.mArrType[4] = (EditText) findViewById(R.id.type5);
        this.mArrType[5] = (EditText) findViewById(R.id.type6);
        this.mArrType[6] = (EditText) findViewById(R.id.type7);
        this.mArrType[7] = (EditText) findViewById(R.id.type8);
        this.mArrType[8] = (EditText) findViewById(R.id.type9);
        this.mArrType[9] = (EditText) findViewById(R.id.type10);
        this.mArrType[10] = (EditText) findViewById(R.id.type11);
        this.mArrType[11] = (EditText) findViewById(R.id.type12);
        this.mSaveButton = (Button) findViewById(R.id.save_button);
        Button button = (Button) findViewById(R.id.return_button);
        this.mReturnButton = button;
        button.requestFocus();
    }

    private void registerListeners() {
        this.mReturnButton.setOnClickListener(new View.OnClickListener() { // from class: com.hisense.uifac.menu.commonview.UserProgramTypeDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UserProgramTypeDialog.this.dismiss();
            }
        });
        this.mSaveButton.setOnClickListener(new View.OnClickListener() { // from class: com.hisense.uifac.menu.commonview.UserProgramTypeDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (UserProgramTypeDialog.this.collectUIDataForUpdateDataSet()) {
                    FacFunction_Adapter.getInstance(UserProgramTypeDialog.this.mContext).setSvcType(UserProgramTypeDialog.this.tempTypeValue.length, UserProgramTypeDialog.this.tempTypeValue);
                    UserProgramTypeDialog.this.dismiss();
                } else {
                    Toast toastMakeText = Toast.makeText(UserProgramTypeDialog.this.getContext(), R.string.str_userprogramtype_check_toast, 1);
                    toastMakeText.setGravity(17, 0, 0);
                    toastMakeText.show();
                }
            }
        });
    }
}
