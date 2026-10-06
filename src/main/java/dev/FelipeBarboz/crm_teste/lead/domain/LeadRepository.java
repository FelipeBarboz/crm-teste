package dev.FelipeBarboz.crm_teste.lead.domain;

import java.util.Optional;
import java.util.UUID;

public interface LeadRepository {
    Lead save(Lead client);
    Optional<Lead> findByLeadId(UUID leadId);
}
