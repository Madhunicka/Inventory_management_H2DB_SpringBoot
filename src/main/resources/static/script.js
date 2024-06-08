var selectedProductId = null;
var selectedProduct = null;

document.getElementById('price').addEventListener('input', function () {
    const priceError = document.getElementById('priceError');
    if (this.value < 0) {
        priceError.textContent = 'Price cannot be negative';
        this.value = '';
    } else {
        priceError.textContent = '';
    }
});

document.getElementById('inv').addEventListener('input', function () {
    const invError = document.getElementById('invError');
    if (this.value < 0) {
        invError.textContent = 'Inventory cannot be negative';
        this.value = '';
    } else {
        invError.textContent = '';
    }
});
document.getElementById('outsourcedPrice').addEventListener('input', function () {
    const priceError = document.getElementById('outsourcedPriceError');
    if (this.value < 0) {
        priceError.textContent = 'Price cannot be negative';
        this.value = '';
    } else {
        priceError.textContent = '';
    }
});

document.getElementById('outsourcedInv').addEventListener('input', function () {
    const invError = document.getElementById('outsourcedInvError');
    if (this.value < 0) {
        invError.textContent = 'Inventory cannot be negative';
        this.value = '';
    } else {
        invError.textContent = '';
    }
});

 function filterPartsTable() {
     const filter = document.getElementById('filterParts').value.toUpperCase();
     const table = document.getElementById('partsTable');
     const trs = table.getElementsByTagName('tr');
     for (let i = 1; i < trs.length; i++) {
         const tds = trs[i].getElementsByTagName('td');
         let display = false;
         for (let j = 0; j < tds.length; j++) {
             if (tds[j].textContent.toUpperCase().includes(filter)) {
                 display = true;
                 break;
             }
         }
         trs[i].style.display = display ? '' : 'none';
     }
 }

 function filterProductsTable() {
     const filter = document.getElementById('filterProducts').value.toUpperCase();
     const table = document.getElementById('productsTable');
     const trs = table.getElementsByTagName('tr');
     for (let i = 1; i < trs.length; i++) {
         const tds = trs[i].getElementsByTagName('td');
         let display = false;
         for (let j = 0; j < tds.length; j++) {
             if (tds[j].textContent.toUpperCase().includes(filter)) {
                 display = true;
                 break;
             }
         }
         trs[i].style.display = display ? '' : 'none';
     }
 }

 function clearPartsFilter() {
     document.getElementById('filterParts').value = '';
     filterPartsTable();
 }

 function clearProductsFilter() {
     document.getElementById('filterProducts').value = '';
     filterProductsTable();
 }

// Show add in-house part popup
document.querySelector('#addIn-housePart').addEventListener('click', function() {
    document.querySelector('#addInhousePartPopup').style.display = 'block';
});
//
//// Show add outsourced part popup
document.querySelector('#addOutsourcedPart').addEventListener('click', function() {
    document.querySelector('#addOutsourcedPartPopup').style.display = 'block';
});

// Show add product popup
document.querySelector('#addProduct').addEventListener('click', function() {
    setProductId(null);
renderAvailablePartsTable();
    document.querySelector('#addProductPopup').style.display = 'block';
});

// Hide all popups when clicking outside
window.addEventListener('click', function(event) {
    if (event.target.classList.contains('popup')) {
        event.target.style.display = 'none';
    }
});

// Prevent closing popup when clicking inside
document.querySelectorAll('.popup form').forEach(function(form) {
    form.addEventListener('click', function(event) {
        event.stopPropagation();
    });
});
function openPopup(popupId) {
    if(popupId=='addProductPopup'){
        setProductId(null)
    }

    document.getElementById(popupId).style.display = 'block';
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('overlay').style.zIndex = '1'; // Ensure overlay is behind the popup
        document.getElementById(popupId).style.zIndex = '2'; // Ensure popup is in front
}

function closePopup(popupId) {
    document.getElementById(popupId).style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
    setProductId(null);
}

