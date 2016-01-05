package io.github.froger.instamaterial;

import android.app.Application;

import cn.bmob.v3.Bmob;
import timber.log.Timber;

/**
 * Created by froger_mcs on 05.11.14.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        Bmob.initialize(App.this, "ffbd83f4172faa75996eb76b663887f9");
    }
}
