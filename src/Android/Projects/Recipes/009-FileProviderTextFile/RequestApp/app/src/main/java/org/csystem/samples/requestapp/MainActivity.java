package org.csystem.samples.requestapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final int ACTION_GET_FILE = 1;
    private TextView m_textViewText;
    private Intent m_requestIntent;

    private void initViews()
    {
        m_textViewText = this.findViewById(R.id.MAINACTIVITY_TEXTVIEW_TEXT);
    }

    private void init()
    {
        this.initViews();
        m_requestIntent = new Intent("org.csystem.action.imageprovider");
        m_requestIntent.setType("text/plain");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (requestCode == ACTION_GET_FILE && resultCode == RESULT_OK) {
            try {
                Uri uri = data.getData();
                InputStream inputStream = getContentResolver().openInputStream(uri);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                m_textViewText.setText("");
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    m_textViewText.append(line);
                    m_textViewText.append("\n");
                }
            }
            catch (Throwable ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onOKButtonClicked(View view)
    {
        try {
            this.startActivityForResult(m_requestIntent, ACTION_GET_FILE);
        }
        catch (Throwable ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}