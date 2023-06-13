package com.nativee.patcher;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aaa.bbb.ccc.TextEditor;
import com.rmartinper.filepicker.model.DialogProperties;
import com.rmartinper.filepicker.view.FilePickerDialog;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class Patch extends AppCompatActivity {
    public static String a;
    public static String b;
    private ProgressDialog xxx;
    private ProgressDialog v;
    public static Context context1;
    private static TextView gg;
    public static ScrollView scrollView;
    public int h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patch);
        Button po = findViewById(R.id.po);
        Button wa = findViewById(R.id.wa);
        context1 = this;
        scrollView = (ScrollView) findViewById(R.id.scrollVie);
        gg = (TextView) findViewById(R.id.TEXT_STATUS_ID);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void picFile(View view) {
        String title = getString(R.string.file_title);
        String[] extensions = new String[]{"so"};
        DialogProperties properties = new DialogProperties(true);
        properties.setExtensions(extensions);
        FilePickerDialog dialog = new FilePickerDialog(Patch.this, properties);
        dialog.setTitle(title);
        dialog.setDialogSelectionListener(files -> {
            String file = files[0];
            if (view.getId() == R.id.po) {
                a = file;
                Toast.makeText(this, "Файл 1 = " + a, Toast.LENGTH_LONG).show();
            } else if (view.getId() == R.id.wa) {
                b = file;
                Toast.makeText(this, "Файл 2 = " + b, Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }

    public void ccc(View view) {
        picFile(view);
    }

    public void zzz(View view) {
        picFile(view);
    }

    public void nnt(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void bbb(View view) {
        Intent intent = new Intent(this, AntiOOM.class);
        startActivity(intent);
    }

    public void jkl(View view) {
        if (a == null || b == null) {
            Toast.makeText(this, "Выберите файлы!!!", Toast.LENGTH_LONG).show();
        } else {
            fig();
        }
    }


    private void fig() {
        mHandler.sendEmptyMessage(0);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String aa = a;
                    String bb = b;
                    Differs kkk = new Differs();
                    kkk.HHH(aa, bb);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(1);
            }
        });
        thread.start();
    }

    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    v = new ProgressDialog(Patch.context1);
                    v.setTitle("");
                    v.setCancelable(false);
                    v.setMessage("Please loading..." + "\nРезультат в файле:" + "sdcard/ResultCompare.txt");
                    v.show();
                    break;
                case 1:
                    v.dismiss();
                    fgfgp();

            }
        }
    };

    private void fgfgp() {
        final Dialog xxx = new Dialog(Patch.context1);
        xxx.setContentView(R.layout.custom1);
        xxx.setCancelable(false);
        xxx.setTitle("");
        Button dialogy = (Button) xxx.findViewById(R.id.button6);
        Button dial = (Button) xxx.findViewById(R.id.button5);
        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xxx.dismiss();
            }
        });
        dialogy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    fffh(xxx);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        xxx.show();
    }

    private void fffh(Dialog xxx) throws IOException {
        xxx.dismiss();
        String vbm = "sdcard/ResultCompare.txt";
        StringBuilder sb = null;
        final int PAGE_SIZE = 1260;
        int p = 0;
        sb = new StringBuilder();
        RandomAccessFile raf = new RandomAccessFile(vbm, "r");
        long fileLength = raf.length();
        long pagesCount = fileLength / PAGE_SIZE;
        Toast.makeText(Patch.context1, "Количество страниц = " + pagesCount + 1, Toast.LENGTH_SHORT).show();
        raf.seek(p * PAGE_SIZE);
        for (int i = 0; i < PAGE_SIZE; i++) {
            char j = (char) raf.read();
            String fad = String.valueOf(j);
            sb.append(fad);

        }
        gg.setText(sb.toString());
        StringBuilder finalSb = sb;
        scrollView.getViewTreeObserver()
                .addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        if (scrollView.getChildAt(0).getBottom()
                                <= (scrollView.getHeight() + scrollView.getScrollY())) {
                            int PAGE_SIZE = 1260;
                            while (true) {
                                try {
                                    if (!(raf.readLine() != null)) break;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                h += 1;
                              
                                try {
                                    raf.seek((long) h * PAGE_SIZE);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                for (int i = 0; i < PAGE_SIZE; i++) {
                                    char kkk = 0;
                                    try {
                                        kkk = (char) raf.read();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    String fad = String.valueOf(kkk);
                                    finalSb.append(fad);

                                }
                                gg.setText(finalSb.toString());
                                break;
                            }

                        }
                    }


                });
    }






}
