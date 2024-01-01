
# News

An android application developed using Jetpack Compose to fetch the latest news for every 15 mins from the remote api with the help of work manager.


## Dependencies

Required dependencies

```place these plugins and dependencies in build.gradle.kts(:app)```

```bash
plugins {
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}
```

```bash
  dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("androidx.hilt:hilt-work:1.1.0")
    kapt("androidx.hilt:hilt-compiler:1.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
}
```

```place this plugin in build.gradle.kts(:project)```
```bash
plugins {
    id("com.google.dagger.hilt.android") version "2.48" apply false
}
```
    
    
