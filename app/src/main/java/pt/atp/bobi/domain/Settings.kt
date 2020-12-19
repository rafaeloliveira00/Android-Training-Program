package pt.atp.bobi.domain

import pt.atp.bobi.INVALID_ID
import pt.atp.bobi.R
import pt.atp.bobi.domain.model.Setting


object Settings {

    val settings = listOf(
        Setting(Setting.AboutType.APP, R.drawable.ic_paw, R.string.app_name, R.string.about_copyright),
        Setting(
            Setting.AboutType.BODY,
            R.drawable.ic_email,
            R.string.about_email,
            website = "mailto:atp-suporte@googlegroups.com"
        ),
        Setting(
            Setting.AboutType.BODY,
            R.drawable.ic_link,
            R.string.about_website,
            website = "https://events.withgoogle.com/atp2020/"
        ),
        Setting(
            Setting.AboutType.BODY,
            R.drawable.ic_youtube,
            R.string.about_youtube,
            website = "https://www.youtube.com/channel/UCIEBWb2nz2huEllUHhH4Lfg"
        ),
        Setting(
            Setting.AboutType.BODY,
            R.drawable.ic_share,
            R.string.about_share,
            website = "https://twitter.com/intent/tweet?url=https%3A%2F%2Fevents.withgoogle.com%2Fatp2020%2F"
        ),

        Setting(Setting.AboutType.HEADER, INVALID_ID, R.string.about_authors),
        Setting(
            Setting.AboutType.BODY,
            R.drawable.ic_twitter,
            R.string.twitter,
            website = "https://twitter.com/"
        ),

        Setting(Setting.AboutType.HEADER, INVALID_ID, R.string.about_libraries),
        Setting(
            Setting.AboutType.BODY,
            R.drawable.ic_android,
            R.string.about_android,
            website = "https://developer.android.com/"
        ),
        Setting(
            Setting.AboutType.BODY,
            R.drawable.ic_square,
            R.string.about_retrofit,
            website = "https://github.com/square/retrofit"
        ),
        Setting(
            Setting.AboutType.BODY,
            R.drawable.ic_glide,
            R.string.about_glide,
            website = "https://github.com/bumptech/glide"
        ),
        Setting(
            Setting.AboutType.BODY,
            R.drawable.ic_icons8,
            R.string.about_icons8,
            website = "https://icons8.com"
        )
    )
}