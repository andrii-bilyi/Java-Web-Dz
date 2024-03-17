
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>Форма додавання товару</h1>
<div class="row">
    <form class="col s12" method="post">
        <div class="row">
            <div class="input-field col s6">
                <i class="material-icons prefix">inventory</i>
                <input id="icon_product" type="text" name="product-name">
                <label for="icon_product">Товар</label>
                <span class="helper-text"
                      data-error="Це необхідне поле"
                      data-success="Правильно">Назва товару</span>
            </div>
            <div class="input-field col s6">
                <i class="material-icons prefix">branding_watermark</i>
                <input  id="icon_brand" type="text"  name="product-brand">
                <label for="icon_brand">Бренд</label>
                <span class="helper-text"
                      data-error="Це необхідне поле"
                      data-success="Правильно">Бренд товару</span>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s6">
                <i class="material-icons prefix">category</i>
                <input id="icon_category" type="text" name="product-category">
                <label for="icon_category">Категорія</label>
                <span class="helper-text"
                      data-error="Це необхідне поле"
                      data-success="Правильно">Категорія товару</span>
            </div>
            <div class="input-field col s6">
                <i class="material-icons prefix">merge_type</i>
                <input  id="icon_type" type="text"  name="product-type">
                <label for="icon_type">Тип</label>
                <span class="helper-text"
                      data-error="Це необхідне поле"
                      data-success="Правильно">Тип товару</span>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s8">
                <textarea id="textarea2" class="materialize-textarea" data-length="120" name="product-description"></textarea>
                <label for="textarea2">Опис товару</label>
            </div>
            <div class="input-field col s4">
                <i class="material-icons prefix">request_quote</i>
                <input  id="icon_price" type="number"  name="product-price">
                <label for="icon_price">Ціна</label>
                <span class="helper-text"
                      data-error="Це необхідне поле"
                      data-success="Правильно">Ціна товару</span>
            </div>
        </div>
        <div class="row">
            <div class="file-field input-field col s6">
                <div class="btn light-green darken-2">
                    <i class="material-icons">photo</i>
                    <input type="file" name="product-photo">
                </div>
                <div class="file-path-wrapper">
                    <label>
                        <input class="file-path validate" type="text" placeholder="Фото">
                    </label>
                </div>
            </div>
            <div class="input-field col s4">
                <i class="material-icons prefix">123</i>
                <input  id="icon_count" type="number"  name="product-count">
                <label for="icon_count">Кількість</label>
                <span class="helper-text"
                      data-error="Це необхідне поле"
                      data-success="Правильно">Кількість товару</span>
            </div>
            <div class="input-field col s2">
                <button type="button" id="new-product-button" class="btn light-green darken-2 right"><i class="material-icons left">task_alt</i>Зберігти</button>
            </div>
        </div>
    </form>
</div>
