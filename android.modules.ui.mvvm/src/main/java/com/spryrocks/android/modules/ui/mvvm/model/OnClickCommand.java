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

package com.spryrocks.android.modules.ui.mvvm.model;

import android.support.annotation.Nullable;
import android.view.View;

@SuppressWarnings("unused")
public class OnClickCommand extends Command implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        click();
    }

    public static class Default1<T> extends Command1.Default<T> implements View.OnClickListener {
        public Default1() {
        }

        public Default1(@Nullable T defaultValue) {
            super(defaultValue);
        }

        @Override
        public void onClick(View view) {
            click();
        }
    }
}
