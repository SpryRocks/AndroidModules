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
