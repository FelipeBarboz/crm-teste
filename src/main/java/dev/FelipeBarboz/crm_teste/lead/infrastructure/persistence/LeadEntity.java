package dev.FelipeBarboz.crm_teste.lead.infrastructure.persistence;

import dev.FelipeBarboz.crm_teste.lead.domain.LeadPriority;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "leads")
public class LeadEntity {
    @Column(name = "id", nullable = false, unique = true)
    private UUID leadId;

    @Column(name = "source")
    private String source;

    @Column(name = "priority", nullable = false)
    private LeadPriority priority;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "lost_reason")
    private String lost_reason;

    @Column(name = "position")
    private Integer position;

    @Column(name = "last_interaction_at")
    private LocalDateTime last_interaction_at;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at;

    @Column(name = "closed_at")
    private LocalDateTime closed_at;
}
