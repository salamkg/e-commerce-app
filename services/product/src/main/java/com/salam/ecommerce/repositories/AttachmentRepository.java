package com.salam.ecommerce.repositories;

import com.salam.ecommerce.models.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
