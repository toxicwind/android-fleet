package com.hisense.uifac.menu.commonview;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.MenuMain;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class UserCurrProgramInforDialog extends Dialog {
    private EditText[] mArrType;
    private Button mReturnButton;
    private Button mSaveButton;

    public UserCurrProgramInforDialog(MenuMain menuMain) {
        super(menuMain);
        this.mArrType = new EditText[4];
        this.mSaveButton = null;
        this.mReturnButton = null;
    }

    public boolean loadDataForUpdateUI() {
        String[] strArrSplit = "".split(",");
        ArrayList arrayList = new ArrayList();
        for (String str : strArrSplit) {
            try {
                arrayList.add(Integer.valueOf(Integer.parseInt(str)));
            } catch (Exception unused) {
                System.out.println("zjl , Parser AudioVideoInfo Para error!!!");
            }
        }
        for (short s = 0; s < 4; s = (short) (s + 1)) {
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
        setContentView(R.layout.user_current_program_info);
        Window window = getWindow();
        Display defaultDisplay = window.getWindowManager().getDefaultDisplay();
        window.setLayout((int) (((double) defaultDisplay.getWidth()) * 0.3d), (int) (((double) defaultDisplay.getHeight()) * 0.3d));
        window.setGravity(17);
        window.setAttributes(window.getAttributes());
        findViews();
        registerListeners();
        loadDataForUpdateUI();
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
        this.mArrType[0] = (EditText) findViewById(R.id.audiotype);
        this.mArrType[1] = (EditText) findViewById(R.id.audiopid);
        this.mArrType[2] = (EditText) findViewById(R.id.videotype);
        this.mArrType[3] = (EditText) findViewById(R.id.videopid);
        this.mSaveButton = (Button) findViewById(R.id.prog_infor_save_button);
        Button button = (Button) findViewById(R.id.prog_infor_return_button);
        this.mReturnButton = button;
        button.requestFocus();
    }

    private void registerListeners() {
        this.mReturnButton.setOnClickListener(new View.OnClickListener() { // from class: com.hisense.uifac.menu.commonview.UserCurrProgramInforDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UserCurrProgramInforDialog.this.dismiss();
            }
        });
        this.mSaveButton.setOnClickListener(new View.OnClickListener() { // from class: com.hisense.uifac.menu.commonview.UserCurrProgramInforDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (UserCurrProgramInforDialog.this.collectUIDataForSave()) {
                    UserCurrProgramInforDialog.this.dismiss();
                }
            }
        });
    }

    boolean collectUIDataForSave() {
        String strTrim = this.mArrType[0].getText().toString().trim();
        String strTrim2 = this.mArrType[1].getText().toString().trim();
        String strTrim3 = this.mArrType[2].getText().toString().trim();
        String strTrim4 = this.mArrType[3].getText().toString().trim();
        try {
            if (strTrim.equalsIgnoreCase("") || strTrim2.equalsIgnoreCase("") || strTrim3.equalsIgnoreCase("") || strTrim4.equalsIgnoreCase("")) {
                throw new Exception();
            }
            return true;
        } catch (Exception unused) {
            Toast toastMakeText = Toast.makeText(getContext(), R.string.str_userprogramtype_check_toast, 1);
            toastMakeText.setGravity(17, 0, 0);
            toastMakeText.show();
            return false;
        }
    }
}
