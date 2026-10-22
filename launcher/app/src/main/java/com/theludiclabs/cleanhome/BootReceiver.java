package com.theludiclabs.cleanhome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/** Best-effort cold-boot entry point for Fire OS versions that permit apps to launch after boot. */
public final class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) return;
        Intent home = new Intent(context, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(home);
    }
}
