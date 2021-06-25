package com.example.encuesta_portoazul;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Mapas extends Activity {
    WebView pag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);
        pag = (WebView) findViewById(R.id.webView1);
        pag.getSettings().setJavaScriptEnabled(true);
        pag.getSettings().setAllowFileAccess( true );
        pag.getSettings().setAppCacheEnabled( true );

        pag.setWebChromeClient(new WebChromeClient());

        //pag.loadUrl("https://www.google.com/maps/place/Polit%C3%A9cnico+Costa+Atl%C3%A1ntica/@10.9868125,-74.8192206,18z/data=!3m1!4b1!4m9!1m6!3m5!1s0x8ef42ce205f8ba07:0xd79e4a07d6b96d70!2sPolit%C3%A9cnico+Costa+Atl%C3%A1ntica!8m2!3d10.9868453!4d-74.8193321!3m1!1s0x0:0xbd1c55208a880244%22");
        pag.loadUrl("https://www.google.com/maps/place/Cl%C3%ADnica+Portoazul/@11.0156663,-74.8458543,15z/data=!4m5!3m4!1s0x0:0x33cdd73acba18ea0!8m2!3d11.0156663!4d-74.8458543");
    }}