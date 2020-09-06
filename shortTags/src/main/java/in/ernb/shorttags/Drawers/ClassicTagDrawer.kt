package `in`.ernb.shorttags.Drawers

import `in`.ernb.shorttags.TagView
import `in`.ernb.shorttags.Utils
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF

/**
 * Author Nadeem Bhat ,
 * Created by Nadeem Bhat on Sunday, Sep, 2020.
 * Copy Right (c) .
 * Srinagar,Kashmir
 * ernnadeembhat@gmail.com
 * ShortTags
 */

class ClassicTagDrawer : TagDrawer {
    override fun drawTag(bounds: Rect?, canvas: Canvas, data: TagView.TagViewData) {
        val rect: RectF? = bounds?.let { Utils.toRectF(it) }
        if (rect != null) {
            data.backgroundPaint?.let {
                canvas.drawRoundRect(rect, data.tagBorderRadius, data.tagBorderRadius,
                    it
                )
            }
        }
    }
}