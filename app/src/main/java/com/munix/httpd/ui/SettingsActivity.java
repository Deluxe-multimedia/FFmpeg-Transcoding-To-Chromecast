package com.munix.httpd.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.munix.httpd.R;
import com.munix.httpd.utils.Constants;
import com.munix.httpd.utils.SharedPreferencesUtils;

/**
 * Created by munix on 18/12/2015.
 */
public class SettingsActivity extends AppCompatActivity {

    Spinner presets;
    Spinner audio;
    Spinner frames;
    Spinner video;
    Spinner container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setPresetsViews();
        setAudioViews();
        setFramesViews();
        setVideoViews();
        setContainerViews();
    }

    private void setPresetsViews() {
        presets = (Spinner) findViewById(R.id.presets);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.presets, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        presets.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(SharedPreferencesUtils.readSharedPreference(this, Constants.PREF_KEY_PRESET,Constants.PREF_VALUE_PRESET));
        presets.setSelection(spinnerPosition);
        presets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferencesUtils.writeSharedPreference(view.getContext(), Constants.PREF_KEY_PRESET, adapter.getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setAudioViews() {
        audio = (Spinner) findViewById(R.id.audio);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.audio, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        audio.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(SharedPreferencesUtils.readSharedPreference(this,Constants.PREF_KEY_AUDIO,Constants.PREF_VALUE_AUDIO));
        audio.setSelection(spinnerPosition);
        audio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferencesUtils.writeSharedPreference(view.getContext(), Constants.PREF_KEY_AUDIO, adapter.getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setFramesViews() {
        frames = (Spinner) findViewById(R.id.frames);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.frames, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frames.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(SharedPreferencesUtils.readSharedPreference(this,Constants.PREF_KEY_FRAMES,Constants.PREF_VALUE_FRAMES));
        frames.setSelection(spinnerPosition);
        frames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferencesUtils.writeSharedPreference(view.getContext(), Constants.PREF_KEY_FRAMES, adapter.getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setVideoViews() {
        video = (Spinner) findViewById(R.id.video);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.video, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        video.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(SharedPreferencesUtils.readSharedPreference(this,Constants.PREF_KEY_VIDEO,Constants.PREF_VALUE_VIDEO));
        video.setSelection(spinnerPosition);
        video.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferencesUtils.writeSharedPreference(view.getContext(), Constants.PREF_KEY_VIDEO, adapter.getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setContainerViews() {
        container = (Spinner) findViewById(R.id.container);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.container, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        container.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(SharedPreferencesUtils.readSharedPreference(this,Constants.PREF_KEY_CONTAINER,Constants.PREF_VALUE_CONTAINER));
        container.setSelection(spinnerPosition);
        container.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferencesUtils.writeSharedPreference(view.getContext(), Constants.PREF_KEY_CONTAINER, adapter.getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
