package boxuegu.com.boxuegu;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import boxuegu.com.boxuegu.utils.AnalysisUtils;
import boxuegu.com.boxuegu.utils.MD5Utils;

public class FindPswActity extends AppCompatActivity {

    private EditText et_validate_name,et_user_name;
    private Button btn_validate;
    private TextView tv_main_title;
    private TextView tv_back;
    //from为security时是从设置密保界面跳转过来的，否则就是从登陆界面跳转过来的
    private String from;
    private TextView tv_reset_psw,tv_user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        from=getIntent().getStringExtra("from");
        init();
    }
    /**
     * 获取界面控件及处理相应控件的点击事件
     */
    private void init(){
        tv_main_title=(TextView) findViewById(R.id.tv_main_title);
        tv_back=(TextView) findViewById(R.id.tv_back);
        et_validate_name=(EditText) findViewById(R.id.et_validate_name);
        btn_validate=(Button) findViewById(R.id.btn_validate);
        tv_reset_psw=(TextView) findViewById(R.id.tv_reset_psw);
        et_user_name=(EditText) findViewById(R.id.et_user_name);
        tv_user_name=(TextView) findViewById(R.id.tv_user_name);
        if("security".equals(from)){
            tv_main_title.setText("设置密保");
        }else{
            tv_main_title.setText("找回密码");
            tv_user_name.setVisibility(View.VISIBLE);
            et_user_name.setVisibility(View.VISIBLE);
        }
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindPswActity.this.finish();
            }
        });
        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validateName=et_validate_name.getText().toString().trim();
                if("security".equals(from)){
                    if(TextUtils.isEmpty(validateName)){
                        Toast.makeText(FindPswActity.this,"请输入要验证的姓名",Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        Toast.makeText(FindPswActity.this,"密保设置成功",Toast.LENGTH_SHORT).show();
                        //保存密保到SharedPreferences中
                        saveSecurity(validateName);
                        FindPswActity.this.finish();
                    }
                }else{
                    //找回密码
                    String userName=et_user_name.getText().toString().trim();
                    String sp_security=readSecurity(userName);
                    if(TextUtils.isEmpty(userName)){
                        Toast.makeText(FindPswActity.this,"请输入您的用户名",Toast.LENGTH_SHORT).show();
                        return;
                    }else if(!isExistUserName(userName)){
                        Toast.makeText(FindPswActity.this,"您输入的用户名不存在",Toast.LENGTH_SHORT).show();
                        return;
                    }else if(TextUtils.isEmpty(validateName)){
                        Toast.makeText(FindPswActity.this,"请输入要验证的姓名",Toast.LENGTH_SHORT).show();
                        return;
                    }if(!validateName.equals(sp_security)){
                        Toast.makeText(FindPswActity.this,"你输入的密保不正确",Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        //输入的密保正确，重新给用户设置一个密码
                        tv_reset_psw.setVisibility(View.VISIBLE);
                        tv_reset_psw.setText("初始密码：123456");
                        savePsw(userName);
                    }
                }
            }
        });
        }
    /**
     * 保存初始化的密码
     */
    private void savePsw(String userName){
        String md5Psw= MD5Utils.md5("123456");//把密码用MD5加密
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();//获取编译器
        editor.putString(userName,md5Psw);
        editor.commit();
    }
    /**
     * 保存密保到SharedPreferences中
     */
    private void saveSecurity(String validateName){
        SharedPreferences sp=getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(AnalysisUtils.readLoginUserName(this)+"_security",validateName);
        editor.commit();
    }

    /**
     * 从SharedPreferences中读取密保
     */
    private  String readSecurity(String userName){
        SharedPreferences sp=getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String security=sp.getString(userName+"_security","");
        return security;
    }
    /**
     * 从SharedPreferences中根据用户输入的用户名来判断是否有此用户
     */
    private  boolean isExistUserName(String userName) {
        boolean hasUserName = false;
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw=sp.getString(userName,"");
        if(!TextUtils.isEmpty(spPsw)){
            hasUserName=true;
        }
        return hasUserName;
    }

}
