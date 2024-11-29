$(document).ready(function () {


    $('.summernote').summernote({
        height: 450,
        lang: "ko-KR",
    });

    // 대분류와 중분류 동적 변경
    const subCategories = {
        1: [
            { name: '티백', value: 1 },
            { name: '잎차', value: 2 },
            { name: '디카페인', value: 3 }
        ],
        2: [
            { name: '싱글오리진', value: 4 },
            { name: '블렌드', value: 5 },
            { name: '디카페인', value: 6 }
        ],
        3: [
            { name: '드립주전자', value: 7 },
            { name: '전기포트', value: 8 },
            { name: '핸드드립세트', value: 9 },
            { name: '다기세트', value: 10 }
        ],
        4: [
            { name: '커피머신', value: 11 },
            { name: '그라인더', value: 12 },
            { name: '커피메이커', value: 13 },
            { name: '핸드드립 세트', value: 14 }
        ]
    };

    $('#category').on('change', function () {
        const selectedValue = $(this).val();
        const $subCategorySelect = $('#subCategory');

        // 중분류 선택 초기화
        $subCategorySelect.empty();
        $subCategorySelect.append('<option value="" disabled selected>중분류를 선택하세요</option>');

        if (subCategories[selectedValue]) {
            subCategories[selectedValue].forEach(function (subCategory) {
                $subCategorySelect.append(
                    $('<option>', {
                        value: subCategory.value,
                        text: subCategory.name
                    })
                );
            });
        }
    });

    // 정가, 할인율, 판매가 계산
    const $regularPriceInput = $('#regularPrice');
    const $discountPercentInput = $('#discountPercent');
    const $salePriceInput = $('#salePrice');

    function calculateSalePrice() {
        const regularPrice = parseFloat($regularPriceInput.val());
        const discountPercent = parseFloat($discountPercentInput.val());

        if (!isNaN(regularPrice) && !isNaN(discountPercent)) {
            const discountAmount = (regularPrice * discountPercent) / 100;
            const salePrice = regularPrice - discountAmount;
            $salePriceInput.val(Math.floor(salePrice));
        } else {
            $salePriceInput.val('');
        }
    }

    $regularPriceInput.on('input', calculateSalePrice);
    $discountPercentInput.on('input', calculateSalePrice);
});