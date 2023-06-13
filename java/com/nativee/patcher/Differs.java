package com.nativee.patcher;

import android.widget.Toast;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Differs {

    public static final int BUFFER_SIZE_BYTES = 9000;

    public void HHH(String aa, String bb) {
        byte[] buffer1 = new byte[BUFFER_SIZE_BYTES];
        byte[] buffer2 = new byte[BUFFER_SIZE_BYTES];
        FileInputStream inputStream1;
        FileInputStream inputStream2;
        int bytesRead1;
        int bytesRead2;
        long index = 0;
        long numberOfDifferentBytes = 0;
        File fileDir = new File("sdcard/ResultCompare.txt");

        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), StandardCharsets.UTF_8));
            inputStream1 = new FileInputStream(aa);
            inputStream2 = new FileInputStream(bb);

            while (true) {

                bytesRead1 = inputStream1.read(buffer1);
                bytesRead2 = inputStream2.read(buffer2);

                for (int i = 0; i < Math.min(bytesRead1, bytesRead2); i++) {
                    if (buffer1[i] != buffer2[i]) {
                        String h = String.format("Offset: %06X Modification bytes: %02X Original bytes: %02X", index,
                                buffer1[i], buffer2[i]);
                        out.append(h).append("\n");
                        out.flush();

                        numberOfDifferentBytes++;
                    }
                    index++;
                }

                if (bytesRead1 == -1 || bytesRead2 == -1) {
                    if (bytesRead1 == -1) {
                        //   System.err.println("End of File 1 reached.");
                    }
                    if (bytesRead2 == -1) {
                        //   System.err.println("End of File 2 reached.");
                    }

                    if (bytesRead1 == -1 && bytesRead2 == -1) {
                        if (numberOfDifferentBytes == 0) {
                            out.append("No differences found!").append("\n");
                            // out.append("Результат в файле:" + "sdcard/ResultCompare.txt");
                            out.flush();
                            //   Toast.makeText(Patch.context1, "Отличий не найдено!", Toast.LENGTH_LONG).show();
                            //  System.out.println("Files are identical!");
                        } else {
                            //  System.out.println(
                            String n = String.format("Found %d differences.", numberOfDifferentBytes);
                            out.append(n).append("\n");
                            out.flush();

                        }
                    } else {
                        if (numberOfDifferentBytes == 0) {
                            out.append("Files of different sizes!").append("\n");
                            out.flush();
                            // Toast.makeText(Patch.context1, "Файлы разного размера!", Toast.LENGTH_LONG).show();
                            // System.out.println("Files have different length.");
                        } else {
                            String bn = String.format("Files of different sizes %d bytes differences!", numberOfDifferentBytes);
                            out.append(bn).append("\n");
                            out.flush();
                            //System.out.println(
                            //   String.format("Files have different length and %d bytes are differ.", numberOfDifferentBytes)
                            // );
                        }
                    }

                    break;
                }

            }

        } catch (FileNotFoundException ex) {
            Toast.makeText(Patch.context1, "Файл не найден!", Toast.LENGTH_LONG).show();
        } catch (IOException ex) {
            Toast.makeText(Patch.context1, "Невозможно прочитать файл!", Toast.LENGTH_LONG).show();
        }

    }


}
