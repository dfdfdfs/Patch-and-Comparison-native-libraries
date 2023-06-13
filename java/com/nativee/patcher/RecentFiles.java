package com.nativee.patcher;

import java.io.File;
import java.util.ArrayList;

//import fr.xgouchet.texteditor.BuildConfig;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;


public class RecentFiles implements Constants {


	public static void loadRecentFiles(String saved) {
		PATHS = new ArrayList<String>();
		String[] paths = saved.split(File.pathSeparator);
		for (String path : paths) {
			if (path.length() > 0) {
				PATHS.add(path);
			}
			if (PATHS.size() == Settings.MAX_RECENT_FILES) {
				break;
			}
		}
	}


	public static void saveRecentList(SharedPreferences prefs) {
		String str = "";
		Editor editor;

		for (String path : PATHS) {
			str += path;
			str += File.pathSeparator;
		}

		editor = prefs.edit();
		editor.putString(PREFERENCE_RECENTS, str);
		editor.commit();
	}

	public static ArrayList<String> getRecentFiles() {
		return PATHS;
	}

	public static void updateRecentList(String path) {
		if (PATHS.contains(path)) {
			PATHS.remove(path);
		}

		PATHS.add(0, path);
		while (PATHS.size() > Settings.MAX_RECENT_FILES) {
			PATHS.remove(Settings.MAX_RECENT_FILES);
		}
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "added path to recent files : " + path);
		}
	}


	public static void removePath(String path) {
		if (PATHS.contains(path)) {
			PATHS.remove(path);
		}
	}


	private static ArrayList<String> PATHS;
}
