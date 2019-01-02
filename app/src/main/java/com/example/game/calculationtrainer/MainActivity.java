package com.example.game.calculationtrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button go , b0, b1 , b2 , b3;
    TextView sumtext , result , points , timer , ins;
    ArrayList<Integer> answers;
    int correctans;
    int score = 0;
    int numberofQuestion =0 ;

    public void generateQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21) + 1;
        int b = rand.nextInt(21) + 1;
        sumtext.setText(Integer.toString(a) + " + " + Integer.toString(b));

        correctans = rand.nextInt(4);
        int[] y1 = new int[4];
        int yc;
        for(int i=0;i<4;i++){
            if(i == correctans){
                y1[i] = a+b;
            }else {
                yc = rand.nextInt(2*a+2*b) + 1;

                while(true){
                    boolean flag = false;
                    for(int j1=0;j1<i;j1++){
                        if(yc == (a+b) || yc == y1[j1]) flag =true;
                    }
                    if(flag)
                        yc = rand.nextInt(2*a+2*b) + 1;
                    else
                        break;
                }
                y1[i] = yc;
            }
        }

        b0.setText(Integer.toString(y1[0]));
        b1.setText(Integer.toString(y1[1]));
        b2.setText(Integer.toString(y1[2]));
        b3.setText(Integer.toString(y1[3]));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go = (Button) findViewById(R.id.button);
        sumtext = (TextView)findViewById(R.id.textView3);
         b0 = (Button) findViewById(R.id.button2);
         b1 = (Button) findViewById(R.id.button3);
         b2 = (Button) findViewById(R.id.button4);
         b3 = (Button) findViewById(R.id.button5);
        result = (TextView)findViewById(R.id.textView5);
        points  = ( TextView) findViewById(R.id.textView4);
        timer = (TextView) findViewById(R.id.textView);
        ins = (TextView) findViewById(R.id.textView6);

        result.setText("Start");
        ins.setText("You have 30 seconds to solve as much problems as you can!");
        b0.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);
        go.setVisibility(View.VISIBLE);
        sumtext.setVisibility(View.INVISIBLE);
        go.setText("Go!");




    }

    public void start(View view) {
        score = 0;
        numberofQuestion =0 ;
        points.setText(Integer.toString(score)+"/"+Integer.toString(numberofQuestion));
        result.setText("Start");
        go.setVisibility(View.INVISIBLE);
        b0.setVisibility(View.VISIBLE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        b3.setVisibility(View.VISIBLE);
        go.setVisibility(View.INVISIBLE);
        ins.setVisibility(View.INVISIBLE);
        sumtext.setVisibility(View.VISIBLE);
        generateQuestion();
        new CountDownTimer(30100 , 1000){


            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText((int) (millisUntilFinished / 1000)  + "s");
            }

            @Override
            public void onFinish() {
                result.setText("Your Score is : " + Integer.toString(score)+"/"+Integer.toString(numberofQuestion));
                b0.setVisibility(View.INVISIBLE);
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
                go.setVisibility(View.VISIBLE);
                sumtext.setVisibility(View.INVISIBLE);
                ins.setVisibility(View.VISIBLE);
                ins.setText("Nice Try! Go on");
                ins.setTextSize(40);
                go.setText("Play Again");

            }
        }.start();

    }

    public void chooseans(View view) {

        if(view.getTag().toString().equals(Integer.toString(correctans))){
            score++;
            result.setText("Correct!");
            generateQuestion();
        } else {
            result.setText("Wrong!");
        }
        numberofQuestion++;
        points.setText(Integer.toString(score)+"/"+Integer.toString(numberofQuestion));
    }
}
