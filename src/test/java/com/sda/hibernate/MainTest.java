package com.sda.hibernate;

import com.sda.hibernate.config.HibernateUtils;
import com.sda.hibernate.entity.Author;
import com.sda.hibernate.entity.Book;
import com.sda.hibernate.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class MainTest {

    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeClass
    public static void beforeTests(){
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Before
    public void setUp(){
        session = sessionFactory.openSession();
        session.beginTransaction();
    }


    @Test
    public void whenInsert_thenCreatesRelationships() {


        Category category = new Category();
        category.setName("Fantastyka");

        Author author = new Author();
        author.setName("Jan");
        author.setLastname("Kowalski");

        Author author1 = new Author();
        author1.setName("Bogusław");
        author1.setLastname("Nowak");

        Book book1 = new Book();
        book1.setName("Wiedzmin");
        book1.setCategory(category);

        Set<Author> authors = new HashSet<>();
        authors.add(author);
        authors.add(author1);

        assertNotNull(authors);
        book1.setAuthors(authors);

        assertNotNull(session.save(book1));

    }

    @Test
    public void whenReadBook_thenReturnAuthorsList() {
        @SuppressWarnings("unchecked")
        List<Book> authorsList = session.createQuery("FROM Book")
                .list();

        assertNotNull(authorsList);

        for(Book book: authorsList) {
            assertNotNull(book.getAuthors());
        }
    }

    @After
    public void tearDown(){
        session.getTransaction().commit();
        session.close();
    }

    @AfterClass
    public static void afterTests(){
        HibernateUtils.closeConnection();
    }
}
