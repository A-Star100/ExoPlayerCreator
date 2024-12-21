package com.google.appinventor.components.runtime.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class LineWithTrendlineRenderer extends LineChartRenderer {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = LineWithTrendlineRenderer.class.getSimpleName();

    public LineWithTrendlineRenderer(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    public void drawData(Canvas c) {
        for (ILineDataSet dataSet : this.mChart.getLineData().getDataSets()) {
            if (dataSet.isVisible() && (dataSet instanceof HasTrendline)) {
                drawTrendline(c, dataSet);
            }
        }
        LineWithTrendlineRenderer.super.drawData(c);
    }

    /* access modifiers changed from: protected */
    public void drawTrendline(Canvas c, ILineDataSet dataSet) {
        if (dataSet instanceof HasTrendline) {
            Transformer trans = this.mChart.getTransformer(dataSet.getAxisDependency());
            HasTrendline<?> hasTrendline = (HasTrendline) dataSet;
            if (hasTrendline.isVisible()) {
                Paint p = new Paint();
                p.setStyle(Paint.Style.STROKE);
                p.setStrokeWidth(hasTrendline.getLineWidth());
                p.setColor(hasTrendline.getColor());
                p.setAlpha((hasTrendline.getColor() >> 24) & 255);
                p.setPathEffect(hasTrendline.getDashPathEffect());
                float[] lineBuffer = hasTrendline.getPoints(this.mChart.getXChartMin(), this.mChart.getXChartMax(), this.mChart.getWidth());
                trans.pointValuesToPixel(lineBuffer);
                c.drawLines(lineBuffer, 0, lineBuffer.length, p);
            }
        }
    }
}