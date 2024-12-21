package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

final class SubtitlePainter {
    private static final float INNER_PADDING_RATIO = 0.125f;
    private static final String TAG = "SubtitlePainter";
    private int backgroundColor;
    private final Paint bitmapPaint;
    private Rect bitmapRect;
    private float bottomPaddingFraction;
    private Bitmap cueBitmap;
    private float cueBitmapHeight;
    private float cueLine;
    private int cueLineAnchor;
    private int cueLineType;
    private float cuePosition;
    private int cuePositionAnchor;
    private float cueSize;
    private CharSequence cueText;
    private Layout.Alignment cueTextAlignment;
    private float cueTextSizePx;
    private float defaultTextSizePx;
    private int edgeColor;
    private StaticLayout edgeLayout;
    private int edgeType;
    private int foregroundColor;
    private final float outlineWidth;
    private int parentBottom;
    private int parentLeft;
    private int parentRight;
    private int parentTop;
    private final float shadowOffset;
    private final float shadowRadius;
    private final float spacingAdd;
    private final float spacingMult;
    private StaticLayout textLayout;
    private int textLeft;
    private int textPaddingX;
    private final TextPaint textPaint;
    private int textTop;
    private int windowColor;
    private final Paint windowPaint;

    public SubtitlePainter(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, new int[]{16843287, 16843288}, 0, 0);
        this.spacingAdd = (float) obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.spacingMult = obtainStyledAttributes.getFloat(1, 1.0f);
        obtainStyledAttributes.recycle();
        float round = (float) Math.round((((float) context.getResources().getDisplayMetrics().densityDpi) * 2.0f) / 160.0f);
        this.outlineWidth = round;
        this.shadowRadius = round;
        this.shadowOffset = round;
        TextPaint textPaint2 = new TextPaint();
        this.textPaint = textPaint2;
        textPaint2.setAntiAlias(true);
        textPaint2.setSubpixelText(true);
        Paint paint = new Paint();
        this.windowPaint = paint;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.bitmapPaint = paint2;
        paint2.setAntiAlias(true);
        paint2.setFilterBitmap(true);
    }

    private static boolean areCharSequencesEqual(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence != charSequence2) {
            return charSequence != null && charSequence.equals(charSequence2);
        }
        return true;
    }

    private void drawBitmapLayout(Canvas canvas) {
        canvas.drawBitmap(this.cueBitmap, (Rect) null, this.bitmapRect, this.bitmapPaint);
    }

    private void drawLayout(Canvas canvas, boolean z) {
        if (z) {
            drawTextLayout(canvas);
            return;
        }
        Assertions.b((Object) this.bitmapRect);
        Assertions.b((Object) this.cueBitmap);
        drawBitmapLayout(canvas);
    }

    private void drawTextLayout(Canvas canvas) {
        StaticLayout staticLayout = this.textLayout;
        StaticLayout staticLayout2 = this.edgeLayout;
        if (staticLayout != null && staticLayout2 != null) {
            int save = canvas.save();
            canvas.translate((float) this.textLeft, (float) this.textTop);
            if (Color.alpha(this.windowColor) > 0) {
                this.windowPaint.setColor(this.windowColor);
                canvas.drawRect((float) (-this.textPaddingX), 0.0f, (float) (staticLayout.getWidth() + this.textPaddingX), (float) staticLayout.getHeight(), this.windowPaint);
            }
            int i = this.edgeType;
            boolean z = true;
            if (i == 1) {
                this.textPaint.setStrokeJoin(Paint.Join.ROUND);
                this.textPaint.setStrokeWidth(this.outlineWidth);
                this.textPaint.setColor(this.edgeColor);
                this.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                staticLayout2.draw(canvas);
            } else if (i == 2) {
                TextPaint textPaint2 = this.textPaint;
                float f = this.shadowRadius;
                float f2 = this.shadowOffset;
                textPaint2.setShadowLayer(f, f2, f2, this.edgeColor);
            } else if (i == 3 || i == 4) {
                if (i != 3) {
                    z = false;
                }
                int i2 = -1;
                int i3 = z ? -1 : this.edgeColor;
                if (z) {
                    i2 = this.edgeColor;
                }
                float f3 = this.shadowRadius / 2.0f;
                this.textPaint.setColor(this.foregroundColor);
                this.textPaint.setStyle(Paint.Style.FILL);
                float f4 = -f3;
                this.textPaint.setShadowLayer(this.shadowRadius, f4, f4, i3);
                staticLayout2.draw(canvas);
                this.textPaint.setShadowLayer(this.shadowRadius, f3, f3, i2);
            }
            this.textPaint.setColor(this.foregroundColor);
            this.textPaint.setStyle(Paint.Style.FILL);
            staticLayout.draw(canvas);
            this.textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            canvas.restoreToCount(save);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0058  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupBitmapLayout() {
        /*
            r7 = this;
            android.graphics.Bitmap r0 = r7.cueBitmap
            int r1 = r7.parentRight
            int r2 = r7.parentLeft
            int r1 = r1 - r2
            int r3 = r7.parentBottom
            int r4 = r7.parentTop
            int r3 = r3 - r4
            float r2 = (float) r2
            float r1 = (float) r1
            float r5 = r7.cuePosition
            float r5 = r5 * r1
            float r2 = r2 + r5
            float r4 = (float) r4
            float r3 = (float) r3
            float r5 = r7.cueLine
            float r5 = r5 * r3
            float r4 = r4 + r5
            float r5 = r7.cueSize
            float r1 = r1 * r5
            int r1 = java.lang.Math.round(r1)
            float r5 = r7.cueBitmapHeight
            r6 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            int r6 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r6 == 0) goto L_0x002c
            goto L_0x0038
        L_0x002c:
            float r3 = (float) r1
            int r5 = r0.getHeight()
            float r5 = (float) r5
            int r0 = r0.getWidth()
            float r0 = (float) r0
            float r5 = r5 / r0
        L_0x0038:
            float r3 = r3 * r5
            int r0 = java.lang.Math.round(r3)
            int r3 = r7.cuePositionAnchor
            r5 = 1
            r6 = 2
            if (r3 != r6) goto L_0x0047
            float r3 = (float) r1
        L_0x0045:
            float r2 = r2 - r3
            goto L_0x004d
        L_0x0047:
            if (r3 != r5) goto L_0x004d
            int r3 = r1 / 2
            float r3 = (float) r3
            goto L_0x0045
        L_0x004d:
            int r2 = java.lang.Math.round(r2)
            int r3 = r7.cueLineAnchor
            if (r3 != r6) goto L_0x0058
            float r3 = (float) r0
        L_0x0056:
            float r4 = r4 - r3
            goto L_0x005e
        L_0x0058:
            if (r3 != r5) goto L_0x005e
            int r3 = r0 / 2
            float r3 = (float) r3
            goto L_0x0056
        L_0x005e:
            int r3 = java.lang.Math.round(r4)
            android.graphics.Rect r4 = new android.graphics.Rect
            int r1 = r1 + r2
            int r0 = r0 + r3
            r4.<init>(r2, r3, r1, r0)
            r7.bitmapRect = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.SubtitlePainter.setupBitmapLayout():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0127, code lost:
        r5 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x012e, code lost:
        r2 = java.lang.Math.max(r2, r4);
        r4 = java.lang.Math.min(r15 + r2, r0.parentRight);
     */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupTextLayout() {
        /*
            r26 = this;
            r0 = r26
            java.lang.CharSequence r1 = r0.cueText
            boolean r2 = r1 instanceof android.text.SpannableStringBuilder
            if (r2 == 0) goto L_0x000b
            android.text.SpannableStringBuilder r1 = (android.text.SpannableStringBuilder) r1
            goto L_0x0012
        L_0x000b:
            android.text.SpannableStringBuilder r1 = new android.text.SpannableStringBuilder
            java.lang.CharSequence r2 = r0.cueText
            r1.<init>(r2)
        L_0x0012:
            int r2 = r0.parentRight
            int r3 = r0.parentLeft
            int r2 = r2 - r3
            int r3 = r0.parentBottom
            int r4 = r0.parentTop
            int r11 = r3 - r4
            android.text.TextPaint r3 = r0.textPaint
            float r4 = r0.defaultTextSizePx
            r3.setTextSize(r4)
            float r3 = r0.defaultTextSizePx
            r4 = 1040187392(0x3e000000, float:0.125)
            float r3 = r3 * r4
            r4 = 1056964608(0x3f000000, float:0.5)
            float r3 = r3 + r4
            int r12 = (int) r3
            int r13 = r12 << 1
            int r3 = r2 - r13
            float r4 = r0.cueSize
            r14 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            int r5 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r5 == 0) goto L_0x003f
            float r3 = (float) r3
            float r3 = r3 * r4
            int r3 = (int) r3
        L_0x003f:
            r15 = r3
            java.lang.String r10 = "SubtitlePainter"
            if (r15 > 0) goto L_0x004a
            java.lang.String r1 = "Skipped drawing subtitle cue (insufficient space)"
            com.google.android.exoplayer2.util.Log.c(r10, r1)
            return
        L_0x004a:
            float r3 = r0.cueTextSizePx
            r16 = 0
            r4 = 16711680(0xff0000, float:2.3418052E-38)
            r9 = 0
            int r3 = (r3 > r16 ? 1 : (r3 == r16 ? 0 : -1))
            if (r3 <= 0) goto L_0x0064
            android.text.style.AbsoluteSizeSpan r3 = new android.text.style.AbsoluteSizeSpan
            float r5 = r0.cueTextSizePx
            int r5 = (int) r5
            r3.<init>(r5)
            int r5 = r1.length()
            r1.setSpan(r3, r9, r5, r4)
        L_0x0064:
            android.text.SpannableStringBuilder r8 = new android.text.SpannableStringBuilder
            r8.<init>(r1)
            int r3 = r0.edgeType
            r7 = 1
            if (r3 != r7) goto L_0x0087
            int r3 = r8.length()
            java.lang.Class<android.text.style.ForegroundColorSpan> r5 = android.text.style.ForegroundColorSpan.class
            java.lang.Object[] r3 = r8.getSpans(r9, r3, r5)
            android.text.style.ForegroundColorSpan[] r3 = (android.text.style.ForegroundColorSpan[]) r3
            int r5 = r3.length
            r6 = 0
        L_0x007c:
            if (r6 >= r5) goto L_0x0087
            r7 = r3[r6]
            r8.removeSpan(r7)
            int r6 = r6 + 1
            r7 = 1
            goto L_0x007c
        L_0x0087:
            int r3 = r0.backgroundColor
            int r3 = android.graphics.Color.alpha(r3)
            r7 = 2
            if (r3 <= 0) goto L_0x00b4
            int r3 = r0.edgeType
            if (r3 == 0) goto L_0x00a6
            if (r3 != r7) goto L_0x0097
            goto L_0x00a6
        L_0x0097:
            android.text.style.BackgroundColorSpan r3 = new android.text.style.BackgroundColorSpan
            int r5 = r0.backgroundColor
            r3.<init>(r5)
            int r5 = r8.length()
            r8.setSpan(r3, r9, r5, r4)
            goto L_0x00b4
        L_0x00a6:
            android.text.style.BackgroundColorSpan r3 = new android.text.style.BackgroundColorSpan
            int r5 = r0.backgroundColor
            r3.<init>(r5)
            int r5 = r1.length()
            r1.setSpan(r3, r9, r5, r4)
        L_0x00b4:
            android.text.Layout$Alignment r3 = r0.cueTextAlignment
            if (r3 != 0) goto L_0x00ba
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_CENTER
        L_0x00ba:
            r21 = r3
            android.text.StaticLayout r6 = new android.text.StaticLayout
            android.text.TextPaint r5 = r0.textPaint
            float r4 = r0.spacingMult
            float r3 = r0.spacingAdd
            r18 = 1
            r19 = r3
            r3 = r6
            r20 = r4
            r4 = r1
            r14 = r6
            r6 = r15
            r7 = r21
            r23 = r8
            r8 = r20
            r25 = r12
            r12 = 0
            r9 = r19
            r12 = r10
            r10 = r18
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            r0.textLayout = r14
            int r3 = r14.getHeight()
            android.text.StaticLayout r4 = r0.textLayout
            int r4 = r4.getLineCount()
            r5 = 0
            r9 = 0
        L_0x00ed:
            if (r9 >= r4) goto L_0x0102
            android.text.StaticLayout r6 = r0.textLayout
            float r6 = r6.getLineWidth(r9)
            double r6 = (double) r6
            double r6 = java.lang.Math.ceil(r6)
            int r6 = (int) r6
            int r5 = java.lang.Math.max(r6, r5)
            int r9 = r9 + 1
            goto L_0x00ed
        L_0x0102:
            float r4 = r0.cueSize
            r6 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x010e
            if (r5 >= r15) goto L_0x010e
            goto L_0x010f
        L_0x010e:
            r15 = r5
        L_0x010f:
            int r15 = r15 + r13
            float r4 = r0.cuePosition
            int r5 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r5 == 0) goto L_0x013a
            float r2 = (float) r2
            float r2 = r2 * r4
            int r2 = java.lang.Math.round(r2)
            int r4 = r0.parentLeft
            int r2 = r2 + r4
            int r5 = r0.cuePositionAnchor
            switch(r5) {
                case 1: goto L_0x0129;
                case 2: goto L_0x0126;
                default: goto L_0x0125;
            }
        L_0x0125:
            goto L_0x0127
        L_0x0126:
            int r2 = r2 - r15
        L_0x0127:
            r5 = 2
            goto L_0x012e
        L_0x0129:
            int r2 = r2 << 1
            int r2 = r2 - r15
            r5 = 2
            int r2 = r2 / r5
        L_0x012e:
            int r2 = java.lang.Math.max(r2, r4)
            int r15 = r15 + r2
            int r4 = r0.parentRight
            int r4 = java.lang.Math.min(r15, r4)
            goto L_0x0142
        L_0x013a:
            r5 = 2
            int r2 = r2 - r15
            int r2 = r2 / r5
            int r4 = r0.parentLeft
            int r2 = r2 + r4
            int r4 = r2 + r15
        L_0x0142:
            int r20 = r4 - r2
            if (r20 > 0) goto L_0x014c
            java.lang.String r1 = "Skipped drawing subtitle cue (invalid horizontal positioning)"
            com.google.android.exoplayer2.util.Log.c(r12, r1)
            return
        L_0x014c:
            float r4 = r0.cueLine
            r6 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 == 0) goto L_0x01ac
            int r6 = r0.cueLineType
            if (r6 != 0) goto L_0x0170
            float r6 = (float) r11
            float r6 = r6 * r4
            int r4 = java.lang.Math.round(r6)
            int r6 = r0.parentTop
            int r4 = r4 + r6
            int r6 = r0.cueLineAnchor
            if (r6 != r5) goto L_0x0168
            goto L_0x019c
        L_0x0168:
            r7 = 1
            if (r6 != r7) goto L_0x019d
            int r4 = r4 << 1
            int r4 = r4 - r3
            int r4 = r4 / r5
            goto L_0x019d
        L_0x0170:
            android.text.StaticLayout r4 = r0.textLayout
            r5 = 0
            int r4 = r4.getLineBottom(r5)
            android.text.StaticLayout r6 = r0.textLayout
            int r5 = r6.getLineTop(r5)
            int r4 = r4 - r5
            float r5 = r0.cueLine
            int r6 = (r5 > r16 ? 1 : (r5 == r16 ? 0 : -1))
            if (r6 < 0) goto L_0x018f
            float r4 = (float) r4
            float r5 = r5 * r4
            int r4 = java.lang.Math.round(r5)
            int r5 = r0.parentTop
            int r4 = r4 + r5
            goto L_0x019d
        L_0x018f:
            r6 = 1065353216(0x3f800000, float:1.0)
            float r5 = r5 + r6
            float r4 = (float) r4
            float r5 = r5 * r4
            int r4 = java.lang.Math.round(r5)
            int r5 = r0.parentBottom
            int r4 = r4 + r5
        L_0x019c:
            int r4 = r4 - r3
        L_0x019d:
            int r5 = r4 + r3
            int r6 = r0.parentBottom
            if (r5 <= r6) goto L_0x01a6
            int r4 = r6 - r3
            goto L_0x01b6
        L_0x01a6:
            int r3 = r0.parentTop
            if (r4 >= r3) goto L_0x01b6
            r11 = r3
            goto L_0x01b7
        L_0x01ac:
            int r4 = r0.parentBottom
            int r4 = r4 - r3
            float r3 = (float) r11
            float r5 = r0.bottomPaddingFraction
            float r3 = r3 * r5
            int r3 = (int) r3
            int r4 = r4 - r3
        L_0x01b6:
            r11 = r4
        L_0x01b7:
            android.text.StaticLayout r12 = new android.text.StaticLayout
            android.text.TextPaint r5 = r0.textPaint
            float r8 = r0.spacingMult
            float r9 = r0.spacingAdd
            r10 = 1
            r3 = r12
            r4 = r1
            r6 = r20
            r7 = r21
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            r0.textLayout = r12
            android.text.StaticLayout r1 = new android.text.StaticLayout
            android.text.TextPaint r3 = r0.textPaint
            float r4 = r0.spacingMult
            float r5 = r0.spacingAdd
            r24 = 1
            r17 = r1
            r18 = r23
            r19 = r3
            r22 = r4
            r23 = r5
            r17.<init>(r18, r19, r20, r21, r22, r23, r24)
            r0.edgeLayout = r1
            r0.textLeft = r2
            r0.textTop = r11
            r1 = r25
            r0.textPaddingX = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.SubtitlePainter.setupTextLayout():void");
    }

    public final void draw(Cue cue, CaptionStyleCompat captionStyleCompat, float f, float f2, float f3, Canvas canvas, int i, int i2, int i3, int i4) {
        int i5;
        boolean z = cue.e == null;
        if (!z) {
            i5 = -16777216;
        } else if (!TextUtils.isEmpty(cue.b)) {
            i5 = cue.m ? cue.n : captionStyleCompat.windowColor;
        } else {
            return;
        }
        if (areCharSequencesEqual(this.cueText, cue.b) && Util.a((Object) this.cueTextAlignment, (Object) cue.c) && this.cueBitmap == cue.e && this.cueLine == cue.f && this.cueLineType == cue.g && Util.a((Object) Integer.valueOf(this.cueLineAnchor), (Object) Integer.valueOf(cue.h)) && this.cuePosition == cue.i && Util.a((Object) Integer.valueOf(this.cuePositionAnchor), (Object) Integer.valueOf(cue.j)) && this.cueSize == cue.k && this.cueBitmapHeight == cue.l && this.foregroundColor == captionStyleCompat.foregroundColor && this.backgroundColor == captionStyleCompat.backgroundColor && this.windowColor == i5 && this.edgeType == captionStyleCompat.edgeType && this.edgeColor == captionStyleCompat.edgeColor && Util.a((Object) this.textPaint.getTypeface(), (Object) captionStyleCompat.typeface) && this.defaultTextSizePx == f && this.cueTextSizePx == f2 && this.bottomPaddingFraction == f3 && this.parentLeft == i && this.parentTop == i2 && this.parentRight == i3 && this.parentBottom == i4) {
            drawLayout(canvas, z);
            return;
        }
        this.cueText = cue.b;
        this.cueTextAlignment = cue.c;
        this.cueBitmap = cue.e;
        this.cueLine = cue.f;
        this.cueLineType = cue.g;
        this.cueLineAnchor = cue.h;
        this.cuePosition = cue.i;
        this.cuePositionAnchor = cue.j;
        this.cueSize = cue.k;
        this.cueBitmapHeight = cue.l;
        this.foregroundColor = captionStyleCompat.foregroundColor;
        this.backgroundColor = captionStyleCompat.backgroundColor;
        this.windowColor = i5;
        this.edgeType = captionStyleCompat.edgeType;
        this.edgeColor = captionStyleCompat.edgeColor;
        this.textPaint.setTypeface(captionStyleCompat.typeface);
        this.defaultTextSizePx = f;
        this.cueTextSizePx = f2;
        this.bottomPaddingFraction = f3;
        this.parentLeft = i;
        this.parentTop = i2;
        this.parentRight = i3;
        this.parentBottom = i4;
        if (z) {
            Assertions.b((Object) this.cueText);
            setupTextLayout();
        } else {
            Assertions.b((Object) this.cueBitmap);
            setupBitmapLayout();
        }
        drawLayout(canvas, z);
    }
}