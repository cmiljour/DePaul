const axios = require('axios');

function fetchData() {
    // you might need the next line, depending on your API provider.
    axios.defaults.headers.post['Content-Type'] = 'application/json';
    axios.get('https://hourlypricing.comed.com/api?type=5minutefeed', {/* here you can pass any parameters you want */})
    .then((response) => {
      // Here you can handle the API response
      // Maybe you want to add to your HTML via JavaScript?
      // let result = (integer > 6) ? "way too much" : "just right";

      document.getElementById("comed").innerHTML = 
        response.data[0].price;
        (parseInt(response.data[0].price, 10) > 6)
        ? document.body.style.backgroundColor = "red"
        : document.body.style.backgroundColor = "green";
    })
    .catch((error) => {
      console.error(error);
    });
    var c = setTimeout(fetchData, 600000);
  }
  
fetchData()

