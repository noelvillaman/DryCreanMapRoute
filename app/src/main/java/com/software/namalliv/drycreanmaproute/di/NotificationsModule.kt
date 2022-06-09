package com.software.namalliv.drycreanmaproute.di


import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.software.namalliv.drycreanmaproute.MainActivity
import com.software.namalliv.drycreanmaproute.R
import com.software.namalliv.drycreanmaproute.util.Constants.ACTION_NAVIGATE_TO_MAPS_FRAGMENT
import com.software.namalliv.drycreanmaproute.util.Constants.NOTIFICATION_CHANNEL_ID
import com.software.namalliv.drycreanmaproute.util.Constants.PERDING_INTENT_REQEST_CODE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
object NotificationsModule {

    @ServiceScoped
    @Provides
    fun providePendingIntennt(
        @ApplicationContext context: Context
    ): PendingIntent {
        return PendingIntent.getActivity(
            context, PERDING_INTENT_REQEST_CODE,
            Intent(context, MainActivity::class.java).apply {
                this.action = ACTION_NAVIGATE_TO_MAPS_FRAGMENT
            },
            PendingIntent.FLAG_IMMUTABLE or
                    PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    @ServiceScoped
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context,
        pendingIntent: PendingIntent
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_iron)
            .setContentIntent(pendingIntent)
    }

    @ServiceScoped
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
}