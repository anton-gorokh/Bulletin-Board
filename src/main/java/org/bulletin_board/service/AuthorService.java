package org.bulletin_board.service;

import org.bulletin_board.domain.model.Author;
import org.bulletin_board.dto.SimplePair;
import org.bulletin_board.dto.author.AuthorDto;
import org.bulletin_board.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorService {
    private final PasswordEncoder passwordEncoder;
    private final AnnouncementService announcementService;
    private final AnnouncementFilterService announcementFilterService;
    private final AuthorMapper mapper;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(PasswordEncoder passwordEncoder, AnnouncementService announcementService, AnnouncementFilterService announcementFilterService, AuthorMapper mapper, AuthorRepository authorRepository) {
        this.passwordEncoder = passwordEncoder;
        this.announcementService = announcementService;
        this.announcementFilterService = announcementFilterService;
        this.mapper = mapper;
        this.authorRepository = authorRepository;
    }

    public List<AuthorDto> getPage(int page, int size) {
        PageRequest request = PageRequest.of(page, size);
        List<Author> authors = authorRepository.findAll(request).getContent();
        return authors.stream().map(mapper::mapToDto).collect(Collectors.toList());
    }

    public AuthorDto getById(Long id) {
        return mapper.mapToDto(authorRepository.getById(id));
    }

    public long save(AuthorDto dto) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Dto has id");
        }
        Author entity = mapper.mapToEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return authorRepository.save(entity).getId();
    }

    public void update(AuthorDto dto, Long id) {
        Author entity = mapper.mapToEntity(dto);
        entity.setId(id);
        authorRepository.save(entity);
    }

    public void updatePassword(SimplePair oldNewPassword, Long id) {
        Author author = authorRepository.getById(id);
        if (!passwordEncoder.matches(oldNewPassword.getFirst(), author.getPassword())) {
            throw new IllegalArgumentException("Given password doesn't match current password");
        }

        author.setPassword(passwordEncoder.encode(oldNewPassword.getSecond()));
    }

    public void deleteById(Long id) {
        announcementService.deleteAllByAuthorId(id);
        announcementFilterService.deleteAllByAuthorId(id);
        authorRepository.deleteById(id);
    }
}
