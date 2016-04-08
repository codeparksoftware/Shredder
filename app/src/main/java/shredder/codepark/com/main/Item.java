package shredder.codepark.com.main;



import java.io.File;
import java.io.FilePermission;

import shredder.codepark.com.Util.Logger;



/**
 * Created by Selami on 06.04.2016.
 */
public class Item {
    private  boolean isDirectory;
    private String name;
    private long size;
    private boolean readable;
    private  boolean writable;
    private  boolean hidden;
    private  String fullPath;
    public Item(File f){

        this.setIsDirectory(f.isDirectory());
        this.setPath(f.getName());
        this.setSize(f.length());
        this.setReadable(f.canRead());
        this.setWritable(f.canWrite());
        this.setHidden(f.isHidden());
        this.setFullPath(f.getAbsolutePath());
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    private void setIsDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    public String getName() {
        return name;
    }

    private void setPath(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    private void setSize(long size) {
        this.size = size;
    }

    public boolean isReadable() {
        return readable;
    }

    private void setReadable(boolean read) {
        this.readable = read;
    }

    public boolean isWritable() {
        return writable;
    }

    private void setWritable(boolean writable) {
        this.writable = writable;
    }

    public boolean isHidden() {
        return hidden;
    }

    private void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getFullPath() {
        return fullPath;
    }

    private void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
}
