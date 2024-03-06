package com.yschang.rickandmortyapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Text
// material3 版本與 material 版本不同。
// 對應 build.gradle.kts 的 implementation("androidx.compose.material3:material3")

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.yschang.rickandmortyapi.model.Character
import com.yschang.rickandmortyapi.ui.theme.RickandMortyAPITheme
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import com.yschang.rickandmortyapi.viewmodel.CharacterViewModel


import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


class MainActivity : ComponentActivity() {
    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // Jetpack Compose 的函數，用於定義 Activity 的 UI 內容。
            RickandMortyAPITheme {
                // 通過 ViewModel 與 LiveData 的結合使用，可以輕鬆地在不同的 UI 組件之間共享和管理數據，
                // 同時保持良好的數據封裝和分離。這確保了 UI 總是顯示最新的數據狀態。

                // 观察 LiveData 并提供一个初始空列表作为默认值。使用了 LiveData 來觀察 ViewModel 中 characters 的變化。
                // 如果characterViewModel中的數據尚未加載完畢，則使用一個空列表作為初始值。
                // 當數據變化時（比如從API加載新數據），這個UI部分會自動更新來反映新的數據。

                // observeAsState 是一個 Compose 擴展函數，它允許 Compose UI 以響應式的方式與 LiveData 對象互動。
                // 這裡提供了一個初始值（空列表），以便在數據還未加載完成時有一個預設值。當 characters 的數據變化時，UI 會自動更新。

                //characterViewModel.characters.observeAsState(initial = listOf()) 是從ViewModel中訂閱characters的LiveData。
                //observeAsState 是 Jetpack Compose 用來觀察 LiveData 的方法，它會將 LiveData 中的數據轉化為 Compose 可用的狀態。
                //initial = listOf() 表示如果 characters 的 LiveData 尚未發出任何數據，則使用一個空的列表作為初始狀態。
                //.value 用來獲取 observeAsState 當前的值，這裡指的是角色列表的當前狀態。

                val characters = characterViewModel.characters.observeAsState(initial = listOf()).value

                //是Composable函數，用來顯示這個角色列表。將上面獲取的角色列表傳遞給CharacterList，讓它顯示在UI上。
                CharacterList(characters)
            }
        }
    }
}

@Composable
fun CharacterList(characters: List<Character>) { //characters是這個函數的參數名稱, List<Character>是參數characters的型別
    LazyColumn { // 可以垂直滾動的列（Column）

        // 對於List<Character>中的每個character元素，都會執行 { character -> CharacterItem(character) } 中的程式碼，
        // 即呼叫 CharacterItem 函數並傳遞目前的 character 作為參數。
        items(characters) { character -> CharacterItem(character) }
    }
}

//@Composable
//fun CharacterItem(character: Character) {
//    Row(verticalAlignment = Alignment.CenterVertically) { // 使图片和文本垂直居中对齐
//        Image(
//            painter = rememberAsyncImagePainter(model = character.image),
//            contentDescription = character.name,
//            modifier = Modifier.size(100.dp), // 举例设置图片大小为 100dp x 100dp
//            contentScale = ContentScale.Crop
//        )
//        Text(
//            text = "Name: ${character.name}",
//            modifier = Modifier.padding(start = 8.dp) // 在图片和文本之间添加一些间距
//        )
//    }
//}

@Composable
fun CharacterItem(character: Character) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        //elevation 屬性直接控制卡片的陰影，高度越大，陰影越明顯
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box( //Box 用作 Card 內的布局容器
            Modifier
                .background(Color.LightGray) // 应用背景色到 Box，这样整个 Card 都会被背景色覆盖
                .fillMaxWidth() // 确保 Box 填满 Card
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = character.image),
                    contentDescription = character.name,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterVertically),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = buildString {
                        append("Name: ${character.name}\n")
                        append("Species: ${character.species}\n")
                        append("Type: ${character.type.ifBlank { "N/A" }}\n") //使用ifBlank处理空字符串
                        append("Gender: ${character.gender}\n")
                        append("Origin: ${character.origin.name}")
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}