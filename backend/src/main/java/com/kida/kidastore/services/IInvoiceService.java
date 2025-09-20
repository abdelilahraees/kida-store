package com.kida.kidastore.services;

import com.kida.kidastore.models.dto.Response.InvoiceDtoResponse;
import com.kida.kidastore.models.entity.Invoice;
import com.kida.kidastore.models.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IInvoiceService {


    InvoiceDtoResponse findById(Long id);

    InvoiceDtoResponse createInvoice(Order order);

    InvoiceDtoResponse updateInvoice(Long id, Boolean isPaid);

    void deleteInvoiceById(Long id);

    InvoiceDtoResponse findInvoiceByOrderId(Long orderId);

    List<InvoiceDtoResponse> findAllInvoices();

}
