$(document).ready(function() {


    $('.summernote').summernote({
        height: 450,
        lang: "ko-KR",
    });

    const productSelects = $('select[name="product_select"]');
    const salePriceInput = $('#salePrice');
    const regularPriceInput = $('#regularPrice');
    const discountInput = $('#discount'); // ID는 그대로 사용

    function calculateTotalPrice() {
        let totalPrice = 0;
        productSelects.each(function() {
            const selectedOption = $(this).find('option:selected');
            if (selectedOption.length && selectedOption.val()) {
                const price = parseFloat(selectedOption.data('price'));
                if (!isNaN(price)) {
                    totalPrice += price;
                }
            }
        });

        regularPriceInput.val(totalPrice); // 정상가 표시
        calculateDiscountedPrice(totalPrice); // 할인된 가격 계산
    }

    function calculateDiscountedPrice(regularPrice) {
        const discount = parseFloat(discountInput.val()) || 0; // 할인율 가져오기
        const discountAmount = (regularPrice * discount) / 100; // 할인 금액 계산
        const discountedPrice = regularPrice - discountAmount; // 할인된 가격 계산
        salePriceInput.val(discountedPrice); // 판매가 표시
    }

    productSelects.on('change', function() {
        calculateTotalPrice();
    });

    discountInput.on('input', function() {
        const regularPrice = parseFloat(regularPriceInput.val()) || 0; // 정상가 가져오기
        calculateDiscountedPrice(regularPrice); // 할인된 가격 재계산
    });

    $("#productForm").on('submit', function(event) {
        var infoInput = $("#event_info");
        infoInput.val(quill.root.innerHTML);

        var term = $('#term').data('term');
        console.log("term =====" + term);
        var selectedProducts = [];
        for (var i = 1; i <= term; i++) {
            var productId = $('#product_' + i).val();
            if (productId) {
                selectedProducts.push(productId);
            }
        }
        $('#product_id').val(selectedProducts.join(','));
    });
});