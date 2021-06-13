package project.akbaralzaini.sesi2.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import project.akbaralzaini.sesi2.R;

public class InfoActivity extends AppCompatActivity {
    private WebView webView;
    private TextView tvClose, tvInfo;
    private EditText edtLinkUrl;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setTitle("info activity");

        tvClose = findViewById(R.id.txtClose);
        tvClose.setOnClickListener(v -> finish());

        tvInfo = findViewById(R.id.txtInfo);
        tvInfo.setOnClickListener(v -> tampilAbout());



        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://akbaralzaini.github.io");

        edtLinkUrl = findViewById(R.id.edtLink);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> {
            if (edtLinkUrl.getText().toString().equals("")){
               Toast t = Toast.makeText(getApplicationContext(),"url kosong",Toast.LENGTH_SHORT);
               t.show();
            }else{
                webView.loadUrl(edtLinkUrl.getText().toString());
            }

        });



    }


    private class MyBrowser extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void tampilAbout() {
        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.loadUrl("file:///android_asset/about.html");

        new AlertDialog.Builder(InfoActivity.this)
                .setTitle("info")
                .setView(webView)
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> dialog.cancel()).show();
    }


}