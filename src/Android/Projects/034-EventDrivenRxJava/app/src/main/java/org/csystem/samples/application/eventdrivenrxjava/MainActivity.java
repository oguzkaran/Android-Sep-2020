package org.csystem.samples.application.eventdrivenrxjava;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding4.view.RxView;
import com.jakewharton.rxbinding4.widget.RxTextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private TextView m_textViewMessage;
    private Disposable m_titleDisposable;

    private void onMessageError(Throwable ex)
    {
        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        //...
    }

    private void buttonIncrementOnNextCallback(TextView textView, int val) throws IOException
    {
        textView.setText(String.valueOf(val));

        if (val == 10)
            throw new IOException("io error");
    }

    private void updateMessageTextView(CharSequence text)
    {
        m_textViewMessage.setText(text);
        if (text.equals("deneme"))
            throw new IllegalArgumentException("Illegal Text"); //Dikkat bu işlemden sonra artık bu metot çağrılamayacak
    }

    private void initMessageViews()
    {
        m_textViewMessage = this.findViewById(R.id.mainActivityTextViewMessage);
        EditText editTextMessage = this.findViewById(R.id.mainActivityEditTextMessage);
        TextView textViewUpperMessage = this.findViewById(R.id.mainActivityTextViewUpperMessage);

        Observable<CharSequence> textChangesObservable =  RxTextView.textChanges(editTextMessage)
                .doOnNext(t -> m_textViewMessage.setText("sınırlar dışında kaldınız"))
                .doOnNext(t -> textViewUpperMessage.setText("SINIRLAR DIŞINDA KALDINIZ"))
                .filter(t -> t.length() > 3)
                .filter(t -> t.length() < 10);


        textChangesObservable
                .map(CharSequence::toString)
                .doOnNext(this::updateMessageTextView)
                .map(CharSequence::toString)
                .map(String::toUpperCase)
                .subscribe(textViewUpperMessage::setText, this::onMessageError);
    }

    private void initCounterViews()
    {
        TextView textViewCounter = this.findViewById(R.id.mainActivityTextViewCounter);
        Button buttonIncrement =  this.findViewById(R.id.mainActivityButtonIncrement);

        RxView.clicks(buttonIncrement).map(o -> 1)
                .scan(0, Integer::sum)
                .subscribe(i -> buttonIncrementOnNextCallback(textViewCounter, i), ex -> Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void initViews()
    {
        this.initCounterViews();
        this.initMessageViews();
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
    protected void onResume()
    {
        //Detaylar ileride anlatılacak
        m_titleDisposable = Observable.just("Android RxJava").delay(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setTitle);

        super.onResume();
    }

    @Override
    protected void onPause()
    {
        if (m_titleDisposable != null && !m_titleDisposable.isDisposed())
            m_titleDisposable.dispose();

        super.onPause();
    }
}