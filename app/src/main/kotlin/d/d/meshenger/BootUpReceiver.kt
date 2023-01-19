package d.d.meshenger

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast

/*
 * Start App on Android bootup. StartActivity is started to check
 * if a password for the database is need. the name and key-pair
 * is set.
 */
class BootUpReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent != null && intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val i = Intent(context, StartActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra(START_MAIN_ACTIVITY, false)
            context.startActivity(i)
        }
    }

    companion object {
        const val START_MAIN_ACTIVITY = "START_MAIN_ACTIVITY"

        fun setEnabled(context: Context, enabled: Boolean) {
            val newState = if (enabled) {
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            } else {
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED
            }

            try {
                context.packageManager
                    .setComponentEnabledSetting(
                        ComponentName(context, BootUpReceiver::class.java),
                        newState, PackageManager.DONT_KILL_APP)
            } catch (e: Exception) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
