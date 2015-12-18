package com.munix.httpd.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.munix.httpd.R;
import com.munix.httpd.ffmpeg.FileMover;
import com.munix.httpd.utils.HttpUtils;
import com.munix.httpd.http.WebServer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private WebServer server;
    private TextView serverStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serverStatus = (TextView) findViewById(R.id.serverStatus);

        server = new WebServer(getApplicationContext());

        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                try {
                    setStartedServer("Servidor web iniciado correctamente. Escoge un canal para empezar a emitir");
                } catch (Exception ioe) {
                    Log.w("Httpd", "The server could not start.");
                    setStoppedServer("El servidor no se ha podido iniciar :(");
                }
            }
        };

        new Thread() {
            @Override
            public void run() {
                try {

                    File ffmpegDirPath = new File(getApplicationContext().getFilesDir().getAbsolutePath() + "/ffmpeg");
                    if (!ffmpegDirPath.exists()) {
                        ffmpegDirPath.mkdir();
                    }

                    try {
                        InputStream ffmpegInputStream = getApplicationContext().getAssets().open("ffmpeg");
                        FileMover fm = new FileMover(ffmpegInputStream, ffmpegDirPath.getAbsolutePath() + "/ffmpeg");
                        fm.moveIt();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        String[] args = {"/system/bin/chmod", "755", ffmpegDirPath.getAbsolutePath() + "/ffmpeg"};
                        Process process = new ProcessBuilder(args).start();
                        try {
                            process.waitFor();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        process.destroy();
                        server.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(1);
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (server != null)
            server.stop();
    }

    private void setStoppedServer(String msg) {
        serverStatus.setText(msg);
        serverStatus.setTextColor(Color.RED);
    }

    private void setStartedServer(String msg) {
        serverStatus.setText(msg);
        serverStatus.setTextColor(Color.parseColor("#59D437"));
    }

    public void changeSettings(View view) {
        startActivity( new Intent( this, SettingsActivity.class ) );
    }


    public void stopServer(View view) {
        if (server != null)
            server.stop();

        setStoppedServer("Servidor detenido");
    }

    public void startServer(View view) {
        String url = ((EditText) findViewById(R.id.canalLibre)).getText().toString();

        if (TextUtils.isEmpty(url)) {
            Toast.makeText(this, "La url no puede estar vacía", Toast.LENGTH_LONG).show();
            return;
        }


        try {
            server.stop();
            server.start();
            server.setUrl(url);
            setStartedServer("Servidor iniciado y listo para servir la url " + url + " en\nhttp://" + HttpUtils.getLocalIpAddress(true) + ":8080/stream\nPuedes abrir esta url en VLC");
        } catch (IOException e) {
            e.printStackTrace();
            setStoppedServer("El servidor no se ha podido iniciar");
        }
    }
}
