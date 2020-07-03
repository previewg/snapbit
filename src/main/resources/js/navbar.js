$(document).ready(()=>{
    $(window).scroll(()=>{
        var scroll = $(window).scrollTop();
        if(scroll>600){
            $("#navbar").css("background","white");
            $("#navbar").css("position","fixed");
            $("#navbar").css("z-index","1");
            $(".change").css("color","black");

        }
        else{
            $("#navbar").css("background","none");
            $("#navbar").css("position","relative");
            $("#navbar").css("z-index","0");
            $(".change").css("color","white");


        }
    })
})
