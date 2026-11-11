package org.mozilla.universalchardet.prober;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.mozilla.universalchardet.prober.sequence.HebrewModel;
import org.mozilla.universalchardet.prober.sequence.Ibm855Model;
import org.mozilla.universalchardet.prober.sequence.Ibm866Model;
import org.mozilla.universalchardet.prober.sequence.Koi8rModel;
import org.mozilla.universalchardet.prober.sequence.Latin5BulgarianModel;
import org.mozilla.universalchardet.prober.sequence.Latin5Model;
import org.mozilla.universalchardet.prober.sequence.Latin7Model;
import org.mozilla.universalchardet.prober.sequence.MacCyrillicModel;
import org.mozilla.universalchardet.prober.sequence.ThaiModel;
import org.mozilla.universalchardet.prober.sequence.Win1251BulgarianModel;
import org.mozilla.universalchardet.prober.sequence.Win1251Model;
import org.mozilla.universalchardet.prober.sequence.Win1253Model;

/* JADX INFO: loaded from: classes8.dex */
public class SBCSGroupProber extends CharsetProber {
    private int activeNum;
    private CharsetProber bestGuess;
    private List<CharsetProber> probers;
    private CharsetProber.ProbingState state;

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public void setOption() {
    }

    public SBCSGroupProber() {
        ArrayList arrayList = new ArrayList();
        this.probers = arrayList;
        arrayList.add(new SingleByteCharsetProber(new Win1251Model()));
        this.probers.add(new SingleByteCharsetProber(new Koi8rModel()));
        this.probers.add(new SingleByteCharsetProber(new Latin5Model()));
        this.probers.add(new SingleByteCharsetProber(new MacCyrillicModel()));
        this.probers.add(new SingleByteCharsetProber(new Ibm866Model()));
        this.probers.add(new SingleByteCharsetProber(new Ibm855Model()));
        this.probers.add(new SingleByteCharsetProber(new Latin7Model()));
        this.probers.add(new SingleByteCharsetProber(new Win1253Model()));
        this.probers.add(new SingleByteCharsetProber(new Latin5BulgarianModel()));
        this.probers.add(new SingleByteCharsetProber(new Win1251BulgarianModel()));
        this.probers.add(new SingleByteCharsetProber(new ThaiModel()));
        HebrewModel hebrewModel = new HebrewModel();
        HebrewProber hebrewProber = new HebrewProber();
        SingleByteCharsetProber singleByteCharsetProber = new SingleByteCharsetProber(hebrewModel, false, hebrewProber);
        SingleByteCharsetProber singleByteCharsetProber2 = new SingleByteCharsetProber(hebrewModel, true, hebrewProber);
        hebrewProber.setModalProbers(singleByteCharsetProber, singleByteCharsetProber2);
        this.probers.add(hebrewProber);
        this.probers.add(singleByteCharsetProber);
        this.probers.add(singleByteCharsetProber2);
        reset();
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public String getCharSetName() {
        if (this.bestGuess == null) {
            getConfidence();
            if (this.bestGuess == null) {
                this.bestGuess = this.probers.get(0);
            }
        }
        return this.bestGuess.getCharSetName();
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public float getConfidence() {
        if (this.state == CharsetProber.ProbingState.FOUND_IT) {
            return 0.99f;
        }
        if (this.state == CharsetProber.ProbingState.NOT_ME) {
            return 0.01f;
        }
        float f = 0.0f;
        for (CharsetProber charsetProber : this.probers) {
            if (charsetProber.isActive()) {
                float confidence = charsetProber.getConfidence();
                if (f < confidence) {
                    this.bestGuess = charsetProber;
                    f = confidence;
                }
            }
        }
        return f;
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public CharsetProber.ProbingState getState() {
        return this.state;
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public CharsetProber.ProbingState handleData(byte[] bArr, int i, int i2) {
        ByteBuffer byteBufferFilterWithoutEnglishLetters = filterWithoutEnglishLetters(bArr, i, i2);
        if (byteBufferFilterWithoutEnglishLetters.position() != 0) {
            for (CharsetProber charsetProber : this.probers) {
                if (charsetProber.isActive()) {
                    CharsetProber.ProbingState probingStateHandleData = charsetProber.handleData(byteBufferFilterWithoutEnglishLetters.array(), 0, byteBufferFilterWithoutEnglishLetters.position());
                    if (probingStateHandleData == CharsetProber.ProbingState.FOUND_IT || 0.99f == charsetProber.getConfidence()) {
                        this.bestGuess = charsetProber;
                        this.state = CharsetProber.ProbingState.FOUND_IT;
                        break;
                    }
                    if (probingStateHandleData == CharsetProber.ProbingState.NOT_ME) {
                        charsetProber.setActive(false);
                        int i3 = this.activeNum - 1;
                        this.activeNum = i3;
                        if (i3 <= 0) {
                            this.state = CharsetProber.ProbingState.NOT_ME;
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return this.state;
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public final void reset() {
        this.activeNum = 0;
        for (CharsetProber charsetProber : this.probers) {
            charsetProber.reset();
            charsetProber.setActive(true);
            this.activeNum++;
        }
        this.bestGuess = null;
        this.state = CharsetProber.ProbingState.DETECTING;
    }
}
