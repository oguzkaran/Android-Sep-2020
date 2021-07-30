package org.csystem.samples.listallapplications;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jakewharton.rxbinding3.widget.RxAdapterView;

import org.csystem.android.activity.FindView;
import org.csystem.android.activity.FindViewUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    @FindView(id = R.id.MAINACTIVITY_EDITTEXT_APPLICATIONS)
    private ListView m_listViewApplications;
    private PackageManager m_packageManager;

    public static boolean isApplicationRunning(Context context, String packageName)
    {
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();

        if (processInfos == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : processInfos) {
            if (runningAppProcessInfo.processName.equals(packageName))
                return true;
        }

        return false;
    }

    private void removeApp(String packageName)
    {
        Intent intent = new Intent(Intent.ACTION_DELETE);

        intent.setData(Uri.parse("package:" + packageName));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void loadApplications()
    {
        List<ApplicationInfo>  applicationInfos = m_packageManager.getInstalledApplications (PackageManager.GET_META_DATA);
        List<ApplicationInfo> apps = new ArrayList<>();

        Disposable disposable = null;

        try {
           disposable = Observable.fromIterable(applicationInfos)
                    .filter(ai -> ai.packageName.contains("samples")) // && isApplicationRunning(this, ai.packageName))
                    .subscribe(ai -> apps.add(ai));

            ArrayAdapter<ApplicationInfo> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, apps);
            m_listViewApplications.setAdapter(adapter);
        }
        finally {
            if (disposable != null && !disposable.isDisposed())
                disposable.dispose();
        }
    }

    private void onListViewApplicationsItemsClicked(int pos)
    {
        ApplicationInfo ai = (ApplicationInfo)m_listViewApplications.getItemAtPosition(pos);

        removeApp(ai.packageName);
    }

    private void initListViewApplications()
    {
        try {
            RxAdapterView.itemClicks(m_listViewApplications)
                    .subscribe(this::onListViewApplicationsItemsClicked, ex -> Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show());
        }
        catch (Throwable ex) {
            //...
        }
    }

    private void initViews()
    {
        FindViewUtil.findViews(this);
        this.initListViewApplications();
    }

    private void init()
    {
        m_packageManager = this.getPackageManager();
        this.initViews();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    public void onListButtonClicked(View view)
    {
        try {
            loadApplications();
        }
        catch (Throwable ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
