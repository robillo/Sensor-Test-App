package com.appbusters.robinkamboj.senseitall.ui.tool_activity.image_tools.filter.filter_adapter;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

public class FilterList {

    private static final String NONE = "NONE";
    private static final String TOON = "TOON";
    private static final String SEPIA = "SEPIA";
    private static final String CONTRAST = "CONTRAST";
    private static final String INVERT = "INVERT";
    private static final String PIXELATE = "PIXELATE";
    private static final String SKETCH = "SKETCH";
    private static final String SWIRL = "SWIRL";
    private static final String BRIGHTNESS = "BRIGHTNESS";
    private static final String DARKNESS = "DARKNESS";
//    private static final String KUWAHARA = "KUWAHARA";
    private static final String VIGNETTE = "VIGNETTE";

    public static List<FilterItem> filterItemsList = new ArrayList<>();

    static {
        filterItemsList.add(new FilterItem(NONE, null));
        filterItemsList.add(new FilterItem(TOON, new ToonFilterTransformation()));
        filterItemsList.add(new FilterItem(SEPIA, new SepiaFilterTransformation()));
        filterItemsList.add(new FilterItem(INVERT, new InvertFilterTransformation()));
        filterItemsList.add(new FilterItem(SKETCH, new SketchFilterTransformation()));
        filterItemsList.add(new FilterItem(VIGNETTE, new VignetteFilterTransformation()));
        filterItemsList.add(new FilterItem(PIXELATE, new PixelationFilterTransformation()));
        filterItemsList.add(new FilterItem(SWIRL, new SwirlFilterTransformation()));
        filterItemsList.add(new FilterItem(BRIGHTNESS, new BrightnessFilterTransformation(0.3f)));
        filterItemsList.add(new FilterItem(DARKNESS, new BrightnessFilterTransformation(-0.3f)));
        filterItemsList.add(new FilterItem(CONTRAST, new ContrastFilterTransformation(1.5f)));
//        filterItemsList.add(new FilterItem(KUWAHARA, new KuwaharaFilterTransformation()));
    }
}
