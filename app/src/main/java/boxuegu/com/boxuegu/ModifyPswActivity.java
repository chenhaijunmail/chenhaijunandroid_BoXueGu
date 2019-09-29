package boxuegu.com.boxuegu;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import boxuegu.com.boxuegu.utils.AnalysisUtils;

public class ModifyPswActivity extends AppCompatActivity {

    private TextView tv_main_title;
    private TextView tv_back;
    private EditText et_original_psw,et_new_psw,et_new_psw_again;
    private Button btn_save;
    private String originalPsw,newPsw,newPswAgain;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psw);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        username= AnalysisUtils.readLoginUserName(this);
    }
    /**
     * 获取界面控件并处理相关控件的点击事件
     */
    private void init(){
        tv_main_title=(TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("修改密码");
        tv_back=(TextView) findViewById(R.id.tv_back);
        et_original_psw=(EditText) findViewById(R.id.et_original_psw);
        et_new_psw=(EditText) findViewById(R.id.et_new_psw);
        et_new_psw_again=(EditText) findViewById(R.id.et_new_again);
        btn_save=(Button) findViewById(R.id.btn_save);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyPswActivity.this.finish();
            }
        });
        //保存按钮的点击事件
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
            }
        });
    }
}
