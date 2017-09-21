package com.lestariinterna.booklisting;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.lestariinterna.booklisting.BookListingActivity.LOG_TAG;

/**
 * Created by AllinOne on 03/09/2017.
 */

public class QueryUtils   {

    private int Loader_ID_tag= 1;

    public QueryUtils(){}


    /*
         * Returns new URL object from the given string URL.
        */
    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG,"Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    public static String makeHttpConnection(URL url)throws IOException {
        String jsonResponse = "";
        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        //Creating Http Connection object and inputstream object
        HttpURLConnection urlConnection;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
               inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }


        }catch (IOException e){
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        }

        return jsonResponse;
    }

    /** Making a HTTP connection for thumbnails
     */
    public static Bitmap makeHTTPConnection(URL url)throws IOException{

        Bitmap mBitmap = null;

        //Creating Http Connection object and inputstream object
        HttpURLConnection urlConnection;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(1000);
            urlConnection.setConnectTimeout(1500);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                BufferedInputStream bInputStream =  new BufferedInputStream(inputStream);
                mBitmap = BitmapFactory.decodeStream(bInputStream);
                return mBitmap;
            } else {
                Log.e("LOG_TAG", "Error response code: " + urlConnection.getResponseCode());
            }


        } catch (IOException e) {
            Log.e("LOG_TAG", "Problem retrieving results.", e);
        }

        return mBitmap;
    }

    public static ArrayList<BookListingData> fetching(String googleUrl){
        URL testURL = QueryUtils.createUrl(googleUrl);
        String JsonResponse = null;
        try {

            JsonResponse = makeHttpConnection(testURL);
        }catch (IOException e){
            Log.e(LOG_TAG,"Making connection",e);
        }
        ArrayList<BookListingData>booklisting = extractFromJson(JsonResponse);
        return booklisting;
    }

    public  static  Bitmap fetchingImage(String url){
        URL mUrl = createUrl(url);
        Bitmap mBitmap= null;
        try {
            mBitmap = makeHTTPConnection(mUrl);
        }catch (IOException e){
            Log.e(LOG_TAG,"Making connection for image",e);
        }
        return mBitmap;
    }

    public static ArrayList<BookListingData> extractFromJson (String JsonExample){
        ArrayList<BookListingData>BookListing = new ArrayList();
        String descriptions;
        Bitmap smallThumbnailUrl= null;
        Bitmap thumbnailUrl= null;
        if(TextUtils.isEmpty(JsonExample)){
            return null;

        }
        try {
            JSONObject read = new JSONObject(JsonExample);
            JSONArray itemArray = read.getJSONArray("items");
            Log.v("Test Array", "item Array:" + itemArray.toString().length());
            ArrayList<String>authorsArray = new ArrayList();
            for (int i = 0; i < itemArray.length(); i++) {
                JSONObject book = itemArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");

                //Some book don't have author's name listed, so we try to catch JSON exception to solve this issue
                try {
                    JSONArray authors = volumeInfo.getJSONArray("authors");
                    if(authors.length()!= 0) {
                        for (int a = 0; a < authors.length(); a++) {
                            authorsArray.add(authors.getString(a));
                        }
                    }
                }catch (JSONException e ) {
                    authorsArray.add("No Authors");// No authors listed, so we use this value instead
                }
                try {
                    descriptions = volumeInfo.getString("description");
                }catch (JSONException e){
                    descriptions = " No descriptions";
                }
                try {
                    JSONObject thumbnails = volumeInfo.getJSONObject("imageLinks");
                    String smallUrl = thumbnails.getString("smallThumbnail");
                    String biggerUrl = thumbnails.getString("thumbnail");
                    smallThumbnailUrl = fetchingImage(smallUrl);
                    thumbnailUrl = fetchingImage(biggerUrl);


                }catch (JSONException e){
                    Log.e(LOG_TAG,"no image link" ,e);


                }
                BookListing.add(new BookListingData(title, authorsArray,descriptions , smallThumbnailUrl, thumbnailUrl));

            }
            return BookListing;
        }catch (JSONException e){
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }

       return null;
    }
    private static String readFromStream(InputStream inputStream)  throws  IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        //Log.v("This",output.toString());
        return output.toString();
    }



}