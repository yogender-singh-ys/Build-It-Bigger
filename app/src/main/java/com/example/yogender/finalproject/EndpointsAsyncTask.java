package com.example.yogender.finalproject;

import android.content.Context;
import android.os.AsyncTask;


import com.example.yogenders.myapplication.backend.noteApi.NoteApi;
import com.example.yogenders.myapplication.backend.noteApi.model.Note;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.List;

/**
 * Created by yogenders on 8/12/16.
 */
class EndpointsAsyncTask extends AsyncTask<String, Void, List<Note>> {
    private static NoteApi myApiService = null;
    private Context context;
    private OnJokeLoad jokeLoadListner;

    public EndpointsAsyncTask(OnJokeLoad jokeLoadListner) {
        this.jokeLoadListner = jokeLoadListner;
    }

    @Override
    protected List<Note> doInBackground(String... params) {

       // Log.e("Error",params[0].second);

        if(myApiService == null) {  // Only do this once
            NoteApi.Builder builder = new NoteApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(params[0])
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }


        try {


            return myApiService.list().execute().getItems();

        } catch (IOException e) {
            return null;
        }
    }


    @Override
    protected void onPostExecute(List<Note> noteList) {

        // Setup Intent Extra to be shared with Android Library

        // displayed only first joke desc
        Note note = noteList.get(0);

        String str = "Joke Desc ::" + note.getTitle();

        jokeLoadListner.jokeLoadTaskHandler(str);

    }
}
