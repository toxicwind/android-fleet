package com.vt.tv.usbota;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class f {
    static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[SysUpgradeState.values().length];
        a = iArr;
        try {
            iArr[SysUpgradeState.IDLE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[SysUpgradeState.CHECKING.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[SysUpgradeState.CHECK_CANCELING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[SysUpgradeState.CHECK_FAILED.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            a[SysUpgradeState.FOUND_NEW_VERSION.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            a[SysUpgradeState.NOT_FOUND_NEW_VERSION.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            a[SysUpgradeState.VERIFYING.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            a[SysUpgradeState.VERIFY_FAILED.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            a[SysUpgradeState.VERIFIED.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            a[SysUpgradeState.INSTALLING.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            a[SysUpgradeState.INSTALLED.ordinal()] = 11;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            a[SysUpgradeState.INSTALE_FAILED.ordinal()] = 12;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            a[SysUpgradeState.REBOOTING.ordinal()] = 13;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            a[SysUpgradeState.WAIT_REBOOT.ordinal()] = 14;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            a[SysUpgradeState.REBOOT_FAILED.ordinal()] = 15;
        } catch (NoSuchFieldError unused15) {
        }
    }
}
