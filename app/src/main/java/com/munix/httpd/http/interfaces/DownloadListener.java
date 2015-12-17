package com.munix.httpd.http.interfaces;

/**
 * Created by munix on 9/12/15.
 */
public interface DownloadListener {

    void updateProgress( int downloadedSize, int totalSize );
}
