package com.example.milib.asyntasks;

import android.os.AsyncTask;

/**
 * Created by eduardo.lafoz on 22/01/2018.
 */

public class HttpAsyncTask extends AsyncTask<String,Void,Void> {


    public HttpAsyncTask(){

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Void doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
