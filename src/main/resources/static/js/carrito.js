document.addEventListener("DOMContentLoaded", function() {

    const carritoItems = document.querySelector(".carrito-items");

    const botonesAgregar = document.querySelectorAll(".boton-item");
    botonesAgregar.forEach(boton => {
        boton.addEventListener("click", function() {
            const item = boton.parentElement;
            const titulo = item.querySelector(".titulo-item").innerText;
            const precio = parseFloat(item.querySelector(".precio-item").innerText.replace("$", ""));

            const nombresItemsCarrito = carritoItems.querySelectorAll(".carrito-item-titulo");
            for (let i = 0; i < nombresItemsCarrito.length; i++) {
                if (nombresItemsCarrito[i].innerText === titulo) {
                    alert("El item ya se encuentra en el carrito");
                    return;
                }
            }

            const nuevoItem = document.createElement("div");
            nuevoItem.classList.add("carrito-item");
            nuevoItem.dataset.precio = precio; // Almacenamos el precio como un atributo de dataset

            nuevoItem.innerHTML = `
                <div class="carrito-item-detalles">
                    <span class="carrito-item-titulo">${titulo}</span>
                    <div class="selector-cantidad">
                        <i class="fa-solid fa-minus restar-cantidad"></i>
                        <input type="text" value="1" class="carrito-item-cantidad" disabled>
                        <i class="fa-solid fa-plus sumar-cantidad"></i>
                    </div>
                    <span class="carrito-item-precio">$${precio.toFixed(2)}</span>
                </div>
                <button class="btn-eliminar">
                    <i class="fa-solid fa-trash"></i>
                </button>
            `;

			
			nuevoItem.querySelector(".btn-eliminar").addEventListener("click", function() {
			                // Eliminar el elemento del DOM al hacer clic en el botón "Eliminar"
			                carritoItems.removeChild(nuevoItem);

			                // Actualizamos el total del carrito
			                actualizarTotalCarrito();
			            });
			
            carritoItems.appendChild(nuevoItem);

            // Actualizamos el total del carrito
            actualizarTotalCarrito();
        });
    });

    // Función para actualizar el total del carrito (ejemplo simple, deberías adaptarlo según tu lógica)
	function actualizarTotalCarrito() {
	    const carritoItems = document.querySelectorAll(".carrito-item");
	    let total = 0;
	    carritoItems.forEach(item => {
	        const precio = parseFloat(item.dataset.precio);
	        total += precio;
	    });

	    const carritoTotal = document.querySelector(".carrito-precio-total");
	    carritoTotal.textContent = `$${total.toFixed(2)}`;
	}
	
		
		

});