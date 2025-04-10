<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" th:replace="~{base :: layout(~{::title}, ~{::section})}">
<head>
    <title>Create Invoice</title>
</head>
<body>
<section th:fragment="content">
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">Create New Invoice</h3>
        </div>
        <form th:action="@{/invoices/create}" method="post" th:object="${invoice}">
            <div class="card-body">
                <!-- Basic Information -->
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="invoiceNumber">Invoice Number *</label>
                            <input type="text" class="form-control" id="invoiceNumber"
                                   th:field="*{invoiceNumber}" required>
                            <small class="form-text text-muted">Auto-generated if left blank</small>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="dateOfSupply">Date of Supply *</label>
                            <input type="date" class="form-control" id="dateOfSupply"
                                   th:field="*{dateOfSupply}" required>
                        </div>
                    </div>
                </div>

                <!-- Parties Information -->
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="sellerId">Seller *</label>
                            <select class="form-control select2" id="sellerId" th:field="*{sellerId}" required>
                                <option value="">Select Seller</option>
                                <option th:each="seller : ${sellers}"
                                        th:value="${seller.id}"
                                        th:text="${seller.name} + ' (' + ${seller.gstNumber} + ')'"></option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="buyerId">Buyer *</label>
                            <select class="form-control select2" id="buyerId" th:field="*{buyerId}" required>
                                <option value="">Select Buyer</option>
                                <option th:each="buyer : ${buyers}"
                                        th:value="${buyer.id}"
                                        th:text="${buyer.name} + ' (' + ${buyer.gstNumber} + ')'"></option>
                            </select>
                        </div>
                    </div>
                </div>

                <!-- Invoice Items -->
                <div class="card card-secondary mt-4">
                    <div class="card-header">
                        <h3 class="card-title">Items</h3>
                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" id="addItemBtn">
                                <i class="fas fa-plus"></i> Add Item
                            </button>
                        </div>
                    </div>
                    <div class="card-body p-0">
                        <div class="table-responsive">
                            <table class="table" id="itemsTable">
                                <thead>
                                <tr>
                                    <th width="10%">Sr. No.</th>
                                    <th width="30%">Product *</th>
                                    <th width="15%">HSN Code</th>
                                    <th width="15%">Qty *</th>
                                    <th width="15%">Rate *</th>
                                    <th width="15%">Amount</th>
                                    <th width="5%"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="item-row" th:each="item, stat : *{items}">
                                    <td>
                                        <input type="text" class="form-control form-control-sm serial-number"
                                               th:field="*{items[__${stat.index}__].serialNumber}" required>
                                    </td>
                                    <td>
                                        <select class="form-control form-control-sm product-select"
                                                th:field="*{items[__${stat.index}__].productId}" required>
                                            <option value="">Select Product</option>
                                            <option th:each="product : ${products}"
                                                    th:value="${product.id}"
                                                    th:text="${product.name}"
                                                    th:selected="${product.id == item.productId}"></option>
                                        </select>
                                    </td>
                                    <td class="hsn-code"
                                        th:text="${item.productId != null} ?
                                                       ${#lists.contains(#lists.transform(products, &quot;p.id&quot;), item.productId)} ?
                                                       ${products[#lists.indexOf(#lists.transform(products, &quot;p.id&quot;), item.productId)].hsnCode} : '' : ''">
                                    </td>
                                    <td>
                                        <input type="number" class="form-control form-control-sm quantity"
                                               th:field="*{items[__${stat.index}__].quantity}" step="0.01" min="0.01" required>
                                    </td>
                                    <td>
                                        <input type="number" class="form-control form-control-sm rate"
                                               th:field="*{items[__${stat.index}__].rate}" step="0.01" min="0.01" required>
                                    </td>
                                    <td class="amount"
                                        th:text="${#numbers.formatDecimal(item.amount, 1, 2, 'COMMA')}"></td>
                                    <td>
                                        <button type="button" class="btn btn-sm btn-danger remove-item">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- Tax & Total Information -->
                <div class="row mt-4">
                    <div class="col-md-6">
                        <div class="card card-info">
                            <div class="card-header">
                                <h3 class="card-title">Tax Information</h3>
                            </div>
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="cgstRate">CGST Rate (%)</label>
                                    <input type="number" class="form-control tax-rate" id="cgstRate"
                                           th:field="*{cgstRate}" step="0.01" min="0" max="100">
                                </div>
                                <div class="form-group">
                                    <label for="sgstRate">SGST Rate (%)</label>
                                    <input type="number" class="form-control tax-rate" id="sgstRate"
                                           th:field="*{sgstRate}" step="0.01" min="0" max="100">
                                </div>
                                <div class="form-group">
                                    <label for="igstRate">IGST Rate (%)</label>
                                    <input type="number" class="form-control tax-rate" id="igstRate"
                                           th:field="*{igstRate}" step="0.01" min="0" max="100">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="card card-success">
                            <div class="card-header">
                                <h3 class="card-title">Invoice Summary</h3>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <tr>
                                            <th>Subtotal:</th>
                                            <td class="text-right" th:text="${#numbers.formatCurrency(invoice.totalBeforeTax)}"></td>
                                        </tr>
                                        <tr th:if="${invoice.cgstAmount != null && invoice.cgstAmount > 0}">
                                            <th>CGST @ <span th:text="${invoice.cgstRate}"></span>%:</th>
                                            <td class="text-right" th:text="${#numbers.formatCurrency(invoice.cgstAmount)}"></td>
                                        </tr>
                                        <tr th:if="${invoice.sgstAmount != null && invoice.sgstAmount > 0}">
                                            <th>SGST @ <span th:text="${invoice.sgstRate}"></span>%:</th>
                                            <td class="text-right" th:text="${#numbers.formatCurrency(invoice.sgstAmount)}"></td>
                                        </tr>
                                        <tr th:if="${invoice.igstAmount != null && invoice.igstAmount > 0}">
                                            <th>IGST @ <span th:text="${invoice.igstRate}"></span>%:</th>
                                            <td class="text-right" th:text="${#numbers.formatCurrency(invoice.igstAmount)}"></td>
                                        </tr>
                                        <tr class="bg-light">
                                            <th>Total Amount:</th>
                                            <th class="text-right" th:text="${#numbers.formatCurrency(invoice.totalAfterTax)}"></th>
                                        </tr>
                                    </table>
                                </div>
                                <div class="form-group">
                                    <label>Amount in Words</label>
                                    <textarea class="form-control" th:field="*{amountInWords}" rows="2" readonly></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Additional Information -->
                <div class="form-group mt-4">
                    <label for="termsAndConditions">Terms & Conditions</label>
                    <textarea class="form-control" id="termsAndConditions"
                              th:field="*{termsAndConditions}" rows="3"></textarea>
                </div>
            </div>
            <div class="card-footer">
                <a th:href="@{/invoices}" class="btn btn-default">Cancel</a>
                <button type="submit" class="btn btn-primary float-right">
                    <i class="fas fa-save"></i> Save Invoice
                </button>
            </div>
        </form>
    </div>
</section>

<script th:inline="javascript">
    $(function () {
        // Initialize Select2
        $('.select2').select2({
            theme: 'bootstrap4'
        });

        // Initialize DataTable for items without DataTable features (just styling)
        $('#itemsTable').DataTable({
            "paging": false,
            "searching": false,
            "ordering": false,
            "info": false,
            "responsive": true,
            "autoWidth": false
        });

        // Add item button click handler
        $('#addItemBtn').on('click', function() {
            const rowCount = $('.item-row').length;
            const newRow = `
                <tr class="item-row">
                    <td>
                        <input type="text" class="form-control form-control-sm serial-number"
                               name="items[${rowCount}].serialNumber" required>
                    </td>
                    <td>
                        <select class="form-control form-control-sm product-select"
                                name="items[${rowCount}].productId" required>
                            <option value="">Select Product</option>
                            <option th:each="product : ${products}"
                                    th:value="${product.id}"
                                    th:text="${product.name}"></option>
                        </select>
                    </td>
                    <td class="hsn-code"></td>
                    <td>
                        <input type="number" class="form-control form-control-sm quantity"
                               name="items[${rowCount}].quantity" step="0.01" min="0.01" required>
                    </td>
                    <td>
                        <input type="number" class="form-control form-control-sm rate"
                               name="items[${rowCount}].rate" step="0.01" min="0.01" required>
                    </td>
                    <td class="amount">0.00</td>
                    <td>
                        <button type="button" class="btn btn-sm btn-danger remove-item">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                </tr>`;
            $('#itemsTable tbody').append(newRow);
            initItemRowEvents($('#itemsTable tbody tr.item-row').last());
        });

        // Initialize event listeners for existing rows
        $('.item-row').each(function() {
            initItemRowEvents($(this));
        });

        // Tax rate change handler
        $('.tax-rate').on('change', calculateTotals);

        // Remove item button click handler
        $(document).on('click', '.remove-item', function() {
            $(this).closest('tr').remove();
            calculateTotals();
        });

        function initItemRowEvents(row) {
            row.find('.product-select').on('change', function() {
                const productId = $(this).val();
                if (productId) {
                    // In a real app, you would fetch the HSN code from the selected product
                    row.find('.hsn-code').text('HSN-' + productId);
                } else {
                    row.find('.hsn-code').text('');
                }
                calculateItemAmount(row);
            });

            row.find('.quantity, .rate').on('input', function() {
                calculateItemAmount(row);
            });
        }

        function calculateItemAmount(row) {
            const quantity = parseFloat(row.find('.quantity').val()) || 0;
            const rate = parseFloat(row.find('.rate').val()) || 0;
            const amount = quantity * rate;
            row.find('.amount').text(amount.toFixed(2));
            calculateTotals();
        }

        function calculateTotals() {
            let totalBeforeTax = 0;

            $('.item-row').each(function() {
                const amountText = $(this).find('.amount').text();
                totalBeforeTax += parseFloat(amountText) || 0;
            });

            const cgstRate = parseFloat($('#cgstRate').val()) || 0;
            const sgstRate = parseFloat($('#sgstRate').val()) || 0;
            const igstRate = parseFloat($('#igstRate').val()) || 0;

            const cgstAmount = totalBeforeTax * cgstRate / 100;
            const sgstAmount = totalBeforeTax * sgstRate / 100;
            const igstAmount = totalBeforeTax * igstRate / 100;

            const totalTaxAmount = cgstAmount + sgstAmount + igstAmount;
            const totalAfterTax = totalBeforeTax + totalTaxAmount;

            // Update hidden form fields
            $('input[name="totalBeforeTax"]').val(totalBeforeTax.toFixed(2));
            $('input[name="cgstAmount"]').val(cgstAmount.toFixed(2));
            $('input[name="sgstAmount"]').val(sgstAmount.toFixed(2));
            $('input[name="igstAmount"]').val(igstAmount.toFixed(2));
            $('input[name="totalTaxAmount"]').val(totalTaxAmount.toFixed(2));
            $('input[name="totalAfterTax"]').val(totalAfterTax.toFixed(2));

            // Update amount in words
            $('textarea[name="amountInWords"]').val(numberToWords(totalAfterTax));
        }

        function numberToWords(num) {
            const units = ['', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine'];
            const teens = ['Ten', 'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen', 'Sixteen', 'Seventeen', 'Eighteen', 'Nineteen'];
            const tens = ['', 'Ten', 'Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety'];

            num = Math.floor(num);
            if (num === 0) return 'Zero Rupees Only';

            let words = '';

            if (num >= 10000000) {
                words += numberToWords(Math.floor(num / 10000000)) + ' Crore ';
                num %= 10000000;
            }

            if (num >= 100000) {
                words += numberToWords(Math.floor(num / 100000)) + ' Lakh ';
                num %= 100000;
            }

            if (num >= 1000) {
                words += numberToWords(Math.floor(num / 1000)) + ' Thousand ';
                num %= 1000;
            }

            if (num >= 100) {
                words += units[Math.floor(num / 100)] + ' Hundred ';
                num %= 100;
            }

            if (num > 0) {
                if (words !== '') words += 'and ';

                if (num < 10) {
                    words += units[num];
                } else if (num < 20) {
                    words += teens[num - 10];
                } else {
                    words += tens[Math.floor(num / 10)];
                    if (num % 10 > 0) {
                        words += ' ' + units[num % 10];
                    }
                }
            }

            return words.trim() + ' Rupees Only';
        }
    });
</script>
</body>
</html>