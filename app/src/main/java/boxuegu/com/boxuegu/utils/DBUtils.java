package boxuegu.com.boxuegu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import boxuegu.com.boxuegu.bean.Userbean;
import boxuegu.com.boxuegu.sqlite.SQLiteHelper;

public class DBUtils {
    private static DBUtils intance=null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;
    public DBUtils(Context context){
        helper=new SQLiteHelper(context);
        db=helper.getWritableDatabase();
    }
    public static DBUtils getIntance(Context context){
        if(intance==null){
            intance=new DBUtils(context);
        }
        return intance;
    }

    /**
     * 保存个人资料信息
     */
    public void saveUserInfo(Userbean bean){
        ContentValues cv=new ContentValues();
        cv.put("userName",bean.userName);
        cv.put("nickName",bean.nickName);
        cv.put("sex",bean.sex);
        cv.put("signature",bean.signature);
        db.insert(SQLiteHelper.U_USERINFO,null,cv);
    }
    /**
     * 获取个人资料
     */
    public UserBean getUserInfo(String userName){
        String sql="select * from"+SQLiteHelper.U_USERINFO+"where userName=?";
        Cursor cursor=db.rawQuery(sql,new String[]{userName});

    }
}
