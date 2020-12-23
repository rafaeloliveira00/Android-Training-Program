package pt.atp.bobi

import android.os.Build

const val INVALID_ID = -1

const val EXTRA_USERNAME = "extra.username"

const val EXTRA_DOG_NAME = "extra.dog.name"
const val EXTRA_DOG_BREED = "extra.dog.breed"

fun deviceName() = "${Build.MODEL}-${Build.MANUFACTURER}"