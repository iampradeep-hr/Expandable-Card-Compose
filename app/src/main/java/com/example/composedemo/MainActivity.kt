package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.ui.theme.Shapes


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            SelectableString()
//            SuperScriptText()
            
//            val data=PersonRepo().getAllData()
//            LazyColumn(contentPadding = PaddingValues(all=12.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ){
//                items(data){ person->
//                    CustomItem(person = person)
//                }
//            }

            ExpandableDropDown()


            
            
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableDropDown() {

    var expandedState by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if(expandedState) 180f else 0f )
    
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .animateContentSize(
            animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
        ),
        shape = Shapes.large,
        onClick ={
            expandedState=!expandedState
        },
        backgroundColor = Color.Cyan
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
        ){
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(modifier = Modifier.weight(6f),
                    text = "My Title",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )
                
                IconButton(modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .rotate(rotation)
                    .weight(1f),
                    onClick = {expandedState=!expandedState}
                ) {
                    Icon(imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if(expandedState){
                Text(
                    text ="Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                            " when an unknown printer took a galley of type and scrambled it to make a type " +
                            "specimen book. It has survived not only five centuries, but also the leap into " +
                            "electronic typesetting, remaining essentially unchanged. " +
                            "It was popularised in the 1960s with the release of Letraset sheets " +
                            "containing Lorem Ipsum passages, and more recently with desktop publishing " +
                            "software like Aldus PageMaker including versions of Lorem Ipsum.",
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )

            }
        }

    }
}



@Composable
fun SuperScriptText() {
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            baselineShift = BaselineShift.Subscript
        )){
            append("Hello")
        }
        append("World")

    }

    )

}

@Composable
fun SelectableString() {
    SelectionContainer {
        Column {
            Text(text = "Hello world")
            Text(text = "Hello world")
            DisableSelection {

                Text(text = "Hello world")

            }
            Text(text = "Hello world")
        }
    }
}

@Composable
fun AnnotatedString() {

    val fontFamily= FontFamily(
        Font(R.font.lexend_thin, FontWeight.Thin),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_medium, FontWeight.Medium),
        Font(R.font.lexend_semibold, FontWeight.SemiBold),
        Font(R.font.lexend_bold, FontWeight.Bold)
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp,
                    )
                ){
                    append("J")
                }
                append("etpack ")

                withStyle(
                    SpanStyle(color = Color.Green, fontSize = 50.sp,)){
                    append("C")
                }

                append("ompose")
            },
            color = Color.White,
            fontSize = 30.sp,
            fontFamily =fontFamily,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline
        )

    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExpandableDropDown()
}
