# Bookstore Catalogue

Reconstructed version of a Java coursework project.

## Features
- Abstract `Media` class
- `Book` subclass
- `Catalogue` interface
- Loads inventory from `books.txt`
- Bubble sort by title
- Binary search by title
- Search, purchase, sell, add, remove, and print catalogue options
- Purchasing decreases stock and raises the listed price by $0.25
- Selling a book pays 25% of the listed price and increases stock

## Run

```bash
javac *.java
java BookstoreApp
```

Keep `books.txt` in the same directory as the compiled classes.
