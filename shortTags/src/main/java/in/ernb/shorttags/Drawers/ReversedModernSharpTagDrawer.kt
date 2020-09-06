package `in`.ernb.shorttags.Drawers

import `in`.ernb.shorttags.TagView
import `in`.ernb.shorttags.Tags.SHARP_TAG_MULTIPLIER
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

class ReversedModernSharpTagDrawer : ReversedSharpTagDrawer() {
    override fun drawTag(bounds: Rect?, canvas: Canvas, data: TagView.TagViewData) {
        val formattedBounds: RectF = bounds?.let { Utils.toRectF(it) }!!
        val rect = RectF(formattedBounds)
        rect.left += data.tagRightPadding * SHARP_TAG_MULTIPLIER
        val halfOfRectHeight = (rect.bottom - rect.top) / 2
        canvas.drawRect(rect, data.backgroundPaint!!)
        val xPos: Float = formattedBounds.right - data.tagRightPadding
        val yPos = (formattedBounds.bottom - formattedBounds.top) / 2
        canvas.drawCircle(xPos, yPos, data.tagCircleRadius, data.circlePaint!!)
        val trianglePath = createTrianglePath(data, rect, halfOfRectHeight)
        data.trianglePaint?.let { canvas.drawPath(trianglePath!!, it) }
    }
}