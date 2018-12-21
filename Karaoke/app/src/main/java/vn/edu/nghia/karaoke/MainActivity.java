package vn.edu.nghia.karaoke;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import vn.edu.nghia.Adapter.AdapterLop;
import vn.edu.nghia.Adapter.MusicAdapter;
import vn.edu.nghia.model.Music;

public class MainActivity extends AppCompatActivity {
    ListView lvBaiHatGoc;
    ArrayList<Music> dsBaiHatGoc;
     MusicAdapter BaiHatGoc;
    ListView lvBaiHatYeuThich;
    ArrayList<Music> dsBaiHatYeuThich;
    MusicAdapter<Music> BaiHatYeuThich;
    TabHost  tabhost;
    public static String DATABASE_NAME = "dbKaraoke.sqlite";
    public static String DB_PATH_SUFFIX = "/databases/";
    public  static SQLiteDatabase database = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xuLySaoChepCSDLTuAssetVaoHeThongMobile();
        addControls();
        
        addEvents();
        
    }

    private void xuLySaoChepCSDLTuAssetVaoHeThongMobile() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if(!dbFile.exists())
        {
            try {
                SaoChepDataBaseTuAsset();
                Toast.makeText(this, "Sao chép CSDL vào hệ thống thành công", Toast.LENGTH_LONG).show();

            }
            catch(Exception ex)
            {
                Toast.makeText(this, ex.toString(),Toast.LENGTH_LONG).show();
            }
        }

    }

    private void SaoChepDataBaseTuAsset() {
        try
        {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String OutFileName = LayDuongDanLuuTru();
            File f= new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!f.exists())
            {
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(OutFileName);
            byte[] buffer = new byte[1024];
            int length ;
            while ((length = myInput.read(buffer)) > 0)
            {
                myOutput.write(buffer,0,length);}
            myOutput.flush();
            myOutput.close();
            myInput.close();

        }
        catch (Exception ex)
        {
            Log.e("Loi Sao Chep",ex.toString());
        }
    }

    private String LayDuongDanLuuTru() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    private void addEvents() {

     tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
               if(tabId.equalsIgnoreCase("t1"))
                    xuLyTab1();
                else if(tabId.equalsIgnoreCase("t2"))
                    xuLyTab2();
            }
        });

    }

    private void xuLyTab1() {
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

        Cursor cursor = database.query("Karoke",null,null,null,null,null,null);
        dsBaiHatGoc.clear();
        while(cursor.moveToNext())
        {

          String ma = cursor.getString(0);
            String ten = cursor.getString(1);
            String casi = cursor.getString(2);
            int yeuthich = cursor.getInt(3);

            Music music = new Music();
            music.setMa(ma);
            music.setTen(ten);
            music.setCaSi(casi);
            music.setThich(yeuthich==1);
            dsBaiHatGoc.add( music);
        }
        cursor.close(); // đóng kết nối
        BaiHatGoc.notifyDataSetChanged();
    }

    private void xuLyTab2() {
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        //cursor là 1 con trỏ để quản lý các bảng dữ liệu
        //movetonext() là kiểm tra nếu nó còn dữ liệu thì cứ truy vấn
        Cursor cursor = database.query("Karoke",null,"THICH=?",new String[] {"1"},null,null,null);
        dsBaiHatYeuThich.clear();
        while(cursor.moveToNext())
        {

            String ma = cursor.getString(0);
            String ten = cursor.getString(1);
            String casi = cursor.getString(2);
            int yeuthich = cursor.getInt(3);

            Music music = new Music();
            music.setMa(ma);
            music.setTen(ten);
            music.setCaSi(casi);
            music.setThich(yeuthich==1);
            dsBaiHatYeuThich.add( music);
        }
        cursor.close(); // đóng kết nối
        BaiHatYeuThich.notifyDataSetChanged();

    }

    private void addControls() {
        tabhost = findViewById(R.id.tabHost);
        tabhost.setup();
        TabHost.TabSpec tab1 = tabhost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("",getResources().getDrawable(R.drawable.music));
        tabhost.addTab(tab1);


        TabHost.TabSpec tab2 = tabhost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("",getResources().getDrawable(R.drawable.heart));
        tabhost.addTab(tab2);






        lvBaiHatGoc = findViewById(R.id.lvBaiHatGoc);
        dsBaiHatGoc = new ArrayList<>();
        BaiHatGoc = new AdapterLop(MainActivity.this,R.layout.item,dsBaiHatGoc);
        lvBaiHatGoc.setAdapter(BaiHatGoc);

        lvBaiHatYeuThich = findViewById(R.id.lvBaiHatYeuThich);
        dsBaiHatYeuThich = new ArrayList<>();
        BaiHatYeuThich = new AdapterLop(MainActivity.this,R.layout.item,dsBaiHatYeuThich);
        lvBaiHatYeuThich.setAdapter(BaiHatYeuThich);
        xuLyTab1();

    }



}
