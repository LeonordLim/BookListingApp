package com.lestariinterna.booklisting;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by AllinOne on 18/09/2017.
 */

public class BookListingAsynctaskLoader extends AsyncTaskLoader<ArrayList<BookListingData>> {

    private String mUrl;
    public BookListingAsynctaskLoader(Context context, String url){
        super(context);
        mUrl=url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<BookListingData> loadInBackground() {
        //don't perform connection if the url is null
        if (mUrl == null){
            return null;
        }

        ArrayList<BookListingData> bookListArray = QueryUtils.fetching(mUrl);
        Log.v("AsynTask", "url[0]" + bookListArray);
        return bookListArray;
    }
}
