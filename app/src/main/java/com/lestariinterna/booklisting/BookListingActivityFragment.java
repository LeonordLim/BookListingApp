package com.lestariinterna.booklisting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static com.lestariinterna.booklisting.R.id.container;

/**
 * Created by AllinOne on 10/09/2017.
 */

public class BookListingActivityFragment extends Fragment {
    private String mBookURL = "https://www.googleapis.com/books/v1/volumes?q=";
    private Button okButton;
    private String SearchTerm;
    private String mBookUrlUpdate="none";
    private  String maxResult = "&maxResults=10";
    private int result=0;
    BookListingListFragment BookListView = new BookListingListFragment();



    public BookListingActivityFragment(){
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("mBookUrlUpdate", mBookUrlUpdate);
        outState.putString("SearchTerm", SearchTerm);
        outState.putInt("test2", result);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mBookUrlUpdate = savedInstanceState.getString("mBookUrlUpdate");
            Log.v("BlActiveFragment", "OnCreateView: mBookUrlUpdate + SearchTerm: " + mBookUrlUpdate );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.book_listing_main_fragment,container,false);
        if (mBookUrlUpdate== null){
            Log.v("result","is null");
            creatingListView();

        }else {
            Log.v("result","is no null");
        }

        final EditText searchInput = (EditText) rootView.findViewById(R.id.SearchByInputET);


        okButton = (Button) rootView.findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchTerm = searchInput.getText().toString();
                String SearchTermNoSpace = SearchTerm.replace(" ", "%20");
                mBookUrlUpdate = mBookURL + SearchTermNoSpace+maxResult;

                creatingListView();

//                List<BookListingData> test = QueryUtils.extractFromJson(JsonExample);
//                Log.v("TestButton", "JSONEXample:" +test.toString() );

            }
        });

        return rootView;

    }
    private void creatingListView(){
        //Passing the url data to other fragment using bundle

        Bundle bundle = new Bundle();
        bundle.putString("url",mBookUrlUpdate);
        BookListView.setArguments(bundle);

        //Replace the layout with the container id in book_listing_main_fragment
        getFragmentManager().beginTransaction().replace(container,BookListView)
                .addToBackStack(null)
                .commit();

    }

}
