package com.example.impfpass.dao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.impfpass.R;

import java.util.LinkedList;

public class ListAdapter extends
        RecyclerView.Adapter<ListAdapter.WordViewHolder> {

    private final LinkedList<String> mWordList;
    private final LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final TextView wordItemView;
        final ListAdapter mAdapter;


        public WordViewHolder(View itemView, ListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.wordbutton);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


        }
    }

    public ListAdapter(Context context, LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    @Override
    public ListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ListAdapter.WordViewHolder holder,
                                 int position) {
        // Retrieve the data for that position.
        String mCurrent = mWordList.get(position);
        // Add the data to the view holder.
        holder.wordItemView.setText(mCurrent);
    }


    @Override
    public int getItemCount() {
        return mWordList.size();
    }






}

