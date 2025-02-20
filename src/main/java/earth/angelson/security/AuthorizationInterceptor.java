package earth.angelson.security;

import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.server.exceptions.ForbiddenOperationException;
import earth.angelson.security.cache.TokenCacheService;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.interceptor.auth.IAuthRule;
import org.hl7.fhir.instance.model.api.IBaseResource;

import java.util.List;

@SuppressWarnings("ConstantConditions")
public class AuthorizationInterceptor extends ca.uhn.fhir.rest.server.interceptor.auth.AuthorizationInterceptor {

	private final TokenCacheService tokenCacheService;

	public AuthorizationInterceptor(TokenCacheService tokenCacheService) {
		this.tokenCacheService = tokenCacheService;
	}

	@Override
	public List<IAuthRule> buildRuleList(RequestDetails theRequestDetails) {
		String authHeader = theRequestDetails.getHeader("Authorization");

		List<IAuthRule> rules = tokenCacheService.getData(authHeader);
		if (rules == null || rules.isEmpty()) {
			throw new ForbiddenOperationException("Unauthorized request");
		}
		return rules;
	}

	@Override
	public void hookOutgoingResponse(RequestDetails theRequestDetails, IBaseResource theResponseObject, Pointcut thePointcut) {
		super.hookOutgoingResponse(theRequestDetails, theResponseObject, thePointcut);
	}
}
