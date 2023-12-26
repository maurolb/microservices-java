package mauro.shopping.shopping.services;

import mauro.shopping.shopping.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    public List<Invoice> getInvoiceList();
    public Invoice getInvoiceById(Long id);
    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Invoice invoice);
}
