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
- **Name:** `Event`
- **Common fields:** `id`, `band`, `ganger`, `place`, `date`, `description`, `rating`, `isFinished`
- **Common methods:** `getInfo()`, `markAsFinished()`

---

## Subclasses
1. **`Concert`**  — focuses on a single headliner/ band; overrides `getInfo()` to include support act details.
2. **`Festival`**  — multi-day event; overrides `getInfo()` to include camping availability and number of days.
3. **`ClubGig`** — smaller venue event; overrides `getInfo()` to include age limits.

---

## Interface
- **Name:** `Rateable`
- **Method:** `addReview`
- **Implemented by:** `Concert`, `Festival`

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

## The justification