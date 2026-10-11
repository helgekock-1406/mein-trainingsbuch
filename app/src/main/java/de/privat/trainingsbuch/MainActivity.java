package de.privat.trainingsbuch;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.webkit.ValueCallback;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.util.Base64;
import android.widget.Toast;
import java.io.OutputStream;

public class MainActivity extends Activity {
  private WebView web;
  private ValueCallback<Uri[]> upload;
  private String exportName, exportData, exportMime;
  private static final int FILE_PICK=301, FILE_SAVE=302;
  @Override public void onCreate(Bundle b){ super.onCreate(b);
    web=new WebView(this); setContentView(web);
    WebSettings s=web.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setAllowFileAccess(true);
    web.setWebViewClient(new WebViewClient());
    web.setWebChromeClient(new WebChromeClient(){
      @Override public boolean onShowFileChooser(WebView w,ValueCallback<Uri[]> cb,FileChooserParams p){ upload=cb; try {startActivityForResult(p.createIntent(),FILE_PICK);return true;}catch(Exception e){upload=null;return false;} }
    });
    web.addJavascriptInterface(new Object(){
      @JavascriptInterface public void save(String name,String mime,String base64){ runOnUiThread(()->{
        exportName=name; exportMime=mime; exportData=base64;
        Intent i=new Intent(Intent.ACTION_CREATE_DOCUMENT);i.setType(mime);i.addCategory(Intent.CATEGORY_OPENABLE);i.putExtra(Intent.EXTRA_TITLE,name);startActivityForResult(i,FILE_SAVE);
      });}
    },"AndroidSave");
    web.loadUrl("file:///android_asset/index.html");
  }
  @Override protected void onActivityResult(int request,int result,Intent data){super.onActivityResult(request,result,data);
    if(request==FILE_PICK){if(upload!=null){upload.onReceiveValue(result==RESULT_OK&&data!=null?new Uri[]{data.getData()}:null);upload=null;}}
    if(request==FILE_SAVE&&result==RESULT_OK&&data!=null){try(OutputStream out=getContentResolver().openOutputStream(data.getData())){out.write(Base64.decode(exportData,Base64.DEFAULT));Toast.makeText(this,"Datei gespeichert",Toast.LENGTH_SHORT).show();}catch(Exception e){Toast.makeText(this,"Speichern fehlgeschlagen",Toast.LENGTH_LONG).show();}}
  }
  @Override public void onBackPressed(){if(web.canGoBack())web.goBack();else super.onBackPressed();}
}
