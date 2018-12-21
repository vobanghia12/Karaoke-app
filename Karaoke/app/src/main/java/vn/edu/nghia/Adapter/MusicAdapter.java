package vn.edu.nghia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import vn.edu.nghia.karaoke.R;
import vn.edu.nghia.model.Music;

public abstract class MusicAdapter<T> extends ArrayAdapter {
    Activity context;
    int resource;
    List<T> objects;
    public MusicAdapter(Activity context, int resource, List<T> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }
    protected abstract View rowDetail(LayoutInflater inflater , int position);  @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = rowDetail(inflater,position);
        return row;
    }


}
