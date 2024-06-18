document.addEventListener("DOMContentLoaded", function() {

	const carritoItems = document.querySelector(".carrito-items");

	const botonesAgregar = document.querySelectorAll(".boton-item");
	botonesAgregar.forEach(boton => {
		boton.addEventListener("click", function() {
			const item = boton.parentElement;
			const titulo = item.querySelector(".titulo-item").innerText;
			const precio = parseFloat(item.querySelector(".precio-item").innerText.replace("$", ""));
			const stock = parseInt(item.dataset.stock);

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
			if (cantidadEnCarrito + 1 > stock) {
				alert(`No se puede agregar más de {stock} unidades de ${titulo}`);
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
				carritoItems.removeChild(nuevoItem);
				actualizarTotalCarrito();
			});


			const btnSumar = nuevoItem.querySelector(".sumar-cantidad");
			btnSumar.addEventListener("click", function() {
				const cantidadInput = nuevoItem.querySelector(".carrito-item-cantidad");
				let cantidad = parseInt(cantidadInput.value);
				cantidad++;
				cantidadInput.value = cantidad;
				actualizarTotalCarrito();
			});


			const btnRestar = nuevoItem.querySelector(".restar-cantidad");
			btnRestar.addEventListener("click", function() {
				const cantidadInput = nuevoItem.querySelector(".carrito-item-cantidad");
				let cantidad = parseInt(cantidadInput.value);
				if (cantidad > 1) {
					cantidad--;
					cantidadInput.value = cantidad;
					actualizarTotalCarrito();
				} else {
					alert(`La cantidad mínima de ${titulo} en el carrito es 1`);
				}
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