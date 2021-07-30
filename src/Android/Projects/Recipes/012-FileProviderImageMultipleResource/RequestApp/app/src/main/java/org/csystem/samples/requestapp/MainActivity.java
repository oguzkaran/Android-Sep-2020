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
    private static final boolean DEBUG = false;
    private static final int ACTION_GET_IMAGE = 1;
    private static final int ACTION_GET_IMAGE_COUNT = 2;
    private ImageView m_imageViewBitmap;
    private int m_index = 0;
    private int m_count;

    private void initViews()
    {
        m_imageViewBitmap = this.findViewById(R.id.MAINACTIVITY_IMAGEVIEW_IMAGE);
    }

    private void init()
    {
        this.initViews();
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
        if (resultCode == RESULT_OK) {
            if (requestCode == ACTION_GET_IMAGE) {
                try {
                    if (DEBUG) {
                        assert data != null;
                    }
                    Uri uri = data.getData();

                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    m_imageViewBitmap.setImageBitmap(BitmapFactory.decodeStream(inputStream));
                } catch (Throwable ex) {
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            else if (requestCode == ACTION_GET_IMAGE_COUNT) {
                assert !DEBUG || data != null: "....";
                m_count = data.getIntExtra("count", 0);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onGetImageCountButtonClicked(View view)
    {
        try {
            Intent intent = new Intent("org.csystem.action.shareimagecount");

            intent.putExtra("count", true);

            this.startActivityForResult(intent, ACTION_GET_IMAGE_COUNT);
        }
        catch (Throwable ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void onGetImageButtonClicked(View view)
    {
        try {
            if (m_count == 0)
                return;

            m_index %= m_count;
            Intent intent = new Intent("org.csystem.action.shareimage");

            intent.setType("image/png");
            intent.putExtra("index", m_index++);
            this.startActivityForResult(intent, ACTION_GET_IMAGE);
        }
        catch (Throwable ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}