package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.LibraryMemberDto;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {
    private static final String FILE_PATH = "src/main/resources/files/json/library-members.json";
    private final LibraryMemberRepository libraryMemberRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository, ModelMapper modelMapper, Gson gson, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.libraryMemberRepository = libraryMemberRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return libraryMemberRepository.count()>0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importLibraryMembers() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readLibraryMembersFileContent(), LibraryMemberDto[].class))
                .filter(libraryMemberDto -> {
                    boolean isValid = validationUtil.isValid(libraryMemberDto)
                            && !isPhoneExist(libraryMemberDto.getPhoneNumber());
                    sb.append(isValid ? String.format("Successfully imported library member %s - %s",libraryMemberDto.getFirstName(),libraryMemberDto.getLastName())
                            :"Invalid library member");
                    sb.append(System.lineSeparator());
                    return isValid;

                })
                .map(libraryMemberDto -> modelMapper.map(libraryMemberDto, LibraryMember.class))
                .forEach(libraryMemberRepository::save);
        return sb.toString();
    }

    @Override
    public LibraryMember findById(Long id) {
        return libraryMemberRepository.getById(id);
    }

    private boolean isPhoneExist(String phoneNumber) {
        return libraryMemberRepository.existsLibraryMemberByPhoneNumber(phoneNumber);
    }
}
