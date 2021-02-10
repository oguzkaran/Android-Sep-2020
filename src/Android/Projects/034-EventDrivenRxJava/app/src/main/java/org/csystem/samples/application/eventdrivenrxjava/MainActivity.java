package org.csystem.samples.application.eventdrivenrxjava;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding4.view.RxView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private Disposable m_titleDisposable;
    private Disposable m_buttonIncrementDisposable;
    private void buttonIncrementOnNextCallback(TextView textView, int val) throws IOException
    {
        textView.setText(val + "");

        if (val == 10)
            throw new IOException("io error");
    }

    private void initViews()
    {
        TextView textViewCounter = this.findViewById(R.id.mainActivityTextViewCounter);
        Button buttonIncrement =  this.findViewById(R.id.mainActivityButtonIncrement);

        m_buttonIncrementDisposable = RxView.clicks(buttonIncrement).map(o -> 1)
                .scan(0, Integer::sum)
                .subscribe(i -> buttonIncrementOnNextCallback(textViewCounter, i), ex -> Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void init()
    {
        //...
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    @Override
    protected void onResume()
    {
        this.initViews();

        //Detaylar ileride anlatılacak
        m_titleDisposable = Observable.just("Merhaba Arkadaşlar").delay(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setTitle);
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        if (m_buttonIncrementDisposable != null && !m_buttonIncrementDisposable.isDisposed())
            m_buttonIncrementDisposable.dispose();

        if (m_titleDisposable != null && !m_titleDisposable.isDisposed())
            m_titleDisposable.dispose();

        super.onPause();
    }
}