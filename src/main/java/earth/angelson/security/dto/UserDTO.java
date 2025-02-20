package earth.angelson.security.dto;

import java.util.Set;
import java.util.UUID;

public record UserDTO(UUID id, String practitionerId, String organizationId, String firstName, String lastName, String username,
							 String password, boolean enabled, Set<RoleDTO> roles) {
}
