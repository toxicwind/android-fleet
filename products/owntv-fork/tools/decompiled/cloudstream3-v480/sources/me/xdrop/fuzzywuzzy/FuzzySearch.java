package me.xdrop.fuzzywuzzy;

import java.util.Collection;
import java.util.List;
import me.xdrop.fuzzywuzzy.algorithms.TokenSet;
import me.xdrop.fuzzywuzzy.algorithms.TokenSort;
import me.xdrop.fuzzywuzzy.algorithms.WeightedRatio;
import me.xdrop.fuzzywuzzy.model.BoundExtractedResult;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import me.xdrop.fuzzywuzzy.ratios.PartialRatio;
import me.xdrop.fuzzywuzzy.ratios.SimpleRatio;

/* JADX INFO: loaded from: classes5.dex */
public class FuzzySearch {
    public static int ratio(String str, String str2) {
        return new SimpleRatio().apply(str, str2);
    }

    public static int ratio(String str, String str2, ToStringFunction<String> toStringFunction) {
        return new SimpleRatio().apply(str, str2, toStringFunction);
    }

    public static int partialRatio(String str, String str2) {
        return new PartialRatio().apply(str, str2);
    }

    public static int partialRatio(String str, String str2, ToStringFunction<String> toStringFunction) {
        return new PartialRatio().apply(str, str2, toStringFunction);
    }

    public static int tokenSortPartialRatio(String str, String str2) {
        return new TokenSort().apply(str, str2, new PartialRatio());
    }

    public static int tokenSortPartialRatio(String str, String str2, ToStringFunction<String> toStringFunction) {
        return new TokenSort().apply(str, str2, new PartialRatio(), toStringFunction);
    }

    public static int tokenSortRatio(String str, String str2) {
        return new TokenSort().apply(str, str2, new SimpleRatio());
    }

    public static int tokenSortRatio(String str, String str2, ToStringFunction<String> toStringFunction) {
        return new TokenSort().apply(str, str2, new SimpleRatio(), toStringFunction);
    }

    public static int tokenSetRatio(String str, String str2) {
        return new TokenSet().apply(str, str2, new SimpleRatio());
    }

    public static int tokenSetRatio(String str, String str2, ToStringFunction<String> toStringFunction) {
        return new TokenSet().apply(str, str2, new SimpleRatio(), toStringFunction);
    }

    public static int tokenSetPartialRatio(String str, String str2) {
        return new TokenSet().apply(str, str2, new PartialRatio());
    }

    public static int tokenSetPartialRatio(String str, String str2, ToStringFunction<String> toStringFunction) {
        return new TokenSet().apply(str, str2, new PartialRatio(), toStringFunction);
    }

    public static int weightedRatio(String str, String str2) {
        return new WeightedRatio().apply(str, str2);
    }

    public static int weightedRatio(String str, String str2, ToStringFunction<String> toStringFunction) {
        return new WeightedRatio().apply(str, str2, toStringFunction);
    }

    public static List<ExtractedResult> extractTop(String str, Collection<String> collection, Applicable applicable, int i, int i2) {
        return new Extractor(i2).extractTop(str, collection, applicable, i);
    }

    public static List<ExtractedResult> extractTop(String str, Collection<String> collection, int i, int i2) {
        return new Extractor(i2).extractTop(str, collection, new WeightedRatio(), i);
    }

    public static List<ExtractedResult> extractTop(String str, Collection<String> collection, Applicable applicable, int i) {
        return new Extractor().extractTop(str, collection, applicable, i);
    }

    public static List<ExtractedResult> extractTop(String str, Collection<String> collection, int i) {
        return new Extractor().extractTop(str, collection, new WeightedRatio(), i);
    }

    public static List<ExtractedResult> extractSorted(String str, Collection<String> collection, Applicable applicable) {
        return new Extractor().extractTop(str, collection, applicable);
    }

    public static List<ExtractedResult> extractSorted(String str, Collection<String> collection, Applicable applicable, int i) {
        return new Extractor(i).extractTop(str, collection, applicable);
    }

    public static List<ExtractedResult> extractSorted(String str, Collection<String> collection) {
        return new Extractor().extractTop(str, collection, new WeightedRatio());
    }

