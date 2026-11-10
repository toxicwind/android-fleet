package com.hisense.uifac.menu.util;

import android.content.Context;
import com.hisense.tvcm.FacPropertyDemo;

/* JADX INFO: loaded from: classes.dex */
public class MenuLogoHelper {
    private MenuConfigManager mMenuConfig;

    public MenuLogoHelper(Context context) {
        this.mMenuConfig = MenuConfigManager.getInstance(context);
    }

    public int currentLogoTypeInSAMarket() {
        String logoName = this.mMenuConfig.getLogoName();
        int i = 3;
        if ("his".equalsIgnoreCase(logoName)) {
            return 3;
        }
        for (FacPropertyDemo.LogoForBGH_SABrandIsolation logoForBGH_SABrandIsolation : FacPropertyDemo.LogoForBGH_SABrandIsolation.values()) {
            if (logoName.equalsIgnoreCase(logoForBGH_SABrandIsolation.getName())) {
                i = 0;
            }
        }
        for (FacPropertyDemo.LogoForDEVANT_SABrandIsolation logoForDEVANT_SABrandIsolation : FacPropertyDemo.LogoForDEVANT_SABrandIsolation.values()) {
            if (logoName.equalsIgnoreCase(logoForDEVANT_SABrandIsolation.getName())) {
                i = 1;
            }
        }
        for (FacPropertyDemo.LogoForNEWSAN_SABrandIsolation logoForNEWSAN_SABrandIsolation : FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.values()) {
            if (logoName.equalsIgnoreCase(logoForNEWSAN_SABrandIsolation.getName())) {
                i = 2;
            }
        }
        return i;
    }
}
