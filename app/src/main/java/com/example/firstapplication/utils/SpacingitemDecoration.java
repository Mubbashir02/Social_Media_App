package com.example.firstapplication.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacingitemDecoration extends RecyclerView.ItemDecoration {
    private final int verticalspaceheight;

    public SpacingitemDecoration(int verticalspaceheight) {
        this.verticalspaceheight = verticalspaceheight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = verticalspaceheight;
    }
}
