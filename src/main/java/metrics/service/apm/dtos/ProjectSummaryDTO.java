package metrics.service.apm.dtos;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.gitlab4j.api.models.Project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSummaryDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String webUrl;
    private String sshUrl;
    private String branchPadrao;
    private String visibilidade;
    private LocalDateTime ultimaAtividade;
    private Integer stars;
    private Integer forks;
    private boolean arquivado;

    public static ProjectSummaryDTO fromProject(Project project) {
        return ProjectSummaryDTO.builder()
                .id(project.getId())
                .nome(project.getName())
                .descricao(project.getDescription() != null ? project.getDescription() : "")
                .webUrl(project.getWebUrl())
                .sshUrl(project.getSshUrlToRepo())
                .branchPadrao(project.getDefaultBranch())
                .visibilidade(project.getVisibility() != null ? project.getVisibility().toString() : "UNKNOWN")
                .ultimaAtividade(
                        project.getLastActivityAt() != null
                                ? project.getLastActivityAt().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                                : null
                )
                .stars(project.getStarCount())
                .forks(project.getForksCount())
                .arquivado(project.getArchived())
                .build();
    }
}
