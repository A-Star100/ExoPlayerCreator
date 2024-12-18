package com.google.android.exoplayer2.text.ttml;

final class TtmlRegion {
    public final String a;
    public final float b;
    public final float c;
    public final int d;
    public final int e;
    public final float f;
    public final float g;
    public final int h;
    public final float i;
    public final int j;

    public TtmlRegion(String str) {
        this(str, -3.4028235E38f, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, -3.4028235E38f, Integer.MIN_VALUE, -3.4028235E38f, Integer.MIN_VALUE);
    }

    public TtmlRegion(String str, float f2, float f3, int i2, int i3, float f4, float f5, int i4, float f6, int i5) {
        this.a = str;
        this.b = f2;
        this.c = f3;
        this.d = i2;
        this.e = i3;
        this.f = f4;
        this.g = f5;
        this.h = i4;
        this.i = f6;
        this.j = i5;
    }
}
