function setBrands() {
    getData(function(data) {
        var brands = document.getElementById("brand");
        //Lọc
        //lấy các kiểu sp
        var getBrands = data.map((data) => {
            return data.brand;
        });

        //triệt tiêu các loại giống nhau
        const uniqueSet = new Set(getBrands);
        const setBrand = [...uniqueSet];

        for (const x of setBrand) {
            brands.options[brands.options.length] = new Option(x);
        }
    })
}
setBrands()

function setProducts() {
    var products = document.getElementById("product");

    getData(function(data) {
        //Lấy ra các sản phẩm cùng loại
        var product = data.filter(function(data, index) {
            return data.brand === document.getElementById('brand').value;
        })
        var getProducts = product.map(function(item, index) {
            return item.title;
        })

        //Xóa toàn bộ các lựa chọn sản phẩm cũ
        let e = document.querySelector("select#product");
        let child = e.firstElementChild;
        while (child) {
            e.removeChild(child);
            child = e.lastElementChild;
        }

        //Thêm sản phẩm tương ứng loại
        products.options[0] = new Option('Chọn SP')
        for (const k of getProducts) {
            products.options[products.options.length] = new Option(k);
        }
    })
}

function getData(callback) {
    fetch(callback)
        .then((res) => res.json())
        .then(callback)
}

function getSelectValue() {
    setProducts();
}