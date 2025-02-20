package earth.angelson.security;

import ca.uhn.fhir.rest.server.interceptor.auth.RuleBuilder;
import earth.angelson.security.cache.TokenCacheService;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.interceptor.auth.IAuthRule;

import java.util.List;

@SuppressWarnings("ConstantConditions")
public class AuthorizationInterceptor extends ca.uhn.fhir.rest.server.interceptor.auth.AuthorizationInterceptor {

	private final TokenCacheService tokenCacheService;
	private final List<String> allowedUrls;

	public AuthorizationInterceptor(TokenCacheService tokenCacheService, List<String> allowedUrls) {
		this.tokenCacheService = tokenCacheService;
		this.allowedUrls = allowedUrls;
	}

	@Override
	public List<IAuthRule> buildRuleList(RequestDetails theRequestDetails) {
		String authHeader = theRequestDetails.getHeader("Authorization");

		for (String url : allowedUrls) {
			if (theRequestDetails.getCompleteUrl().contains(url)) {
				return new RuleBuilder().allowAll().build();
			}
		}

		return tokenCacheService.getData(authHeader);
	}
}
