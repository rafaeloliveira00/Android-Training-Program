package pt.atp.bobi.presentation.compose


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.atp.bobi.R
import pt.atp.bobi.domain.Settings
import pt.atp.bobi.domain.model.Setting


@Composable
fun AboutAppItem(setting: Setting) {

    Row {

//        Box(aligment = Alignment.Center) {
//
//            Box(
//                modifier = Modifier
//                    .preferredSize(32.dp)
//                    .clip(CircleShape)
//                    .background(colorResource(id = R.color.colorPrimaryMain))
//            )
//
//            Image(
//                asset = vectorResource(id = setting.image),
//                modifier = Modifier.width(21.dp).height(21.dp)
//            )
//        }
        Column(modifier = Modifier.fillMaxWidth().padding(start = 16.dp)) {
            Text(
                text = stringResource(id = setting.title),
                style = TextStyle(
                    fontSize = 19.sp,
                    color = colorResource(id = R.color.white)
                )
            )

            Text(
                text = stringResource(id = setting.subtitle),
                style = TextStyle(
                    fontSize = 19.sp,
                    color = colorResource(id = R.color.grey)
                )
            )
        }
    }
}