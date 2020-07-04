let title;
let link;

window.onload = () => {
    console.log('test')
    $.ajax({
        type: "GET",
        url: "/getNews",
        success: (result) => {
            console.log(result);
            for (let item in result.items) {
                title = result.items[item].title;
                link = result.items[item].link;
                addNews();
            }
        }
    });
};


addNews = () => {
    let html = `<li>\
                <p class="transition duration-500 ease-in-out transform hover:-translate-y-1 hover:scale-110"><a href=${link} style="text-decoration: none"><strong>${title}</strong></a></p>\
                </li>`;
    $('.messages').append(html);
};









