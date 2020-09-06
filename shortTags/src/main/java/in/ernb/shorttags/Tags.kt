package `in`.ernb.shorttags

import android.graphics.Color

/**
 * Author Nadeem Bhat ,
 * Created by Nadeem Bhat on Sunday, Sep, 2020.
 * Copy Right (c).
 * Srinagar,Kashmir
 * ernnadeembhat@gmail.com
 * ShortTags
 */

object Tags {
    // Tag types
    const val CLASSIC = 0
    const val MODERN = 1
    const val SHARP = 2
    const val MODERN_SHARP = 3
    const val REVERSED_MODERN = 4
    const val REVERSED_SHARP = 5
    const val REVERSED_MODERN_SHARP = 6

    // Default tag paddings
    const val LEFT_PADDING_DEFAULT = 15
    const val RIGHT_PADDING_DEFAULT = 15
    const val TOP_PADDING_DEFAULT = 10
    const val BOTTOM_PADDING_DEFAULT = 10

    // Default tag colors
    const val TEXT_COLOR_DEFAULT = Color.WHITE
    const val CIRCLE_COLOR_DEFAULT = Color.WHITE

    // Tag constants
    const val BORDER_RADIUS_DEFAULT = 5
    const val CIRCLE_RADIUS_DEFAULT = 7
    const val MODERN_TAG_MULTIPLIER = 2 // used to draw crop inside modern tag
    const val SHARP_TAG_MULTIPLIER = 3 // used to draw triangle for "sharp" tag
}