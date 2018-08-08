package com.ceylonapz.nytimes.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ceylonapz.nytimes.databinding.NewsDataBinding;
import com.ceylonapz.nytimes.model.News;
import com.ceylonapz.nytimes.presenter.NewsClickHandler;

import java.util.List;

/**
 * Created by AMAL on 2018-08-08.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final Context mContext;
    private final List<News> newsList;
    private LayoutInflater layoutInflater;

    NewsAdapter(Context context, List<News> dataList) {
        this.mContext = context;
        this.newsList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        NewsDataBinding dataBinding = NewsDataBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(dataBinding);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //SET VIEW DATA
        final News news = newsList.get(position);
        holder.bind(news);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final NewsDataBinding newsDataBinding;

        ViewHolder(NewsDataBinding dataBinding) {
            super(dataBinding.getRoot());
            this.newsDataBinding = dataBinding;
            newsDataBinding.setHandler(new NewsClickHandler(mContext));
        }

        void bind(News news) {
            this.newsDataBinding.setNews(news);
        }
    }
}
