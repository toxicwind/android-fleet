package com.hisense.tvcm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.hisense.uifac.R;

/* JADX INFO: loaded from: classes.dex */
public class CamDisplayManager implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener, DialogInterface.OnCancelListener, DialogInterface.OnKeyListener {
    private static CamDisplayManager mCamDisplayManager;
    private static Context mContext;
    private static Handler sHandler;
    private FacFunction mFacFunction;
    private String[] mMenuData = null;
    private boolean mIsInCamMenu = false;
    private AlertDialog mDialog = null;
    private AlertDialog mEnq = null;

    public String getCIInfo() {
        return null;
    }

    public CamDisplayManager() {
        this.mFacFunction = null;
        Log.d("Fac_CamDisplayManager", "Create CamDisplayManager");
        new MenuDataAdapter();
        this.mFacFunction = FacFunction_Adapter.getInstance(mContext.getApplicationContext());
        Handler handler = new Handler() { // from class: com.hisense.tvcm.CamDisplayManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Log.d("Fac_CamDisplayManager", "handleMessage");
                CamDisplayManager camDisplayManager = (CamDisplayManager) message.obj;
                int i = message.what;
                if (i == 121) {
                    Toast.makeText(camDisplayManager.getContext(), R.string.str_ci_inserted, 0).show();
                    return;
                }
                if (i == 122) {
                    Toast.makeText(camDisplayManager.getContext(), R.string.str_ci_removed, 0).show();
                    return;
                }
                if (i != 125) {
                    if (i != 131) {
                        return;
                    }
                    camDisplayManager.exitCamMenu();
                    return;
                }
                Log.d("Fac_CamDisplayManager", "CAM_MESSAGE_SHOW_MENU arg1" + message.arg1);
                if (!CamDisplayManager.this.mIsInCamMenu && message.arg1 == 1) {
                    CamDisplayManager.this.mIsInCamMenu = true;
                }
                if (CamDisplayManager.this.mIsInCamMenu) {
                    Log.i("Fac_CamDisplayManager", "CAM msg show menu");
                    camDisplayManager.displayMenu();
                } else {
                    Log.i("Fac_CamDisplayManager", "CAM msg show notification");
                }
            }
        };
        sHandler = handler;
        initCamDisplayHandler(handler);
        this.mFacFunction.registerCamListeners();
    }

    private void initCamDisplayHandler(Handler handler) {
        this.mFacFunction.initCamDisplayHandler(handler, this);
    }

    public static CamDisplayManager getInstance(Context context) {
        if (mCamDisplayManager == null) {
            mContext = context;
            mCamDisplayManager = new CamDisplayManager();
        }
        return mCamDisplayManager;
    }

    public boolean enterCamMenu(Context context, boolean z) {
        this.mFacFunction.closeCurrentMMI();
        if (!this.mFacFunction.isSlotActive()) {
            Log.e("Fac_CamDisplayManager", "There is no available CA and CI Card");
            if (z) {
                return false;
            }
            Toast.makeText(mContext, R.string.str_no_card, 1).show();
            return false;
        }
        Log.d("Fac_CamDisplayManager", "enter Cam menu");
        this.mIsInCamMenu = true;
        this.mFacFunction.enterCAMMainMenu();
        return true;
    }

    public void exitCamMenu() {
        Log.d("Fac_CamDisplayManager", "exit Cam menu");
        this.mFacFunction.closeCurrentMMI();
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mDialog = null;
        }
        AlertDialog alertDialog2 = this.mEnq;
        if (alertDialog2 != null) {
            alertDialog2.dismiss();
            this.mEnq = null;
        }
        this.mIsInCamMenu = false;
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (i != 4357) {
            return false;
        }
        Log.i("Fac_CamDisplayManager", "receive EXIT key");
        exitCamMenu();
        return true;
    }

    public Context getContext() {
        return mContext;
    }

    public void displayMenu() {
        Log.i("Fac_CamDisplayManager", "enter displayMenu**********");
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Log.d("Fac_CamDisplayManager", "onClick which: " + i);
        this.mFacFunction.selectMenuItem(i);
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        Log.d("Fac_CamDisplayManager", "the dialog on Cancel");
        this.mFacFunction.cancelCurrMenu();
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        Log.d("Fac_CamDisplayManager", "the dialog on Dismiss");
        this.mDialog = null;
    }

    private class MenuDataAdapter extends BaseAdapter {
        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        private MenuDataAdapter() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (CamDisplayManager.this.mMenuData != null) {
                return CamDisplayManager.this.mMenuData.length;
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            if (CamDisplayManager.this.mMenuData == null) {
                return null;
            }
            return CamDisplayManager.this.mMenuData[i];
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ((LayoutInflater) CamDisplayManager.mContext.getSystemService("layout_inflater")).inflate(android.R.layout.select_dialog_item, (ViewGroup) null);
            }
            TextView textView = (TextView) view;
            textView.setText(CamDisplayManager.this.mMenuData[i]);
            textView.setBackgroundColor(-12303292);
            return view;
        }
    }
}
