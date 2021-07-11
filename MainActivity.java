package example.com.webview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //create activity
        setContentView(R.layout.activity_main); //for setting xml

        webview = this.findViewById(R.id.simpleWebView); //Finds a view that was identified by the id attribute
        webview.setWebViewClient(new CustomWebViewClient());
        WebSettings webSetting = webview.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDisplayZoomControls(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.loadUrl("https://...");  // give the url of the web page.https://your-ip-address/project-name.

    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
       /* public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;


        }*/


        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse(url));
                startActivity(intent);
            }else if(url.startsWith("http:") || url.startsWith("https:")) {
                view.loadUrl(url);
            }
            return true;
        }
    }



}
