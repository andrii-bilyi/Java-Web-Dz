<%@ page import="step.learning.dal.dto.ActionItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String context = request.getContextPath();
    ActionItem[] actionItems = (ActionItem[]) request.getAttribute("actions");
%>
<h1>Акційні пропозиції</h1>
<%-- Відображаємо данні--%>
<% for(ActionItem item: actionItems) { %>
<div class="col s12 m7">
<%--    <h2 class="header"><%= item.getType() %>  <%= item.getBrand() %></h2>--%>
    <div class="card horizontal">
        <div class="card-image">
            <img src="<%=context%>/img/no-imag.jpg" alt="img">
        </div>
        <div class="card-stacked">
            <div class="card-content">
                <h4><%= item.getType() %> <b><%= item.getBrand() %> <%= item.getName() %></b> </h4>
                <h3><span class="strike"><%= item.getOldPrice() %></span> <span class="red-text"><b><%= item.getNewPrice() %></b> ₴</span></h3>
            </div>
            <div class="card-action">
                <a href="#">Купити</a>
            </div>
        </div>
    </div>
</div>
<%}%>