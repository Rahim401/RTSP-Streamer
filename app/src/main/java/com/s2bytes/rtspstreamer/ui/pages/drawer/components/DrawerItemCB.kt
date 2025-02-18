package com.s2bytes.rtspstreamer.ui.pages.drawer.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerItemCB(name:String, state: Boolean = false, onChanged: (Boolean) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable(onClick = { onChanged(!state) })
            .padding(horizontal = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            name, modifier = Modifier.weight(0.8f),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Checkbox(
            checked = state, onCheckedChange = null,
            modifier = Modifier.weight(0.2f),
        )
    }
}