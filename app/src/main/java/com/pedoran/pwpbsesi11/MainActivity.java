package com.pedoran.pwpbsesi11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pedoran.pwpbsesi11.SpeechToText.SupeechToTeksuto;
import com.pedoran.pwpbsesi11.StopWatch.SutoppuWatch;

public class MainActivity extends AppCompatActivity {
    Button lat1,lat2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentButton(lat1, SutoppuWatch.class,R.id.btn_lat1);
        intentButton(lat2, SupeechToTeksuto.class,R.id.btn_lat2);
    }

    public void intentButton(Button v, final Class target, int id_button){
        v = (Button) findViewById(id_button);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MainActivity.this,target);
                startActivity(pindah);
            }
        });
    }

}