    public static List<ExtractedResult> extractSorted(String str, Collection<String> collection, int i) {
        return new Extractor(i).extractTop(str, collection, new WeightedRatio());
    }

    public static List<ExtractedResult> extractAll(String str, Collection<String> collection, Applicable applicable) {
        return new Extractor().extractWithoutOrder(str, collection, applicable);
    }

    public static List<ExtractedResult> extractAll(String str, Collection<String> collection, Applicable applicable, int i) {
        return new Extractor(i).extractWithoutOrder(str, collection, applicable);
    }

    public static List<ExtractedResult> extractAll(String str, Collection<String> collection) {
        return new Extractor().extractWithoutOrder(str, collection, new WeightedRatio());
    }

    public static List<ExtractedResult> extractAll(String str, Collection<String> collection, int i) {
        return new Extractor(i).extractWithoutOrder(str, collection, new WeightedRatio());
    }

    public static ExtractedResult extractOne(String str, Collection<String> collection, Applicable applicable) {
        return new Extractor().extractOne(str, collection, applicable);
    }

    public static ExtractedResult extractOne(String str, Collection<String> collection) {
        return new Extractor().extractOne(str, collection, new WeightedRatio());
    }

    public static <T> List<BoundExtractedResult<T>> extractTop(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, Applicable applicable, int i, int i2) {
        return new Extractor(i2).extractTop(str, collection, toStringFunction, applicable, i);
    }

    public static <T> List<BoundExtractedResult<T>> extractTop(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, int i, int i2) {
        return new Extractor(i2).extractTop(str, collection, toStringFunction, new WeightedRatio(), i);
    }

    public static <T> List<BoundExtractedResult<T>> extractTop(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, Applicable applicable, int i) {
        return new Extractor().extractTop(str, collection, toStringFunction, applicable, i);
    }

    public static <T> List<BoundExtractedResult<T>> extractTop(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, int i) {
        return new Extractor().extractTop(str, collection, toStringFunction, new WeightedRatio(), i);
    }

    public static <T> List<BoundExtractedResult<T>> extractSorted(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, Applicable applicable) {
        return new Extractor().extractTop(str, collection, toStringFunction, applicable);
    }

    public static <T> List<BoundExtractedResult<T>> extractSorted(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, Applicable applicable, int i) {
        return new Extractor(i).extractTop(str, collection, toStringFunction, applicable);
    }

    public static <T> List<BoundExtractedResult<T>> extractSorted(String str, Collection<T> collection, ToStringFunction<T> toStringFunction) {
        return new Extractor().extractTop(str, collection, toStringFunction, new WeightedRatio());
    }

    public static <T> List<BoundExtractedResult<T>> extractSorted(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, int i) {
        return new Extractor(i).extractTop(str, collection, toStringFunction, new WeightedRatio());
    }

    public static <T> List<BoundExtractedResult<T>> extractAll(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, Applicable applicable) {
        return new Extractor().extractWithoutOrder(str, collection, toStringFunction, applicable);
    }

    public static <T> List<BoundExtractedResult<T>> extractAll(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, Applicable applicable, int i) {
        return new Extractor(i).extractWithoutOrder(str, collection, toStringFunction, applicable);
    }

    public static <T> List<BoundExtractedResult<T>> extractAll(String str, Collection<T> collection, ToStringFunction<T> toStringFunction) {
        return new Extractor().extractWithoutOrder(str, collection, toStringFunction, new WeightedRatio());
    }

    public static <T> List<BoundExtractedResult<T>> extractAll(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, int i) {
        return new Extractor(i).extractWithoutOrder(str, collection, toStringFunction, new WeightedRatio());
    }

    public static <T> BoundExtractedResult<T> extractOne(String str, Collection<T> collection, ToStringFunction<T> toStringFunction, Applicable applicable) {
        return new Extractor().extractOne(str, collection, toStringFunction, applicable);
    }

    public static <T> BoundExtractedResult<T> extractOne(String str, Collection<T> collection, ToStringFunction<T> toStringFunction) {
        return new Extractor().extractOne(str, collection, toStringFunction, new WeightedRatio());
    }
}
