package com.lestariinterna.booklisting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookListingListFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<BookListingData>> {

    private BookListingDataAdapter blAdapter;
    private View rootView;
    private String BookUrl;
    private TextView mEmptyStateTextView;


    public BookListingListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.book_listing_listview, container, false);

        Bundle bundle = getArguments();
        BookUrl = bundle.getString("url");
        Log.v("Test Url", "url: " + BookUrl);
        ListView listView = (ListView) rootView.findViewById(R.id.book_listing);

////        // Create a new adapter that takes an empty list of earthquakes as input
        blAdapter = new BookListingDataAdapter(getActivity(), new ArrayList<BookListingData>());
////
////        // Set the adapter on the {@link ListView}
////        // so the list can be populated in the user interface
        listView.setAdapter(blAdapter);
//        //set empty view
        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyStateTextView);

        // set up loader
        getLoaderManager().initLoader(1,null,this);


//        BookListingAsyncTask tesAsync = new BookListingAsyncTask();
//        tesAsync.execute(BookUrl);

        return rootView;
    }

    @Override
    public Loader<ArrayList<BookListingData>> onCreateLoader(int id, Bundle args) {
        return new BookListingAsynctaskLoader(getActivity(),BookUrl);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<BookListingData>> loader, ArrayList<BookListingData> data) {
        mEmptyStateTextView.setText("No data");
        blAdapter.clear();
        if(data !=null && !data.isEmpty()){
            blAdapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<BookListingData>> loader) {
        blAdapter.clear();

    }

//    public class BookListingAsyncTask extends AsyncTask<String, Void, ArrayList<BookListingData>> {
//        @Override
//        protected ArrayList<BookListingData> doInBackground(String... urls) {
//            //don't perform connection if the url is null
//            if (urls.length<1|| urls[0] == null){
//                return null;
//            }
//
//            ArrayList<BookListingData> bookListArray = QueryUtils.fetching(urls[0]);
//            Log.v("AsynTask", "url[0]" + bookListArray);
//
//            return bookListArray;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<BookListingData> bookListingDatas) {
//            super.onPostExecute(bookListingDatas);
//             //Clear the adapter of previous earthquake data
//            //blAdapter.clear();
//             //If there is a valid list of {@link Earthquake}s, then add them to the adapter's
//             //data set. This will trigger the ListView to update.
////            if (bookListingDatas != null && !bookListingDatas.isEmpty()) {
////                blAdapter.addAll(bookListingDatas);
////            }
////
//            ListView listView = (ListView) rootView.findViewById(R.id.book_listing);
//            // Create a new adapter that takes
//            blAdapter = new BookListingDataAdapter(getActivity(), bookListingDatas);
//
//            // Set the adapter on the {@link ListView}
//            // so the list can be populated in the user interface
//            listView.setAdapter(blAdapter);
//        }
//
//    }
}