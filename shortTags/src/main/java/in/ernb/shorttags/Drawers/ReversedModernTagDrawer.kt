package `in`.ernb.shorttags.Drawers

import `in`.ernb.shorttags.TagView
import `in`.ernb.shorttags.Utils
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF


/**
 * Author Nadeem Bhat ,
 * Created by Nadeem Bhat on Sunday, Sep, 2020.
 * Copy Right (c).
 * Srinagar,Kashmir
 * ernnadeembhat@gmail.com
 * ShortTags
 */

class ReversedModernTagDrawer : TagDrawer {
    override fun drawTag(bounds: Rect?, canvas: Canvas, data: TagView.TagViewData) {
        val rect: RectF = bounds?.let { Utils.toRectF(it) }!!
        canvas.drawRoundRect(rect, data.tagBorderRadius, data.tagBorderRadius,
            data.backgroundPaint!!
        )
        val xPos: Float = rect.right - data.tagRightPadding
        val yPos = (rect.bottom - rect.top) / 2
        canvas.drawCircle(xPos, yPos, data.tagCircleRadius, data.circlePaint!!)
    }
}