/*
 * Copyright 2017 Spry Rocks, Inc
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

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
