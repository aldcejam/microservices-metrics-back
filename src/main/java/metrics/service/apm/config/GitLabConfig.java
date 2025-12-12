package metrics.service.apm.config;

import org.gitlab4j.api.GitLabApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GitLabConfig {

    @Bean
    public GitLabApi gitLabApi(
            @Value("${gitlab.url}") String gitLabUrl,
            @Value("${gitlab.token}") String gitLabToken
    ) {
        return new GitLabApi(GitLabApi.ApiVersion.V4, gitLabUrl, gitLabToken);
    }
}
