package bg.softuni.mobilele.models.dto;

public record UserRegistrationDTO(String firstName,
                                  String lastName,
                                  String password,
                                  String email) {
}
