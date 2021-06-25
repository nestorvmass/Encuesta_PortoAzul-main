package com.example.encuesta_portoazul;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Pagina extends Activity {
    WebView pag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina);
        pag = findViewById(R.id.webView1);
        pag.getSettings().setJavaScriptEnabled(true);
        pag.setWebChromeClient(new WebChromeClient());
        //pag.loadUrl("https://trentwalton.com/");
        pag.loadUrl("https://www.clinicaportoazul.com/");
    }
}