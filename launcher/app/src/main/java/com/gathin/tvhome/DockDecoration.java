package com.gathin.tvhome;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Spaces the dock row. The dock's N icons share one grid row (N equal cells), so each icon is shifted toward the
 * centre to keep the same spacing a full dock has, keeping the group centred. The icons float on the photo.
 */
public class DockDecoration extends RecyclerView.ItemDecoration {

    private final AppAdapter adapter;
    private final int columns;
    private final float density;

    DockDecoration(AppAdapter adapter, int columns, float density) {
        this.adapter = adapter;
        this.columns = columns;
        this.density = density;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        int pos = parent.getChildAdapterPosition(view);
        int n = adapter.dockCount();
        if (pos < 0 || pos >= n) return;
        outRect.top = (int) (6 * density);
        outRect.bottom = (int) (12 * density);
        float inner = parent.getMeasuredWidth() - parent.getPaddingLeft() - parent.getPaddingRight();
        float actual = (pos + 0.5f) * inner / n;
        float wanted = inner / 2f + (pos - (n - 1) / 2f) * inner / columns;
        int shift = Math.round(2 * (wanted - actual));   // the item's content is centred: offsets move it by half
        if (shift > 0) outRect.left = shift;
        else outRect.right = -shift;
    }
}
