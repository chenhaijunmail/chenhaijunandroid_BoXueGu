package boxuegu.com.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AnalysisUtils {
    /**
     * 从SharedPreferences
     */
    public static String readLoginUserName(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String userName=sp.getString("loginUserName","");
        return userName;
    }
}
