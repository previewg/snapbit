var content = document.querySelector('.content');
var duplicate = content.cloneNode(true);
var contentBlurred = document.createElement('div');
contentBlurred.className = 'content-blurred';
contentBlurred.appendChild(duplicate);


var contentWrapper = document.querySelector('.content-wrapper'),
    translation;

contentWrapper.addEventListener('scroll',function(){
    translation = 'translate3d(0,' + (-this.scrollTop + 'px') + ',0)';
    duplicate.style['-webkit-transform'] = translation;
    duplicate.style['-moz-transform'] = translation;
    duplicate.style['-ms-transform'] = translation;
    duplicate.style['transform'] = translation;

    console.log(duplicate);
});

// offset to demo blurring
contentWrapper.scrollTop = 140;


