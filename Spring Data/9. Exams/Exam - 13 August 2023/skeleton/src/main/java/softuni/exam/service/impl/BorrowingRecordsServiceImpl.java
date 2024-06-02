package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.BorrowingRecordDto;
import softuni.exam.models.dto.xml.BorrowingRecordRootDto;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.Genre;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BookService;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {
    private static final String FILE_PATH = "src/main/resources/files/xml/borrowing-records.xml";
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final BookRepository bookRepository;
    private final LibraryMemberRepository libraryMemberRepository;

    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, ModelMapper modelMapper, Gson gson, XmlParser xmlParser, ValidationUtil validationUtil, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
    }

    @Override
    public boolean areImported() {
        return borrowingRecordRepository.count()>0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {
        StringBuilder sb =new StringBuilder();




        xmlParser.fromFile(FILE_PATH, BorrowingRecordRootDto.class)
                .getBorrowingRecordDto()
                .stream()
                .filter(borrowingRecordDto -> {
                    boolean isValid = validationUtil.isValid(borrowingRecordDto);
                    //&& !isExistTitle(borrowingRecordDto.getBook().getTitle());
                    //&& !isExistId(borrowingRecordDto.getMember().getId());
                    sb.append(isValid ? String.format("Successfully imported borrowing record %s - %s",borrowingRecordDto.getBook().getTitle(),borrowingRecordDto.getBorrowDate())
                            :"Invalid borrowing record");
                    sb.append(System.lineSeparator());
                    return isValid;
                })
                .map(borrowingRecordDto -> {
                    BorrowingRecord borrowingRecord = modelMapper.map(borrowingRecordDto,BorrowingRecord.class);

                    borrowingRecord.setBook(bookRepository.findBookByTitle(borrowingRecordDto.getBook().getTitle()));
                    borrowingRecord.setLibraryMember(libraryMemberRepository.findLibraryMemberById(borrowingRecordDto.getMember().getId()));

                    return borrowingRecord;
                })
                .forEach(borrowingRecordRepository::save);

        return sb.toString();
    }

    private boolean isExistTitle(String title) {
        return bookRepository.existsBookByTitle(title);
    }

    private boolean isExistId(Long id) {
        return libraryMemberRepository.existsLibraryMemberById(id);
    }


    @Override
    public String exportBorrowingRecords() {
        StringBuilder sb = new StringBuilder();
        borrowingRecordRepository.findAllByBook_GenreOrderByBorrowDateDesc(Genre.SCIENCE_FICTION)
                .forEach(br ->{
                    sb.append(String.format("Book title: %s\n" +
                            "*Book author: %s\n" +
                            "**Date borrowed: %s\n" +
                            "***Borrowed by: %s %s\n"
                    ,br.getBook().getTitle().toString(),
                            br.getBook().getAuthor()
                    ,br.getBorrowDate(),
                            br.getLibraryMember().getFirstName(),
                            br.getLibraryMember().getLastName()));
                    sb.append(System.lineSeparator());
                });
        return sb.toString().trim();
    }
}
