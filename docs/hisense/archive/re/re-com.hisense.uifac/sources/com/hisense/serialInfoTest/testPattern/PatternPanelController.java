package com.hisense.serialInfoTest.testPattern;

import android.content.Context;
import com.hisense.serialInfoTest.util.Logger;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class PatternPanelController {
    public static PatternPanelController mPatternPanelController;
    private final ArrayList<Listerner> mListeners = new ArrayList<>();

    public interface Listerner {
        void onHidePanel();
    }

    public PatternPanelController(Context context) {
    }

    public static PatternPanelController getInstance(Context context) {
        PatternPanelController patternPanelController = mPatternPanelController;
        if (patternPanelController != null) {
            return patternPanelController;
        }
        PatternPanelController patternPanelController2 = new PatternPanelController(context);
        mPatternPanelController = patternPanelController2;
        return patternPanelController2;
    }

    public void register(Listerner listerner) {
        Logger.d("enter register");
        if (this.mListeners.contains(listerner)) {
            return;
        }
        this.mListeners.add(listerner);
    }

    public void unregister(Listerner listerner) {
        Logger.d("enter unregister");
        if (this.mListeners.contains(listerner)) {
            this.mListeners.remove(listerner);
        }
    }

    public void onHidePanels() {
        Logger.d("enter onHidePanels");
        Iterator<Listerner> it = this.mListeners.iterator();
        Logger.d("onHidePanels length" + this.mListeners.size());
        while (it.hasNext()) {
            Logger.d("onHidePanels ");
            it.next().onHidePanel();
        }
    }
}
