# Splash [![Build Status](https://travis-ci.com/PHELAT/Splash.svg?branch=master)](https://travis-ci.com/PHELAT/Splash) [![codecov](https://codecov.io/gh/PHELAT/Splash/branch/master/graph/badge.svg)](https://codecov.io/gh/PHELAT/Splash)
<img src="https://github.com/PHELAT/Splash/blob/master/screenshot/screen1.gif" width="320" align="right" hspace="20">

Splash is an unofficial Unsplash Android client with the focus on software architecture.
## Getting started
1. Clone the repository and import it to Android Studio
2. Open `splash.properties` file
3. Replace your Unsplash API key with `your_client_id_here` (You can get your API key from [here](https://unsplash.com/developers))
4. Run the app!
## Architecture
Splash is using an architectural pattern called MVP-VM to architect the presentation layer of the application and at it's core it's using the Clean Architecture concepts by Uncle Bob.
## What you can learn from this?
- You can learn how to apply Clean Architecture in an Android project.
- You can learn how to apply MVP-VM pattern in an Android project by using Architecture Component's ViewModel.
- You can learn how to use Dagger in Android in general and how to use it in a modular project.
- You can learn how to use Retrofit in an Android project.
- You can learn how to test your classes with the help of Mockito.
- You can learn how to use LiveData and ViewModel from the Architecture Components.

## When to use MVP-VM?
In my opinion, you can use MVP-VM pattern in cases where you are using several ViewModels in your View class, and you need a communicator between those ViewModels, and you don't want to have this type of logic in your View class, or perhaps you need to test this logic.