function closeAllPopups() {
    document.querySelectorAll('.popup').forEach(function(popup) {
        popup.style.display = 'none';
    });
    document.getElementById('overlay').style.display = 'none';
}
async function fetchParts() {
    try {
        const response = await fetch('http://localhost:8080/parts');
        if (!response.ok) {
            throw new Error('Failed to fetch parts');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching parts:', error);
        return [];
    }
}


async function renderParts() {
console.log("patyd")
    const partsTable = document.getElementById('partsTable');
    const parts = await fetchParts();
    console.log(parts);
    const body = partsTable.querySelector('tbody');
    body.innerHTML = ''; // Clear existing table rows
    parts.forEach(part => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${part.name}</td>
            <td>${part.price}</td>
            <td>${part.inv}</td>
            <td class="action-buttons">
                <button class="button" onclick="showPopup(${part.id}, '${part.name}', ${part.price}, ${part.inv})">Update</button>
                <button class="button" onclick="deletePart(${part.id})">Delete</button>
            </td>
        `;
        body.appendChild(row);
    });
}

async function renderAvailablePartsTable() {
    const availablePartsTable = document.getElementById('availablePartsTable');
    const availablePartsBody = document.getElementById('availablePartsBody');

    // Clear existing table rows
    availablePartsBody.innerHTML = '';

    try {
        // Fetch parts data
        let parts = await fetchParts();

        if (!parts.length) {
            const row = document.createElement('tr');
            row.innerHTML = '<td colspan="4">No parts available</td>';
            availablePartsBody.appendChild(row);
            return;
        }



        if(getProductId() === 'null') {
            const row = document.createElement('tr');
            row.innerHTML = '<td colspan="4">Please select a product or create new product to associate parts with</td>';
            availablePartsBody.appendChild(row);
            return;
        }

        console.log(getProductId())

        const selectedPart = await getProductById(getProductId());



        document.getElementById('addProductForm').name.value = selectedPart.name;
        document.getElementById('addProductForm').price.value = selectedPart.price;
        document.getElementById('addProductForm').inv.value = selectedPart.inv;


        console.log(selectedPart);

        // Filter out parts that are already associated with the selected product

        parts = parts.filter(part => {
            return !selectedPart.parts.some(associatedPart => associatedPart.id === part.id);
        });


        console.log(parts);

        // Populate table with parts data
        parts.forEach(part => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${part.name}</td>
                <td>${part.price}</td>
                <td>${part.inv}</td>
                <td>
                    <button class="button" onclick="associatePartWithProduct(${part.id})">Associate</button>
                </td>
            `;
            availablePartsBody.appendChild(row);
        });

    } catch (error) {
        console.error('Error rendering available parts table:', error);
    }
}

