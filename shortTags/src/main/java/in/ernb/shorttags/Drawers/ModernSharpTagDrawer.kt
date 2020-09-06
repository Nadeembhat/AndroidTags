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
 * Copy Right (c) .
 * Srinagar,Kashmir
 * ernnadeembhat@gmail.com
 * ShortTags
 */

class ModernSharpTagDrawer : SharpTagDrawer() {
    override fun drawTag(bounds: Rect?, canvas: Canvas, data: TagView.TagViewData) {
        val formattedBounds: RectF = bounds?.let { Utils.toRectF(it) }!!
        val rect = RectF(formattedBounds)
        rect.right -= data.tagRightPadding * SHARP_TAG_MULTIPLIER
        data.backgroundPaint?.let { canvas.drawRect(rect, it) }
        val xPos: Float = formattedBounds.left + data.tagLeftPadding
        val yPos = (formattedBounds.bottom - formattedBounds.top) / 2
        data.circlePaint?.let { canvas.drawCircle(xPos, yPos, data.tagCircleRadius, it) }
        val halfOfRectHeight = (rect.bottom - rect.top) / 2
        val trianglePath = createTrianglePath(data, rect, halfOfRectHeight)
        data.trianglePaint?.let { canvas.drawPath(trianglePath!!, it) }
    }
}