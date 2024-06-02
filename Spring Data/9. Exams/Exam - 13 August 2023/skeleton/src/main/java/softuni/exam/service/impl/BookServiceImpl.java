package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.BookDto;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class BookServiceImpl  implements BookService {
    private static final String FILE_PATH = "src/main/resources/files/json/books.json";
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, Gson gson, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return bookRepository.count()>0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importBooks() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readBooksFromFile(), BookDto[].class))
                .filter(bookDto -> {
                    boolean isValid = validationUtil.isValid(bookDto)
                            && !isTitleExist(bookDto.getTitle());
                    sb.append(isValid ? String.format("Successfully imported book %s - %s",bookDto.getAuthor(),bookDto.getTitle())
                            :"Invalid book");
                    sb.append(System.lineSeparator());
                    return isValid;
                })
                .map(bookDto -> modelMapper.map(bookDto, Book.class))
                .forEach(bookRepository::save);

        return sb.toString();
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }






    private boolean isTitleExist(String title) {
        return bookRepository.existsBookByTitle(title);
    }
}
