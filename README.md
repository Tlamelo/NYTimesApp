# NYTimesApp
# NY Times - A NewYork Times most popular news App (Kotlin)

A basic master/detail android application which shows most popular viewed news from NYTimes using its public api.

 

#### App Features
* App fetches NYTimes popular news and displays in RecyclerView.
* App built on master/detail flow pattern, so if launched on tab screens will be visible side by side.
* App shows shimmer effect while data is being fetched from server.
* Error cases handled and an error screen with retry button is shown in case of error.
* App includes UI and unit testing using Junit4 and Espresso.

#### App Architecture 
Based on MVVM architecture and repository pattern, where database serves as a single source of truth for data.
Refer media/mvvm_data_flow.png for diagram explanation.

#### App Specs
- Minimum SDK 15
- [Java8](https://java.com/en/download/faq/java8.xml)
- [Kotlin](https://kotlinlang.org/)
- MVVM Architecture
- Android Architecture Components (LiveData, Lifecycle, ViewModel, Room Persistence Library, ConstraintLayout)
- [Lifecycle-aware components](https://developer.android.com/topic/libraries/architecture/lifecycle)
- [Dagger 2](https://google.github.io/dagger/) for dependency injection.
- [Retrofit 2](https://square.github.io/retrofit/) for API integration.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) for data observation.
- [ViewModels](https://developer.android.com/topic/libraries/architecture/viewmodel) for data container.
- [Room](https://developer.android.com/topic/libraries/architecture/room) for data persistence.
- [Gson](https://github.com/google/gson) for serialisation.
- [Okhhtp3](https://github.com/square/okhttp) for implementing interceptor, logging and mocking web server.
- [Mockito](https://site.mockito.org/) for implementing unit test cases.
- [Glide](https://github.com/bumptech/glide) for image loading.
