document.addEventListener( 'DOMContentLoaded', () => {
    // шукаємо кнопку реєстрації, якщо знаходимо - додаємо обробник
    const signupButton = document.getElementById("signup-button");
    if(signupButton) { signupButton.onclick = signupButtonClick; }
    // шукаємо кнопку автентифікації, якщо знаходимо - додаємо обробник
    const authButton = document.getElementById("auth-button");
    if(authButton) { authButton.onclick = authButtonClick; }
    // шукаємо кнопку збереження товару, якщо знаходимо - додаємо обробник
    const newProductButton = document.getElementById("new-product-button");
    if(newProductButton) { newProductButton.onclick = newProductButtonClick; }
    // налаштування модальних вікон
    var elems = document.querySelectorAll('.modal');
    M.Modal.init(elems, {
        "opacity": 	    	0.5, 	// Opacity of the modal overlay.
        "inDuration": 		250, 	// Transition in duration in milliseconds.
        "outDuration": 		250, 	// Transition out duration in milliseconds.
        "onOpenStart": 		null,	// Callback function called before modal is opened.
        "onOpenEnd": 		null,	// Callback function called after modal is opened.
        "onCloseStart":		null,	// Callback function called before modal is closed.
        "onCloseEnd": 		null,	// Callback function called after modal is closed.
        "preventScrolling": true,	// Prevent page from scrolling while modal is open.
        "dismissible": 		true,	// Allow modal to be dismissed by keyboard or overlay click.
        "startingTop": 		'4%',	// Starting top offset
        "endingTop": 		'10%'	// Ending top offset
    });
    checkAuth();
});

