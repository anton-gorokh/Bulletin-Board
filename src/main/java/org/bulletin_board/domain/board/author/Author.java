package org.bulletin_board.domain.board.author;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.AuditModel;
import org.bulletin_board.domain.Role;
import org.bulletin_board.domain.board.Announcement;
import org.bulletin_board.domain.board.Announcement_;
import org.bulletin_board.domain.board.address.Address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Entity
public class Author extends AuditModel {
    public static final String FULL_NAME_DELIMITER = " ";

    @NotBlank
    @Column(nullable = false, unique = true)
    String userName;

    @Column(nullable = false)
    String password;

    @Size(max = 64)
    @NotBlank(message = "First name cannot be empty")
    @Column(nullable = false)
    String firstName;

    @Size(max = 64)
    @NotBlank(message = "Second name cannot be empty")
    @Column(nullable = false)
    String lastName;

    @Min(value = 18, message = "Age cannot be less than 18")
    @Column(nullable = false)
    int age;

    @NotNull(message = "Address cannot be null")
    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
    Address address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "author_role",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = Email_.AUTHOR)
    Set<@NotNull @Valid Email> emails;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = Phone_.AUTHOR)
    Set<@NotNull @Valid Phone> phones;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = Announcement_.AUTHOR)
    Set<@NotNull @Valid Announcement> announcements;

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

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, address, emails, phones);
    }

    @Builder
    public Author(Long id, Instant createdAt, Instant updatedAt, String firstName, String lastName, int age,
                  Address address, Set<@NotNull @Valid Email> emails, Set<@NotNull @Valid Phone> phones,
                  Set<@NotNull @Valid Announcement> announcements) {
        super(id, createdAt, updatedAt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.emails = emails;
        this.phones = phones;
        this.announcements = announcements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && age == author.age && firstName.equals(author.firstName) && lastName.equals(author.lastName) && address.equals(author.address) && emails.equals(author.emails) && phones.equals(author.phones);
    }
}
