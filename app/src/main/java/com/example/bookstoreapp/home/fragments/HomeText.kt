package com.example.bookstoreapp.home.fragments

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun HomeText(
    text: String,
    modifier: Modifier = Modifier,
    size: TextUnit = 25.sp
){
    Text(
        text,
        color = Color.Black,
        fontSize = size,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Light,
        modifier = modifier
    )
}