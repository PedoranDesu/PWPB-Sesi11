package com.pedoran.pwpbsesi11.SpeechToText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pedoran.pwpbsesi11.R;

import java.util.ArrayList;
import java.util.Locale;

public class SupeechToTeksuto extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    TextView tv_voiceInput;
    ImageButton mSpeakBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supeech_to_teksuto);

        tv_voiceInput = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVoiceInput();
            }
        });
    }

    private void startVoiceInput(){
        Intent voiceInput = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voiceInput.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voiceInput.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        voiceInput.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hi, What Can I Help You?");

        try{
            startActivityForResult(voiceInput,REQ_CODE_SPEECH_INPUT);
        }catch (ActivityNotFoundException a){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT :
                if(resultCode == RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tv_voiceInput.setText(result.get(0));
                }
                break;
        }
    }
}
