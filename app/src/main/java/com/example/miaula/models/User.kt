package com.example.miaula.models

import android.net.Uri

data class User(var idUser: String, var email: String, var name: String, var number: String, val photo: Uri?)
