package com.mg.otpview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.view.KeyEvent
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatEditText

class OTPView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var boxCount = 4
    private var boxSize = 50 // default in dp
    private var boxSpacing = 8 // default in dp
    private var boxElevation = 1 // default in dp
    private var boxStrokeSize = 4 // default in dp
    private var boxShape = 0 // default in Square
    private var cornerRadiusAll = 0
    private var cornerRadiusTopLeft = 0
    private var cornerRadiusTopRight = 0
    private var cornerRadiusBottomLeft = 0
    private var cornerRadiusBottomRight = 0

    private var activeBackgroundColor = Color.WHITE
    private var inactiveBackgroundColor = Color.LTGRAY
    private var focusBackgroundColor = Color.YELLOW
    private var activeStrokeColor = Color.BLUE
    private var focusStrokeColor = Color.BLUE
    private var inactiveStrokeColor = Color.GRAY
    private var activeTextColor = Color.WHITE
    private var focusTextColor = Color.BLACK
    private var cursorDrawableRes: Int = 0

    private var inputTypeText = InputType.TYPE_CLASS_NUMBER // Default
    private val editTexts = mutableListOf<AppCompatEditText>()
    private var onCompleteOtp: ((String) -> Unit)? = null
    fun setOnCompleteOtpListener(listener: (String) -> Unit) {
        this.onCompleteOtp = listener
    }

    init {
        orientation = HORIZONTAL
        context.theme.obtainStyledAttributes(attrs, R.styleable.OTPView, 0, 0).apply {
            try {
                inputTypeText = getInt(R.styleable.OTPView_inputType, InputType.TYPE_CLASS_NUMBER)
                boxCount = getInt(R.styleable.OTPView_boxCount, 4)
                boxSize = getDimensionPixelSize(R.styleable.OTPView_boxSize, 50)
                boxSpacing = getDimensionPixelSize(R.styleable.OTPView_boxSpacing, 8)
                boxElevation = getDimensionPixelSize(R.styleable.OTPView_boxElevation, 1)
                boxStrokeSize = getDimensionPixelSize(R.styleable.OTPView_boxStrokeSize, 4)
                boxShape = getInt(R.styleable.OTPView_boxShape, 0)
                cornerRadiusAll = getDimensionPixelSize(R.styleable.OTPView_boxCornerRadiusAll, 0)
                cornerRadiusTopLeft =
                    getDimensionPixelSize(R.styleable.OTPView_boxCornerRadiusTopLeft, 0)
                cornerRadiusTopRight =
                    getDimensionPixelSize(R.styleable.OTPView_boxCornerRadiusTopRight, 0)
                cornerRadiusBottomLeft =
                    getDimensionPixelSize(R.styleable.OTPView_boxCornerRadiusBottomLeft, 0)
                cornerRadiusBottomRight =
                    getDimensionPixelSize(R.styleable.OTPView_boxCornerRadiusBottomRight, 0)
                activeBackgroundColor =
                    getColor(R.styleable.OTPView_activeBackgroundColor, Color.WHITE)
                inactiveBackgroundColor =
                    getColor(R.styleable.OTPView_inactiveBackgroundColor, Color.LTGRAY)
                focusBackgroundColor =
                    getColor(R.styleable.OTPView_focusBackgroundColor, Color.YELLOW)
                activeStrokeColor = getColor(R.styleable.OTPView_activeStrokeColor, Color.BLUE)
                focusStrokeColor = getColor(R.styleable.OTPView_focusStrokeColor, Color.BLUE)
                inactiveStrokeColor = getColor(R.styleable.OTPView_inactiveStrokeColor, Color.GRAY)
                cursorDrawableRes = getResourceId(R.styleable.OTPView_cursorDrawable, 0)
                activeTextColor = getColor(R.styleable.OTPView_activeTextColor, Color.WHITE)
                focusTextColor = getColor(R.styleable.OTPView_focusTextColor, Color.BLACK)
            } finally {
                recycle()
            }
        }

        clipChildren = false
        clipToPadding = false

        setupView()

    }

    private fun setupView() {
        removeAllViews()
        editTexts.clear()

        for (i in 0 until boxCount) {
            val editText = AppCompatEditText(context).apply {
                layoutParams = LayoutParams(boxSize, boxSize).apply {
                    setMargins(boxSpacing, 20, boxSpacing, 20)
                }

                elevation = boxElevation.toFloat()

                textAlignment = TEXT_ALIGNMENT_CENTER
                filters = arrayOf(InputFilter.LengthFilter(1))

                if(boxShape ==3){
                    gravity = Gravity.CENTER_HORIZONTAL // Align text horizontally centered
                    setPadding(0, 0, 0, 20)
                }

                background = createBackgroundDrawable(inactiveBackgroundColor, inactiveStrokeColor)
                isFocusableInTouchMode = true
                textSize = 18f
                inputType = inputTypeText
                setSingleLine(true)
                if (cursorDrawableRes != 0) {
                    setCustomCursorDrawable(cursorDrawableRes)
                }
                setLayerType(LAYER_TYPE_SOFTWARE, null) // Prevent clipping of strokes

                // Focus change listener
                onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
                    background = when {
                        hasFocus -> createBackgroundDrawable(focusBackgroundColor, focusStrokeColor)
                        text.isNullOrEmpty() -> {createBackgroundDrawable(
                            inactiveBackgroundColor,
                            inactiveStrokeColor
                        )
                        }

                        else -> createBackgroundDrawable(activeBackgroundColor, activeStrokeColor)
                    }
                    setTextColor(if (hasFocus) focusTextColor else activeTextColor)
                }

                // Text watcher
                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int,
                    ) {


                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int,
                    ) {
                        if (s?.isNotEmpty() == true) {
                            background = createBackgroundDrawable(
                                activeBackgroundColor, activeStrokeColor
                            )
                            setTextColor(activeTextColor) // Set color after filling
                            if (i < boxCount - 1) {
                                editTexts[i + 1].requestFocus()
                            }
                        } else {
                            if (i == boxCount - 1) {
                                background = createBackgroundDrawable(
                                    focusBackgroundColor, focusStrokeColor
                                )
                                setTextColor(focusTextColor) // Set color after filling
                            }
                        }


                    }

                    override fun afterTextChanged(s: Editable?) {
                        editTexts[i].apply {
                            setSelection(text?.length ?: 0)
                        }
                        if (isOTPFilled) {
                            onCompleteOtp?.invoke(getOTP())
                        }

                    }
                })

                // Backspace navigation
                setOnKeyListener { _, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                        if (text.isNullOrEmpty() && i > 0) {
                            editTexts[i - 1].apply {
                                requestFocus()
                                setText("")
                            }
                        } else {
                            editTexts[i].apply {
                                requestFocus()
                                setText("")
                            }
                        }
                        true
                    } else {
                        false
                    }
                }


            }

            editTexts.add(editText)
            addView(editText)
        }
    }

    @SuppressLint("DiscouragedPrivateApi")
    private fun AppCompatEditText.setCustomCursorDrawable(cursorDrawableRes: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            textCursorDrawable = AppCompatResources.getDrawable(context, cursorDrawableRes)
        }else{
            try {
                // Get the cursor resource ID
                val field = TextView::class.java.getDeclaredField("mCursorDrawableRes")
                field.isAccessible = true
                field.setInt(this, cursorDrawableRes)
            } catch (e: Exception) {
                // Handle exception (e.g., log it)
                e.printStackTrace()
            }
        }
    }

    private fun createBackgroundDrawable(backgroundColor: Int, strokeColor: Int): GradientDrawable {
        return GradientDrawable().apply {

            setColor(backgroundColor)
            setStroke(boxStrokeSize, strokeColor)
            when (boxShape) {
                0 ->  // Square
                    cornerRadius = 0f

                1 ->  // Rounded
                    if (cornerRadiusAll.toFloat() != 0f) {
                        cornerRadius = cornerRadiusAll.toFloat()

                    } else {
                        cornerRadii = floatArrayOf(
                            cornerRadiusTopLeft.toFloat(),
                            cornerRadiusTopLeft.toFloat(),
                            cornerRadiusTopRight.toFloat(),
                            cornerRadiusTopRight.toFloat(),
                            cornerRadiusBottomRight.toFloat(),
                            cornerRadiusBottomRight.toFloat(),
                            cornerRadiusBottomLeft.toFloat(),
                            cornerRadiusBottomLeft.toFloat()
                        )

                    }

                2 ->  // Circle
                    shape = GradientDrawable.OVAL

                3 ->  // Line
                    shape = GradientDrawable.LINE
            }
        }
    }


    fun getOTP(): String {
        return editTexts.joinToString("") { it.text.toString() }
    }


    val length: Int
        get() = editTexts.count { it.text?.isNotEmpty() == true }

    val isOTPFilled: Boolean get() = editTexts.all { it.text?.isNotEmpty() == true }

    fun setOTP(otp: String) {
        clearOTP()
        otp.forEachIndexed { index, char ->
            if (index < boxCount) {
                editTexts[index].setText(char.toString())
            }
        }
    }

    fun clearOTP() {
        editTexts.forEach { it.text?.clear() }
    }
}
