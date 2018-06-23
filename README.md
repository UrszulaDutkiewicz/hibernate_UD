Relacja Many to many

- Author
- Book

Właścicielem jest klasa Book, w klasie właściciela nie umieszczamy `mappedBy` (tutaj zrobiliśmy błąd)
Klasa encyjna author jest związana z klasą book - tutaj musi być `mappedBy` i wskazanie na kolekcje po drugiej stronie,
w taki sposób zadziała bez problemu. Proszę przetestować kod i spojrzeć na testy, zachęcam to przetrenowania 
adnotacji `@JoinColumn` i umieszczenia `mappedBy` po drugiej stronie relacji.

Można też to zrobić z wykorzystaniem adnotacji `@JoinTable`. Adnotacja `@JoinTable` wskazuje 
właściciela relacji - odpowiednia tabela zawiera kolumnę z obcym kluczem do przywoływanej tabeli.


````
@Entity
public class Author {
    @ManyToMany
    @JoinTable(name = "author_book",
               joinColumns = {@JoinColumn(name = "books_id")},
               inverseJoinColumns = {@JoinColumn(name = "authors_id")})
    private Set<Book> books;
    ...
}


@Entity
public class Book {
    @ManyToMany
    @JoinTable(name = "author_book",
               joinColumns = {@JoinColumn(name = "authors_id")},
               inverseJoinColumns = {@JoinColumn(name = "books_id")})
    private Set<Author> authors;
    ...
}```


