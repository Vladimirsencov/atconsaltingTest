package ru.atconsalting.librare.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.atconsalting.librare.model.Book;
import ru.atconsalting.librare.model.User;

import static org.junit.Assert.assertEquals;

/**
 * Created by Vladimir_Sentso on 29.07.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BookServiceImplTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    BookService bookService;

    @Test
    public void testGetAllBooks() throws Exception {
        String st = "[Book{id=1, ISBN='978-5-8189-1095-6', title='Последний венгерский король', authorName='Леопольд Захер-Мазок', readerName='null'}, Book{id=2, ISBN='978-5-7035-2113-7', title='Электропривод летательных аппаратов', authorName='Полковников В.А., Петров Б.И., Рывкин С.Е.', readerName='null'}, Book{id=3, ISBN='5-217-010112-6', title='Автоматизированный электропривод станков и промышленных роботов', authorName='О.П Михайлов', readerName='null'}, Book{id=4, ISBN='978-5-9556-0082-6', title='Введение в цифровую схемотехнику', authorName='Ю. В. Новиков', readerName='null'}, Book{id=5, ISBN='978-5-9775-0162-0', title='Цифровая схемотехника', authorName='Угрюмов Е.П.', readerName='null'}, Book{id=6, ISBN='5-283-04468-8', title='Примеры расчета автоматизированног электропривода на ЭВМ', authorName='А.В. Башарин, Ю.В. Постников', readerName='null'}, Book{id=7, ISBN='978-58118-5115-5', title='США: Истроия география и традиции', authorName='Галина Бардина', readerName='null'}]";
        assertEquals(st, bookService.getAllBooks().toString());
    }

    @Test
    public void testGetBooksWithLimit() throws Exception {
        String st = "[Book{id=5, ISBN='978-5-9775-0162-0', title='Цифровая схемотехника', authorName='Угрюмов Е.П.', readerName='null'}]";
        assertEquals(st, bookService.getBooksWithLimit(1, 4).toString());
    }

    @Test
    public void testSaveBook() throws Exception {
        String st = "[Book{id=1, ISBN='978-5-8189-1095-6', title='Последний венгерский король', authorName='Леопольд Захер-Мазок', readerName='null'}, Book{id=2, ISBN='978-5-7035-2113-7', title='Электропривод летательных аппаратов', authorName='Полковников В.А., Петров Б.И., Рывкин С.Е.', readerName='null'}, Book{id=3, ISBN='5-217-010112-6', title='Автоматизированный электропривод станков и промышленных роботов', authorName='О.П Михайлов', readerName='null'}, Book{id=4, ISBN='978-5-9556-0082-6', title='Введение в цифровую схемотехнику', authorName='Ю. В. Новиков', readerName='null'}, Book{id=5, ISBN='978-5-9775-0162-0', title='Цифровая схемотехника', authorName='Угрюмов Е.П.', readerName='null'}, Book{id=6, ISBN='5-283-04468-8', title='Примеры расчета автоматизированног электропривода на ЭВМ', authorName='А.В. Башарин, Ю.В. Постников', readerName='null'}, Book{id=7, ISBN='978-58118-5115-5', title='США: Истроия география и традиции', authorName='Галина Бардина', readerName='null'}, Book{id=8, ISBN='555-5588-889', title='Похождения шарика', authorName='Василий Петров', readerName='IVAN_PETROV'}]";
        Book book = new Book();
        book.setReaderName("IVAN_PETROV");
        book.setAuthorName("Василий Петров");
        book.setISBN("555-5588-889");
        book.setTitle("Похождения шарика");
        bookService.saveBook(book);
        assertEquals(st, bookService.getAllBooks().toString());
    }

    @Test
    public void testUpdateBook() throws Exception {
        User user = new User();
        user.setId(5L);
        String st = "[Book{id=1, ISBN='978-5-8189-1095-6', title='Последний венгерский король', authorName='Леопольд Захер-Мазок', readerName='null'}, Book{id=2, ISBN='978-5-7035-2113-7', title='Электропривод летательных аппаратов', authorName='Полковников В.А., Петров Б.И., Рывкин С.Е.', readerName='null'}, Book{id=3, ISBN='5-217-010112-6', title='Автоматизированный электропривод станков и промышленных роботов', authorName='О.П Михайлов', readerName='null'}, Book{id=4, ISBN='978-5-9556-0082-6', title='Введение в цифровую схемотехнику', authorName='Ю. В. Новиков', readerName='null'}, Book{id=5, ISBN='555-5588-889', title='Похождения шарика', authorName='Василий Петров', readerName='IVAN_PETROV'}, Book{id=6, ISBN='5-283-04468-8', title='Примеры расчета автоматизированног электропривода на ЭВМ', authorName='А.В. Башарин, Ю.В. Постников', readerName='null'}, Book{id=7, ISBN='978-58118-5115-5', title='США: Истроия география и традиции', authorName='Галина Бардина', readerName='null'}]";
        Book book = new Book();
        book.setId(5L);
        book.setReaderName("IVAN_PETROV");
        book.setAuthorName("Василий Петров");
        book.setISBN("555-5588-889");
        book.setTitle("Похождения шарика");
        assertEquals(true, bookService.updateBook(book) != null);
        assertEquals(st, bookService.getAllBooks().toString());
    }

    @Test
    public void testDeleteBook() throws Exception {
        bookService.deleteBook(1L);
        String st = "[Book{id=2, ISBN='978-5-7035-2113-7', title='Электропривод летательных аппаратов', authorName='Полковников В.А., Петров Б.И., Рывкин С.Е.', readerName='null'}, Book{id=3, ISBN='5-217-010112-6', title='Автоматизированный электропривод станков и промышленных роботов', authorName='О.П Михайлов', readerName='null'}, Book{id=4, ISBN='978-5-9556-0082-6', title='Введение в цифровую схемотехнику', authorName='Ю. В. Новиков', readerName='null'}, Book{id=5, ISBN='978-5-9775-0162-0', title='Цифровая схемотехника', authorName='Угрюмов Е.П.', readerName='null'}, Book{id=6, ISBN='5-283-04468-8', title='Примеры расчета автоматизированног электропривода на ЭВМ', authorName='А.В. Башарин, Ю.В. Постников', readerName='null'}, Book{id=7, ISBN='978-58118-5115-5', title='США: Истроия география и традиции', authorName='Галина Бардина', readerName='null'}]";
        assertEquals(st, bookService.getAllBooks().toString());
    }

}