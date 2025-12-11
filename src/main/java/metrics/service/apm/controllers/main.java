package metrics.service.apm.controllers;

import lombok.RequiredArgsConstructor;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.models.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "main")
@RequiredArgsConstructor
public class main {

    @Value("${gitlab.url}")
    private String gitLabUrl;

    @Value("${gitlab.token}")
    private String gitLabToken;

    @Value("${gitlab.group-id}")
    private Long groupId;

    public List<Project> listarProjetosDoGrupo() {
        // O try-with-resources garante que a conexão seja fechada se necessário
        try (
                GitLabApi gitLabApi = new GitLabApi(GitLabApi.ApiVersion.V4, gitLabUrl, gitLabToken);

        ) {
            // Busca projetos do grupo
            return gitLabApi.getGroupApi().getProjects(groupId).stream().map(project -> {
                System.out.println("Projeto: " + project.getName() + " - ID: " + project.getId());
                return project;
            }).toList();
        } catch (Exception e) {
            throw new RuntimeException("Erro no GitLab: " + e.getMessage(), e);
        }
    }

    @GetMapping()
    public Object listar() {
        return listarProjetosDoGrupo();
    }

}
