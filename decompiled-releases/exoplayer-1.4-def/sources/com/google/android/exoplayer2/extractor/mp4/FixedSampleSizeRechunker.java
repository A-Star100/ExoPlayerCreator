package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.util.Util;

final class FixedSampleSizeRechunker {

    public final class Results {
        public final long[] a;
        public final int[] b;
        public final int c;
        public final long[] d;
        public final int[] e;
        public final long f;

        private Results(long[] jArr, int[] iArr, int i, long[] jArr2, int[] iArr2, long j) {
            this.a = jArr;
            this.b = iArr;
            this.c = i;
            this.d = jArr2;
            this.e = iArr2;
            this.f = j;
        }

        /* synthetic */ Results(long[] jArr, int[] iArr, int i, long[] jArr2, int[] iArr2, long j, byte b2) {
            this(jArr, iArr, i, jArr2, iArr2, j);
        }
    }

    public static Results a(int i, long[] jArr, int[] iArr, long j) {
        int[] iArr2 = iArr;
        int i2 = 8192 / i;
        int i3 = 0;
        for (int a : iArr2) {
            i3 += Util.a(a, i2);
        }
        long[] jArr2 = new long[i3];
        int[] iArr3 = new int[i3];
        long[] jArr3 = new long[i3];
        int[] iArr4 = new int[i3];
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < iArr2.length; i7++) {
            int i8 = iArr2[i7];
            long j2 = jArr[i7];
            while (i8 > 0) {
                int min = Math.min(i2, i8);
                jArr2[i5] = j2;
                int i9 = i * min;
                iArr3[i5] = i9;
                i6 = Math.max(i6, i9);
                jArr3[i5] = ((long) i4) * j;
                iArr4[i5] = 1;
                j2 += (long) iArr3[i5];
                i4 += min;
                i8 -= min;
                i5++;
            }
        }
        return new Results(jArr2, iArr3, i6, jArr3, iArr4, j * ((long) i4), (byte) 0);
    }
}
