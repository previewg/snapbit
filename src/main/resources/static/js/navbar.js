$(document).ready(()=>{
    $(window).scroll(()=>{
        var scroll = $(window).scrollTop();
        if(scroll>1){
            $("#navbar").css("background-color","white");
            $("#navbar").css("position","fixed");
            $(".change").css("color","black");

        }
        else{
            $("#navbar").css("background-color","transparent");
            $(".change").css("color","white");



        }
    })
})
