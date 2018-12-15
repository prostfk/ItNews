function getNews(page){
    fetch(`/api/news/${page}`).then(response => {
        return response.json()
    }).then(data => {
        if (data.error === undefined) {
            let news = `<div>`;
            newsObjs = data;
            for (let i = 0; i < data.length; i++) {
                news += `
                        <div class="jumbotron">
                    <blockquote>
                        <p>${data[i].title}</p>
                        <small>${data[i].type}</small>
                    </blockquote>
                </div>
                <img src="${data[i].pathToFile}" style="max-height: 300px;max-width: 600px" class="thumbnail"/>
                <p style="overflow: hidden;max-height: 65px">${data[i].content}</p>
                <a href="/article/${data[i].id}" class="btn btn-primary">READ MORE</a>
                <div style="border: 1px solid silver;margin-bottom: 1%; margin-top: 1%"></div>
                    `
            }
            console.log(data);
            document.getElementById('div-news').innerHTML = news;
        }else{
            console.log(data.error);
        }
    })
}
