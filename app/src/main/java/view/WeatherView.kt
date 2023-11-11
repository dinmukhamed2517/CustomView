package view

import android.content.Context
import android.content.res.TypedArray
import android.text.SpannableString
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.TypedArrayUtils.getString
import kz.just_code.customviewweather.R
import kz.just_code.customviewweather.databinding.ViewWeatherBinding

class WeatherView @JvmOverloads constructor(
    context: Context, attrs:AttributeSet? = null, defStyleAttr:Int = 0
):ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: ViewWeatherBinding =
        ViewWeatherBinding.inflate(LayoutInflater.from(context), this)

    init {
        setAttrs(attrs, R.styleable.WeatherView) {
            binding.temp.text = "${it.getString(R.styleable.WeatherView_weather_temp)}º"
            val type = when (it.getInt(R.styleable.WeatherView_weather_type, 0)) {
                0 -> Type.TORNADO
                1 -> Type.NIGHT_RAIN_CLOUD
                2 -> Type.NIGHT_CLOUD_WIND
                3 -> Type.DAY_RAIN_CLOUD
                else -> Type.NIGHT_RAIN_CLOUD
            }
            binding.image.setImageResource(type.image)
            binding.description.setText(type.description)
            val location = "${it.getString(R.styleable.WeatherView_weather_city)}, ${it.getString(R.styleable.WeatherView_weather_country)}"
            binding.location.text = location
        }
    }

    enum class Type(@DrawableRes val image: Int, @StringRes val description: Int) {
        DAY_RAIN_CLOUD(R.drawable.day_cloud_rain, R.string.day_cloud_rain),
        NIGHT_RAIN_CLOUD(R.drawable.night_cloud_rain, R.string.night_cloud_rain),
        NIGHT_CLOUD_WIND(R.drawable.night_cloud_wind, R.string.night_cloud_wind),
        TORNADO(R.drawable.tornado, R.string.tornado),

    }


    inline fun View.setAttrs(
        attrs: AttributeSet?,
        styleable: IntArray,
        crossinline body: (TypedArray) -> Unit
    ) {
        context.theme.obtainStyledAttributes(attrs, styleable, 0, 0)
            .apply {
                try {
                    body.invoke(this)
                } finally {
                    recycle()
                }
            }
    }
}

