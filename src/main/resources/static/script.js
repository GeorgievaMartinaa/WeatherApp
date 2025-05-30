document.addEventListener("DOMContentLoaded", function (){

    fetch("http://localhost:8080/api/weather/load", {method: 'GET'})
        .then(response =>
            console.log(response)
        )

    fetch("http://localhost:8080/api/weather/hotDays", {method: 'GET'})
        .then(response => response.json())
        .then(data =>{
            console.log(data)
            const table = document.getElementById("sunny");
            data.forEach(day =>{
                const newRow = document.createElement("tr");
                const cityCell = document.createElement("td");
                const dateCell = document.createElement("td");
                const maxTempCell = document.createElement("td");
                const weatherDescCell = document.createElement("td");

                cityCell.innerText=`${day.city}`;
                dateCell.innerText=`${day.date}`;
                maxTempCell.innerText=`${day.maxTemp}`;
                weatherDescCell.innerText= `${day.rainDesc}`

                newRow.appendChild(cityCell);
                newRow.appendChild(dateCell);
                newRow.appendChild(maxTempCell);
                newRow.appendChild(weatherDescCell);
                table.appendChild(newRow);

            })
        })

    fetch("http://localhost:8080/api/weather/rainyDays", {method: 'GET'})
        .then(response => response.json())
        .then(data =>{
            console.log(data)
            const table = document.getElementById("rainy");

            data.forEach(day =>{
                const newRow = document.createElement("tr");
                const cityCell = document.createElement("td");
                const dateCell = document.createElement("td");
                const rainDescCell = document.createElement("td");

                cityCell.innerText = `${day.city}`;
                dateCell.innerText = `${day.date}`;
                rainDescCell.innerText= `${day.rainDesc}`

                newRow.appendChild(cityCell);
                newRow.appendChild(dateCell);
                newRow.appendChild(rainDescCell);
                table.appendChild(newRow);
            })
        })

})