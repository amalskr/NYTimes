package com.ceylonapz.nytimes.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ceylonapz.nytimes.model.News;
import com.ceylonapz.nytimes.view.DetailsActivity;

/**
 * Created by AMAL on 2018-08-08.
 */

public class NewsClickHandler {

    private final Context context;

    public NewsClickHandler(Context context) {
        this.context = context;
    }

    public void onSaveClick(View view, News news) {
        Intent nextInt = new Intent(view.getContext(), DetailsActivity.class);
        nextInt.putExtra("SELECTED_NEWS",news);
        context.startActivity(nextInt);
    }
}
