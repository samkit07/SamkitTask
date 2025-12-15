# Portfolio Tracker App

This is a simple Android application that demonstrates a modern Android architecture for displaying a user's investment portfolio. The app fetches portfolio data from a mock API and displays the holdings along with a summary of the user's profit and loss.

## 📸 Screenshot
<img width = "250" height = "500" alt="Screenshot_20251215_133235" src="https://github.com/user-attachments/assets/25f55ea8-4a49-4880-bfe2-3d0aea1dd05b" />
<img width = "250" height = "500" alt="Screenshot_20251215_133301" src="https://github.com/user-attachments/assets/af5fe82b-fc7e-42eb-8819-5683884e5725" />



## ✨ Features

*   Displays a list of user's holdings.
*   Calculates and displays a summary of the portfolio, including:
    *   Current Value
    *   Total Investment
    *   Today's Profit & Loss
    *   Total Profit & Loss
*   Expandable/collapsible summary view.
*   Handles loading and error states gracefully with a retry mechanism.

## Architecture

The project follows the principles of **Clean Architecture** with a multi-module setup. The architecture is based on the **Model-View-ViewModel (MVVM)** design pattern.

### Modules

*   **:app**: The main application module that brings everything together. It depends on the `feature-portfolio` module.
*   **:core**: A core library module containing shared code, such as network utilities (`Retrofit`, `OkHttp`), common models, and utility functions (`toRupees`).
*   **:feature-portfolio**: A feature module group that encapsulates all the logic related to the portfolio feature.
    *   **:feature-portfolio:domain**: The domain layer. Contains the business logic (use cases) and the domain models. It is a pure Kotlin module with no Android framework dependencies.
    *   **:feature-portfolio:data**: The data layer. Contains the repository implementation and the data sources (network API definitions). It's responsible for fetching data and mapping it to the domain models.
    *   **:feature-portfolio:presentation**: The presentation layer. Contains the UI (Jetpack Compose screens), ViewModels, and UI state management.

## Tech Stack & Libraries

*   **[Kotlin](https://kotlinlang.org/)**: First-class and official programming language for Android development.
*   **[Jetpack Compose](https://developer.android.com/jetpack/compose)**: Android’s modern toolkit for building native UI.
*   **[MVVM (Model-View-ViewModel)](https://developer.android.com/jetpack/guide)**: UI design pattern.
*   **[Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)**: Enforced via a multi-module setup.
*   **[Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)**: For asynchronous programming.
*   **[Hilt](https://developer.android.com/training/dependency-injection/hilt-android)**: For dependency injection.
*   **[Retrofit](https://square.github.io/retrofit/)**: A type-safe HTTP client for Android and Java.
*   **[OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)**: For logging HTTP requests and responses.
*   **[JUnit 5](https://junit.org/junit5/) & [MockK](https://mockk.io/)**: For unit testing.

## How to Build

1.  Clone the repository.
2.  Open the project in Android Studio.
3.  Let Gradle sync the project dependencies.
4.  Run the app on an emulator or a physical device.
