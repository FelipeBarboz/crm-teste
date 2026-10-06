package dev.FelipeBarboz.crm_teste.lead.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Lead {
    private UUID leadId;
    private String source;
    private LeadPriority priority;
    private BigDecimal value;
    private String lost_reason;
    private Integer position;
    private LocalDateTime last_interaction_at;
    private LocalDateTime updated_at;
    private LocalDateTime closed_at;
    private LocalDateTime created_at;

    public Lead(UUID leadId, String source, LeadPriority priority, BigDecimal value, String lost_reason, Integer position, LocalDateTime last_interaction_at, LocalDateTime updated_at, LocalDateTime closed_at, LocalDateTime created_at) {
        this.leadId = leadId;
        this.source = source;
        this.priority = priority;
        this.value = value;
        this.lost_reason = lost_reason;
        this.position = position;
        this.last_interaction_at = last_interaction_at;
        this.updated_at = updated_at;
        this.closed_at = closed_at;
        this.created_at = created_at;
    }

    public static Lead create(String source, LeadPriority priority, BigDecimal value, String lost_reason, Integer position, LocalDateTime last_interaction_at, LocalDateTime updated_at, LocalDateTime closed_at, LocalDateTime created_at){
        return new Lead(
                UUID.randomUUID(),
                source,
                priority,
                value,
                lost_reason,
                position,
                last_interaction_at,
                LocalDateTime.now(),
                null,
                LocalDateTime.now()
        );
    }

    public static Lead reconstitute(UUID leadId, String source, LeadPriority priority, BigDecimal value, String lost_reason, Integer position, LocalDateTime last_interaction_at, LocalDateTime updated_at, LocalDateTime closed_at, LocalDateTime created_at){
        return new Lead(
                leadId,
                source,
                priority,
                value,
                lost_reason,
                position,
                last_interaction_at,
                updated_at,
                closed_at,
                created_at
        );
    }
}
