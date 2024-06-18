document.addEventListener("DOMContentLoaded", function() {
   

const botonesEliminar = document.querySelectorAll(".btn-eliminar");

    botonesEliminar.forEach(boton => {
        boton.addEventListener("click", function() {
            const itemId = boton.dataset.itemId;
            	console.log("Estoy en el boton de borraaarr");
            // Realizar una solicitud POST para eliminar el ítem del carrito
            fetch(`/carrito/eliminar/${itemId}`, {
                method: 'POST'
            })
            .then(response => {
                if (response.ok) {
                    // Actualizar la vista del carrito o hacer algo después de eliminar
                    location.reload(); // Recargar la página o actualizar el contenido del carrito
                } else {
                    console.error('Error al eliminar el ítem del carrito');
                }
            })
            .catch(error => {
                console.error('Error de red:', error);
            });
        });
    });


 const botonesAgregar = document.querySelectorAll(".boton-item");
    botonesAgregar.forEach(boton => {
        boton.addEventListener("click", function() {
            const productoId = boton.dataset.productoId;
            const cantidadInput = boton.previousElementSibling; 
            const cantidad = cantidadInput.value;
		
		
            fetch('/carrito/agregar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    'productoId': productoId,
                    'cantidad': cantidad
                })
            })
            .then(response => response.text())
            .then(data => {
                // Aquí puedes manejar la respuesta del servidor
                console.log('Producto agregado:', data);
                actualizarCarrito();
            })
            .catch(error => {
                console.error('Error al agregar el producto:', error);
            });
        });
    });

    function actualizarCarrito() {
        fetch('/carrito')
            .then(response => response.text())
            .then(html => {
                const parser = new DOMParser();
                const doc = parser.parseFromString(html, 'text/html');
                const carritoItems = doc.querySelector('.carrito-items').innerHTML;
                document.querySelector('.carrito-items').innerHTML = carritoItems;
                actualizarTotalCarrito();
            })
            .catch(error => {
                console.error('Error al actualizar el carrito:', error);
            });
    }

    function actualizarTotalCarrito() {
        const carritoItems = document.querySelectorAll(".carrito-item");
        let total = 0;
        carritoItems.forEach(item => {
            const precio = parseFloat(item.querySelector(".carrito-item-precio").textContent.replace("$", ""));
            const cantidad = parseInt(item.querySelector(".carrito-item-cantidad").textContent);
            total += precio * cantidad;
        });

        const carritoTotal = document.querySelector(".carrito-precio-total");
        carritoTotal.textContent = `$${total.toFixed(2)}`;
    }
});