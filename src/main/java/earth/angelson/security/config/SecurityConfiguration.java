package earth.angelson.security.config;

import earth.angelson.security.AuthorizationInterceptor;
import earth.angelson.security.cache.TokenCacheService;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class SecurityConfiguration {


	@Value("${security.service.url:http://localhost:8090/account/info}")
	private String securityServiceUrl;

	@Value("#{'${security.service.allowed-urls:swagger-ui,api-docs}'.split(',')}")
	private List<String> allowedUrls;

	@Bean
	public TokenCacheService tokenCacheService() {
		return new TokenCacheService(securityServiceUrl);
	}

	@Bean
	public AuthorizationInterceptor authorizationInterceptor(TokenCacheService tokenCacheService) {
		return new AuthorizationInterceptor(tokenCacheService, allowedUrls);
	}

	@Bean
	public CacheManager caffeineCacheManager() {
		CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
		caffeineCacheManager
			.setCaffeine(Caffeine.newBuilder()
				.expireAfterWrite(15, TimeUnit.MINUTES));
		return caffeineCacheManager;
	}

}
