package com.app.randomuser.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.app.randomuser.data.remote.dto.UserDto
import com.app.randomuser.presentation.theme.RandomUserTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<MainViewModel>()

            RandomUserTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    ShowProgressBar(progress = viewModel.progress)
                    ShowErrorMessage(errorMessage = viewModel.errorMessage)
                    UserList(user = viewModel.userList)
                }
            }
        }
    }

    @Composable
    fun UserList(user: List<UserDto>) {
        LazyColumn {
            itemsIndexed(items = user) { index, item ->
                UserItem(user = item)
            }
        }
    }

    @Composable
    fun ShowProgressBar(progress: Boolean) {
        if (progress) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    }

    @Composable
    fun ShowErrorMessage(errorMessage: String?) {
        if (!errorMessage.isNullOrEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red

                )
            }
        }
    }

    @Composable
    fun UserItem(user: UserDto) {
        Card(
            modifier = Modifier
                .padding(8.dp, 8.dp)
                .fillMaxWidth()
                .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
        ) {
            Surface() {

                Row(
                    Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                ) {

                    Image(
                        painter = rememberImagePainter(
                            data = user.pictureDto?.large,

                            builder = {
                                scale(Scale.FILL)
                                transformations(CircleCropTransformation())

                            }
                        ),
                        contentDescription = "User's Picture",
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.2f)
                    )


                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxHeight()
                            .weight(0.8f)
                    ) {
                        Text(
                            text = user.name?.getFullName() ?: "",
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = user.email,
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = user.phone,
                            style = MaterialTheme.typography.body1,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                    }
                }
            }
        }

    }
}