package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int HOME_REQUEST_CODE = 1;
    private static final int AWAY_REQUEST_CODE = 2;

    private String homeTeam, awayTeam;
    private EditText etHomeTeam, etAwayTeam;
    private ImageView logoHomeTeam, logoAwayTeam;
    private Button btnNext;
    private Uri homeImg, awayImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity

        etHomeTeam = findViewById(R.id.home_team);
        etAwayTeam = findViewById(R.id.away_team);
        logoHomeTeam = findViewById(R.id.home_logo);
        logoAwayTeam = findViewById(R.id.away_logo);
        btnNext = findViewById(R.id.btn_team);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeTeam = etHomeTeam.getText().toString();
                awayTeam = etAwayTeam.getText().toString();

                if(homeImg!=null && awayImg!=null){
                    Intent i = new Intent(MainActivity.this, MatchActivity.class);
                    i.putExtra("KEY_NAME_HOME", homeTeam);
                    i.putExtra("KEY_NAME_AWAY", awayTeam);
                    i.putExtra("KEY_IMG_HOME", logoHomeTeam.toString());
                    i.putExtra("KEY_IMG_AWAY", logoAwayTeam.toString());
                    startActivity(i);
                }
                else {
                    Toast.makeText(MainActivity.this, "Masukkan logo terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logoHomeTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), HOME_REQUEST_CODE);
            }
        });

        logoAwayTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), AWAY_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            Log.d(TAG, "Pilih Gambar Dicancel");
            return;
        }
        else if (requestCode == HOME_REQUEST_CODE) {
            if (data != null){
                try {
                    Uri imageUri = data.getData();
                    homeImg = imageUri;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeImg);
                    logoHomeTeam.setImageBitmap(bitmap);
                }
                catch (IOException error) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_LONG).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
        else if (requestCode == AWAY_REQUEST_CODE) {
            if (data != null){
                try {
                    Uri image = data.getData();
                    awayImg = image;
                    Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayImg);
                    logoAwayTeam.setImageBitmap(bitmap2);
                }
                catch (IOException error) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_LONG).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
    }


}
