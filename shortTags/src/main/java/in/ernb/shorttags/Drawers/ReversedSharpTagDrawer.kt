package `in`.ernb.shorttags.Drawers

import `in`.ernb.shorttags.TagView
import `in`.ernb.shorttags.Tags.SHARP_TAG_MULTIPLIER
import `in`.ernb.shorttags.Utils
import android.graphics.Canvas
import android.graphics.Path
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

open class ReversedSharpTagDrawer : TagDrawer {
    override fun drawTag(bounds: Rect?, canvas: Canvas, data: TagView.TagViewData) {
        val formattedBounds: RectF = bounds?.let { Utils.toRectF(it) }!!
        val rect = RectF(formattedBounds)
        rect.left += data.tagRightPadding * SHARP_TAG_MULTIPLIER
        val halfOfRectHeight = (rect.bottom - rect.top) / 2
        canvas.drawRect(rect, data.backgroundPaint!!)
        val trianglePath = createTrianglePath(data, rect, halfOfRectHeight)
        canvas.drawPath(trianglePath, data.trianglePaint!!)
    }

    protected fun createTrianglePath(
        data: TagView.TagViewData,
        rect: RectF,
        halfOfRectHeight: Float
    ): Path {
        val path = Path()
        path.setFillType(Path.FillType.EVEN_ODD)
        path.moveTo(rect.left, rect.top)
        path.lineTo(rect.left - data.tagLeftPadding * SHARP_TAG_MULTIPLIER, halfOfRectHeight)
        path.lineTo(rect.left, rect.bottom)
        path.lineTo(rect.left, rect.top)
        return path
    }
}