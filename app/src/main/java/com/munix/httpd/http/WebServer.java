package com.munix.httpd.http;

import android.content.Context;
import android.util.Log;

import com.munix.httpd.ffmpeg.FFmpegExecutor;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by munix on 9/12/15.
 */
public class WebServer extends NanoHTTPD {

    private Context context;
    private FFmpegExecutor mExecutor;
    private ArrayList<String> mCommands;
    private Process mCurrentProcess;
    private File ffmpegDirPath;
    private String url = "http://www.sample-videos.com/video/flv/720/big_buck_bunny_720p_50mb.flv";

    public WebServer( Context context ) {
        super( 8080 );
        this.context = context.getApplicationContext();
        ffmpegDirPath = new File( this.context.getFilesDir().getAbsolutePath() + "/ffmpeg" );

        Log.w( "Httpd", "Web server initialized." );
        this.stop();
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    @Override
    public Response serve( IHTTPSession session ) {

        if ( session.getUri().equals( "/stream" ) ) {
            try {
                return getStreamContent();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            new Response( Response.Status.NOT_FOUND, "text/html", "File not found" );
        }
        return getDefautlResponse();
    }

    private Response getDefautlResponse() {
        String url = "http://" + Utils.getLocalIpAddress( true ) + ":8080/stream";
        String body = "<html><body>";
        body += "<head><title>Munix web server</title>" +
                "<style>\n" +
                "body {background-color:lightgrey;font-family: arial;}\n" +
                "h1   {color:blue;}\n" +
                "</style>" +
                "</head>";
        body += "<h1>Munix web server</h1>";
        body += "Para ver un video de ejemplo servido con el webserver puedes añadir un stream en la app de tv a la url <a target='_blank' href='" + url + "'>" + url + "</a>";
        body += "<br>Esta url es capaz de verse desde el navegador del pc (no optimizado aun para movil), VLC o cualquier dispositivo puesto que acepta rangos para el multipart o descarga completa.";
        body += "</body></html>";
        Response response = new Response( Response.Status.OK, "text/html", body );
        response.addHeader( "Content-Type", "text/html; charset=UTF-8" );
        return response;
    }

    private Response getStreamContent() {
        mExecutor = new FFmpegExecutor( this.context, ffmpegDirPath.getAbsolutePath() + "/ffmpeg" );
        mExecutor.init();

        //.putCommand( "-headers" ).putCommand( "User-Agent:Lavf/55.19.104\r\n" )

        mExecutor.putCommand( "-i" ).putCommand( url ).putCommand( "-preset" ).putCommand( "ultrafast" ).putCommand( "-f" ).putCommand( "mp4" ).putCommand( "-b:a" ).putCommand( "64k" ).putCommand( "-frag_duration" ).putCommand( "1" ).putCommand( "-strict" ).putCommand( "-2" ).putCommand( "-b:v" ).putCommand( "1500k" ).putCommand( "-r" ).putCommand( "22" ).putCommand( "-rtbufsize" ).putCommand( "1000k" ).putCommand( "-bufsize" ).putCommand( "1000k" ).putCommand( "-v" ).putCommand( "0" ).putCommand( "-" );

        mCommands = mExecutor.getmCommands();
        try {
            mCurrentProcess = new ProcessBuilder( mCommands ).redirectErrorStream( false ).start();
            Response response = new Response( Response.Status.OK, "video/mkv", mCurrentProcess.getInputStream() );
            response.addHeader( "Connection", "keep-alive" );
            response.addHeader( "Transfer-Encoding", "chunked" );
            response.setChunkedTransfer( true );
            return response;
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return null;
    }
}