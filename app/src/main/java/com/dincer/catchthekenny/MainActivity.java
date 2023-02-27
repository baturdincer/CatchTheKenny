package com.dincer.catchthekenny;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView score;
    int number;
    TextView time;
    Button button;
/*    Handler handler;
    Runnable run;*/

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = findViewById(R.id.score);
        number=0;
        time = findViewById(R.id.timer);
        button= findViewById(R.id.button2);
        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("Time Left: "+millisUntilFinished/1000);
                int randomX = new Random().nextInt(1000 - 0) + 0;
                int randomY = new Random().nextInt(1000 - 0) + 0;
                button.setX(randomX);
                button.setY(randomY);
            }

            @Override
            public void onFinish() {
                time.setText("Time Off");
                button.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Do you want to play again?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Restart
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game is over", Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
                Toast.makeText(MainActivity.this, "Your Score is "+number, Toast.LENGTH_SHORT).show();
                number=0;
                score.setText("Score: "+number );

            }
        }.start();

    }

    public void kenny(View view){
        number++;
        score.setText("Score: "+number );
    }


/*    public void generateNumber(){
        handler = new Handler();
        run = new Runnable() {
            @Override
            public void run() {
                int randomX = new Random().nextInt(1000 - 0) + 0;
                int randomY = new Random().nextInt(1000 - 0) + 0;
                handler.postDelayed(this, 900);
                button.setX(randomX);
                button.setY(randomY);
            }
        };
        handler.post(run);

    }*/
}