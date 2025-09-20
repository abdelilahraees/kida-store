package com.kida.kidastore.dao;

import com.kida.kidastore.dao.InvoiceDao;
import com.kida.kidastore.models.entity.Invoice;
import com.kida.kidastore.repository.InvoiceRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InvoiceDao {


    private InvoiceRepository invoiceRepository;


    public InvoiceDao(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }


    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }


    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);

    }

    public Optional<Invoice> findByOrderId(Long orderId) {
        return invoiceRepository.findByOrderId(orderId);
    }

    public List<Invoice> findAllInvoices() {
        return invoiceRepository.findAll();
    }
}
