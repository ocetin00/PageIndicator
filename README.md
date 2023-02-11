<h1 align="center">Page-Indicator </h1></br>
<p align="center">
  </a><br>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
</p> <br>

<p align="center">
<img src="https://github.com/ocetin00/temp/blob/main/ezgif.com-video-to-gif.gif" width="250"/>
</p>

## How to Use

### Gradle
Add the dependency below to your **module**'s `build.gradle` file:

```gradle
dependencies {
     implementation "com.github.ocetin00:PageIndicator:1.0"
}
```

</details>


### Create Page-Indicator


```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PageIndicatorPreview(
    pageIndicatorState: PageIndicatorState = rememberPageIndicatorState(
        listOf(
            "First",
            "Second",
            "Third",
            "Fourth"
        )
    )

) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
      
        PageIndicator(
            pageIndicatorState = pageIndicatorState,
            indicatorColor = MaterialTheme.colorScheme.primary,
            onIndicatorColor = MaterialTheme.colorScheme.onPrimary,
        )
        Row(){
            Button(onClick = { pageIndicatorState.onPrevious() }) {
                Text("Previous")
            }
            Button(onClick = { pageIndicatorState.onNext() }) {
                Text("Next")
            }

        }

        Text("current page: ${pageIndicatorState.current+1}")
    }

}
```
<img src="https://github.com/ocetin00/temp/blob/main/WhatsApp%20Image%202023-02-11%20at%2016.49.47.jpeg" width="200"/>



## Find this library useful? :heart:
__[follow me](https://github.com/ocetin00)__ on GitHub for my next creations! 

# License
```xml
Copyright 2023 ocetin00 (Oğuzhan Çetin)

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
