package org.csystem.libs.activitylib;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LibActivity extends AppCompatActivity {
    private EditText m_editTextText;

    private void init()
    {
        m_editTextText = findViewById(R.id.LIBMAINACTIVITY_EDITTEXT_TEXT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib);
        init();
    }

    public void onOkButtonClicked(View v)
    {
        String text = m_editTextText.getText().toString();

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}