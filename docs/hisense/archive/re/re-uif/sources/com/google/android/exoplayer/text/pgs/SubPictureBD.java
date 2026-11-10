package com.google.android.exoplayer.text.pgs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SubPictureBD extends SubPicture {
    private List<ImageObject> imageObjectList;
    private int objectID;
    private List<List<PaletteInfo>> palettes;
    private int type;
    private int windowHeight;
    private int windowWidth;
    private int xWindowOffset;
    private int yWindowOffset;

    public SubPictureBD() {
        this.imageObjectList = new ArrayList();
        this.palettes = new ArrayList();
        for (int i = 0; i < 8; i++) {
            this.palettes.add(new ArrayList());
        }
    }

    public SubPictureBD(SubPictureBD subPictureBD) {
        super(subPictureBD);
        this.imageObjectList = new ArrayList();
        this.palettes = new ArrayList();
        for (int i = 0; i < 8; i++) {
            this.palettes.add(new ArrayList());
        }
        this.objectID = subPictureBD.objectID;
        this.windowWidth = subPictureBD.windowWidth;
        this.windowHeight = subPictureBD.windowHeight;
        this.xWindowOffset = subPictureBD.xWindowOffset;
        this.yWindowOffset = subPictureBD.yWindowOffset;
        this.type = subPictureBD.type;
        if (subPictureBD.palettes != null) {
            this.palettes = new ArrayList();
            Iterator<List<PaletteInfo>> it = subPictureBD.palettes.iterator();
            while (it.hasNext()) {
                this.palettes.add(new ArrayList(it.next()));
            }
        }
        Iterator<ImageObject> it2 = subPictureBD.imageObjectList.iterator();
        while (it2.hasNext()) {
            this.imageObjectList.add(new ImageObject(it2.next()));
        }
    }

    public boolean isMergableWith(SubPictureBD subPictureBD) {
        if (subPictureBD == null) {
            return false;
        }
        if (subPictureBD.getEndTime() == 0 || getStartTime() - subPictureBD.getEndTime() < 18000) {
            return isThisImageObjectIdenticalWith(subPictureBD.getImageObject());
        }
        return false;
    }

    private boolean isThisImageObjectIdenticalWith(ImageObject imageObject) {
        ImageObject imageObject2 = getImageObject();
        return imageObject != null && imageObject2 != null && imageObject.getBufferSize() == imageObject2.getBufferSize() && imageObject.getWidth() == imageObject2.getWidth() && imageObject.getHeight() == imageObject2.getHeight();
    }

    @Override // com.google.android.exoplayer.text.pgs.SubPicture
    public int getImageWidth() {
        return this.imageObjectList.get(this.objectID).getWidth();
    }

    @Override // com.google.android.exoplayer.text.pgs.SubPicture
    public int getImageHeight() {
        return this.imageObjectList.get(this.objectID).getHeight();
    }

    @Override // com.google.android.exoplayer.text.pgs.SubPicture
    public int getXOffset() {
        return this.imageObjectList.get(this.objectID).getXOffset();
    }

    @Override // com.google.android.exoplayer.text.pgs.SubPicture
    public int getYOffset() {
        return this.imageObjectList.get(this.objectID).getYOffset();
    }

    ImageObject getImageObject(int i) {
        return this.imageObjectList.get(i);
    }

    ImageObject getImageObject() {
        return this.imageObjectList.get(this.objectID);
    }

    public void setObjectID(int i) {
        this.objectID = i;
    }

    public List<ImageObject> getImageObjectList() {
        return this.imageObjectList;
    }

    public int getWindowWidth() {
        return this.windowWidth;
    }

    public void setWindowWidth(int i) {
        this.windowWidth = i;
    }

    public int getWindowHeight() {
        return this.windowHeight;
    }

    public void setWindowHeight(int i) {
        this.windowHeight = i;
    }

    public void setXWindowOffset(int i) {
        this.xWindowOffset = i;
    }

    public void setYWindowOffset(int i) {
        this.yWindowOffset = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public List<List<PaletteInfo>> getPalettes() {
        return this.palettes;
    }

    @Override // com.google.android.exoplayer.text.pgs.SubPicture
    public String toString() {
        return "SubPictureBD [objectID=" + this.objectID + ", imageObjectList=" + this.imageObjectList + ", windowWidth=" + this.windowWidth + ", windowHeight=" + this.windowHeight + ", xWindowOffset=" + this.xWindowOffset + ", yWindowOffset=" + this.yWindowOffset + ", type=" + this.type + ", palettes=" + this.palettes + "]\n" + super.toString();
    }
}
