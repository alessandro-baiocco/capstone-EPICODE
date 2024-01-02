package application.capstone.payloads;

import java.util.Date;

public record ErrorResponseDTO(String message, Date timestamp) {
}
