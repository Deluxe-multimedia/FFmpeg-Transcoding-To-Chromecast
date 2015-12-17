package com.munix.httpd.files;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Environment;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by munix on 9/12/15.
 */
public class FileUtils {

    public static void copyAssetToSdcard( Context context, String assetName ) {
        AssetManager am = context.getAssets();
        AssetFileDescriptor afd = null;
        try {
            afd = am.openFd( assetName );

            File file = new File( Environment.getExternalStorageDirectory() + java.io.File.separator + assetName );
            if ( !file.exists() || file.length() == 0 ) {
                file.createNewFile();
                copyFdToFile( afd.getFileDescriptor(), file );
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }


    public static void copyFdToFile( FileDescriptor src, File dst ) throws IOException {
        FileChannel inChannel = new FileInputStream( src ).getChannel();
        FileChannel outChannel = new FileOutputStream( dst ).getChannel();
        try {
            inChannel.transferTo( 0, inChannel.size(), outChannel );
        } finally {
            if ( inChannel != null )
                inChannel.close();
            if ( outChannel != null )
                outChannel.close();
        }
    }

    public static void copyRawToSdcard( Context context, int rawRes ) throws IOException {
        File outFile = new File( Environment.getExternalStorageDirectory() + java.io.File.separator + "sample.mp4" );
        if ( !outFile.exists() || outFile.length() == 0 ) {
            InputStream in = context.getResources().openRawResource( rawRes );
            OutputStream out = new FileOutputStream( outFile );

            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            try {
                // A little more explicit
                while ( ( len = in.read( buf, 0, buf.length ) ) != -1 ) {
                    out.write( buf, 0, len );
                }
            } finally {
                // Ensure the Streams are closed:
                in.close();
                out.close();
            }
        }
    }
}
