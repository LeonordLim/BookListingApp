package com.lestariinterna.booklisting;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by AllinOne on 20/09/2017.
 */

public class ImageLoader extends android.content.AsyncTaskLoader<Bitmap> {

    private String mUrl;

    public ImageLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Bitmap loadInBackground() {
//        Don't perform the request if there are no URLs, or the first URL is null.
        if (mUrl==null) {
            return null;
        }
        Bitmap result = QueryUtils.fetchingImage(mUrl);
        return result;

    }

}
