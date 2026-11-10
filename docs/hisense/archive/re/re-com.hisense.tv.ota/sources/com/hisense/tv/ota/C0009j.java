package com.hisense.tv.ota;

/* JADX INFO: renamed from: com.hisense.tv.ota.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class C0009j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f40a;

    static {
        int[] iArr = new int[SysUpgradeState.values().length];
        f40a = iArr;
        try {
            iArr[SysUpgradeState.FOUND_NEW_VERSION.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f40a[SysUpgradeState.VERIFIED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f40a[SysUpgradeState.INSTALLED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f40a[SysUpgradeState.WAIT_REBOOT.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f40a[SysUpgradeState.IDLE.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            f40a[SysUpgradeState.CHECKING.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            f40a[SysUpgradeState.CHECK_CANCELING.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            f40a[SysUpgradeState.CHECK_FAILED.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            f40a[SysUpgradeState.NOT_FOUND_NEW_VERSION.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            f40a[SysUpgradeState.UPGRADE_CANCEL.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            f40a[SysUpgradeState.DOWNLOADING.ordinal()] = 11;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            f40a[SysUpgradeState.DOWNLOAD_CANCELING.ordinal()] = 12;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            f40a[SysUpgradeState.DOWNLOAD_FAILED.ordinal()] = 13;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            f40a[SysUpgradeState.DOWNLOADED.ordinal()] = 14;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            f40a[SysUpgradeState.VERIFYING.ordinal()] = 15;
        } catch (NoSuchFieldError unused15) {
        }
        try {
            f40a[SysUpgradeState.VERIFY_FAILED.ordinal()] = 16;
        } catch (NoSuchFieldError unused16) {
        }
        try {
            f40a[SysUpgradeState.INSTALE_FAILED.ordinal()] = 17;
        } catch (NoSuchFieldError unused17) {
        }
        try {
            f40a[SysUpgradeState.INSTALLING.ordinal()] = 18;
        } catch (NoSuchFieldError unused18) {
        }
        try {
            f40a[SysUpgradeState.REBOOTING.ordinal()] = 19;
        } catch (NoSuchFieldError unused19) {
        }
        try {
            f40a[SysUpgradeState.WAITING_NETWORK.ordinal()] = 20;
        } catch (NoSuchFieldError unused20) {
        }
        try {
            f40a[SysUpgradeState.REBOOT_FAILED.ordinal()] = 21;
        } catch (NoSuchFieldError unused21) {
        }
    }
}
