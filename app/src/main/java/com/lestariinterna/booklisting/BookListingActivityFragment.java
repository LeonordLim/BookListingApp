package com.lestariinterna.booklisting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by AllinOne on 10/09/2017.
 */

public class BookListingActivityFragment extends Fragment {
    private String mBookURL = "https://www.googleapis.com/books/v1/volumes?q=";
    private Button okButton;
    private String SearchTerm;
    String mBookUrlUpdate;

    public BookListingActivityFragment(){
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.book_listing_main_fragment,container,false);
        final EditText searchInput = (EditText) rootView.findViewById(R.id.SearchByInputET);
        okButton = (Button) rootView.findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchTerm = searchInput.getText().toString();
                SearchTerm = SearchTerm.replace(" ", "%20");
                mBookUrlUpdate = mBookURL + SearchTerm+"&maxResults=10";

                //Passing the url data to other fragment using bundle
                Bundle bundle = new Bundle();
                bundle.putString("url",mBookUrlUpdate);
                BookListingListFragment BookListView = new BookListingListFragment();
                BookListView.setArguments(bundle);

                //Replace the layout in activity_book_listing with listView layout,
                getFragmentManager().beginTransaction().replace(R.id.activity_book_listing_Frame,BookListView)
                        .addToBackStack(null)
                        .commit();
//                List<BookListingData> test = QueryUtils.extractFromJson(JsonExample);
//                Log.v("TestButton", "JSONEXample:" +test.toString() );

            }
        });

        return rootView;
    }

}
