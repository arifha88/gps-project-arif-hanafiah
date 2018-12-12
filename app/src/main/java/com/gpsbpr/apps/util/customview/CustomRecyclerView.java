package com.gpsbpr.apps.util.customview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by test(test@gmail.com) on 11/11/16.
 */

public class CustomRecyclerView extends RecyclerView {

    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private GridSpacingItemDecoration mGridSpacingItemDecoration;

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setUpAsList() {
        setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        setLayoutManager(mLinearLayoutManager);
    }

    public void setUpAsHorizontal() {
        setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getContext(),HORIZONTAL,false);
        setLayoutManager(mLinearLayoutManager);
    }

    public void setUpAsGrid(int spanCount) {
        setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        setLayoutManager(mGridLayoutManager);
    }

    public void setUpItemDecoration(int spanCount, int spacing, boolean resevseItem) {
        setHasFixedSize(true);
        mGridSpacingItemDecoration = new GridSpacingItemDecoration(getContext(), spanCount,spacing,resevseItem);
        addItemDecoration(mGridSpacingItemDecoration);
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearLayoutManager;
    }

    public GridLayoutManager getGridLayoutManager() {
        return mGridLayoutManager;
    }
}
