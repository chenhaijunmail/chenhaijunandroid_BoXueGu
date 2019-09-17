package boxuegu.com.boxuegu;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView textView=findViewById(R.id.tv_version);

        //设置此界面为竖屏幕（默认）
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        PackageManager packageManager=getPackageManager();

        try{
            //获取包信息
            PackageInfo packageInfo=packageManager.getPackageInfo(getPackageName(),0);
            textView.setText("V"+packageInfo.versionName);
        }
        catch (Exception e){
            textView.setText("V");
            e.printStackTrace();
        }

        //主界面3秒后自己跳转，Timer实现
        Timer timer=new Timer();

        TimerTask task=new TimerTask() {
            @Override
            public void run() {//超过3秒后执行此代码块
                Intent intent=new Intent(SplashActivity.this,RegisterActivity.class);//新建一个意图对象
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        timer.schedule(task,3000);
    }
}
