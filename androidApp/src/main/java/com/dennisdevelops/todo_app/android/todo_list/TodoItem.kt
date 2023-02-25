package com.dennisdevelops.todo_app.android.todo_list

import androidx.annotation.FloatRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dennisdevelops.todo_app.android.R
import com.dennisdevelops.todo_app.domain.Todo.Todo
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter


fun overlappingRowMeasurePolicy(overlapFactor: Float) = MeasurePolicy { measurables, constraints ->
    val placeables = measurables.map { measurable -> measurable.measure(constraints)}
    val height = placeables.maxOf { it.height }
    val width = (placeables.subList(1,placeables.size).sumOf { it.width  }* overlapFactor + placeables[0].width).toInt()
    layout(width,height) {
        var xPos = 0
        for (placeable in placeables) {
            placeable.placeRelative(xPos, 0, 0f)
            xPos += (placeable.width * overlapFactor).toInt()
        }
    }
}

@Composable
fun OverlappingRow(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.1,to = 1.0) overlapFactor:Float = 0.5f,
    content: @Composable () -> Unit,
){
    val measurePolicy = overlappingRowMeasurePolicy(overlapFactor)
    Layout(measurePolicy = measurePolicy,
        content = content,
        modifier = modifier )
}

@Composable
@Preview
fun TodoItemPreview() {
    TodoItem(
        todo = prevewTodo(),
        onCheckedChange = {},
        onTodoClick = {},
        onDeleteClick = {}
    )
}

fun prevewTodo(): Todo {
    return Todo(
        id = 1,
        title = "Todo 1",
        description = "Description 1",
        date = kotlinx.datetime.LocalDateTime(2021, 1, 1, 0, 0),
        isCompleted = false
    )
}
@Composable
fun TodoItem(
    todo: Todo,
    onCheckedChange: (Boolean) -> Unit,
    onTodoClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier

) {


    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(color = MaterialTheme.colors.surface)
            .clickable { onTodoClick() }
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
           Column() {
                Text(
                     text = todo.title,
                     style = MaterialTheme.typography.h6
                )
                Text(
                     text = todo.description,
                     style = MaterialTheme.typography.body2
                )
           }
           Checkbox(checked = todo.isCompleted, onCheckedChange = onCheckedChange)




        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(text = todo.date.toJavaLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE), style = MaterialTheme.typography.caption)

            OverlappingRow(
               overlapFactor = 0.6f,

            ) {
                Image(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(shape = CircleShape),
                    painter = painterResource(id = R.drawable.josh),
                    contentDescription = "Your Image"
                )
                Image(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(shape = CircleShape),
                    painter = painterResource(id = R.drawable.josh),
                    contentDescription = "Your Image"
                )
                Image(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(shape = CircleShape),
                    painter = painterResource(id = R.drawable.josh),
                    contentDescription = "Your Image"
                )
            }




        }



    }


}