package com.lestariinterna.booklisting;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by AllinOne on 06/09/2017.
 */

public class BookListingData {

    String title;
    ArrayList<String> authors;
    String description;
    Bitmap smallThumbnailUrl;
    Bitmap thumbnailUrl;

    public BookListingData(String title, ArrayList<String> authors, String description, Bitmap smallThumbnailUrl, Bitmap thumbnailUrl){
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.smallThumbnailUrl = smallThumbnailUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public Bitmap getSmallThumbnailUrl() {
        return smallThumbnailUrl;
    }

    public Bitmap getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
