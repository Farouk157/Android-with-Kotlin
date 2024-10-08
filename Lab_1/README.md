# Android Fragment Communication Project

## Overview

This project demonstrates fragment communication and orientation handling in an Android app using Kotlin. The app contains two main activities: `MainActivity` and `MainActivity2`. `MainActivity` hosts `FragmentA` by default and shows `FragmentB` conditionally based on device orientation. The project also implements communication between fragments using an interface to handle data passing between fragments and activities.

In landscape mode, both fragments are shown in the same activity (`MainActivity`). In portrait mode, `FragmentB` is opened in `MainActivity2` when a club is selected in `FragmentA`.

## Project Structure

### 1. MainActivity
   - **Purpose**: This activity is the entry point of the app and hosts `FragmentA` by default.
   - **Orientation Handling**: 
     - In **landscape** mode, both `FragmentA` and `FragmentB` can be displayed within the same activity.
     - In **portrait** mode, selecting an item in `FragmentA` launches `MainActivity2` to display `FragmentB`.
   - **Fragment Communication**: Implements the `Communicator` interface to allow `FragmentA` to pass data to `FragmentB` or `MainActivity2`.

   **Key Methods**:
   - `passDataToActivity2(clubDetails: String)`: Handles data passed from `FragmentA`. If the device is in landscape mode, it replaces the existing fragment with `FragmentB` and passes the data to it. If the device is in portrait mode, it starts `MainActivity2` and sends the data through an intent.

### 2. MainActivity2
   - **Purpose**: This activity is used only in portrait mode to display `FragmentB` when a club is selected in `FragmentA`.
   - **Intent Handling**: Receives data (`clubDetails`) passed from `MainActivity` via an intent and uses this data to populate `FragmentB`.

### 3. FragmentA
   - **Purpose**: Displays a list of clubs using a `RecyclerView`.
   - **Interaction**: When a club is selected, `FragmentA` communicates with `MainActivity` using the `Communicator` interface to pass the selected club's details to `FragmentB` or `MainActivity2`.
   
   **Key Components**:
   - **RecyclerView**: Displays a list of clubs using the `ClubAdapter`.
   - **ClubAdapter**: A custom adapter that handles club item selection and triggers the data-passing process.

### 4. FragmentB
   - **Purpose**: Displays the details of the selected club.
   - **Data Handling**: Receives data either from the bundle (in landscape mode) or from the intent (in portrait mode via `MainActivity2`) and updates its UI with the club details.

## Communication Between Fragments

The app uses an interface called `Communicator` to facilitate communication between `FragmentA` and `FragmentB`. This ensures that the data is passed from the fragment to the activity, and the activity decides where and how to display `FragmentB`.

```kotlin
interface Communicator {
    fun passDataToActivity2(clubDetails: String)
}
