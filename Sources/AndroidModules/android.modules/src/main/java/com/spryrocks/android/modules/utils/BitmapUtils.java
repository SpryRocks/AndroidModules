package com.spryrocks.android.modules.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BitmapUtils {
    public static int getImageOrientation(File file) throws IOException {
        ExifInterface exif = new ExifInterface(file.getAbsolutePath());
        return exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
    }

    public static Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public static Bitmap scale(Bitmap source, float power) {
        int width = (int) (power * source.getWidth());
        int height = (int) (power * source.getHeight());

        return Bitmap.createScaledBitmap(source, width, height, true);
    }

    public static Bitmap scaleBitmapByHeight(Bitmap bitmap, int height) {
        double aspect = (double) bitmap.getHeight() / bitmap.getWidth();
        int width = (int) (height / aspect);
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    public static Bitmap scaleBitmapByWidth(Bitmap bitmap, int width) {
        double aspect = (double) bitmap.getHeight() / bitmap.getWidth();
        return Bitmap.createScaledBitmap(bitmap, width,
                (int) (width * aspect), true);
    }

    public static Bitmap scaleBitmapByWidthLandscape(Bitmap bitmap, int width, boolean isFront) {
        double aspect = (double) bitmap.getHeight() / bitmap.getWidth();
        return Bitmap.createScaledBitmap(bitmap, width,
                (int) (width * aspect), true);
    }

    public static void saveToStream(Bitmap bitmap, OutputStream outputStream) {
        if(bitmap == null)
            throw new NullPointerException("bitmap");
        if(outputStream == null)
            throw new NullPointerException("outputStream");
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream);
    }

    public static void saveToFile(Bitmap bitmap, File file) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            saveToStream(bitmap, fileOutputStream);
        }
        finally {
            if(fileOutputStream != null)
                fileOutputStream.close();
        }

    }

    public static int getOrientationFromExif(String path) throws IOException {

        int orientation = -1;

        ExifInterface exif = null;
        if (path != null)
            exif = new ExifInterface(path);
        int exifOrientation = ExifInterface.ORIENTATION_ROTATE_270;
        if (exif != null)
            exifOrientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_FLIP_VERTICAL);

        switch (exifOrientation) {
            case ExifInterface.ORIENTATION_ROTATE_270:
                orientation = 270;

                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                orientation = 180;

                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                orientation = 90;

                break;

            case ExifInterface.ORIENTATION_NORMAL:
                orientation = 0;

                break;
            default:
                break;
        }


        return orientation;
    }

    public static Bitmap cropBitmapToCircle(Bitmap bitmap, boolean drawBorder,
                                            int borderSize, int borderColor) {
        if(bitmap==null)
            return null;
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        if (!drawBorder)
            borderSize = 0;

        int d = borderSize / 2;

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        int width = bitmap.getWidth();

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2 - d, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        if (drawBorder) {
            paint.setXfermode(null);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(borderColor);
            paint.setStrokeWidth(borderSize);
            canvas.drawCircle(width / 2, width / 2, width / 2 - borderSize / 2,
                    paint);
        }

        return output;
    }

    public static Bitmap cropBitmapToSquare(Bitmap source, int size_px) {
        return cropBitmapToRect(source, size_px, size_px);
    }

    public static Bitmap cropBitmapToRect(Bitmap bitmap, int width, int height) {
        return ThumbnailUtils.extractThumbnail(bitmap, width, height);
    }

    public static Point getBitmapSize(InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        int width = options.outWidth;
        int height = options.outHeight;
        return new Point(width, height);
    }

    public static Point getBitmapSize(File file) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getBitmapSize(inputStream);
        }
        finally {
            if(inputStream != null)
                inputStream.close();
        }
    }

    public static Point calculateSize(Point sourceSize, Point targetMaxSize, boolean upScale, boolean canCropImage) {

        // target
        int targetMaxSizeWidth = targetMaxSize.x;
        int targetMaxSizeHeight = targetMaxSize.y;
        float screenAspect = (float) targetMaxSizeWidth / targetMaxSizeHeight;

        // source
        int sourceWidth = sourceSize.x;
        int sourceHeight = sourceSize.y;
        float sourceAspect = (float) sourceWidth / sourceHeight;

        // if upScale is false
        // and imageSize smaller than targetMaxSize
        // return image size
        if(!upScale &&
                sourceWidth < targetMaxSizeWidth && sourceHeight < targetMaxSizeHeight) {
            return sourceSize;
        }

        // calculate new size
        boolean useParentWidth;

        useParentWidth = screenAspect < sourceAspect;

        if(canCropImage)
            useParentWidth = !useParentWidth;

        // result
        int resultWidth;
        int resultHeight;

        if(useParentWidth) {
            resultWidth = targetMaxSizeWidth;
            resultHeight = (int)(resultWidth / sourceAspect);
        }
        else {
            resultHeight = targetMaxSizeHeight;
            resultWidth = (int)(resultHeight * sourceAspect);
        }
/*
        if (resultHeight > sourceHeight || resultWidth > sourceWidth) {
            resultHeight = sourceHeight;
            resultWidth = sourceWidth;
        }
*/
        Point result = new Point(resultWidth, resultHeight);
        return result;

    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        Matrix matrix = new Matrix();

        boolean shouldRotate = true;
        switch (orientation) {
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;

            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;

            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;

            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;

            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;

            default:
                shouldRotate = false;
                break;
        }

        if (shouldRotate) {
            Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            bitmap = rotatedBitmap;
        }

        return bitmap;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int maxWidth, int maxHeight) {
        int height = options.outHeight;
        int width = options.outWidth;

        int heightInSampleSize = (int) Math.ceil((double) height / maxHeight);
        int widthInSampleSize = (int) Math.ceil((double) width / maxWidth);

        return Math.min(heightInSampleSize, widthInSampleSize);
    }

    public static Bitmap decodeSampledBitmapFromFile(String filePath, int maxWidth, int maxHeight) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap decodeBitmapFromFile(String filePath) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

}