async function renderAssociatedPartsTable(productId) {
    const associatedPartsTable = document.getElementById('associatedPartsTable');
    const associatedPartsBody = document.getElementById('associatedPartsBody');
    console.log(productId);
    // Clear existing table rows
    associatedPartsBody.innerHTML = '';

    try {
        // Fetch associated parts data
        const response = await fetch(`http://localhost:8080/products/${productId}/parts`);
        if (!response.ok) {
            throw new Error('Failed to fetch associated parts');
        }
        const parts = await response.json();

        // Populate table with associated parts data
        parts.forEach(part => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${part.name}</td>
                <td>${part.price}</td>
                <td>${part.inv}</td>
                <td>
                    <button class="button" onclick="disassociatePartWithProduct(${part.id})">Dissociate</button>
                </td>
            `;
            associatedPartsBody.appendChild(row);
        });

    } catch (error) {

        console.error('Error rendering associated parts table:', error);

        }

    }

// Call the function to render the available parts table
//renderAvailablePartsTable();
async function showPopup(id)
 {
    try {
        // Fetch the part details from the server
        const response = await fetch(`http://localhost:8080/parts/${id}`);
        if (!response.ok) {
            throw new Error('Failed to fetch part details');
        }
        const part = await response.json();

        // Populate the form fields with the part details
        document.getElementById('updatePartName').value = part.name;
        document.getElementById('updatePartPrice').value = part.price;
        document.getElementById('updatePartInventory').value = part.inv;
        document.getElementById('updatePartId').value = part.id;

        // Show the popup
        openPopup('updatePartPopup');
    } catch (error) {
        console.error('Error fetching part details:', error);
    }
}
async function submitUpdatePartForm(event) {
    event.preventDefault();

    const id = document.getElementById('updatePartId').value;
    const name = document.getElementById('updatePartName').value;
    const price = parseFloat(document.getElementById('updatePartPrice').value);
    const inventory = parseInt(document.getElementById('updatePartInventory').value);

    const updatedPart = { id, name, price, inv: inventory };

    try {
        const response = await fetch(`http://localhost:8080/parts/update/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedPart)
        });

        if (response.ok) {
            console.log('Part updated successfully');
            closePopup('updatePartPopup');
            renderParts(); // Refresh the parts table
        } else {
            console.error('Error updating part:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}



async function deletePart(id) {
    try {
        const response = await fetch(`http://localhost:8080/parts/delete/${id}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            console.log('Part deleted successfully');
            renderParts(); // Refresh the parts table
        } else {
            console.error('Error deleting part:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

async function fetchProducts() {
    try {
        const response = await fetch('http://localhost:8080/products');
        if (!response.ok) {
            throw new Error('Failed to fetch products');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching products:', error);
        return [];
    }
}

async function renderProducts() {
    const productsTable = document.getElementById('productsTable');
    let products = await fetchProducts();
    console.log(products);
    //remove products with no id
    productswithoutid = products.filter(product => !product.id);

    products = products.filter(product => product.id);


    //for await
    for (const product of productswithoutid) {
        const response = await getProductById(product);
        products.push(response);
    }


    const body = productsTable.querySelector('tbody');
    body.innerHTML = ''; // Clear existing table rows
    products.forEach(product => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.inv}</td>
            <td class="action-buttons">
                <button class="button" onclick="productUpdateInit(${product.id})">Update</button>
                 <button class="button" onclick="deleteProduct(${product.id})">Delete</button>
            </td>
        `;
        body.appendChild(row);
    });
}

async function setProductId(id) {
    localStorage.setItem('productId', id);
}

function getProductId() {
    return localStorage.getItem('productId');
}

async function getProductById(id) {
    try {
        const response = await fetch(`http://localhost:8080/products/${id}`);
        if (!response.ok) {
            throw new Error('Failed to fetch product details');
        }
        const product = await response.json();
        return product;
    }
    catch (error) {
        console.error('Error fetching product details:', error);
    }
}


async function productUpdateInit(productId)
{

    setProductId(productId);
    console.log(getProductId())
   renderAssociatedPartsTable(productId);
    renderAvailablePartsTable();

   document.querySelector('#addProductPopup').style.display = 'block';

}


async function deleteProduct(id) {
    try {
        const response = await fetch(`http://localhost:8080/products/delete/${id}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            console.log('Product deleted successfully');
            renderProducts(); // Refresh the products table
        } else {
            console.error('Error deleting product:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}




async function submitInhousePartForm(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const partData = Object.fromEntries(formData.entries());

     console.log('Form Data:', partData);


    // Convert inventory and price to the correct data type
    partData.price = parseFloat(partData.price);
    partData.inv = parseInt(partData.inv);

    const partId = document.getElementById('id').value; // Get the value entered in the ID input field

    try {
        const response = await fetch(`http://localhost:8080/inhouseparts/add/${partId}`,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(partData)
        });

        if (response.ok) {
//            const result = await response.json();
//            console.log('Part added successfully:', result);
            closePopup('addInhousePartPopup');
            renderParts(); // Refresh the parts table
        } else {
            console.error('Error adding part:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}


async function submitOutsourcedPartForm(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const partData = Object.fromEntries(formData.entries());

     console.log('Form Data:', partData);


    // Convert inventory and price to the correct data type
    partData.price = parseFloat(partData.price);
    partData.inv = parseInt(partData.inv);

    const companyName = partData.companyName; // Get the value entered in the ID input field

    try {
        const response = await fetch(`http://localhost:8080/outsourcedparts/add/${companyName}`,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(partData)
        });

        if (response.ok) {
//            const result = await response.json();
//            console.log('Part added successfully:', result);
            closePopup('addOutsourcedPartPopup');
            renderParts(); // Refresh the parts table
        } else {
            console.error('Error adding part:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}
async function submitProductForm(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const productData = Object.fromEntries(formData.entries());
    console.log('Form Data:', productData);
    // Convert inventory and price to the correct data type
    productData.price = parseFloat(productData.price);
    productData.inv = parseInt(productData.inv);

    if(getProductId() != 'null'){
        //update
        id = getProductId();
        console.log(id)
        try {
                const response = await fetch(`http://localhost:8080/products/update/${id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(productData)
                });

                if (response.ok) {
                    const result = await response.json();
                    console.log('Product added successfully:', result);
                    selectedProduct = result;
                    selectedProductId = result.id;
                    closePopup('addProductPopup');
                      document.getElementById('addProductForm').name.value = '';
                                        document.getElementById('addProductForm').price.value = 0;
                                        document.getElementById('addProductForm').inv.value =0;
                    renderProducts(); // Refresh the products table
                } else {
                    console.error('Error adding product:', response.statusText);
                }
                }catch (error) {
                                          console.error('Error:', error);
                                      }

    }else{


    try {
        const response = await fetch('http://localhost:8080/products/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(productData)
        });

        if (response.ok) {
            const result = await response.json();
            console.log('Product added successfully:', result);
            selectedProduct = result;
            selectedProductId = result.id;
            closePopup('addProductPopup');
            document.getElementById('addProductForm').name.value = '';
                    document.getElementById('addProductForm').price.value = 0;
                    document.getElementById('addProductForm').inv.value =0;
            renderProducts(); // Refresh the products table
        } else {
            console.error('Error adding product:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}


}

async function associatePartWithProduct(partId) {
selectedProductId = getProductId();
    try {
        const response = await fetch(`http://localhost:8080/products/${selectedProductId}/addPart/${partId}`, {
            method: 'PUT'
        });
        if (response.ok) {
            console.log('Part associated with product successfully');
            renderProducts(); // Refresh the products table
            renderAssociatedPartsTable(getProductId());
            renderAvailablePartsTable();
        } else {
            console.error('Error associating part with product:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

async function disassociatePartWithProduct(partId) {
selectedProductId = getProductId();

    try {
        const response = await fetch(`http://localhost:8080/products/${selectedProductId}/removePart/${partId}`, {
            method: 'PUT'
        });
        if (response.ok) {
            console.log('Part disassociated with product successfully');
            renderAssociatedPartsTable(getProductId());
            renderAvailablePartsTable();
            renderProducts(); // Refresh the products table
        } else {
            console.error('Error disassociating part with product:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}


window.addEventListener('load', () => {
    renderParts();
    renderProducts();
});
