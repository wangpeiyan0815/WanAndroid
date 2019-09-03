package com.wpy.wanandroid.utils.status;

import android.app.Activity;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

interface OsStatusBarCompat {
    void setDarkIconMode(@NonNull Activity activity, boolean darkIconMode);
    void setDarkIconMode(@NonNull Fragment fragment, boolean darkIconMode);
    void setDarkIconMode(@NonNull Window window, boolean darkIconMode);
}