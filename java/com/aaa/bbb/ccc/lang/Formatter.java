package com.aaa.bbb.ccc.lang;

public interface Formatter {
    public int createAutoIndent(CharSequence text);

    public CharSequence format(CharSequence text, int width);
}