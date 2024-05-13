package earth.angelson.security.dto;

import java.util.Set;
import java.util.UUID;

public class UserDTO {
	private UUID id;
	private String practitionerId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean enabled;
	private Set<RoleDTO> roles;

	public UserDTO() {
	}

	public UserDTO(UUID id, String practitionerId, String firstName, String lastName, String username, String password, boolean enabled, Set<RoleDTO> roles) {
		this.id = id;
		this.practitionerId = practitionerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getPractitionerId() {
		return practitionerId;
	}

	public void setPractitionerId(String practitionerId) {
		this.practitionerId = practitionerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}
}
