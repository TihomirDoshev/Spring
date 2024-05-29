package softuni.exam.service;

import softuni.exam.models.entity.LibraryMember;

import java.io.IOException;


public interface LibraryMemberService {

    boolean areImported();

    String readLibraryMembersFileContent() throws IOException;
	
	String importLibraryMembers() throws IOException;

    LibraryMember findById(Long id);
}
