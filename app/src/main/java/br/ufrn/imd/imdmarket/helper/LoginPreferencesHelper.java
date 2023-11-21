package br.ufrn.imd.imdmarket.helper;
import android.content.Context;
import android.content.SharedPreferences;

public class LoginPreferencesHelper {

    private static final String PREFS_NAME = "login.txt";
    private static final String KEY_LOGIN = "LOGIN";
    private static final String KEY_PASSWORD = "PASSWORD";

    public static void saveLoginCredentials(Context context, String login, String password) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_LOGIN, login);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public static String getSavedLogin(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_LOGIN, "");
    }

    // Retrieve password from SharedPreferences
    public static String getSavedPassword(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_PASSWORD, "");
    }

    public static boolean checkLoginCredentials(Context context, String login, String password) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (!prefs.contains(KEY_LOGIN) || !prefs.contains(KEY_PASSWORD)) {
            SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
            editor.putString(KEY_LOGIN, "admin");
            editor.putString(KEY_PASSWORD, "admin");
            editor.apply();
        }

        String storedLogin = prefs.getString(KEY_LOGIN, "");
        String storedPassword = prefs.getString(KEY_PASSWORD, "");
        return login.equals(storedLogin) && password.equals(storedPassword);
    }
}