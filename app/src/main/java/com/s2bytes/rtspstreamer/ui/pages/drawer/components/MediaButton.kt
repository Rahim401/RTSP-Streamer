package com.s2bytes.rtspstreamer.ui.pages.drawer.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun MediaButton(@DrawableRes iconId:Int, onClick:()->Unit){
    IconButton(onClick, modifier = Modifier.size(50.dp)){
        Icon(
            painterResource(iconId), null,
            modifier = Modifier.fillMaxSize().padding(10.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}