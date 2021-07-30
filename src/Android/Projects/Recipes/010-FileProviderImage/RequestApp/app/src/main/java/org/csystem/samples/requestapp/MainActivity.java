package org.csystem.samples.requestapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final int ACTION_GET_IMAGE = 1;
    private ImageView m_imageViewBitmap;
    private Intent m_requestIntent;

    private void initViews()
    {
        m_imageViewBitmap = this.findViewById(R.id.MAINACTIVITY_IMAGEVIEW_IMAGE);
    }

    private void init()
    {
        this.initViews();
        m_requestIntent = new Intent("org.csystem.action.shareimage");
        m_requestIntent.setType("image/png");
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
        if (requestCode == ACTION_GET_IMAGE && resultCode == RESULT_OK) {
            try {
                Uri uri = data.getData();

                InputStream inputStream = getContentResolver().openInputStream(uri);
                m_imageViewBitmap.setImageBitmap(BitmapFactory.decodeStream(inputStream));

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
            this.startActivityForResult(m_requestIntent, ACTION_GET_IMAGE);
        }
        catch (Throwable ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}