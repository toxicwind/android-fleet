package com.mediatek.twoworlds.tv.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvCIMMIEnqBase extends MtkTvCIMMIBase {
    public static final Parcelable.Creator<MtkTvCIMMIEnqBase> CREATOR = new Parcelable.Creator<MtkTvCIMMIEnqBase>() { // from class: com.mediatek.twoworlds.tv.model.MtkTvCIMMIEnqBase.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MtkTvCIMMIEnqBase createFromParcel(Parcel parcel) {
            return new MtkTvCIMMIEnqBase(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MtkTvCIMMIEnqBase[] newArray(int i) {
            return new MtkTvCIMMIEnqBase[i];
        }
    };
    private static final String TAG = "MtkTvCIMMIEnq";
    private byte ans_txt_len;
    private byte b_blind_ans;
    private String w2s_text;

    public int describeContents() {
        return 0;
    }

    public MtkTvCIMMIEnqBase(int i, byte b, byte b2, String str) {
        super(i);
        this.ans_txt_len = b;
        this.b_blind_ans = b2;
        this.w2s_text = str;
    }

    public MtkTvCIMMIEnqBase() {
    }

    public void setText(String str) {
        this.w2s_text = str;
    }

    public void setBlindAns(byte b) {
        this.b_blind_ans = b;
    }

    public void setAnsTextLen(byte b) {
        this.ans_txt_len = b;
    }

    public String getText() {
        return this.w2s_text;
    }

    public boolean getBlindAns() {
        return this.b_blind_ans > 0;
    }

    public byte getAnsTextLen() {
        return this.ans_txt_len;
    }

    public String toString() {
        return "MMIEnq [enq id=" + this.mmi_id + ", text=" + this.w2s_text + ", blind answer=" + ((int) this.b_blind_ans) + ", answer text length=" + ((int) this.ans_txt_len) + "]";
    }

    private MtkTvCIMMIEnqBase(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mmi_id);
        parcel.writeString(this.w2s_text);
        parcel.writeByte(this.b_blind_ans);
        parcel.writeByte(this.ans_txt_len);
    }

    public void readFromParcel(Parcel parcel) {
        this.mmi_id = parcel.readInt();
        this.w2s_text = parcel.readString();
        this.b_blind_ans = parcel.readByte();
        this.ans_txt_len = parcel.readByte();
    }
}
