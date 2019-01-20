package me.jesuscodes.bagel;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;

import java.util.UUID;

public class BagelUtility {
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static String projectName() {
        Context context = Bagel.instance.context;
        PackageManager packageManager = context.getPackageManager();
        try {
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA));
        } catch (PackageManager.NameNotFoundException e) {
        }
        return context.getPackageName();
    }

    public static String deviceId() {
        return deviceName() + "-" + deviceDescription();
    }

    public static String deviceName() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            return Settings.Secure.getString(Bagel.instance.context.getContentResolver(), Settings.Global.DEVICE_NAME);
        }
        return "Unknown";
    }

    public static String deviceDescription() {
        return Build.MANUFACTURER + Build.PRODUCT + "(" + Build.MODEL + ")" + " " + "android " + Build.VERSION.RELEASE;
    }


}
