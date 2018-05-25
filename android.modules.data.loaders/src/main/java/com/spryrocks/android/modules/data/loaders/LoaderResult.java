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

public class LoaderResult<T> {
    public static <T> LoaderResult<T> create(T value) {
        return new LoaderResult<T>(value, null);
    }

    public static <T> LoaderResult<T> create(Exception error) {
        return new LoaderResult<T>(null, error);
    }

    public final T value;
    public final Exception error;

    private LoaderResult(T value, Exception error) {
        this.value = value;
        this.error = error;
    }
}
