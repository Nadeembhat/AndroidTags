package `in`.ernb.shorttags

import `in`.ernb.shorttags.Drawers.*
import `in`.ernb.shorttags.Tags.BORDER_RADIUS_DEFAULT
import `in`.ernb.shorttags.Tags.BOTTOM_PADDING_DEFAULT
import `in`.ernb.shorttags.Tags.CIRCLE_COLOR_DEFAULT
import `in`.ernb.shorttags.Tags.CIRCLE_RADIUS_DEFAULT
import `in`.ernb.shorttags.Tags.CLASSIC
import `in`.ernb.shorttags.Tags.LEFT_PADDING_DEFAULT
import `in`.ernb.shorttags.Tags.MODERN
import `in`.ernb.shorttags.Tags.MODERN_SHARP
import `in`.ernb.shorttags.Tags.MODERN_TAG_MULTIPLIER
import `in`.ernb.shorttags.Tags.REVERSED_MODERN
import `in`.ernb.shorttags.Tags.REVERSED_MODERN_SHARP
import `in`.ernb.shorttags.Tags.REVERSED_SHARP
import `in`.ernb.shorttags.Tags.RIGHT_PADDING_DEFAULT
import `in`.ernb.shorttags.Tags.SHARP
import `in`.ernb.shorttags.Tags.SHARP_TAG_MULTIPLIER
import `in`.ernb.shorttags.Tags.TEXT_COLOR_DEFAULT
import `in`.ernb.shorttags.Tags.TOP_PADDING_DEFAULT
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * Author Nadeem Bhat ,
 * Created by Nadeem Bhat on Sunday, Sep, 2020.
 * Copy Right (c).
 * Srinagar,Kashmir
 * ernnadeembhat@gmail.com
 * ShortTags
 */

class TagView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    inner class TagViewData {
        // TagView properties
        var tagType = 0
        var tagColor = 0
        var tagTextColor = 0
        var tagUpperCase = false
        var tagBorderRadius = 0f
        var tagCircleRadius = 0f
        var tagCircleColor = 0
        var tagLeftPadding = 0
        var tagRightPadding = 0
        var tagTopPadding = 0
        var tagBottomPadding = 0

