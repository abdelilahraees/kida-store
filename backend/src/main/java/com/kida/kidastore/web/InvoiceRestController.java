package com.kida.kidastore.web;

import com.kida.kidastore.dao.InvoiceDao;
import com.kida.kidastore.models.dto.Response.InvoiceDtoResponse;
import com.kida.kidastore.services.IInvoiceService;
import com.kida.kidastore.shared.GlobalResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceRestController {
    private IInvoiceService invoiceService;

    public InvoiceRestController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<InvoiceDtoResponse>>> findAllInvoices() {
        List<InvoiceDtoResponse> response = invoiceService.findAllInvoices();
        return ResponseEntity.status(200).body(new GlobalResponse<>(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<InvoiceDtoResponse>> findInvoiceById(@PathVariable Long id) {
        InvoiceDtoResponse response = invoiceService.findById(id);
        return ResponseEntity.status(200).body(new GlobalResponse<>(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<Void>> deleteInvoiceById(@PathVariable Long id) {
        invoiceService.deleteInvoiceById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<InvoiceDtoResponse>> updateInvoice(@PathVariable Long id, Boolean isPaid) {
        InvoiceDtoResponse response = invoiceService.updateInvoice(id, isPaid);
        return ResponseEntity.status(2011).body(new GlobalResponse<>(response));
    }
}
