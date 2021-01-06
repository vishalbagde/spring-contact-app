
console.log("this is java script");
const toggleSidebar = ()=>{
   
    if($(".sidebar").is(":visible"))
    {
        $(".sidebar").css("display","none");
        $(".content").css("margin-left","0%");
        $(".togglebar").css("display","block");
        
    }else{
    
        $(".sidebar").css("display","block");
        $(".content").css("margin-left","20%");
        $(".togglebar").css("display","none");
    }
}


