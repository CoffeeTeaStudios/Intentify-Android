package lawonga.intentify.view.fragment;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lawonga.intentify.R;
import lawonga.intentify.view.MainActivity;

/**
 * Created by Andy on 9/24/2016.
 * Simulates:
 *
 * 1) Bar going up
 * 2) Bar cashed in (resets bar)
 *
 * 3) Simulate a notification saying game has ended/end of round
 */
public class PresenterFragment extends Fragment{


    public static PresenterFragment getInstance(){
        PresenterFragment presenterFragment = new PresenterFragment();
        return presenterFragment;
    }

    // Required for singleton
    public PresenterFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_presenter, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.step_1)
    public void addATokenToBar(){

    }


    @OnClick(R.id.step_2)
    public void add15Tokens(){

    }

    @OnClick(R.id.step_3)
    public void resetBar(){

    }

    @OnClick(R.id.step_4)
    public void simulateNotification(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getActivity())
                .setSmallIcon(R.drawable.common_ic_googleplayservices)
                .setContentTitle("Dip has finished!")
                .setContentText("Click to view results!")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
