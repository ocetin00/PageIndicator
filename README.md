<h1 align="center">Page-Indicator </h1></br>
<p align="center">
  </a><br>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
</p> <br>

<p align="center">
<img src="https://github.com/ocetin00/temp/blob/main/WhatsApp-Video-2023-01-22-at-21.08.08.gif" width="250"/>
</p>

## How to Use

### Gradle
Add the dependency below to your **module**'s `build.gradle` file:

```gradle
dependencies {
    implementation "com.github.ocetin00:ToggleTab:1.0.0"
}
```

</details>


### Create Page-Indicator
<img src="https://github.com/ocetin00/temp/blob/main/WhatsApp%20Image%202023-01-22%20at%2021.23.12.jpeg" width="280"/>

```kotlin
    @Preview
    @Composable
    fun ToggleTabPreview1() {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            ToggleTab(modifier = Modifier.fillMaxWidth(0.6f),titleList = listOf("Male", "Famele"), onTabSelected = {})
        }
    }
```



### Create ToggleTab
You can create toggle-tab with selected color,container color and text style



<details>
 <summary>Keep reading for more details</summary>
  
```kotlin
   @Preview
@Composable
fun ToggleTabPreview2() {
    var selectedPageIndex by remember {
        mutableStateOf(1)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .background(Color.White), horizontalArrangement = Arrangement.Center
        ) {
            ToggleTab(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.9f)
                    .clip(ShapeDefaults.ExtraLarge),
                onTabSelected = { index ->
                    selectedPageIndex = index
                },
                titleList = listOf("First", "Second", "Third"),
                tabItemTextStyle = TextStyle.Default.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        var backgroundColor: Color = when (selectedPageIndex) {
            0 -> Color.Green
            1 -> Color.Magenta
            2 -> Color.Cyan
            else -> {
                Color.Green
            }
        }
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
                .background(backgroundColor)
        ) {

        }
    }
}
```
  <img src="https://github.com/ocetin00/temp/blob/main/WhatsApp-Video-2023-01-22-at-21.08.08.gif" width="150"/>
  
</details>



## Find this library useful? :heart:
__[follow me](https://github.com/ocetin00)__ on GitHub for my next creations! 

# License
```xml
Copyright 2019 ocetin00 (Oğuzhan Çetin)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
