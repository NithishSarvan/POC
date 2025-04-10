<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" th:replace="~{base :: layout(~{::title}, ~{::section})}">
<head>
    <title>Edit Invoice</title>
</head>
<body>
<section th:fragment="content">
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">
                Edit Invoice <span th:text="'#' + ${invoice.invoiceNumber}"></span>
                <small class="text-muted" th:text="'Created: ' + ${#temporals.format(invoice.createdAt, 'dd MMM yyyy')}"></small>
            </h3>
        </div>

        <!-- Include shared form fragment -->
        <div th:replace="~{fragments/invoice-form :: invoice-form('/invoices/' + ${invoice.id} + '/edit', true)}"></div>

        <!-- Delete confirmation modal -->
        <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Confirm Deletion</h5>
                        <button type="button" class="close" data-dismiss="modal">
                            <span>&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to delete this invoice? This action cannot be undone.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <form th:action="@{'/invoices/' + ${invoice.id} + '/delete'}" method="post">
                            <button type="submit" class="btn btn-danger">Delete Permanently</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script th:inline="javascript">
    $(function () {
        // Initialize all plugins
        $('.select2').select2({ theme: 'bootstrap4' });

        // Calculate totals when page loads
        calculateTotals();

        // Delete confirmation
        window.confirmDelete = function() {
            $('#deleteModal').modal('show');
        };
    });
</script>
</body>
</html>