package projectv2.technocoders.com.sqlitetracking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class GPSDatabase extends SQLiteOpenHelper {
    MapsActivity myMAP;
    public static final String DATABASE_NAME = "sqlitenew1.db";
    public static final String TABLE_NAME = "location_table";
   /* public static final String COL_1 = "E_ID";
    public static final String COL_2 = "NAME";*/
    public static final String COL_1 = "LONGITUDE";
    public static final String COL_2= "LATITUDE";
    public static final String COL_3 = "TIME";
   // public static double log;
   // public static  double lat;
    public double longitude ;
    public double latitude;
    String Date;



    public GPSDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("database","created");
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (LONGITUDE TEXT,LATITUDE TEXT,TIME TEXT)");
        Log.d("database","table created");
      //  String clearDBQuery = "DELETE FROM "+TABLE_NAME;
      //  sqLiteDatabase.execSQL(clearDBQuery);
       // myMAP = new MapsActivity(this);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

   /* public void clearDatabase(String TABLE_NAME) {
        clearDBQuery = "DELETE FROM "+TABLE_NAME;
        sqLiteDatabase.execSQL(clearDBQuery);
    }*/

 public boolean insertData( double latitude, double longitude,String Date )
    {
        this.longitude = longitude;
        this.latitude = latitude;
        this.Date = Date;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       /* contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,time);*/
        contentValues.put(COL_1,latitude);
        contentValues.put(COL_2,longitude);
        contentValues.put(COL_3,Date);
        long result =  sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
        {
            return false;
        }
        else{
            return true;

        }
    }

    public List<DataModel> getdata(){
        // DataModel dataModel = new DataModel();
        List<DataModel> data=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        DataModel dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new DataModel();
            String longitude = cursor.getString(cursor.getColumnIndexOrThrow("LONGITUDE"));
            String latitude = cursor.getString(cursor.getColumnIndexOrThrow("LATITUDE"));
            String time = cursor.getString(cursor.getColumnIndexOrThrow("TIME"));
           // dataModel.setLongitude(longitude);
           // dataModel.setLatitude(latitude);
            dataModel.setTime(time);
            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            data.add(dataModel);
        }

        for (DataModel mo:data ) {

            Log.i("Hellomo",""+mo.getTime());
        }

        //

        return data;
    }
}
