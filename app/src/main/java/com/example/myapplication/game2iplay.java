package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class game2iplay extends AppCompatActivity {

    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7,iv8,iv9,iv10,iv11,iv12;
    TextView textView;
    int[] card = { R.drawable.front1, R.drawable.front1, R.drawable.front2,
            R.drawable.front2, R.drawable.front3, R.drawable.front3, R.drawable.front4, R.drawable.front4, R.drawable.front5, R.drawable.front5, R.drawable.front6, R.drawable.front6 };
    boolean[] bool = { false, false, false, false, false, false,false, false, false, false, false, false };
    Timer timer = new Timer();
    int flop1, flop2, btncount = 0, time = 0, end = 0;
    ImageView fiv1, fiv2;
    MediaPlayer mp = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //skip
        Intent intent = new Intent(game2iplay.this, game2win.class);
        startActivity(intent);
        setContentView(R.layout.activity_game2iplay);
        textView = findViewById(R.id.textView);
        iv1 =  findViewById(R.id.imageView1);
        iv2 =  findViewById(R.id.imageView2);
        iv3 =  findViewById(R.id.imageView3);
        iv4 =  findViewById(R.id.imageView4);
        iv5 =  findViewById(R.id.imageView5);
        iv6 =  findViewById(R.id.imageView6);
        iv7 =  findViewById(R.id.imageView7);
        iv8 =  findViewById(R.id.imageView8);
        iv9 =  findViewById(R.id.imageView9);
        iv10 =  findViewById(R.id.imageView10);
        iv11 =  findViewById(R.id.imageView11);
        iv12 =  findViewById(R.id.imageView12);

        for (int i = 0; i < 10000; i++) {
            int shuffle = (int) (Math.random() * 12);
            int c = card[shuffle];
            card[shuffle] = card[0];
            card[0] = c;
        }

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }, 0, 200);

        iv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv1, 0);
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv2, 1);
            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv3, 2);
            }
        });

        iv4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv4, 3);
            }
        });

        iv5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv5, 4);
            }
        });

        iv6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv6, 5);
            }
        });
        iv7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv7, 6);
            }
        });
        iv8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv8, 7);
            }
        });
        iv9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv9, 8);
            }
        });
        iv10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv10, 9);
            }
        });
        iv11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv11, 10);
            }
        });
        iv12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv12, 11);
            }
        });

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment()).commit();
//        }
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 1) {
                setTitle("遊戲時間：" + (time++) / 5 + " s ");
                textView.setText("遊戲時間：" + (time++) / 5 + " s ");

                if (btncount == 3) {
                    if (card[flop1] == card[flop2]) {
                        fiv1.setVisibility(View.INVISIBLE);
                        fiv2.setVisibility(View.INVISIBLE);
                        end++;
                        mp = MediaPlayer
                                .create(game2iplay.this, R.raw.o);
                        if (card.length / 2 == end) {
                            timer.cancel();
                            //跳轉在這裡做
                            //跳轉在這裡做
                            //跳轉在這裡做
                            Intent intent = new Intent(game2iplay.this, game2win.class);
                            startActivity(intent);
                            //跳轉在這裡做
                            //跳轉在這裡做
                            //跳轉在這裡做
//                            setTitle("遊戲結束!! 記錄：" + time / 5 + " s ");
//                            textView.setText("遊戲結束!! 記錄：" + time / 5 + " s ");
                        }
                    } else {
                        fiv1.setEnabled(true);
                        fiv1.setImageResource(R.drawable.back);
                        fiv2.setImageResource(R.drawable.back);
                        bool[flop1] = false;
                        bool[flop2] = false;
                        mp = MediaPlayer
                                .create(game2iplay.this, R.raw.x);
                    }
                    mp.start();
                    btncount = 0;
                    iv1.setEnabled(true);
                    iv2.setEnabled(true);
                    iv3.setEnabled(true);
                    iv4.setEnabled(true);
                    iv5.setEnabled(true);
                    iv6.setEnabled(true);
                    iv7.setEnabled(true);
                    iv8.setEnabled(true);
                    iv9.setEnabled(true);
                    iv10.setEnabled(true);
                    iv11.setEnabled(true);
                    iv12.setEnabled(true);
                } else if (btncount >= 2)
                    btncount++;
            }
        }

    };

    private void animation(final ImageView img, final int ivValue) {
        Animation animation = AnimationUtils.loadAnimation(game2iplay.this,
                R.anim.back);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                // TODO Auto-generated method stub
                if (!bool[ivValue]) {
                    img.setImageResource(card[ivValue]);
                    bool[ivValue] = true;
                }
                img.startAnimation(AnimationUtils.loadAnimation(
                        game2iplay.this, R.anim.front));

                btncount++;
                if (btncount == 1) {
                    fiv1 = img;
                    flop1 = ivValue;
                    img.setEnabled(false);
                }
                else if (btncount == 2) {
                    fiv2 = img;
                    flop2 = ivValue;
                    iv1.setEnabled(false);
                    iv2.setEnabled(false);
                    iv3.setEnabled(false);
                    iv4.setEnabled(false);
                    iv5.setEnabled(false);
                    iv6.setEnabled(false);
                    iv7.setEnabled(false);
                    iv8.setEnabled(false);
                    iv9.setEnabled(false);
                    iv10.setEnabled(false);
                    iv11.setEnabled(false);
                    iv12.setEnabled(false);
                }
            }
        });
        img.startAnimation(animation);
    }

//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container,
//                    false);
//            return rootView;
//        }
//    }


}