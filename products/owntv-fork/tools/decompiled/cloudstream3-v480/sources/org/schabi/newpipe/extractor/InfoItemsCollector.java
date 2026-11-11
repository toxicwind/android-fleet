package org.schabi.newpipe.extractor;

import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.InfoItemExtractor;
import org.schabi.newpipe.extractor.exceptions.FoundAdException;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

/* JADX INFO: loaded from: classes8.dex */
public abstract class InfoItemsCollector<I extends InfoItem, E extends InfoItemExtractor> implements Collector<I, E> {

    @Nullable
    private final Comparator<I> comparator;
    private final List<Throwable> errors;
    private final List<I> itemList;
    private final int serviceId;

    public InfoItemsCollector(int i) {
        this(i, null);
    }

    public InfoItemsCollector(int i, @Nullable Comparator<I> comparator) {
        this.itemList = new ArrayList();
        this.errors = new ArrayList();
        this.serviceId = i;
        this.comparator = comparator;
    }

    @Override // org.schabi.newpipe.extractor.Collector
    public List<I> getItems() {
        Comparator<I> comparator = this.comparator;
        if (comparator != null) {
            j$.util.List.EL.sort(this.itemList, comparator);
        }
        return DesugarCollections.unmodifiableList(this.itemList);
    }

    @Override // org.schabi.newpipe.extractor.Collector
    public List<Throwable> getErrors() {
        return DesugarCollections.unmodifiableList(this.errors);
    }

    @Override // org.schabi.newpipe.extractor.Collector
    public void reset() {
        this.itemList.clear();
        this.errors.clear();
    }

    protected void addError(Exception exc) {
        this.errors.add(exc);
    }

    protected void addItem(I i) {
        this.itemList.add(i);
    }

    public int getServiceId() {
        return this.serviceId;
    }

    @Override // org.schabi.newpipe.extractor.Collector
    public void commit(E e) {
        try {
            addItem(extract(e));
        } catch (FoundAdException unused) {
        } catch (ParsingException e2) {
            addError(e2);
        }
    }
}
