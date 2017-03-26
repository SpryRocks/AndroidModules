package com.spryrocks.android.modules.data.loaders;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class PaginationLoader<T> extends AsyncTaskLoaderBase<List<T>> implements IPaginationLoader {
    public static <T> PaginationLoader<T> create(Context context, IDataSource<T> dataSource) {
        return new PaginationLoader<T>(context, dataSource);
    }

    private final ILoaderAlgorithm<T> algorithm;

    private int count = -1;
    private boolean isEnd;

    private final List<T> items;

    public PaginationLoader(Context context, IDataSource<T> dataSource) {
        super(context);

        if(dataSource == null)
            dataSource = new EmptyDataSource<>();

        this.algorithm = dataSource.createAlgorithm(context);

        this.items = new ArrayList<T>();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        onContentChanged();
    }

    @Override
    public LoaderResult<List<T>> loadInBackground() {
        try {

            boolean isPaginationEnabled = algorithm.isPaginationSupported() && count > -1;

            if(isPaginationEnabled) {

                while (items.size() < count && !isEnd) {

                    int offset = items.size();

                    List<T> items = algorithm.loadItems(offset, count);
                    if (items.size() < 1) {
                        isEnd = true;
                        break;
                    }
                    this.items.addAll(items);
                }

            }
            else {
                List<T> postInfos = algorithm.loadItems(0, -1);
                items.clear();
                items.addAll(postInfos);
                isEnd = true;
            }

        } catch (Exception e) {
            isEnd = true;
            return LoaderResult.create(e);
        }
        return LoaderResult.create(items);
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
