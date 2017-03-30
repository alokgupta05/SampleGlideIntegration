package com.example.gilde.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

/**
 * Created by  on 7/25/2016.
 */

public class ImageUtils {
    private static final String TAG = ImageUtils.class.getName();

    public static void setImageWithGlide(String url, ImageView imageView) {
        Log.i(TAG, "Image URL to load: " + url);
        Glide.with(imageView.getContext())
                .load(url)
                .priority(Priority.IMMEDIATE)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(false)
                .into(imageView);
    }

    public static void setImageWithGlide(String url, ImageView imageView, int fallbackResId, int errorResId) {
        Log.i(TAG, "Image URL to load: " + url);
        Glide.with(imageView.getContext())
                .load(url)
                .priority(Priority.IMMEDIATE)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(false)
                .fallback(fallbackResId)
                .error(errorResId)
                .into(imageView);
    }

    public static void setImageWithGlide(String url, ImageView imageView, int fallbackResId) {
        Log.i(TAG, "Image URL to load: " + url);
        Glide.with(imageView.getContext())
                .load(url)
                .priority(Priority.IMMEDIATE)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(false)
                .fallback(fallbackResId)
                .into(imageView);
    }

    public static void loadImageWithGlide(String imageUri, ImageView view, final Context context) {
        Glide.with(context).load(imageUri).asBitmap().priority(Priority.IMMEDIATE).centerCrop()
                .listener(new RequestListener<String, Bitmap>() {

                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, final String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (target != null) {
                            BitmapImageViewTarget d = (BitmapImageViewTarget) target;
                            d.getSize(new SizeReadyCallback() {
                                @Override
                                public void onSizeReady(int width, int height) {
                                    if (width < 5 || height < 5) {

                                    }
                                }
                            });
                        }
                        return false;
                    }
                })
                .into(new BitmapImageViewTarget(view) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(Math.min(resource.getWidth(), resource.getHeight()) / 2.0f);
                        circularBitmapDrawable.setCornerRadius(Math.min(resource.getWidth(), resource.getHeight()) / 1.5f);
                        view.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
    /*private static void saveImageOnCard(Context context, final LayoutConstants.LAYOUT_TYPE layoutType, final Clip clip, String imageUri) {
        Glide.with(context)
                .load(imageUri)
                .asBitmap()
                .priority(Priority.LOW)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new SimpleTarget<Bitmap>(SimpleTarget.SIZE_ORIGINAL, SimpleTarget.SIZE_ORIGINAL) {

                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
//                        VuLog.d(TAG, "onResourceReady..........");
                        try {
                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            resource.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

                            String imagePath = Config.buildDefaultConfig().buildDestinationImagePath(clip, layoutType);
                            File f = new File(imagePath);
                            f.createNewFile();
                            //write the bytes in file
                            FileOutputStream fo = new FileOutputStream(f);
                            fo.write(bytes.toByteArray());
                            // remember close de FileOutput
                            fo.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                });
    }*/
}
