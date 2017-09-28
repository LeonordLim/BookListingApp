package com.lestariinterna.booklisting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
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
    public String mBookUrlUpdate;
    private  String maxResult = "&maxResults=10";
    private int result=0;
    BookListingListFragment BookListView = new BookListingListFragment();

    public BookListingActivityFragment(){
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("mBookUrlUpdate", mBookUrlUpdate);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.book_listing_main_fragment,container,false);
        final EditText searchInput = (EditText) rootView.findViewById(R.id.SearchByInputET);


        searchInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    SearchTerm = searchInput.getText().toString();
                    String SearchTermNoSpace = SearchTerm.replace(" ", "%20");
                    mBookUrlUpdate = mBookURL + SearchTermNoSpace+maxResult;
                    Log.v("Test","mBookUrlUpdate"+mBookUrlUpdate);

//                    Passing the url data to other fragment using bundle
//                    if(savedInstanceState==null){
                    PutBundleString(mBookUrlUpdate);
                    creatingListView();
//                    }

//                    if(savedInstanceState!= null){
////
//                        Fragment fragment = getFragmentManager().findFragmentByTag("theFirst");
//                        FragmentManager fragmentManager = getFragmentManager();
//                        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
//                        fragmentTransaction.remove(fragment);
//                        fragmentTransaction.commit();
//                    }
                    return true;
                }
                return false;
            }
        });

//        okButton = (Button) rootView.findViewById(R.id.okButton);
//        okButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SearchTerm = searchInput.getText().toString();
//                String SearchTermNoSpace = SearchTerm.replace(" ", "%20");
//                mBookUrlUpdate = mBookURL + SearchTermNoSpace+maxResult;
//
//                creatingListView();
//
////                List<BookListingData> test = QueryUtils.extractFromJson(JsonExample);
////                Log.v("TestButton", "JSONEXample:" +test.toString() );
//
//            }
//        });

        return rootView;

    }
    private void creatingListView(){
         getFragmentManager().beginTransaction().replace(R.id.container,BookListView,"theFirst").commit();


    }
    public String getmBookUrlUpdate(String mBookUrlUpdate){return mBookUrlUpdate;}
    private void PutBundleString(String mBookUrlUpdate){
        Bundle bundle = new Bundle();
        bundle.clear();
        bundle.putString("url",mBookUrlUpdate);
        BookListView.setArguments(bundle);
    }

}
