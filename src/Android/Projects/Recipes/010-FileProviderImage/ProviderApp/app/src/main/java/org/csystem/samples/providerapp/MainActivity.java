package org.csystem.samples.providerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.csystem.android.util.bitmap.BitmapUtil;
import org.csystem.net.http.HttpConnection;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private File m_file;
    private Intent m_intent;

    private class DownloadTask extends AsyncTask<String, String, Optional<Bitmap>> {
        @Override
        protected Optional<Bitmap> doInBackground(String... strings)
        {
            try (HttpConnection httpConnection = new HttpConnection(strings[0])){
                return Optional.of(BitmapUtil.createBitmap(httpConnection.getInputStream()));
            }
            catch (Throwable ex) {
                publishProgress(ex.getMessage());
            }

            return Optional.empty();
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Optional<Bitmap> optionalBitmap)
        {
            try {
                FileOutputStream fos = new FileOutputStream(m_file);
                optionalBitmap.ifPresent(b -> b.compress(Bitmap.CompressFormat.PNG, 100, fos));
            }
            catch (Throwable ex) {
                Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void allowShareFile()
    {
        Uri fileUri = FileProvider.getUriForFile(this, "org.csystem.shareimage", m_file);

        m_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        m_intent.setDataAndType(fileUri, getContentResolver().getType(fileUri));
        m_intent.setData(fileUri);
        this.setResult(RESULT_OK, m_intent);
        this.finish();
    }

    private void addNumbersToFile()
    {
        new DownloadTask().execute("https://i.ytimg.com/vi/YzrAHP0TovY/maxresdefault.jpg");
    }

    private void initFile()
    {
        try {
            m_file = new File(this.getFilesDir(), "shared_images/test.png");
            m_file.getParentFile().mkdirs();
            addNumbersToFile();
        }
        catch (Throwable ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void init()
    {
        this.initFile();
        m_intent = new Intent("org.csystem.fileprovider.ACTION_RETURN_FILE");
        allowShareFile();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.init();
    }
}