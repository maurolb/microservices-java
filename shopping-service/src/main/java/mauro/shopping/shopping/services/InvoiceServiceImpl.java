package mauro.shopping.shopping.services;

import lombok.extern.slf4j.Slf4j;
import mauro.shopping.shopping.clients.CustomerClient;
import mauro.shopping.shopping.clients.ProductClient;
import mauro.shopping.shopping.entities.Invoice;
import mauro.shopping.shopping.entities.InvoiceItem;
import mauro.shopping.shopping.models.Customer;
import mauro.shopping.shopping.models.Product;
import mauro.shopping.shopping.repositories.InvoiceItemsRepository;
import mauro.shopping.shopping.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService{
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceItemsRepository invoiceItemsRepository;
    @Autowired
    private CustomerClient customerClient;
    @Autowired
    private ProductClient productClient;

    @Override
    public List<Invoice> getInvoiceList() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if(invoice != null){
            Customer customer = customerClient.getCustomerById(invoice.getCustomerId()).getBody();
            invoice.setCustomer(customer);
            List<InvoiceItem> itemList = invoice.getItems().stream().map(invoiceItem -> {
                Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
                invoiceItem.setProduct(product);
                return invoiceItem;
            }).collect(Collectors.toList());
            invoice.setItems(itemList);
        }
        return invoice;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
        if (invoiceDB !=null){
            return  invoiceDB;
        }
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        invoiceDB.getItems().forEach(invoiceItem -> {
            productClient.updateStock(invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
        });
        return invoiceDB;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoiceById(invoice.getId());
        if (invoiceDB == null){
            return null;
        }
        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoiceById(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }
}
