package com.kida.kidastore.models.mappers;

import com.kida.kidastore.models.dto.Response.InvoiceDtoResponse;
import com.kida.kidastore.models.entity.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice toEntity(InvoiceDtoResponse dto);
    InvoiceDtoResponse toDto(Invoice invoice);
}
