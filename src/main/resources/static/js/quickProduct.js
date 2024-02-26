const productViews = document.querySelectorAll('.quick-view');

productViews.forEach(productView => {
    productView.addEventListener('click', (e) => {
        e.preventDefault();
        const productModal = document.getElementById(`product-modal-${productView.dataset.productId}`);
        productModal.style.display = 'block';
        // fetch product data using productView.dataset.productId
        // and populate product-modal-title, product-modal-description, product-modal-price
    });
});

document.addEventListener('click', (e) => {
    if (e.target.matches('.product-modal-close')) {
        e.target.closest('.product-modal').style.display = 'none';
    }
});