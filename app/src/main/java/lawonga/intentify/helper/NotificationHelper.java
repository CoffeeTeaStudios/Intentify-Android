package lawonga.intentify.helper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import lawonga.intentify.view.MainActivity;
import lawonga.intentify.R;

/**
 * Created by Andy on 9/23/2016.
 */
public class NotificationHelper {
    private static NotificationHelper ourInstance = new NotificationHelper();
    private Context context;
    private int notificationid = 123;

    public NotificationHelper initInstance(Context context) {
        this.context = context;
        return ourInstance;
    }

    public static NotificationHelper getInstance() {
        return ourInstance;
    }

    private NotificationHelper() {
    }

    public void triggerNotification(String title, String message) {
        // Notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                        .setContentTitle(title)
                        .setContentText(message);

        // Notification's action
        Intent resultIntent = new Intent(context, MainActivity.class); // Where we go to when we click on the notification
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        // Set it so we will use the notificaiton's action on click
        mBuilder.setContentIntent(resultPendingIntent);


        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Builds the notification and issues it.
        mNotifyMgr.notify(notificationid, mBuilder.build());
    }
}
