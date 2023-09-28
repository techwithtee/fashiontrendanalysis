const API_BASE = "http://localhost:6363/api";

// Fetch and display categories
function fetchCategories() {
    fetch(`${API_BASE}/categories`)
        .then(response => response.json())
        .then(data => {
            let categoriesHtml = '';
            data.forEach(category => {
                // Add code to format and display each category and its associated data
                categoriesHtml += `<div>${category.name}</div>`;
            });
            document.getElementById('categories-list').innerHTML = categoriesHtml;
        });
}

// Fetch and display designers
function fetchDesigners() {
    fetch(`${API_BASE}/designers`)
        .then(response => response.json())
        .then(data => {
            let designersHtml = '';
            data.forEach(designer => {
                // Add code to format and display each designer and its associated data
                designersHtml += `<div>${designer.name}</div>`;
            });
            document.getElementById('designers-list').innerHTML = designersHtml;
        });
}

// Fetch and display products
function fetchProducts() {
    fetch(`${API_BASE}/products`)
        .then(response => response.json())
        .then(data => {
            let productsHtml = '';
            data.forEach(product => {
                // Add code to format and display each product and its associated data
                productsHtml += `<div>${product.name}</div>`;
            });
            document.getElementById('products-list').innerHTML = productsHtml;
        });
}

// Fetch and display trends
function fetchTrends() {
    fetch(`${API_BASE}/trends`)
        .then(response => response.json())
        .then(data => {
            let trendsHtml = '';
            data.forEach(trend => {
                // Add code to format and display each trend and its associated data
                trendsHtml += `<div>${trend.name}</div>`;
            });
            document.getElementById('trends-list').innerHTML = trendsHtml;
        });
}

// Visualization for getCategoriesByTrend
function fetchCategoriesByTrend(trendId) {
    fetch(`${API_BASE}/categories/trend/${trendId}`)
        .then(response => response.json())
        .then(data => {
            const labels = data.map(category => category.name);
            const values = data.map(category => category.value); // Assuming 'value' contains the numeric data to plot
            const ctx = document.getElementById('categoriesByTrendChart').getContext('2d');
            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Categories by Trend',
                        data: values,
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1
                    }]
                }
            });
        });
}

// Visualization for getCategoriesByProduct
function fetchCategoriesByProduct(productId) {
    fetch(`${API_BASE}/categories/product/${productId}`)
        .then(response => response.json())
        .then(data => {
            const labels = data.map(category => category.name);
            const values = data.map(category => category.value);
            const ctx = document.getElementById('categoriesByProductChart').getContext('2d');
            new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: labels,
                    datasets: [{
                        data: values,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)'
                            // ... add more colors if needed
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)'
                            // ... add more colors if needed
                        ],
                        borderWidth: 1
                    }]
                }
            });
        });
}

// Visualization for getCategoryPopularityForSeason
function fetchCategoryPopularityForSeason(categoryId, season) {
    fetch(`${API_BASE}/categories/${categoryId}/popularity/${season}`)
        .then(response => response.json())
        .then(data => {
            const ctx = document.getElementById('categoryPopularityForSeasonChart').getContext('2d');
            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ['Winter', 'Spring', 'Summer', 'Fall'], // Adjust if needed
                    datasets: [{
                        label: `Popularity for ${season}`,
                        data: data, // Assuming the data is an array of numbers
                        fill: false,
                        borderColor: 'rgb(75, 192, 192)',
                        tension: 0.1
                    }]
                }
            });
        });
}

// Visualization for getAllCategoryPopularities
function fetchAllCategoryPopularities(categoryId) {
    fetch(`${API_BASE}/categories/${categoryId}/all-popularities`)
        .then(response => response.json())
        .then(data => {
            const ctx = document.getElementById('allCategoryPopularitiesChart').getContext('2d');
            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: data.map(item => item.season), // Assuming each item has a 'season'
                    datasets: [{
                        label: 'Popularity Across Seasons',
                        data: data.map(item => item.popularity), // Assuming each item has a 'popularity' value
                        backgroundColor: 'rgba(153, 102, 255, 0.2)',
                        borderColor: 'rgba(153, 102, 255, 1)',
                        borderWidth: 1
                    }]
                }
            });
        });
}

// Call the fetch functions to populate the data on page load
window.onload = function() {
    fetchCategories();
    fetchDesigners();
    fetchProducts();
    fetchTrends();

    // New fetch functions for visualization
    fetchCategoriesByTrend(1);
    fetchCategoriesByProduct(1);
    fetchCategoryPopularityForSeason(1, 'Fall');
    fetchAllCategoryPopularities(1);
};
