package br.ufrn.imd.imdmarket.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {

    public static boolean saveImage(Context context, Bitmap bitmap, String filename) throws IOException {
        try {
            File internalStorageDir = context.getFilesDir();
            File imageFile = new File(internalStorageDir, filename);

            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Bitmap getBitmapFromUri(Context context, Uri uri) {
        Bitmap bitmap = null;
        ContentResolver contentResolver = context.getContentResolver();

        try {
            InputStream inputStream = contentResolver.openInputStream(uri);

            if (inputStream != null) {
                bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static Drawable getDrawableFromUri(Context context, Uri uri) {
        Drawable drawable = null;
        ContentResolver contentResolver = context.getContentResolver();

        try {
            InputStream inputStream = contentResolver.openInputStream(uri);
            if (inputStream != null) {
                drawable = Drawable.createFromStream(inputStream, uri.toString());
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return drawable;
    }

    public static Bitmap getBitmapFromInternalStorage(Context context, String filename) {
        File internalStorageDir = context.getFilesDir();
        File imageFile = new File(internalStorageDir, filename);

        if (imageFile.exists()) {
            return BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        } else {
            return null; // File does not exist
        }
    }

    public static Drawable getDrawableFromInternalStorage(Context context, String filename) {
        Bitmap bitmap = getBitmapFromInternalStorage(context, filename);

        if (bitmap != null) {
            return new BitmapDrawable(context.getResources(), bitmap);
        } else {
            return null;
        }
    }
}
