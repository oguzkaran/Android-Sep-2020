package org.csystem.compileannotationsample;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.csystem.android.compilerannotation.entity.CardInfo;
import org.csystem.android.compilerannotation.entity.CardInfoBuilder;
import org.csystem.compilerannotation.entity.Device;
import org.csystem.compilerannotation.entity.DeviceBuilder;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    private TextView m_textViewText;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Device device = new DeviceBuilder()
                .setId(1)
                .setName("test")
                .setHost("192.168.2.145")
                .build();

        Toast.makeText(this, device.toString(), Toast.LENGTH_LONG).show();

        CardInfo cardInfo = new CardInfoBuilder()
                .setId(1)
                .setOwnerName("My Card")
                .setExpiryDate(LocalDate.now())
                .build();

        Toast.makeText(this, cardInfo.toString(), Toast.LENGTH_LONG).show();
    }
}