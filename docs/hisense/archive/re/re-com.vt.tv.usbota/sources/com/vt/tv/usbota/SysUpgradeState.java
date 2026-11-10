package com.vt.tv.usbota;

/* JADX INFO: loaded from: classes.dex */
public enum SysUpgradeState {
    IDLE,
    CHECKING,
    CHECK_CANCELING,
    CHECK_FAILED,
    FOUND_NEW_VERSION,
    NOT_FOUND_NEW_VERSION,
    UPGRADE_CANCEL,
    VERIFYING,
    VERIFY_FAILED,
    VERIFIED,
    INSTALLING,
    INSTALLED,
    INSTALE_FAILED,
    REBOOTING,
    WAIT_REBOOT,
    REBOOT_FAILED
}
