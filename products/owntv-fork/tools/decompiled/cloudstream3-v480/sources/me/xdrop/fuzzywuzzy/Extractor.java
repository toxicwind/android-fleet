package me.xdrop.fuzzywuzzy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import me.xdrop.fuzzywuzzy.algorithms.Utils;
import me.xdrop.fuzzywuzzy.model.BoundExtractedResult;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

/* JADX INFO: loaded from: classes5.dex */
public class Extractor {
    private int cutoff;

    public Extractor() {
        this.cutoff = 0;
    }

    public Extractor(int i) {
        this.cutoff = i;
    }

    public Extractor with(int i) {
        setCutoff(i);
        return this;
    }

    public List<ExtractedResult> extractWithoutOrder(String str, Collection<String> collection, Applicable applicable) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (String str2 : collection) {
            int iApply = applicable.apply(str, str2);
            if (iApply >= this.cutoff) {
                arrayList.add(new ExtractedResult(str2, iApply, i));
            }
            i++;
        }
        return arrayList;
    }

    public <T> List<BoundExtractedResult<T>> extractWithoutOrder(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, Applicable applicable) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (T t : collection) {
            String strApply = toStringFunction.apply(t);
            int iApply = applicable.apply(str, strApply);
            if (iApply >= this.cutoff) {
                arrayList.add(new BoundExtractedResult(t, strApply, iApply, i));
            }
            i++;
        }
        return arrayList;
    }

    public ExtractedResult extractOne(String str, Collection<String> collection, Applicable applicable) {
        return (ExtractedResult) Collections.max(extractWithoutOrder(str, collection, applicable));
    }

    public <T> BoundExtractedResult<T> extractOne(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, Applicable applicable) {
        return (BoundExtractedResult) Collections.max(extractWithoutOrder(str, collection, toStringFunction, applicable));
    }

    public List<ExtractedResult> extractTop(String str, Collection<String> collection, Applicable applicable) {
        List<ExtractedResult> listExtractWithoutOrder = extractWithoutOrder(str, collection, applicable);
        Collections.sort(listExtractWithoutOrder, Collections.reverseOrder());
        return listExtractWithoutOrder;
    }

    public <T> List<BoundExtractedResult<T>> extractTop(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, Applicable applicable) {
        List<BoundExtractedResult<T>> listExtractWithoutOrder = extractWithoutOrder(str, collection, toStringFunction, applicable);
        Collections.sort(listExtractWithoutOrder, Collections.reverseOrder());
        return listExtractWithoutOrder;
    }

    public List<ExtractedResult> extractTop(String str, Collection<String> collection, Applicable applicable, int i) {
        List<ExtractedResult> listFindTopKHeap = Utils.findTopKHeap(extractWithoutOrder(str, collection, applicable), i);
        Collections.reverse(listFindTopKHeap);
        return listFindTopKHeap;
    }

    public <T> List<BoundExtractedResult<T>> extractTop(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, Applicable applicable, int i) {
        List<BoundExtractedResult<T>> listFindTopKHeap = Utils.findTopKHeap(extractWithoutOrder(str, collection, toStringFunction, applicable), i);
        Collections.reverse(listFindTopKHeap);
        return listFindTopKHeap;
    }

    public int getCutoff() {
        return this.cutoff;
    }

    public void setCutoff(int i) {
        this.cutoff = i;
    }
}
