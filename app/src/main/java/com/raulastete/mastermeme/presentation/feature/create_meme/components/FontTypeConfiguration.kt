package com.raulastete.mastermeme.presentation.feature.create_meme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raulastete.mastermeme.presentation.model.MemeFontTypeUi

@Composable
fun FontTypeConfiguration(
    modifier: Modifier = Modifier,
    selectedFontType: MemeFontTypeUi?,
    fontTypeList: List<MemeFontTypeUi>,
    onTypeSelected: (MemeFontTypeUi) -> Unit
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(horizontal = 16.dp)
            .padding(bottom = 2.dp, top = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(fontTypeList) { memeFontType ->

                val textColor = if (selectedFontType == memeFontType)
                    Color.White else Color.Unspecified

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (selectedFontType == memeFontType)
                                MaterialTheme.colorScheme.surfaceContainerHigh
                            else
                                Color.Transparent
                        )
                        .clickable { onTypeSelected(memeFontType) }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Good",
                        style = memeFontType.textStyle.copy(
                            color = textColor,
                            fontSize = 28.sp
                        ),
                    )
                    Text(
                        text = memeFontType.displayName,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 10.sp,
                            color = textColor
                        )
                    )
                }
            }
        }
    }
}