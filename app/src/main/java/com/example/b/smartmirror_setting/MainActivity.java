package com.example.b.smartmirror_setting;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText et_ip;
    private TextView tv_currentIP;
    private Button btn_setIP;

    private String current_ip; // 환경변수에 저장된 ip주소
    private String smartmirror_ip; // 스마트 미러의 할당받은 ip 주소
    public static String ip_set = "";

    /* 환경 변수 */
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_currentIP = (TextView)findViewById(R.id.tv_currentIP);
        et_ip = (EditText)findViewById(R.id.et_ip);
        btn_setIP = (Button)findViewById(R.id.btn_setIP);

        prefs = getSharedPreferences("login", 0);
        editor = prefs.edit();

        current_ip = prefs.getString("SMARTMIRROR_IP","0.0.0.0");

        if(current_ip.equals("0.0.0.0")) {
            tv_currentIP.setText(current_ip);
            Toast.makeText(getApplicationContext(),"IP가 설정되어 있지 않습니다. 스마트미러를 보고 IP를 설정해주세요.", Toast.LENGTH_LONG).show();
        }
        else{
            tv_currentIP.setText(current_ip);
            ip_set = current_ip;
        }

        btn_setIP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                smartmirror_ip = et_ip.getText().toString();
                editor.putString("SMARTMIRROR_IP",smartmirror_ip);
                editor.commit();
                tv_currentIP.setText(smartmirror_ip);
                Toast.makeText(getApplicationContext(),"IP가 "+ smartmirror_ip +"로 설정되었습니다.", Toast.LENGTH_SHORT).show();
                ip_set = smartmirror_ip;

            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WebviewActivity.class));
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ip_set.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"IP가 설정되어 있지 않습니다. 스마트미러를 보고 IP를 설정해주세요.", Toast.LENGTH_LONG).show();
                }
                else{
                    startActivity(new Intent(MainActivity.this, ImgupActivity.class));
                }
            }
        });
    }
}
