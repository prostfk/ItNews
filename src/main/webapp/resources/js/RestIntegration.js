function getIndexNews() {
    fetch('/rest/news/1').then(function (response) {
        response.json().then(function (data) {
            console.log(data);
        })
    })
};