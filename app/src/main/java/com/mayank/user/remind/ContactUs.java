package com.mayank.user.remind;

/**
 * Created by user on 16-07-2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;


public class ContactUs extends Fragment {

    Handler handler;
    TextView mailAddress;

    Animation in;
    Animation out;

    boolean fadeOut,clicked;
    private int tick;

    public ContactUs() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        fadeOut = true;
        clicked = false;
        tick = 5;
        handler = new Handler();

        mailAddress = (TextView) view.findViewById(R.id.mailAddress);
        mailAddress.setTextColor(getResources().getColor(R.color.chrome));
        mailAddress.setText("mayanknarula96@gmail.com");

        in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(5000);
        in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (--tick == 0 && !clicked) {

                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fadeOut = true;
                mailAddress.setTextColor(getResources().getColor(R.color.chrome));
                mailAddress.setText("mayanknarula96@gmail.com");

                mailAddress.startAnimation(out);
                handler.postDelayed(mFadeOut, 5000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(5000);
        out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                fadeOut = false;
                mailAddress.setTextColor(getResources().getColor(R.color.colorAccent));
                mailAddress.setText("oasis9594@gmail.com");
                mailAddress.startAnimation(in);
                handler.postDelayed(mFadeOut, 5000);
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub
                if (tick < 0 && clicked) {

                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                }
            }
        });

        mailAddress.startAnimation(out);
        handler.postDelayed(mFadeOut, 5000);

        mailAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = true;
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"oasis9594@gmail.com", "mayanknarula96@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Write your Subject Here");
                email.putExtra(Intent.EXTRA_TEXT, "Give a brief description of your problem here.. ");

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }

        });
        clicked = false;
        return view;
    }

    private Runnable mFadeOut = new Runnable(){

        @Override
        public void run() {
            //Speed up the last fade-out so that the text comes faster
            if (fadeOut == true){
                out.setDuration(2000);
                mailAddress.startAnimation(out);
            } else {
                in.setDuration(2000);
                mailAddress.startAnimation(in);
            }
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("Activity Key", 4);
        super.onSaveInstanceState(outState);
    }



}
