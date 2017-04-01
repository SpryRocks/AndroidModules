package com.spryrocks.android.modules.ui.mvp.v1.solutions.photo;

import java.io.File;
import java.io.IOException;

public interface IFileCreator {
    File createTempFile() throws IOException;
}
