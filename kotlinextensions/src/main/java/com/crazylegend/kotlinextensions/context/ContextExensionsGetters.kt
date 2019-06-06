package com.crazylegend.kotlinextensions.context

import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.*
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.*
import android.content.Intent
import android.content.RestrictionsManager
import android.content.pm.LauncherApps
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.hardware.ConsumerIrManager
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.usb.UsbManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.media.projection.MediaProjectionManager
import android.media.session.MediaSessionManager
import android.media.tv.TvInputManager
import android.net.ConnectivityManager
import android.net.Uri
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.net.wifi.p2p.WifiP2pManager
import android.nfc.NfcManager
import android.os.*
import android.os.storage.StorageManager
import android.print.PrintManager
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.inputmethod.InputMethodManager
import android.view.textservice.TextServicesManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat


/**
 * Created by hristijan on 2/22/19 to long live and prosper !
 */

/**
 * Get inputManager for Context.
 */
inline val Context.inputManager: InputMethodManager?
    get() = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager

/**
 * Get notificationManager for Context.
 */
inline val Context.notificationManager: NotificationManager?
    get() = getSystemService(NOTIFICATION_SERVICE) as? NotificationManager

/**
 * Get keyguardManager for Context.
 */
inline val Context.keyguardManager: KeyguardManager?
    get() = getSystemService(KEYGUARD_SERVICE) as? KeyguardManager


/**
 * Get telephonyManager for Context.
 */
inline val Context.telephonyManager: TelephonyManager?
    get() = getSystemService(TELEPHONY_SERVICE) as? TelephonyManager


/**
 * Get devicePolicyManager for Context.
 */
inline val Context.devicePolicyManager: DevicePolicyManager?
    get() = getSystemService(DEVICE_POLICY_SERVICE) as? DevicePolicyManager


/**
 * Get connectivityManager for Context.
 */
inline val Context.connectivityManager: ConnectivityManager?
    get() = getSystemService(CONNECTIVITY_SERVICE) as? ConnectivityManager


/**
 *Get alarmManager for Context.
 */
inline val Context.alarmManager: AlarmManager?
    get() = getSystemService(ALARM_SERVICE) as? AlarmManager


/**
 * Get clipboardManager for Context.
 */
inline val Context.clipboardManager: ClipboardManager?
    get() = getSystemService(CLIPBOARD_SERVICE) as? ClipboardManager


/**
 * Get jobScheduler for Context.
 */
inline val Context.jobScheduler: JobScheduler?
    get() = getSystemService(JOB_SCHEDULER_SERVICE) as? JobScheduler


/**
 * Get notification for Context.
 */
inline fun Context.notification(body: NotificationCompat.Builder.() -> Unit, channelID: String): Notification {
    val builder = NotificationCompat.Builder(this, channelID)
    builder.body()
    return builder.build()
}


/**
 * Get color from resources
 */
fun Context.getCompatColor(@ColorRes colorInt: Int): Int =
    ContextCompat.getColor(this, colorInt)

/**
 * Get drawable from resources
 */
fun Context.getCompatDrawable(@DrawableRes drawableRes: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableRes)

/**
 * Get ActivityManager
 */
inline val Context.activityManager: ActivityManager
    get() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

/**
 * Get AppWidgetManager
 */
inline val Context.appWidgetManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.APPWIDGET_SERVICE) as AppWidgetManager

/**
 * Get InputMethodManager
 */
inline val Context.inputMethodManager: InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

/**
 * Get BluetoothManager
 */
inline val Context.bluetoothManager: BluetoothManager
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    get() = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

/**
 * Get AudioManager
 */
inline val Context.audioManager
    get() = getSystemService(Context.AUDIO_SERVICE) as AudioManager

/**
 * Get BatteryManager
 */
inline val Context.batteryManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.BATTERY_SERVICE) as BatteryManager

/**
 * Get CameraManager
 */
inline val Context.cameraManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.CAMERA_SERVICE) as CameraManager

/**
 * Get Vibrator
 */
inline val Context.vibrator
    get() = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator


/**
 * Dial a number
 */
fun Context.dial(tel: String?) = startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$tel")))

/**
 * Get if screen is in landscape mode
 */
val Context.isLandscape get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

/**
 * Get if screen is in portrait mode
 */
val Context.isPortrait get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT


