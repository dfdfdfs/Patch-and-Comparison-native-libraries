package com.nativee.patcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rmartinper.filepicker.model.DialogProperties;
import com.rmartinper.filepicker.view.FilePickerDialog;

import org.apache.http.util.EncodingUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AntiOOM extends AppCompatActivity {
    long offsetread = 0;
    public static String fileread = "NONE";

    int Lines = 100;
    public static FloatingActionButton fs;
    public FloatingActionButton FS;
    public static CD fas;
    public static ScrollView scrollView;
    List<Long> offsethistory = new ArrayList<Long>();
    String ENCOD = "UTF-8";
    private int oldScrollYPostion = 0;
   // android:background="@null"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fack_oom);
        Toast.makeText(this, "Двойной клик для показа первой страницы", Toast.LENGTH_SHORT).show();
        fas = (CD) findViewById(R.id.lineNumberEditText);
     //   fas.setTypeface(Typeface.defaultFromStyle(3));

       // fas.setLineNumberColor(Color.GRAY);
     //   fas.setLineNumberSize(35);
        //fas.setTextColor(Color.GRAY);
      //  fas.setTextSize(12);
        fs = findViewById(R.id.f);
        FS = findViewById(R.id.c);
        scrollView = (ScrollView) findViewById(R.id.scrollVie);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    public void p(View view) {
        String title = getString(R.string.file_title);
        String[] extensions = new String[]{"txt", "cs"};
        DialogProperties properties = new DialogProperties(true);
        properties.setExtensions(extensions);
        FilePickerDialog dialog = new FilePickerDialog(AntiOOM.this, properties);
        dialog.setTitle(title);
        dialog.setDialogSelectionListener(files -> {
            String file = files[0];
            fileread = file;
            XZ(fileread, offsetread, Lines, ENCOD, fas);
        });
        dialog.show();
    }

    public long XZ(String filename, long offset, int Lines, String encod, CD fas) {
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (scrollView.getScrollY() > oldScrollYPostion) {
                    fs.hide();
                    FS.hide();
                } else if (scrollView.getScrollY() < oldScrollYPostion || scrollView.getScrollY() <= 0) {
                    fs.show();
                    FS.show();
                }
                oldScrollYPostion = scrollView.getScrollY();
            }
        });
        File fi = new File(filename);

        long offsetbakl = 0;
        long offsettmp;
        offsettmp = 0;
        // было 10 000 
        byte[] buf = new byte[1000000];
        try {
            InputStream fis = new FileInputStream(fi);
            fis.skip(0);
            if (offset > fis.available()) {
                offset = fis.available();

            }
            fis.skip(offset);
            for (int i = 0; i < Lines; ) {

                buf[((int) offsettmp)] = (byte) fis.read();
                if (buf[((int) offsettmp)] == -1)
                    break;
                if (buf[((int) offsettmp)] == (byte) 10) {
                    i++;
                }
                offsettmp++;
                if (i == Lines - 2) {
                    offsetbakl = offset + offsettmp;
                }

            }
            AntiOOM.fas.setText(String.valueOf(fis.available()));
            fis.close();
            String tmp = "";
            tmp = EncodingUtils.getString(buf, encod);
            AntiOOM.fas.setText(tmp);
        } catch (IOException e) {
            AntiOOM.fas.setText("Выберите файл!!!");
            return 0;
        }
        return offsetbakl;
    }

    public void PERRRR(View view) {
        Toast.makeText(this, "Количество страниц = " + String.valueOf(offsetread), Toast.LENGTH_SHORT).show();
        offsethistory.add(offsetread);
        offsetread = XZ(fileread, offsetread, Lines, ENCOD, fas);
    }

    public void VADImmm(View view) {
        if (offsethistory.size() > 1) {
            offsetread = offsethistory.get(offsethistory.size() - 2);
            offsethistory.remove(offsethistory.size() - 1);

            offsetread = XZ(fileread, offsetread, Lines, ENCOD, fas);
        } else {
            Toast.makeText(this, "Конец!", Toast.LENGTH_SHORT).show();
        }
    }
}