// Define a global variable to store category data
let allCategoriesData = [];

// Function to create a bar chart
function createBarChart(chartId, labels, data, chartTitle) {
    const ctx = document.getElementById(chartId).getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: chartTitle,
                data: data,
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

// Function to fetch category popularity data
function fetchCategoryPopularities() {
    fetch('/{categoryId}/all-popularities')
        .then(response => response.json())
        .then(data => {
            // Data has been fetched, you can use it for visualization
            allCategoriesData = data;

            // Now, create or update your chart
            createBarChart('categoryPopularityChart', ['Category 1', 'Category 2', 'Category 3', 'Category 4', 'Category 5'], allCategoriesData, 'Category Popularity');
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}

// Call the function to fetch data when your page loads or as needed
fetchCategoryPopularities();
