package shredder.codepark.com.main;

import android.os.Bundle;

import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import shredder.codepark.com.Util.Logger;
import shredder.codepark.com.test.R;

public class MainActivity extends AppCompatActivity {

    Boolean acceptExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LoadRootPath("/sdcard/");
        ListView lst = (ListView) findViewById(R.id.listView);
        lst.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                ListView lst = (ListView) findViewById(R.id.listView);
                Item item = (Item) lst.getAdapter().getItem(position);
                LoadRootPath(item.getFullPath());
            }
        });

        ImageView view = (ImageView) findViewById(R.id.back);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                returnParent();
            }
        });
    }

    private String getCurrentPath() {
        TextView txtAddr = (TextView) findViewById(R.id.address);
        return (String) txtAddr.getText();
    }

    private void returnParent() {
        if (getCurrentPath().equals("/"))
            return;
        String path = getCurrentPath();
        File curFile = new File(path);
        LoadRootPath(curFile.getParent());

    }

    @Override
    public void onBackPressed() {
        if (acceptExit) {
            super.onBackPressed();
            return;
        }

        if (getCurrentPath().equals("/")) {
            this.acceptExit = true;
            Logger.getInstance().LogToast(this, getResources().getString(R.string.exit_message));
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    acceptExit = false;
                }
            }, 2000);
        } else {
            returnParent();
        }
    }

    private void LoadRootPath(String s) {
        File f = new File(s);
        f.setReadable(true);
        File[] list = f.listFiles();
        ListView lst = (ListView) findViewById(R.id.listView);
        List<Item> flist = new ArrayList<Item>();
        for (int i = 0; list != null && i < list.length; i++) {
            Item item = new Item(list[i]);
            flist.add(item);
        }
        FileAdapter fileAdapter = new FileAdapter(this, flist);
        lst.setAdapter(fileAdapter);
        TextView txtAddr = (TextView) findViewById(R.id.address);
        txtAddr.setText(s);
    }

}
