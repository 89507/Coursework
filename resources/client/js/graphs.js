function pageLoadgraphs() {

    const canvas = document.getElementById('chartCanvas');
    const context = canvas.getContext('2d');

    let myChart = new Chart(context, {
        type: 'bar',   // change this to 'pie' if wanted
        data: {
            labels: [
                'Red things',
                'Blue things',
                'Yellow things',
                'Green things',
                'Purple things',
                'Orange things'
            ],
            datasets: [{
                label: 'Number of things',
                data: [
                    12,
                    19,
                    3,
                    5,
                    2,
                    3
                ],
                backgroundColor: [
                    'red',
                    'blue',
                    'yellow',
                    'green',
                    'purple',
                    'orange'
                ]
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            responsive: false
        }
    });

}

