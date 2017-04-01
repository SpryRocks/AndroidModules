package com.spryrocks.android.modules.ui.mvp.v1.solutions.photo;

import java.io.File;

public interface IPhotoRouter {
    void launchCamera(File photoFile, int requestCode);
    void launchGallery(int requestCode);
    void launchCropper(File photoFile, int requestCode, File cropPhotoFile, int crop_width, int crop_height);
}
