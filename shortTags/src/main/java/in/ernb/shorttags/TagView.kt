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

class TagView(context: Context,attributeSet: AttributeSet) : AppCompatTextView(context) {



    class TagViewData {
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

    private var data: TagViewData
    init {
        val typedArray = context.theme.obtainStyledAttributes(attributeSet, R.styleable.TagView, 0, 0)
        data = TagViewData()
        try {
            data!!.tagType = typedArray.getInteger(R.styleable.TagView_tagType, CLASSIC)
            data!!.tagColor = typedArray.getColor(R.styleable.TagView_tagColor, Color.BLACK)
            data!!.tagUpperCase = typedArray.getBoolean(R.styleable.TagView_tagUpperCase, false)
            data!!.tagBorderRadius =
                typedArray.getInteger(R.styleable.TagView_tagBorderRadius, BORDER_RADIUS_DEFAULT)
                    .toFloat()
            data!!.tagCircleRadius =
                typedArray.getInteger(R.styleable.TagView_tagCircleRadius, CIRCLE_RADIUS_DEFAULT)
                    .toFloat()
            data!!.tagCircleColor =
                typedArray.getColor(R.styleable.TagView_tagCircleColor, CIRCLE_COLOR_DEFAULT)
            data!!.tagTextColor =
                typedArray.getColor(R.styleable.TagView_tagTextColor, TEXT_COLOR_DEFAULT)
        } finally {
            typedArray.recycle()
        }
        initPadding()
        init()
    }

    private inner class TagDrawable : Drawable() {
        val no: Int = 0
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
        override fun getOpacity(): Int {
            return no
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

    init {
        val typedArray = context.theme.obtainStyledAttributes(attributeSet, R.styleable.TagView, 0, 0)
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
    fun getTagType(): Int {
        return data!!.tagType
    }

    fun setTagType(tagType: Int) {
        data!!.tagType = tagType
        invalidate()
        requestLayout()
    }

    fun getTagColor(): Int {
        return data!!.tagColor
    }

    fun setTagColor(tagColor: Int) {
        data!!.tagColor = tagColor
        init()
        invalidate()
        requestLayout()
    }

    fun isTagUpperCase(): Boolean {
        return data!!.tagUpperCase
    }

    fun setTagUpperCase(tagUpperCase: Boolean) {
        data!!.tagUpperCase = tagUpperCase
        invalidate()
        requestLayout()
    }

    fun getTagBorderRadius(): Float {
        return data!!.tagBorderRadius
    }

    fun setTagBorderRadius(tagBorderRadius: Int) {
        data!!.tagBorderRadius = tagBorderRadius.toFloat()
        invalidate()
        requestLayout()
    }

    fun getTagCircleRadius(): Float {
        return data!!.tagCircleRadius
    }

    fun setTagCircleRadius(tagCircleRadius: Float) {
        data!!.tagCircleRadius = tagCircleRadius
        invalidate()
        requestLayout()
    }

    fun getTagCircleColor(): Int {
        return data!!.tagCircleColor
    }

    fun setTagCircleColor(tagCircleColor: Int) {
        data!!.tagCircleColor = tagCircleColor
        init()
        invalidate()
        requestLayout()
    }

    fun getTagTextColor(): Int {
        return data!!.tagTextColor
    }

    fun setTagTextColor(tagTextColor: Int) {
        data!!.tagTextColor = tagTextColor
        invalidate()
        requestLayout()
    }

    fun getTagLeftPadding(): Int {
        return data!!.tagLeftPadding
    }

    fun setTagLeftPadding(tagLeftPadding: Int) {
        data!!.tagLeftPadding = tagLeftPadding
        invalidate()
        requestLayout()
    }

    fun getTagRightPadding(): Int {
        return data!!.tagRightPadding
    }

    fun setTagRightPadding(tagRightPadding: Int) {
        data!!.tagRightPadding = tagRightPadding
        invalidate()
        requestLayout()
    }

    fun getTagTopPadding(): Int {
        return data!!.tagTopPadding
    }

    fun setTagTopPadding(tagTopPadding: Int) {
        data!!.tagTopPadding = tagTopPadding
        invalidate()
        requestLayout()
    }

    fun getTagBottomPadding(): Int {
        return data!!.tagBottomPadding
    }

    fun setTagBottomPadding(tagBottomPadding: Int) {
        data!!.tagBottomPadding = tagBottomPadding
        invalidate()
        requestLayout()
    }
}