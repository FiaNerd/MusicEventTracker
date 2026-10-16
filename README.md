**Student name:** Sofia Travnicek Mattiasson
**Class:** DevOpsM26

---

# Music Event Tracker
A console application for music lovers to keep track of music events, concerts, and festivals around the world.

## Project Idea
This program will keep track of different music events worldwide. It stores upcoming and past events, locations, 
descriptions, bands, and user reviews/ratings. The user can register, update, search, rate, and delete events.

---

## Superclass

- **Name:** `EventModel` (abstract class)
- **Common fields:** `id`, `artist`, `genre`, `venue`, `city`, `country`, `date`, `description`, `isOutdoor`, and `reviews`.
- **Common methods:** `getDescription()`, `toString()`, and methods for managing reviews.

## Subclasses

1. **`Concert`** — represents a concert and overrides `getDescription()` and `toString()` to include concert-specific information, such as support acts and tour name.
2. **`Festival`** — represents a festival and overrides `getDescription()` and `toString()` to include camping availability and number of days.
3. **`ClubGig`** — represents a smaller music event and overrides `getDescription()` and `toString()` to include details such as age limit, entry fee, and opening hours.

## Interfaces

- **`Reviewable`** — defines how reviewable objects add and retrieve reviews, and provides a default method for calculating the average rating.
- **`Summarizable`** — defines the `getSummary()` method for objects that can provide a summary.
- **Implementation:** `EventModel` implements `Reviewable`, which its subclasses inherit. `Review` implements `Summarizable`.

---

## Menu
1. Register new event
2. Show all events
3. Search event / band
4. Rate and review event
5. Delete event
6. Exit

---

## Error Scenarios
- Should not be possible to create or update an event with empty mandatory fields.
- Should not be possible to delete or update an event ID that does not exist in the collection.
- Should not crash the program if the user enters letters or invalid numbers in the menu input parser.

---

## Design Decisions and Justification

### Class Hierarchy and Inheritance
I chose to use an abstract `EventModel` class because concerts, festivals, and club gigs share several properties, such as 
artist, genre, venue, date, and description. Instead of repeating these fields and methods in every class, I placed the shared 
functionality in the superclass.

I considered that each event type could be implemented as a separate class without inheritance. However, this would lead to 
duplicated code and make it harder to manage events consistently. By using inheritance, I can store different event types in 
the same collection while still allowing each subclass to provide its own implementation of methods such as `getDescription()`
and `toString()`.

The abstract superclass also provides a common structure without allowing a generic event to be created directly.

### Separation of Responsibilities
I chose to separate the application into models, services, and views. The model classes represent events and reviews, the 
service classes handle operations and business rules, and the menu classes handle user interaction.

One alternative would have been to put most of the functionality directly into the menu classes. This might have been simpler 
at the beginning, but it would mix user interaction with business logic and make the code harder to maintain as the project 
grows.

By separating these responsibilities, the services can be reused without depending on console input or output. For example, 
`ReviewService` handles searching for events with reviews, adding reviews, and counting reviews, while `ReviewMenu` asks the 
user for input and displays the results.

### Interfaces and Polymorphism
I used the `Reviewable` interface to define common review-related behaviour and the `Summarizable` interface to define how 
a review can provide a summary.

Interfaces allow code to work with an object's behaviour instead of depending entirely on its concrete class. For example, 
`ReviewService` can count reviews through `Reviewable` references, and `ReviewMenu` can display a review through a `Summarizable`
reference.

I chose this approach to make the code more flexible and easier to extend. If new classes need to support the same behaviour
in the future, they can implement the relevant interface without requiring the existing code to be completely rewritten.

### Validation and Error Handling
I chose to validate user input and domain operations instead of allowing invalid data to enter the application. For example, 
the application checks required event fields, validates review ratings, and handles attempts to update or delete events that 
do not exist.

An alternative would have been to rely only on the menu to prevent invalid operations. However, validation in the service layer 
provides an additional level of protection because the rules are checked when the operation is performed, not only when the user
enters information.

The application also handles invalid menu input so that the user can correct mistakes without the program crashing.

### Future Improvements
The current application stores its events in memory, which means the data is not preserved when the program closes. A possible 
future improvement would be to introduce persistent storage, such as a database or file storage.

The class hierarchy also makes it possible to add new event types in the future. A new subclass could reuse the common 
functionality in `EventModel` and override methods to provide its own event-specific information.

### Challenges and Lessons Learned
**Separating responsibilities**

One challenge was deciding where different types of logic should belong. I learned that menu classes should focus on user 
interaction, while services should handle operations and business logic. Separating these responsibilities made the code easier 
to understand and maintain.

**Understanding polymorphism and interfaces**

Another challenge was understanding how inheritance, method overriding, and interfaces work together. By using an abstract 
`EventModel` class and different event subclasses, I could handle several event types through a common reference while still 
displaying their specific information. I also used interfaces to make certain operations independent of a concrete class.

**Validation and error handling**

Handling invalid input was another important part of the project. I needed to consider situations such as empty artist names, 
invalid ratings, and attempts to update or delete events that do not exist. This helped me understand why validation should be
part of the application design rather than relying only on the user to enter correct information.

**What I learned**

During the project, I learned that a working solution is not the only goal. The structure of the code also matters. I became
more aware of how class responsibilities, interfaces, validation, and error handling affect maintainability and future
development. And Debuging take longer time that i thought, even if i think i checked everything, i still find a little bug or 
somthing that i did not really was happy about.

One important lesson I took from this project is that software should not rely on users always doing what the developer expects. 
A robust application should anticipate mistakes and handle them gracefully.