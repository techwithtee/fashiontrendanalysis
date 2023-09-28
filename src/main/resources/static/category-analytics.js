// Wait for the DOM to be ready
document.addEventListener("DOMContentLoaded", function () {
    // Fetch data from your Spring MVC backend for getCategoriesByTrend
    fetch('/api/categories/trend/1')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok: ' + response.statusText);
            }
            return response.json();
        })
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

    // Fetch data from your Spring MVC backend for getCategoryPopularityForSeason
    fetch('/api/categories/1/popularity/Fall') // Replace {categoryId} and {season} with actual values
        .then(response => response.json())
        .then(data => {
            // Process and use the data here
            console.log(data); // You can log it to see the retrieved data

            // Create a visualization (e.g., line chart) for getCategoryPopularityForSeason
            createLineChartForCategoryPopularity(data);
        })
        .catch(error => {
            console.error('Error fetching data for getCategoryPopularityForSeason:', error);
        });

    // Fetch data from your Spring MVC backend for getAllCategoryPopularities
    fetch('/api/categories/1/all-popularities') // Replace {categoryId} with an actual category ID
        .then(response => response.json())
        .then(data => {
            // Process and use the data here
            console.log(data); // You can log it to see the retrieved data

            // Create a visualization (e.g., bar chart) for getAllCategoryPopularities
            createBarChartForAllCategoryPopularities(data);
        })
        .catch(error => {
            console.error('Error fetching data for getAllCategoryPopularities:', error);
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

// Function to create a line chart for getCategoryPopularityForSeason
function createLineChartForCategoryPopularity(data) {
    const ctx = document.getElementById('chartCategoryPopularity').getContext('2d');
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: data.map(item => item.season), // Use appropriate property names
            datasets: [{
                label: 'Popularity Score',
                data: data.map(item => item.popularityScore), // Use appropriate property names
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1,
                fill: false
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

// Function to create a bar chart for getAllCategoryPopularities
function createBarChartForAllCategoryPopularities(data) {
    const ctx = document.getElementById('chartAllPopularities').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: data.map(item => item.season), // Use appropriate property names
            datasets: [{
                label: 'Popularity Scores',
                data: data.map(item => item.popularityScore), // Use appropriate property names
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

