package com.hisense.uifac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import com.hisense.tvcm.FacFunction;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacPropertyDemo;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;

/* JADX INFO: loaded from: classes.dex */
public class LuminanceTestActivity extends Activity {
    private String TAG = "LuminanceTestActivity";
    private FacFunction mFacFunction_Adapter = null;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Log.i(this.TAG, "onCreate");
        setContentView(R.layout.activity_luninance_test);
        getWindow().addFlags(128);
        FacFunction_Adapter facFunction_Adapter = FacFunction_Adapter.getInstance(getApplicationContext());
        this.mFacFunction_Adapter = facFunction_Adapter;
        facFunction_Adapter.setPictureMode(FacPropertyDemo.PictureMode.STANDARD);
        super.onCreate(bundle);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Log.i(this.TAG, "OnkeyDown" + i);
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Log.i(this.TAG, "OnkeyUp" + i);
        if (i == 166 || i == 167 || i == 645 || i == 646 || i == 4199 || i == 4200) {
            return true;
        }
        switch (i) {
            case 4:
                Log.i(this.TAG, "keycode KEYCODE_BACK pressed");
                openFacRootMenu();
                break;
            case 66:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_CAM_CARD_MISSING /* 82 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_SERVICE_PIN_BLOCKED /* 85 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF /* 140 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_AUDIO_STREAM_CTR_NOT_SUPPORT /* 209 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONN_PMT_UPDATED /* 211 */:
            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FILE_OPER_ERROR /* 213 */:
            case 230:
            case 4212:
            case 4213:
            case 4214:
            case 4215:
            case 4216:
            case 4217:
            case 4218:
            case 4219:
            case 4220:
            case 4221:
            case 4222:
            case 4223:
            case 4224:
            case 4225:
            case 4226:
            case 4227:
            case 4228:
            case 4229:
            case 4230:
            case 4231:
            case 4232:
            case 4233:
            case 4234:
            case 4235:
            case 4236:
            case 4237:
            case 4238:
            case 4239:
            case 4240:
            case 4241:
            case 4242:
            case 4243:
            case 4244:
            case 4245:
            case 4246:
            case 4247:
            case 4248:
            case 4249:
            case 4250:
            case 4251:
            case 4252:
            case 4293:
            case 4298:
            case 4299:
            case 4300:
            case 4301:
            case 4302:
            case 4303:
            case 4304:
            case 4305:
            case 4306:
            case 4357:
            case 4362:
            case 4363:
            case 4364:
            case 4365:
            case 4366:
            case 4367:
            case 4368:
            case 4369:
            case 4370:
            case 4371:
            case 4372:
            case 4373:
            case 4374:
            case 4375:
            case 4376:
            case 4377:
            case 4383:
            case 4384:
            case 4385:
            case 4387:
            case 4388:
            case 4390:
            case 4391:
            case 4392:
            case 4393:
            case 4394:
            case 4395:
            case 4396:
            case 4397:
            case 5100:
            case 5101:
            case 5102:
            case 5103:
            case 5104:
            case 5105:
            case 5106:
            case 5107:
            case 5108:
            case 5109:
            case 5110:
            case 5111:
            case 5112:
            case 112233:
            case 112234:
            case 112235:
            case 112236:
            case 112237:
            case 112238:
                return true;
            default:
                switch (i) {
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        return true;
                    default:
                        switch (i) {
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                                return true;
                            default:
                                switch (i) {
                                    case 29:
                                    case 30:
                                    case 31:
                                    case 32:
                                    case 33:
                                    case 34:
                                    case 35:
                                    case 36:
                                    case 37:
                                    case 38:
                                    case 39:
                                    case 40:
                                    case 41:
                                    case 42:
                                    case 43:
                                    case 44:
                                    case 45:
                                    case 46:
                                    case 47:
                                    case 48:
                                    case 49:
                                    case 50:
                                    case 51:
                                    case 52:
                                    case 53:
                                    case 54:
                                        return true;
                                    default:
                                        switch (i) {
                                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_OFF /* 144 */:
                                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STARTED /* 145 */:
                                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STOPPED /* 146 */:
                                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PAUSED /* 147 */:
                                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_SOURCE_READY /* 148 */:
                                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_ES_LIST /* 149 */:
                                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_STOP /* 150 */:
                                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PIN_CAP_CHANGE /* 151 */:
                                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_ES_AUTH_UPDATED /* 152 */:
                                            case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_BOUND /* 153 */:
                                                return true;
                                            default:
                                                switch (i) {
                                                    case 4204:
                                                    case 4205:
                                                    case 4206:
                                                    case 4207:
                                                    case 4208:
                                                    case 4209:
                                                    case 4210:
                                                        return true;
                                                    default:
                                                        switch (i) {
                                                            case 4278:
                                                            case 4279:
                                                            case 4280:
                                                            case 4281:
                                                            case 4282:
                                                            case 4283:
                                                            case 4284:
                                                            case 4285:
                                                            case 4286:
                                                            case 4287:
                                                            case 4288:
                                                            case 4289:
                                                            case 4290:
                                                                return true;
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
        return super.onKeyUp(i, keyEvent);
    }

    private void openFacRootMenu() {
        startActivity(new Intent(this, (Class<?>) FacRootMenu.class));
    }
}
