package org.bulletin_board.service;

import org.bulletin_board.domain.model.Author;
import org.bulletin_board.dto.author.AuthorDto;
import org.bulletin_board.repository.AddressRepository;
import org.bulletin_board.service.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class AuthorMapper implements SimpleDtoMapper<Author, AuthorDto> {
    private final AddressRepository addressRepository;

    @Autowired
    public AuthorMapper(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AuthorDto mapToDto(Author author) {
        return AuthorDto.builder()
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .age(author.getAge())
                .address(author.getAddress().toSimpleValue())
                .phones(author.getPhones())
                .emails(author.getEmails())
                .build();
    }

    public Author mapToEntity(AuthorDto authorDto) {
        return Author.builder()
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .age(authorDto.getAge())
                .address(addressRepository.getById(authorDto.getAddress().getId()))
                .phones(authorDto.getPhones())
                .emails(authorDto.getEmails())
                .build();
    }
}
