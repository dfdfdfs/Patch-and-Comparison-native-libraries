package com.aaa.bbb.ccc.util;

import android.graphics.Rect;

import com.aaa.bbb.ccc.lang.DefFormatter;
import com.aaa.bbb.ccc.lang.Language;
import com.aaa.bbb.ccc.lang.LanguageNonProg;

import java.util.ArrayList;
import java.util.List;

/**
 * Does lexical analysis of a text for C-like languages.
 * The programming language syntax used is set as a static class variable.
 */
public class Lexer {
    public final static int UNKNOWN = -1;
    public final static int NORMAL = 0;
    public final static int KEYWORD = 1;
    public final static int OPERATOR = 2;
    public final static int NAME = 3;
    public final static int LITERAL = 4;
    public final static int NUMBER = 5;

    /**
     * A word that starts with a special symbol, inclusive.
     * Examples:
     * :ruby_symbol
     */
    public final static int SINGLE_SYMBOL_WORD = 10;
    /**
     * Tokens that extend from a single start symbol, inclusive, until the end of line.
     * Up to 2 types of symbols are supported per language, denoted by A and B
     * Examples:
     * #include "myCppFile"
     * #this is a comment in Python
     * %this is a comment in Prolog
     */
    public final static int SINGLE_SYMBOL_LINE_A = 20;
    public final static int SINGLE_SYMBOL_LINE_B = 21;
    /**
     * Tokens that extend from a two start symbols, inclusive, until the end of line.
     * Examples:
     * //this is a comment in C
     */
    public final static int DOUBLE_SYMBOL_LINE = 30;
    /**
     * Tokens that are enclosed between a start and end sequence, inclusive,
     * that can span multiple lines. The start and end sequences contain exactly
     * 2 symbols.
     * Examples:
     * {- this is a...
     * ...multi-line comment in Haskell -}
     */
    public final static int DOUBLE_SYMBOL_DELIMITED_MULTILINE = 40;
    /**
     * Tokens that are enclosed by the same single symbol, inclusive, and
     * do not span over more than one line.
     * Examples: 'c', "hello world"
     */
    public final static int SINGLE_SYMBOL_DELIMITED_A = 50;
    public final static int SINGLE_SYMBOL_DELIMITED_B = 51;
    public final static int MAX_KEYWORD_LENGTH = 127;
    public static ArrayList<Rect> mLines = new ArrayList<>();
    private static Language mGlobalLanguage = LanguageNonProg.getInstance();
    LexCallback mCallback = null;
    private DocumentProvider hDoc;
    private LexThread mWorkerThread = null;

    public Lexer(LexCallback callback) {
        mCallback = callback;
    }

    synchronized public static Language getLanguage() {
        return mGlobalLanguage;
    }

    synchronized public static void setLanguage(Language lang) {
        mGlobalLanguage = lang;
    }

    public static DefFormatter getFormatter() {
        return mGlobalLanguage.getFormatter();
    }

    public void tokenize(DocumentProvider hDoc) {
        //tokenize will modify the state of hDoc; make a copy
        setDocument(new DocumentProvider(hDoc));
        if (mWorkerThread == null) {
            mWorkerThread = new LexThread(this);
            mWorkerThread.start();
        } else {
            mWorkerThread.restart();
        }
    }

    void tokenizeDone(List<Pair> result) {
        if (mCallback != null) {
            mCallback.lexDone(result);
        }
        mWorkerThread = null;
    }

    public void cancelTokenize() {
        if (mWorkerThread != null) {
            mWorkerThread.abort();
            mWorkerThread = null;
        }
    }

    public synchronized DocumentProvider getDocument() {
        return hDoc;
    }

    public synchronized void setDocument(DocumentProvider hDoc) {
        this.hDoc = hDoc;
    }

    public interface LexCallback {
        public void lexDone(List<Pair> results);
    }

    private class LexThread extends Thread {
        private final Lexer _lexManager;
        /**
         * can be set by another thread to stop the scan immediately
         */
        private final Flag _abort;
        private boolean rescan = false;
        //private int max=2 ^ 18;
        /**
         * A collection of Pairs, where Pair.first is the start
         * position of the token, and Pair.second is the type of the token.
         */
        private ArrayList<Pair> _tokens;

        public LexThread(Lexer p) {
            _lexManager = p;
            _abort = new Flag();
        }

        //@Override
        public void run() {
            do {
                rescan = false;
                _abort.clear();
                _tokens = Lexer.getLanguage().getTokenizer().tokenize(getDocument(), _abort);
            } while (rescan);

            if (!_abort.isSet()) {
                // lex complete
                _lexManager.tokenizeDone(_tokens);
            }
        }

        public void restart() {
            rescan = true;
            _abort.set();
        }

        public void abort() {
            _abort.set();
        }
    }
}