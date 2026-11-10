package org.slf4j.helpers;

import org.slf4j.Logger;

/* JADX INFO: loaded from: classes.dex */
public abstract class MarkerIgnoringBase extends NamedLoggerBase implements Logger {
    private static final long serialVersionUID = 9044267456635152283L;

    public String toString() {
        return getClass().getName() + "(" + getName() + ")";
    }
}
