package earth.angelson.security.dto;

import java.util.Map;
import java.util.Set;

public class RoleAttachmentsDTO {
	private UserDTO user;
	private Set<Map<String, String>> attachments;

	public RoleAttachmentsDTO() {
	}

	public RoleAttachmentsDTO(UserDTO user, Set<Map<String, String>> attachments) {
		this.user = user;
		this.attachments = attachments;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Set<Map<String, String>> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Map<String, String>> attachments) {
		this.attachments = attachments;
	}
}
