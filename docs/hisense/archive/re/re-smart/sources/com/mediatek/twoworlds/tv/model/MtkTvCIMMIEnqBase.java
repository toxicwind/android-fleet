package com.mediatek.twoworlds.tv.model;

import android.os.Parcel;
import android.os.Parcelable;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvCIMMIEnqBase extends MtkTvCIMMIBase {
    public static final Parcelable.Creator<MtkTvCIMMIEnqBase> CREATOR = new Parcelable.Creator<MtkTvCIMMIEnqBase>() { // from class: com.mediatek.twoworlds.tv.model.MtkTvCIMMIEnqBase.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MtkTvCIMMIEnqBase createFromParcel(Parcel parcel) {
            return new MtkTvCIMMIEnqBase(parcel, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MtkTvCIMMIEnqBase[] newArray(int i) {
            return new MtkTvCIMMIEnqBase[i];
        }
    };
    public static final String TAG = "MtkTvCIMMIEnq";
    public byte ans_txt_len;
    public byte b_blind_ans;
    public String w2s_text;

    public MtkTvCIMMIEnqBase(int i, byte b2, byte b3, String str) {
        super(i);
        this.ans_txt_len = b2;
        this.b_blind_ans = b3;
        this.w2s_text = str;
    }

    public int describeContents() {
        return 0;
    }

    public byte getAnsTextLen() {
        return this.ans_txt_len;
    }

    public boolean getBlindAns() {
        return this.b_blind_ans > 0;
    }

    public String getText() {
        return this.w2s_text;
    }

    public void readFromParcel(Parcel parcel) {
        this.mmi_id = parcel.readInt();
        this.w2s_text = parcel.readString();
        this.b_blind_ans = parcel.readByte();
        this.ans_txt_len = parcel.readByte();
    }

    public void setAnsTextLen(byte b2) {
        this.ans_txt_len = b2;
    }

    public void setBlindAns(byte b2) {
        this.b_blind_ans = b2;
    }

    public void setText(String str) {
        this.w2s_text = str;
    }

    public String toString() {
        StringBuilder sbA = a.a("MMIEnq [enq id=");
        sbA.append(this.mmi_id);
        sbA.append(", text=");
        sbA.append(this.w2s_text);
        sbA.append(", blind answer=");
        sbA.append((int) this.b_blind_ans);
        sbA.append(", answer text length=");
        return a.a(sbA, this.ans_txt_len, "]");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mmi_id);
        parcel.writeString(this.w2s_text);
        parcel.writeByte(this.b_blind_ans);
        parcel.writeByte(this.ans_txt_len);
    }

    public MtkTvCIMMIEnqBase() {
    }

    public MtkTvCIMMIEnqBase(Parcel parcel) {
        readFromParcel(parcel);
    }

    public /* synthetic */ MtkTvCIMMIEnqBase(Parcel parcel, AnonymousClass1 anonymousClass1) {
        readFromParcel(parcel);
    }
}
