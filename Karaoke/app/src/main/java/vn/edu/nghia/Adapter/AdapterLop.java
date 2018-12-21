package vn.edu.nghia.Adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import vn.edu.nghia.karaoke.MainActivity;
import vn.edu.nghia.karaoke.R;
import vn.edu.nghia.model.Music;

public class AdapterLop extends MusicAdapter<Music> {
    
    public AdapterLop(Activity context, int resource, List<Music> objects) {
        super(context, resource, objects);
    }

    @Override
    protected View rowDetail(LayoutInflater inflater, int position) {
        View row = inflater.inflate(this.resource,null);
        TextView txtMa = row.findViewById(R.id.txtMa);
        TextView txtTen = row.findViewById(R.id.txtTen);
        TextView txtCasi = row.findViewById(R.id.txtCasi);
        ImageButton btnLike = row.findViewById(R.id.btnLike);
        ImageButton btnDislike = row.findViewById(R.id.btnDislike);
        final Music music = this.objects.get(position);
        txtMa.setText(music.getMa());
        txtTen.setText(music.getTen());
        txtCasi.setText(music.getCaSi());
        if(music.isThich())
        {
            btnLike.setVisibility(View.INVISIBLE);
            btnDislike.setVisibility(View.VISIBLE);}
        else {
            btnLike.setVisibility(View.VISIBLE);
            btnDislike.setVisibility(View.INVISIBLE);
        }
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLike(music);
            }
        });
        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDisLike(music);
            }
        });


        return row;
    }
    private void xuLyDisLike(Music music) {
        ContentValues  row = new ContentValues();
        row.put("THICH",0);
        MainActivity.database.update("Karoke",row,"MA=?",new String[]{music.getMa()});

    }

    private void xuLyLike(Music music) {
        ContentValues  row = new ContentValues();
        row.put("THICH",1);
        MainActivity.database.update("Karoke",row,"MA=?",new String[]{music.getMa()});
    }
}
