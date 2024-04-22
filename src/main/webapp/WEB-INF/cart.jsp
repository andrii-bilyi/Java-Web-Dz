<%@ page import="step.learning.dal.dto.CartItem" %>
<%@ page import="step.learning.model.CartPageModel" %>
<%@ page import="step.learning.dal.dto.Product" %>
<%@ page import="step.learning.dal.dto.Cart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String context = request.getContextPath();
    // Вилучаємо дані, передані сервлетом (контролером)
    // CartItem[] cartItems = (CartItem[]) request.getAttribute("cart");
    CartPageModel model = (CartPageModel) request.getAttribute("model");
%>


<%--<%=model.getCartItems().size()%>--%>
<%--<%=model.getCarts().size()%>--%>
<%--<%=model.getProducts().size()%>--%>
<div class="row">
    <div class="col s8">
        <div class="row">
            <% for(Product product: model.getProducts()) { %>

            <div class="col s6 m4">
                <div class="card small">
                    <div class="card-image">
                        <img src="<%=context%>/img/products/<%=product.getImage() == null ? "no-image.png" : product.getImage()%>" alt="image">
                        <a data-product="<%=product.getId()%>" class="btn-floating halfway-fab waves-effect waves-light red product-cart-btn"><i class="material-icons">shopping_cart</i></a>
                    </div>
                    <div class="card-content">
                        <span class="card-title"><%=product.getName()%></span>
                        <p><%=product.getDescription()%></p>
                        <p>Ціна: <span class=""><%=product.getPrice()%></span> грн</p>
                    </div>
                </div>
            </div>

            <%}%>
        </div>
    </div>
    <div class="col s4">
        <h1>Ваш кошик</h1>
<%--        <div class="row">--%>
<%--            <% for(CartItem item: model.getCartItems()) { %>--%>
<%--                <div class="col s12">--%>
<%--                    <h2 class="header"> </h2>--%>
<%--                    <div class="card horizontal">--%>
<%--                        <div class="card-image flex1">--%>
<%--                            <img src="<%=context%>/img/no-imag.jpg" alt="img">--%>
<%--                        </div>--%>
<%--                        <div class="card-stacked flex3">--%>
<%--                            <div class="card-content">--%>
<%--                                <p><%= item.getProductId()%></p>--%>
<%--                                <p><%= item.getCount()%></p>--%>
<%--                            </div>--%>
<%--                            <div class="card-action">--%>
<%--                                <a href="#">Видалити з кошику</a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            <%}%>--%>
<%--        </div>--%>

        <div class="row">
            <% for(Cart item: model.getCarts()) { %>
            <div class="col s12">
                <h2 class="header"> </h2>
                <div class="card horizontal">
                    <div class="card-image flex1">
                        <img src="<%=context%>/img/products/<%=item.getProduct().getImage() == null ? "no-image.png" : item.getProduct().getImage()%>" alt="image">
                    </div>
                    <div class="card-stacked flex3">
                        <div class="card-content">
                            <h4><%=item.getProduct().getName()%></h4>
                            <p><%=item.getProduct().getDescription()%></p>
                            <p>Ціна: <%=item.getProduct().getPrice()%> грн</p>
                            <p>Кількість: <%=item.getCount()%></p>
                        </div>
                        <div class="card-action">
                            <a href="#">Видалити з кошику</a>
                        </div>
                    </div>
                </div>
            </div>
            <%}%>
        </div>

    </div>
</div>