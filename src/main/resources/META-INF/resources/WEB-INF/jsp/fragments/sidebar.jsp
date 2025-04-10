<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<!-- Sidebar Fragment -->
<aside th:fragment="sidebar" class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a th:href="@{/}" class="brand-link">
        <img th:src="@{/image/logo.png}" alt="Invoice System Logo"
             class="brand-image img-circle elevation-3" style="opacity: .8">
        <span class="brand-text font-weight-light">Invoice System</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Dashboard -->
                <li class="nav-item" th:with="isActive=${currentPath == '/'}">
                    <a th:href="@{/}" class="nav-link" th:classappend="${isActive} ? 'active'">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>Dashboard</p>
                    </a>
                </li>

                <!-- Invoices Section -->
                <li class="nav-item" th:classappend="${#strings.startsWith(currentPath, '/invoices')} ? 'menu-open'">
                    <a href="#" class="nav-link" th:classappend="${#strings.startsWith(currentPath, '/invoices')} ? 'active'">
                        <i class="nav-icon fas fa-file-invoice"></i>
                        <p>
                            Invoices
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a th:href="@{/invoices}" class="nav-link"
                               th:classappend="${#strings.startsWith(currentPath, '/invoices') and not #strings.startsWith(currentPath, '/invoices/create')} ? 'active'">
                                <i class="far fa-circle nav-icon"></i>
                                <p>All Invoices</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a th:href="@{/invoices/create}" class="nav-link"
                               th:classappend="${#strings.startsWith(currentPath, '/invoices/create')} ? 'active'">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Create New</p>
                            </a>
                        </li>
                    </ul>
                </li>

                <!-- Companies -->
                <li class="nav-item">
                    <a th:href="@{/companies}" class="nav-link"
                       th:classappend="${#strings.startsWith(currentPath, '/companies')} ? 'active'">
                        <i class="nav-icon fas fa-building"></i>
                        <p>Companies</p>
                    </a>
                </li>

                <!-- Products -->
                <li class="nav-item">
                    <a th:href="@{/products}" class="nav-link"
                       th:classappend="${#strings.startsWith(currentPath, '/products')} ? 'active'">
                        <i class="nav-icon fas fa-boxes"></i>
                        <p>Products</p>
                    </a>
                </li>

                <!-- Reports -->
                <li class="nav-item">
                    <a th:href="@{/reports}" class="nav-link"
                       th:classappend="${#strings.startsWith(currentPath, '/reports')} ? 'active'">
                        <i class="nav-icon fas fa-chart-bar"></i>
                        <p>Reports</p>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</aside>
</body>
</html>