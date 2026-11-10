package com.ju.lib.utils.file;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ThumbnailUtils;
import b.a.a.a.a;
import com.ju.lib.utils.log.LogUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class ImageProcess {
    public static final int MAX_HIGHT = 640;
    public static final int MAX_WIDTH = 810;
    public static final String TAG = "ImageProcess";

    public static final class ResultCode {
        public static final int FAIL_BITMAP_IS_NULL = 1;
        public static final int FAIL_CONTEXT_IS_NULL = 2;
        public static final int FAIL_DELETE = 6;
        public static final int FAIL_FILE_EXIT = 3;
        public static final int FAIL_FILE_NOT_EXIT = 5;
        public static final int FAIL_SAVE = 4;
        public static final int SUCCESS = 0;
    }

    public static int deleteImage(Context context, String str, FilePathConstants.Type type) {
        String directory = FilePathConstants.getDirectory(context, type);
        if (directory == null) {
            return 2;
        }
        if (!new File(directory).exists()) {
            return 5;
        }
        File file = new File(a.a(a.a(directory), File.separator, str));
        if (file.exists()) {
            return !file.delete() ? 6 : 0;
        }
        return 5;
    }

    public static Bitmap getCompressImage(String str, int i, int i2) {
        int i3;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        int i4 = options.outWidth;
        int i5 = options.outHeight;
        if (i4 < i5 || i4 <= i) {
            i3 = (i4 >= i5 || i5 <= i2) ? 1 : i5 / i2;
        } else {
            i3 = i4 / i;
        }
        if (i3 <= 0) {
            i3 = 1;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = i3;
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap getImage(Context context, String str, FilePathConstants.Type type) {
        String directory = FilePathConstants.getDirectory(context, type);
        if (directory == null) {
            return null;
        }
        StringBuilder sbA = a.a(directory);
        sbA.append(File.separator);
        File file = new File(sbA.toString(), str);
        if (file.exists()) {
            return getCompressImage(file.getAbsolutePath(), MAX_WIDTH, MAX_HIGHT);
        }
        return null;
    }

    public static Bitmap getImageThumbnail(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        if (i <= 0 || i2 <= 0) {
            return bitmap;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth() / i;
        int i3 = height / i2;
        if (width < i3) {
            i3 = width;
        }
        if (i3 <= 0) {
            i3 = 1;
        }
        options.inSampleSize = i3;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, options);
    }

    public static Bitmap getRoundBitmap(Bitmap bitmap) {
        float f2;
        float f3;
        float f4;
        float f5;
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            f5 = width;
            f2 = f5;
            f3 = f5 / 2.0f;
            f4 = 0.0f;
        } else {
            f2 = height;
            f3 = f2 / 2.0f;
            f4 = (width - height) / 2.0f;
            f5 = width - f4;
            width = height;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect((int) f4, (int) 0.0f, (int) f5, (int) f2);
        Rect rect2 = new Rect((int) 0.0f, (int) 0.0f, (int) f2, (int) f2);
        RectF rectF = new RectF(rect2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f3, f3, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap getRoundBitmapWidth(Bitmap bitmap, int i) {
        LogUtil.d(TAG, "getRoundBitmapWidth");
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width < i || height < i) {
            return bitmap;
        }
        LogUtil.d(TAG, "--", Integer.valueOf(width), "--", Integer.valueOf(height), "--", Integer.valueOf(i));
        float f2 = height;
        float f3 = f2 / 2.0f;
        float f4 = (width - i) / 2.0f;
        float f5 = (height - i) / 2.0f;
        float f6 = i;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect((int) f4, (int) f5, (int) (width - f4), (int) (f2 - f5));
        int i2 = (int) 0.0f;
        int i3 = (int) f6;
        Rect rect2 = new Rect(i2, i2, i3, i3);
        RectF rectF = new RectF(rect2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f3, f3, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap getRoundedCornerBitmap1(Bitmap bitmap, float f2) {
        Bitmap imageThumbnail = getImageThumbnail(bitmap, 100, 100);
        if (imageThumbnail == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(imageThumbnail.getWidth(), imageThumbnail.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, imageThumbnail.getWidth(), imageThumbnail.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(imageThumbnail, rect, rect, paint);
        if (!imageThumbnail.isRecycled()) {
            imageThumbnail.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap getRoundedCornerBitmapWithStroke(Bitmap bitmap, float f2) {
        Bitmap imageThumbnail = getImageThumbnail(bitmap, 100, 100);
        if (imageThumbnail == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(imageThumbnail.getWidth(), imageThumbnail.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, imageThumbnail.getWidth(), imageThumbnail.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(imageThumbnail, rect, rect, paint);
        paint.setColor(-1);
        paint.setStrokeWidth(2.0f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        canvas.drawRoundRect(rectF, f2, f2, paint);
        if (!imageThumbnail.isRecycled()) {
            imageThumbnail.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap getThumbnailImage(Context context, String str, FilePathConstants.Type type, int i, int i2) {
        String directory = FilePathConstants.getDirectory(context, type);
        if (directory == null) {
            return null;
        }
        StringBuilder sbA = a.a(directory);
        sbA.append(File.separator);
        sbA.append(str);
        return getImageThumbnail(sbA.toString(), i, i2);
    }

    public static boolean isImageFile(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return options.outHeight > 0 && options.outWidth > 0;
    }

    public static Bitmap readBitMap(Context context, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inInputShareable = true;
        return readBitMap(context, i, options);
    }

    public static int saveHeadImage(Context context, String str, Bitmap bitmap) {
        return saveImage(context, str, bitmap, FilePathConstants.Type.HEADIMAGE, Bitmap.CompressFormat.JPEG, 70);
    }

    public static int saveImage(Context context, String str, Bitmap bitmap, FilePathConstants.Type type, Bitmap.CompressFormat compressFormat, int i) throws Throwable {
        FileOutputStream fileOutputStream;
        if (bitmap == null) {
            return 1;
        }
        String directory = FilePathConstants.getDirectory(context, type);
        if (directory == null) {
            return 2;
        }
        File file = new File(directory);
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.e(TAG, "file.mkdirs fail");
        }
        File file2 = new File(a.a(a.a(directory), File.separator, str));
        if (file2.exists()) {
            return 3;
        }
        int i2 = 50;
        if (i >= 0 && i <= 100) {
            i2 = i;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                if (!file2.createNewFile()) {
                    LogUtil.e(TAG, "createNewFile fail");
                }
                fileOutputStream = new FileOutputStream(file2);
                try {
                    bitmap.compress(compressFormat, i2, fileOutputStream);
                    fileOutputStream.flush();
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        LogUtil.e(TAG, "saveImage(2)", e2.getMessage());
                    }
                    return 0;
                } catch (FileNotFoundException e3) {
                    e = e3;
                    fileOutputStream2 = fileOutputStream;
                    LogUtil.e(TAG, "saveImage(1)", e.getMessage());
                    if (fileOutputStream2 == null) {
                        return 4;
                    }
                    try {
                        fileOutputStream2.close();
                        return 4;
                    } catch (IOException e4) {
                        LogUtil.e(TAG, "saveImage(2)", e4.getMessage());
                        return 4;
                    }
                } catch (IOException e5) {
                    e = e5;
                    fileOutputStream2 = fileOutputStream;
                    LogUtil.e(TAG, "saveImage(2)", e.getMessage());
                    if (fileOutputStream2 == null) {
                        return 4;
                    }
                    try {
                        fileOutputStream2.close();
                        return 4;
                    } catch (IOException e6) {
                        LogUtil.e(TAG, "saveImage(2)", e6.getMessage());
                        return 4;
                    }
                } catch (Exception e7) {
                    e = e7;
                    fileOutputStream2 = fileOutputStream;
                    LogUtil.e(TAG, "saveImage(3)", e.getMessage());
                    if (fileOutputStream2 == null) {
                        return 4;
                    }
                    try {
                        fileOutputStream2.close();
                        return 4;
                    } catch (IOException e8) {
                        LogUtil.e(TAG, "saveImage(2)", e8.getMessage());
                        return 4;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e9) {
                            LogUtil.e(TAG, "saveImage(2)", e9.getMessage());
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (FileNotFoundException e10) {
            e = e10;
        } catch (IOException e11) {
            e = e11;
        } catch (Exception e12) {
            e = e12;
        }
    }

    public static Bitmap getThumbnailImage(Context context, String str, FilePathConstants.Type type, int i) {
        String directory = FilePathConstants.getDirectory(context, type);
        if (directory == null) {
            return null;
        }
        StringBuilder sbA = a.a(directory);
        sbA.append(File.separator);
        sbA.append(str);
        return getThumbnailImage(sbA.toString(), i);
    }

    public static Bitmap getImage(Context context, String str) {
        File file = new File(str);
        if (file.exists()) {
            return getCompressImage(file.getAbsolutePath(), MAX_WIDTH, MAX_HIGHT);
        }
        return null;
    }

    public static Bitmap getThumbnailImage(String str, int i) {
        Bitmap bitmap = null;
        if (!new File(str).exists()) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        if (options.outWidth < 200 && options.outHeight < 200) {
            options.inSampleSize = 1;
        } else {
            options.inSampleSize = 2;
        }
        if (bitmapDecodeFile == null || bitmapDecodeFile.isRecycled()) {
            bitmap = bitmapDecodeFile;
        } else {
            bitmapDecodeFile.recycle();
        }
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError unused) {
            return bitmap;
        }
    }

    public static Bitmap readBitMap(Context context, int i, boolean z) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (z) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        } else {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        options.inPurgeable = true;
        options.inInputShareable = true;
        return readBitMap(context, i, options);
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0030 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static Bitmap readBitMap(Context context, int i, BitmapFactory.Options options) throws Throwable {
        InputStream inputStreamOpenRawResource;
        try {
            inputStreamOpenRawResource = context.getResources().openRawResource(i);
            try {
                try {
                    Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenRawResource, null, options);
                    if (inputStreamOpenRawResource != null) {
                        try {
                            inputStreamOpenRawResource.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    return bitmapDecodeStream;
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    if (inputStreamOpenRawResource != null) {
                        try {
                            inputStreamOpenRawResource.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                if (inputStreamOpenRawResource != null) {
                    try {
                        inputStreamOpenRawResource.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            inputStreamOpenRawResource = null;
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpenRawResource = null;
            if (inputStreamOpenRawResource != null) {
                inputStreamOpenRawResource.close();
            }
            throw th;
        }
    }

    public static Bitmap getImageThumbnail(String str, int i, int i2) {
        if (!new File(str).exists()) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        int i3 = options.outHeight;
        int i4 = options.outWidth / i;
        int i5 = i3 / i2;
        if (i4 < i5) {
            i5 = i4;
        }
        options.inSampleSize = i5 > 0 ? i5 : 1;
        return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(str, options), i, i2, 2);
    }
}
