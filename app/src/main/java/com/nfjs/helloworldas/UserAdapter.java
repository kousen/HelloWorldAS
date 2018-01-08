package com.nfjs.helloworldas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mm98800 on 1/6/18.
 * Adapted from: https://www.raywenderlich.com/124438/android-listview-tutorial
 */

public class UserAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<User> mDataSource;
    private int us;

    public UserAdapter(Context context, ArrayList<User> currentUsers) {
        mContext = context;
        mDataSource = currentUsers;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //getCount() lets ListView know how many items to display, or in other words, it returns the size of your data source.
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //getItem() returns an item to be placed in a given position from the data source, specifically, User objects obtained from mDataSource.
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //This implements the getItemId() method that defines a unique ID for each row in the list. For simplicity, you just use the position of the item as its ID.
    @Override
    public long getItemId(int position) {
        return position;
    }

    //Finally, getView() creates a view to be used as a row in the list. Here you define what information shows and where it sits within the ListView.
    // You also inflate a custom view from the XML layout defined in res/layout/list_item_recipe.xml -- more on this in the next section.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.display_user, parent, false);

        // Get UserName
        TextView userNameTextView =
                rowView.findViewById(R.id.userName);

        // Get BirthMonth
        TextView birthMonthTextView =
                rowView.findViewById(R.id.birthMonth);

        //Get info from current row
        User currentUser = (User) getItem(position);

        //Set each TextView's value
        userNameTextView.setText(currentUser.userName);
        birthMonthTextView.setText(currentUser.birthMonth);

        return rowView;
    }
}
