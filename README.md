![](./app/src/main/res/mipmap-xxhdpi/ic_launcher.webp)
# Demo Network App
Demo Network App is a demo application for getting images from the network using the Pixabay API and then caching them. This application is built according to the principles of Clean Architecture.

## Setup
Before building the application, you need to add the Pixabay API key to your `local.properties` file:

```
API_KEY=YOUR_TOKEN_HERE
```

## Libraries
The application uses the following libraries:

 - **Jetpack Compose:** For UI rendering
 - **Hilt:** For dependency injection
 - **Room:** For local data storage
 - **Retrofit:** For remote data retrieval
 - **Gson:** For JSON parsing
 - **Coil:** For image loading