        // Paint object for drawing various types of tags
        var backgroundPaint: Paint? = null
        var circlePaint: Paint? = null
        var trianglePaint: Paint? = null
    }

    private val data: TagViewData

    private inner class TagDrawable : Drawable() {
        override fun setAlpha(alpha: Int) {}

        override fun draw(canvas: Canvas) {
            val bounds = bounds
            if (data.tagType == CLASSIC) {
                setPadding(
                    data.tagLeftPadding, data.tagTopPadding,
                    data.tagRightPadding, data.tagBottomPadding
                )
                ClassicTagDrawer().drawTag(bounds, canvas, data)
            } else if (data.tagType == MODERN) {
                setPadding(
                    data.tagLeftPadding * MODERN_TAG_MULTIPLIER, data.tagTopPadding,
                    data.tagRightPadding, data.tagBottomPadding
                )
                ModernTagDrawer().drawTag(bounds, canvas, data)
            } else if (data.tagType == REVERSED_MODERN) {
                setPadding(
                    data.tagLeftPadding, data.tagTopPadding,
                    data.tagRightPadding * MODERN_TAG_MULTIPLIER, data.tagBottomPadding
                )
                ReversedModernTagDrawer().drawTag(bounds, canvas, data)
            } else if (data.tagType == SHARP) {
                setPadding(
                    data.tagLeftPadding, data.tagTopPadding,
                    data.tagRightPadding * SHARP_TAG_MULTIPLIER, data.tagBottomPadding
                )
                SharpTagDrawer().drawTag(bounds, canvas, data)
            } else if (data.tagType == REVERSED_SHARP) {
                setPadding(
                    data.tagLeftPadding * SHARP_TAG_MULTIPLIER, data.tagTopPadding,
                    data.tagRightPadding, data.tagBottomPadding
                )
                ReversedSharpTagDrawer().drawTag(bounds, canvas, data)
            } else if (data.tagType == MODERN_SHARP) {
                setPadding(
                    data.tagLeftPadding * MODERN_TAG_MULTIPLIER, data.tagTopPadding,
                    data.tagRightPadding * SHARP_TAG_MULTIPLIER, data.tagBottomPadding
                )
                ModernSharpTagDrawer().drawTag(bounds, canvas, data)
            } else if (data.tagType == REVERSED_MODERN_SHARP) {
                setPadding(
                    data.tagLeftPadding * SHARP_TAG_MULTIPLIER, data.tagTopPadding,
                    data.tagRightPadding * MODERN_TAG_MULTIPLIER, data.tagBottomPadding
                )
                ReversedModernSharpTagDrawer().drawTag(bounds, canvas, data)
            }
            setTextColor(data.tagTextColor)
        }

        override fun setColorFilter(colorFilter: ColorFilter?) {}
        @SuppressLint("WrongConstant")
        override fun getOpacity(): Int {
            return 0
        }
    }

    public override fun onDraw(canvas: Canvas) {
        if (data.tagUpperCase) text = text.toString().toUpperCase()
        super.onDraw(canvas)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            background = TagDrawable()
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(TagDrawable())
        }
    }

    /**
     * Initializes Paint objects that will be used to draw tags, speed up draw method
     */
    private fun init() {
        data.backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        data.backgroundPaint!!.color = data.tagColor
        data.circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        data.circlePaint!!.color = data.tagCircleColor
        data.trianglePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        data.trianglePaint!!.color = data.tagColor
        data.trianglePaint!!.style = Paint.Style.FILL
    }

    /**
     * Set default values for padding, if you not specify it in xml layout
     */
    private fun initPadding() {
        val left = paddingLeft
        val right = paddingRight
        val top = paddingTop
        val bottom = paddingBottom
        if (left == 0) data.tagLeftPadding = LEFT_PADDING_DEFAULT else data.tagLeftPadding = left
        if (right == 0) data.tagRightPadding = RIGHT_PADDING_DEFAULT else data.tagRightPadding =
            right
        if (top == 0) data.tagTopPadding = TOP_PADDING_DEFAULT else data.tagTopPadding = top
        if (bottom == 0) data.tagBottomPadding = BOTTOM_PADDING_DEFAULT else data.tagBottomPadding =
            bottom
    }

    var tagType: Int
        get() = data.tagType
        set(tagType) {
            data.tagType = tagType
            invalidate()
            requestLayout()
        }
    var tagColor: Int
        get() = data.tagColor
        set(tagColor) {
            data.tagColor = tagColor
            init()
            invalidate()
            requestLayout()
        }
    var isTagUpperCase: Boolean
        get() = data.tagUpperCase
        set(tagUpperCase) {
            data.tagUpperCase = tagUpperCase
            invalidate()
            requestLayout()
        }
    val tagBorderRadius: Float
        get() = data.tagBorderRadius

    fun setTagBorderRadius(tagBorderRadius: Int) {
        data.tagBorderRadius = tagBorderRadius.toFloat()
        invalidate()
        requestLayout()
    }

    var tagCircleRadius: Float
        get() = data.tagCircleRadius
        set(tagCircleRadius) {
            data.tagCircleRadius = tagCircleRadius
            invalidate()
            requestLayout()
        }
    var tagCircleColor: Int
        get() = data.tagCircleColor
        set(tagCircleColor) {
            data.tagCircleColor = tagCircleColor
            init()
            invalidate()
            requestLayout()
        }
    var tagTextColor: Int
        get() = data.tagTextColor
        set(tagTextColor) {
            data.tagTextColor = tagTextColor
            invalidate()
            requestLayout()
        }
    var tagLeftPadding: Int
        get() = data.tagLeftPadding
        set(tagLeftPadding) {
            data.tagLeftPadding = tagLeftPadding
            invalidate()
            requestLayout()
        }
    var tagRightPadding: Int
        get() = data.tagRightPadding
        set(tagRightPadding) {
            data.tagRightPadding = tagRightPadding
            invalidate()
            requestLayout()
        }
    var tagTopPadding: Int
        get() = data.tagTopPadding
        set(tagTopPadding) {
            data.tagTopPadding = tagTopPadding
            invalidate()
            requestLayout()
        }
    var tagBottomPadding: Int
        get() = data.tagBottomPadding
        set(tagBottomPadding) {
            data.tagBottomPadding = tagBottomPadding
            invalidate()
            requestLayout()
        }

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.TagView, 0, 0)
        data = TagViewData()
        try {
            data.tagType = typedArray.getInteger(R.styleable.TagView_tagType, CLASSIC)
            data.tagColor = typedArray.getColor(R.styleable.TagView_tagColor, Color.BLACK)
            data.tagUpperCase = typedArray.getBoolean(R.styleable.TagView_tagUpperCase, false)
            data.tagBorderRadius =
                typedArray.getInteger(R.styleable.TagView_tagBorderRadius, BORDER_RADIUS_DEFAULT)
                    .toFloat()
            data.tagCircleRadius =
                typedArray.getInteger(R.styleable.TagView_tagCircleRadius, CIRCLE_RADIUS_DEFAULT)
                    .toFloat()
            data.tagCircleColor =
                typedArray.getColor(R.styleable.TagView_tagCircleColor, CIRCLE_COLOR_DEFAULT)
            data.tagTextColor =
                typedArray.getColor(R.styleable.TagView_tagTextColor, TEXT_COLOR_DEFAULT)
        } finally {
            typedArray.recycle()
        }
        initPadding()
        init()
    }
}