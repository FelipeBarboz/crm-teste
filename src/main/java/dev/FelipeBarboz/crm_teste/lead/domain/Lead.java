package dev.FelipeBarboz.crm_teste.lead.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Lead {
    private UUID leadId;
    private UUID assignedTo;
    private LeadSource source;
    private LeadPriority priority;
    private LeadStatus status;
    private BigDecimal value;
    private  Integer score;
    private LostReason lostReason;
    private Integer position;
    private LocalDateTime lastInteractionAt;
    private LocalDateTime updatedAt;
    private LocalDateTime closedAt;
    private LocalDateTime nextContactAt;
    private LocalDateTime createdAt;

    public Lead(UUID leadId, UUID assignedTo, LeadSource source, LeadPriority priority, LeadStatus status, BigDecimal value, Integer score, LostReason lostReason, Integer position, LocalDateTime lastInteractionAt, LocalDateTime updatedAt, LocalDateTime closedAt, LocalDateTime nextContactAt, LocalDateTime createdAt) {
        if(leadId == null){
            throw new RuntimeException("O leadId nao pode ser nulo");
        }
        this.leadId = leadId;

        this.assignedTo = assignedTo;
        this.source = source;

        if (priority == null){
            throw new RuntimeException("O lead nao pode ter prioridade nula");
        }
        this.priority = priority;

        if (status == null){
            throw new RuntimeException("O status do lead nao pode ser nulo");
        }
        this.status = status;

        validateClosure(status, closedAt);

        this.value = value;
        this.score = score;
        this.lostReason = lostReason;
        this.position = position;
        this.lastInteractionAt = lastInteractionAt;
        this.updatedAt = updatedAt;
        this.closedAt = closedAt;
        this.nextContactAt = nextContactAt;
        this.createdAt = createdAt;
    }

    public static Lead create(LeadSource source, UUID assignedTo, LeadPriority priority, LeadStatus status, BigDecimal value, Integer score, LostReason lostReason, Integer position, LocalDateTime lastInteractionAt, LocalDateTime updatedAt, LocalDateTime closedAt, LocalDateTime nextContactAt, LocalDateTime createdAt){
        return new Lead(
                UUID.randomUUID(),
                assignedTo,
                source,
                priority,
                status,
                value,
                score,
                lostReason,
                position,
                lastInteractionAt,
                LocalDateTime.now(),
                null,
                nextContactAt,
                LocalDateTime.now()
        );
    }

    public static Lead reconstitute(UUID leadId, UUID assignedTo, LeadSource source, LeadPriority priority, LeadStatus status, BigDecimal value, Integer score, LostReason lostReason, Integer position, LocalDateTime lastInteractionAt, LocalDateTime updatedAt, LocalDateTime closedAt, LocalDateTime nextContactAt, LocalDateTime createdAt){
        return new Lead(
                leadId,
                assignedTo,
                source,
                priority,
                status,
                value,
                score,
                lostReason,
                position,
                lastInteractionAt,
                updatedAt,
                closedAt,
                nextContactAt,
                createdAt
        );
    }

    private void validateClosure(LeadStatus status, LocalDateTime closedAt) {
        boolean closed = status == LeadStatus.WON || status == LeadStatus.LOST;

        if (closed && closedAt == null) {
            throw new RuntimeException("Lead encerrado deve possuir data de fechamento");
        }

        if (!closed && closedAt != null) {
            throw new RuntimeException("Lead aberto não pode possuir data de fechamento");
        }
    }

    public void changeStatus(LeadStatus status){
        if (status == null){
            throw new RuntimeException("O status nao pode ser nulo");
        }

        this.status = status;
        this.updatedAt = LocalDateTime.now();

        if(status == LeadStatus.WON || status == LeadStatus.LOST){
            this.closedAt = LocalDateTime.now();
        }
    }

    public void assignTo(UUID assignedTo) {
        this.assignedTo = assignedTo;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateValue(BigDecimal value){
        if (value != null && value.signum() < 0){
            throw  new RuntimeException("O valor do lead nao pode ser negativo");
        }

        this.value = value;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsWon(){
        if (status == LeadStatus.WON || status == LeadStatus.LOST){
            throw new RuntimeException("Nao e possivel marcar um lead finalizado como won");
        }

        this.status = LeadStatus.WON;
        this.updatedAt = LocalDateTime.now();
        this.closedAt = LocalDateTime.now();
    }

    public void markAsLost(){
        if (status == LeadStatus.WON || status == LeadStatus.LOST){
            throw new RuntimeException("Nao e possivel marcar um lead finalizado como lost");
        }

        this.status = LeadStatus.LOST;
        this.updatedAt = LocalDateTime.now();
        this.closedAt = LocalDateTime.now();
    }

    public void reopen() {
        boolean closed = this.status == LeadStatus.WON || this.status == LeadStatus.LOST;

        if (!closed || this.closedAt == null) {
            throw new RuntimeException("Somente leads finalizados podem ser reabertos");
        }

        this.status = LeadStatus.CONTACTED;
        this.closedAt = null;
        this.updatedAt = LocalDateTime.now();
    }

    public void scheduleNextContact(LocalDateTime nextContactAt) {
        if (nextContactAt == null) {
            throw new RuntimeException("A data do proximo contato nao pode ser nula");
        }

        if (nextContactAt.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("A data do proximo contato nao pode estar no passado");
        }

        this.nextContactAt = nextContactAt;
        this.updatedAt = LocalDateTime.now();
    }
}
