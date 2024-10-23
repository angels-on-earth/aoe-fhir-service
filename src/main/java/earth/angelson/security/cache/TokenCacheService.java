package earth.angelson.security.cache;


import ca.uhn.fhir.rest.server.interceptor.auth.IAuthRule;
import ca.uhn.fhir.rest.server.interceptor.auth.RuleBuilder;
import earth.angelson.security.dto.UserRoleAttachmentsDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TokenCacheService {

	private final RestTemplate restTemplate;
	private final String url;

	public TokenCacheService(String url) {
		this.restTemplate = new RestTemplate();
		this.url = url;
	}

	@Cacheable(value = "jwtTokenCache", cacheManager = "caffeineCacheManager")
	public List<IAuthRule> getData(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);

		// Create HttpEntity with headers
		HttpEntity<UserRoleAttachmentsDTO> entity = new HttpEntity<>(headers);

		ResponseEntity<UserRoleAttachmentsDTO> response =
			restTemplate.exchange(url, HttpMethod.GET, entity, UserRoleAttachmentsDTO.class);


		if (response.getBody() != null) {
			var builder = new RuleBuilder().build();
			var userInfo = response.getBody();

			userInfo.user().roles().forEach(role -> {
				switch (role.name().toUpperCase()) {
					case "ADMIN", "PRACTITIONER", "OPERATOR": {
						builder.addAll(new RuleBuilder().allowAll().build());
						break;
					}
				}

			});
			return builder;
		}

		// By default, deny everything. This should never get hit, but it's
		// good to be defensive
		return new RuleBuilder().denyAll().build();
	}
}
