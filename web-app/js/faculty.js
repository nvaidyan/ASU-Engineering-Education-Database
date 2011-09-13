jQuery(document).ready(function(){
    jQuery("facultySlides")
    .before("<aside id='tabs'")
    .cycle({
       fx:"shuffle",
       speed:"medium",
       pager:"#tabs"
    });
});