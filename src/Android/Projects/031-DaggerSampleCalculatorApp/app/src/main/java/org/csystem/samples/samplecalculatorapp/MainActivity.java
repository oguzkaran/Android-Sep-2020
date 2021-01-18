package org.csystem.samples.samplecalculatorapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.csystem.android.activity.FindView;
import org.csystem.android.activity.FindViewUtil;
import org.csystem.samples.samplecalculatorapp.application.CalculatorApplication;
import org.csystem.samples.samplecalculatorapp.operation.Calculator;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {
    @FindView(id = R.id.MAINACTIVITY_EDITTEXT_VAL1)
    private EditText m_editTextVal1;

    @FindView(id = R.id.MAINACTIVITY_EDITTEXT_VAL2)
    private EditText m_editTextVal2;

    @FindView(id = R.id.MAINACTIVITY_SPINNER_OPERATIONS)
    private Spinner m_spinnerOperations;

    @FindView(id = R.id.MAINACTIVITY_TEXTVIEW_RESULT)
    private TextView m_textViewResult;

    @Inject
    Calculator calculator;

    private void initOperationsSpinner()
    {
        String [] ops = {"+", "-", "*", "%"};

        m_spinnerOperations.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ops));
    }

    private void initViews()
    {
        FindViewUtil.findViews(this);
        this.initOperationsSpinner();
    }

    private void init()
    {
        this.initViews();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        CalculatorApplication.COMPONENT.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    @SuppressLint("CheckResult")
    public void onCalculateButtonClicked(View view)
    {
        String val1Str = m_editTextVal1.getText().toString();
        String val2Str = m_editTextVal2.getText().toString();
        char op = ((String)m_spinnerOperations.getSelectedItem()).charAt(0);

        Observable.just(new OperationInfo(Integer.parseInt(val1Str), Integer.parseInt(val2Str), op))
                .map(oi -> calculator.doOperation(oi.getVal1(), oi.getVal2(), oi.getOp()))
                .map(res -> res + "")
                .subscribe(m_textViewResult::setText, ex -> m_textViewResult.setText(ex.getMessage()));
    }
}
