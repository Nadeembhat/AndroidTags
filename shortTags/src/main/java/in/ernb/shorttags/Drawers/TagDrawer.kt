package `in`.ernb.shorttags.Drawers

import `in`.ernb.shorttags.TagView
import android.graphics.Canvas
import android.graphics.Rect


/**
 * Author Nadeem Bhat ,
 * Created by Nadeem Bhat on Sunday, Sep, 2020.
 * Copy Right (c) .
 * Srinagar,Kashmir
 * ernnadeembhat@gmail.com
 * ShortTags
 */

interface TagDrawer {
    // add java docs for this method
    fun drawTag(bounds: Rect?, canvas: Canvas, data: TagView.TagViewData)
}