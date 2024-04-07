<h1>Додати новий товар</h1>

<form method="post" id="add-product-form">
    <div class="row">
        <div class="col s12">
            <div class="row">

                <div class="input-field col s12 m6">
                    <input id="product-name" name="product-name" type="text">
                    <label for="product-name">Назва товару</label>
                    <span class="helper-text"
                          data-error="Назва товару повинна містити щонайменше 3 символи"
                          data-success="Правильно">Використовуйте лише літери</span>
                </div>

                <div class="input-field col s12 m6">
                    <input id="product-price" name="product-price" type="number" class="validate">
                    <label for="product-price">Ціна товару</label>
                    <span class="helper-text"
                          data-error="Ціна товару повинна бути позитивним числом"
                          data-success="Правильно">Використовуйте лише цифри</span>
                </div>

                <div class="input-field col s12 m12">
                    <textarea id="product-description" name="product-description" class="materialize-textarea"></textarea>
                    <label for="product-description">Опис товару</label>
                    <span class="helper-text"
                          data-error="Опис товару повинен містити щонайменше 10 символів"
                          data-success="Правильно">Використовуйте щонайменше 10 символів</span>
                </div>

                <div class="file-field input-field col s12 m12">
                    <div class="btn light-green darken-2">
                        <span>Зображення товару</span>
                        <input id="product-img" name="product-img" type="file">
                    </div>
                    <div class="file-path-wrapper">
                        <input id="product-img-path" class="file-path validate">
                        <span class="helper-text"
                              data-error="Використовуйте JPEG, PNG, WEBP або GIF"
                              data-success="Правильно">Використовуйте JPEG, PNG, WEBP або GIF</span>
                    </div>
                </div>

            </div>

        </div>

    </div>
    <div class="row">
        <div class="col s12 m12 center">
            <button type="button" class="btn light-green darken-2" id="add-product-button">Зберегти</button>
            <div id="add-product-result"></div>
        </div>
    </div>
</form>