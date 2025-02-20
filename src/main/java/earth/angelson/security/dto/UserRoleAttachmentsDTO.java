package earth.angelson.security.dto;

import java.util.Set;

public record UserRoleAttachmentsDTO(UserDTO user, Set<Object> attachments) {
}

