package org.csystem.samples.application.eventdrivenjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Optional;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText m_editTextName;
    private EditText m_editTextSurname;
    private TextView m_textViewFullName;

    private static class PersonInfo {
        String name;
        String surname;

        public PersonInfo(String name, String surname)
        {
            this.name = name;
            this.surname = surname;
        }

        String getFullName()
        {
            return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase()
                    + " " + surname.toUpperCase();
        }
    }

    private Optional<PersonInfo> getPersonInfo()
    {
        String name = m_editTextName.getText().toString();
        String surname = m_editTextSurname.getText().toString();

        return name.trim().isEmpty() || surname.trim().isEmpty() ?
                Optional.empty() : Optional.of(new PersonInfo(name, surname));
    }

    private void okOKButtonsClicked(String message)
    {
        Optional<PersonInfo> optionalPersonInfo = this.getPersonInfo();

        if (optionalPersonInfo.isPresent())
            m_textViewFullName.setText(String.format("%s:%s", message, optionalPersonInfo.get().getFullName()));
        else
            Toast.makeText(this, "Geçersiz girişler", Toast.LENGTH_LONG).show();
    }

    private void initOkButton2()
    {
        Button buttonOK2 = this.findViewById(R.id.mainActivityButtonOK2);

        buttonOK2.setOnClickListener(v -> okOKButtonsClicked("OK2"));
    }

    private void initOkButton3()
    {
        Button buttonOK3 = this.findViewById(R.id.mainActivityButtonOK3);

        buttonOK3.setOnClickListener(this);
    }

    private void initButtons()
    {
        this.initOkButton2();
        this.initOkButton3();
    }

    private void initTextViews()
    {
        m_textViewFullName = this.findViewById(R.id.mainActivityTextViewFullname);
    }

    private void initEditTexts()
    {
        m_editTextName = this.findViewById(R.id.mainActivityEditTextName);
        m_editTextSurname = this.findViewById(R.id.mainActivityEditTextSurname);
    }

    private void initViews()
    {
        this.initEditTexts();
        this.initTextViews();
        this.initButtons();
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
    public void onClick(View view)
    {
        this.okOKButtonsClicked("OK3");
    }

    public void onOKButtonClicked(View view)
    {
        okOKButtonsClicked("OK");
    }

    public void onExitButtonClicked(View view)
    {
        this.finish();
    }

    public void onFullNameTextViewClicked(View view)
    {
        Toast.makeText(this, m_textViewFullName.getText().toString(), Toast.LENGTH_LONG).show();
    }
}