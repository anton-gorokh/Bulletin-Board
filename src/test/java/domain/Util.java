package domain;

import org.bulletin_board.domain.Announcement;
import org.bulletin_board.domain.MatchingAd;
import org.bulletin_board.domain.Rubric;
import org.bulletin_board.domain.address.Address;
import org.bulletin_board.domain.address.Country;
import org.bulletin_board.domain.author.Author;
import org.bulletin_board.domain.author.Email;
import org.bulletin_board.domain.author.Phone;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public final class Util {
    public static Address getSampleAddress() {
        Address address = Address.builder()
                .country(Country.US)
                .city("California")
                .street("Mountain View")
                .homeNumber(1)
                .build();

        return address;
    }

    public static Author getSampleAuthor(String firstName, String lastName) {
        Author sampleAuthor = Author.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(18)
                .phones(new HashSet<>(List.of(getSamplePhone())))
                .emails(Arrays.asList(getSampleEmail()))
                .build();
        return sampleAuthor;
    }

    public static Rubric getSampleRubric(String name) {
        Rubric rubric = Rubric.builder()
                .name(name)
                .build();
        return rubric;
    }

    public static Announcement getSampleAnnouncement(String name) {
        Announcement announcement = Announcement.builder()
                .name(name)
                .pay(new BigDecimal(777))
                .creationDate(new Date())
                .text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Nulla rhoncus sem mattis lectus luctus viverra. " +
                        "Nullam placerat dui ac blandit posuere.")
                .build();
        return announcement;
    }

    public static MatchingAd getSampleMad(String title, BigDecimal priceFrom, BigDecimal priceTo) {
        MatchingAd matchingAd = MatchingAd.builder()
                .priceFrom(priceFrom)
                .priceTo(priceTo)
                .title(title)
                .build();
        return matchingAd;
    }

    public static Email getSampleEmail() {
        Email sampleEmail = Email.builder()
                .name("sample@mail.com")
                .build();
        return sampleEmail;
    }

    public static Phone getSamplePhone() {
        Phone samplePhone = Phone.builder()
                .phoneNumber("+1-212-456-7890")
                .build();
        return samplePhone;
    }
}
