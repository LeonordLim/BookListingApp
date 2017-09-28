package com.lestariinterna.booklisting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class BookListingActivity extends AppCompatActivity{

    public final static String LOG_TAG = BookListingActivity.class.getName();
    private String mBookURL = "https://www.googleapis.com/books/v1/volumes?q=";



    private String SearchTerm;
    public String mBookUrlUpdate;
    private String maxResult = "&maxResults=10";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_listing_main_fragment);


        final EditText searchInput = (EditText) findViewById(R.id.SearchByInputET);


        searchInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    SearchTerm = searchInput.getText().toString();
                    String SearchTermNoSpace = SearchTerm.replace(" ", "%20");
                    mBookUrlUpdate = mBookURL + SearchTermNoSpace + maxResult;
                    Log.v("Test", "mBookUrlUpdate" + mBookUrlUpdate);
                    BookListingListFragment BookListView = new BookListingListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("url",mBookUrlUpdate);
                    BookListView.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction().replace(R.id.container,BookListView).commit();

                    return true;
                }
                return false;
            }
        });


    }


}
//        inputText = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//
//                try {
//
//                    String JsonResponse = QueryUtils.makeHttpConnection(mBookUrlUpdate);
//                    Log.v("Test","Input Stream"+JsonResponse);
//                }
//                catch (IOException e){
//                    Log.e("Test","IOException",e);
//                }
//
//
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//

//            }
//        };
//        searchInput.addTextChangedListener(inputText);







