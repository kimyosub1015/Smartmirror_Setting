package com.example.b.smartmirror_setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.b.smartmirror_setting.MainActivity;

public class WebviewActivity extends AppCompatActivity {

    public String the_ip_data = MainActivity.ip_set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        // 웹뷰 세팅
        System.out.println("ipdata : "+the_ip_data);
        WebView mWebView = (WebView) findViewById(R.id.webview); //레이어와 연결
        mWebView .setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        WebSettings mWebSettings = mWebView.getSettings(); //세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true); // 자바스크립트 사용 허용
        mWebView.loadUrl("http://"+the_ip_data+":8080/"); //원하는 URL 입력
    }
}
