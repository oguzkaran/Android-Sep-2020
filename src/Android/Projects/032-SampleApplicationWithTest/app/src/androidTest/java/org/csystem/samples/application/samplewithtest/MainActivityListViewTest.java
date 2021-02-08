package org.csystem.samples.application.samplewithtest;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityListViewTest {
    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testListViewAdapter()
    {
        ActivityScenario<MainActivity> scenario = rule.getScenario();

        scenario.onActivity(activity -> {
            View view = activity.findViewById(R.id.mainActivityListViewNames);

            assertThat(view, notNullValue());
            assertThat(view, instanceOf(ListView.class)); //ListView mu?

            ListView listView = (ListView)view;
            ListAdapter adapter = listView.getAdapter();

            assertThat(adapter, notNullValue()); //Adapter set edilmiş mi?
            assertThat(adapter, instanceOf(ArrayAdapter.class)); // ArrayAdapter mı?
            assertThat(adapter.getCount(), greaterThan(2)); // Adapter'ın eleman sayısı 2(iki)'den büyük mü?
            assertThat(adapter.getCount(), lessThan(5)); // Adapter'ın eleman sayısı 5(beş)'den küçük mü?
        });
    }
}
