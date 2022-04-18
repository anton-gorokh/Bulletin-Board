package org.bulletin_board.service.author;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dto.author.AuthorDto;
import org.bulletin_board.repository.AuthorRepository;
import org.bulletin_board.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AuthorService {
    PasswordEncoder passwordEncoder;
    AuthorRepository authorRepository;
    AnnouncementService announcementService;
    AuthorMapper authorMapper;

    @Autowired
    public AuthorService(PasswordEncoder passwordEncoder, AuthorRepository authorRepository, AnnouncementService announcementService, AuthorMapper authorMapper) {
        this.passwordEncoder = passwordEncoder;
        this.authorRepository = authorRepository;
        this.announcementService = announcementService;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream().map(authorMapper::mapToDto).collect(Collectors.toList());
    }

    public AuthorDto findById(Long id) {
        return authorMapper.mapToDto(authorRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("There is no author with such id")));
    }

    public long save(AuthorDto author) throws Exception {
        if (author.getId() != null) {
            throw new Exception("Entry has id");
        }
        author.setPassword(passwordEncoder.encode(author.getPassword()));
        return authorRepository.save(authorMapper.mapToEntity(author)).getId();
    }

    public void update(AuthorDto author) {
        author.setPassword(passwordEncoder.encode(author.getPassword()));
        authorRepository.save(authorMapper.mapToEntity(author));
    }

    public void deleteById(Long id) {
        announcementService.deleteAllAnnouncementsByAuthorId(id);
        authorRepository.deleteById(id);
    }
}
