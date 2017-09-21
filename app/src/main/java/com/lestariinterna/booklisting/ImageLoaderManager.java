package com.lestariinterna.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by AllinOne on 20/09/2017.
 */

public class  ImageLoaderManager extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<Bitmap> {

    private String ImageAddress;
    private Context contextl;
    public Bitmap mBitmap;


    public ImageLoaderManager(Context context,String url){
        contextl = context;
        ImageAddress = url;
        Log.v("ImageLoaderManager","ImageAdress"+ImageAddress);
    }

    public Bitmap getImage(){

        LoaderManager test = getLoaderManager();
        test.initLoader(2,null,this);

        return mBitmap;
    }

    @Override
    public android.content.Loader<Bitmap> onCreateLoader(int id, Bundle args) {
        return new ImageLoader(this,ImageAddress);
    }

    @Override
    public void onLoadFinished(android.content.Loader<Bitmap> loader, Bitmap data) {
        mBitmap = data;

//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        data.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
//        Intent in1 = new Intent(this, QueryUtils.class);
//        in1.putExtra("image",byteArray);

    }

    @Override
    public void onLoaderReset(android.content.Loader<Bitmap> loader) {
        loader =null;
    }

}
