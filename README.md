This is an example using 
Reactive REST API With 
- Spring, 
- Kotlin, 
- Coroutines
- Postgres r2dbc
- Test Containers


```
.
├── HELP.md
├── README.md
├── build.gradle.kts
├── docker.bat.txt
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── src
    ├── main
    │   ├── kotlin
    │   │   └── eespinor
    │   │       └── user
    │   │           ├── UserApplication.kt
    │   │           ├── application
    │   │           │   ├── Request.kt
    │   │           │   ├── Response.kt
    │   │           │   ├── TypeResponse.kt
    │   │           │   └── controller
    │   │           │       ├── CompanyController.kt
    │   │           │       ├── SearchController.kt
    │   │           │       └── UserController.kt
    │   │           └── domain
    │   │               ├── model
    │   │               │   └── Model.kt
    │   │               ├── repository
    │   │               │   ├── CompanyRepository.kt
    │   │               │   └── UserRepository.kt
    │   │               └── usecase
    │   │                   ├── CompanyService.kt
    │   │                   └── UserService.kt
    │   └── resources
    │       ├── application.properties
    │       ├── data.sql
    │       └── schema.sql
    └── test
        ├── kotlin
        │   └── eespinor
        │       └── user
        │           └── UserApplicationTests.kt
        └── resources
            └── application.properties
```

