package org.bulletin_board.domain.board.author;

import lombok.*;
import org.bulletin_board.domain.AuditModel;
import org.bulletin_board.domain.Role;
import org.bulletin_board.domain.board.Announcement;
import org.bulletin_board.domain.board.Announcement_;
import org.bulletin_board.domain.board.address.Address;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Author extends AuditModel {
    public static final String FULL_NAME_DELIMITER = " ";

    @NotBlank
    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Size(max = 64)
    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @Size(max = 64)
    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @Min(value = 18)
    @Column(nullable = false)
    private int age;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "author_role",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = Email_.AUTHOR)
    private Set<@NotNull @Valid Email> emails;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = Phone_.AUTHOR)
    private Set<@NotNull @Valid Phone> phones;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = Announcement_.AUTHOR)
    private Set<@NotNull @Valid Announcement> announcements;

    public String getFullName() {
        return this.firstName + FULL_NAME_DELIMITER + this.lastName;
    }

    public void setFullName(String fullName) {
        String[] columns = fullName.split(FULL_NAME_DELIMITER);
        this.firstName = columns[0];
        this.lastName = columns[1];
    }

    public void setFullName(String fullName, String delimiter) {
        String[] columns = fullName.split(delimiter);
        this.firstName = columns[0];
        this.lastName = columns[1];
    }

    @Builder
    public Author(Long id, Instant createdAt, Instant updatedAt, String userName, String password, String firstName,
                  String lastName, int age, Address address, Set<Role> roles, Set<@NotNull @Valid Email> emails,
                  Set<@NotNull @Valid Phone> phones, Set<@NotNull @Valid Announcement> announcements) {
        super(id, createdAt, updatedAt);
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.roles = roles;
        this.emails = emails;
        this.phones = phones;
        this.announcements = announcements;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, address, emails, phones);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && age == author.age && firstName.equals(author.firstName) && lastName.equals(author.lastName) && address.equals(author.address) && emails.equals(author.emails) && phones.equals(author.phones);
    }
}
