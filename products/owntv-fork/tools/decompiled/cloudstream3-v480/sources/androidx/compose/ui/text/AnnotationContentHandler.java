package androidx.compose.ui.text;

import android.text.Editable;
import java.util.ArrayList;
import kotlin.Metadata;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/* JADX INFO: compiled from: Html.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0019\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J0\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J&\u0010\u0013\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0012H\u0002J\b\u0010\u0016\u001a\u00020\fH\u0002J\b\u0010\u0017\u001a\u00020\fH\u0002J\b\u0010\u0018\u001a\u00020\fH\u0002J\b\u0010\u0019\u001a\u00020\fH\u0002J\b\u0010\u001a\u001a\u00020\fH\u0002J\b\u0010\u001b\u001a\u00020\fH\u0002J)\u0010\u001c\u001a\u00020\f2\u000e\u0010\u001d\u001a\n \u001f*\u0004\u0018\u00010\u001e0\u001e2\u0006\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\bH\u0096\u0001J\t\u0010\"\u001a\u00020\fH\u0096\u0001J\u0019\u0010#\u001a\u00020\f2\u000e\u0010\u001d\u001a\n \u001f*\u0004\u0018\u00010\u000e0\u000eH\u0096\u0001J)\u0010$\u001a\u00020\f2\u000e\u0010\u001d\u001a\n \u001f*\u0004\u0018\u00010\u001e0\u001e2\u0006\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\bH\u0096\u0001J)\u0010%\u001a\u00020\f2\u000e\u0010\u001d\u001a\n \u001f*\u0004\u0018\u00010\u000e0\u000e2\u000e\u0010 \u001a\n \u001f*\u0004\u0018\u00010\u000e0\u000eH\u0096\u0001J\u0019\u0010&\u001a\u00020\f2\u000e\u0010\u001d\u001a\n \u001f*\u0004\u0018\u00010'0'H\u0096\u0001J\u0019\u0010(\u001a\u00020\f2\u000e\u0010\u001d\u001a\n \u001f*\u0004\u0018\u00010\u000e0\u000eH\u0096\u0001J\t\u0010)\u001a\u00020\fH\u0096\u0001J)\u0010*\u001a\u00020\f2\u000e\u0010\u001d\u001a\n \u001f*\u0004\u0018\u00010\u000e0\u000e2\u000e\u0010 \u001a\n \u001f*\u0004\u0018\u00010\u000e0\u000eH\u0096\u0001R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Landroidx/compose/ui/text/AnnotationContentHandler;", "Lorg/xml/sax/ContentHandler;", "contentHandler", "output", "Landroid/text/Editable;", "<init>", "(Lorg/xml/sax/ContentHandler;Landroid/text/Editable;)V", "bulletIndentation", "", "currentBulletSpan", "Landroidx/compose/ui/text/BulletSpanWithLevel;", "startElement", "", "uri", "", "localName", "qName", "atts", "Lorg/xml/sax/Attributes;", "endElement", "handleAnnotationStart", "attributes", "handleAnnotationEnd", "handleUlStart", "handleUlEnd", "handleLiStart", "handleLiEnd", "commitCurrentBulletSpan", "characters", "p0", "", "kotlin.jvm.PlatformType", "p1", "p2", "endDocument", "endPrefixMapping", "ignorableWhitespace", "processingInstruction", "setDocumentLocator", "Lorg/xml/sax/Locator;", "skippedEntity", "startDocument", "startPrefixMapping", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class AnnotationContentHandler implements ContentHandler {
    private int bulletIndentation;
    private final ContentHandler contentHandler;
    private BulletSpanWithLevel currentBulletSpan;
    private final Editable output;

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] p0, int p1, int p2) throws SAXException {
        this.contentHandler.characters(p0, p1, p2);
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        this.contentHandler.endDocument();
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String p0) throws SAXException {
        this.contentHandler.endPrefixMapping(p0);
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] p0, int p1, int p2) throws SAXException {
        this.contentHandler.ignorableWhitespace(p0, p1, p2);
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String p0, String p1) throws SAXException {
        this.contentHandler.processingInstruction(p0, p1);
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator p0) {
        this.contentHandler.setDocumentLocator(p0);
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String p0) throws SAXException {
        this.contentHandler.skippedEntity(p0);
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        this.contentHandler.startDocument();
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String p0, String p1) throws SAXException {
        this.contentHandler.startPrefixMapping(p0, p1);
    }

    public AnnotationContentHandler(ContentHandler contentHandler, Editable editable) {
        this.contentHandler = contentHandler;
        this.output = editable;
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (localName != null) {
            int iHashCode = localName.hashCode();
            if (iHashCode != -1555043537) {
                if (iHashCode != 3453) {
                    if (iHashCode == 3735 && localName.equals("ul")) {
                        handleUlStart();
                        return;
                    }
                } else if (localName.equals("li")) {
                    handleLiStart();
                    return;
                }
            } else if (localName.equals("annotation")) {
                if (atts != null) {
                    handleAnnotationStart(atts);
                    return;
                }
                return;
            }
        }
        this.contentHandler.startElement(uri, localName, qName, atts);
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName != null) {
            int iHashCode = localName.hashCode();
            if (iHashCode != -1555043537) {
                if (iHashCode != 3453) {
                    if (iHashCode == 3735 && localName.equals("ul")) {
                        handleUlEnd();
                        return;
                    }
                } else if (localName.equals("li")) {
                    handleLiEnd();
                    return;
                }
            } else if (localName.equals("annotation")) {
                handleAnnotationEnd();
                return;
            }
        }
        this.contentHandler.endElement(uri, localName, qName);
    }

    private final void handleAnnotationStart(Attributes attributes) {
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            String localName = attributes.getLocalName(i);
            if (localName == null) {
                localName = "";
            }
            String value = attributes.getValue(i);
            String str = value != null ? value : "";
            if (localName.length() > 0 && str.length() > 0) {
                int length2 = this.output.length();
                this.output.setSpan(new AnnotationSpan(localName, str), length2, length2, 17);
            }
        }
    }

    private final void handleAnnotationEnd() {
        Editable editable = this.output;
        Object[] spans = editable.getSpans(0, editable.length(), AnnotationSpan.class);
        ArrayList arrayList = new ArrayList();
        for (Object obj : spans) {
            if (this.output.getSpanFlags((AnnotationSpan) obj) == 17) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            AnnotationSpan annotationSpan = (AnnotationSpan) arrayList2.get(i);
            int spanStart = this.output.getSpanStart(annotationSpan);
            int length = this.output.length();
            this.output.removeSpan(annotationSpan);
            if (spanStart != length) {
                this.output.setSpan(annotationSpan, spanStart, length, 33);
            }
        }
    }

    private final void handleUlStart() {
        commitCurrentBulletSpan();
        this.bulletIndentation++;
    }

    private final void handleUlEnd() {
        commitCurrentBulletSpan();
        this.bulletIndentation--;
    }

    private final void handleLiStart() {
        commitCurrentBulletSpan();
        this.currentBulletSpan = new BulletSpanWithLevel(Bullet.INSTANCE.getDefault(), this.bulletIndentation, this.output.length());
    }

    private final void handleLiEnd() {
        commitCurrentBulletSpan();
    }

    private final void commitCurrentBulletSpan() {
        BulletSpanWithLevel bulletSpanWithLevel = this.currentBulletSpan;
        if (bulletSpanWithLevel != null) {
            this.output.setSpan(bulletSpanWithLevel, bulletSpanWithLevel.getStart(), this.output.length(), 33);
        }
        this.currentBulletSpan = null;
    }
}
