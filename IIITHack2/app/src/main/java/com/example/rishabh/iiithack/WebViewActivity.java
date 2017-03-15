package com.example.rishabh.iiithack;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

  private WebView myWebView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web_view);

    myWebView = (WebView) findViewById(R.id.webview);
    WebSettings webSettings = myWebView.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setDomStorageEnabled(true);
    myWebView.addJavascriptInterface(new WebAppInterface(WebViewActivity.this), "Android");
    myWebView.setWebViewClient(new MyWebViewClient());
    myWebView.loadUrl("https://arctic.000webhostapp.com/");

  }

  private class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
      //if (Uri.parse(url).getHost().equals("https://arctic.000webhostapp.com/")) {
      //  // This is my web site, so do not override; let my WebView load the page
      //
      //  return false;
      //}
      //// Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
      //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
      //startActivity(intent);
      //return true;

      view.loadUrl(url);
    return false;
    }
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    // Check if the key event was the Back button and if there's history
    if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
      myWebView.goBack();
      return true;
    }
    // If it wasn't the Back key or there's no web page history, bubble up to the default
    // system behavior (probably exit the activity)
    return super.onKeyDown(keyCode, event);
  }


  public class WebAppInterface extends MainActivity{

    Activity mActivity;

    /** Instantiate the interface and set the context */
    WebAppInterface(Activity c) {
      mActivity = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void scan() {
      System.out.println("JSMethod");
      //new IntentIntegrator(MainActivity.this).initiateScan(); // `this` is the current Activity

      Toast.makeText(mActivity, "Scanner Started", Toast.LENGTH_SHORT).show();

      //myWebView.post(new Runnable() {
      //  @Override public void run() {
      //    String s="awfa";
      //    String st="javascript:androidFunc('"+s+"');";
      //    //myWebView.loadUrl("javascript:androidFunc("+st+");");
      //    myWebView.loadUrl(st);
      //  }
      //});
      //

    }
  }

}
