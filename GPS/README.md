# Android Location Updates with Cancellation

This Android project demonstrates how to request location updates using the `FusedLocationProviderClient` and how to cancel the location request without modifying the original code logic. It uses a helper class, `LocationHelper`, to manage location updates and cancellation functionality.

## Features

- Request high-accuracy location updates.
- Display the latitude, longitude, and geocoded address on the UI.
- Cancel location updates dynamically using a button.
- Encapsulate the location logic within a helper class to keep the original `MainActivity` code clean and maintainable.

## Requirements

- Android SDK 23 (Marshmallow) or higher
- Google Play Services (for Fused Location Provider API)
- Kotlin language

## Permissions

Ensure the following permissions are declared in the `AndroidManifest.xml` for accessing the device location:

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>

## Now you can use the whole project and description about the locations in Android 
