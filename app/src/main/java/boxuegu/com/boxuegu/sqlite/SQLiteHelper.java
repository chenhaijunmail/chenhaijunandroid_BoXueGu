package boxuegu.com.boxuegu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;
    private static String DB_NAME="bxg.db";
    public static final String U_USERINFO="userinfo";
    public SQLiteOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void OnCreate(SQLiteDatabase db){
        /**
         * 创建个人信息表
         */
        db.execSQL("CREATE TABLE IF NOT EXISTS"+U_USERINFO+"("
                    +"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +"userName VARCHAR,"
                    +"nickName VARCHAR,"
                    +"sex VARCHAR,"
                    +")");
    }
    /**
     * 当数据库版本号增加时才会调用此方法
     */
    @Override
    public  void OnUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+U_USERINFO);
        onCreate(db);
    }

}
