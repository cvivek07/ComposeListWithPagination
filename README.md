# CompostListWithPagination App

Create a simple Android application that fetches data from a public API endpoint and displays it in a paginated list format. No use of external library. 

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
@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: PostsRepository) : ViewModel() {

    var state by mutableStateOf(ScreenState())

    lateinit var postClicked : Post

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            repository.getPosts(nextPage)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    fun fetchPosts() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}
```
