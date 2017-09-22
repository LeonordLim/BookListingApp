package com.lestariinterna.booklisting;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AllinOne on 10/09/2017.
 */

public class BookListingDataAdapter extends ArrayAdapter<BookListingData> {

    Bitmap mBitmap;

        BookListingDataAdapter(Activity context, ArrayList<BookListingData>bookData){
            super(context,0,bookData);
        }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View dataItem = convertView;
        if (dataItem==null){
            dataItem = LayoutInflater.from(getContext()).inflate(R.layout.book_data_item,parent,false);
        }

        BookListingData currentData = getItem(position);
        //Get title
        TextView title = (TextView) dataItem.findViewById(R.id.BookTitle);
        title.setText(currentData.getTitle());
        //Get description
        TextView description = (TextView)dataItem.findViewById(R.id.Description);
        description.setText(currentData.getDescription());
        //Get smallthumbnail
        ImageView thumbnail = (ImageView)dataItem.findViewById(R.id.bookSmallThumbnail);
        if(currentData.getSmallThumbnailUrl()!= null){
        thumbnail.setImageBitmap(currentData.getSmallThumbnailUrl());}


        return dataItem;
    }



}
