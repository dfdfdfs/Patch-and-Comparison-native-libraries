package com.nativee.patcher;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.rmartinper.filepicker.model.DialogProperties;
import com.rmartinper.filepicker.view.FilePickerDialog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    private static TextView textView;

    private static RandomAccessFile sss;
    public static Context context;
    SharedPreferences sPref;
    final String x = (new Object() {int t;public String toString() {byte[] buf = new byte[1];t = -1493420187;buf[0] = (byte) (t >>> 7);return new String(buf);}}.toString());
    final String vc = (new Object() {int t;public String toString() {byte[] buf = new byte[1];t = -1497968008;buf[0] = (byte) (t >>> 15);return new String(buf);}}.toString());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath((new Object() {int t;public String toString() {byte[] buf = new byte[13];t = -1498197384;buf[0] = (byte) (t >>> 15);t = 771283345;buf[1] = (byte) (t >>> 21);t = -1448813365;buf[2] = (byte) (t >>> 9);t = 1855633789;buf[3] = (byte) (t >>> 21);t = 969042246;buf[4] = (byte) (t >>> 23);t = 1504109756;buf[5] = (byte) (t >>> 2);t = 1577543158;buf[6] = (byte) (t >>> 12);t = 1859264944;buf[7] = (byte) (t >>> 21);t = 1261359568;buf[8] = (byte) (t >>> 13);t = -233147675;buf[9] = (byte) (t >>> 4);t = 404049540;buf[10] = (byte) (t >>> 5);t = 585702401;buf[11] = (byte) (t >>> 17);t = 1892530955;buf[12] = (byte) (t >>> 17);return new String(buf);}}.toString()))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        context = this;
        TextView cs = findViewById(R.id.cs);
        Button button = findViewById(R.id.button);
        Button ARM = findViewById(R.id.ARM);
        Button AAA = findViewById(R.id.AAA);
        Button mm = findViewById(R.id.mm);
        EditText nameBox1 = findViewById(R.id.nameBox1);
        EditText nameBox = findViewById(R.id.nameBox);
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(x, (new Object() {int t;public String toString() {byte[] buf = new byte[0];return new String(buf);}}.toString()));
        String saveText = sPref.getString(vc, (new Object() {int t;public String toString() {byte[] buf = new byte[0];return new String(buf);}}.toString()));
        nameBox1.setText(savedText);
        nameBox.setText(saveText);
       // Toast.makeText(this, (new Object() {int t;public String toString() {byte[] buf = new byte[11];t = -1437394710;buf[0] = (byte) (t >>> 23);t = 1696885584;buf[1] = (byte) (t >>> 24);t = 395216113;buf[2] = (byte) (t >>> 20);t = 684705600;buf[3] = (byte) (t >>> 4);t = 1162356435;buf[4] = (byte) (t >>> 14);t = 1664693839;buf[5] = (byte) (t >>> 7);t = -694423678;buf[6] = (byte) (t >>> 14);t = 616350416;buf[7] = (byte) (t >>> 9);t = -1280630332;buf[8] = (byte) (t >>> 11);t = -355734243;buf[9] = (byte) (t >>> 17);t = -741320402;buf[10] = (byte) (t >>> 6);return new String(buf);}}.toString()), Toast.LENGTH_SHORT).show();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }
    }

    @Override
    protected void attachBaseContext(Context MainActivity) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(MainActivity));
    }

    public void pickFile(View view) {
        String title = getString(R.string.file_title);
        String[] extensions = new String[]{(new Object() {int t;public String toString() {byte[] buf = new byte[2];t = -1494959695;buf[0] = (byte) (t >>> 7);t = -1477591440;buf[1] = (byte) (t >>> 10);return new String(buf);}}.toString())};
        DialogProperties properties = new DialogProperties(true);
        properties.setExtensions(extensions);
        FilePickerDialog dialog = new FilePickerDialog(MainActivity.this, properties);
        dialog.setTitle(title);
        dialog.setDialogSelectionListener(files -> {
            String file = files[0];
            try {
                sss = new RandomAccessFile(file, (new Object() {int t;public String toString() {byte[] buf = new byte[2];t = -1497803068;buf[0] = (byte) (t >>> 15);t = -680139418;buf[1] = (byte) (t >>> 20);return new String(buf);}}.toString()));
                Toast.makeText(context, (new Object() {int t;public String toString() {byte[] buf = new byte[13];t = -1498818876;buf[0] = (byte) (t >>> 15);t = -699013786;buf[1] = (byte) (t >>> 20);t = -241484060;buf[2] = (byte) (t >>> 14);t = -1740007884;buf[3] = (byte) (t >>> 10);t = -2015697356;buf[4] = (byte) (t >>> 4);t = 175392576;buf[5] = (byte) (t >>> 16);t = -2010497965;buf[6] = (byte) (t >>> 22);t = 751036357;buf[7] = (byte) (t >>> 21);t = -1260741517;buf[8] = (byte) (t >>> 23);t = 1825971120;buf[9] = (byte) (t >>> 24);t = -1719884405;buf[10] = (byte) (t >>> 22);t = 30229397;buf[11] = (byte) (t >>> 18);t = 147981882;buf[12] = (byte) (t >>> 12);return new String(buf);}}.toString()) + file, Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });
        dialog.show();
    }


    public void FJ(View view) {
        Intent intent = new Intent(this, Patch.class);
        startActivity(intent);
    }

    public void BN(View view) {
        XR();
    }

    private void XR() {
        if (DS()) {
            Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse((new Object() {int t;public String toString() {byte[] buf = new byte[25];t = -1271161545;buf[0] = (byte) (t >>> 23);t = -675645659;buf[1] = (byte) (t >>> 15);t = 1570410728;buf[2] = (byte) (t >>> 1);t = 1222755655;buf[3] = (byte) (t >>> 17);t = -1012947662;buf[4] = (byte) (t >>> 19);t = 340695276;buf[5] = (byte) (t >>> 14);t = 788780511;buf[6] = (byte) (t >>> 24);t = -156344603;buf[7] = (byte) (t >>> 9);t = -111797145;buf[8] = (byte) (t >>> 12);t = -412200968;buf[9] = (byte) (t >>> 13);t = 1354147381;buf[10] = (byte) (t >>> 15);t = 1397432051;buf[11] = (byte) (t >>> 11);t = -606726399;buf[12] = (byte) (t >>> 22);t = 1861475204;buf[13] = (byte) (t >>> 6);t = -232541631;buf[14] = (byte) (t >>> 11);t = -1492359864;buf[15] = (byte) (t >>> 8);t = -1807063250;buf[16] = (byte) (t >>> 4);t = -830235487;buf[17] = (byte) (t >>> 21);t = -1594701270;buf[18] = (byte) (t >>> 9);t = 1634627147;buf[19] = (byte) (t >>> 5);t = -64776430;buf[20] = (byte) (t >>> 7);t = 837285809;buf[21] = (byte) (t >>> 23);t = -185147761;buf[22] = (byte) (t >>> 9);t = 886811133;buf[23] = (byte) (t >>> 17);t = 387416162;buf[24] = (byte) (t >>> 15);return new String(buf);}}.toString())));
            startActivity(intent);
        } else {
            Toast.makeText(context, (new Object() {int t;public String toString() {byte[] buf = new byte[42];t = -6019;buf[0] = (byte) (t >>> 7);t = -99771;buf[1] = (byte) (t >>> 10);t = -5897;buf[2] = (byte) (t >>> 7);t = -60995;buf[3] = (byte) (t >>> 9);t = -381;buf[4] = (byte) (t >>> 3);t = -570;buf[5] = (byte) (t >>> 3);t = -6119;buf[6] = (byte) (t >>> 7);t = -658949035;buf[7] = (byte) (t >>> 23);t = -1523;buf[8] = (byte) (t >>> 5);t = -4460;buf[9] = (byte) (t >>> 6);t = -25025056;buf[10] = (byte) (t >>> 19);t = -320;buf[11] = (byte) (t >>> 2);t = 2048336925;buf[12] = (byte) (t >>> 5);t = -12043;buf[13] = (byte) (t >>> 8);t = -553921432;buf[14] = (byte) (t >>> 23);t = -379;buf[15] = (byte) (t >>> 3);t = -77730629;buf[16] = (byte) (t >>> 20);t = -193023307;buf[17] = (byte) (t >>> 22);t = -2015;buf[18] = (byte) (t >>> 4);t = 1246562323;buf[19] = (byte) (t >>> 6);t = -1528;buf[20] = (byte) (t >>> 5);t = -65900;buf[21] = (byte) (t >>> 10);t = -24416;buf[22] = (byte) (t >>> 9);t = -1100436848;buf[23] = (byte) (t >>> 24);t = -12183;buf[24] = (byte) (t >>> 8);t = -2412;buf[25] = (byte) (t >>> 5);t = -191;buf[26] = (byte) (t >>> 2);t = -1167635614;buf[27] = (byte) (t >>> 24);t = -24786006;buf[28] = (byte) (t >>> 19);t = -140804;buf[29] = (byte) (t >>> 11);t = -95372;buf[30] = (byte) (t >>> 11);t = -3710559;buf[31] = (byte) (t >>> 15);t = -23752;buf[32] = (byte) (t >>> 9);t = -1928;buf[33] = (byte) (t >>> 4);t = -96620;buf[34] = (byte) (t >>> 11);t = -76501;buf[35] = (byte) (t >>> 10);t = -99380218;buf[36] = (byte) (t >>> 21);t = -2192266;buf[37] = (byte) (t >>> 15);t = -49536813;buf[38] = (byte) (t >>> 20);t = -74690463;buf[39] = (byte) (t >>> 20);t = -1495;buf[40] = (byte) (t >>> 5);t = -7190;buf[41] = (byte) (t >>> 6);return new String(buf);}}.toString()), Toast.LENGTH_LONG).show();
        }

    }

    private boolean DS() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void DFD(View view) {
        ffff();
    }


    public void VV(View view) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom);
        dialog.setCancelable(false);
        TextView text = (TextView) dialog.findViewById(R.id.text);
        Button dialogButton = (Button) dialog.findViewById(R.id.S);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void ffff() {
        EditText nameBox1 = findViewById(R.id.nameBox1);
        EditText nameBox = findViewById(R.id.nameBox);
        String s = nameBox1.getText().toString();
        String S1 = nameBox.getText().toString();
        L(s, S1);
        if (S1.contains("0x")) {
            S1 = S1.replace("0x", "");
        }
        if (S1.isEmpty() || s.isEmpty()) {
            Toast.makeText(context, (new Object() {int t;public String toString() {byte[] buf = new byte[37];t = -3062;buf[0] = (byte) (t >>> 6);t = -53734;buf[1] = (byte) (t >>> 9);t = -195568;buf[2] = (byte) (t >>> 12);t = -333363595;buf[3] = (byte) (t >>> 22);t = -12087;buf[4] = (byte) (t >>> 8);t = -8457933;buf[5] = (byte) (t >>> 17);t = -3117194;buf[6] = (byte) (t >>> 16);t = -550829462;buf[7] = (byte) (t >>> 23);t = -3057;buf[8] = (byte) (t >>> 6);t = -70482;buf[9] = (byte) (t >>> 10);t = -97369;buf[10] = (byte) (t >>> 11);t = -137000;buf[11] = (byte) (t >>> 11);t = -3098164;buf[12] = (byte) (t >>> 16);t = -4599;buf[13] = (byte) (t >>> 6);t = -6071245;buf[14] = (byte) (t >>> 17);t = -252;buf[15] = (byte) (t >>> 1);t = -394708075;buf[16] = (byte) (t >>> 23);t = -76476;buf[17] = (byte) (t >>> 10);t = -1742698285;buf[18] = (byte) (t >>> 16);t = -1506;buf[19] = (byte) (t >>> 5);t = -81003168;buf[20] = (byte) (t >>> 20);t = -1534618;buf[21] = (byte) (t >>> 15);t = -16161;buf[22] = (byte) (t >>> 7);t = -95;buf[23] = (byte) (t >>> 1);t = -4790;buf[24] = (byte) (t >>> 6);t = -1846409994;buf[25] = (byte) (t >>> 12);t = -3105578;buf[26] = (byte) (t >>> 16);t = -270808208;buf[27] = (byte) (t >>> 22);t = -97324;buf[28] = (byte) (t >>> 11);t = -2150782;buf[29] = (byte) (t >>> 15);t = -399880998;buf[30] = (byte) (t >>> 23);t = -549;buf[31] = (byte) (t >>> 3);t = -24489246;buf[32] = (byte) (t >>> 19);t = -900;buf[33] = (byte) (t >>> 3);t = -1164671183;buf[34] = (byte) (t >>> 10);t = 687300742;buf[35] = (byte) (t >>> 2);t = 1120945083;buf[36] = (byte) (t >>> 9);return new String(buf);}}.toString()), Toast.LENGTH_LONG).show();
        } else {
            try {
                int i = Integer.parseInt(S1, 16);
                F(i, s);
            } catch (NumberFormatException e) {
                Toast.makeText(context, (new Object() {int t;public String toString() {byte[] buf = new byte[70];t = -778403;buf[0] = (byte) (t >>> 14);t = -50856688;buf[1] = (byte) (t >>> 19);t = -392653034;buf[2] = (byte) (t >>> 23);t = -243806;buf[3] = (byte) (t >>> 11);t = -99883935;buf[4] = (byte) (t >>> 21);t = -587398;buf[5] = (byte) (t >>> 13);t = -24306;buf[6] = (byte) (t >>> 9);t = -642065;buf[7] = (byte) (t >>> 13);t = -96;buf[8] = (byte) (t >>> 1);t = -8859;buf[9] = (byte) (t >>> 7);t = -796176104;buf[10] = (byte) (t >>> 24);t = -163139;buf[11] = (byte) (t >>> 11);t = 822434968;buf[12] = (byte) (t >>> 19);t = -1522;buf[13] = (byte) (t >>> 5);t = -16602;buf[14] = (byte) (t >>> 8);t = -24158060;buf[15] = (byte) (t >>> 19);t = -130229;buf[16] = (byte) (t >>> 10);t = -1569304;buf[17] = (byte) (t >>> 15);t = -2133637;buf[18] = (byte) (t >>> 15);t = -767;buf[19] = (byte) (t >>> 4);t = -78930;buf[20] = (byte) (t >>> 10);t = -3046;buf[21] = (byte) (t >>> 6);t = -1185;buf[22] = (byte) (t >>> 4);t = -12168123;buf[23] = (byte) (t >>> 18);t = -532724301;buf[24] = (byte) (t >>> 22);t = -189954;buf[25] = (byte) (t >>> 12);t = -59220;buf[26] = (byte) (t >>> 9);t = -3019917;buf[27] = (byte) (t >>> 16);t = -8033;buf[28] = (byte) (t >>> 6);t = -3092060;buf[29] = (byte) (t >>> 16);t = -76492;buf[30] = (byte) (t >>> 10);t = 1539899734;buf[31] = (byte) (t >>> 11);t = -24116;buf[32] = (byte) (t >>> 9);t = -609042356;buf[33] = (byte) (t >>> 23);t = -195496;buf[34] = (byte) (t >>> 12);t = -654919;buf[35] = (byte) (t >>> 13);t = -760;buf[36] = (byte) (t >>> 4);t = -520;buf[37] = (byte) (t >>> 3);t = -3070;buf[38] = (byte) (t >>> 6);t = -4310691;buf[39] = (byte) (t >>> 16);t = -24139;buf[40] = (byte) (t >>> 9);t = -1153765704;buf[41] = (byte) (t >>> 24);t = -12354708;buf[42] = (byte) (t >>> 18);t = -4234;buf[43] = (byte) (t >>> 6);t = -6192441;buf[44] = (byte) (t >>> 17);t = -9730685;buf[45] = (byte) (t >>> 17);t = -194905;buf[46] = (byte) (t >>> 12);t = -561378002;buf[47] = (byte) (t >>> 23);t = -95;buf[48] = (byte) (t >>> 1);t = -8475;buf[49] = (byte) (t >>> 7);t = -24042;buf[50] = (byte) (t >>> 9);t = -61229535;buf[51] = (byte) (t >>> 19);t = -3088328;buf[52] = (byte) (t >>> 16);t = -19459805;buf[53] = (byte) (t >>> 18);t = -1841285008;buf[54] = (byte) (t >>> 17);t = -24687392;buf[55] = (byte) (t >>> 19);t = -630733914;buf[56] = (byte) (t >>> 23);t = -1511;buf[57] = (byte) (t >>> 5);t = -333596735;buf[58] = (byte) (t >>> 22);t = -754;buf[59] = (byte) (t >>> 4);t = -8520;buf[60] = (byte) (t >>> 7);t = -99654444;buf[61] = (byte) (t >>> 21);t = -67607;buf[62] = (byte) (t >>> 10);t = -94266;buf[63] = (byte) (t >>> 11);t = -1901015;buf[64] = (byte) (t >>> 14);t = -12499367;buf[65] = (byte) (t >>> 18);t = -77827813;buf[66] = (byte) (t >>> 20);t = 1910653523;buf[67] = (byte) (t >>> 9);t = 236513413;buf[68] = (byte) (t >>> 2);t = 1804936252;buf[69] = (byte) (t >>> 5);return new String(buf);}}.toString()), Toast.LENGTH_LONG).show();
            } catch (NullPointerException e) {
                Toast.makeText(context, (new Object() {int t;public String toString() {byte[] buf = new byte[28];t = -397516199;buf[0] = (byte) (t >>> 23);t = -28578510;buf[1] = (byte) (t >>> 18);t = -94;buf[2] = (byte) (t >>> 1);t = -14897;buf[3] = (byte) (t >>> 7);t = -12188;buf[4] = (byte) (t >>> 8);t = -321033;buf[5] = (byte) (t >>> 12);t = -402401768;buf[6] = (byte) (t >>> 23);t = -599;buf[7] = (byte) (t >>> 3);t = -95761;buf[8] = (byte) (t >>> 11);t = -260574;buf[9] = (byte) (t >>> 11);t = -782822;buf[10] = (byte) (t >>> 14);t = -4585;buf[11] = (byte) (t >>> 6);t = -2981;buf[12] = (byte) (t >>> 6);t = -525438584;buf[13] = (byte) (t >>> 22);t = -6264440;buf[14] = (byte) (t >>> 17);t = -153335;buf[15] = (byte) (t >>> 11);t = -1313370097;buf[16] = (byte) (t >>> 7);t = -95069;buf[17] = (byte) (t >>> 11);t = -991;buf[18] = (byte) (t >>> 3);t = -199665356;buf[19] = (byte) (t >>> 22);t = -669429390;buf[20] = (byte) (t >>> 23);t = -388845;buf[21] = (byte) (t >>> 13);t = -73453624;buf[22] = (byte) (t >>> 20);t = -6025;buf[23] = (byte) (t >>> 7);t = -35922445;buf[24] = (byte) (t >>> 19);t = -1336860392;buf[25] = (byte) (t >>> 8);t = -385121003;buf[26] = (byte) (t >>> 19);t = -259503338;buf[27] = (byte) (t >>> 14);return new String(buf);}}.toString()), Toast.LENGTH_LONG).show();
            }
        }
    }


    public void L(String s, String S1) {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(x, s);
        ed.putString(vc, S1);
        ed.apply();
      //  Toast.makeText(this, (new Object() {int t;public String toString() {byte[] buf = new byte[10];t = -1488410004;buf[0] = (byte) (t >>> 7);t = -971616216;buf[1] = (byte) (t >>> 12);t = -1381037964;buf[2] = (byte) (t >>> 13);t = -2025215748;buf[3] = (byte) (t >>> 20);t = -936595717;buf[4] = (byte) (t >>> 22);t = -302270018;buf[5] = (byte) (t >>> 7);t = 1637707695;buf[6] = (byte) (t >>> 24);t = 183364710;buf[7] = (byte) (t >>> 17);t = 1467210071;buf[8] = (byte) (t >>> 6);t = 17392856;buf[9] = (byte) (t >>> 8);return new String(buf);}}.toString()), Toast.LENGTH_SHORT).show();
    }

    private static void F(int i, String s) {
        try {
            sss.seek(i);
            sss.write(G(s));
            Toast.makeText(context, (new Object() {int t;public String toString() {byte[] buf = new byte[12];t = -1564621;buf[0] = (byte) (t >>> 15);t = -455252152;buf[1] = (byte) (t >>> 22);t = -24821919;buf[2] = (byte) (t >>> 19);t = -539277;buf[3] = (byte) (t >>> 13);t = -2956;buf[4] = (byte) (t >>> 6);t = -263042711;buf[5] = (byte) (t >>> 21);t = -3031;buf[6] = (byte) (t >>> 6);t = -2156586;buf[7] = (byte) (t >>> 15);t = -398973795;buf[8] = (byte) (t >>> 23);t = -312;buf[9] = (byte) (t >>> 2);t = -3017;buf[10] = (byte) (t >>> 6);t = -4211;buf[11] = (byte) (t >>> 6);return new String(buf);}}.toString()), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] G(String str) {
        if (str.contains(" ")) {
            str = str.replace(" ", "");
        }
        if (str == null) {
            throw new IllegalArgumentException((new Object() {int t;public String toString() {byte[] buf = new byte[11];t = -1489747642;buf[0] = (byte) (t >>> 15);t = 1866753341;buf[1] = (byte) (t >>> 8);t = -1986133401;buf[2] = (byte) (t >>> 14);t = -217930623;buf[3] = (byte) (t >>> 2);t = 568881100;buf[4] = (byte) (t >>> 19);t = -1791139408;buf[5] = (byte) (t >>> 16);t = 476712960;buf[6] = (byte) (t >>> 7);t = 571149787;buf[7] = (byte) (t >>> 5);t = 887842630;buf[8] = (byte) (t >>> 17);t = 455863659;buf[9] = (byte) (t >>> 22);t = 595065264;buf[10] = (byte) (t >>> 2);return new String(buf);}}.toString()));
        } else if (str.length() % 2 != 0) {
            throw new IllegalArgumentException((new Object() {int t;public String toString() {byte[] buf = new byte[23];t = -1430853978;buf[0] = (byte) (t >>> 23);t = 1222441223;buf[1] = (byte) (t >>> 17);t = 1672436039;buf[2] = (byte) (t >>> 6);t = -679203706;buf[3] = (byte) (t >>> 20);t = -97645118;buf[4] = (byte) (t >>> 13);t = -241902828;buf[5] = (byte) (t >>> 18);t = 15386229;buf[6] = (byte) (t >>> 9);t = -394706010;buf[7] = (byte) (t >>> 3);t = -1934642031;buf[8] = (byte) (t >>> 21);t = -38635268;buf[9] = (byte) (t >>> 15);t = -1075244959;buf[10] = (byte) (t >>> 7);t = -1137430741;buf[11] = (byte) (t >>> 15);t = -761414454;buf[12] = (byte) (t >>> 1);t = 1271290267;buf[13] = (byte) (t >>> 19);t = -34012844;buf[14] = (byte) (t >>> 11);t = -1178933342;buf[15] = (byte) (t >>> 9);t = -361639448;buf[16] = (byte) (t >>> 10);t = -1184811214;buf[17] = (byte) (t >>> 23);t = 1345620571;buf[18] = (byte) (t >>> 15);t = -1379553153;buf[19] = (byte) (t >>> 21);t = 840563371;buf[20] = (byte) (t >>> 14);t = -790001692;buf[21] = (byte) (t >>> 18);t = -2076062457;buf[22] = (byte) (t >>> 17);return new String(buf);}}.toString()) + str);
        } else {
            byte[] bArr = new byte[(str.length() / 2)];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) ((X(str.charAt(i * 2)) << 4) + X(str.charAt((i * 2) + 1)));

            }
            return bArr;
        }
    }

    private static int X(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 65) + 10;
        }
        throw new IllegalArgumentException((new Object() {int t;public String toString() {byte[] buf = new byte[22];t = -1490377601;buf[0] = (byte) (t >>> 15);t = -304450787;buf[1] = (byte) (t >>> 21);t = 1706118010;buf[2] = (byte) (t >>> 24);t = 1271737072;buf[3] = (byte) (t >>> 1);t = 1018398355;buf[4] = (byte) (t >>> 11);t = -1707168978;buf[5] = (byte) (t >>> 3);t = -664598202;buf[6] = (byte) (t >>> 16);t = -4563477;buf[7] = (byte) (t >>> 15);t = 1759339840;buf[8] = (byte) (t >>> 8);t = -480113;buf[9] = (byte) (t >>> 5);t = -1873446659;buf[10] = (byte) (t >>> 23);t = 1278483297;buf[11] = (byte) (t >>> 15);t = -2028046765;buf[12] = (byte) (t >>> 4);t = 1468687761;buf[13] = (byte) (t >>> 20);t = -628609682;buf[14] = (byte) (t >>> 14);t = 419997270;buf[15] = (byte) (t >>> 22);t = -837381508;buf[16] = (byte) (t >>> 12);t = -1775022869;buf[17] = (byte) (t >>> 5);t = 1625084682;buf[18] = (byte) (t >>> 9);t = 489653465;buf[19] = (byte) (t >>> 22);t = -911638058;buf[20] = (byte) (t >>> 3);t = 688213116;buf[21] = (byte) (t >>> 19);return new String(buf);}}.toString()) + c);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String permGranted = getString(R.string.perm_granted);
                Toast.makeText(this, permGranted, Toast.LENGTH_SHORT).show();
            } else {
                String permDenied = getString(R.string.perm_denied);
                Toast.makeText(this, permDenied, Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
