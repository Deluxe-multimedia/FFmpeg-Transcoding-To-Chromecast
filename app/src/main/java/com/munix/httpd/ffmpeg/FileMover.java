package com.munix.httpd.ffmpeg;

import android.content.Context;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by munix on 10/12/15.
 */
public class FileMover {

    InputStream inputStream;
    String destination;

    public FileMover( InputStream _inputStream, String _destination ) {
        inputStream = _inputStream;
        destination = _destination;
    }

    public static String getBinPath( Context context ) {
        return "/data/data/" + context.getPackageName() + "/";
    }

    public void moveIt() throws IOException {

        File destinationFile = new File( destination );
        OutputStream destinationOut = new BufferedOutputStream( new FileOutputStream( destinationFile ) );

        int numRead;
        byte[] buf = new byte[1024];
        while ( ( numRead = inputStream.read( buf ) ) >= 0 ) {
            destinationOut.write( buf, 0, numRead );
        }

        destinationOut.flush();
        destinationOut.close();
    }
}
