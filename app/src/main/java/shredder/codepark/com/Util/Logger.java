package shredder.codepark.com.Util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Selami on 06.04.2016.
 */
public class Logger {
    private String TAG = "Logger";

    private Logger() {

    }

    private static Logger instance = null;

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void LogToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void LogWatch(String str) {
        Log.v(TAG, str);
    }
}
