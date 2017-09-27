package com.lestariinterna.booklisting;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class BookListingActivity extends AppCompatActivity {

    public final static String LOG_TAG= BookListingActivity.class.getName();
    private int result= 0;
    BookListingActivityFragment TellMe = new BookListingActivityFragment();




    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("test",result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_listing);

        if(savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.activity_book_listing_Frame, TellMe)
                    .commit();
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

    }
    private int SimpleMath(int a, int b){
        int result = a*b;
        return result;
    }


}