/**
 * get Height of status bar
 */
val Context.getStatusBarHeight: Int get() {
    val resourceId = this.resources.getIdentifier("status_bar_height", "dimen", "android")
    return this.resources.getDimensionPixelSize(resourceId)
}

val Context.getDeviceUsableMemory: Long get() {
    val am = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val memoryInfo = ActivityManager.MemoryInfo()
    am.getMemoryInfo(memoryInfo)
    return memoryInfo.availMem / (1024 * 1024)
}

inline val Context.isFinishing: Boolean
    get() = (this as? Activity)?.isFinishing ?: false

inline val Context.isRtl: Boolean
    get() = resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL

val Context.isTablet: Boolean get()  {
    return this.resources.configuration.screenLayout and Configuration
        .SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
}

fun Context.getAnimatedVectorDrawable (@DrawableRes id: Int): AnimatedVectorDrawableCompat? {
    return AnimatedVectorDrawableCompat.create(this, id)
}



val Context.networkOperatorName: String?
    get() = telephonyManager?.networkOperatorName

val Context.accessibilityManager
    get() = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager?

val Context.accountManager
    get() = getSystemService(Context.ACCOUNT_SERVICE) as AccountManager?


val Context.appOpsManager
    @TargetApi(Build.VERSION_CODES.KITKAT)
    get() = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager?

val Context.captioningManager
    @TargetApi(Build.VERSION_CODES.KITKAT)
    get() = getSystemService(Context.CAPTIONING_SERVICE) as CaptioningManager?


val Context.consumerIrManager
    @TargetApi(Build.VERSION_CODES.KITKAT)
    get() = getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager?

val Context.displayManager
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    get() = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager?

val Context.downloadManager
    get() = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?

val Context.dropBoxManager
    get() = getSystemService(Context.DROPBOX_SERVICE) as DropBoxManager?


val Context.launcherApps
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.LAUNCHER_APPS_SERVICE) as LauncherApps?

val Context.layoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

val Context.locationManager
    get() = getSystemService(Context.LOCATION_SERVICE) as LocationManager?

val Context.mediaProjectionManager
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager?

val Context.mediaRouter
    get() = getSystemService(Context.MEDIA_ROUTER_SERVICE) as MediaRouter?

val Context.mediaSessionManager
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager?

val Context.nfcManager
    get() = getSystemService(Context.NFC_SERVICE) as NfcManager?

val Context.nsdManager
    get() = getSystemService(Context.NSD_SERVICE) as NsdManager?

val Context.powerManager
    get() = getSystemService(Context.POWER_SERVICE) as PowerManager?

val Context.printManager
    @TargetApi(Build.VERSION_CODES.KITKAT)
    get() = getSystemService(Context.PRINT_SERVICE) as PrintManager?

val Context.restrictionsManager
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.RESTRICTIONS_SERVICE) as RestrictionsManager?

val Context.searchManager
    get() = getSystemService(Context.SEARCH_SERVICE) as SearchManager?

val Context.sensorManager
    get() = getSystemService(Context.SENSOR_SERVICE) as SensorManager?

val Context.storageManager
    get() = getSystemService(Context.STORAGE_SERVICE) as StorageManager?

val Context.telecomManager
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.TELECOM_SERVICE) as TelecomManager?



val Context.textServicesManager
    get() = getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE) as TextServicesManager?

val Context.tvInputManager
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.TV_INPUT_SERVICE) as TvInputManager?

val Context.uiModeManager
    get() = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager?

val Context.usbManager
    get() = getSystemService(Context.USB_SERVICE) as UsbManager?

val Context.userManager
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    get() = getSystemService(Context.USER_SERVICE) as UserManager?

val Context.wallpaperManager
    @SuppressLint("ServiceCast")
    get() = getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager?

val Context.wifiP2pManager
    get() = getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager?

val Context.wifiManager
    get() = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?

val Context.windowManager
    get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager?

inline val Context.configuration: android.content.res.Configuration
    get() = resources.configuration

inline val Context.isOneHanded: Boolean get() {
    return isPortrait && configuration.smallestScreenWidthDp < 600
}


inline fun Context.isPermissionGranted(permission: String): Boolean =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

inline fun Context.arePermissionsGranted(vararg permissions: String): Boolean =
    permissions.all { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }
