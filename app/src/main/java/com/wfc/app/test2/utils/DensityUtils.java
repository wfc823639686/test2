package com.wfc.app.test2.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * @author 宋正朋
 * @date 2016-3-28
 * @time 下午4:26:55 像素转换单位
 */
public class DensityUtils {
    /**
     * @param context
     * @param dpValue
     * @return 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static float dip2px2(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return  (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param context （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * @param context
     * @return 获取手机屏幕宽度
     */
    @SuppressWarnings("deprecation")
    public static int getScreenWidthSize(Context context) {
        WindowManager window = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = window.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * @param context
     * @return 获取手机屏幕高度
     */
    @SuppressWarnings("deprecation")
    public static int getScreenHeightSize(Context context) {
        WindowManager window = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int height = window.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * @return 获取手机设备信息
     */
    @SuppressWarnings("deprecation")
    public static String getPhoneDevice() {
        // 手机厂商
        String phoneManufacturer = android.os.Build.MANUFACTURER;
        // 手机型号
        String phoneModel = android.os.Build.MODEL;
        // 系统版本
        String systemVersion = android.os.Build.VERSION.RELEASE;
        // SDK版本
        String sdkVersion = android.os.Build.VERSION.SDK;
        return phoneManufacturer + "---" + phoneModel + "---" + systemVersion
                + "---" + sdkVersion;
    }
}