function getContext(){
    return window.location.pathname.split('/')[1] ;
}
function authButtonClick(e) {
    const emailInput = document.querySelector('input[name="auth-email"]');
    if( ! emailInput ) { throw "'auth-email' not found" ; }
    const passwordInput = document.querySelector('input[name="auth-password"]');
    if( ! passwordInput ) { throw "'auth-password' not found" ; }

    fetch(`/${getContext()}/auth?email=${emailInput.value}&password=${passwordInput.value}`, {
        method: 'GET'
    })
        .then( r => r.json() )
        .then(j => {
            if(j.data == null || typeof j.data.token == "undefined"){
                document.getElementById("modal-auth-message").innerText = "У вході відмовлено";
            }
            else {
                //авторизація токенами передбачає їх збереження з метою подальшого використання
                //Для того щоб токени були доступні після перезавантаження їх вміщують
                //до постійного сховища браузера - localStorage ...
                localStorage.setItem("auth-token", j.data.token);
                window.location.reload();
            }
        })
    //.then( console.log ) ;
}
function checkAuth() {
    // ... при завантаженні сторінки перевіряємо наявність даних автентифікації у localStorage
    const  authToken = localStorage.getItem("auth-token");
    if (authToken){
        //перевіряємо токен на валідність і одержуємо дані про користувача
        fetch(`/${getContext()}/auth?token=${authToken}`, {
            method: 'POST'
        })
            .then( r => r.json() )
            .then(console.log);
    }
}
function newProductButtonClick(e){
    // шукаємо форму - батьківській елемент кнопки (e.target)
    const productForm = e.target.closest('form') ;
    if( ! productForm ) {
        throw "Product form not found" ;
    }
    // всередині форми productForm знаходимо елементи
    const nameInput = productForm.querySelector('input[name="product-name"]');
    if( ! nameInput ) { throw "nameInput not found" ; }
    const brandInput = productForm.querySelector('input[name="product-brand"]');
    if( ! brandInput ) { throw "brandInput not found" ; }
    const categoryInput = productForm.querySelector('input[name="product-category"]');
    if( ! categoryInput ) { throw "categoryInput not found" ; }
    const typeInput = productForm.querySelector('input[name="product-type"]');
    if( ! typeInput ) { throw "typeInput not found" ; }
    const descriptionInput = productForm.querySelector('textarea[name="product-description"]');
    if( ! descriptionInput ) { throw "descriptionInput not found" ; }
    const photoInput = productForm.querySelector('input[name="product-photo"]');
    if( ! photoInput ) { throw "photoInput not found" ; }
    const countInput = productForm.querySelector('input[name="product-count"]');
    if( ! countInput ) { throw "photoInput not found" ; }
    const priceInput = productForm.querySelector('input[name="product-price"]');
    if( ! priceInput ) { throw "photoInput not found" ; }
    /// Валідація даних
    let isFormValid = true ;

    if( nameInput.value == "" ) {
        nameInput.classList.remove("valid");
        nameInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        nameInput.classList.remove("invalid");
        nameInput.classList.add("valid");
    }

    if( brandInput.value == "" ) {
        brandInput.classList.remove("valid");
        brandInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        brandInput.classList.remove("invalid");
        brandInput.classList.add("valid");
    }

    if( categoryInput.value == "" ) {
        categoryInput.classList.remove("valid");
        categoryInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        categoryInput.classList.remove("invalid");
        categoryInput.classList.add("valid");
    }

    if( typeInput.value == "" ) {
        typeInput.classList.remove("valid");
        typeInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        typeInput.classList.remove("invalid");
        typeInput.classList.add("valid");
    }

    if( photoInput.value == "" ) {
        photoInput.classList.remove("valid");
        photoInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        photoInput.classList.remove("invalid");
        photoInput.classList.add("valid");
    }

    if( ! isFormValid ) return ;
    /// кінець валідації

    // формуємо дані для передачі на бекенд
    const formData = new FormData() ;
    formData.append( "product-name", nameInput.value ) ;
    formData.append( "product-brand", brandInput.value ) ;
    formData.append( "product-category", categoryInput.value ) ;
    formData.append( "product-type", typeInput.value ) ;
    formData.append( "product-description", descriptionInput.value ) ;
    formData.append( "product-count", countInput.value ) ;
    formData.append( "product-price", priceInput.value ) ;

    if( photoInput.files.length > 0 ) {
        formData.append( "product-photo", photoInput.files[0] ) ;
    }

    // передаємо - формуємо запит
    fetch( window.location.href, { method: 'POST', body: formData } )
        .then( r => r.json() )
        .then( j => {
            console.log(j);
            // if( j.status == 1 ) {  // реєстрація успішна
            //     alert( 'реєстрація успішна' ) ;
            //     window.location = '/' ;  // переходимо на головну сторінку
            // }
            // else {  // помилка реєстрації (повідомлення - у полі message)
            //     alert( j.data.message ) ;
            // }
        } ) ;
}
function signupButtonClick(e) {
    // шукаємо форму - батьківській елемент кнопки (e.target)
    const signupForm = e.target.closest('form') ;
    if( ! signupForm ) {
        throw "Signup form not found" ;
    }
    // всередині форми signupForm знаходимо елементи
    const nameInput = signupForm.querySelector('input[name="user-name"]');
    if( ! nameInput ) { throw "nameInput not found" ; }
    const emailInput = signupForm.querySelector('input[name="user-email"]');
    if( ! emailInput ) { throw "emailInput not found" ; }
    const passwordInput = signupForm.querySelector('input[name="user-password"]');
    if( ! passwordInput ) { throw "passwordInput not found" ; }
    const repeatInput = signupForm.querySelector('input[name="user-repeat"]');
    if( ! repeatInput ) { throw "repeatInput not found" ; }
    const avatarInput = signupForm.querySelector('input[name="user-avatar"]');
    if( ! avatarInput ) { throw "avatarInput not found" ; }

    /// Валідація даних
    let isFormValid = true ;

    if( nameInput.value == "" ) {
        nameInput.classList.remove("valid");
        nameInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        nameInput.classList.remove("invalid");
        nameInput.classList.add("valid");
    }
    if( emailInput.value == "" ) {
        emailInput.classList.remove("valid");
        emailInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        emailInput.classList.remove("invalid");
        emailInput.classList.add("valid");
    }
    if( passwordInput.value == "" ) {
        passwordInput.classList.remove("valid");
        passwordInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        passwordInput.classList.remove("invalid");
        passwordInput.classList.add("valid");
    }
    if( passwordInput.value == "" ) {
        passwordInput.classList.remove("valid");
        passwordInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        passwordInput.classList.remove("invalid");
        passwordInput.classList.add("valid");
    }
    if( repeatInput.value != passwordInput.value ) {
        repeatInput.classList.remove("valid");
        repeatInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        repeatInput.classList.remove("invalid");
        repeatInput.classList.add("valid");
    }

    if( ! isFormValid ) return ;
    /// кінець валідації

    // формуємо дані для передачі на бекенд
    const formData = new FormData() ;
    formData.append( "user-name", nameInput.value ) ;
    formData.append( "user-email", emailInput.value ) ;
    formData.append( "user-password", passwordInput.value ) ;
    if( avatarInput.files.length > 0 ) {
        formData.append( "user-avatar", avatarInput.files[0] ) ;
    }

    // передаємо - формуємо запит
    fetch( window.location.href, { method: 'POST', body: formData } )
        .then( r => r.json() )
        .then( j => {
            //console.log(j);
            if(j.meta.status === "success"){// реєстрація успішна
                //confirm(j.message);
                alert("Pеєстрація успішна");

                // Перенаправлення на домашню сторінку

                //window.location.href = window.location.href.replace("/signup", "");
                window.location = `/${getContext()}/` ;  // переходимо на головну сторінку
            }
            else { // помилка реєстрації (повідомлення - у полі message)
                console.log(j);
                alert( j.meta.message ) ;
                // Перезапускаем текущую страницу
                //window.location.reload();
            }
            // if( j.status == 1 ) {  // реєстрація успішна
            //     alert( 'реєстрація успішна' ) ;
            //     window.location = '/' ;  // переходимо на головну сторінку
            // }
            // else {  // помилка реєстрації (повідомлення - у полі message)
            //     alert( j.data.message ) ;
            // }
        } ) ;
}


