$(document).ready(()=>{
    $(window).scroll(()=>{
        var scroll = $(window).scrollTop();
        if(scroll>0){
            $("#navbar").css("background-color","white");
            $("#navbar").css("z-index","100");
            $("#navbar").css("position","fixed");
            $(".change").css("color","black");

        }
        else{
            $("#navbar").css("background-color","transparent");
            $("#navbar").css("z-index","0");
            $("#navbar").css("position","relative");
            $(".change").css("color","white");



        }
    })
})
