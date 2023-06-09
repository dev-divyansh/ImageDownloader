package com.divyansh.imagedownloader

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import java.sql.Time
import java.util.Date
import java.util.UUID

// in this class we have the logic to download the images

class downloader (uri : String? , context : Context){

    private var uri : String? = ""

    init {


        this.uri = uri

        val fileName = UUID.randomUUID().toString() + Date()

        val request = DownloadManager.Request(Uri.parse(uri))
            .setTitle("Image Download")
            .setDescription("downloading an image")
            .setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, fileName)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)


        var downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)

    }

}