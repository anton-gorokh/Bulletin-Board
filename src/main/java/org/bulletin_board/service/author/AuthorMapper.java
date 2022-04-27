package org.bulletin_board.service.author;

import org.bulletin_board.domain.board.author.Author;
import org.bulletin_board.dto.SimpleValue;
import org.bulletin_board.dto.author.AuthorDto;
import org.bulletin_board.repository.AddressRepository;
import org.bulletin_board.repository.EmailRepository;
import org.bulletin_board.repository.PhoneRepository;
import org.bulletin_board.service.util.MapperUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorMapper {
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;
    private final AddressRepository addressRepository;

    public AuthorMapper(PhoneRepository phoneRepository, EmailRepository emailRepository, AddressRepository addressRepository) {
        this.phoneRepository = phoneRepository;
        this.emailRepository = emailRepository;
        this.addressRepository = addressRepository;
    }

    public AuthorDto mapToDto(Author author) {
        return AuthorDto.builder()
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .age(author.getAge())
                .address(author.getAddress().toSimpleValue())
                .phones(MapperUtil.toSimpleValues(author.getPhones()))
                .emails(MapperUtil.toSimpleValues(author.getEmails()))
                .build();
    }

    public Author mapToEntity(AuthorDto authorDto) {
        return Author.builder()
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .age(authorDto.getAge())
                .address(addressRepository.getById(authorDto.getAddress().getId()))
                .phones(Set.copyOf(phoneRepository.findAllById(MapperUtil.getSimpleValueIds(authorDto.getPhones()))))
                .emails(Set.copyOf(emailRepository.findAllById(MapperUtil.getSimpleValueIds(authorDto.getEmails()))))
                .build();
    }
}
