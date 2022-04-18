package org.bulletin_board.service.author;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.board.author.Author;
import org.bulletin_board.dto.SimpleValue;
import org.bulletin_board.dto.author.AuthorDto;
import org.bulletin_board.repository.AddressRepository;
import org.bulletin_board.repository.EmailRepository;
import org.bulletin_board.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorMapper {
    PhoneRepository phoneRepository;
    EmailRepository emailRepository;
    AddressRepository addressRepository;

    @Autowired
    public AuthorMapper(PhoneRepository phoneRepository, EmailRepository emailRepository, AddressRepository addressRepository) {
        this.phoneRepository = phoneRepository;
        this.emailRepository = emailRepository;
        this.addressRepository = addressRepository;
    }

    public AuthorDto mapToDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .age(author.getAge())
                .address(new SimpleValue(author.getAddress().getId(), author.getAddress().toString()))
                .phones(author.getPhones().stream()
                        .map(phone -> new SimpleValue(phone.getId(), phone.getPhoneNumber()))
                        .collect(Collectors.toList()))
                .emails(author.getEmails().stream()
                        .map(email -> new SimpleValue(email.getId(), email.getName()))
                        .collect(Collectors.toList()))
                .build();
    }

    public Author mapToEntity(AuthorDto authorDto) {
        return Author.builder()
                .id(authorDto.getId())
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .age(authorDto.getAge())
                .address(addressRepository.getById(authorDto.getAddress().getId()))
                .phones(new HashSet<>(phoneRepository.findAllById(authorDto.getPhones().stream().map(SimpleValue::getId).collect(Collectors.toList()))))
                .emails(new HashSet<>(emailRepository.findAllById(authorDto.getEmails().stream().map(SimpleValue::getId).collect(Collectors.toList()))))
                .build();
    }
}
