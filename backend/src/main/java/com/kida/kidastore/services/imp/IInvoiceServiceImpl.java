package com.kida.kidastore.services.imp;

import com.kida.kidastore.dao.InvoiceDao;
import com.kida.kidastore.exception.ResourceNotFoundException;
import com.kida.kidastore.models.dto.Response.InvoiceDtoResponse;
import com.kida.kidastore.models.entity.Invoice;
import com.kida.kidastore.models.entity.Order;
import com.kida.kidastore.models.mappers.InvoiceMapper;
import com.kida.kidastore.services.IInvoiceService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IInvoiceServiceImpl implements IInvoiceService {


    private final InvoiceMapper invoiceMapper;
    private InvoiceDao invoiceDao;

    public IInvoiceServiceImpl(InvoiceDao invoiceDao, InvoiceMapper invoiceMapper) {
        this.invoiceDao = invoiceDao;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public InvoiceDtoResponse findById(Long id) {
        return invoiceDao.findById(id).map(invoiceMapper::toDto).orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
    }

    @Override
    public InvoiceDtoResponse createInvoice(Order order) {
        Invoice invoice = Invoice.builder()
                .order(order)
                .creatAt(new Date())
                .total(order.getTotal())
                .isPaid(true)
                .build();
        invoice = invoiceDao.createInvoice(invoice);
        return invoiceMapper.toDto(invoice);
    }

    @Override
    public InvoiceDtoResponse updateInvoice(Long id, Boolean isPaid) {
        Invoice invoice = invoiceDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
        invoice.setIsPaid(isPaid);
        invoice = invoiceDao.updateInvoice(invoice);
        System.out.println(invoice.toString());
        return invoiceMapper.toDto(invoice);
    }

    @Override
    public void deleteInvoiceById(Long id) {
        invoiceDao.deleteInvoiceById(id);
    }


    @Override
    public List<InvoiceDtoResponse> findAllInvoices() {
        return invoiceDao.findAllInvoices().stream().map(invoiceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public InvoiceDtoResponse findInvoiceByOrderId(Long orderId) {
        return invoiceDao.findByOrderId(orderId)
                .map(invoiceMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for order id: " + orderId));
    }
}
