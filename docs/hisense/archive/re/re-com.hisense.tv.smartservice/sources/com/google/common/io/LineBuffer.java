package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public abstract class LineBuffer {
    public StringBuilder line = new StringBuilder();
    public boolean sawReturn;

    @CanIgnoreReturnValue
    private boolean finishLine(boolean z) {
        String str;
        if (this.sawReturn) {
            str = z ? "\r\n" : "\r";
        } else {
            str = z ? "\n" : "";
        }
        handleLine(this.line.toString(), str);
        this.line = new StringBuilder();
        this.sawReturn = false;
        return z;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0019  */
    public void add(char[] cArr, int i, int i2) {
        int i3;
        if (!this.sawReturn || i2 <= 0) {
            i3 = i;
        } else {
            boolean z = cArr[i] == '\n';
            finishLine(z);
            if (z) {
                i3 = i + 1;
            } else {
                i3 = i;
            }
        }
        int i4 = i + i2;
        int i5 = i3;
        while (i3 < i4) {
            char c2 = cArr[i3];
            if (c2 != '\n') {
                if (c2 == '\r') {
                    this.line.append(cArr, i5, i3 - i5);
                    this.sawReturn = true;
                    int i6 = i3 + 1;
                    if (i6 < i4) {
                        boolean z2 = cArr[i6] == '\n';
                        finishLine(z2);
                        if (z2) {
                            i3 = i6;
                        }
                    }
                }
                i3++;
            } else {
                this.line.append(cArr, i5, i3 - i5);
                finishLine(true);
            }
            i5 = i3 + 1;
            i3++;
        }
        this.line.append(cArr, i5, i4 - i5);
    }

    public void finish() {
        if (this.sawReturn || this.line.length() > 0) {
            finishLine(false);
        }
    }

    public abstract void handleLine(String str, String str2);
}
