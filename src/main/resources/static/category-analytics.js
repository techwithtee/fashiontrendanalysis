// Wait for the DOM to be ready
document.addEventListener("DOMContentLoaded", function () {
    // Fetch data from your Spring MVC backend for getCategoriesByTrend
    fetch('/api/categories/trend/1 ') // Replace {trendId} with an actual trend ID
        .then(response => response.json())
        .then(data => {
            // Process and use the data here
            console.log(data); // You can log it to see the retrieved data

            // Create a visualization (e.g., bar chart) for getCategoriesByTrend
            createBarChartForCategoriesByTrend(data);
        })
        .catch(error => {
            console.error('Error fetching data for getCategoriesByTrend:', error);
        });

    // Fetch data from your Spring MVC backend for getCategoriesByProduct
    fetch('/api/categories/product/1') // Replace {productId} with an actual product ID
        .then(response => response.json())
        .then(data => {
            // Process and use the data here
            console.log(data); // You can log it to see the retrieved data

            // Create a visualization (e.g., pie chart) for getCategoriesByProduct
            createPieChartForCategoriesByProduct(data);
        })
        .catch(error => {
            console.error('Error fetching data for getCategoriesByProduct:', error);
        });
});

// Function to create a bar chart for getCategoriesByTrend
function createBarChartForCategoriesByTrend(data) {
    const ctx = document.getElementById('chartByTrend').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: data.map(item => item.categoryName), // Use appropriate property names
            datasets: [{
                label: 'Category Count',
                data: data.map(item => item.categoryCount), // Use appropriate property names
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

// Function to create a pie chart for getCategoriesByProduct
function createPieChartForCategoriesByProduct(data) {
    const ctx = document.getElementById('chartByProduct').getContext('2d');
    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: data.map(item => item.categoryName), // Use appropriate property names
            datasets: [{
                data: data.map(item => item.categoryCount), // Use appropriate property names
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    // Add more colors as needed
                ]
            }]
        }
    });
}