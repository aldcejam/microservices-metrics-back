package metrics.service.apm.controllers;

import lombok.RequiredArgsConstructor;
import metrics.service.apm.services.ProjectService;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "metrics")
@RequiredArgsConstructor
public class main {

    private final ProjectService projectService;

    @GetMapping("/projects-summary")
    public Object listar() throws GitLabApiException {
        return projectService.listarProjetosDoGrupo();
    }

    /**
     * Endpoint para comparar branches.
     * Exemplo de chamada:
     * GET /main/compare?projectId=123&dest=feature/nova-tabela&base=main
     */
    @GetMapping("/compare")
    public int verificarCommitsAFrente(
            @RequestParam("projectId") Long projectId,
            @RequestParam("origin") String branchDestino,
            @RequestParam("target") String branchComparacao
    ) throws GitLabApiException {
        return projectService.contarCommitsAFrente(projectId, branchDestino, branchComparacao);
    }

}
