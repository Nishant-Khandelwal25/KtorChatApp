package com.example.ktorchatapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ktorchatapp.domain.model.Message

@Composable
fun ChatItem(isOwnMessage: Boolean , message: Message) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (isOwnMessage) {
            Alignment.CenterEnd
        } else Alignment.CenterStart,
    ) {
        Column(
            modifier = Modifier
                .width(200.dp)
                .drawBehind {
                    val cornerRadius = 10.dp.toPx()
                    val triangleHeight = 20.dp.toPx()
                    val triangleWidth = 25.dp.toPx()
                    val trianglePath = Path().apply {
                        if (isOwnMessage) {
                            moveTo(size.width, size.height - cornerRadius)
                            lineTo(size.width, size.height + triangleHeight)
                            lineTo(
                                size.width - triangleWidth,
                                size.height - cornerRadius
                            )
                            close()
                        } else {
                            moveTo(0f, size.height - cornerRadius)
                            lineTo(0f, size.height + triangleHeight)
                            lineTo(
                                triangleWidth,
                                size.height - cornerRadius
                            )
                            close()
                        }
                    }
                    drawPath(
                        trianglePath,
                        color = if (isOwnMessage) Color.Green else Color.DarkGray
                    )
                }
                .background(
                    color = if (isOwnMessage) Color.Green else Color.DarkGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(8.dp)
        ) {
            Text(
                text = message.username,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = message.text,
                color = Color.White
            )
            Text(
                text = message.formattedTime,
                color = Color.White,
                modifier = Modifier.align(Alignment.End)
            )

        }
    }
}