package `in`.ernb.shorttags

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


object Utils {
    /**
     * Converts Rect object to RectF
     * @param rect
     * @return
     */
    fun toRectF(rect: Rect): RectF {
        return RectF(rect.left.toFloat(), rect.top.toFloat(), rect.right.toFloat(),
            rect.bottom.toFloat()
        )
    }
}