let title;
let description;
let pubDate;

window.onload = () => {
    console.log('test')
    $.ajax({
        type: "GET",
        url: "/getNews",
        success: (result) => {
            console.log(result);
            for (let item in result.items) {
                title = result.items[item].title;
                description = result.items[item].description;
                pubDate = result.items[item].pubDate;

                addNews();
            }
            init();

        }
    });
};


addNews = () => {
    let html = `<div class="news-slider__item swiper-slide">\
                    <a href="#" class="news__item no-underline">\
                    <div class="news-date">\
                    <span class="news-date__title">\
                    <span class="news-date__txt">${pubDate}</span>\
                    </div>\
                    <div class="news__title">${title}</div>\
                    <p class="news__txt">${description}</p>\
                    </a>\
                </div>`;

    $('.swiper-wrapper').append(html);

    // let date = $('<div class="news-date"><span class="news-date__title"></span></div>');
    // date.innerText = pubDate;
    //
    // let newsTitle = $('<div class="news__title"></div>');
    // newsTitle.innerText = title;
    //
    // let newsText = $('<p class="news__txt"></p>');
    // newsText.innerText = description;




};









