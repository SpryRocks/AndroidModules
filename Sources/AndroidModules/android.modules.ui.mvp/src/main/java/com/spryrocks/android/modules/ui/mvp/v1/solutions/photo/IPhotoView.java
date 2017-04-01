package com.spryrocks.android.modules.ui.mvp.v1.solutions.photo;

import com.spryrocks.android.modules.ui.mvp.v1.ui.IBaseView;

import java.io.File;

public interface IPhotoView extends IBaseView {
    void photoTaken(String source, File photoFile, boolean isCropped);
    IPhotoRouter getRouter();
}
