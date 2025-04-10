<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" th:replace="~{base :: layout(~{::title}, ~{::section})}">
<head>
    <title>Invoice List</title>
</head>
<body>
<section th:fragment="content">
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">Invoice Management</h3>
            <div class="card-tools">
                <a th:href="@{/invoices/create}" class="btn btn-primary btn-sm">
                    <i class="fas fa-plus"></i> Create Invoice
                </a>
            </div>
        </div>
        <div class="card-body">
            <table id="invoicesTable" class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>Invoice #</th>
                    <th>Date</th>
                    <th>Seller</th>
                    <th>Buyer</th>
                    <th>Total Amount</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr th:each="invoice : ${invoices}">
                    <td th:text="${invoice.invoiceNumber}"></td>
                    <td th:text="${#temporals.format(invoice.dateOfSupply, 'dd-MM-yyyy')}"></td>
                    <td th:text="${invoice.seller.name}"></td>
                    <td th:text="${invoice.buyer.name}"></td>
                    <td class="text-right" th:text="${#numbers.formatCurrency(invoice.totalAfterTax)}"></td>
                    <td>
                                <span class="badge"
                                      th:classappend="${invoice.paid} ? 'bg-success' : 'bg-warning'"
                                      th:text="${invoice.paid} ? 'Paid' : 'Pending'"></span>
                    </td>
                    <td class="text-center">
                        <div class="btn-group">
                            <a th:href="@{/invoices/{id}(id=${invoice.id})}"
                               class="btn btn-sm btn-info" title="View">
                                <i class="fas fa-eye"></i>
                            </a>
                            <a th:href="@{/invoices/{id}/edit(id=${invoice.id})}"
                               class="btn btn-sm btn-primary" title="Edit">
                                <i class="fas fa-edit"></i>
                            </a>
                            <a th:href="@{/invoices/{id}/print(id=${invoice.id})}"
                               class="btn btn-sm btn-secondary" title="Print" target="_blank">
                                <i class="fas fa-print"></i>
                            </a>
                            <form th:action="@{/invoices/{id}/delete(id=${invoice.id})}"
                                  method="post" class="d-inline">
                                <button type="submit" class="btn btn-sm btn-danger"
                                        onclick="return confirm('Are you sure?')"
                                        title="Delete">
                                    <i class="fas fa-trash"></i>
                                </button>
                            </form>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</section>

<script th:inline="javascript">
    $(function () {
        $('#invoicesTable').DataTable({
            "responsive": true,
            "autoWidth": false,
            "order": [[1, "desc"]],
            "language": {
                "paginate": {
                    "previous": "<i class='fas fa-chevron-left'></i>",
                    "next": "<i class='fas fa-chevron-right'></i>"
                }
            }
        });
    });
</script>
</body>
</html>