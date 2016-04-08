package shredder.codepark.com.main;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import shredder.codepark.com.test.R;

/**
 * Created by Selami on 07.04.2016.
 */
public class FileAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Item> items;

    public FileAdapter(Activity activity, List<Item> items) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View linView;

        linView = layoutInflater.inflate(R.layout.line_layout, null);
        TextView txtPath = (TextView) linView.findViewById(R.id.file);
        TextView txtSize = (TextView) linView.findViewById(R.id.size);
        ImageView imgFile = (ImageView) linView.findViewById(R.id.file_icon);
        ImageView imgRead = (ImageView) linView.findViewById(R.id.ic_readable);
        ImageView imgWrite = (ImageView) linView.findViewById(R.id.ic_writable);

        Item item = items.get(position);
        txtPath.setText(item.getName());
        txtSize.setText(GetFileSizeHR(item.getSize()));
        if (item.isDirectory()) {
            imgFile.setImageResource(R.drawable.ic_folder);
        } else {
            imgFile.setImageResource(R.drawable.ic_file);
        }
        if (item.isReadable()) {
            imgRead.setImageResource(R.drawable.ic_ok);
        } else {
            imgRead.setImageResource(R.drawable.ic_no);
        }
        if (item.isWritable()) {
            imgWrite.setImageResource(R.drawable.ic_ok);
        } else {
            imgWrite.setImageResource(R.drawable.ic_no);
        }

        return linView;
    }

    private String GetFileSizeHR(long size) {
        double len = size;
        long MB = 1024 * 1024;
        long KB = 1024;
        long GB = 1024 * 1024 * 1024;

        if (size < 1024) {
            return len + " B";
        } else if (size >= KB && size < MB) {
            len = (size / KB);
            return len + " KB";
        } else if (size >= MB && size < GB) {
            len = (size / MB);
            return len + " MB";
        } else if (size >= GB) {
            len = (size / GB);
            return len + " GB";
        }
        return String.valueOf(len);
    }
}
