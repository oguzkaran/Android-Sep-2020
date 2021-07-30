package org.csystem.samples.providerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private void allowShareFile()
    {
        try {
            int index = this.getIntent().getIntExtra("index", 0);
            Uri uri = ProviderApplication.getUris().get(index);

            Intent intent = new Intent("org.csystem.fileprovider.ACTION_RETURN_FILE");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, getContentResolver().getType(uri));
            intent.setData(uri);

            this.setResult(RESULT_OK, intent);
            this.finish();
        }
        catch (Throwable ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void sendCount()
    {
        Intent intent = new Intent();

        intent.putExtra("count", ProviderApplication.getUris().size());
        this.setResult(RESULT_OK, intent);
        this.finish();
    }

    private void init()
    {
        if (this.getIntent().getBooleanExtra("count", false))
            sendCount();
        else
            allowShareFile();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.init();
    }
}