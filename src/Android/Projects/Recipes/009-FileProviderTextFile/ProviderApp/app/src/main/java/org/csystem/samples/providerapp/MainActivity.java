package org.csystem.samples.providerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private File m_file;
    private Intent m_intent;

    private void allowShareFile()
    {
        Uri fileUri = FileProvider.getUriForFile(this, "org.csystem.fileprovider", m_file);

        m_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        m_intent.setDataAndType(fileUri, getContentResolver().getType(fileUri));
        m_intent.setData(fileUri);
        this.setResult(RESULT_OK, m_intent);
        this.finish();
    }

    private void addNumbersToFile()
    {
        Random r = new Random();

        try (PrintWriter printWriter = new PrintWriter(m_file)) {
            for (int i = 0; i < 10; ++i)
                printWriter.append(r.nextInt(100) + "\n");
        }
        catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void initFile()
    {
        try {
            m_file = new File(this.getFilesDir(), "shared_images/text.txt");
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