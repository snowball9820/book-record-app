package com.app.bookrecordapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.bookrecordapp.R

val maruburi = FontFamily(
    Font(R.font.maruburi_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.maruburi_extralight, FontWeight.ExtraLight,FontStyle.Normal),
    Font(R.font.maruburi_regular, FontWeight.Medium,FontStyle.Normal),
    Font(R.font.maruburi_semibold, FontWeight.SemiBold,FontStyle.Normal),
    Font(R.font.maruburi_bold, FontWeight.Bold,FontStyle.Normal)

)
val bmeuljiro=FontFamily(
    Font(R.font.mbeuljiro,FontWeight.Light,FontStyle.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(

    bodyMedium = TextStyle(
        fontFamily = bmeuljiro,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = bmeuljiro,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)