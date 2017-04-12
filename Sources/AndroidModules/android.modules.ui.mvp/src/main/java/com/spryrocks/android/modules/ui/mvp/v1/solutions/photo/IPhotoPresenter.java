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

package com.spryrocks.android.modules.ui.mvp.v1.solutions.photo;

import android.support.annotation.StringDef;

import com.spryrocks.android.modules.ui.mvp.v1.IPresenter;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface IPhotoPresenter extends IPresenter<IPhotoView> {
    String SOURCE_CAMERA = "SOURCE_CAMERA";
    String SOURCE_GALLERY = "SOURCE_GALLERY";
    String SOURCE_FILE = "SOURCE_FILE";

    void takePhoto(@Source String source, boolean cropPhoto);
    void takePhoto(@Source String source);
    void cropPhoto(File photoFile);

    IConfig config();

    @StringDef({SOURCE_CAMERA, SOURCE_GALLERY})
    @Retention(RetentionPolicy.SOURCE)
    @interface Source {
    }
}
