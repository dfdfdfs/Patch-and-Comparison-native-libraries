package com.nativee.patcher;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aaa.bbb.ccc.TextEditor;
import com.rmartinper.filepicker.model.DialogProperties;
import com.rmartinper.filepicker.view.FilePickerDialog;

import org.apache.xml.utils.FastStringBuffer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity2 extends AppCompatActivity implements SearchEditText.OnSearchClickListener {
    public static File fil;
    private SearchEditText searchEditText;
    public static Context context;
    public TextEditor editor;
    public static int bufSize = 3000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        searchEditText = (SearchEditText) findViewById(R.id.searchEditText);
        searchEditText.setOnSearchClickListener(this);
        editor = (TextEditor) findViewById(R.id.xxx);
        editor.requestFocus();
        editor.setAutoIndentWidth(4);
        editor.setShowLineNumbers(true);
        editor.setHighlightCurrentRow(true);
        editor.setTabSpaces(4);
        context = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    public void piFile(View view) {
        String title = getString(R.string.file_title);
        String[] extensions = new String[]{"txt", "cs"};
        DialogProperties properties = new DialogProperties(true);
        properties.setExtensions(extensions);
        FilePickerDialog dialog = new FilePickerDialog(MainActivity2.this, properties);
        dialog.setTitle(title);
        dialog.setDialogSelectionListener(files -> {
            String file = files[0];
            //fil = new File(file);
            String fff = file;
            // String vbm = file;
            T(fff);

        });
        dialog.show();
    }

    private void T(String fff) {
        V f = new V(fff);
        f.startRead();
    }

    public class V {
        private int C = 8;
        private String file;
        private ExecutorService threadPool;

        public V(String fff) {
            this.threadPool = Executors.newFixedThreadPool(C);
            this.file = fff;
        }


        public void startRead() {

            threadPool.execute(new F());
            threadPool.shutdown();
        }

        public class F implements Runnable {
            @Override
            public void run() {

                CC();


            }

            public void CC() {
                FastStringBuffer sb = new FastStringBuffer();
                FileChannel fcin = null;
                try {
                    fcin = new RandomAccessFile(file, "r").getChannel();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
                String enter = "\n";
                List<String> dataList = new ArrayList<String>();
                byte[] lineByte = new byte[0];
                String encode = "UTF-8";
                try {
                    
                    byte[] temp = new byte[0];
                    while (fcin.read(rBuffer) != -1) {
                        int rSize = rBuffer.position();
                        byte[] bs = new byte[rSize];
                        rBuffer.rewind();
                        rBuffer.get(bs);
                        rBuffer.clear();

                        int startNum = 0;
                        int LF = 10;
                        int CR = 13;
                        boolean hasLF = false;
                        for (int i = 0; i < rSize; i++) {
                            if (bs[i] == LF) {
                                hasLF = true;
                                int tempNum = temp.length;
                                int lineNum = i - startNum;
                                lineByte = new byte[tempNum + lineNum];

                                System.arraycopy(temp, 0, lineByte, 0, tempNum);
                                temp = new byte[0];
                                System.arraycopy(bs, startNum, lineByte, tempNum, lineNum);

                                String line = new String(lineByte, 0, lineByte.length, encode);
                                dataList.add(line);


                                sb.append(line);
                                sb.append("\n");

                                if (i + 1 < rSize && bs[i + 1] == CR) {
                                    startNum = i + 2;
                                } else {
                                    startNum = i + 1;
                                }

                            }
                        

                        }

                        if (hasLF) {
                            temp = new byte[bs.length - startNum];
                            System.arraycopy(bs, startNum, temp, 0, temp.length);
                        } else {
                            byte[] toTemp = new byte[temp.length + bs.length];
                            System.arraycopy(temp, 0, toTemp, 0, temp.length);
                            System.arraycopy(bs, 0, toTemp, temp.length, bs.length);
                            temp = toTemp;
                        }

                    }
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            editor.setText(sb.toString());
                        }
                    });

                    if (temp != null && temp.length > 0) {
                        String line = new String(temp, 0, temp.length, encode);
                        dataList.add(line);
                        sb.append(line);
                        sb.append("\n");
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                editor.setText(sb.toString());
                            }
                        });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (OutOfMemoryError e) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity2.context, "Out memory,90kb! Используйте текстовый редактор 2!", Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        }


    }

    @Override
    public void onSearchClick(View view) {
        final String[] keyword = new String[1];
        final String[] text = new String[1];
        final int[] selection = new int[1];
        final int[] next = new int[1];
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                keyword[0] = searchEditText.getText().toString();
                text[0] = editor.getText().toString();
                selection[0] = editor.getSelectionEnd();
                if (keyword[0].length() == 0) {
                    Toast.makeText(MainActivity2.this, "Введите текст для поиска!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Settings.SEARCHMATCHCASE) {
                    keyword[0] = keyword[0].toLowerCase();
                    text[0] = text[0].toLowerCase();
                }

                next[0] = text[0].indexOf(keyword[0], selection[0]);
                if (next[0] > -1) {
                    editor.setSelection(next[0], next[0] + keyword[0].length());
                    if (!editor.isFocused())
                        editor.requestFocus();
                } else {
                    if (Settings.SEARCHWRAP) {
                        next[0] = text[0].indexOf(keyword[0]);
                        if (next[0] > -1) {
                            editor.setSelection(next[0], next[0] + keyword[0].length());
                            if (!editor.isFocused())
                                editor.requestFocus();
                        } else {
                            Toast.makeText(MainActivity2.this, "Не найдено!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity2.this, "Не найдено!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }


}






