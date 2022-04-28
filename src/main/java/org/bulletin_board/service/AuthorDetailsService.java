package org.bulletin_board.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.model.author.Author;
import org.bulletin_board.repository.AuthorRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AuthorDetailsService implements UserDetailsService {

    AuthorRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author author = repository.getByUsername(username);
        return buildUser(author);
    }

    private Set<GrantedAuthority> getAuthorAuthorities(Author author) {
        return author.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toSet());
    }

    private UserDetails buildUser(Author author) {
        Set<GrantedAuthority> authorities = getAuthorAuthorities(author);
        return new User(author.getUsername(), author.getPassword(), authorities);
    }
}
