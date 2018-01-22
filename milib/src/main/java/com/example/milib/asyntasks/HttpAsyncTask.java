package com.example.milib.asyntasks;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by eduardo.lafoz on 22/01/2018.
 */

public class HttpAsyncTask extends AsyncTask<String,Integer,String> {


    public HttpAsyncTask(){

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... urls) {
        int count;
        String pathfin=null;
        try {
            String root = Environment.getExternalStorageDirectory().toString();



            this.publishProgress(10);
            System.out.println("Downloading");
            URL url = new URL(urls[0]);

            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file
            this.publishProgress(20);

            pathfin=root+"/downloadedfile.jpg";
            OutputStream output = new FileOutputStream(pathfin);
            byte data[] = new byte[1024];
            int contador=0;
            long total = 0;
            this.publishProgress(30);


            while ((count = input.read(data)) != -1) {
                total += count;
                contador=contador+5;
                this.publishProgress(contador);
                // writing data to file
                output.write(data, 0, count);

            }
            this.publishProgress(100);
            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return pathfin;

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String in) {
        super.onPostExecute(in);
    }
}
