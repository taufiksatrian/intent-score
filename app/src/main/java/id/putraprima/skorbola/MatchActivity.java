package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private String sHomeTeam, sAwayTeam, sWinner;
    private TextView tvScoreHome, tvScoreAway, tvHomeText, tvAwayText;
    private ImageView imgLogoHome, imgLogoAway;
    private int homeScore, awayScore;
    private Button btnAddHome1, btnAddHome2, btnAddHome3, btnAddAway1, btnAddAway2, btnAddAway3;
    private Button btnCekResult, btnResetResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"

        tvHomeText = findViewById(R.id.txt_home);
        tvAwayText = findViewById(R.id.txt_away);
        tvScoreHome = findViewById(R.id.score_home);
        tvScoreAway = findViewById(R.id.score_away);
        imgLogoHome = findViewById(R.id.home_logo);
        imgLogoAway = findViewById(R.id.away_logo);
        btnAddHome1 = findViewById(R.id.btn_add_home);
        btnAddHome2 = findViewById(R.id.btn_add_home2);
        btnAddHome3 = findViewById(R.id.btn_add_home3);
        btnAddAway1 = findViewById(R.id.btn_add_away);
        btnAddAway2 = findViewById(R.id.btn_add_away2);
        btnAddAway3 = findViewById(R.id.btn_add_away3);
        btnCekResult = findViewById(R.id.btn_result);
        btnResetResult =  findViewById(R.id.btn_resetresult);

        homeScore = 0;
        awayScore = 0;
        tvScoreHome.setText(String.valueOf(homeScore));
        tvScoreAway.setText(String.valueOf(awayScore));

        Bundle bundle = getIntent().getExtras();
        sHomeTeam = bundle.getString("KEY_NAME_HOME");
        tvHomeText.setText(sHomeTeam);
        sAwayTeam = bundle.getString("KEY_NAME_AWAY");
        tvAwayText.setText(sAwayTeam);
        imgLogoHome.setImageURI(Uri.parse(bundle.getString("KEY_IMG_HOME")));
        imgLogoAway.setImageURI(Uri.parse(bundle.getString("KEY_IMG_AWAY")));

        btnAddHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore += 1;
                tvScoreHome.setText(String.valueOf(homeScore));
            }
        });

        btnAddHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore += 2;
                tvScoreHome.setText(String.valueOf(homeScore));
            }
        });

        btnAddHome3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore += 1;
                tvScoreHome.setText(String.valueOf(homeScore));
            }
        });

        btnAddAway1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayScore += 1;
                tvScoreAway.setText(String.valueOf(awayScore));
            }
        });

        btnAddAway2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayScore += 2;
                tvScoreAway.setText(String.valueOf(awayScore));
            }
        });

        btnAddAway3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore += 3;
                tvScoreAway.setText(String.valueOf(awayScore));
            }
        });

        btnCekResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sWinner = "empty";
                if (homeScore > awayScore){
                    sWinner = sHomeTeam;
                }
                else if (homeScore == awayScore){
                    sWinner = "draw";
                }
                else {
                    sWinner = sAwayTeam;
                }

                Intent i = new Intent(MatchActivity.this, ResultActivity.class);
                i.putExtra("KEY_WINNER", sWinner);
                startActivity(i);
            }
        });

        btnResetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore =0 ;
                tvScoreHome.setText(String.valueOf(homeScore));

                awayScore = 0;
                tvScoreAway.setText(String.valueOf(awayScore));

            }
        });
    }
}
