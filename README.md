# List Application

Create a simple Android application that fetches data from a public API endpoint and displays it in a paginated list format. However, the API response contains a large dataset, and each item in the list requires heavy computation to display additional details.

## Demo Video

https://github.com/cvivek07/ListApp/assets/16047933/bf1ed729-2ef2-441a-90a2-9ee4e821b0d3

## Libraries and Architecture Used

1. MVVM Architecture.
2. Dependency Injection using Hilt.
3. Jetpack compose based user interface.
4. Latest navigation controller for navigation between screens.
5. Supports latest android 14.
6. Dependency management using version catalog.
7. Custom Pagination Implementation.
8. Utilized coroutines whereever applicable.

## Usage

```kotlin
@Composable
fun LazyList(list: List<Photo>, errorResId: Int, placeholderResId: Int) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(items = list,
            key = {
                it.id
            }
        ) { photo ->
            val imageUrl = photo.getImageUrl()
            val context = LocalContext.current
            var bitmap by remember { mutableStateOf<Bitmap?>(null) }
            LaunchedEffect(imageUrl) {
                bitmap = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .error(errorResId)
                    .placeholder(placeholderResId)
                    .build()
            }
            if (bitmap != null) {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(photo.getAspectRatio().toFloat())
                )
            } else {
                Image(
                    painter = painterResource(id = placeholderResId),
                    contentDescription = null,
                    modifier = Modifier
                        .background(Color.Gray)
                        .aspectRatio(
                            photo
                                .getAspectRatio()
                                .toFloat()
                        )
                )
            }
        }
    }
}
```

