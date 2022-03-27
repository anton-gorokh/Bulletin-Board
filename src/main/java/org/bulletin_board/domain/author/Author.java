package org.bulletin_board.domain.author;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.address.Address;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Table(name = "Author")
public class Author {
    @Id
    @Column(name = "author_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(max = 64)
    @NotBlank(message = "First name cannot be empty")
    @Column(name = "first_name")
    String firstName;

    @Size(max = 64)
    @NotBlank(message = "Second name cannot be empty")
    @Column(name = "last_name")
    String lastName;

    @Min(value = 18, message = "Age cannot be less than 18")
    @Max(value = 150, message = "Age cannot be more than 150")
    int age;

    @NotNull(message = "Address cannot be null")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    @JoinColumn(name = "FK_Author_Address")
    Address address;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "Author_Email",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "email_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = "author_id"))
    List<@NotNull @Valid Email> emails;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "Author_Phone",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "phone_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = "author_id"))
    Set<@NotNull @Valid Phone> phones;

    @Override
    public String toString() {
        return "Author:" +
                "id = " + id +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", age = " + age +
                ", address = " + address +
                ", \nemails: " + emails +
                ", \nphones:" + phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && age == author.age && firstName.equals(author.firstName) && lastName.equals(author.lastName) && address.equals(author.address) && emails.equals(author.emails) && phones.equals(author.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, address, emails, phones);
    }
}
