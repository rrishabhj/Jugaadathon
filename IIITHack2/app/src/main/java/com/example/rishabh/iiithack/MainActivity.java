package com.example.rishabh.iiithack;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

  private static final int REQUEST = 5;
  private StringBuffer stringBuffer;

  EditText etUserId,etName,etGender,etDob,etRelation, etAddress,etPatientStat,etSymtoms,etPrescibeMed;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    etUserId = (EditText) findViewById(R.id.etUserId);
    etName = (EditText) findViewById(R.id.etName);
    etGender = (EditText) findViewById(R.id.etGender);
    etDob = (EditText) findViewById(R.id.etDob);
    etRelation = (EditText) findViewById(R.id.etRelation);
    etAddress = (EditText) findViewById(R.id.etAddress);
    etPatientStat = (EditText) findViewById(R.id.etPatientStat);
    etSymtoms = (EditText) findViewById(R.id.etSymtoms);
    etPrescibeMed = (EditText) findViewById(R.id.etPrescibeMed);

    getCameraPermission(MainActivity.this);

  }


  public void getQRScan(View v){
    new IntentIntegrator(MainActivity.this).initiateScan(); // `this` is the current Activity

  }

  public void submitData(View view){

    Intent intent=new Intent(this, WebViewActivity.class);

    startActivity(intent);

  }

  // Get the results:
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
    if(result != null) {
      if(result.getContents() == null) {
        Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
      } else {
        //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

        //myWebView.post(new Runnable() {
        //  @Override public void run() {
        //    myWebView.loadUrl("javascript:androidFunc();");
        //  }
        //});
        //("javascript:androidFunc()");

        //HashMap<String, String> hashMap=new HashMap<>()

        System.out.println(result.getContents());
        saveDataLocal(result.getContents());
        System.out.println("Scanned: " + result.getContents());
      }
    } else {
      Toast.makeText(this,"Scanned:", Toast.LENGTH_SHORT).show();
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  private void saveDataLocal(String str) {

    final String uid,name,gendar,address,dob,relation;

    stringBuffer =new StringBuffer();
    //myWebView.loadUrl("https://arctic.000webhostapp.com/2.html");

    str=str.replaceAll("=",":");

    stringBuffer.append("{");

    stringBuffer.append(str.substring(str.indexOf("uid:"),str.indexOf("name:") )+", ");


    uid = str.substring(str.indexOf("uid:")+5,str.indexOf("name:") -1);

    etUserId.setText(""+uid);
    //
    //System.out.println();
    //
    stringBuffer.append(str.substring(str.indexOf("name:")+6,str.indexOf("gender:")-1 )+", ");

    name=str.substring(str.indexOf("name:"),str.indexOf("gender:") );

    etName.setText(""+name);

    //
    //System.out.println();
    //
    stringBuffer.append(str.substring(str.indexOf("gender:")+8,str.indexOf("yob:")-2 )+", ");

    gendar=str.substring(str.indexOf("gender:"),str.indexOf("yob:") );


    etGender.setText(""+gendar);

    //
    //System.out.println();
    //
    stringBuffer.append(str.substring(str.indexOf("dob:")+5,str.indexOf("/>") -1)+",");

    dob= str.substring(str.indexOf("dob:"),str.indexOf("/>") );

    etDob.setText(""+dob);
    //

    stringBuffer.append(str.substring(str.indexOf("co:")+4,str.indexOf("house:") -1)+", ");
    relation = str.substring(str.indexOf("co:"),str.indexOf("house:") );


    etRelation.setText(relation);

    //
    //stringBuffer.append(str.substring(str.indexOf("house:")+7,str.indexOf("loc:") -1)+", " );
    //
    //str.substring(str.indexOf("house:")+7,str.indexOf("loc:") -1);
    //
    //stringBuffer.append(str.substring(str.indexOf("loc:")+5,str.indexOf("vtc:")-1 )+", ");
    //
    //str.substring(str.indexOf("loc:")+5,str.indexOf("vtc:")-1 );
    //
    ////
    //stringBuffer.append(str.substring(str.indexOf("subdist:")+9,str.indexOf("state:")-1 )+", ");
    //str.substring(str.indexOf("subdist:")+9,str.indexOf("state:")-1 )
    ////
    //stringBuffer.append(str.substring(str.indexOf("state:")+7,str.indexOf("pc:")-1 )+", ");
    //
    //str.substring(str.indexOf("state:")+7,str.indexOf("pc:")-1 )
    ////
    //stringBuffer.append(str.substring(str.indexOf("pc:")+4,str.indexOf("dob:") -1));
    //
    //str.substring(str.indexOf("pc:")+4,str.indexOf("dob:") -1);
    //
    address=  str.substring(str.indexOf("house:")+7,str.indexOf("loc:") -1)+" "+
        str.substring(str.indexOf("loc:")+5,str.indexOf("vtc:")-1 )+" "+
        str.substring(str.indexOf("subdist:")+9,str.indexOf("state:")-1 )+" "+
        str.substring(str.indexOf("state:")+7,str.indexOf("pc:")-1 )+" "+
        str.substring(str.indexOf("pc:")+4,str.indexOf("dob:") -1);


    etAddress.setText(""+address);

    //
    //stringBuffer.append("}");
    //
    //System.out.println(stringBuffer.toString());

    //
    //myWebView.post(new Runnable() {
    //  @Override public void run() {
    //    //myWebView.loadUrl("https://arctic.000webhostapp.com/2.html");
    //    String s="asad";
    //    String str="javascript:autoFill('"+uid+"');";
    //
    //    //myWebView.loadUrl("javascript:autoFill();");
    //    myWebView.loadUrl(str);
    //  }
    //});


    //
    //stringBuffer.append(str.substring(str.indexOf("yob="),str.indexOf("co=") )+", ");
    //
    //System.out.println();
    //
    //
    //
    //System.out.println();
    //
    //stringBuffer.append(+", ");
    //
    //System.out.println();
    //
    //stringBuffer.append(+", ");
    //
    //
    //System.out.println();
    //
    //System.out.println(str.substring(str.indexOf("vtc="),str.indexOf("po=") ));
    //
    //System.out.println(str.substring(str.indexOf("po="),str.indexOf("dist=") ));
    //
    //System.out.println(str.substring(str.indexOf("dist="),str.indexOf("subdist=") ));
    //
    //System.out.println();
    //
    //System.out.println();
    //
    //System.out.println();
    //
    //System.out.println();

  }



  public static boolean getCameraPermission(Activity activity) {
    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
          REQUEST);
    } else {
      return true;
    }
    return false;
  }
}
