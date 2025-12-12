package metrics.service.apm.services;

import lombok.RequiredArgsConstructor;
import metrics.service.apm.dtos.ProjectSummaryDTO;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.CompareResults;
import org.gitlab4j.api.models.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    @Value("${gitlab.url}")
    private String gitLabUrl;

    @Value("${gitlab.token}")
    private String gitLabToken;

    @Value("${gitlab.group-id}")
    private Long groupId;

    private final GitLabApi gitLabApi;

    public List<ProjectSummaryDTO> listarProjetosDoGrupo() throws GitLabApiException {
            return gitLabApi.getGroupApi().getProjects(groupId)
                    .stream()
                    .map(ProjectSummaryDTO::fromProject)
                    .toList();

    }

    /**
     * Compara duas branches e retorna quantos commits a branch de destino
     * tem a mais que a branch de comparação.
     *
     * @param projectId ID do projeto no GitLab
     * @param originBranch A branch que queremos verificar o avanço (ex: feature/nova-funcionalidade)
     * @param targetBranch A branch base (ex: main ou develop)
     * @return Número de commits que a branchDestino está à frente.
     */
    public int contarCommitsAFrente(Long projectId, String originBranch, String targetBranch) throws GitLabApiException {
            CompareResults result = gitLabApi.getRepositoryApi()
                    .compare(projectId, targetBranch, originBranch);

            return result.getCommits().size();

    }
}
