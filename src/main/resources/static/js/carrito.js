document.addEventListener("DOMContentLoaded", function() {
    const carritoItems = document.querySelector(".carrito-items");

    const botonesAgregar = document.querySelectorAll(".boton-item");
    botonesAgregar.forEach(boton => {
        boton.addEventListener("click", function() {
            const item = boton.parentElement;
            const titulo = item.querySelector(".titulo-item").innerText;
            const precio = parseFloat(item.querySelector(".precio-item").innerText.replace("$", ""));
            const stockActual = parseInt(boton.dataset.stockactual);

            const nombresItemsCarrito = carritoItems.querySelectorAll(".carrito-item-titulo");
            let cantidadEnCarrito = 0;

            // Calcular la cantidad total de este producto en el carrito
            nombresItemsCarrito.forEach(nombre => {
                if (nombre.innerText === titulo) {
                    const cantidadItem = parseInt(nombre.parentElement.querySelector(".carrito-item-cantidad").value);
                    cantidadEnCarrito += cantidadItem;
                }
            });

            // Verificar si la cantidad en el carrito más la que se desea agregar excede el stock disponible
            if (cantidadEnCarrito >= stockActual) {
                alert(`No se puede agregar más de ${stockActual} unidades de ${titulo}`);
                return;
            }

            for (let i = 0; i < nombresItemsCarrito.length; i++) {
                if (nombresItemsCarrito[i].innerText === titulo) {
                    alert("El item ya se encuentra en el carrito");
                    return;
                }
            }

            const nuevoItem = document.createElement("div");
            nuevoItem.classList.add("carrito-item");
            nuevoItem.dataset.precio = precio;

            nuevoItem.innerHTML = `
                <div class="carrito-item-detalles">
                    <span class="carrito-item-titulo">${titulo}</span>
                    <div class="selector-cantidad">
                        <input type="number" value="1" min="1" max="${stockActual}" class="carrito-item-cantidad">
                    </div>
                    <span class="carrito-item-precio">$${precio.toFixed(2)}</span>
                </div>
                <button class="btn-eliminar">
                    <i class="fa-solid fa-trash"></i>
                </button>
            `;

            nuevoItem.querySelector(".btn-eliminar").addEventListener("click", function() {
                carritoItems.removeChild(nuevoItem);
                actualizarTotalCarrito();
            });

            const cantidadInput = nuevoItem.querySelector(".carrito-item-cantidad");
            cantidadInput.addEventListener("change", function() {
                if (parseInt(cantidadInput.value) > stockActual) {
                    cantidadInput.value = stockActual;
                } else if (parseInt(cantidadInput.value) < 1) {
                    cantidadInput.value = 1;
                }
                actualizarTotalCarrito();
            });

            carritoItems.appendChild(nuevoItem);

            // Actualizamos el total del carrito
            actualizarTotalCarrito();
        });
    });

    function actualizarTotalCarrito() {
        const carritoItems = document.querySelectorAll(".carrito-item");
        let total = 0;
        carritoItems.forEach(item => {
            const precio = parseFloat(item.dataset.precio);
            const cantidad = parseInt(item.querySelector(".carrito-item-cantidad").value);
            total += precio * cantidad;
        });

        const carritoTotal = document.querySelector(".carrito-precio-total");
        carritoTotal.textContent = `$${total.toFixed(2)}`;
    }
});
